package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzc.smali */
final class zzc<TResult, TContinuationResult> implements zzq<TResult> {
    private final Executor zzd;
    private final Continuation<TResult, TContinuationResult> zze;
    private final zzu<TContinuationResult> zzf;

    public zzc(Executor executor, Continuation<TResult, TContinuationResult> continuation, zzu<TContinuationResult> zzuVar) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzuVar;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void onComplete(Task<TResult> task) {
        this.zzd.execute(new zzd(this, task));
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void cancel() {
        throw new UnsupportedOperationException();
    }
}
