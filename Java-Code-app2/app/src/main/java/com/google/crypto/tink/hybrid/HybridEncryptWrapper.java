package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\hybrid\HybridEncryptWrapper.smali */
class HybridEncryptWrapper implements PrimitiveWrapper<HybridEncrypt, HybridEncrypt> {
    HybridEncryptWrapper() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public HybridEncrypt wrap(final PrimitiveSet<HybridEncrypt> primitives) {
        return new WrappedHybridEncrypt(primitives);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getPrimitiveClass() {
        return HybridEncrypt.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getInputPrimitiveClass() {
        return HybridEncrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new HybridEncryptWrapper());
    }
}
