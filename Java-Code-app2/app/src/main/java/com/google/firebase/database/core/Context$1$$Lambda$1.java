package com.google.firebase.database.core;

import com.google.firebase.database.connection.ConnectionAuthTokenProvider;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\Context$1$$Lambda$1.smali */
final /* synthetic */ class Context$1$$Lambda$1 implements Runnable {
    private final ConnectionAuthTokenProvider.GetTokenCallback arg$1;
    private final String arg$2;

    private Context$1$$Lambda$1(ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback, String str) {
        this.arg$1 = getTokenCallback;
        this.arg$2 = str;
    }

    public static Runnable lambdaFactory$(ConnectionAuthTokenProvider.GetTokenCallback getTokenCallback, String str) {
        return new Context$1$$Lambda$1(getTokenCallback, str);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.arg$1.onSuccess(this.arg$2);
    }
}
