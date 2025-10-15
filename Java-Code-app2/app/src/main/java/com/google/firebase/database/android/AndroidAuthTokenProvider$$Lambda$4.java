package com.google.firebase.database.android;

import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.concurrent.ExecutorService;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider$$Lambda$4.smali */
final /* synthetic */ class AndroidAuthTokenProvider$$Lambda$4 implements Deferred.DeferredHandler {
    private final ExecutorService arg$1;
    private final AuthTokenProvider.TokenChangeListener arg$2;

    private AndroidAuthTokenProvider$$Lambda$4(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        this.arg$1 = executorService;
        this.arg$2 = tokenChangeListener;
    }

    public static Deferred.DeferredHandler lambdaFactory$(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        return new AndroidAuthTokenProvider$$Lambda$4(executorService, tokenChangeListener);
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public void handle(Provider provider) {
        AndroidAuthTokenProvider.lambda$addTokenChangeListener$5(this.arg$1, this.arg$2, provider);
    }
}
