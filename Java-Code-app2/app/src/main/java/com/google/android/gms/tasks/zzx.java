package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzx.smali */
final class zzx implements Continuation<Void, Task<List<Task<?>>>> {
    private final /* synthetic */ Collection zzae;

    zzx(Collection collection) {
        this.zzae = collection;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<List<Task<?>>> then(Task<Void> task) throws Exception {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.zzae);
        return Tasks.forResult(arrayList);
    }
}
