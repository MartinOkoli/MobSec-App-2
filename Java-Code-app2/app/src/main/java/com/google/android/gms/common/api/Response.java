package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\Response.smali */
public class Response<T extends Result> {
    private T zzap;

    public Response() {
    }

    protected Response(T t) {
        this.zzap = t;
    }

    protected T getResult() {
        return this.zzap;
    }

    public void setResult(T t) {
        this.zzap = t;
    }
}
