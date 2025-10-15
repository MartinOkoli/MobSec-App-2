package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabj.smali */
final class zabj implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaiy;

    zabj(GoogleApiManager.zaa zaaVar) {
        this.zaiy = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaiy.zabg();
    }
}
