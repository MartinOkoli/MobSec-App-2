package com.google.android.gms.common.internal.safeparcel;

import android.os.Parcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable.smali */
public interface SafeParcelable extends Parcelable {
    public static final String NULL = "SAFE_PARCELABLE_NULL_STRING";

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$Class.smali */
    public @interface Class {
        String creator();

        boolean validate() default false;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$Constructor.smali */
    public @interface Constructor {
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$Field.smali */
    public @interface Field {
        String defaultValue() default "SAFE_PARCELABLE_NULL_STRING";

        String defaultValueUnchecked() default "SAFE_PARCELABLE_NULL_STRING";

        String getter() default "SAFE_PARCELABLE_NULL_STRING";

        int id();

        String type() default "SAFE_PARCELABLE_NULL_STRING";
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$Indicator.smali */
    public @interface Indicator {
        String getter() default "SAFE_PARCELABLE_NULL_STRING";
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$Param.smali */
    public @interface Param {
        int id();
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$Reserved.smali */
    public @interface Reserved {
        int[] value();
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\safeparcel\SafeParcelable$VersionField.smali */
    public @interface VersionField {
        String getter() default "SAFE_PARCELABLE_NULL_STRING";

        int id();

        String type() default "SAFE_PARCELABLE_NULL_STRING";
    }
}
