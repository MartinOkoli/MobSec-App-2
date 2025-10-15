package com.scottyab.rootbeer;

import com.scottyab.rootbeer.util.QLog;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\scottyab\rootbeer\RootBeerNative.smali */
public class RootBeerNative {
    private static boolean libraryLoaded;

    public native int checkForRoot(Object[] objArr);

    public native int setLogDebugMessages(boolean z);

    static {
        libraryLoaded = false;
        try {
            System.loadLibrary("tool-checker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            QLog.e(e);
        }
    }

    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }
}
