package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.mac.HmacKeyManager;
import com.google.crypto.tink.proto.AesCtrHmacAeadKey;
import com.google.crypto.tink.proto.AesCtrHmacAeadKeyFormat;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EncryptThenAuthenticate;
import com.google.crypto.tink.subtle.IndCpaCipher;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\AesCtrHmacAeadKeyManager.smali */
public final class AesCtrHmacAeadKeyManager extends KeyTypeManager<AesCtrHmacAeadKey> {
    AesCtrHmacAeadKeyManager() {
        super(AesCtrHmacAeadKey.class, new KeyTypeManager.PrimitiveFactory<Aead, AesCtrHmacAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public Aead getPrimitive(AesCtrHmacAeadKey key) throws GeneralSecurityException {
                return new EncryptThenAuthenticate((IndCpaCipher) new AesCtrKeyManager().getPrimitive(key.getAesCtrKey(), IndCpaCipher.class), (Mac) new HmacKeyManager().getPrimitive(key.getHmacKey(), Mac.class), key.getHmacKey().getParams().getTagSize());
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(AesCtrHmacAeadKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        new AesCtrKeyManager().validateKey(key.getAesCtrKey());
        new HmacKeyManager().validateKey(key.getHmacKey());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public AesCtrHmacAeadKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesCtrHmacAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesCtrHmacAeadKeyFormat, AesCtrHmacAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCtrHmacAeadKeyFormat, AesCtrHmacAeadKey>(AesCtrHmacAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCtrHmacAeadKeyFormat format) throws GeneralSecurityException {
                new AesCtrKeyManager().keyFactory().validateKeyFormat(format.getAesCtrKeyFormat());
                new HmacKeyManager().keyFactory().validateKeyFormat(format.getHmacKeyFormat());
                Validators.validateAesKeySize(format.getAesCtrKeyFormat().getKeySize());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesCtrHmacAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesCtrHmacAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesCtrHmacAeadKey createKey(AesCtrHmacAeadKeyFormat format) throws GeneralSecurityException {
                AesCtrKey aesCtrKey = new AesCtrKeyManager().keyFactory().createKey(format.getAesCtrKeyFormat());
                HmacKey hmacKey = new HmacKeyManager().keyFactory().createKey(format.getHmacKeyFormat());
                return AesCtrHmacAeadKey.newBuilder().setAesCtrKey(aesCtrKey).setHmacKey(hmacKey).setVersion(AesCtrHmacAeadKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesCtrHmacAeadKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate aes128CtrHmacSha256Template() {
        return createKeyTemplate(16, 16, 32, 16, HashType.SHA256);
    }

    public static final KeyTemplate aes256CtrHmacSha256Template() {
        return createKeyTemplate(32, 16, 32, 32, HashType.SHA256);
    }

    private static KeyTemplate createKeyTemplate(int aesKeySize, int ivSize, int hmacKeySize, int tagSize, HashType hashType) {
        AesCtrKeyFormat aesCtrKeyFormat = AesCtrKeyFormat.newBuilder().setParams(AesCtrParams.newBuilder().setIvSize(ivSize).build()).setKeySize(aesKeySize).build();
        HmacKeyFormat hmacKeyFormat = HmacKeyFormat.newBuilder().setParams(HmacParams.newBuilder().setHash(hashType).setTagSize(tagSize).build()).setKeySize(hmacKeySize).build();
        AesCtrHmacAeadKeyFormat format = AesCtrHmacAeadKeyFormat.newBuilder().setAesCtrKeyFormat(aesCtrKeyFormat).setHmacKeyFormat(hmacKeyFormat).build();
        return KeyTemplate.create(new AesCtrHmacAeadKeyManager().getKeyType(), format.toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }
}
