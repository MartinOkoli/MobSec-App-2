package com.google.firebase.database.android;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.core.AuthTokenProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\android\AndroidAuthTokenProvider.smali */
public class AndroidAuthTokenProvider implements AuthTokenProvider {
    private final Deferred<InternalAuthProvider> deferredAuthProvider;
    private final AtomicReference<InternalAuthProvider> internalAuth = new AtomicReference<>();

    public AndroidAuthTokenProvider(Deferred<InternalAuthProvider> deferredAuthProvider) {
        this.deferredAuthProvider = deferredAuthProvider;
        deferredAuthProvider.whenAvailable(AndroidAuthTokenProvider$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$new$0(AndroidAuthTokenProvider androidAuthTokenProvider, Provider authProvider) {
        androidAuthTokenProvider.internalAuth.set((InternalAuthProvider) authProvider.get());
    }

    @Override // com.google.firebase.database.core.AuthTokenProvider
    public void getToken(boolean forceRefresh, AuthTokenProvider.GetTokenCompletionListener listener) {
        InternalAuthProvider authProvider = this.internalAuth.get();
        if (authProvider != null) {
            Task<GetTokenResult> getTokenResult = authProvider.getAccessToken(forceRefresh);
            getTokenResult.addOnSuccessListener(AndroidAuthTokenProvider$$Lambda$2.lambdaFactory$(listener)).addOnFailureListener(AndroidAuthTokenProvider$$Lambda$3.lambdaFactory$(listener));
        } else {
            listener.onSuccess(null);
        }
    }

    static /* synthetic */ void lambda$getToken$1(AuthTokenProvider.GetTokenCompletionListener listener, GetTokenResult result) {
        listener.onSuccess(result.getToken());
    }

    static /* synthetic */ void lambda$getToken$2(AuthTokenProvider.GetTokenCompletionListener listener, Exception e) {
        if (isUnauthenticatedUsage(e)) {
            listener.onSuccess(null);
        } else {
            listener.onError(e.getMessage());
        }
    }

    @Override // com.google.firebase.database.core.AuthTokenProvider
    public void addTokenChangeListener(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenListener) {
        this.deferredAuthProvider.whenAvailable(AndroidAuthTokenProvider$$Lambda$4.lambdaFactory$(executorService, tokenListener));
    }

    static /* synthetic */ void lambda$addTokenChangeListener$5(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenListener, Provider provider) {
        ((InternalAuthProvider) provider.get()).addIdTokenListener(AndroidAuthTokenProvider$$Lambda$5.lambdaFactory$(executorService, tokenListener));
    }

    static /* synthetic */ void lambda$addTokenChangeListener$4(ExecutorService executorService, AuthTokenProvider.TokenChangeListener tokenListener, InternalTokenResult tokenResult) {
        executorService.execute(AndroidAuthTokenProvider$$Lambda$6.lambdaFactory$(tokenListener, tokenResult));
    }

    static /* synthetic */ void lambda$addTokenChangeListener$3(AuthTokenProvider.TokenChangeListener tokenListener, InternalTokenResult tokenResult) {
        tokenListener.onTokenChange(tokenResult.getToken());
    }

    @Override // com.google.firebase.database.core.AuthTokenProvider
    public void removeTokenChangeListener(AuthTokenProvider.TokenChangeListener tokenListener) {
    }

    private static boolean isUnauthenticatedUsage(Exception e) {
        return (e instanceof FirebaseApiNotAvailableException) || (e instanceof FirebaseNoSignedInUserException);
    }
}
