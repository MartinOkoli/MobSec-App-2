package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\PublicKeySignFactory.smali */
public final class PublicKeySignFactory {
    @Deprecated
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle, final KeyManager<PublicKeySign> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PublicKeySignWrapper());
        PrimitiveSet<PublicKeySign> primitives = Registry.getPrimitives(keysetHandle, keyManager, PublicKeySign.class);
        return (PublicKeySign) Registry.wrap(primitives);
    }
}
