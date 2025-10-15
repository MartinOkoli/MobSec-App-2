package androidx.media;

import android.content.Context;
import android.os.Parcel;
import androidx.media.MediaBrowserServiceCompatApi21;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\media\MediaBrowserServiceCompatApi23.smali */
class MediaBrowserServiceCompatApi23 {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\media\MediaBrowserServiceCompatApi23$ServiceCompatProxy.smali */
    public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        void onLoadItem(String str, MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> resultWrapper);
    }

    public static Object createService(Context context, ServiceCompatProxy serviceProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceProxy);
    }

    private MediaBrowserServiceCompatApi23() {
    }
}
