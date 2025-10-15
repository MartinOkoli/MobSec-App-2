package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\service\zad.smali */
public final class zad implements zac {
    @Override // com.google.android.gms.common.internal.service.zac
    public final PendingResult<Status> zaa(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zae(this, googleApiClient));
    }
}
