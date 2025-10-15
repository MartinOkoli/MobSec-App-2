package androidx.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\ViewGroupOverlayApi18.smali */
class ViewGroupOverlayApi18 implements ViewGroupOverlayImpl {
    private final ViewGroupOverlay mViewGroupOverlay;

    ViewGroupOverlayApi18(ViewGroup group) {
        this.mViewGroupOverlay = group.getOverlay();
    }

    @Override // androidx.transition.ViewOverlayImpl
    public void add(Drawable drawable) {
        this.mViewGroupOverlay.add(drawable);
    }

    @Override // androidx.transition.ViewOverlayImpl
    public void remove(Drawable drawable) {
        this.mViewGroupOverlay.remove(drawable);
    }

    @Override // androidx.transition.ViewGroupOverlayImpl
    public void add(View view) {
        this.mViewGroupOverlay.add(view);
    }

    @Override // androidx.transition.ViewGroupOverlayImpl
    public void remove(View view) {
        this.mViewGroupOverlay.remove(view);
    }
}
