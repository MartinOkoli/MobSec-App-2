package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKey;
import com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\KmsEnvelopeAeadKeyManager.smali */
public class KmsEnvelopeAeadKeyManager extends KeyTypeManager<KmsEnvelopeAeadKey> {
    KmsEnvelopeAeadKeyManager() {
        super(KmsEnvelopeAeadKey.class, new KeyTypeManager.PrimitiveFactory<Aead, KmsEnvelopeAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public Aead getPrimitive(KmsEnvelopeAeadKey keyProto) throws GeneralSecurityException {
                String keyUri = keyProto.getParams().getKekUri();
                KmsClient kmsClient = KmsClients.get(keyUri);
                Aead remote = kmsClient.getAead(keyUri);
                return new KmsEnvelopeAead(keyProto.getParams().getDekTemplate(), remote);
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
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
    public void validateKey(KmsEnvelopeAeadKey key) throws GeneralSecurityException {
        Validators.validateVersion(key.getVersion(), getVersion());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public KmsEnvelopeAeadKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return KmsEnvelopeAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<KmsEnvelopeAeadKeyFormat, KmsEnvelopeAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsEnvelopeAeadKeyFormat, KmsEnvelopeAeadKey>(KmsEnvelopeAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(KmsEnvelopeAeadKeyFormat format) throws GeneralSecurityException {
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public KmsEnvelopeAeadKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return KmsEnvelopeAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public KmsEnvelopeAeadKey createKey(KmsEnvelopeAeadKeyFormat format) throws GeneralSecurityException {
                return KmsEnvelopeAeadKey.newBuilder().setParams(format).setVersion(KmsEnvelopeAeadKeyManager.this.getVersion()).build();
            }
        };
    }

    public static void register(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerKeyManager(new KmsEnvelopeAeadKeyManager(), newKeyAllowed);
    }
}
