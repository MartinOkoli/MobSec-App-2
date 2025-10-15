package com.google.firebase.database.core;

import java.util.concurrent.ScheduledFuture;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\RunLoop.smali */
public interface RunLoop {
    void restart();

    ScheduledFuture schedule(Runnable runnable, long j);

    void scheduleNow(Runnable runnable);

    void shutdown();
}
