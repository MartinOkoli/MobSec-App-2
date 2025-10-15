package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\internal\ViewOverlayApi18.smali */
class ViewOverlayApi18 implements ViewOverlayImpl {
    private final ViewOverlay viewOverlay;

    ViewOverlayApi18(View view) {
        this.viewOverlay = view.getOverlay();
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void add(Drawable drawable) {
        this.viewOverlay.add(drawable);
    }

    @Override // com.google.android.material.internal.ViewOverlayImpl
    public void remove(Drawable drawable) {
        this.viewOverlay.remove(drawable);
    }
}
