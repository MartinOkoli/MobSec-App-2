package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.IAccountAccessor;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\AccountAccessor.smali */
public class AccountAccessor extends IAccountAccessor.Stub {
    public static Account getAccountBinderSafe(IAccountAccessor iAccountAccessor) {
        if (iAccountAccessor != null) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return iAccountAccessor.getAccount();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
        return null;
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account getAccount() {
        throw new NoSuchMethodError();
    }

    public boolean equals(Object obj) {
        throw new NoSuchMethodError();
    }
}
