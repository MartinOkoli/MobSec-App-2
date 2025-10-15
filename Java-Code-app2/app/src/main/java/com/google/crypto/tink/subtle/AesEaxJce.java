package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesEaxJce.smali */
public final class AesEaxJce implements Aead {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int BLOCK_SIZE_IN_BYTES = 16;
    static final int TAG_SIZE_IN_BYTES = 16;
    private final byte[] b;
    private final int ivSizeInBytes;
    private final SecretKeySpec keySpec;
    private final byte[] p;
    private static final ThreadLocal<Cipher> localEcbCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.subtle.AesEaxJce.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/ECB/NOPADDING");
            } catch (GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }
    };
    private static final ThreadLocal<Cipher> localCtrCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.subtle.AesEaxJce.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/CTR/NOPADDING");
            } catch (GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }
    };

    public AesEaxJce(final byte[] key, int ivSizeInBytes) throws GeneralSecurityException {
        if (ivSizeInBytes != 12 && ivSizeInBytes != 16) {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
        this.ivSizeInBytes = ivSizeInBytes;
        Validators.validateAesKeySize(key.length);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        this.keySpec = secretKeySpec;
        Cipher ecb = localEcbCipher.get();
        ecb.init(1, secretKeySpec);
        byte[] block = ecb.doFinal(new byte[16]);
        byte[] bArrMultiplyByX = multiplyByX(block);
        this.b = bArrMultiplyByX;
        this.p = multiplyByX(bArrMultiplyByX);
    }

    private static byte[] xor(final byte[] x, final byte[] y) {
        if (x.length != y.length) {
            throw new AssertionError();
        }
        int len = x.length;
        byte[] res = new byte[len];
        for (int i = 0; i < len; i++) {
            res[i] = (byte) (x[i] ^ y[i]);
        }
        return res;
    }

    private static byte[] multiplyByX(final byte[] block) {
        byte[] res = new byte[16];
        for (int i = 0; i < 15; i++) {
            res[i] = (byte) (((block[i] << 1) ^ ((block[i + 1] & UByte.MAX_VALUE) >>> 7)) & 255);
        }
        int i2 = block[15];
        res[15] = (byte) ((i2 << 1) ^ ((block[0] & ByteCompanionObject.MIN_VALUE) != 0 ? 135 : 0));
        return res;
    }

    private byte[] pad(final byte[] data) {
        if (data.length == 16) {
            return xor(data, this.b);
        }
        byte[] res = Arrays.copyOf(this.p, 16);
        for (int i = 0; i < data.length; i++) {
            res[i] = (byte) (res[i] ^ data[i]);
        }
        int i2 = data.length;
        res[i2] = (byte) (res[data.length] ^ ByteCompanionObject.MIN_VALUE);
        return res;
    }

    private byte[] omac(Cipher ecb, int tag, final byte[] data, int offset, int length) throws BadPaddingException, IllegalBlockSizeException {
        if (length < 0) {
            throw new AssertionError();
        }
        if (tag < 0 || tag > 3) {
            throw new AssertionError();
        }
        byte[] block = new byte[16];
        block[15] = (byte) tag;
        if (length == 0) {
            return ecb.doFinal(xor(block, this.b));
        }
        byte[] block2 = ecb.doFinal(block);
        int position = 0;
        while (length - position > 16) {
            for (int i = 0; i < 16; i++) {
                block2[i] = (byte) (block2[i] ^ data[(offset + position) + i]);
            }
            block2 = ecb.doFinal(block2);
            position += 16;
        }
        byte[] padded = pad(Arrays.copyOfRange(data, offset + position, offset + length));
        return ecb.doFinal(xor(block2, padded));
    }

    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        byte[] aad;
        int length = plaintext.length;
        int i = this.ivSizeInBytes;
        if (length > (Integer.MAX_VALUE - i) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] ciphertext = new byte[plaintext.length + i + 16];
        byte[] iv = Random.randBytes(i);
        System.arraycopy(iv, 0, ciphertext, 0, this.ivSizeInBytes);
        Cipher ecb = localEcbCipher.get();
        ecb.init(1, this.keySpec);
        byte[] n = omac(ecb, 0, iv, 0, iv.length);
        if (associatedData != null) {
            aad = associatedData;
        } else {
            byte[] aad2 = new byte[0];
            aad = aad2;
        }
        byte[] h = omac(ecb, 1, aad, 0, aad.length);
        Cipher ctr = localCtrCipher.get();
        ctr.init(1, this.keySpec, new IvParameterSpec(n));
        ctr.doFinal(plaintext, 0, plaintext.length, ciphertext, this.ivSizeInBytes);
        byte[] t = omac(ecb, 2, ciphertext, this.ivSizeInBytes, plaintext.length);
        int offset = plaintext.length + this.ivSizeInBytes;
        for (int i2 = 0; i2 < 16; i2++) {
            ciphertext[offset + i2] = (byte) ((h[i2] ^ n[i2]) ^ t[i2]);
        }
        return ciphertext;
    }

    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        byte[] aad;
        int plaintextLength = (ciphertext.length - this.ivSizeInBytes) - 16;
        if (plaintextLength < 0) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        Cipher ecb = localEcbCipher.get();
        ecb.init(1, this.keySpec);
        byte[] n = omac(ecb, 0, ciphertext, 0, this.ivSizeInBytes);
        if (associatedData != null) {
            aad = associatedData;
        } else {
            byte[] aad2 = new byte[0];
            aad = aad2;
        }
        byte[] h = omac(ecb, 1, aad, 0, aad.length);
        byte[] t = omac(ecb, 2, ciphertext, this.ivSizeInBytes, plaintextLength);
        byte res = 0;
        int offset = ciphertext.length - 16;
        for (int i = 0; i < 16; i++) {
            res = (byte) ((((ciphertext[offset + i] ^ h[i]) ^ n[i]) ^ t[i]) | res);
        }
        if (res != 0) {
            throw new AEADBadTagException("tag mismatch");
        }
        Cipher ctr = localCtrCipher.get();
        ctr.init(1, this.keySpec, new IvParameterSpec(n));
        return ctr.doFinal(ciphertext, this.ivSizeInBytes, plaintextLength);
    }
}
