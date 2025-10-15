package com.google.android.material.transition;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\transition\FadeModeResult.smali */
class FadeModeResult {
    final int endAlpha;
    final boolean endOnTop;
    final int startAlpha;

    static FadeModeResult startOnTop(int startAlpha, int endAlpha) {
        return new FadeModeResult(startAlpha, endAlpha, false);
    }

    static FadeModeResult endOnTop(int startAlpha, int endAlpha) {
        return new FadeModeResult(startAlpha, endAlpha, true);
    }

    private FadeModeResult(int startAlpha, int endAlpha, boolean endOnTop) {
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
        this.endOnTop = endOnTop;
    }
}
