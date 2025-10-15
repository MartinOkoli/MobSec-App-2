package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\Lifecycle.smali */
public abstract class Lifecycle {
    AtomicReference<Object> mInternalScopeRef = new AtomicReference<>();

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\Lifecycle$Event.smali */
    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY
    }

    public abstract void addObserver(LifecycleObserver lifecycleObserver);

    public abstract State getCurrentState();

    public abstract void removeObserver(LifecycleObserver lifecycleObserver);

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\Lifecycle$State.smali */
    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public boolean isAtLeast(State state) {
            return compareTo(state) >= 0;
        }
    }
}
