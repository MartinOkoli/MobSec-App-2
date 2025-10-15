package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\dynamic\zae.smali */
final class zae implements View.OnClickListener {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Intent zaro;

    zae(Context context, Intent intent) {
        this.val$context = context;
        this.zaro = intent;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            this.val$context.startActivity(this.zaro);
        } catch (ActivityNotFoundException e) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
        }
    }
}
