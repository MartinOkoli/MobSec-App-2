package com.google.firebase.database.connection;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\connection\ConnectionAuthTokenProvider.smali */
public interface ConnectionAuthTokenProvider {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\database\connection\ConnectionAuthTokenProvider$GetTokenCallback.smali */
    public interface GetTokenCallback {
        void onError(String str);

        void onSuccess(String str);
    }

    void getToken(boolean z, GetTokenCallback getTokenCallback);
}
