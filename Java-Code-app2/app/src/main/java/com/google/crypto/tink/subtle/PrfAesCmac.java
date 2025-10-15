package com.google.crypto.tink.subtle;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Immutable
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\PrfAesCmac.smali */
public final class PrfAesCmac implements Prf {
    private final SecretKey keySpec;
    private byte[] subKey1;
    private byte[] subKey2;

    private static Cipher instance() throws GeneralSecurityException {
        return EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
    }

    public PrfAesCmac(final byte[] key) throws GeneralSecurityException {
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, "AES");
        generateSubKeys();
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(final byte[] data, int outputLength) throws GeneralSecurityException {
        byte[] mLast;
        if (outputLength > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher aes = instance();
        aes.init(1, this.keySpec);
        int n = Math.max(1, (int) Math.ceil(data.length / 16.0d));
        boolean flag = n * 16 == data.length;
        if (flag) {
            mLast = Bytes.xor(data, (n - 1) * 16, this.subKey1, 0, 16);
        } else {
            mLast = Bytes.xor(AesUtil.cmacPad(Arrays.copyOfRange(data, (n - 1) * 16, data.length)), this.subKey2);
        }
        byte[] x = new byte[16];
        for (int i = 0; i < n - 1; i++) {
            byte[] y = Bytes.xor(x, 0, data, i * 16, 16);
            x = aes.doFinal(y);
        }
        byte[] y2 = Bytes.xor(mLast, x);
        byte[] output = Arrays.copyOf(aes.doFinal(y2), outputLength);
        return output;
    }

    private void generateSubKeys() throws GeneralSecurityException {
        Cipher aes = instance();
        aes.init(1, this.keySpec);
        byte[] zeroes = new byte[16];
        byte[] l = aes.doFinal(zeroes);
        byte[] bArrDbl = AesUtil.dbl(l);
        this.subKey1 = bArrDbl;
        this.subKey2 = AesUtil.dbl(bArrDbl);
    }
}
