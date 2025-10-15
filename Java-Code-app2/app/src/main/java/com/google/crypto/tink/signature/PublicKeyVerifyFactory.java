package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\signature\PublicKeyVerifyFactory.smali */
public final class PublicKeyVerifyFactory {
    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle, final KeyManager<PublicKeyVerify> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PublicKeyVerifyWrapper());
        PrimitiveSet<PublicKeyVerify> primitives = Registry.getPrimitives(keysetHandle, keyManager, PublicKeyVerify.class);
        return (PublicKeyVerify) Registry.wrap(primitives);
    }
}
