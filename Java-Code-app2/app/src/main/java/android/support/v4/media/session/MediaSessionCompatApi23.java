package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompatApi21;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\session\MediaSessionCompatApi23.smali */
class MediaSessionCompatApi23 {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\session\MediaSessionCompatApi23$Callback.smali */
    public interface Callback extends MediaSessionCompatApi21.Callback {
        void onPlayFromUri(Uri uri, Bundle bundle);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\android\support\v4\media\session\MediaSessionCompatApi23$CallbackProxy.smali */
    static class CallbackProxy<T extends Callback> extends MediaSessionCompatApi21.CallbackProxy<T> {
        public CallbackProxy(T callback) {
            super(callback);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPlayFromUri(Uri uri, Bundle extras) {
            MediaSessionCompat.ensureClassLoader(extras);
            ((Callback) this.mCallback).onPlayFromUri(uri, extras);
        }
    }

    private MediaSessionCompatApi23() {
    }
}
