package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zap.smali */
final class zap extends ThreadLocal<Boolean> {
    zap() {
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ Boolean initialValue() {
        return false;
    }
}
