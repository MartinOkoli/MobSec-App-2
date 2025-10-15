package androidx.media;

import androidx.versionedparcelable.VersionedParcel;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\media\AudioAttributesCompatParcelizer.smali */
public final class AudioAttributesCompatParcelizer {
    public static AudioAttributesCompat read(VersionedParcel parcel) {
        AudioAttributesCompat obj = new AudioAttributesCompat();
        obj.mImpl = (AudioAttributesImpl) parcel.readVersionedParcelable(obj.mImpl, 1);
        return obj;
    }

    public static void write(AudioAttributesCompat obj, VersionedParcel parcel) throws SecurityException, IllegalArgumentException {
        parcel.setSerializationFlags(false, false);
        parcel.writeVersionedParcelable(obj.mImpl, 1);
    }
}
