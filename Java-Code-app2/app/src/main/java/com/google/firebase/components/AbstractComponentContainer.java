package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Set;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\components\AbstractComponentContainer.smali */
abstract class AbstractComponentContainer implements ComponentContainer {
    AbstractComponentContainer() {
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> T get(Class<T> anInterface) {
        Provider<T> provider = getProvider(anInterface);
        if (provider == null) {
            return null;
        }
        return provider.get();
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Set<T> setOf(Class<T> anInterface) {
        return setOfProvider(anInterface).get();
    }
}
