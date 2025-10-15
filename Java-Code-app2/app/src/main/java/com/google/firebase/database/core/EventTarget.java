package com.google.firebase.database.core;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\EventTarget.smali */
public interface EventTarget {
    void postEvent(Runnable runnable);

    void restart();

    void shutdown();
}
