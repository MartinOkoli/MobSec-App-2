package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\zzg.smali */
abstract class zzg extends zze {
    private static final WeakReference<byte[]> zzw = new WeakReference<>(null);
    private WeakReference<byte[]> zzv;

    zzg(byte[] bArr) {
        super(bArr);
        this.zzv = zzw;
    }

    protected abstract byte[] zzd();

    @Override // com.google.android.gms.common.zze
    final byte[] getBytes() {
        byte[] bArrZzd;
        synchronized (this) {
            bArrZzd = this.zzv.get();
            if (bArrZzd == null) {
                bArrZzd = zzd();
                this.zzv = new WeakReference<>(bArrZzd);
            }
        }
        return bArrZzd;
    }
}
