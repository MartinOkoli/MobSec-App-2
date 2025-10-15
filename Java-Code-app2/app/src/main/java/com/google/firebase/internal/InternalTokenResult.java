package com.google.firebase.internal;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.firebase:firebase-auth-interop@@19.0.2 */
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\internal\InternalTokenResult.smali */
public class InternalTokenResult {
    private String zza;

    public InternalTokenResult(String str) {
        this.zza = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof InternalTokenResult) {
            return Objects.equal(this.zza, ((InternalTokenResult) obj).zza);
        }
        return false;
    }

    public String getToken() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("token", this.zza).toString();
    }
}
