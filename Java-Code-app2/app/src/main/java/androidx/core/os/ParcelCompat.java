package androidx.core.os;

import android.os.Parcel;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\os\ParcelCompat.smali */
public final class ParcelCompat {
    public static boolean readBoolean(Parcel in) {
        return in.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    private ParcelCompat() {
    }
}
