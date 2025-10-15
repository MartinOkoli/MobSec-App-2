package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabc.smali */
final class zabc extends zabr {
    private WeakReference<zaaw> zahm;

    zabc(zaaw zaawVar) {
        this.zahm = new WeakReference<>(zaawVar);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zas() {
        zaaw zaawVar = this.zahm.get();
        if (zaawVar == null) {
            return;
        }
        zaawVar.resume();
    }
}
