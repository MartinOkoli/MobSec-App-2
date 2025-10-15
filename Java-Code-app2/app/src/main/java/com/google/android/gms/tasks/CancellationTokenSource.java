package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\CancellationTokenSource.smali */
public class CancellationTokenSource {
    private final zza zzc = new zza();

    public CancellationToken getToken() {
        return this.zzc;
    }

    public void cancel() {
        this.zzc.cancel();
    }
}
