package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\EciesAeadHkdfDemHelper.smali */
public interface EciesAeadHkdfDemHelper {
    Aead getAead(final byte[] symmetricKeyValue) throws GeneralSecurityException;

    int getSymmetricKeySizeInBytes();
}
