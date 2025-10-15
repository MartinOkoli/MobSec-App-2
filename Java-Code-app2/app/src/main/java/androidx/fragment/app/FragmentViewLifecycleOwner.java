package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\fragment\app\FragmentViewLifecycleOwner.smali */
class FragmentViewLifecycleOwner implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry = null;

    FragmentViewLifecycleOwner() {
    }

    void initialize() {
        if (this.mLifecycleRegistry == null) {
            this.mLifecycleRegistry = new LifecycleRegistry(this);
        }
    }

    boolean isInitialized() {
        return this.mLifecycleRegistry != null;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        initialize();
        return this.mLifecycleRegistry;
    }

    void handleLifecycleEvent(Lifecycle.Event event) {
        this.mLifecycleRegistry.handleLifecycleEvent(event);
    }
}
