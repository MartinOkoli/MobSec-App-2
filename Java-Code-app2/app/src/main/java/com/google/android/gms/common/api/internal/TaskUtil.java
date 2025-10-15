package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\TaskUtil.smali */
public class TaskUtil {
    public static void setResultOrApiException(Status status, TaskCompletionSource<Void> taskCompletionSource) {
        setResultOrApiException(status, null, taskCompletionSource);
    }

    public static <TResult> void setResultOrApiException(Status status, TResult tresult, TaskCompletionSource<TResult> taskCompletionSource) {
        if (status.isSuccess()) {
            taskCompletionSource.setResult(tresult);
        } else {
            taskCompletionSource.setException(new ApiException(status));
        }
    }

    @Deprecated
    public static Task<Void> toVoidTaskThatFailsOnFalse(Task<Boolean> task) {
        return task.continueWith(new zacl());
    }
}
