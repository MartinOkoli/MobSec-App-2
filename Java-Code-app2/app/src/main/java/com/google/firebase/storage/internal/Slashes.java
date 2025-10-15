package com.google.firebase.storage.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\internal\Slashes.smali */
public class Slashes {
    public static String preserveSlashEncode(String s) {
        if (TextUtils.isEmpty(s)) {
            return "";
        }
        return slashize(Uri.encode(s));
    }

    public static String slashize(String s) {
        Preconditions.checkNotNull(s);
        return s.replace("%2F", "/");
    }

    public static String normalizeSlashes(String uriSegment) {
        if (TextUtils.isEmpty(uriSegment)) {
            return "";
        }
        if (uriSegment.startsWith("/") || uriSegment.endsWith("/") || uriSegment.contains("//")) {
            StringBuilder result = new StringBuilder();
            for (String stringSegment : uriSegment.split("/", -1)) {
                if (!TextUtils.isEmpty(stringSegment)) {
                    if (result.length() > 0) {
                        result.append("/");
                        result.append(stringSegment);
                    } else {
                        result.append(stringSegment);
                    }
                }
            }
            return result.toString();
        }
        return uriSegment;
    }
}
