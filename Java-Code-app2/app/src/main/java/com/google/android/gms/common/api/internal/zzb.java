package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zzb.smali */
final class zzb implements Runnable {
    private final /* synthetic */ LifecycleCallback zzbi;
    private final /* synthetic */ String zzbj;
    private final /* synthetic */ zza zzbk;

    zzb(zza zzaVar, LifecycleCallback lifecycleCallback, String str) {
        this.zzbk = zzaVar;
        this.zzbi = lifecycleCallback;
        this.zzbj = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzbk.zzbg > 0) {
            this.zzbi.onCreate(this.zzbk.zzbh != null ? this.zzbk.zzbh.getBundle(this.zzbj) : null);
        }
        if (this.zzbk.zzbg >= 2) {
            this.zzbi.onStart();
        }
        if (this.zzbk.zzbg >= 3) {
            this.zzbi.onResume();
        }
        if (this.zzbk.zzbg >= 4) {
            this.zzbi.onStop();
        }
        if (this.zzbk.zzbg >= 5) {
            this.zzbi.onDestroy();
        }
    }
}
