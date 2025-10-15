package androidx.security.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import androidx.collection.ArraySet;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.daead.AesSivKeyManager;
import com.google.crypto.tink.daead.DeterministicAeadConfig;
import com.google.crypto.tink.integration.android.AndroidKeysetManager;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.google.crypto.tink.subtle.Base64;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\security\crypto\EncryptedSharedPreferences.smali */
public final class EncryptedSharedPreferences implements SharedPreferences {
    private static final String KEY_KEYSET_ALIAS = "__androidx_security_crypto_encrypted_prefs_key_keyset__";
    private static final String NULL_VALUE = "__NULL__";
    private static final String VALUE_KEYSET_ALIAS = "__androidx_security_crypto_encrypted_prefs_value_keyset__";
    final String mFileName;
    final DeterministicAead mKeyDeterministicAead;
    final List<SharedPreferences.OnSharedPreferenceChangeListener> mListeners = new ArrayList();
    final String mMasterKeyAlias;
    final SharedPreferences mSharedPreferences;
    final Aead mValueAead;

    EncryptedSharedPreferences(String name, String masterKeyAlias, SharedPreferences sharedPreferences, Aead aead, DeterministicAead deterministicAead) {
        this.mFileName = name;
        this.mSharedPreferences = sharedPreferences;
        this.mMasterKeyAlias = masterKeyAlias;
        this.mValueAead = aead;
        this.mKeyDeterministicAead = deterministicAead;
    }

    public static SharedPreferences create(Context context, String fileName, MasterKey masterKey, PrefKeyEncryptionScheme prefKeyEncryptionScheme, PrefValueEncryptionScheme prefValueEncryptionScheme) throws GeneralSecurityException, IOException {
        return create(fileName, masterKey.getKeyAlias(), context, prefKeyEncryptionScheme, prefValueEncryptionScheme);
    }

    @Deprecated
    public static SharedPreferences create(String fileName, String masterKeyAlias, Context context, PrefKeyEncryptionScheme prefKeyEncryptionScheme, PrefValueEncryptionScheme prefValueEncryptionScheme) throws GeneralSecurityException, IOException {
        DeterministicAeadConfig.register();
        AeadConfig.register();
        Context applicationContext = context.getApplicationContext();
        KeysetHandle daeadKeysetHandle = new AndroidKeysetManager.Builder().withKeyTemplate(prefKeyEncryptionScheme.getKeyTemplate()).withSharedPref(applicationContext, KEY_KEYSET_ALIAS, fileName).withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + masterKeyAlias).build().getKeysetHandle();
        KeysetHandle aeadKeysetHandle = new AndroidKeysetManager.Builder().withKeyTemplate(prefValueEncryptionScheme.getKeyTemplate()).withSharedPref(applicationContext, VALUE_KEYSET_ALIAS, fileName).withMasterKeyUri(AndroidKeystoreKmsClient.PREFIX + masterKeyAlias).build().getKeysetHandle();
        DeterministicAead daead = (DeterministicAead) daeadKeysetHandle.getPrimitive(DeterministicAead.class);
        Aead aead = (Aead) aeadKeysetHandle.getPrimitive(Aead.class);
        return new EncryptedSharedPreferences(fileName, masterKeyAlias, applicationContext.getSharedPreferences(fileName, 0), aead, daead);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\security\crypto\EncryptedSharedPreferences$PrefKeyEncryptionScheme.smali */
    public enum PrefKeyEncryptionScheme {
        AES256_SIV(AesSivKeyManager.aes256SivTemplate());

        private final KeyTemplate mDeterministicAeadKeyTemplate;

        PrefKeyEncryptionScheme(KeyTemplate keyTemplate) {
            this.mDeterministicAeadKeyTemplate = keyTemplate;
        }

        KeyTemplate getKeyTemplate() {
            return this.mDeterministicAeadKeyTemplate;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\security\crypto\EncryptedSharedPreferences$PrefValueEncryptionScheme.smali */
    public enum PrefValueEncryptionScheme {
        AES256_GCM(AesGcmKeyManager.aes256GcmTemplate());

        private final KeyTemplate mAeadKeyTemplate;

        PrefValueEncryptionScheme(KeyTemplate keyTemplates) {
            this.mAeadKeyTemplate = keyTemplates;
        }

        KeyTemplate getKeyTemplate() {
            return this.mAeadKeyTemplate;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\security\crypto\EncryptedSharedPreferences$Editor.smali */
    private static final class Editor implements SharedPreferences.Editor {
        private final SharedPreferences.Editor mEditor;
        private final EncryptedSharedPreferences mEncryptedSharedPreferences;
        private AtomicBoolean mClearRequested = new AtomicBoolean(false);
        private final List<String> mKeysChanged = new CopyOnWriteArrayList();

        Editor(EncryptedSharedPreferences encryptedSharedPreferences, SharedPreferences.Editor editor) {
            this.mEncryptedSharedPreferences = encryptedSharedPreferences;
            this.mEditor = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String key, String value) {
            if (value == null) {
                value = EncryptedSharedPreferences.NULL_VALUE;
            }
            byte[] stringBytes = value.getBytes(StandardCharsets.UTF_8);
            int stringByteLength = stringBytes.length;
            ByteBuffer buffer = ByteBuffer.allocate(stringByteLength + 8);
            buffer.putInt(EncryptedType.STRING.getId());
            buffer.putInt(stringByteLength);
            buffer.put(stringBytes);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            if (values == null) {
                values = new ArraySet();
                values.add(EncryptedSharedPreferences.NULL_VALUE);
            }
            List<byte[]> byteValues = new ArrayList<>(values.size());
            int totalBytes = values.size() * 4;
            for (String strValue : values) {
                byte[] byteValue = strValue.getBytes(StandardCharsets.UTF_8);
                byteValues.add(byteValue);
                totalBytes += byteValue.length;
            }
            ByteBuffer buffer = ByteBuffer.allocate(totalBytes + 4);
            buffer.putInt(EncryptedType.STRING_SET.getId());
            for (byte[] bytes : byteValues) {
                buffer.putInt(bytes.length);
                buffer.put(bytes);
            }
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String key, int value) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putInt(EncryptedType.INT.getId());
            buffer.putInt(value);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String key, long value) {
            ByteBuffer buffer = ByteBuffer.allocate(12);
            buffer.putInt(EncryptedType.LONG.getId());
            buffer.putLong(value);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String key, float value) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            buffer.putInt(EncryptedType.FLOAT.getId());
            buffer.putFloat(value);
            putEncryptedObject(key, buffer.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(5);
            byteBufferAllocate.putInt(EncryptedType.BOOLEAN.getId());
            byteBufferAllocate.put(z ? (byte) 1 : (byte) 0);
            putEncryptedObject(str, byteBufferAllocate.array());
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String key) {
            if (this.mEncryptedSharedPreferences.isReservedKey(key)) {
                throw new SecurityException(key + " is a reserved key for the encryption keyset.");
            }
            this.mEditor.remove(this.mEncryptedSharedPreferences.encryptKey(key));
            this.mKeysChanged.remove(key);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.mClearRequested.set(true);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            clearKeysIfNeeded();
            try {
                return this.mEditor.commit();
            } finally {
                notifyListeners();
                this.mKeysChanged.clear();
            }
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            clearKeysIfNeeded();
            this.mEditor.apply();
            notifyListeners();
            this.mKeysChanged.clear();
        }

        private void clearKeysIfNeeded() {
            if (this.mClearRequested.getAndSet(false)) {
                for (String key : this.mEncryptedSharedPreferences.getAll().keySet()) {
                    if (!this.mKeysChanged.contains(key) && !this.mEncryptedSharedPreferences.isReservedKey(key)) {
                        this.mEditor.remove(this.mEncryptedSharedPreferences.encryptKey(key));
                    }
                }
            }
        }

        private void putEncryptedObject(String key, byte[] value) {
            if (this.mEncryptedSharedPreferences.isReservedKey(key)) {
                throw new SecurityException(key + " is a reserved key for the encryption keyset.");
            }
            this.mKeysChanged.add(key);
            if (key == null) {
                key = EncryptedSharedPreferences.NULL_VALUE;
            }
            try {
                Pair<String, String> encryptedPair = this.mEncryptedSharedPreferences.encryptKeyValuePair(key, value);
                this.mEditor.putString((String) encryptedPair.first, (String) encryptedPair.second);
            } catch (GeneralSecurityException ex) {
                throw new SecurityException("Could not encrypt data: " + ex.getMessage(), ex);
            }
        }

        private void notifyListeners() {
            for (SharedPreferences.OnSharedPreferenceChangeListener listener : this.mEncryptedSharedPreferences.mListeners) {
                for (String key : this.mKeysChanged) {
                    listener.onSharedPreferenceChanged(this.mEncryptedSharedPreferences, key);
                }
            }
        }
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        Map<String, ? super Object> allEntries = new HashMap<>();
        for (Map.Entry<String, ?> entry : this.mSharedPreferences.getAll().entrySet()) {
            if (!isReservedKey(entry.getKey())) {
                String decryptedKey = decryptKey(entry.getKey());
                allEntries.put(decryptedKey, getDecryptedObject(decryptedKey));
            }
        }
        return allEntries;
    }

    @Override // android.content.SharedPreferences
    public String getString(String key, String defValue) {
        Object value = getDecryptedObject(key);
        return (value == null || !(value instanceof String)) ? defValue : (String) value;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String key, Set<String> defValues) {
        Set<String> returnValues;
        Object value = getDecryptedObject(key);
        if (value instanceof Set) {
            returnValues = (Set) value;
        } else {
            returnValues = new ArraySet<>();
        }
        return returnValues.size() > 0 ? returnValues : defValues;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String key, int defValue) {
        Object value = getDecryptedObject(key);
        return (value == null || !(value instanceof Integer)) ? defValue : ((Integer) value).intValue();
    }

    @Override // android.content.SharedPreferences
    public long getLong(String key, long defValue) {
        Object value = getDecryptedObject(key);
        return (value == null || !(value instanceof Long)) ? defValue : ((Long) value).longValue();
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String key, float defValue) {
        Object value = getDecryptedObject(key);
        return (value == null || !(value instanceof Float)) ? defValue : ((Float) value).floatValue();
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String key, boolean defValue) {
        Object value = getDecryptedObject(key);
        return (value == null || !(value instanceof Boolean)) ? defValue : ((Boolean) value).booleanValue();
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String key) {
        if (isReservedKey(key)) {
            throw new SecurityException(key + " is a reserved key for the encryption keyset.");
        }
        String encryptedKey = encryptKey(key);
        return this.mSharedPreferences.contains(encryptedKey);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new Editor(this, this.mSharedPreferences.edit());
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        this.mListeners.add(listener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        this.mListeners.remove(listener);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\security\crypto\EncryptedSharedPreferences$EncryptedType.smali */
    private enum EncryptedType {
        STRING(0),
        STRING_SET(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        BOOLEAN(5);

        private final int mId;

        EncryptedType(int id) {
            this.mId = id;
        }

        public int getId() {
            return this.mId;
        }

        public static EncryptedType fromId(int id) {
            if (id == 0) {
                return STRING;
            }
            if (id == 1) {
                return STRING_SET;
            }
            if (id == 2) {
                return INT;
            }
            if (id == 3) {
                return LONG;
            }
            if (id == 4) {
                return FLOAT;
            }
            if (id == 5) {
                return BOOLEAN;
            }
            return null;
        }
    }

    private Object getDecryptedObject(String key) {
        String key2;
        if (isReservedKey(key)) {
            throw new SecurityException(key + " is a reserved key for the encryption keyset.");
        }
        if (key != null) {
            key2 = key;
        } else {
            key2 = NULL_VALUE;
        }
        try {
            String encryptedKey = encryptKey(key2);
            String encryptedValue = this.mSharedPreferences.getString(encryptedKey, null);
            if (encryptedValue == null) {
                return null;
            }
            byte[] cipherText = Base64.decode(encryptedValue, 0);
            byte[] value = this.mValueAead.decrypt(cipherText, encryptedKey.getBytes(StandardCharsets.UTF_8));
            ByteBuffer buffer = ByteBuffer.wrap(value);
            buffer.position(0);
            int typeId = buffer.getInt();
            EncryptedType type = EncryptedType.fromId(typeId);
            switch (AnonymousClass1.$SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[type.ordinal()]) {
                case 1:
                    int stringLength = buffer.getInt();
                    ByteBuffer stringSlice = buffer.slice();
                    buffer.limit(stringLength);
                    String stringValue = StandardCharsets.UTF_8.decode(stringSlice).toString();
                    if (stringValue.equals(NULL_VALUE)) {
                        return null;
                    }
                    return stringValue;
                case 2:
                    Object returnValue = Integer.valueOf(buffer.getInt());
                    return returnValue;
                case 3:
                    Object returnValue2 = Long.valueOf(buffer.getLong());
                    return returnValue2;
                case 4:
                    Object returnValue3 = Float.valueOf(buffer.getFloat());
                    return returnValue3;
                case 5:
                    Object returnValue4 = Boolean.valueOf(buffer.get() != 0);
                    return returnValue4;
                case 6:
                    ArraySet<String> stringSet = new ArraySet<>();
                    while (buffer.hasRemaining()) {
                        int subStringLength = buffer.getInt();
                        ByteBuffer subStringSlice = buffer.slice();
                        subStringSlice.limit(subStringLength);
                        buffer.position(buffer.position() + subStringLength);
                        stringSet.add(StandardCharsets.UTF_8.decode(subStringSlice).toString());
                    }
                    if (stringSet.size() == 1 && NULL_VALUE.equals(stringSet.valueAt(0))) {
                        return null;
                    }
                    return stringSet;
                default:
                    return null;
            }
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("Could not decrypt value. " + ex.getMessage(), ex);
        }
    }

    /* renamed from: androidx.security.crypto.EncryptedSharedPreferences$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\security\crypto\EncryptedSharedPreferences$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType;

        static {
            int[] iArr = new int[EncryptedType.values().length];
            $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType = iArr;
            try {
                iArr[EncryptedType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.INT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.LONG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$androidx$security$crypto$EncryptedSharedPreferences$EncryptedType[EncryptedType.STRING_SET.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    String encryptKey(String key) {
        if (key == null) {
            key = NULL_VALUE;
        }
        try {
            byte[] encryptedKeyBytes = this.mKeyDeterministicAead.encryptDeterministically(key.getBytes(StandardCharsets.UTF_8), this.mFileName.getBytes());
            return Base64.encode(encryptedKeyBytes);
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("Could not encrypt key. " + ex.getMessage(), ex);
        }
    }

    String decryptKey(String encryptedKey) {
        try {
            byte[] clearText = this.mKeyDeterministicAead.decryptDeterministically(Base64.decode(encryptedKey, 0), this.mFileName.getBytes());
            String key = new String(clearText, StandardCharsets.UTF_8);
            if (key.equals(NULL_VALUE)) {
                return null;
            }
            return key;
        } catch (GeneralSecurityException ex) {
            throw new SecurityException("Could not decrypt key. " + ex.getMessage(), ex);
        }
    }

    boolean isReservedKey(String key) {
        if (KEY_KEYSET_ALIAS.equals(key) || VALUE_KEYSET_ALIAS.equals(key)) {
            return true;
        }
        return false;
    }

    Pair<String, String> encryptKeyValuePair(String key, byte[] value) throws GeneralSecurityException {
        String encryptedKey = encryptKey(key);
        byte[] cipherText = this.mValueAead.encrypt(value, encryptedKey.getBytes(StandardCharsets.UTF_8));
        return new Pair<>(encryptedKey, Base64.encode(cipherText));
    }
}
