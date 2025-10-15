package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTask$$Lambda$13.smali */
final /* synthetic */ class StorageTask$$Lambda$13 implements OnSuccessListener {
    private final TaskCompletionSource arg$1;

    private StorageTask$$Lambda$13(TaskCompletionSource taskCompletionSource) {
        this.arg$1 = taskCompletionSource;
    }

    public static OnSuccessListener lambdaFactory$(TaskCompletionSource taskCompletionSource) {
        return new StorageTask$$Lambda$13(taskCompletionSource);
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public void onSuccess(Object obj) {
        this.arg$1.setResult(obj);
    }
}
