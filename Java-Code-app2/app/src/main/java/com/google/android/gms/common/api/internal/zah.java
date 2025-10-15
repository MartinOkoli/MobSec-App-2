package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zah.smali */
public final class zah extends zad<Boolean> {
    private final ListenerHolder.ListenerKey<?> zact;

    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zact = listenerKey;
    }

    public final void zad(GoogleApiManager.zaa<?> zaaVar) throws RemoteException {
        zabw zabwVarRemove = zaaVar.zabk().remove(this.zact);
        if (zabwVarRemove != null) {
            zabwVarRemove.zajy.unregisterListener(zaaVar.zaab(), this.zacn);
            zabwVarRemove.zajx.clearListener();
        } else {
            this.zacn.trySetResult(false);
        }
    }

    public final Feature[] zab(GoogleApiManager.zaa<?> zaaVar) {
        zabw zabwVar = zaaVar.zabk().get(this.zact);
        if (zabwVar == null) {
            return null;
        }
        return zabwVar.zajx.getRequiredFeatures();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaaVar) {
        zabw zabwVar = zaaVar.zabk().get(this.zact);
        return zabwVar != null && zabwVar.zajx.shouldAutoResolveMissingFeatures();
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
