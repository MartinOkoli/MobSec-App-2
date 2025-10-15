package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\Mac.smali */
public interface Mac {
    byte[] computeMac(final byte[] data) throws GeneralSecurityException;

    void verifyMac(final byte[] mac, final byte[] data) throws GeneralSecurityException;
}
