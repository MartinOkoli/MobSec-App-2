package com.scottyab.rootbeer.util;

import java.lang.reflect.Method;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\scottyab\rootbeer\util\Utils.smali */
public final class Utils {
    private Utils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSelinuxFlagInEnabled() {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            String selinux = (String) get.invoke(c, "ro.build.selinux");
            return "1".equals(selinux);
        } catch (Exception e) {
            return false;
        }
    }
}
