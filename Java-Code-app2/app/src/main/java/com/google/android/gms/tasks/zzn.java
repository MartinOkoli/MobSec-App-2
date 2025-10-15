package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzn.smali */
final class zzn implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzm zzq;

    zzn(zzm zzmVar, Task task) {
        this.zzq = zzmVar;
        this.zzg = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzq.mLock) {
            if (this.zzq.zzp != null) {
                this.zzq.zzp.onSuccess(this.zzg.getResult());
            }
        }
    }
}
