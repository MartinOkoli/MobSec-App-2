package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.Enums;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\RsaSsaPssVerifyJce.smali */
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(final RSAPublicKey pubKey, Enums.HashType sigHash, Enums.HashType mgf1Hash, int saltLength) throws GeneralSecurityException {
        Validators.validateSignatureHash(sigHash);
        Validators.validateRsaModulusSize(pubKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(pubKey.getPublicExponent());
        this.publicKey = pubKey;
        this.sigHash = sigHash;
        this.mgf1Hash = mgf1Hash;
        this.saltLength = saltLength;
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException {
        BigInteger e = this.publicKey.getPublicExponent();
        BigInteger n = this.publicKey.getModulus();
        int nLengthInBytes = (n.bitLength() + 7) / 8;
        int mLen = ((n.bitLength() - 1) + 7) / 8;
        if (nLengthInBytes != signature.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger s = SubtleUtil.bytes2Integer(signature);
        if (s.compareTo(n) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        BigInteger m = s.modPow(e, n);
        byte[] em = SubtleUtil.integer2Bytes(m, mLen);
        emsaPssVerify(data, em, n.bitLength() - 1);
    }

    private void emsaPssVerify(byte[] m, byte[] em, int emBits) throws GeneralSecurityException {
        String str;
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest digest = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] mHash = digest.digest(m);
        int hLen = digest.getDigestLength();
        int emLen = em.length;
        String str2 = "inconsistent";
        if (emLen < this.saltLength + hLen + 2) {
            throw new GeneralSecurityException("inconsistent");
        }
        if (em[em.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        byte[] maskedDb = Arrays.copyOf(em, (emLen - hLen) - 1);
        byte[] h = Arrays.copyOfRange(em, maskedDb.length, maskedDb.length + hLen);
        int i = 0;
        while (true) {
            byte[] h2 = h;
            MessageDigest digest2 = digest;
            if (i >= (emLen * 8) - emBits) {
                byte[] dbMask = SubtleUtil.mgf1(h2, (emLen - hLen) - 1, this.mgf1Hash);
                byte[] db = new byte[dbMask.length];
                for (int i2 = 0; i2 < db.length; i2++) {
                    db[i2] = (byte) (dbMask[i2] ^ maskedDb[i2]);
                }
                int i3 = 0;
                while (true) {
                    byte[] maskedDb2 = maskedDb;
                    str = str2;
                    if (i3 > (emLen * 8) - emBits) {
                        break;
                    }
                    int bytePos = i3 / 8;
                    int bitPos = 7 - (i3 % 8);
                    db[bytePos] = (byte) (db[bytePos] & (~(1 << bitPos)));
                    i3++;
                    str2 = str;
                    maskedDb = maskedDb2;
                }
                int i4 = 0;
                while (true) {
                    int i5 = this.saltLength;
                    if (i4 < ((emLen - hLen) - i5) - 2) {
                        if (db[i4] == 0) {
                            i4++;
                        } else {
                            throw new GeneralSecurityException(str);
                        }
                    } else {
                        int i6 = emLen - hLen;
                        if (db[(i6 - i5) - 2] != 1) {
                            throw new GeneralSecurityException(str);
                        }
                        byte[] salt = Arrays.copyOfRange(db, db.length - i5, db.length);
                        byte[] mPrime = new byte[hLen + 8 + this.saltLength];
                        System.arraycopy(mHash, 0, mPrime, 8, mHash.length);
                        System.arraycopy(salt, 0, mPrime, hLen + 8, salt.length);
                        byte[] hPrime = digest2.digest(mPrime);
                        if (!Bytes.equal(hPrime, h2)) {
                            throw new GeneralSecurityException(str);
                        }
                        return;
                    }
                }
            } else {
                int bitPos2 = 7 - (i % 8);
                if (((maskedDb[i / 8] >> bitPos2) & 1) == 0) {
                    i++;
                    h = h2;
                    digest = digest2;
                } else {
                    throw new GeneralSecurityException("inconsistent");
                }
            }
        }
    }
}
