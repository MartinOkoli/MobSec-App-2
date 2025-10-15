package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaal.smali */
final class zaal implements Runnable {
    private final /* synthetic */ zaak zagj;

    zaal(zaak zaakVar) {
        this.zagj = zaakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zagj.zaey.cancelAvailabilityErrorNotifications(this.zagj.mContext);
    }
}
