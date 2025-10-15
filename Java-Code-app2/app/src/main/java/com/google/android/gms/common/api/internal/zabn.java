package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabn.smali */
final class zabn implements Runnable {
    private final /* synthetic */ zabm zaja;

    zabn(zabm zabmVar) {
        this.zaja = zabmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaja.zaiy.zaio.disconnect();
    }
}
