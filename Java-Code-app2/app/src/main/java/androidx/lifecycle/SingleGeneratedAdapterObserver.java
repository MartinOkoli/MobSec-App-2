package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\SingleGeneratedAdapterObserver.smali */
class SingleGeneratedAdapterObserver implements LifecycleEventObserver {
    private final GeneratedAdapter mGeneratedAdapter;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        this.mGeneratedAdapter = generatedAdapter;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        this.mGeneratedAdapter.callMethods(source, event, false, null);
        this.mGeneratedAdapter.callMethods(source, event, true, null);
    }
}
