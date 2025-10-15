package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zza.smali */
final class zza extends CancellationToken {
    private final zzu<Void> zza = new zzu<>();

    zza() {
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(OnTokenCanceledListener onTokenCanceledListener) {
        this.zza.addOnSuccessListener(new zzb(this, onTokenCanceledListener));
        return this;
    }

    public final void cancel() {
        this.zza.trySetResult(null);
    }
}
