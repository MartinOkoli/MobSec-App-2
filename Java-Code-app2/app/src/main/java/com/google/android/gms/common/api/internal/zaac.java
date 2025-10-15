package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaac.smali */
final class zaac implements PendingResult.StatusListener {
    private final /* synthetic */ BasePendingResult zafm;
    private final /* synthetic */ zaab zafn;

    zaac(zaab zaabVar, BasePendingResult basePendingResult) {
        this.zafn = zaabVar;
        this.zafm = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        this.zafn.zafk.remove(this.zafm);
    }
}
