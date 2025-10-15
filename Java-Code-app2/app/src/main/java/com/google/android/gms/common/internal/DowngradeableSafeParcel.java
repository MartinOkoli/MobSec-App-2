package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\DowngradeableSafeParcel.smali */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdc = new Object();
    private static ClassLoader zzdd = null;
    private static Integer zzde = null;
    private boolean zzdf = false;

    protected abstract boolean prepareForClientVersion(int i);

    private static ClassLoader zzp() {
        synchronized (zzdc) {
        }
        return null;
    }

    protected static Integer getUnparcelClientVersion() {
        synchronized (zzdc) {
        }
        return null;
    }

    protected boolean shouldDowngrade() {
        return this.zzdf;
    }

    public void setShouldDowngrade(boolean z) {
        this.zzdf = z;
    }

    protected static boolean canUnparcelSafely(String str) {
        zzp();
        return true;
    }
}
