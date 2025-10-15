package androidx.arch.core.executor;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\arch\core\executor\TaskExecutor.smali */
public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(Runnable runnable);

    public abstract boolean isMainThread();

    public abstract void postToMainThread(Runnable runnable);

    public void executeOnMainThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            postToMainThread(runnable);
        }
    }
}
