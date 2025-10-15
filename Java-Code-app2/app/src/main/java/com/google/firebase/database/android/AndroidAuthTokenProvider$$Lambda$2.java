package com.google.firebase.database.android;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.core.AuthTokenProvider;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider$$Lambda$2.smali */
final /* synthetic */ class AndroidAuthTokenProvider$$Lambda$2 implements OnSuccessListener {
    private final AuthTokenProvider.GetTokenCompletionListener arg$1;

    private AndroidAuthTokenProvider$$Lambda$2(AuthTokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        this.arg$1 = getTokenCompletionListener;
    }

    public static OnSuccessListener lambdaFactory$(AuthTokenProvider.GetTokenCompletionListener getTokenCompletionListener) {
        return new AndroidAuthTokenProvider$$Lambda$2(getTokenCompletionListener);
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public void onSuccess(Object obj) {
        AndroidAuthTokenProvider.lambda$getToken$1(this.arg$1, (GetTokenResult) obj);
    }
}
