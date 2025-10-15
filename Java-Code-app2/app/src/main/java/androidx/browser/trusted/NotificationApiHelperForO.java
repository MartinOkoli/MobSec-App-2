package androidx.browser.trusted;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\trusted\NotificationApiHelperForO.smali */
class NotificationApiHelperForO {
    static boolean isChannelEnabled(NotificationManager manager, String channelId) {
        NotificationChannel channel = manager.getNotificationChannel(channelId);
        return channel == null || channel.getImportance() != 0;
    }

    static Notification copyNotificationOntoChannel(Context context, NotificationManager manager, Notification notification, String channelId, String channelName) {
        manager.createNotificationChannel(new NotificationChannel(channelId, channelName, 3));
        if (manager.getNotificationChannel(channelId).getImportance() == 0) {
            return null;
        }
        Notification.Builder builder = Notification.Builder.recoverBuilder(context, notification);
        builder.setChannelId(channelId);
        return builder.build();
    }

    private NotificationApiHelperForO() {
    }
}
