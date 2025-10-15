package com.google.firebase.storage;

import java.io.IOException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\CancelException.smali */
class CancelException extends IOException {
    CancelException() {
        super("The operation was canceled.");
    }
}
