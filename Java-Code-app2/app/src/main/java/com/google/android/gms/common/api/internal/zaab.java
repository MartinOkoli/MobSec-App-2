package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaab.smali */
public final class zaab {
    private final Map<BasePendingResult<?>, Boolean> zafk = Collections.synchronizedMap(new WeakHashMap());
    private final Map<TaskCompletionSource<?>, Boolean> zafl = Collections.synchronizedMap(new WeakHashMap());

    final void zaa(BasePendingResult<? extends Result> basePendingResult, boolean z) {
        this.zafk.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.addStatusListener(new zaac(this, basePendingResult));
    }

    final <TResult> void zaa(TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zafl.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new zaad(this, taskCompletionSource));
    }

    final boolean zaag() {
        return (this.zafk.isEmpty() && this.zafl.isEmpty()) ? false : true;
    }

    public final void zaah() {
        zaa(false, GoogleApiManager.zahx);
    }

    public final void zaai() {
        zaa(true, zacp.zakx);
    }

    private final void zaa(boolean z, Status status) {
        HashMap map;
        HashMap map2;
        synchronized (this.zafk) {
            map = new HashMap(this.zafk);
        }
        synchronized (this.zafl) {
            map2 = new HashMap(this.zafl);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).zab(status);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }
}
