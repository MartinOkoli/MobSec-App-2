package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClientEventManager;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaax.smali */
final class zaax implements GmsClientEventManager.GmsClientEventState {
    private final /* synthetic */ zaaw zahh;

    zaax(zaaw zaawVar) {
        this.zahh = zaawVar;
    }

    @Override // com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final boolean isConnected() {
        return this.zahh.isConnected();
    }

    @Override // com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final Bundle getConnectionHint() {
        return null;
    }
}
