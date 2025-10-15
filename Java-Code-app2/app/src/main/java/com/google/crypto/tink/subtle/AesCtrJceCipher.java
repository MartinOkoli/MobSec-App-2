package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesCtrJceCipher.smali */
public final class AesCtrJceCipher implements IndCpaCipher {
    private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int MIN_IV_SIZE_IN_BYTES = 12;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.subtle.AesCtrJceCipher.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance(AesCtrJceCipher.CIPHER_ALGORITHM);
            } catch (GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }
    };
    private final int blockSize;
    private final int ivSize;
    private final SecretKeySpec keySpec;

    public AesCtrJceCipher(final byte[] key, int ivSize) throws GeneralSecurityException {
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        int blockSize = localCipher.get().getBlockSize();
        this.blockSize = blockSize;
        if (ivSize < 12 || ivSize > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.ivSize = ivSize;
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException {
        int length = plaintext.length;
        int i = this.ivSize;
        if (length > Integer.MAX_VALUE - i) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - this.ivSize));
        }
        byte[] ciphertext = new byte[plaintext.length + i];
        byte[] iv = Random.randBytes(i);
        System.arraycopy(iv, 0, ciphertext, 0, this.ivSize);
        doCtr(plaintext, 0, plaintext.length, ciphertext, this.ivSize, iv, true);
        return ciphertext;
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException {
        int length = ciphertext.length;
        int i = this.ivSize;
        if (length < i) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] iv = new byte[i];
        System.arraycopy(ciphertext, 0, iv, 0, i);
        int length2 = ciphertext.length;
        int i2 = this.ivSize;
        byte[] plaintext = new byte[length2 - i2];
        doCtr(ciphertext, i2, ciphertext.length - i2, plaintext, 0, iv, false);
        return plaintext;
    }

    private void doCtr(final byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset, final byte[] iv, boolean encrypt) throws GeneralSecurityException {
        Cipher cipher = localCipher.get();
        byte[] counter = new byte[this.blockSize];
        System.arraycopy(iv, 0, counter, 0, this.ivSize);
        IvParameterSpec paramSpec = new IvParameterSpec(counter);
        if (encrypt) {
            cipher.init(1, this.keySpec, paramSpec);
        } else {
            cipher.init(2, this.keySpec, paramSpec);
        }
        int numBytes = cipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
        if (numBytes != inputLen) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }
}
