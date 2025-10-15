package android.support.v4.app;

import androidx.core.app.RemoteActionCompat;
import androidx.versionedparcelable.VersionedParcel;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\app\RemoteActionCompatParcelizer.smali */
public final class RemoteActionCompatParcelizer extends androidx.core.app.RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel parcel) {
        return androidx.core.app.RemoteActionCompatParcelizer.read(parcel);
    }

    public static void write(RemoteActionCompat obj, VersionedParcel parcel) throws SecurityException, IllegalArgumentException {
        androidx.core.app.RemoteActionCompatParcelizer.write(obj, parcel);
    }
}
