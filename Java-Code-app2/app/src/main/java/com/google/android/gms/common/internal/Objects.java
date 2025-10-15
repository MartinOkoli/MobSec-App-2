package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\Objects.smali */
public final class Objects {
    public static boolean equal(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\internal\Objects$ToStringHelper.smali */
    public static final class ToStringHelper {
        private final List<String> zzer;
        private final Object zzes;

        private ToStringHelper(Object obj) {
            this.zzes = Preconditions.checkNotNull(obj);
            this.zzer = new ArrayList();
        }

        public final ToStringHelper add(String str, Object obj) {
            List<String> list = this.zzer;
            String str2 = (String) Preconditions.checkNotNull(str);
            String strValueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(strValueOf).length());
            sb.append(str2);
            sb.append("=");
            sb.append(strValueOf);
            list.add(sb.toString());
            return this;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.zzes.getClass().getSimpleName());
            sb.append('{');
            int size = this.zzer.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.zzer.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }
    }

    private Objects() {
        throw new AssertionError("Uninstantiable");
    }
}
