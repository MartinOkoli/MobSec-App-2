package androidx.lifecycle;

import androidx.lifecycle.ClassesInfoCache;
import androidx.lifecycle.Lifecycle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\ReflectiveGenericLifecycleObserver.smali */
class ReflectiveGenericLifecycleObserver implements LifecycleEventObserver {
    private final ClassesInfoCache.CallbackInfo mInfo;
    private final Object mWrapped;

    ReflectiveGenericLifecycleObserver(Object wrapped) {
        this.mWrapped = wrapped;
        this.mInfo = ClassesInfoCache.sInstance.getInfo(wrapped.getClass());
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) throws IllegalArgumentException {
        this.mInfo.invokeCallbacks(source, event, this.mWrapped);
    }
}
