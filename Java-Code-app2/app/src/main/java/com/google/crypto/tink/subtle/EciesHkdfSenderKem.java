package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EciesHkdfSenderKem.smali */
public final class EciesHkdfSenderKem {
    private ECPublicKey recipientPublicKey;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EciesHkdfSenderKem$KemKey.smali */
    public static final class KemKey {
        private final ImmutableByteArray kemBytes;
        private final ImmutableByteArray symmetricKey;

        public KemKey(final byte[] kemBytes, final byte[] symmetricKey) {
            this.kemBytes = ImmutableByteArray.of(kemBytes);
            this.symmetricKey = ImmutableByteArray.of(symmetricKey);
        }

        public byte[] getKemBytes() {
            ImmutableByteArray immutableByteArray = this.kemBytes;
            if (immutableByteArray == null) {
                return null;
            }
            return immutableByteArray.getBytes();
        }

        public byte[] getSymmetricKey() {
            ImmutableByteArray immutableByteArray = this.symmetricKey;
            if (immutableByteArray == null) {
                return null;
            }
            return immutableByteArray.getBytes();
        }
    }

    public EciesHkdfSenderKem(final ECPublicKey recipientPublicKey) {
        this.recipientPublicKey = recipientPublicKey;
    }

    public KemKey generateKey(String hmacAlgo, final byte[] hkdfSalt, final byte[] hkdfInfo, int keySizeInBytes, EllipticCurves.PointFormatType pointFormat) throws GeneralSecurityException {
        KeyPair ephemeralKeyPair = EllipticCurves.generateKeyPair(this.recipientPublicKey.getParams());
        ECPublicKey ephemeralPublicKey = (ECPublicKey) ephemeralKeyPair.getPublic();
        ECPrivateKey ephemeralPrivateKey = (ECPrivateKey) ephemeralKeyPair.getPrivate();
        byte[] sharedSecret = EllipticCurves.computeSharedSecret(ephemeralPrivateKey, this.recipientPublicKey);
        byte[] kemBytes = EllipticCurves.pointEncode(ephemeralPublicKey.getParams().getCurve(), pointFormat, ephemeralPublicKey.getW());
        byte[] symmetricKey = Hkdf.computeEciesHkdfSymmetricKey(kemBytes, sharedSecret, hmacAlgo, hkdfSalt, hkdfInfo, keySizeInBytes);
        return new KemKey(kemBytes, symmetricKey);
    }
}
