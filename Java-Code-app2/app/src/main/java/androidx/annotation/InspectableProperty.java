package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\annotation\InspectableProperty.smali */
public @interface InspectableProperty {

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\annotation\InspectableProperty$EnumEntry.smali */
    public @interface EnumEntry {
        String name();

        int value();
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\annotation\InspectableProperty$FlagEntry.smali */
    public @interface FlagEntry {
        int mask() default 0;

        String name();

        int target();
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\annotation\InspectableProperty$ValueType.smali */
    public enum ValueType {
        NONE,
        INFERRED,
        INT_ENUM,
        INT_FLAG,
        COLOR,
        GRAVITY,
        RESOURCE_ID
    }

    int attributeId() default 0;

    EnumEntry[] enumMapping() default {};

    FlagEntry[] flagMapping() default {};

    boolean hasAttributeId() default true;

    String name() default "";

    ValueType valueType() default ValueType.INFERRED;
}
