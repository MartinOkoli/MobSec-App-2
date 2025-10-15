package com.google.android.gms.internal.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\internal\common\zze.smali */
public class zze extends Handler {
    private static volatile zzf zziu = null;

    public zze() {
    }

    public zze(Looper looper) {
        super(looper);
    }

    public zze(Looper looper, Handler.Callback callback) {
        super(looper, callback);
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }
}
