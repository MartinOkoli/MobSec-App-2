package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\HybridEncrypt.smali */
public interface HybridEncrypt {
    byte[] encrypt(final byte[] plaintext, final byte[] contextInfo) throws GeneralSecurityException;
}
