package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabi.smali */
final class zabi implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ GoogleApiManager zaim;

    zabi(GoogleApiManager googleApiManager) {
        this.zaim = googleApiManager;
    }

    @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
    public final void onBackgroundStateChanged(boolean z) {
        this.zaim.handler.sendMessage(this.zaim.handler.obtainMessage(1, Boolean.valueOf(z)));
    }
}
