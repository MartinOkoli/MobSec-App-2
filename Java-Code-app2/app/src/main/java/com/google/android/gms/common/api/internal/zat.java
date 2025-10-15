package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zat.smali */
final class zat implements Runnable {
    private final /* synthetic */ zas zaeq;

    zat(zas zasVar) {
        this.zaeq = zasVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zax();
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }
}
