package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\config\zzb.smali */
final class zzb extends GservicesValue<Long> {
    zzb(String str, Long l) {
        super(str, l);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Long zzd(String str) {
        GservicesValue.zza zzaVar = null;
        return zzaVar.getLong(this.mKey, (Long) this.zzbq);
    }
}
