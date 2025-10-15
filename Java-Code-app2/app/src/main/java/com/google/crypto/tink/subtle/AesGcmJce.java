package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesGcmJce.smali */
public final class AesGcmJce implements Aead {
    private static final int IV_SIZE_IN_BYTES = 12;
    private static final int TAG_SIZE_IN_BYTES = 16;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.subtle.AesGcmJce.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
            } catch (GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }
    };
    private final SecretKey keySpec;

    public AesGcmJce(final byte[] key) throws GeneralSecurityException {
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, "AES");
    }

    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (plaintext.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] ciphertext = new byte[plaintext.length + 12 + 16];
        byte[] iv = Random.randBytes(12);
        System.arraycopy(iv, 0, ciphertext, 0, 12);
        AlgorithmParameterSpec params = getParams(iv);
        ThreadLocal<Cipher> threadLocal = localCipher;
        threadLocal.get().init(1, this.keySpec, params);
        if (associatedData != null && associatedData.length != 0) {
            threadLocal.get().updateAAD(associatedData);
        }
        int written = threadLocal.get().doFinal(plaintext, 0, plaintext.length, ciphertext, 12);
        if (written != plaintext.length + 16) {
            int actualTagSize = written - plaintext.length;
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(actualTagSize)));
        }
        return ciphertext;
    }

    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.length < 28) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        AlgorithmParameterSpec params = getParams(ciphertext, 0, 12);
        ThreadLocal<Cipher> threadLocal = localCipher;
        threadLocal.get().init(2, this.keySpec, params);
        if (associatedData != null && associatedData.length != 0) {
            threadLocal.get().updateAAD(associatedData);
        }
        return threadLocal.get().doFinal(ciphertext, 12, ciphertext.length - 12);
    }

    private static AlgorithmParameterSpec getParams(final byte[] iv) throws GeneralSecurityException {
        return getParams(iv, 0, iv.length);
    }

    private static AlgorithmParameterSpec getParams(final byte[] buf, int offset, int len) throws GeneralSecurityException {
        if (SubtleUtil.isAndroid() && SubtleUtil.androidApiLevel() <= 19) {
            return new IvParameterSpec(buf, offset, len);
        }
        return new GCMParameterSpec(128, buf, offset, len);
    }
}
