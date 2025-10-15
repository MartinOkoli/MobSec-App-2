package com.google.firebase.database.android;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider$$Lambda$1.smali */
final /* synthetic */ class AndroidAuthTokenProvider$$Lambda$1 implements Deferred.DeferredHandler {
    private final AndroidAuthTokenProvider arg$1;

    private AndroidAuthTokenProvider$$Lambda$1(AndroidAuthTokenProvider androidAuthTokenProvider) {
        this.arg$1 = androidAuthTokenProvider;
    }

    public static Deferred.DeferredHandler lambdaFactory$(AndroidAuthTokenProvider androidAuthTokenProvider) {
        return new AndroidAuthTokenProvider$$Lambda$1(androidAuthTokenProvider);
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public void handle(Provider provider) {
        AndroidAuthTokenProvider.lambda$new$0(this.arg$1, provider);
    }
}
