package android.support.v4.media.session;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\session\MediaControllerCompatApi23.smali */
class MediaControllerCompatApi23 {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\session\MediaControllerCompatApi23$TransportControls.smali */
    public static class TransportControls {
        public static void playFromUri(Object controlsObj, Uri uri, Bundle extras) {
            ((MediaController.TransportControls) controlsObj).playFromUri(uri, extras);
        }

        private TransportControls() {
        }
    }

    private MediaControllerCompatApi23() {
    }
}
