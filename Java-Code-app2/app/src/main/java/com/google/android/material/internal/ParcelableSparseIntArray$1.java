package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\internal\ParcelableSparseIntArray$1.smali */
class ParcelableSparseIntArray$1 implements Parcelable.Creator<ParcelableSparseIntArray> {
    ParcelableSparseIntArray$1() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ParcelableSparseIntArray createFromParcel(Parcel source) {
        int size = source.readInt();
        ParcelableSparseIntArray read = new ParcelableSparseIntArray(size);
        int[] keys = new int[size];
        int[] values = new int[size];
        source.readIntArray(keys);
        source.readIntArray(values);
        for (int i = 0; i < size; i++) {
            read.put(keys[i], values[i]);
        }
        return read;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ParcelableSparseIntArray[] newArray(int size) {
        return new ParcelableSparseIntArray[size];
    }
}
