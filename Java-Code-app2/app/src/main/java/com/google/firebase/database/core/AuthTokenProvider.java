package com.google.firebase.database.core;

import java.util.concurrent.ExecutorService;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\AuthTokenProvider.smali */
public interface AuthTokenProvider {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\AuthTokenProvider$GetTokenCompletionListener.smali */
    public interface GetTokenCompletionListener {
        void onError(String str);

        void onSuccess(String str);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\core\AuthTokenProvider$TokenChangeListener.smali */
    public interface TokenChangeListener {
        void onTokenChange();

        void onTokenChange(String str);
    }

    void addTokenChangeListener(ExecutorService executorService, TokenChangeListener tokenChangeListener);

    void getToken(boolean z, GetTokenCompletionListener getTokenCompletionListener);

    void removeTokenChangeListener(TokenChangeListener tokenChangeListener);
}
