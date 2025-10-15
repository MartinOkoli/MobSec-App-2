package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zacd;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\ResultTransform.smali */
public abstract class ResultTransform<R extends Result, S extends Result> {
    public abstract PendingResult<S> onSuccess(R r);

    public Status onFailure(Status status) {
        return status;
    }

    public final PendingResult<S> createFailedResult(Status status) {
        return new zacd(status);
    }
}
