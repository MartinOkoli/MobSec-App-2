package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\HybridDecrypt.smali */
public interface HybridDecrypt {
    byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException;
}
