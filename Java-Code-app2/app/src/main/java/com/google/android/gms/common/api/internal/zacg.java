package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zacg.smali */
final class zacg implements Runnable {
    private final /* synthetic */ com.google.android.gms.signin.internal.zaj zagr;
    private final /* synthetic */ zace zakk;

    zacg(zace zaceVar, com.google.android.gms.signin.internal.zaj zajVar) {
        this.zakk = zaceVar;
        this.zagr = zajVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakk.zac(this.zagr);
    }
}
