package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\StreamSegmentDecrypter.smali */
public interface StreamSegmentDecrypter {
    void decryptSegment(ByteBuffer ciphertext, int segmentNr, boolean isLastSegment, ByteBuffer plaintext) throws GeneralSecurityException;

    void init(ByteBuffer header, byte[] aad) throws GeneralSecurityException;
}
