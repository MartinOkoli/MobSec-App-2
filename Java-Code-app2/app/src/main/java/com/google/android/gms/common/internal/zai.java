package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zai.smali */
final class zai implements PendingResultUtil.zaa {
    zai() {
    }

    @Override // com.google.android.gms.common.internal.PendingResultUtil.zaa
    public final ApiException zaf(Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
