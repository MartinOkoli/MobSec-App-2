package com.google.crypto.tink.subtle;

import com.google.crypto.tink.DeterministicAead;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\AesSiv.smali */
public final class AesSiv implements DeterministicAead {
    private final byte[] aesCtrKey;
    private final PrfAesCmac cmacForS2V;
    private static final Collection<Integer> KEY_SIZES = Arrays.asList(64);
    private static final byte[] BLOCK_ZERO = new byte[16];
    private static final byte[] BLOCK_ONE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

    public AesSiv(final byte[] key) throws GeneralSecurityException {
        if (!KEY_SIZES.contains(Integer.valueOf(key.length))) {
            throw new InvalidKeyException("invalid key size: " + key.length + " bytes; key must have 64 bytes");
        }
        byte[] k1 = Arrays.copyOfRange(key, 0, key.length / 2);
        this.aesCtrKey = Arrays.copyOfRange(key, key.length / 2, key.length);
        this.cmacForS2V = new PrfAesCmac(k1);
    }

    private byte[] s2v(final byte[]... s) throws GeneralSecurityException {
        byte[] result;
        byte[] currBlock;
        if (s.length == 0) {
            return this.cmacForS2V.compute(BLOCK_ONE, 16);
        }
        byte[] result2 = this.cmacForS2V.compute(BLOCK_ZERO, 16);
        for (int i = 0; i < s.length - 1; i++) {
            if (s[i] == null) {
                currBlock = new byte[0];
            } else {
                currBlock = s[i];
            }
            result2 = Bytes.xor(AesUtil.dbl(result2), this.cmacForS2V.compute(currBlock, 16));
        }
        int i2 = s.length;
        byte[] lastBlock = s[i2 - 1];
        if (lastBlock.length >= 16) {
            result = Bytes.xorEnd(lastBlock, result2);
        } else {
            result = Bytes.xor(AesUtil.cmacPad(lastBlock), AesUtil.dbl(result2));
        }
        return this.cmacForS2V.compute(result, 16);
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] encryptDeterministically(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (plaintext.length > 2147483631) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Cipher aesCtr = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] computedIv = s2v(associatedData, plaintext);
        byte[] ivForJavaCrypto = (byte[]) computedIv.clone();
        ivForJavaCrypto[8] = (byte) (ivForJavaCrypto[8] & ByteCompanionObject.MAX_VALUE);
        ivForJavaCrypto[12] = (byte) (ivForJavaCrypto[12] & ByteCompanionObject.MAX_VALUE);
        aesCtr.init(1, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(ivForJavaCrypto));
        byte[] ctrCiphertext = aesCtr.doFinal(plaintext);
        return Bytes.concat(computedIv, ctrCiphertext);
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] decryptDeterministically(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.length < 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        Cipher aesCtr = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] expectedIv = Arrays.copyOfRange(ciphertext, 0, 16);
        byte[] ivForJavaCrypto = (byte[]) expectedIv.clone();
        ivForJavaCrypto[8] = (byte) (ivForJavaCrypto[8] & ByteCompanionObject.MAX_VALUE);
        ivForJavaCrypto[12] = (byte) (ivForJavaCrypto[12] & ByteCompanionObject.MAX_VALUE);
        aesCtr.init(2, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(ivForJavaCrypto));
        byte[] ctrCiphertext = Arrays.copyOfRange(ciphertext, 16, ciphertext.length);
        byte[] decryptedPt = aesCtr.doFinal(ctrCiphertext);
        if (ctrCiphertext.length == 0 && decryptedPt == null && SubtleUtil.isAndroid()) {
            decryptedPt = new byte[0];
        }
        byte[] computedIv = s2v(associatedData, decryptedPt);
        if (Bytes.equal(expectedIv, computedIv)) {
            return decryptedPt;
        }
        throw new AEADBadTagException("Integrity check failed.");
    }
}
