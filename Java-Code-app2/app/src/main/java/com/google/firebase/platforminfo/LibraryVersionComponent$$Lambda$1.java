package com.google.firebase.platforminfo;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.platforminfo.LibraryVersionComponent;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\platforminfo\LibraryVersionComponent$$Lambda$1.smali */
final /* synthetic */ class LibraryVersionComponent$$Lambda$1 implements ComponentFactory {
    private final String arg$1;
    private final LibraryVersionComponent.VersionExtractor arg$2;

    private LibraryVersionComponent$$Lambda$1(String str, LibraryVersionComponent.VersionExtractor versionExtractor) {
        this.arg$1 = str;
        this.arg$2 = versionExtractor;
    }

    public static ComponentFactory lambdaFactory$(String str, LibraryVersionComponent.VersionExtractor versionExtractor) {
        return new LibraryVersionComponent$$Lambda$1(str, versionExtractor);
    }

    @Override // com.google.firebase.components.ComponentFactory
    public Object create(ComponentContainer componentContainer) {
        return LibraryVersionComponent.lambda$fromContext$0(this.arg$1, this.arg$2, componentContainer);
    }
}
