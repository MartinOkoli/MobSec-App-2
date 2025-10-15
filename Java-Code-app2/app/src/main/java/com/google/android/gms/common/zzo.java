package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\zzo.smali */
final class zzo extends zzm {
    private final Callable<String> zzaf;

    private zzo(Callable<String> callable) {
        super(false, null, null);
        this.zzaf = callable;
    }

    @Override // com.google.android.gms.common.zzm
    final String getErrorMessage() {
        try {
            return this.zzaf.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
