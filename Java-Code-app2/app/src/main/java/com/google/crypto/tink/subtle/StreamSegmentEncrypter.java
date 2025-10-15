package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\subtle\StreamSegmentEncrypter.smali */
public interface StreamSegmentEncrypter {
    void encryptSegment(ByteBuffer part1, ByteBuffer part2, boolean isLastSegment, ByteBuffer ciphertext) throws GeneralSecurityException;

    void encryptSegment(ByteBuffer plaintext, boolean isLastSegment, ByteBuffer ciphertext) throws GeneralSecurityException;

    ByteBuffer getHeader();
}
