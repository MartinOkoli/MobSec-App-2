package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTypeManager;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\EcdsaVerifyKeyManager.smali */
class EcdsaVerifyKeyManager extends KeyTypeManager<EcdsaPublicKey> {
    public EcdsaVerifyKeyManager() {
        super(EcdsaPublicKey.class, new KeyTypeManager.PrimitiveFactory<PublicKeyVerify, EcdsaPublicKey>(PublicKeyVerify.class) { // from class: com.google.crypto.tink.signature.EcdsaVerifyKeyManager.1
            @Override // com.google.crypto.tink.KeyTypeManager.PrimitiveFactory
            public PublicKeyVerify getPrimitive(EcdsaPublicKey keyProto) throws GeneralSecurityException {
                ECPublicKey publicKey = EllipticCurves.getEcPublicKey(SigUtil.toCurveType(keyProto.getParams().getCurve()), keyProto.getX().toByteArray(), keyProto.getY().toByteArray());
                return new EcdsaVerifyJce(publicKey, SigUtil.toHashType(keyProto.getParams().getHashType()), SigUtil.toEcdsaEncoding(keyProto.getParams().getEncoding()));
            }
        });
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    /* renamed from: parseKey */
    public EcdsaPublicKey mo6parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return EcdsaPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeyTypeManager
    public void validateKey(EcdsaPublicKey pubKey) throws GeneralSecurityException {
        Validators.validateVersion(pubKey.getVersion(), getVersion());
        SigUtil.validateEcdsaParams(pubKey.getParams());
    }
}
