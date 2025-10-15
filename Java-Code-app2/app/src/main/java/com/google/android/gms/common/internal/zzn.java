package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zzn.smali */
public abstract class zzn extends com.google.android.gms.internal.common.zzb implements zzm {
    public static zzm zzc(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGoogleCertificatesApi");
        return iInterfaceQueryLocalInterface instanceof zzm ? (zzm) iInterfaceQueryLocalInterface : new zzo(iBinder);
    }
}
