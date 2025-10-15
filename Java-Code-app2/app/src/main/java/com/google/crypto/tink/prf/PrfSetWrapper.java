package com.google.crypto.tink.prf;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

@Immutable
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\prf\PrfSetWrapper.smali */
public class PrfSetWrapper implements PrimitiveWrapper<Prf, PrfSet> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PrfSet wrap(PrimitiveSet<Prf> set) throws GeneralSecurityException {
        return new WrappedPrfSet(set, (AnonymousClass1) null);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PrfSet> getPrimitiveClass() {
        return PrfSet.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Prf> getInputPrimitiveClass() {
        return Prf.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(new PrfSetWrapper());
    }
}
