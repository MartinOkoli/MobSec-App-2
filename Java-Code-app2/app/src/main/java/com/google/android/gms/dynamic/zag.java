package com.google.android.gms.dynamic;

import com.google.android.gms.dynamic.DeferredLifecycleHelper;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\dynamic\zag.smali */
final class zag implements DeferredLifecycleHelper.zaa {
    private final /* synthetic */ DeferredLifecycleHelper zarj;

    zag(DeferredLifecycleHelper deferredLifecycleHelper) {
        this.zarj = deferredLifecycleHelper;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final int getState() {
        return 5;
    }

    @Override // com.google.android.gms.dynamic.DeferredLifecycleHelper.zaa
    public final void zaa(LifecycleDelegate lifecycleDelegate) {
        this.zarj.zarf.onResume();
    }
}
