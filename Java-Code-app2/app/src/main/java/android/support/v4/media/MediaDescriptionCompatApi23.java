package android.support.v4.media;

import android.media.MediaDescription;
import android.net.Uri;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\MediaDescriptionCompatApi23.smali */
class MediaDescriptionCompatApi23 {
    public static Uri getMediaUri(Object descriptionObj) {
        return ((MediaDescription) descriptionObj).getMediaUri();
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\MediaDescriptionCompatApi23$Builder.smali */
    static class Builder {
        public static void setMediaUri(Object builderObj, Uri mediaUri) {
            ((MediaDescription.Builder) builderObj).setMediaUri(mediaUri);
        }

        private Builder() {
        }
    }

    private MediaDescriptionCompatApi23() {
    }
}
