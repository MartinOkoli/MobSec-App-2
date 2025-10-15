package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zad.smali */
final class zad extends DialogRedirect {
    private final /* synthetic */ Fragment val$fragment;
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;

    zad(Intent intent, Fragment fragment, int i) {
        this.zaoh = intent;
        this.val$fragment = fragment;
        this.val$requestCode = i;
    }

    public final void redirect() {
        Intent intent = this.zaoh;
        if (intent != null) {
            this.val$fragment.startActivityForResult(intent, this.val$requestCode);
        }
    }
}
