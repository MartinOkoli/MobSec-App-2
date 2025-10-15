package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.EllipticCurve;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EciesAeadHkdfHybridDecrypt.smali */
public final class EciesAeadHkdfHybridDecrypt implements HybridDecrypt {
    private static final byte[] EMPTY_AAD = new byte[0];
    private final EciesAeadHkdfDemHelper demHelper;
    private final EllipticCurves.PointFormatType ecPointFormat;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final EciesHkdfRecipientKem recipientKem;
    private final ECPrivateKey recipientPrivateKey;

    public EciesAeadHkdfHybridDecrypt(final ECPrivateKey recipientPrivateKey, final byte[] hkdfSalt, String hkdfHmacAlgo, EllipticCurves.PointFormatType ecPointFormat, EciesAeadHkdfDemHelper demHelper) throws GeneralSecurityException {
        this.recipientPrivateKey = recipientPrivateKey;
        this.recipientKem = new EciesHkdfRecipientKem(recipientPrivateKey);
        this.hkdfSalt = hkdfSalt;
        this.hkdfHmacAlgo = hkdfHmacAlgo;
        this.ecPointFormat = ecPointFormat;
        this.demHelper = demHelper;
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
        EllipticCurve curve = this.recipientPrivateKey.getParams().getCurve();
        int headerSize = EllipticCurves.encodingSizeInBytes(curve, this.ecPointFormat);
        if (ciphertext.length < headerSize) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] kemBytes = Arrays.copyOfRange(ciphertext, 0, headerSize);
        byte[] symmetricKey = this.recipientKem.generateKey(kemBytes, this.hkdfHmacAlgo, this.hkdfSalt, contextInfo, this.demHelper.getSymmetricKeySizeInBytes(), this.ecPointFormat);
        Aead aead = this.demHelper.getAead(symmetricKey);
        return aead.decrypt(Arrays.copyOfRange(ciphertext, headerSize, ciphertext.length), EMPTY_AAD);
    }
}
