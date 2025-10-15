package com.google.android.gms.common.api.internal;

import android.os.RemoteException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\RemoteCall.smali */
public interface RemoteCall<T, U> {
    void accept(T t, U u) throws RemoteException;
}
