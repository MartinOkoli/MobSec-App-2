package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;
import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\XChaCha20.smali */
class XChaCha20 extends ChaCha20Base {
    XChaCha20(byte[] key, int initialCounter) throws InvalidKeyException {
        super(key, initialCounter);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int[] createInitialState(final int[] nonce, int counter) {
        if (nonce.length != nonceSizeInBytes() / 4) {
            throw new IllegalArgumentException(String.format("XChaCha20 uses 192-bit nonces, but got a %d-bit nonce", Integer.valueOf(nonce.length * 32)));
        }
        int[] state = new int[16];
        ChaCha20Base.setSigmaAndKey(state, hChaCha20(this.key, nonce));
        state[12] = counter;
        state[13] = 0;
        state[14] = nonce[4];
        state[15] = nonce[5];
        return state;
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int nonceSizeInBytes() {
        return 24;
    }

    static int[] hChaCha20(final int[] key, final int[] nonce) {
        int[] state = new int[16];
        ChaCha20Base.setSigmaAndKey(state, key);
        state[12] = nonce[0];
        state[13] = nonce[1];
        state[14] = nonce[2];
        state[15] = nonce[3];
        ChaCha20Base.shuffleState(state);
        state[4] = state[12];
        state[5] = state[13];
        state[6] = state[14];
        state[7] = state[15];
        return Arrays.copyOf(state, 8);
    }
}
