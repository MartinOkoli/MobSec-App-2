package com.google.android.gms.common.api;

import android.util.Log;
import com.google.android.gms.common.api.Result;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\ResultCallbacks.smali */
public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R> {
    public abstract void onFailure(Status status);

    public abstract void onSuccess(R r);

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(R r) {
        Status status = r.getStatus();
        if (status.isSuccess()) {
            onSuccess(r);
            return;
        }
        onFailure(status);
        if (r instanceof Releasable) {
            try {
                ((Releasable) r).release();
            } catch (RuntimeException e) {
                String strValueOf = String.valueOf(r);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(strValueOf);
                Log.w("ResultCallbacks", sb.toString(), e);
            }
        }
    }
}
