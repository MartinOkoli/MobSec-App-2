package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ISignInButtonCreator.smali */
public interface ISignInButtonCreator extends IInterface {
    IObjectWrapper newSignInButton(IObjectWrapper iObjectWrapper, int i, int i2) throws RemoteException;

    IObjectWrapper newSignInButtonFromConfig(IObjectWrapper iObjectWrapper, SignInButtonConfig signInButtonConfig) throws RemoteException;
}
