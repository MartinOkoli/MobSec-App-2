package com.google.firebase.platforminfo;

import android.content.Context;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\platforminfo\LibraryVersionComponent.smali */
public class LibraryVersionComponent {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\platforminfo\LibraryVersionComponent$VersionExtractor.smali */
    public interface VersionExtractor<T> {
        String extract(T t);
    }

    private LibraryVersionComponent() {
    }

    public static Component<?> create(String sdkName, String version) {
        return Component.intoSet(LibraryVersion.create(sdkName, version), LibraryVersion.class);
    }

    public static Component<?> fromContext(String sdkName, VersionExtractor<Context> extractor) {
        return Component.intoSetBuilder(LibraryVersion.class).add(Dependency.required(Context.class)).factory(LibraryVersionComponent$$Lambda$1.lambdaFactory$(sdkName, extractor)).build();
    }

    static /* synthetic */ LibraryVersion lambda$fromContext$0(String sdkName, VersionExtractor extractor, ComponentContainer c) {
        return LibraryVersion.create(sdkName, extractor.extract((Context) c.get(Context.class)));
    }
}
