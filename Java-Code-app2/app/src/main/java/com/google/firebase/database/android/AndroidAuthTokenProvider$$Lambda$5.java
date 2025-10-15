package com.google.firebase.database.android;

import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import java.util.concurrent.ExecutorService;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider$$Lambda$5.smali */
final /* synthetic */ class AndroidAuthTokenProvider$$Lambda$5 implements IdTokenListener {
    private final ExecutorService arg$1;
    private final AuthTokenProvider.TokenChangeListener arg$2;

    private AndroidAuthTokenProvider$$Lambda$5(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        this.arg$1 = executorService;
        this.arg$2 = tokenChangeListener;
    }

    public static IdTokenListener lambdaFactory$(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenChangeListener) {
        return new AndroidAuthTokenProvider$$Lambda$5(executorService, tokenChangeListener);
    }

    @Override // com.google.firebase.auth.internal.IdTokenListener
    public void onIdTokenChanged(InternalTokenResult internalTokenResult) {
        AndroidAuthTokenProvider.lambda$addTokenChangeListener$4(this.arg$1, this.arg$2, internalTokenResult);
    }
}
