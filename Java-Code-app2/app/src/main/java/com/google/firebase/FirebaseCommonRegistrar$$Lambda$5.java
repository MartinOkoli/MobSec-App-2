package com.google.firebase;

import android.content.Context;
import com.google.firebase.platforminfo.LibraryVersionComponent;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\FirebaseCommonRegistrar$$Lambda$5.smali */
final /* synthetic */ class FirebaseCommonRegistrar$$Lambda$5 implements LibraryVersionComponent.VersionExtractor {
    private static final FirebaseCommonRegistrar$$Lambda$5 instance = new FirebaseCommonRegistrar$$Lambda$5();

    private FirebaseCommonRegistrar$$Lambda$5() {
    }

    @Override // com.google.firebase.platforminfo.LibraryVersionComponent.VersionExtractor
    public String extract(Object obj) {
        return FirebaseCommonRegistrar.lambda$getComponents$2((Context) obj);
    }
}
