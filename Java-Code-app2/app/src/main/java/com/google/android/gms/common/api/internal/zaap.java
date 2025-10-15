package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaap.smali */
final class zaap extends zabf {
    private final /* synthetic */ BaseGmsClient.ConnectionProgressReportCallbacks zago;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zaap(zaan zaanVar, zabd zabdVar, BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabdVar);
        this.zago = connectionProgressReportCallbacks;
    }

    @Override // com.google.android.gms.common.api.internal.zabf
    public final void zaan() {
        this.zago.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
