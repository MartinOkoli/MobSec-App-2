package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\components\OptionalProvider$$Lambda$4.smali */
final /* synthetic */ class OptionalProvider$$Lambda$4 implements Deferred.DeferredHandler {
    private static final OptionalProvider$$Lambda$4 instance = new OptionalProvider$$Lambda$4();

    private OptionalProvider$$Lambda$4() {
    }

    @Override // com.google.firebase.inject.Deferred.DeferredHandler
    public void handle(Provider provider) {
        OptionalProvider.lambda$static$0(provider);
    }
}
