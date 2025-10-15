package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\daead\DeterministicAeadFactory.smali */
public final class DeterministicAeadFactory {
    @Deprecated
    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle, final KeyManager<DeterministicAead> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new DeterministicAeadWrapper());
        PrimitiveSet<DeterministicAead> primitives = Registry.getPrimitives(keysetHandle, keyManager, DeterministicAead.class);
        return (DeterministicAead) Registry.wrap(primitives);
    }
}
