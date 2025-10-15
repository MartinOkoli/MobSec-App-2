package com.google.firebase.database.android;

import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.internal.InternalTokenResult;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider$$Lambda$6.smali */
final /* synthetic */ class AndroidAuthTokenProvider$$Lambda$6 implements Runnable {
    private final AuthTokenProvider.TokenChangeListener arg$1;
    private final InternalTokenResult arg$2;

    private AndroidAuthTokenProvider$$Lambda$6(AuthTokenProvider.TokenChangeListener tokenChangeListener, InternalTokenResult internalTokenResult) {
        this.arg$1 = tokenChangeListener;
        this.arg$2 = internalTokenResult;
    }

    public static Runnable lambdaFactory$(AuthTokenProvider.TokenChangeListener tokenChangeListener, InternalTokenResult internalTokenResult) {
        return new AndroidAuthTokenProvider$$Lambda$6(tokenChangeListener, internalTokenResult);
    }

    @Override // java.lang.Runnable
    public void run() {
        AndroidAuthTokenProvider.lambda$addTokenChangeListener$3(this.arg$1, this.arg$2);
    }
}
