package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\gms\common\images\zab.smali */
final class zab {
    public final Uri uri;

    public zab(Uri uri) {
        this.uri = uri;
    }

    public final int hashCode() {
        return Objects.hashCode(this.uri);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zab)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return Objects.equal(((zab) obj).uri, this.uri);
    }
}
