package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zacq.smali */
final class zacq implements zacs {
    private final /* synthetic */ zacp zalb;

    zacq(zacp zacpVar) {
        this.zalb = zacpVar;
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    public final void zac(BasePendingResult<?> basePendingResult) {
        this.zalb.zakz.remove(basePendingResult);
    }
}
