package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseException;

/* compiled from: com.google.firebase:firebase-auth-interop@@19.0.2 */
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\auth\FirebaseAuthException.smali */
public class FirebaseAuthException extends FirebaseException {
    private final String zza;

    public FirebaseAuthException(String str, String str2) {
        super(str2);
        this.zza = Preconditions.checkNotEmpty(str);
    }

    public String getErrorCode() {
        return this.zza;
    }
}
