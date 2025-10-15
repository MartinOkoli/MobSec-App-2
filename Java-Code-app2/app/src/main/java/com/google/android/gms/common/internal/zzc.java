package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\zzc.smali */
public final class zzc implements Parcelable.Creator<zzb> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzb[] newArray(int i) {
        return new zzb[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzb createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Bundle bundleCreateBundle = null;
        Feature[] featureArr = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
            } else if (fieldId == 2) {
                featureArr = (Feature[]) SafeParcelReader.createTypedArray(parcel, header, Feature.CREATOR);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzb(bundleCreateBundle, featureArr);
    }
}
