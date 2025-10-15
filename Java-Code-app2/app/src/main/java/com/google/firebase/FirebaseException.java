package com.google.firebase;

import com.google.android.gms.common.internal.Preconditions;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\FirebaseException.smali */
public class FirebaseException extends Exception {
    @Deprecated
    protected FirebaseException() {
    }

    public FirebaseException(String str) {
        super(Preconditions.checkNotEmpty(str, "Detail message must not be empty"));
    }

    public FirebaseException(String str, Throwable th) {
        super(Preconditions.checkNotEmpty(str, "Detail message must not be empty"), th);
    }
}
