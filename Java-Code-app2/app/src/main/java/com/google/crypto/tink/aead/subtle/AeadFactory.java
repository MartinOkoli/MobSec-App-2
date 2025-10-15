package com.google.crypto.tink.aead.subtle;

import com.google.crypto.tink.Aead;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\aead\subtle\AeadFactory.smali */
public interface AeadFactory {
    Aead createAead(final byte[] symmetricKey) throws GeneralSecurityException;

    int getKeySizeInBytes();
}
