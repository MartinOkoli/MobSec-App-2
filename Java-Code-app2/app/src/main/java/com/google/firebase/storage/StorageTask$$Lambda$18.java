package com.google.firebase.storage;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\StorageTask$$Lambda$18.smali */
final /* synthetic */ class StorageTask$$Lambda$18 implements OnCanceledListener {
    private final CancellationTokenSource arg$1;

    private StorageTask$$Lambda$18(CancellationTokenSource cancellationTokenSource) {
        this.arg$1 = cancellationTokenSource;
    }

    public static OnCanceledListener lambdaFactory$(CancellationTokenSource cancellationTokenSource) {
        return new StorageTask$$Lambda$18(cancellationTokenSource);
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public void onCanceled() {
        this.arg$1.cancel();
    }
}
