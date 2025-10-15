package com.google.android.gms.common.api.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabq.smali */
public final class zabq extends BroadcastReceiver {
    private Context mContext;
    private final zabr zaji;

    public zabq(zabr zabrVar) {
        this.zaji = zabrVar;
    }

    public final void zac(Context context) {
        this.mContext = context;
    }

    public final synchronized void unregister() {
        Context context = this.mContext;
        if (context != null) {
            context.unregisterReceiver(this);
        }
        this.mContext = null;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String schemeSpecificPart;
        Uri data = intent.getData();
        if (data == null) {
            schemeSpecificPart = null;
        } else {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(schemeSpecificPart)) {
            this.zaji.zas();
            unregister();
        }
    }
}
