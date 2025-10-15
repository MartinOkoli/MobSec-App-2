package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\IndCpaCipher.smali */
public interface IndCpaCipher {
    byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException;

    byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException;
}
