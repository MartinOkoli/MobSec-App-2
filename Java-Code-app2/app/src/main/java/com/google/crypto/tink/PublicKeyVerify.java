package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\PublicKeyVerify.smali */
public interface PublicKeyVerify {
    void verify(final byte[] signature, final byte[] data) throws GeneralSecurityException;
}
