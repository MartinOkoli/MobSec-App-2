package com.google.android.gms.common.util;

import android.os.Looper;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\util\zzc.smali */
public final class zzc {
    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
