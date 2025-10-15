package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\IStatusCallback.smali */
public interface IStatusCallback extends IInterface {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\IStatusCallback$Stub.smali */
    public static abstract class Stub extends com.google.android.gms.internal.base.zab implements IStatusCallback {
        public Stub() {
            super("com.google.android.gms.common.api.internal.IStatusCallback");
        }

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\api\internal\IStatusCallback$Stub$zaa.smali */
        public static class zaa extends com.google.android.gms.internal.base.zaa implements IStatusCallback {
            zaa(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.api.internal.IStatusCallback");
            }

            @Override // com.google.android.gms.common.api.internal.IStatusCallback
            public final void onResult(Status status) throws RemoteException {
                Parcel parcelZaa = zaa();
                com.google.android.gms.internal.base.zac.zaa(parcelZaa, status);
                zac(1, parcelZaa);
            }
        }

        public static IStatusCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.api.internal.IStatusCallback");
            if (iInterfaceQueryLocalInterface instanceof IStatusCallback) {
                return (IStatusCallback) iInterfaceQueryLocalInterface;
            }
            return new zaa(iBinder);
        }

        @Override // com.google.android.gms.internal.base.zab
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                onResult((Status) com.google.android.gms.internal.base.zac.zaa(parcel, Status.CREATOR));
                return true;
            }
            return false;
        }
    }

    void onResult(Status status) throws RemoteException;
}
