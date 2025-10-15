package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import java.util.Collections;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabo.smali */
final class zabo implements Runnable {
    private final /* synthetic */ ConnectionResult zaiz;
    private final /* synthetic */ GoogleApiManager.zac zajg;

    zabo(GoogleApiManager.zac zacVar, ConnectionResult connectionResult) {
        this.zajg = zacVar;
        this.zaiz = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.zaiz.isSuccess()) {
            ((GoogleApiManager.zaa) GoogleApiManager.this.zaii.get(this.zajg.zafq)).onConnectionFailed(this.zaiz);
            return;
        }
        GoogleApiManager.zac.zaa(this.zajg, true);
        if (this.zajg.zaio.requiresSignIn()) {
            this.zajg.zabr();
            return;
        }
        try {
            this.zajg.zaio.getRemoteService(null, Collections.emptySet());
        } catch (SecurityException e) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
            ((GoogleApiManager.zaa) GoogleApiManager.this.zaii.get(this.zajg.zafq)).onConnectionFailed(new ConnectionResult(10));
        }
    }
}
