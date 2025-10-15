package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\PrimitiveWrapper.smali */
public interface PrimitiveWrapper<B, P> {
    Class<B> getInputPrimitiveClass();

    Class<P> getPrimitiveClass();

    P wrap(PrimitiveSet<B> set) throws GeneralSecurityException;
}
