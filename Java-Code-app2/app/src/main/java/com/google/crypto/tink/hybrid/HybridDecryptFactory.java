package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\HybridDecryptFactory.smali */
public final class HybridDecryptFactory {
    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle, final KeyManager<HybridDecrypt> keyManager) throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new HybridDecryptWrapper());
        PrimitiveSet<HybridDecrypt> primitives = Registry.getPrimitives(keysetHandle, keyManager, HybridDecrypt.class);
        return (HybridDecrypt) Registry.wrap(primitives);
    }
}
