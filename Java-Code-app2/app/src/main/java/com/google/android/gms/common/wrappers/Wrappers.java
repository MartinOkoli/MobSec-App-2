package com.google.android.gms.common.wrappers;

import android.content.Context;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\wrappers\Wrappers.smali */
public class Wrappers {
    private static Wrappers zzhz = new Wrappers();
    private PackageManagerWrapper zzhy = null;

    private final synchronized PackageManagerWrapper zzi(Context context) {
        if (this.zzhy == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzhy = new PackageManagerWrapper(context);
        }
        return this.zzhy;
    }

    public static PackageManagerWrapper packageManager(Context context) {
        return zzhz.zzi(context);
    }
}
