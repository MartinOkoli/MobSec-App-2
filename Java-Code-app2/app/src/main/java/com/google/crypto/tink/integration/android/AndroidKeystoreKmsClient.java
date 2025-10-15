package com.google.crypto.tink.integration.android;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\AndroidKeystoreKmsClient.smali */
public final class AndroidKeystoreKmsClient implements KmsClient {
    public static final String PREFIX = "android-keystore://";
    private static final String TAG = AndroidKeystoreKmsClient.class.getSimpleName();
    private static final int WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 20;
    private KeyStore keyStore;
    private final String keyUri;

    public AndroidKeystoreKmsClient() throws GeneralSecurityException {
        this(new Builder());
    }

    @Deprecated
    public AndroidKeystoreKmsClient(String uri) {
        this(new Builder().setKeyUri(uri));
    }

    private AndroidKeystoreKmsClient(Builder builder) {
        this.keyUri = builder.keyUri;
        this.keyStore = builder.keyStore;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\AndroidKeystoreKmsClient$Builder.smali */
    public static final class Builder {
        KeyStore keyStore;
        String keyUri = null;

        public Builder() {
            this.keyStore = null;
            if (!AndroidKeystoreKmsClient.isAtLeastM()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
            } catch (IOException | GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }

        public Builder setKeyUri(String val) {
            if (val == null || !val.toLowerCase(Locale.US).startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("val must start with android-keystore://");
            }
            this.keyUri = val;
            return this;
        }

        public Builder setKeyStore(KeyStore val) {
            if (val == null) {
                throw new IllegalArgumentException("val cannot be null");
            }
            this.keyStore = val;
            return this;
        }

        public AndroidKeystoreKmsClient build() {
            return new AndroidKeystoreKmsClient(this);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0021  */
    @Override // com.google.crypto.tink.KmsClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean doesSupport(java.lang.String r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = r3.keyUri     // Catch: java.lang.Throwable -> L24
            r1 = 1
            if (r0 == 0) goto Le
            boolean r0 = r0.equals(r4)     // Catch: java.lang.Throwable -> L24
            if (r0 == 0) goto Le
            monitor-exit(r3)
            return r1
        Le:
            java.lang.String r0 = r3.keyUri     // Catch: java.lang.Throwable -> L24
            if (r0 != 0) goto L21
            java.util.Locale r0 = java.util.Locale.US     // Catch: java.lang.Throwable -> L24
            java.lang.String r0 = r4.toLowerCase(r0)     // Catch: java.lang.Throwable -> L24
            java.lang.String r2 = "android-keystore://"
            boolean r0 = r0.startsWith(r2)     // Catch: java.lang.Throwable -> L24
            if (r0 == 0) goto L21
            goto L22
        L21:
            r1 = 0
        L22:
            monitor-exit(r3)
            return r1
        L24:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient.doesSupport(java.lang.String):boolean");
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withCredentials(String unused) throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withDefaultCredentials() throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public synchronized Aead getAead(String uri) throws GeneralSecurityException {
        Aead aead;
        String str = this.keyUri;
        if (str != null && !str.equals(uri)) {
            throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.keyUri, uri));
        }
        aead = new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, uri), this.keyStore);
        return validateAead(aead);
    }

    public synchronized void deleteKey(String keyUri) throws GeneralSecurityException {
        String keyId = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        this.keyStore.deleteEntry(keyId);
    }

    synchronized boolean hasKey(String keyUri) throws GeneralSecurityException {
        String keyId;
        keyId = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        try {
        } catch (NullPointerException e) {
            Log.w(TAG, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
            try {
                Thread.sleep(20L);
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
            } catch (IOException ex2) {
                throw new GeneralSecurityException(ex2);
            } catch (InterruptedException e2) {
            }
            return this.keyStore.containsAlias(keyId);
        }
        return this.keyStore.containsAlias(keyId);
    }

    public static Aead getOrGenerateNewAeadKey(String keyUri) throws GeneralSecurityException, IOException {
        AndroidKeystoreKmsClient client = new AndroidKeystoreKmsClient();
        if (!client.hasKey(keyUri)) {
            Log.w(TAG, String.format("key URI %s doesn't exist, generating a new one", keyUri));
            generateNewAeadKey(keyUri);
        }
        return client.getAead(keyUri);
    }

    public static void generateNewAeadKey(String keyUri) throws GeneralSecurityException {
        AndroidKeystoreKmsClient client = new AndroidKeystoreKmsClient();
        if (client.hasKey(keyUri)) {
            throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", keyUri));
        }
        String keyId = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, keyUri);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(keyId, 3).setKeySize(256).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build();
        keyGenerator.init(spec);
        keyGenerator.generateKey();
    }

    private static Aead validateAead(Aead aead) throws GeneralSecurityException {
        byte[] message = Random.randBytes(10);
        byte[] aad = new byte[0];
        byte[] ciphertext = aead.encrypt(message, aad);
        byte[] decrypted = aead.decrypt(ciphertext, aad);
        if (!Arrays.equals(message, decrypted)) {
            throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
        }
        return aead;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
