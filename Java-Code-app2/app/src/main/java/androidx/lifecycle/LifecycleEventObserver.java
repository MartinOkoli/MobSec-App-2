package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\LifecycleEventObserver.smali */
public interface LifecycleEventObserver extends LifecycleObserver {
    void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event);
}
