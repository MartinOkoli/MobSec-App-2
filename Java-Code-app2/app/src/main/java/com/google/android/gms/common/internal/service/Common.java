package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Api;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\service\Common.smali */
public final class Common {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.ClientKey<zai> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zaph;
    public static final zac zapi;

    static {
        Api.ClientKey<zai> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zab zabVar = new zab();
        zaph = zabVar;
        API = new Api<>("Common.API", zabVar, clientKey);
        zapi = new zad();
    }
}
