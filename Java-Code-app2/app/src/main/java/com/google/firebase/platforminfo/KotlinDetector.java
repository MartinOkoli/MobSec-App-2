package com.google.firebase.platforminfo;

import kotlin.KotlinVersion;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\platforminfo\KotlinDetector.smali */
public final class KotlinDetector {
    private KotlinDetector() {
    }

    public static String detectVersion() {
        try {
            return KotlinVersion.CURRENT.toString();
        } catch (NoClassDefFoundError e) {
            return null;
        }
    }
}
