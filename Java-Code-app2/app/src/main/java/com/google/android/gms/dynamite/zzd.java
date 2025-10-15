package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\dynamite\zzd.smali */
final class zzd implements DynamiteModule.VersionPolicy {
    zzd() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zzaVar) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zzb zzbVar = new DynamiteModule.VersionPolicy.zzb();
        zzbVar.zzir = zzaVar.getLocalVersion(context, str);
        zzbVar.zzis = zzaVar.zza(context, str, true);
        if (zzbVar.zzir == 0 && zzbVar.zzis == 0) {
            zzbVar.zzit = 0;
        } else if (zzbVar.zzir >= zzbVar.zzis) {
            zzbVar.zzit = -1;
        } else {
            zzbVar.zzit = 1;
        }
        return zzbVar;
    }
}
