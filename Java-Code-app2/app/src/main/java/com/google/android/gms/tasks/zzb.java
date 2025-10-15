package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzb.smali */
final class zzb implements OnSuccessListener<Void> {
    private final /* synthetic */ OnTokenCanceledListener zzb;

    zzb(zza zzaVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.zzb = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* synthetic */ void onSuccess(Void r1) {
        this.zzb.onCanceled();
    }
}
