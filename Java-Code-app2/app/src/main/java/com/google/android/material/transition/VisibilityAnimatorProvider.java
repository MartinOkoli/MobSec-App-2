package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\transition\VisibilityAnimatorProvider.smali */
public interface VisibilityAnimatorProvider {
    Animator createAppear(ViewGroup viewGroup, View view);

    Animator createDisappear(ViewGroup viewGroup, View view);
}
