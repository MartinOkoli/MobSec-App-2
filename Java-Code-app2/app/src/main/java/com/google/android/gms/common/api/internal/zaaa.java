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

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zaaa.smali */
final class zaaa implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafi;
    private SignInConnectionListener zafj;

    zaaa(zax zaxVar, SignInConnectionListener signInConnectionListener) {
        this.zafi = zaxVar;
        this.zafj = signInConnectionListener;
    }

    final void cancel() {
        this.zafj.onComplete();
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Map<zai<?>, String>> task) {
        this.zafi.zaeo.lock();
        try {
            if (!this.zafi.zafd) {
                this.zafj.onComplete();
                return;
            }
            if (task.isSuccessful()) {
                zax zaxVar = this.zafi;
                zaxVar.zaff = new ArrayMap(zaxVar.zaev.size());
                Iterator it = this.zafi.zaev.values().iterator();
                while (it.hasNext()) {
                    this.zafi.zaff.put(((zaw) it.next()).zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task.getException();
                if (this.zafi.zafb) {
                    zax zaxVar2 = this.zafi;
                    zaxVar2.zaff = new ArrayMap(zaxVar2.zaev.size());
                    for (zaw zawVar : this.zafi.zaev.values()) {
                        Object objZak = zawVar.zak();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zawVar);
                        if (this.zafi.zaa((zaw<?>) zawVar, connectionResult)) {
                            this.zafi.zaff.put(objZak, new ConnectionResult(16));
                        } else {
                            this.zafi.zaff.put(objZak, connectionResult);
                        }
                    }
                } else {
                    this.zafi.zaff = availabilityException.zaj();
                }
            } else {
                Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                this.zafi.zaff = Collections.emptyMap();
            }
            if (this.zafi.isConnected()) {
                this.zafi.zafe.putAll(this.zafi.zaff);
                if (this.zafi.zaaf() == null) {
                    this.zafi.zaad();
                    this.zafi.zaae();
                    this.zafi.zaez.signalAll();
                }
            }
            this.zafj.onComplete();
        } finally {
            this.zafi.zaeo.unlock();
        }
    }
}
