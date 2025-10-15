package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EcdsaSignJce.smali */
public final class EcdsaSignJce implements PublicKeySign {
    private final EllipticCurves.EcdsaEncoding encoding;
    private final ECPrivateKey privateKey;
    private final String signatureAlgorithm;

    public EcdsaSignJce(final ECPrivateKey priv, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding) throws GeneralSecurityException {
        this.privateKey = priv;
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hash);
        this.encoding = encoding;
    }

    public byte[] sign(final byte[] data) throws GeneralSecurityException {
        Signature signer = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        signer.initSign(this.privateKey);
        signer.update(data);
        byte[] signature = signer.sign();
        if (this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
            EllipticCurve curve = this.privateKey.getParams().getCurve();
            return EllipticCurves.ecdsaDer2Ieee(signature, EllipticCurves.fieldSizeInBytes(curve) * 2);
        }
        return signature;
    }
}
