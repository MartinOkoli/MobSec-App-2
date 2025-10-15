package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\textfield\EndIconDelegate.smali */
abstract class EndIconDelegate {
    Context context;
    CheckableImageButton endIconView;
    TextInputLayout textInputLayout;

    abstract void initialize();

    EndIconDelegate(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.context = textInputLayout.getContext();
        this.endIconView = textInputLayout.getEndIconView();
    }

    boolean shouldTintIconOnError() {
        return false;
    }

    boolean isBoxBackgroundModeSupported(int boxBackgroundMode) {
        return true;
    }

    void onSuffixVisibilityChanged(boolean visible) {
    }
}
