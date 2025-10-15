package com.google.crypto.tink;

import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\CleartextKeysetHandle.smali */
public final class CleartextKeysetHandle {
    @Deprecated
    public static final KeysetHandle parseFrom(final byte[] serialized) throws GeneralSecurityException {
        try {
            Keyset keyset = Keyset.parseFrom(serialized, ExtensionRegistryLite.getEmptyRegistry());
            return KeysetHandle.fromKeyset(keyset);
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static KeysetHandle read(KeysetReader reader) throws GeneralSecurityException, IOException {
        return KeysetHandle.fromKeyset(reader.read());
    }

    public static Keyset getKeyset(KeysetHandle keysetHandle) {
        return keysetHandle.getKeyset();
    }

    public static KeysetHandle fromKeyset(Keyset keyset) throws GeneralSecurityException {
        return KeysetHandle.fromKeyset(keyset);
    }

    public static void write(KeysetHandle handle, KeysetWriter keysetWriter) throws IOException {
        keysetWriter.write(handle.getKeyset());
    }
}
