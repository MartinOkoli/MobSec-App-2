package com.google.firebase.internal;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.GetTokenResult;

/* compiled from: com.google.firebase:firebase-auth-interop@@19.0.2 */
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\internal\InternalTokenProvider.smali */
public interface InternalTokenProvider {
    Task<GetTokenResult> getAccessToken(boolean z);

    String getUid();
}
