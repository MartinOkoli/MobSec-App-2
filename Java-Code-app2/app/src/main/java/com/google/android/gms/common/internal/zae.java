package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zae.smali */
final class zae extends DialogRedirect {
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;
    private final /* synthetic */ LifecycleFragment zaoi;

    zae(Intent intent, LifecycleFragment lifecycleFragment, int i) {
        this.zaoh = intent;
        this.zaoi = lifecycleFragment;
        this.val$requestCode = i;
    }

    public final void redirect() {
        Intent intent = this.zaoh;
        if (intent != null) {
            this.zaoi.startActivityForResult(intent, this.val$requestCode);
        }
    }
}
