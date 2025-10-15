package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\IAccountAccessor.smali */
public interface IAccountAccessor extends IInterface {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\IAccountAccessor$Stub.smali */
    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements IAccountAccessor {
        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\IAccountAccessor$Stub$zza.smali */
        public static class zza extends com.google.android.gms.internal.common.zza implements IAccountAccessor {
            zza(IBinder iBinder) {
                super(iBinder, "com.google.android.gms.common.internal.IAccountAccessor");
            }

            @Override // com.google.android.gms.common.internal.IAccountAccessor
            public final Account getAccount() throws RemoteException {
                Parcel parcelZza = zza(2, zza());
                Account account = (Account) com.google.android.gms.internal.common.zzc.zza(parcelZza, Account.CREATOR);
                parcelZza.recycle();
                return account;
            }
        }

        public static IAccountAccessor asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            if (iInterfaceQueryLocalInterface instanceof IAccountAccessor) {
                return (IAccountAccessor) iInterfaceQueryLocalInterface;
            }
            return new zza(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 2) {
                Account account = getAccount();
                parcel2.writeNoException();
                com.google.android.gms.internal.common.zzc.zzb(parcel2, account);
                return true;
            }
            return false;
        }
    }

    Account getAccount() throws RemoteException;
}
