package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTask$$Lambda$14.smali */
final /* synthetic */ class StorageTask$$Lambda$14 implements OnFailureListener {
    private final TaskCompletionSource arg$1;

    private StorageTask$$Lambda$14(TaskCompletionSource taskCompletionSource) {
        this.arg$1 = taskCompletionSource;
    }

    public static OnFailureListener lambdaFactory$(TaskCompletionSource taskCompletionSource) {
        return new StorageTask$$Lambda$14(taskCompletionSource);
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public void onFailure(Exception exc) {
        this.arg$1.setException(exc);
    }
}
