package com.google.crypto.tink.subtle.prf;

import com.google.errorprone.annotations.Immutable;
import java.io.InputStream;

@Immutable
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\prf\StreamingPrf.smali */
public interface StreamingPrf {
    InputStream computePrf(final byte[] input);
}
