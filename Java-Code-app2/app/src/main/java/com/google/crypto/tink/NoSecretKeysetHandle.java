package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\NoSecretKeysetHandle.smali */
public final class NoSecretKeysetHandle {
    @Deprecated
    public static final KeysetHandle parseFrom(final byte[] serialized) throws GeneralSecurityException {
        try {
            Keyset keyset = Keyset.parseFrom(serialized, ExtensionRegistryLite.getEmptyRegistry());
            validate(keyset);
            return KeysetHandle.fromKeyset(keyset);
        } catch (InvalidProtocolBufferException e) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final KeysetHandle read(KeysetReader reader) throws GeneralSecurityException, IOException {
        Keyset keyset = reader.read();
        validate(keyset);
        return KeysetHandle.fromKeyset(keyset);
    }

    private static void validate(Keyset keyset) throws GeneralSecurityException {
        for (Keyset.Key key : keyset.getKeyList()) {
            if (key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException("keyset contains secret key material");
            }
        }
    }
}
