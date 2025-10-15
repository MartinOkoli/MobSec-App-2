package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.IOException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\KeysetWriter.smali */
public interface KeysetWriter {
    void write(EncryptedKeyset keyset) throws IOException;

    void write(Keyset keyset) throws IOException;
}
