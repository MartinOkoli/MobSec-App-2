package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaaf.smali */
final class zaaf {
    private final zai<?> zafq;
    private final TaskCompletionSource<Boolean> zafr = new TaskCompletionSource<>();

    public zaaf(zai<?> zaiVar) {
        this.zafq = zaiVar;
    }

    public final zai<?> zak() {
        return this.zafq;
    }

    public final TaskCompletionSource<Boolean> zaal() {
        return this.zafr;
    }
}
