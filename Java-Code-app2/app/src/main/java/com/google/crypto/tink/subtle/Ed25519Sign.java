package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519Sign.smali */
public final class Ed25519Sign implements PublicKeySign {
    public static final int SECRET_KEY_LEN = 32;
    private final byte[] hashedPrivateKey;
    private final byte[] publicKey;

    public Ed25519Sign(final byte[] privateKey) throws GeneralSecurityException {
        if (privateKey.length != 32) {
            throw new IllegalArgumentException(String.format("Given private key's length is not %s", 32));
        }
        byte[] hashedScalar = Ed25519.getHashedScalar(privateKey);
        this.hashedPrivateKey = hashedScalar;
        this.publicKey = Ed25519.scalarMultWithBaseToBytes(hashedScalar);
    }

    public byte[] sign(final byte[] data) throws GeneralSecurityException {
        return Ed25519.sign(data, this.publicKey, this.hashedPrivateKey);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\Ed25519Sign$KeyPair.smali */
    public static final class KeyPair {
        private final byte[] privateKey;
        private final byte[] publicKey;

        private KeyPair(final byte[] publicKey, final byte[] privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public byte[] getPublicKey() {
            byte[] bArr = this.publicKey;
            return Arrays.copyOf(bArr, bArr.length);
        }

        public byte[] getPrivateKey() {
            byte[] bArr = this.privateKey;
            return Arrays.copyOf(bArr, bArr.length);
        }

        public static KeyPair newKeyPair() throws GeneralSecurityException {
            byte[] privateKey = Random.randBytes(32);
            byte[] publicKey = Ed25519.scalarMultWithBaseToBytes(Ed25519.getHashedScalar(privateKey));
            return new KeyPair(publicKey, privateKey);
        }
    }
}
