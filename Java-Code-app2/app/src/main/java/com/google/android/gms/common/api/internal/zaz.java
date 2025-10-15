package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaz.smali */
final class zaz implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafi;

    private zaz(zax zaxVar) {
        this.zafi = zaxVar;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Map<zai<?>, String>> task) {
        this.zafi.zaeo.lock();
        try {
            if (this.zafi.zafd) {
                if (task.isSuccessful()) {
                    zax zaxVar = this.zafi;
                    zaxVar.zafe = new ArrayMap(zaxVar.zaeu.size());
                    Iterator it = this.zafi.zaeu.values().iterator();
                    while (it.hasNext()) {
                        this.zafi.zafe.put(((zaw) it.next()).zak(), ConnectionResult.RESULT_SUCCESS);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zafi.zafb) {
                        zax zaxVar2 = this.zafi;
                        zaxVar2.zafe = new ArrayMap(zaxVar2.zaeu.size());
                        for (zaw zawVar : this.zafi.zaeu.values()) {
                            Object objZak = zawVar.zak();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(zawVar);
                            if (this.zafi.zaa((zaw<?>) zawVar, connectionResult)) {
                                this.zafi.zafe.put(objZak, new ConnectionResult(16));
                            } else {
                                this.zafi.zafe.put(objZak, connectionResult);
                            }
                        }
                    } else {
                        this.zafi.zafe = availabilityException.zaj();
                    }
                    zax zaxVar3 = this.zafi;
                    zaxVar3.zafh = zaxVar3.zaaf();
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zafi.zafe = Collections.emptyMap();
                    this.zafi.zafh = new ConnectionResult(8);
                }
                if (this.zafi.zaff != null) {
                    this.zafi.zafe.putAll(this.zafi.zaff);
                    zax zaxVar4 = this.zafi;
                    zaxVar4.zafh = zaxVar4.zaaf();
                }
                if (this.zafi.zafh == null) {
                    this.zafi.zaad();
                    this.zafi.zaae();
                } else {
                    zax.zaa(this.zafi, false);
                    this.zafi.zaex.zac(this.zafi.zafh);
                }
                this.zafi.zaez.signalAll();
            }
        } finally {
            this.zafi.zaeo.unlock();
        }
    }
}
