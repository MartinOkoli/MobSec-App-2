package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\TaskListenerImpl$$Lambda$3.smali */
final /* synthetic */ class TaskListenerImpl$$Lambda$3 implements Runnable {
    private final TaskListenerImpl arg$1;
    private final Object arg$2;
    private final StorageTask.ProvideError arg$3;

    private TaskListenerImpl$$Lambda$3(TaskListenerImpl taskListenerImpl, Object obj, StorageTask.ProvideError provideError) {
        this.arg$1 = taskListenerImpl;
        this.arg$2 = obj;
        this.arg$3 = provideError;
    }

    public static Runnable lambdaFactory$(TaskListenerImpl taskListenerImpl, Object obj, StorageTask.ProvideError provideError) {
        return new TaskListenerImpl$$Lambda$3(taskListenerImpl, obj, provideError);
    }

    @Override // java.lang.Runnable
    public void run() {
        TaskListenerImpl.lambda$onInternalStateChanged$2(this.arg$1, this.arg$2, this.arg$3);
    }
}
