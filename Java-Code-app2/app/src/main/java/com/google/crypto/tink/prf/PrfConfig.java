package com.google.crypto.tink.prf;

import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\prf\PrfConfig.smali */
public final class PrfConfig {
    public static final String PRF_TYPE_URL = new HkdfPrfKeyManager().getKeyType();

    public static void register() throws GeneralSecurityException {
        AesCmacPrfKeyManager.register(true);
        HkdfPrfKeyManager.register(true);
        HmacPrfKeyManager.register(true);
        PrfSetWrapper.register();
    }

    private PrfConfig() {
    }
}
