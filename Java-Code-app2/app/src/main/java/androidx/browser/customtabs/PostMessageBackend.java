package androidx.browser.customtabs;

import android.content.Context;
import android.os.Bundle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\customtabs\PostMessageBackend.smali */
public interface PostMessageBackend {
    void onDisconnectChannel(Context appContext);

    boolean onNotifyMessageChannelReady(Bundle extras);

    boolean onPostMessage(String message, Bundle extras);
}
