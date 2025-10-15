package com.google.android.gms.common.api.internal;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\zabf.smali */
abstract class zabf {
    private final zabd zahu;

    protected zabf(zabd zabdVar) {
        this.zahu = zabdVar;
    }

    protected abstract void zaan();

    public final void zac(zabe zabeVar) {
        zabeVar.zaeo.lock();
        try {
            if (zabeVar.zahq != this.zahu) {
                return;
            }
            zaan();
        } finally {
            zabeVar.zaeo.unlock();
        }
    }
}
