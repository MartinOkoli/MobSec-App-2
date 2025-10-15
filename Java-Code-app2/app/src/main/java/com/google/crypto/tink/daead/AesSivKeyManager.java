package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.AesSiv;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\daead\AesSivKeyManager.smali */
public final class AesSivKeyManager extends KeyTypeManager<AesSivKey> {
    AesSivKeyManager() {
        super(AesSivKey.class, new KeyTypeManager.PrimitiveFactory<DeterministicAead, AesSivKey>(DeterministicAead.class) { // from class: com.google.crypto.tink.daead.AesSivKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public DeterministicAead getPrimitive(AesSivKey key) throws GeneralSecurityException {
                return new AesSiv(key.getKeyValue().toByteArray());
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
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
    public void validateKey(AesSivKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
        if (key.getKeyValue().size() != 64) {
            throw new InvalidKeyException("invalid key size: " + key.getKeyValue().size() + ". Valid keys must have 64 bytes.");
        }
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public AesSivKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return AesSivKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey>(AesSivKeyFormat.class) { // from class: com.google.crypto.tink.daead.AesSivKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesSivKeyFormat format) throws GeneralSecurityException {
                if (format.getKeySize() != 64) {
                    throw new InvalidAlgorithmParameterException("invalid key size: " + format.getKeySize() + ". Valid keys must have 64 bytes.");
                }
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesSivKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return AesSivKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public AesSivKey createKey(AesSivKeyFormat format) throws GeneralSecurityException {
                return AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(format.getKeySize()))).setVersion(AesSivKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new AesSivKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate aes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.RAW);
    }

    private static KeyTemplate createKeyTemplate(int keySize, KeyTemplate.OutputPrefixType prefixType) {
        AesSivKeyFormat format = AesSivKeyFormat.newBuilder().setKeySize(keySize).build();
        return KeyTemplate.create(new AesSivKeyManager().getKeyType(), format.toByteArray(), prefixType);
    }
}
