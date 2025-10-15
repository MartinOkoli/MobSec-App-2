package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\KeysetHandle.smali */
public final class KeysetHandle {
    private final Keyset keyset;

    private KeysetHandle(Keyset keyset) {
        this.keyset = keyset;
    }

    static final KeysetHandle fromKeyset(Keyset keyset) throws GeneralSecurityException {
        assertEnoughKeyMaterial(keyset);
        return new KeysetHandle(keyset);
    }

    Keyset getKeyset() {
        return this.keyset;
    }

    public KeysetInfo getKeysetInfo() {
        return Util.getKeysetInfo(this.keyset);
    }

    @Deprecated
    public static final KeysetHandle generateNew(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        return KeysetManager.withEmptyKeyset().rotate(keyTemplate).getKeysetHandle();
    }

    public static final KeysetHandle generateNew(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return KeysetManager.withEmptyKeyset().rotate(keyTemplate.getProto()).getKeysetHandle();
    }

    public static final KeysetHandle read(KeysetReader reader, Aead masterKey) throws GeneralSecurityException, IOException {
        EncryptedKeyset encryptedKeyset = reader.readEncrypted();
        assertEnoughEncryptedKeyMaterial(encryptedKeyset);
        return new KeysetHandle(decrypt(encryptedKeyset, masterKey));
    }

    public static final KeysetHandle readNoSecret(KeysetReader reader) throws GeneralSecurityException, IOException {
        try {
            Keyset keyset = reader.read();
            assertNoSecretKeyMaterial(keyset);
            return fromKeyset(keyset);
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final KeysetHandle readNoSecret(final byte[] serialized) throws GeneralSecurityException {
        try {
            Keyset keyset = Keyset.parseFrom(serialized, ExtensionRegistryLite.getEmptyRegistry());
            assertNoSecretKeyMaterial(keyset);
            return fromKeyset(keyset);
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public void write(KeysetWriter keysetWriter, Aead masterKey) throws GeneralSecurityException, IOException {
        EncryptedKeyset encryptedKeyset = encrypt(this.keyset, masterKey);
        keysetWriter.write(encryptedKeyset);
    }

    public void writeNoSecret(KeysetWriter writer) throws GeneralSecurityException, IOException {
        assertNoSecretKeyMaterial(this.keyset);
        writer.write(this.keyset);
    }

    private static EncryptedKeyset encrypt(Keyset keyset, Aead masterKey) throws GeneralSecurityException {
        byte[] encryptedKeyset = masterKey.encrypt(keyset.toByteArray(), new byte[0]);
        try {
            Keyset keyset2 = Keyset.parseFrom(masterKey.decrypt(encryptedKeyset, new byte[0]), ExtensionRegistryLite.getEmptyRegistry());
            if (!keyset2.equals(keyset)) {
                throw new GeneralSecurityException("cannot encrypt keyset");
            }
            return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(encryptedKeyset)).setKeysetInfo(Util.getKeysetInfo(keyset)).build();
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static Keyset decrypt(EncryptedKeyset encryptedKeyset, Aead masterKey) throws GeneralSecurityException {
        try {
            Keyset keyset = Keyset.parseFrom(masterKey.decrypt(encryptedKeyset.getEncryptedKeyset().toByteArray(), new byte[0]), ExtensionRegistryLite.getEmptyRegistry());
            assertEnoughKeyMaterial(keyset);
            return keyset;
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
        if (this.keyset == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        Keyset.Builder keysetBuilder = Keyset.newBuilder();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            KeyData keyData = createPublicKeyData(key.getKeyData());
            keysetBuilder.addKey(Keyset.Key.newBuilder().mergeFrom((Keyset.Key.Builder) key).setKeyData(keyData).build());
        }
        keysetBuilder.setPrimaryKeyId(this.keyset.getPrimaryKeyId());
        return new KeysetHandle(keysetBuilder.build());
    }

    private static KeyData createPublicKeyData(KeyData privateKeyData) throws GeneralSecurityException {
        if (privateKeyData.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
            throw new GeneralSecurityException("The keyset contains a non-private key");
        }
        KeyData publicKeyData = Registry.getPublicKeyData(privateKeyData.getTypeUrl(), privateKeyData.getValue());
        validate(publicKeyData);
        return publicKeyData;
    }

    private static void validate(KeyData keyData) throws GeneralSecurityException {
        Registry.getPrimitive(keyData);
    }

    public String toString() {
        return getKeysetInfo().toString();
    }

    private static void assertNoSecretKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        for (Keyset.Key key : keyset.getKeyList()) {
            if (key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", key.getKeyData().getKeyMaterialType(), key.getKeyData().getTypeUrl()));
            }
        }
    }

    public static void assertEnoughKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        if (keyset == null || keyset.getKeyCount() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public static void assertEnoughEncryptedKeyMaterial(EncryptedKeyset keyset) throws GeneralSecurityException {
        if (keyset == null || keyset.getEncryptedKeyset().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private <B, P> P getPrimitiveWithKnownInputPrimitive(Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        return (P) Registry.wrap(Registry.getPrimitives(this, cls2), cls);
    }

    public <P> P getPrimitive(Class<P> cls) throws GeneralSecurityException {
        Class<?> inputPrimitive = Registry.getInputPrimitive(cls);
        if (inputPrimitive == null) {
            throw new GeneralSecurityException("No wrapper found for " + cls.getName());
        }
        return (P) getPrimitiveWithKnownInputPrimitive(cls, inputPrimitive);
    }

    public <P> P getPrimitive(KeyManager<P> keyManager, Class<P> cls) throws GeneralSecurityException {
        if (keyManager == null) {
            throw new IllegalArgumentException("customKeyManager must be non-null.");
        }
        return (P) Registry.wrap(Registry.getPrimitives(this, keyManager, cls));
    }
}
