package com.google.android.material.timepicker;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\timepicker\TimePickerControls.smali */
interface TimePickerControls {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\timepicker\TimePickerControls$ActiveSelection.smali */
    public @interface ActiveSelection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\timepicker\TimePickerControls$ClockPeriod.smali */
    public @interface ClockPeriod {
    }

    void setActiveSelection(int i);

    void setHandRotation(float f);

    void setValues(String[] strArr, int i);

    void updateTime(int i, int i2, int i3);
}
