package com.google.android.material.animation;

import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\animation\TransformationCallback.smali */
public interface TransformationCallback<T extends View> {
    void onScaleChanged(T t);

    void onTranslationChanged(T t);
}
