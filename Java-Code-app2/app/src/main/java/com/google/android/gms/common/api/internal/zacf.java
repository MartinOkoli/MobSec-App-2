package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zacf.smali */
final class zacf implements Runnable {
    private final /* synthetic */ zace zakk;

    zacf(zace zaceVar) {
        this.zakk = zaceVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakk.zakj.zag(new ConnectionResult(4));
    }
}
