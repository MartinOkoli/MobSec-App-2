package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import java.util.concurrent.TimeUnit;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\PendingResult.smali */
public abstract class PendingResult<R extends Result> {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\PendingResult$StatusListener.smali */
    public interface StatusListener {
        void onComplete(Status status);
    }

    public abstract R await();

    public abstract R await(long j, TimeUnit timeUnit);

    public abstract void cancel();

    public abstract boolean isCanceled();

    public abstract void setResultCallback(ResultCallback<? super R> resultCallback);

    public abstract void setResultCallback(ResultCallback<? super R> resultCallback, long j, TimeUnit timeUnit);

    public void addStatusListener(StatusListener statusListener) {
        throw new UnsupportedOperationException();
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        throw new UnsupportedOperationException();
    }

    public Integer zam() {
        throw new UnsupportedOperationException();
    }
}
