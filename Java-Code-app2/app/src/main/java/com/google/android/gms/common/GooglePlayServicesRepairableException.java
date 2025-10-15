package com.google.android.gms.common;

import android.content.Intent;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\GooglePlayServicesRepairableException.smali */
public class GooglePlayServicesRepairableException extends UserRecoverableException {
    private final int zzag;

    public GooglePlayServicesRepairableException(int i, String str, Intent intent) {
        super(str, intent);
        this.zzag = i;
    }

    public int getConnectionStatusCode() {
        return this.zzag;
    }
}
