package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\server\response\zap.smali */
public final class zap implements Parcelable.Creator<SafeParcelResponse> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse[] newArray(int i) {
        return new SafeParcelResponse[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Parcel parcelCreateParcel = null;
        int i = 0;
        zak zakVar = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 2) {
                parcelCreateParcel = SafeParcelReader.createParcel(parcel, header);
            } else if (fieldId == 3) {
                zakVar = (zak) SafeParcelReader.createParcelable(parcel, header, zak.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new SafeParcelResponse(i, parcelCreateParcel, zakVar);
    }
}
