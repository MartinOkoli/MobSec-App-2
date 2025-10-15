package com.google.firebase.inject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\inject\Deferred.smali */
public interface Deferred<T> {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\inject\Deferred$DeferredHandler.smali */
    public interface DeferredHandler<T> {
        void handle(Provider<T> provider);
    }

    void whenAvailable(DeferredHandler<T> deferredHandler);
}
