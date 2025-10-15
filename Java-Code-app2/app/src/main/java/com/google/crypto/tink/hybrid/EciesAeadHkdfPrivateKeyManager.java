package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PrivateKeyTypeManager;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager;
import com.google.crypto.tink.aead.AesGcmKeyManager;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesAeadHkdfKeyFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EciesAeadHkdfPrivateKey;
import com.google.crypto.tink.proto.EciesAeadHkdfPublicKey;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EciesAeadHkdfDemHelper;
import com.google.crypto.tink.subtle.EciesAeadHkdfHybridDecrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\EciesAeadHkdfPrivateKeyManager.smali */
public final class EciesAeadHkdfPrivateKeyManager extends PrivateKeyTypeManager<EciesAeadHkdfPrivateKey, EciesAeadHkdfPublicKey> {
    private static final byte[] EMPTY_SALT = new byte[0];

    EciesAeadHkdfPrivateKeyManager() {
        super(EciesAeadHkdfPrivateKey.class, EciesAeadHkdfPublicKey.class, new KeyTypeManager.PrimitiveFactory<HybridDecrypt, EciesAeadHkdfPrivateKey>(HybridDecrypt.class) { // from class: com.google.crypto.tink.hybrid.EciesAeadHkdfPrivateKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public HybridDecrypt getPrimitive(EciesAeadHkdfPrivateKey recipientKeyProto) throws GeneralSecurityException {
                EciesAeadHkdfParams eciesParams = recipientKeyProto.getPublicKey().getParams();
                EciesHkdfKemParams kemParams = eciesParams.getKemParams();
                ECPrivateKey recipientPrivateKey = EllipticCurves.getEcPrivateKey(HybridUtil.toCurveType(kemParams.getCurveType()), recipientKeyProto.getKeyValue().toByteArray());
                EciesAeadHkdfDemHelper demHelper = new RegistryEciesAeadHkdfDemHelper(eciesParams.getDemParams().getAeadDem());
                return new EciesAeadHkdfHybridDecrypt(recipientPrivateKey, kemParams.getHkdfSalt().toByteArray(), HybridUtil.toHmacAlgo(kemParams.getHkdfHashType()), HybridUtil.toPointFormatType(eciesParams.getEcPointFormat()), demHelper);
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.PrivateKeyTypeManager
    public EciesAeadHkdfPublicKey getPublicKey(EciesAeadHkdfPrivateKey key) throws GeneralSecurityException {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public EciesAeadHkdfPrivateKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EciesAeadHkdfPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(EciesAeadHkdfPrivateKey keyProto) throws GeneralSecurityException {
        if (keyProto.getKeyValue().isEmpty()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        Validators.validateVersion(keyProto.getVersion(), getVersion());
        HybridUtil.validate(keyProto.getPublicKey().getParams());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<EciesAeadHkdfKeyFormat, EciesAeadHkdfPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<EciesAeadHkdfKeyFormat, EciesAeadHkdfPrivateKey>(EciesAeadHkdfKeyFormat.class) { // from class: com.google.crypto.tink.hybrid.EciesAeadHkdfPrivateKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(EciesAeadHkdfKeyFormat eciesKeyFormat) throws GeneralSecurityException {
                HybridUtil.validate(eciesKeyFormat.getParams());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public EciesAeadHkdfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return EciesAeadHkdfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public EciesAeadHkdfPrivateKey createKey(EciesAeadHkdfKeyFormat eciesKeyFormat) throws GeneralSecurityException {
                EciesHkdfKemParams kemParams = eciesKeyFormat.getParams().getKemParams();
                KeyPair keyPair = EllipticCurves.generateKeyPair(HybridUtil.toCurveType(kemParams.getCurveType()));
                ECPublicKey pubKey = (ECPublicKey) keyPair.getPublic();
                ECPrivateKey privKey = (ECPrivateKey) keyPair.getPrivate();
                ECPoint w = pubKey.getW();
                EciesAeadHkdfPublicKey eciesPublicKey = EciesAeadHkdfPublicKey.newBuilder().setVersion(EciesAeadHkdfPrivateKeyManager.this.getVersion()).setParams(eciesKeyFormat.getParams()).setX(ByteString.copyFrom(w.getAffineX().toByteArray())).setY(ByteString.copyFrom(w.getAffineY().toByteArray())).build();
                return EciesAeadHkdfPrivateKey.newBuilder().setVersion(EciesAeadHkdfPrivateKeyManager.this.getVersion()).setPublicKey(eciesPublicKey).setKeyValue(ByteString.copyFrom(privKey.getS().toByteArray())).build();
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EciesAeadHkdfPrivateKeyManager(), new EciesAeadHkdfPublicKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128GcmTemplate() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AesGcmKeyManager.aes128GcmTemplate(), KeyTemplate.OutputPrefixType.TINK, EMPTY_SALT);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128GcmCompressedTemplate() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AesGcmKeyManager.aes128GcmTemplate(), KeyTemplate.OutputPrefixType.RAW, EMPTY_SALT);
    }

    public static final KeyTemplate eciesP256HkdfHmacSha256Aes128CtrHmacSha256Template() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.UNCOMPRESSED, AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template(), KeyTemplate.OutputPrefixType.TINK, EMPTY_SALT);
    }

    public static final KeyTemplate rawEciesP256HkdfHmacSha256Aes128CtrHmacSha256CompressedTemplate() {
        return createKeyTemplate(EllipticCurveType.NIST_P256, HashType.SHA256, EcPointFormat.COMPRESSED, AesCtrHmacAeadKeyManager.aes128CtrHmacSha256Template(), KeyTemplate.OutputPrefixType.RAW, EMPTY_SALT);
    }

    private static KeyTemplate createKeyTemplate(EllipticCurveType curve, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate demKeyTemplate, KeyTemplate.OutputPrefixType outputPrefixType, byte[] salt) {
        EciesAeadHkdfKeyFormat format = EciesAeadHkdfKeyFormat.newBuilder().setParams(createParams(curve, hashType, ecPointFormat, demKeyTemplate, salt)).build();
        return KeyTemplate.create(new EciesAeadHkdfPrivateKeyManager().getKeyType(), format.toByteArray(), outputPrefixType);
    }

    static EciesAeadHkdfParams createParams(EllipticCurveType curve, HashType hashType, EcPointFormat ecPointFormat, KeyTemplate demKeyTemplate, byte[] salt) {
        EciesHkdfKemParams kemParams = EciesHkdfKemParams.newBuilder().setCurveType(curve).setHkdfHashType(hashType).setHkdfSalt(ByteString.copyFrom(salt)).build();
        com.google.crypto.tink.proto.KeyTemplate protoKt = com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(demKeyTemplate.getTypeUrl()).setValue(ByteString.copyFrom(demKeyTemplate.getValue())).setOutputPrefixType(toProto(demKeyTemplate.getOutputPrefixType())).build();
        EciesAeadDemParams demParams = EciesAeadDemParams.newBuilder().setAeadDem(protoKt).build();
        return EciesAeadHkdfParams.newBuilder().setKemParams(kemParams).setDemParams(demParams).setEcPointFormat(ecPointFormat).build();
    }

    /* renamed from: com.google.crypto.tink.hybrid.EciesAeadHkdfPrivateKeyManager$3, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\EciesAeadHkdfPrivateKeyManager$3.smali */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType;

        static {
            int[] iArr = new int[KeyTemplate.OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType = iArr;
            try {
                iArr[KeyTemplate.OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[KeyTemplate.OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[KeyTemplate.OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[KeyTemplate.OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private static OutputPrefixType toProto(KeyTemplate.OutputPrefixType outputPrefixType) {
        int i = AnonymousClass3.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[outputPrefixType.ordinal()];
        if (i == 1) {
            return OutputPrefixType.TINK;
        }
        if (i == 2) {
            return OutputPrefixType.LEGACY;
        }
        if (i == 3) {
            return OutputPrefixType.RAW;
        }
        if (i == 4) {
            return OutputPrefixType.CRUNCHY;
        }
        throw new IllegalArgumentException("Unknown output prefix type");
    }
}
