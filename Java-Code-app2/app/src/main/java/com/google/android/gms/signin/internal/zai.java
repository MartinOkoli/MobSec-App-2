package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\signin\internal\zai.smali */
public final class zai implements Parcelable.Creator<zah> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zah[] newArray(int i) {
        return new zah[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zah createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        ResolveAccountRequest resolveAccountRequest = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                resolveAccountRequest = (ResolveAccountRequest) SafeParcelReader.createParcelable(parcel, header, ResolveAccountRequest.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zah(i, resolveAccountRequest);
    }
}
