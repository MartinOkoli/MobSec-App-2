package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Hkdf.smali */
public final class Hkdf {
    public static byte[] computeHkdf(String macAlgorithm, final byte[] ikm, final byte[] salt, final byte[] info, int size) throws IllegalStateException, GeneralSecurityException {
        Mac mac = EngineFactory.MAC.getInstance(macAlgorithm);
        if (size > mac.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        if (salt == null || salt.length == 0) {
            mac.init(new SecretKeySpec(new byte[mac.getMacLength()], macAlgorithm));
        } else {
            mac.init(new SecretKeySpec(salt, macAlgorithm));
        }
        byte[] prk = mac.doFinal(ikm);
        byte[] result = new byte[size];
        int ctr = 1;
        int pos = 0;
        mac.init(new SecretKeySpec(prk, macAlgorithm));
        byte[] digest = new byte[0];
        while (true) {
            mac.update(digest);
            mac.update(info);
            mac.update((byte) ctr);
            digest = mac.doFinal();
            if (digest.length + pos < size) {
                System.arraycopy(digest, 0, result, pos, digest.length);
                pos += digest.length;
                ctr++;
            } else {
                System.arraycopy(digest, 0, result, pos, size - pos);
                return result;
            }
        }
    }

    public static byte[] computeEciesHkdfSymmetricKey(final byte[] ephemeralPublicKeyBytes, final byte[] sharedSecret, String hmacAlgo, final byte[] hkdfSalt, final byte[] hkdfInfo, int keySizeInBytes) throws GeneralSecurityException {
        byte[] hkdfInput = Bytes.concat(ephemeralPublicKeyBytes, sharedSecret);
        return computeHkdf(hmacAlgo, hkdfInput, hkdfSalt, hkdfInfo, keySizeInBytes);
    }
}
