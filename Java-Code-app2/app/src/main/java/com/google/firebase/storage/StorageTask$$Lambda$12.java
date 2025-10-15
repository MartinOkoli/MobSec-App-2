package com.google.firebase.storage;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTask$$Lambda$12.smali */
final /* synthetic */ class StorageTask$$Lambda$12 implements Runnable {
    private final StorageTask arg$1;

    private StorageTask$$Lambda$12(StorageTask storageTask) {
        this.arg$1 = storageTask;
    }

    public static Runnable lambdaFactory$(StorageTask storageTask) {
        return new StorageTask$$Lambda$12(storageTask);
    }

    @Override // java.lang.Runnable
    public void run() {
        StorageTask.lambda$getRunnable$7(this.arg$1);
    }
}
