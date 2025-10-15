package androidx.media;

import android.os.Bundle;
import androidx.versionedparcelable.VersionedParcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\media\AudioAttributesImpl.smali */
interface AudioAttributesImpl extends VersionedParcelable {
    Object getAudioAttributes();

    int getContentType();

    int getFlags();

    int getLegacyStreamType();

    int getRawLegacyStreamType();

    int getUsage();

    int getVolumeControlStream();

    Bundle toBundle();
}
