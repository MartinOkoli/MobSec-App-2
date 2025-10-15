package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\FirebaseExceptionMapper.smali */
public class FirebaseExceptionMapper implements StatusExceptionMapper {
    @Override // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public Exception getException(Status status) {
        return status.getStatusCode() == 8 ? new FirebaseException(status.zzg()) : new FirebaseApiNotAvailableException(status.zzg());
    }
}
