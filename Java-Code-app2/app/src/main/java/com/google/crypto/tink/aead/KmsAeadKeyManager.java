package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsAeadKey;
import com.google.crypto.tink.proto.KmsAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\KmsAeadKeyManager.smali */
public class KmsAeadKeyManager extends KeyTypeManager<KmsAeadKey> {
    KmsAeadKeyManager() {
        super(KmsAeadKey.class, new KeyTypeManager.PrimitiveFactory<Aead, KmsAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public Aead getPrimitive(KmsAeadKey keyProto) throws GeneralSecurityException {
                String keyUri = keyProto.getParams().getKeyUri();
                KmsClient kmsClient = KmsClients.get(keyUri);
                return kmsClient.getAead(keyUri);
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.REMOTE;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(KmsAeadKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public KmsAeadKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return KmsAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<KmsAeadKeyFormat, KmsAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsAeadKeyFormat, KmsAeadKey>(KmsAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(KmsAeadKeyFormat format) throws GeneralSecurityException {
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public KmsAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return KmsAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public KmsAeadKey createKey(KmsAeadKeyFormat format) throws GeneralSecurityException {
                return KmsAeadKey.newBuilder().setParams(format).setVersion(KmsAeadKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsAeadKeyManager(), newKeyAllowed);
    }
}
