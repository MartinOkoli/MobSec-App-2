package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\RsaSsaPkcs1SignJce.smali */
public final class RsaSsaPkcs1SignJce implements PublicKeySign {
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final String signatureAlgorithm;

    public RsaSsaPkcs1SignJce(final RSAPrivateCrtKey priv, Enums.HashType hash) throws GeneralSecurityException {
        Validators.validateSignatureHash(hash);
        Validators.validateRsaModulusSize(priv.getModulus().bitLength());
        Validators.validateRsaPublicExponent(priv.getPublicExponent());
        this.privateKey = priv;
        this.signatureAlgorithm = SubtleUtil.toRsaSsaPkcs1Algo(hash);
        KeyFactory kf = EngineFactory.KEY_FACTORY.getInstance("RSA");
        this.publicKey = (RSAPublicKey) kf.generatePublic(new RSAPublicKeySpec(priv.getModulus(), priv.getPublicExponent()));
    }

    public byte[] sign(final byte[] data) throws GeneralSecurityException {
        Signature signer = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        signer.initSign(this.privateKey);
        signer.update(data);
        byte[] signature = signer.sign();
        Signature verifier = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        verifier.initVerify(this.publicKey);
        verifier.update(data);
        if (!verifier.verify(signature)) {
            throw new RuntimeException("Security bug: RSA signature computation error");
        }
        return signature;
    }
}
