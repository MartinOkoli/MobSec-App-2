package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.core.AuthTokenProvider;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider$$Lambda$3.smali */
final /* synthetic */ class AndroidAuthTokenProvider$$Lambda$3 implements OnFailureListener {
    private final AuthTokenProvider.GetTokenCompletionListener arg$1;

    private AndroidAuthTokenProvider$$Lambda$3(AuthTokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        this.arg$1 = getTokenCompletionListener;
    }

    public static OnFailureListener lambdaFactory$(AuthTokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        return new AndroidAuthTokenProvider$$Lambda$3(getTokenCompletionListener);
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public void onFailure(Exception exc) {
        AndroidAuthTokenProvider.lambda$getToken$2(this.arg$1, exc);
    }
}
