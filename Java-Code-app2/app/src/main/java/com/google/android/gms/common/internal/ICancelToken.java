package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ICancelToken.smali */
public interface ICancelToken extends IInterface {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ICancelToken$Stub.smali */
    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements ICancelToken {
        public Stub() {
            super("com.google.android.gms.common.internal.ICancelToken");
        }

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\ICancelToken$Stub$zza.smali */
        public static class zza extends com.google.android.gms.internal.common.zza implements ICancelToken {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.ICancelToken");
            }

            @Override // com.google.android.gms.common.internal.ICancelToken
            public final void cancel() throws RemoteException {
                zzc(2, zza());
            }
        }

        public static ICancelToken asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.ICancelToken");
            if (iInterfaceQueryLocalInterface instanceof ICancelToken) {
                return (ICancelToken) iInterfaceQueryLocalInterface;
            }
            return new zza(iBinder);
        }
    }

    void cancel() throws RemoteException;
}
