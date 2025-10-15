package com.google.crypto.tink;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\KmsClient.smali */
public interface KmsClient {
    boolean doesSupport(String keyUri);

    Aead getAead(String keyUri) throws GeneralSecurityException;

    KmsClient withCredentials(String credentialPath) throws GeneralSecurityException;

    KmsClient withDefaultCredentials() throws GeneralSecurityException;
}
