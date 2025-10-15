package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zza.smali */
final class zza implements Parcelable.Creator<BinderWrapper> {
    zza() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BinderWrapper[] newArray(int i) {
        return new BinderWrapper[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ BinderWrapper createFromParcel(Parcel parcel) {
        return new BinderWrapper(parcel, null);
    }
}
