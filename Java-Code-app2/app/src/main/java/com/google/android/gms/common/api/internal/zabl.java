package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabl.smali */
final class zabl implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaiy;
    private final /* synthetic */ ConnectionResult zaiz;

    zabl(GoogleApiManager.zaa zaaVar, ConnectionResult connectionResult) {
        this.zaiy = zaaVar;
        this.zaiz = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaiy.onConnectionFailed(this.zaiz);
    }
}
