package com.google.firebase.storage.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\internal\SleeperImpl.smali */
public class SleeperImpl implements Sleeper {
    @Override // com.google.firebase.storage.internal.Sleeper
    public void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
