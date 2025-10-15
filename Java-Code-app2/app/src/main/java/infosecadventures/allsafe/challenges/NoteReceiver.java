package infosecadventures.allsafe.challenges;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import infosecadventures.allsafe.R;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\NoteReceiver.smali */
public class NoteReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String server = intent.getStringExtra("server");
        String note = intent.getStringExtra("note");
        String notification_message = intent.getStringExtra("notification_message");
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        HttpUrl httpUrl = new HttpUrl.Builder().scheme("http").host(server).addPathSegment("api").addPathSegment("v1").addPathSegment("note").addPathSegment("add").addQueryParameter("auth_token", "YWxsc2FmZV9kZXZfYWRtaW5fdG9rZW4=").addQueryParameter("note", note).build();
        Log.d("ALLSAFE", httpUrl.getUrl());
        Request request = new Request.Builder().url(httpUrl).build();
        okHttpClient.newCall(request).enqueue(new Callback() { // from class: infosecadventures.allsafe.challenges.NoteReceiver.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Log.d("ALLSAFE", e.getMessage());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBodyBody = response.body();
                Objects.requireNonNull(responseBodyBody);
                Log.d("ALLSAFE", responseBodyBody.string());
            }
        });
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ALLSAFE");
        builder.setContentTitle("Notification from Allsafe");
        builder.setContentText(notification_message);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setAutoCancel(true);
        builder.setChannelId("ALLSAFE");
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel("ALLSAFE", "ALLSAFE_NOTIFICATION", 4);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1, notification);
    }
}
