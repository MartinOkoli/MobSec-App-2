package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: Add missing generic type declarations: [TResult] */
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaad.smali */
final class zaad<TResult> implements OnCompleteListener<TResult> {
    private final /* synthetic */ zaab zafn;
    private final /* synthetic */ TaskCompletionSource zafo;

    zaad(zaab zaabVar, TaskCompletionSource taskCompletionSource) {
        this.zafn = zaabVar;
        this.zafo = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<TResult> task) {
        this.zafn.zafl.remove(this.zafo);
    }
}
