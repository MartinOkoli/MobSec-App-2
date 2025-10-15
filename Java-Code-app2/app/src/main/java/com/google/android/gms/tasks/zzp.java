package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzp.smali */
final class zzp implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzo zzs;

    zzp(zzo zzoVar, Task task) {
        this.zzs = zzoVar;
        this.zzg = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task taskThen = this.zzs.zzr.then(this.zzg.getResult());
            if (taskThen == null) {
                this.zzs.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            taskThen.addOnSuccessListener(TaskExecutors.zzw, this.zzs);
            taskThen.addOnFailureListener(TaskExecutors.zzw, this.zzs);
            taskThen.addOnCanceledListener(TaskExecutors.zzw, this.zzs);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzs.onFailure((Exception) e.getCause());
            } else {
                this.zzs.onFailure(e);
            }
        } catch (CancellationException e2) {
            this.zzs.onCanceled();
        } catch (Exception e3) {
            this.zzs.onFailure(e3);
        }
    }
}
