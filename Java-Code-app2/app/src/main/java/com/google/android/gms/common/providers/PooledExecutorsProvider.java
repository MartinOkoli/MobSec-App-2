package com.google.android.gms.common.providers;

import java.util.concurrent.ScheduledExecutorService;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\providers\PooledExecutorsProvider.smali */
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zzey;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\providers\PooledExecutorsProvider$PooledExecutorFactory.smali */
    public interface PooledExecutorFactory {
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    public static synchronized PooledExecutorFactory getInstance() {
        if (zzey == null) {
            zzey = new zza();
        }
        return zzey;
    }

    private PooledExecutorsProvider() {
    }
}
