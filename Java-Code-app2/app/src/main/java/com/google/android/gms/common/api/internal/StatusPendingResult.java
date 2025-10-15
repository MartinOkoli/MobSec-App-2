package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\StatusPendingResult.smali */
public class StatusPendingResult extends BasePendingResult<Status> {
    public StatusPendingResult(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Deprecated
    public StatusPendingResult(Looper looper) {
        super(looper);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }
}
