package androidx.browser.trusted;

import android.app.NotificationManager;
import android.os.Parcelable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\trusted\NotificationApiHelperForM.smali */
public class NotificationApiHelperForM {
    static Parcelable[] getActiveNotifications(NotificationManager manager) {
        return manager.getActiveNotifications();
    }

    private NotificationApiHelperForM() {
    }
}
