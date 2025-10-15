package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\PendingResultUtil.smali */
public class PendingResultUtil {
    private static final zaa zaou = new zai();

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\PendingResultUtil$ResultConverter.smali */
    public interface ResultConverter<R extends Result, T> {
        T convert(R r);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\PendingResultUtil$zaa.smali */
    public interface zaa {
        ApiException zaf(Status status);
    }

    public static <R extends Result, T> Task<T> toTask(PendingResult<R> pendingResult, ResultConverter<R, T> resultConverter) {
        zaa zaaVar = zaou;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.addStatusListener(new zaj(pendingResult, taskCompletionSource, resultConverter, zaaVar));
        return taskCompletionSource.getTask();
    }

    public static <R extends Result, T extends Response<R>> Task<T> toResponseTask(PendingResult<R> pendingResult, T t) {
        return toTask(pendingResult, new zak(t));
    }

    public static <R extends Result> Task<Void> toVoidTask(PendingResult<R> pendingResult) {
        return toTask(pendingResult, new zal());
    }
}
