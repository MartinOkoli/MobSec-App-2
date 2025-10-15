package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\prf\Prf.smali */
public interface Prf {
    byte[] compute(byte[] input, int outputLength) throws GeneralSecurityException;
}
