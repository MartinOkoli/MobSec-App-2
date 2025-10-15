package com.google.firebase.database.core;

import java.lang.Thread;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\ThreadInitializer.smali */
public interface ThreadInitializer {
    public static final ThreadInitializer defaultInstance = new ThreadInitializer() { // from class: com.google.firebase.database.core.ThreadInitializer.1
        @Override // com.google.firebase.database.core.ThreadInitializer
        public void setName(Thread t, String name) {
            t.setName(name);
        }

        @Override // com.google.firebase.database.core.ThreadInitializer
        public void setDaemon(Thread t, boolean isDaemon) {
            t.setDaemon(isDaemon);
        }

        @Override // com.google.firebase.database.core.ThreadInitializer
        public void setUncaughtExceptionHandler(Thread t, Thread.UncaughtExceptionHandler handler) {
            t.setUncaughtExceptionHandler(handler);
        }
    };

    void setDaemon(Thread thread, boolean z);

    void setName(Thread thread, String str);

    void setUncaughtExceptionHandler(Thread thread, Thread.UncaughtExceptionHandler uncaughtExceptionHandler);
}
