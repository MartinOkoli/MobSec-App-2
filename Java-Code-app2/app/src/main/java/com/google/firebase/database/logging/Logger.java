package com.google.firebase.database.logging;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\logging\Logger.smali */
public interface Logger {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\logging\Logger$Level.smali */
    public enum Level {
        DEBUG,
        INFO,
        WARN,
        ERROR,
        NONE
    }

    Level getLogLevel();

    void onLogMessage(Level level, String str, String str2, long j);
}
