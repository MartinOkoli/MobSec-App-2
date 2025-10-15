package androidx.lifecycle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\FullLifecycleObserver.smali */
interface FullLifecycleObserver extends LifecycleObserver {
    void onCreate(LifecycleOwner lifecycleOwner);

    void onDestroy(LifecycleOwner lifecycleOwner);

    void onPause(LifecycleOwner lifecycleOwner);

    void onResume(LifecycleOwner lifecycleOwner);

    void onStart(LifecycleOwner lifecycleOwner);

    void onStop(LifecycleOwner lifecycleOwner);
}
