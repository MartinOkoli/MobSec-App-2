package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.Set;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\components\ComponentContainer.smali */
public interface ComponentContainer {
    <T> T get(Class<T> cls);

    <T> Deferred<T> getDeferred(Class<T> cls);

    <T> Provider<T> getProvider(Class<T> cls);

    <T> Set<T> setOf(Class<T> cls);

    <T> Provider<Set<T>> setOfProvider(Class<T> cls);
}
