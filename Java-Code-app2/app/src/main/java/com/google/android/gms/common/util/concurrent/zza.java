package com.google.android.gms.common.util.concurrent;

import android.os.Process;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\util\concurrent\zza.smali */
final class zza implements Runnable {
    private final int priority;
    private final Runnable zzhu;

    public zza(Runnable runnable, int i) {
        this.zzhu = runnable;
        this.priority = i;
    }

    @Override // java.lang.Runnable
    public final void run() throws SecurityException, IllegalArgumentException {
        Process.setThreadPriority(this.priority);
        this.zzhu.run();
    }
}
