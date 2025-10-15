package kotlin.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PlatformImplementations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\"\u0010\b\u001a\u0002H\t\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0083\b¢\u0006\u0002\u0010\f\u001a\b\u0010\r\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "castToBaseType", ExifInterface.GPS_DIRECTION_TRUE, "", "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "getJavaVersion", "kotlin-stdlib"}, k = 2, mv = {1, 4, 1})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\internal\PlatformImplementationsKt.smali */
public final class PlatformImplementationsKt {
    public static final PlatformImplementations IMPLEMENTATIONS;

    static {
        PlatformImplementations platformImplementations;
        int version = getJavaVersion();
        if (version >= 65544) {
            try {
                Object objNewInstance = Class.forName("kotlin.internal.jdk8.JDK8PlatformImplementations").newInstance();
                Intrinsics.checkNotNullExpressionValue(objNewInstance, "Class.forName(\"kotlin.in…entations\").newInstance()");
                try {
                    if (objNewInstance == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                    }
                    platformImplementations = (PlatformImplementations) objNewInstance;
                } catch (ClassCastException e) {
                    Throwable thInitCause = new ClassCastException("Instance classloader: " + objNewInstance.getClass().getClassLoader() + ", base type classloader: " + PlatformImplementations.class.getClassLoader()).initCause(e);
                    Intrinsics.checkNotNullExpressionValue(thInitCause, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
                    throw thInitCause;
                }
            } catch (ClassNotFoundException e2) {
                try {
                    Object objNewInstance2 = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
                    Intrinsics.checkNotNullExpressionValue(objNewInstance2, "Class.forName(\"kotlin.in…entations\").newInstance()");
                    try {
                        if (objNewInstance2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                        }
                        platformImplementations = (PlatformImplementations) objNewInstance2;
                    } catch (ClassCastException e3) {
                        Throwable thInitCause2 = new ClassCastException("Instance classloader: " + objNewInstance2.getClass().getClassLoader() + ", base type classloader: " + PlatformImplementations.class.getClassLoader()).initCause(e3);
                        Intrinsics.checkNotNullExpressionValue(thInitCause2, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
                        throw thInitCause2;
                    }
                } catch (ClassNotFoundException e4) {
                }
            }
        } else if (version >= 65543) {
            try {
                Object objNewInstance3 = Class.forName("kotlin.internal.jdk7.JDK7PlatformImplementations").newInstance();
                Intrinsics.checkNotNullExpressionValue(objNewInstance3, "Class.forName(\"kotlin.in…entations\").newInstance()");
                try {
                    if (objNewInstance3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                    }
                    platformImplementations = (PlatformImplementations) objNewInstance3;
                } catch (ClassCastException e5) {
                    Throwable thInitCause3 = new ClassCastException("Instance classloader: " + objNewInstance3.getClass().getClassLoader() + ", base type classloader: " + PlatformImplementations.class.getClassLoader()).initCause(e5);
                    Intrinsics.checkNotNullExpressionValue(thInitCause3, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
                    throw thInitCause3;
                }
            } catch (ClassNotFoundException e6) {
                try {
                    Object objNewInstance4 = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
                    Intrinsics.checkNotNullExpressionValue(objNewInstance4, "Class.forName(\"kotlin.in…entations\").newInstance()");
                    try {
                        if (objNewInstance4 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
                        }
                        platformImplementations = (PlatformImplementations) objNewInstance4;
                    } catch (ClassCastException e7) {
                        Throwable thInitCause4 = new ClassCastException("Instance classloader: " + objNewInstance4.getClass().getClassLoader() + ", base type classloader: " + PlatformImplementations.class.getClassLoader()).initCause(e7);
                        Intrinsics.checkNotNullExpressionValue(thInitCause4, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
                        throw thInitCause4;
                    }
                } catch (ClassNotFoundException e8) {
                }
            }
        } else {
            platformImplementations = new PlatformImplementations();
        }
        IMPLEMENTATIONS = platformImplementations;
    }

    private static final /* synthetic */ <T> T castToBaseType(Object obj) throws Throwable {
        try {
            Intrinsics.reifiedOperationMarker(1, ExifInterface.GPS_DIRECTION_TRUE);
            return (T) obj;
        } catch (ClassCastException e) {
            ClassLoader classLoader = obj.getClass().getClassLoader();
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Throwable thInitCause = new ClassCastException("Instance classloader: " + classLoader + ", base type classloader: " + Object.class.getClassLoader()).initCause(e);
            Intrinsics.checkNotNullExpressionValue(thInitCause, "ClassCastException(\"Inst…baseTypeCL\").initCause(e)");
            throw thInitCause;
        }
    }

    private static final int getJavaVersion() {
        String version = System.getProperty("java.specification.version");
        if (version == null) {
            return 65542;
        }
        int firstDot = StringsKt.indexOf$default((CharSequence) version, '.', 0, false, 6, (Object) null);
        if (firstDot < 0) {
            try {
                return Integer.parseInt(version) * 65536;
            } catch (NumberFormatException e) {
                return 65542;
            }
        }
        int secondDot = StringsKt.indexOf$default((CharSequence) version, '.', firstDot + 1, false, 4, (Object) null);
        if (secondDot < 0) {
            secondDot = version.length();
        }
        if (version != null) {
            String firstPart = version.substring(0, firstDot);
            Intrinsics.checkNotNullExpressionValue(firstPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            int i = firstDot + 1;
            if (version != null) {
                String secondPart = version.substring(i, secondDot);
                Intrinsics.checkNotNullExpressionValue(secondPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                try {
                    return (Integer.parseInt(firstPart) * 65536) + Integer.parseInt(secondPart);
                } catch (NumberFormatException e2) {
                    return 65542;
                }
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    public static final boolean apiVersionIsAtLeast(int major, int minor, int patch) {
        return KotlinVersion.CURRENT.isAtLeast(major, minor, patch);
    }
}
