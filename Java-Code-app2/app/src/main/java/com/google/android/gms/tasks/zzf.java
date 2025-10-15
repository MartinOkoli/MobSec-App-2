package com.google.android.gms.tasks;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\tasks\zzf.smali */
final class zzf implements Runnable {
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zze zzi;

    zzf(zze zzeVar, Task task) {
        this.zzi = zzeVar;
        this.zzg = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task task = (Task) this.zzi.zze.then(this.zzg);
            if (task == null) {
                this.zzi.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            task.addOnSuccessListener(TaskExecutors.zzw, this.zzi);
            task.addOnFailureListener(TaskExecutors.zzw, this.zzi);
            task.addOnCanceledListener(TaskExecutors.zzw, this.zzi);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzi.zzf.setException((Exception) e.getCause());
            } else {
                this.zzi.zzf.setException(e);
            }
        } catch (Exception e2) {
            this.zzi.zzf.setException(e2);
        }
    }
}
