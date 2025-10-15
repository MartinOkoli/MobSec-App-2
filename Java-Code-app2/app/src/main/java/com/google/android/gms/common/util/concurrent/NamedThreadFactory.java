package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\util\concurrent\NamedThreadFactory.smali */
public class NamedThreadFactory implements ThreadFactory {
    private final String name;
    private final int priority;
    private final ThreadFactory zzhr;

    public NamedThreadFactory(String str) {
        this(str, 0);
    }

    private NamedThreadFactory(String str, int i) {
        this.zzhr = Executors.defaultThreadFactory();
        this.name = (String) Preconditions.checkNotNull(str, "Name must not be null");
        this.priority = 0;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread threadNewThread = this.zzhr.newThread(new zza(runnable, 0));
        threadNewThread.setName(this.name);
        return threadNewThread;
    }
}
