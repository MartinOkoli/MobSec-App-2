package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\wrappers\InstantApps.smali */
public class InstantApps {
    private static Context zzhv;
    private static Boolean zzhw;

    public static synchronized boolean isInstantApp(Context context) {
        Boolean bool;
        Context applicationContext = context.getApplicationContext();
        Context context2 = zzhv;
        if (context2 != null && (bool = zzhw) != null && context2 == applicationContext) {
            return bool.booleanValue();
        }
        zzhw = null;
        if (PlatformVersion.isAtLeastO()) {
            zzhw = Boolean.valueOf(applicationContext.getPackageManager().isInstantApp());
        } else {
            try {
                context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                zzhw = true;
            } catch (ClassNotFoundException e) {
                zzhw = false;
            }
        }
        zzhv = applicationContext;
        return zzhw.booleanValue();
    }
}
