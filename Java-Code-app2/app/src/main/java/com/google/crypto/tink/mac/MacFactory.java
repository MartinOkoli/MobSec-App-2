package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\mac\MacFactory.smali */
public final class MacFactory {
    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle, final KeyManager<Mac> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new MacWrapper());
        PrimitiveSet<Mac> primitives = Registry.getPrimitives(keysetHandle, keyManager, Mac.class);
        return (Mac) Registry.wrap(primitives);
    }
}
