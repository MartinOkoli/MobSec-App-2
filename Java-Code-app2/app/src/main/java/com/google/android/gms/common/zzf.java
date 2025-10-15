package com.google.android.gms.common;

import java.util.Arrays;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\zzf.smali */
final class zzf extends zze {
    private final byte[] zzu;

    zzf(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zzu = bArr;
    }

    @Override // com.google.android.gms.common.zze
    final byte[] getBytes() {
        return this.zzu;
    }
}
