package com.google.android.material.textfield;

import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\textfield\NoEndIconDelegate.smali */
class NoEndIconDelegate extends EndIconDelegate {
    NoEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void initialize() {
        this.textInputLayout.setEndIconOnClickListener(null);
        this.textInputLayout.setEndIconDrawable((Drawable) null);
        this.textInputLayout.setEndIconContentDescription((CharSequence) null);
    }
}
