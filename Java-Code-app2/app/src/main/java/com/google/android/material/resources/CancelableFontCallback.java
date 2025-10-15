package com.google.android.material.resources;

import android.graphics.Typeface;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\resources\CancelableFontCallback.smali */
public final class CancelableFontCallback extends TextAppearanceFontCallback {
    private final ApplyFont applyFont;
    private boolean cancelled;
    private final Typeface fallbackFont;

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\resources\CancelableFontCallback$ApplyFont.smali */
    public interface ApplyFont {
        void apply(Typeface typeface);
    }

    public CancelableFontCallback(ApplyFont applyFont, Typeface fallbackFont) {
        this.fallbackFont = fallbackFont;
        this.applyFont = applyFont;
    }

    public void onFontRetrieved(Typeface font, boolean fontResolvedSynchronously) {
        updateIfNotCancelled(font);
    }

    public void onFontRetrievalFailed(int reason) {
        updateIfNotCancelled(this.fallbackFont);
    }

    public void cancel() {
        this.cancelled = true;
    }

    private void updateIfNotCancelled(Typeface updatedFont) {
        if (!this.cancelled) {
            this.applyFont.apply(updatedFont);
        }
    }
}
