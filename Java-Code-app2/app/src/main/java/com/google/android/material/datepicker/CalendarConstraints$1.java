package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\datepicker\CalendarConstraints$1.smali */
class CalendarConstraints$1 implements Parcelable.Creator<CalendarConstraints> {
    CalendarConstraints$1() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CalendarConstraints createFromParcel(Parcel source) {
        Month start = (Month) source.readParcelable(Month.class.getClassLoader());
        Month end = (Month) source.readParcelable(Month.class.getClassLoader());
        Month openAt = (Month) source.readParcelable(Month.class.getClassLoader());
        CalendarConstraints$DateValidator validator = (CalendarConstraints$DateValidator) source.readParcelable(CalendarConstraints$DateValidator.class.getClassLoader());
        return new CalendarConstraints(start, end, validator, openAt, (CalendarConstraints$1) null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.Parcelable.Creator
    public CalendarConstraints[] newArray(int size) {
        return new CalendarConstraints[size];
    }
}
