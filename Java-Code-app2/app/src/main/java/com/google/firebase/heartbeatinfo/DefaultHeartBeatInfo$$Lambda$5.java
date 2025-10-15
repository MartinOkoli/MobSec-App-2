package com.google.firebase.heartbeatinfo;

import java.util.concurrent.ThreadFactory;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\heartbeatinfo\DefaultHeartBeatInfo$$Lambda$5.smali */
final /* synthetic */ class DefaultHeartBeatInfo$$Lambda$5 implements ThreadFactory {
    private static final DefaultHeartBeatInfo$$Lambda$5 instance = new DefaultHeartBeatInfo$$Lambda$5();

    private DefaultHeartBeatInfo$$Lambda$5() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return DefaultHeartBeatInfo.lambda$static$0(runnable);
    }
}
