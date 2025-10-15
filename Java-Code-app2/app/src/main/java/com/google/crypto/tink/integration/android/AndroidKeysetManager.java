package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.KeysetManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.ProviderException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\AndroidKeysetManager.smali */
public final class AndroidKeysetManager {
    private static final String TAG = AndroidKeysetManager.class.getSimpleName();
    private KeysetManager keysetManager;
    private final Aead masterKey;
    private final KeysetWriter writer;

    /* synthetic */ AndroidKeysetManager(Builder x0, AnonymousClass1 x1) throws GeneralSecurityException, IOException {
        this(x0);
    }

    private AndroidKeysetManager(Builder builder) throws GeneralSecurityException, IOException {
        this.writer = builder.writer;
        this.masterKey = builder.masterKey;
        this.keysetManager = builder.keysetManager;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\AndroidKeysetManager$Builder.smali */
    public static final class Builder {
        private KeysetManager keysetManager;
        private KeysetReader reader = null;
        private KeysetWriter writer = null;
        private String masterKeyUri = null;
        private Aead masterKey = null;
        private boolean useKeystore = true;
        private KeyTemplate keyTemplate = null;
        private KeyStore keyStore = null;

        public Builder withSharedPref(Context context, String keysetName, String prefFileName) throws IOException {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            if (keysetName == null) {
                throw new IllegalArgumentException("need a keyset name");
            }
            this.reader = new SharedPrefKeysetReader(context, keysetName, prefFileName);
            this.writer = new SharedPrefKeysetWriter(context, keysetName, prefFileName);
            return this;
        }

        public Builder withMasterKeyUri(String val) {
            if (!val.startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            }
            if (!this.useKeystore) {
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            this.masterKeyUri = val;
            return this;
        }

        @Deprecated
        public Builder withKeyTemplate(com.google.crypto.tink.proto.KeyTemplate val) {
            this.keyTemplate = KeyTemplate.create(val.getTypeUrl(), val.getValue().toByteArray(), AndroidKeysetManager.fromProto(val.getOutputPrefixType()));
            return this;
        }

        public Builder withKeyTemplate(KeyTemplate val) {
            this.keyTemplate = val;
            return this;
        }

        @Deprecated
        public Builder doNotUseKeystore() {
            this.masterKeyUri = null;
            this.useKeystore = false;
            return this;
        }

        Builder withKeyStore(KeyStore val) {
            this.keyStore = val;
            return this;
        }

        public synchronized AndroidKeysetManager build() throws GeneralSecurityException, IOException {
            if (this.masterKeyUri != null) {
                this.masterKey = readOrGenerateNewMasterKey();
            }
            this.keysetManager = readOrGenerateNewKeyset();
            return new AndroidKeysetManager(this, null);
        }

        private Aead readOrGenerateNewMasterKey() throws GeneralSecurityException {
            AndroidKeystoreKmsClient client;
            if (!AndroidKeysetManager.isAtLeastM()) {
                Log.w(AndroidKeysetManager.TAG, "Android Keystore requires at least Android M");
                return null;
            }
            if (this.keyStore != null) {
                client = new AndroidKeystoreKmsClient.Builder().setKeyStore(this.keyStore).build();
            } else {
                client = new AndroidKeystoreKmsClient();
            }
            boolean existed = client.hasKey(this.masterKeyUri);
            if (!existed) {
                try {
                    AndroidKeystoreKmsClient.generateNewAeadKey(this.masterKeyUri);
                } catch (GeneralSecurityException | ProviderException ex) {
                    Log.w(AndroidKeysetManager.TAG, "cannot use Android Keystore, it'll be disabled", ex);
                    return null;
                }
            }
            try {
                return client.getAead(this.masterKeyUri);
            } catch (GeneralSecurityException | ProviderException ex2) {
                if (!existed) {
                    Log.w(AndroidKeysetManager.TAG, "cannot use Android Keystore, it'll be disabled", ex2);
                    return null;
                }
                throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.masterKeyUri), ex2);
            }
        }

        private KeysetManager readOrGenerateNewKeyset() throws GeneralSecurityException, IOException {
            try {
                return read();
            } catch (FileNotFoundException ex) {
                Log.w(AndroidKeysetManager.TAG, "keyset not found, will generate a new one", ex);
                if (this.keyTemplate != null) {
                    KeysetManager manager = KeysetManager.withEmptyKeyset().add(this.keyTemplate);
                    int keyId = manager.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId();
                    KeysetManager manager2 = manager.setPrimary(keyId);
                    if (this.masterKey != null) {
                        manager2.getKeysetHandle().write(this.writer, this.masterKey);
                    } else {
                        CleartextKeysetHandle.write(manager2.getKeysetHandle(), this.writer);
                    }
                    return manager2;
                }
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
        }

        private KeysetManager read() throws GeneralSecurityException, IOException {
            Aead aead = this.masterKey;
            if (aead != null) {
                try {
                    return KeysetManager.withKeysetHandle(KeysetHandle.read(this.reader, aead));
                } catch (InvalidProtocolBufferException | GeneralSecurityException ex) {
                    Log.w(AndroidKeysetManager.TAG, "cannot decrypt keyset: ", ex);
                }
            }
            return KeysetManager.withKeysetHandle(CleartextKeysetHandle.read(this.reader));
        }
    }

    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return this.keysetManager.getKeysetHandle();
    }

    @Deprecated
    public synchronized AndroidKeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager keysetManagerRotate = this.keysetManager.rotate(keyTemplate);
        this.keysetManager = keysetManagerRotate;
        write(keysetManagerRotate);
        return this;
    }

    @Deprecated
    public synchronized AndroidKeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager keysetManagerAdd = this.keysetManager.add(keyTemplate);
        this.keysetManager = keysetManagerAdd;
        write(keysetManagerAdd);
        return this;
    }

    public synchronized AndroidKeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeysetManager keysetManagerAdd = this.keysetManager.add(keyTemplate);
        this.keysetManager = keysetManagerAdd;
        write(keysetManagerAdd);
        return this;
    }

    public synchronized AndroidKeysetManager setPrimary(int keyId) throws GeneralSecurityException {
        KeysetManager primary = this.keysetManager.setPrimary(keyId);
        this.keysetManager = primary;
        write(primary);
        return this;
    }

    @Deprecated
    public synchronized AndroidKeysetManager promote(int keyId) throws GeneralSecurityException {
        return setPrimary(keyId);
    }

    public synchronized AndroidKeysetManager enable(int keyId) throws GeneralSecurityException {
        KeysetManager keysetManagerEnable = this.keysetManager.enable(keyId);
        this.keysetManager = keysetManagerEnable;
        write(keysetManagerEnable);
        return this;
    }

    public synchronized AndroidKeysetManager disable(int keyId) throws GeneralSecurityException {
        KeysetManager keysetManagerDisable = this.keysetManager.disable(keyId);
        this.keysetManager = keysetManagerDisable;
        write(keysetManagerDisable);
        return this;
    }

    public synchronized AndroidKeysetManager delete(int keyId) throws GeneralSecurityException {
        KeysetManager keysetManagerDelete = this.keysetManager.delete(keyId);
        this.keysetManager = keysetManagerDelete;
        write(keysetManagerDelete);
        return this;
    }

    public synchronized AndroidKeysetManager destroy(int keyId) throws GeneralSecurityException {
        KeysetManager keysetManagerDestroy = this.keysetManager.destroy(keyId);
        this.keysetManager = keysetManagerDestroy;
        write(keysetManagerDestroy);
        return this;
    }

    public synchronized boolean isUsingKeystore() {
        return shouldUseKeystore();
    }

    private void write(KeysetManager manager) throws GeneralSecurityException {
        try {
            if (shouldUseKeystore()) {
                manager.getKeysetHandle().write(this.writer, this.masterKey);
            } else {
                CleartextKeysetHandle.write(manager.getKeysetHandle(), this.writer);
            }
        } catch (IOException e) {
            throw new GeneralSecurityException(e);
        }
    }

    private boolean shouldUseKeystore() {
        return this.masterKey != null && isAtLeastM();
    }

    /* renamed from: com.google.crypto.tink.integration.android.AndroidKeysetManager$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\AndroidKeysetManager$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTemplate.OutputPrefixType fromProto(OutputPrefixType outputPrefixType) {
        int i = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i == 1) {
            return KeyTemplate.OutputPrefixType.TINK;
        }
        if (i == 2) {
            return KeyTemplate.OutputPrefixType.LEGACY;
        }
        if (i == 3) {
            return KeyTemplate.OutputPrefixType.RAW;
        }
        if (i == 4) {
            return KeyTemplate.OutputPrefixType.CRUNCHY;
        }
        throw new IllegalArgumentException("Unknown output prefix type");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
