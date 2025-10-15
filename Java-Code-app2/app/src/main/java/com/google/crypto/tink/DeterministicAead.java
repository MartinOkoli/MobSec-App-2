package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\DeterministicAead.smali */
public interface DeterministicAead {
    byte[] decryptDeterministically(final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException;

    byte[] encryptDeterministically(final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException;
}
