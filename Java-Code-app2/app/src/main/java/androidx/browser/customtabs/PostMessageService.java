package androidx.browser.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\customtabs\PostMessageService.smali */
public class PostMessageService extends Service {
    private IPostMessageService.Stub mBinder = new IPostMessageService.Stub() { // from class: androidx.browser.customtabs.PostMessageService.1
        @Override // android.support.customtabs.IPostMessageService
        public void onMessageChannelReady(ICustomTabsCallback callback, Bundle extras) throws RemoteException {
            callback.onMessageChannelReady(extras);
        }

        @Override // android.support.customtabs.IPostMessageService
        public void onPostMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException {
            callback.onPostMessage(message, extras);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
