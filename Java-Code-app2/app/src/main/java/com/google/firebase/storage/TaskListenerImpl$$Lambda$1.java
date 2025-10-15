package com.google.firebase.storage;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\TaskListenerImpl$$Lambda$1.smali */
final /* synthetic */ class TaskListenerImpl$$Lambda$1 implements Runnable {
    private final TaskListenerImpl arg$1;
    private final Object arg$2;

    private TaskListenerImpl$$Lambda$1(TaskListenerImpl taskListenerImpl, Object obj) {
        this.arg$1 = taskListenerImpl;
        this.arg$2 = obj;
    }

    public static Runnable lambdaFactory$(TaskListenerImpl taskListenerImpl, Object obj) {
        return new TaskListenerImpl$$Lambda$1(taskListenerImpl, obj);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.arg$1.removeListener(this.arg$2);
    }
}
