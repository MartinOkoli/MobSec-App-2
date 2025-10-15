package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PrivateKeyTypeManager;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPrivateKey;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\EcdsaSignKeyManager.smali */
public final class EcdsaSignKeyManager extends PrivateKeyTypeManager<EcdsaPrivateKey, EcdsaPublicKey> {
    EcdsaSignKeyManager() {
        super(EcdsaPrivateKey.class, EcdsaPublicKey.class, new KeyTypeManager.PrimitiveFactory<PublicKeySign, EcdsaPrivateKey>(PublicKeySign.class) { // from class: com.google.crypto.tink.signature.EcdsaSignKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public PublicKeySign getPrimitive(EcdsaPrivateKey key) throws GeneralSecurityException {
                ECPrivateKey privateKey = EllipticCurves.getEcPrivateKey(SigUtil.toCurveType(key.getPublicKey().getParams().getCurve()), key.getKeyValue().toByteArray());
                return new EcdsaSignJce(privateKey, SigUtil.toHashType(key.getPublicKey().getParams().getHashType()), SigUtil.toEcdsaEncoding(key.getPublicKey().getParams().getEncoding()));
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.PrivateKeyTypeManager
    public EcdsaPublicKey getPublicKey(EcdsaPrivateKey key) throws GeneralSecurityException {
        return key.getPublicKey();
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public EcdsaPrivateKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EcdsaPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(EcdsaPrivateKey privKey) throws GeneralSecurityException {
        Validators.validateVersion(privKey.getVersion(), getVersion());
        SigUtil.validateEcdsaParams(privKey.getPublicKey().getParams());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyTypeManager.KeyFactory<EcdsaKeyFormat, EcdsaPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<EcdsaKeyFormat, EcdsaPrivateKey>(EcdsaKeyFormat.class) { // from class: com.google.crypto.tink.signature.EcdsaSignKeyManager.2
            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public void validateKeyFormat(EcdsaKeyFormat format) throws GeneralSecurityException {
                SigUtil.validateEcdsaParams(format.getParams());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public EcdsaKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return EcdsaKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.KeyTypeManager.KeyFactory
            public EcdsaPrivateKey createKey(EcdsaKeyFormat format) throws GeneralSecurityException {
                EcdsaParams ecdsaParams = format.getParams();
                KeyPair keyPair = EllipticCurves.generateKeyPair(SigUtil.toCurveType(ecdsaParams.getCurve()));
                ECPublicKey pubKey = (ECPublicKey) keyPair.getPublic();
                ECPrivateKey privKey = (ECPrivateKey) keyPair.getPrivate();
                ECPoint w = pubKey.getW();
                EcdsaPublicKey ecdsaPubKey = EcdsaPublicKey.newBuilder().setVersion(EcdsaSignKeyManager.this.getVersion()).setParams(ecdsaParams).setX(ByteString.copyFrom(w.getAffineX().toByteArray())).setY(ByteString.copyFrom(w.getAffineY().toByteArray())).build();
                return EcdsaPrivateKey.newBuilder().setVersion(EcdsaSignKeyManager.this.getVersion()).setPublicKey(ecdsaPubKey).setKeyValue(ByteString.copyFrom(privKey.getS().toByteArray())).build();
            }
        };
    }

    public static void registerPair(boolean newKeyAllowed) throws GeneralSecurityException {
        Registry.registerAsymmetricKeyManagers(new EcdsaSignKeyManager(), new EcdsaVerifyKeyManager(), newKeyAllowed);
    }

    public static final KeyTemplate ecdsaP256Template() {
        return createKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.DER, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawEcdsaP256Template() {
        return createKeyTemplate(HashType.SHA256, EllipticCurveType.NIST_P256, EcdsaSignatureEncoding.IEEE_P1363, KeyTemplate.OutputPrefixType.RAW);
    }

    public static KeyTemplate createKeyTemplate(HashType hashType, EllipticCurveType curve, EcdsaSignatureEncoding encoding, KeyTemplate.OutputPrefixType prefixType) {
        EcdsaParams params = EcdsaParams.newBuilder().setHashType(hashType).setCurve(curve).setEncoding(encoding).build();
        EcdsaKeyFormat format = EcdsaKeyFormat.newBuilder().setParams(params).build();
        return KeyTemplate.create(new EcdsaSignKeyManager().getKeyType(), format.toByteArray(), prefixType);
    }
}
