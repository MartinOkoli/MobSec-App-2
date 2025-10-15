package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaf.smali */
public final class zaf extends zad<Void> {
    private final RegisterListenerMethod<Api.AnyClient, ?> zacp;
    private final UnregisterListenerMethod<Api.AnyClient, ?> zacq;

    public zaf(zabw zabwVar, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zacp = zabwVar.zajx;
        this.zacq = zabwVar.zajy;
    }

    public final void zad(GoogleApiManager.zaa<?> zaaVar) throws RemoteException {
        this.zacp.registerListener(zaaVar.zaab(), this.zacn);
        if (this.zacp.getListenerKey() != null) {
            zaaVar.zabk().put(this.zacp.getListenerKey(), new zabw(this.zacp, this.zacq));
        }
    }

    public final Feature[] zab(GoogleApiManager.zaa<?> zaaVar) {
        return this.zacp.getRequiredFeatures();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaaVar) {
        return this.zacp.shouldAutoResolveMissingFeatures();
    }

    public final /* bridge */ /* synthetic */ void zaa(zaab zaabVar, boolean z) {
    }

    public final /* bridge */ /* synthetic */ void zaa(RuntimeException runtimeException) {
        super.zaa(runtimeException);
    }

    public final /* bridge */ /* synthetic */ void zaa(Status status) {
        super.zaa(status);
    }
}
