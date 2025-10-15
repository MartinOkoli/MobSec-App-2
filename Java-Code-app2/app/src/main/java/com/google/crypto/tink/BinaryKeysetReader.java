package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\BinaryKeysetReader.smali */
public final class BinaryKeysetReader implements KeysetReader {
    private final boolean closeStreamAfterReading;
    private final InputStream inputStream;

    public static KeysetReader withInputStream(InputStream stream) {
        return new BinaryKeysetReader(stream, false);
    }

    public static KeysetReader withBytes(final byte[] bytes) {
        return new BinaryKeysetReader(new ByteArrayInputStream(bytes), true);
    }

    public static KeysetReader withFile(File file) throws IOException {
        return new BinaryKeysetReader(new FileInputStream(file), true);
    }

    private BinaryKeysetReader(InputStream stream, boolean closeStreamAfterReading) {
        this.inputStream = stream;
        this.closeStreamAfterReading = closeStreamAfterReading;
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            Keyset keyset = Keyset.parseFrom(this.inputStream, ExtensionRegistryLite.getEmptyRegistry());
            return keyset;
        } finally {
            if (this.closeStreamAfterReading) {
                this.inputStream.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            EncryptedKeyset keyset = EncryptedKeyset.parseFrom(this.inputStream, ExtensionRegistryLite.getEmptyRegistry());
            return keyset;
        } finally {
            if (this.closeStreamAfterReading) {
                this.inputStream.close();
            }
        }
    }
}
