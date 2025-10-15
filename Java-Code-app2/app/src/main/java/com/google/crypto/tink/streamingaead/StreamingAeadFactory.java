package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\streamingaead\StreamingAeadFactory.smali */
public final class StreamingAeadFactory {
    public static StreamingAead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static StreamingAead getPrimitive(KeysetHandle keysetHandle, final KeyManager<StreamingAead> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new StreamingAeadWrapper());
        PrimitiveSet<StreamingAead> primitives = Registry.getPrimitives(keysetHandle, keyManager, StreamingAead.class);
        return (StreamingAead) Registry.wrap(primitives);
    }
}
