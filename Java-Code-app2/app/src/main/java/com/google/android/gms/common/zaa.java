package com.google.android.gms.common;

import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\zaa.smali */
final class zaa implements Continuation<Map<zai<?>, String>, Void> {
    zaa(GoogleApiAvailability googleApiAvailability) {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Void then(Task<Map<zai<?>, String>> task) throws Exception {
        task.getResult();
        return null;
    }
}
