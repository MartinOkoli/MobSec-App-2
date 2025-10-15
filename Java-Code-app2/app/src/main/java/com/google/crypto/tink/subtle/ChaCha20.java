package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\ChaCha20.smali */
class ChaCha20 extends ChaCha20Base {
    ChaCha20(final byte[] key, int initialCounter) throws InvalidKeyException {
        super(key, initialCounter);
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int[] createInitialState(final int[] nonce, int counter) {
        if (nonce.length != nonceSizeInBytes() / 4) {
            throw new IllegalArgumentException(String.format("ChaCha20 uses 96-bit nonces, but got a %d-bit nonce", Integer.valueOf(nonce.length * 32)));
        }
        int[] state = new int[16];
        ChaCha20Base.setSigmaAndKey(state, this.key);
        state[12] = counter;
        System.arraycopy(nonce, 0, state, 13, nonce.length);
        return state;
    }

    @Override // com.google.crypto.tink.subtle.ChaCha20Base
    int nonceSizeInBytes() {
        return 12;
    }
}
