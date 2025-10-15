package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzl.smali */
final class zzl implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzk zzo;

    zzl(zzk zzkVar, Task task) {
        this.zzo = zzkVar;
        this.zzg = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzo.mLock) {
            if (this.zzo.zzn != null) {
                this.zzo.zzn.onFailure(this.zzg.getException());
            }
        }
    }
}
