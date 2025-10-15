package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zag.smali */
final class zag implements BaseGmsClient.BaseOnConnectionFailedListener {
    private final /* synthetic */ GoogleApiClient.OnConnectionFailedListener zaok;

    zag(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zaok = onConnectionFailedListener;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zaok.onConnectionFailed(connectionResult);
    }
}
