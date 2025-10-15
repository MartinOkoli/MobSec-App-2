package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzh.smali */
final class zzh implements Runnable {
    private final /* synthetic */ zzg zzk;

    zzh(zzg zzgVar) {
        this.zzk = zzgVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzk.mLock) {
            if (this.zzk.zzj != null) {
                this.zzk.zzj.onCanceled();
            }
        }
    }
}
