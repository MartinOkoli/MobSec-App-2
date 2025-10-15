package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\KeyWrap.smali */
public interface KeyWrap {
    byte[] unwrap(final byte[] data) throws GeneralSecurityException;

    byte[] wrap(final byte[] data) throws GeneralSecurityException;
}
