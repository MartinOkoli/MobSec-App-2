package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.AEADBadTagException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\ChaCha20Poly1305Base.smali */
abstract class ChaCha20Poly1305Base implements Aead {
    private final ChaCha20Base chacha20;
    private final ChaCha20Base macKeyChaCha20;

    abstract ChaCha20Base newChaCha20Instance(final byte[] key, int initialCounter) throws InvalidKeyException;

    public ChaCha20Poly1305Base(final byte[] key) throws InvalidKeyException {
        this.chacha20 = newChaCha20Instance(key, 1);
        this.macKeyChaCha20 = newChaCha20Instance(key, 0);
    }

    public byte[] encrypt(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (plaintext.length > (Integer.MAX_VALUE - this.chacha20.nonceSizeInBytes()) - 16) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer ciphertext = ByteBuffer.allocate(plaintext.length + this.chacha20.nonceSizeInBytes() + 16);
        encrypt(ciphertext, plaintext, associatedData);
        return ciphertext.array();
    }

    private void encrypt(ByteBuffer output, final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        if (output.remaining() < plaintext.length + this.chacha20.nonceSizeInBytes() + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        int firstPosition = output.position();
        this.chacha20.encrypt(output, plaintext);
        output.position(firstPosition);
        byte[] nonce = new byte[this.chacha20.nonceSizeInBytes()];
        output.get(nonce);
        output.limit(output.limit() - 16);
        byte[] aad = associatedData;
        if (aad == null) {
            aad = new byte[0];
        }
        byte[] tag = Poly1305.computeMac(getMacKey(nonce), macDataRfc8439(aad, output));
        output.limit(output.limit() + 16);
        output.put(tag);
    }

    public byte[] decrypt(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        return decrypt(ByteBuffer.wrap(ciphertext), associatedData);
    }

    private byte[] decrypt(ByteBuffer ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (ciphertext.remaining() < this.chacha20.nonceSizeInBytes() + 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        int firstPosition = ciphertext.position();
        byte[] tag = new byte[16];
        ciphertext.position(ciphertext.limit() - 16);
        ciphertext.get(tag);
        ciphertext.position(firstPosition);
        ciphertext.limit(ciphertext.limit() - 16);
        byte[] nonce = new byte[this.chacha20.nonceSizeInBytes()];
        ciphertext.get(nonce);
        byte[] aad = associatedData;
        if (aad == null) {
            aad = new byte[0];
        }
        try {
            Poly1305.verifyMac(getMacKey(nonce), macDataRfc8439(aad, ciphertext), tag);
            ciphertext.position(firstPosition);
            return this.chacha20.decrypt(ciphertext);
        } catch (GeneralSecurityException ex) {
            throw new AEADBadTagException(ex.toString());
        }
    }

    private byte[] getMacKey(final byte[] nonce) throws GeneralSecurityException {
        ByteBuffer firstBlock = this.macKeyChaCha20.chacha20Block(nonce, 0);
        byte[] result = new byte[32];
        firstBlock.get(result);
        return result;
    }

    private static byte[] macDataRfc8439(final byte[] aad, ByteBuffer ciphertext) {
        int aadPaddedLen = aad.length % 16 == 0 ? aad.length : (aad.length + 16) - (aad.length % 16);
        int ciphertextLen = ciphertext.remaining();
        int ciphertextPaddedLen = ciphertextLen % 16 == 0 ? ciphertextLen : (ciphertextLen + 16) - (ciphertextLen % 16);
        ByteBuffer macData = ByteBuffer.allocate(aadPaddedLen + ciphertextPaddedLen + 16).order(ByteOrder.LITTLE_ENDIAN);
        macData.put(aad);
        macData.position(aadPaddedLen);
        macData.put(ciphertext);
        macData.position(aadPaddedLen + ciphertextPaddedLen);
        macData.putLong(aad.length);
        macData.putLong(ciphertextLen);
        return macData.array();
    }
}
