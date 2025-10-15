package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\internal\ParcelableSparseBooleanArray$1.smali */
class ParcelableSparseBooleanArray$1 implements Parcelable.Creator<ParcelableSparseBooleanArray> {
    ParcelableSparseBooleanArray$1() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ParcelableSparseBooleanArray createFromParcel(Parcel source) {
        int size = source.readInt();
        ParcelableSparseBooleanArray read = new ParcelableSparseBooleanArray(size);
        int[] keys = new int[size];
        boolean[] values = new boolean[size];
        source.readIntArray(keys);
        source.readBooleanArray(values);
        for (int i = 0; i < size; i++) {
            read.put(keys[i], values[i]);
        }
        return read;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public ParcelableSparseBooleanArray[] newArray(int size) {
        return new ParcelableSparseBooleanArray[size];
    }
}
