package com.google.android.gms.common.util;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\util\Clock.smali */
public interface Clock {
    long currentThreadTimeMillis();

    long currentTimeMillis();

    long elapsedRealtime();

    long nanoTime();
}
