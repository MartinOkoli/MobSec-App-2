package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\ChaCha20Base.smali */
abstract class ChaCha20Base implements IndCpaCipher {
    public static final int BLOCK_SIZE_IN_BYTES = 64;
    public static final int BLOCK_SIZE_IN_INTS = 16;
    public static final int KEY_SIZE_IN_BYTES = 32;
    public static final int KEY_SIZE_IN_INTS = 8;
    private static final int[] SIGMA = toIntArray(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107});
    private final int initialCounter;
    int[] key;

    abstract int[] createInitialState(final int[] nonce, int counter);

    abstract int nonceSizeInBytes();

    ChaCha20Base(final byte[] key, int initialCounter) throws InvalidKeyException {
        if (key.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.key = toIntArray(key);
        this.initialCounter = initialCounter;
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException {
        if (plaintext.length > Integer.MAX_VALUE - nonceSizeInBytes()) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer ciphertext = ByteBuffer.allocate(nonceSizeInBytes() + plaintext.length);
        encrypt(ciphertext, plaintext);
        return ciphertext.array();
    }

    void encrypt(ByteBuffer output, final byte[] plaintext) throws GeneralSecurityException {
        if (output.remaining() - nonceSizeInBytes() < plaintext.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        byte[] nonce = Random.randBytes(nonceSizeInBytes());
        output.put(nonce);
        process(nonce, output, ByteBuffer.wrap(plaintext));
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException {
        return decrypt(ByteBuffer.wrap(ciphertext));
    }

    byte[] decrypt(ByteBuffer ciphertext) throws GeneralSecurityException {
        if (ciphertext.remaining() < nonceSizeInBytes()) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] nonce = new byte[nonceSizeInBytes()];
        ciphertext.get(nonce);
        ByteBuffer plaintext = ByteBuffer.allocate(ciphertext.remaining());
        process(nonce, plaintext, ciphertext);
        return plaintext.array();
    }

    private void process(final byte[] nonce, ByteBuffer output, ByteBuffer input) throws GeneralSecurityException {
        int length = input.remaining();
        int numBlocks = (length / 64) + 1;
        for (int i = 0; i < numBlocks; i++) {
            ByteBuffer keyStreamBlock = chacha20Block(nonce, this.initialCounter + i);
            if (i == numBlocks - 1) {
                Bytes.xor(output, input, keyStreamBlock, length % 64);
            } else {
                Bytes.xor(output, input, keyStreamBlock, 64);
            }
        }
    }

    ByteBuffer chacha20Block(final byte[] nonce, int counter) {
        int[] state = createInitialState(toIntArray(nonce), counter);
        int[] workingState = (int[]) state.clone();
        shuffleState(workingState);
        for (int i = 0; i < state.length; i++) {
            state[i] = state[i] + workingState[i];
        }
        ByteBuffer out = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        out.asIntBuffer().put(state, 0, 16);
        return out;
    }

    static void setSigmaAndKey(int[] state, final int[] key) {
        int[] iArr = SIGMA;
        System.arraycopy(iArr, 0, state, 0, iArr.length);
        System.arraycopy(key, 0, state, iArr.length, 8);
    }

    static void shuffleState(final int[] state) {
        for (int i = 0; i < 10; i++) {
            quarterRound(state, 0, 4, 8, 12);
            quarterRound(state, 1, 5, 9, 13);
            quarterRound(state, 2, 6, 10, 14);
            quarterRound(state, 3, 7, 11, 15);
            quarterRound(state, 0, 5, 10, 15);
            quarterRound(state, 1, 6, 11, 12);
            quarterRound(state, 2, 7, 8, 13);
            quarterRound(state, 3, 4, 9, 14);
        }
    }

    static void quarterRound(int[] x, int a, int b, int c, int d) {
        x[a] = x[a] + x[b];
        x[d] = rotateLeft(x[d] ^ x[a], 16);
        x[c] = x[c] + x[d];
        x[b] = rotateLeft(x[b] ^ x[c], 12);
        x[a] = x[a] + x[b];
        x[d] = rotateLeft(x[d] ^ x[a], 8);
        x[c] = x[c] + x[d];
        x[b] = rotateLeft(x[b] ^ x[c], 7);
    }

    static int[] toIntArray(final byte[] input) {
        IntBuffer intBuffer = ByteBuffer.wrap(input).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] ret = new int[intBuffer.remaining()];
        intBuffer.get(ret);
        return ret;
    }

    private static int rotateLeft(int x, int y) {
        return (x << y) | (x >>> (-y));
    }
}
