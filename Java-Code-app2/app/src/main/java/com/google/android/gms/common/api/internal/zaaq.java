package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import java.util.ArrayList;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaaq.smali */
final class zaaq extends zaau {
    private final /* synthetic */ zaak zagj;
    private final ArrayList<Api.Client> zagp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaq(zaak zaakVar, ArrayList<Api.Client> arrayList) {
        super(zaakVar, null);
        this.zagj = zaakVar;
        this.zagp = arrayList;
    }

    @Override // com.google.android.gms.common.api.internal.zaau
    public final void zaan() {
        this.zagj.zaft.zaee.zaha = this.zagj.zaat();
        ArrayList<Api.Client> arrayList = this.zagp;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Api.Client client = arrayList.get(i);
            i++;
            client.getRemoteService(this.zagj.zagf, this.zagj.zaft.zaee.zaha);
        }
    }
}
