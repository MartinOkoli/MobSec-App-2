package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\ApiExceptionMapper.smali */
public class ApiExceptionMapper implements StatusExceptionMapper {
    @Override // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public Exception getException(Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
