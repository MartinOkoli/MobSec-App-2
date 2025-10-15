package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import java.security.spec.EllipticCurve;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EcdsaVerifyJce.smali */
public final class EcdsaVerifyJce implements PublicKeyVerify {
    private final EllipticCurves.EcdsaEncoding encoding;
    private final ECPublicKey publicKey;
    private final String signatureAlgorithm;

    public EcdsaVerifyJce(final ECPublicKey pubKey, Enums.HashType hash, EllipticCurves.EcdsaEncoding encoding) throws GeneralSecurityException {
        EllipticCurves.checkPublicKey(pubKey);
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hash);
        this.publicKey = pubKey;
        this.encoding = encoding;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
        boolean verified;
        byte[] derSignature = signature;
        if (this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363) {
            EllipticCurve curve = this.publicKey.getParams().getCurve();
            if (signature.length != EllipticCurves.fieldSizeInBytes(curve) * 2) {
                throw new GeneralSecurityException("Invalid signature");
            }
            derSignature = EllipticCurves.ecdsaIeee2Der(signature);
        }
        if (!EllipticCurves.isValidDerEncoding(derSignature)) {
            throw new GeneralSecurityException("Invalid signature");
        }
        Signature verifier = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
        verifier.initVerify(this.publicKey);
        verifier.update(data);
        try {
            verified = verifier.verify(derSignature);
        } catch (RuntimeException e) {
            verified = false;
        }
        if (!verified) {
            throw new GeneralSecurityException("Invalid signature");
        }
    }
}
