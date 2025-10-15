package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\OptionalPendingResult.smali */
public abstract class OptionalPendingResult<R extends Result> extends PendingResult<R> {
    public abstract R get();

    public abstract boolean isDone();
}
