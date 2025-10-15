package androidx.browser.trusted;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.trusted.ITrustedWebActivityCallback;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\trusted\TrustedWebActivityCallbackRemote.smali */
public class TrustedWebActivityCallbackRemote {
    private final ITrustedWebActivityCallback mCallbackBinder;

    private TrustedWebActivityCallbackRemote(ITrustedWebActivityCallback callbackBinder) {
        this.mCallbackBinder = callbackBinder;
    }

    static TrustedWebActivityCallbackRemote fromBinder(IBinder binder) {
        ITrustedWebActivityCallback callbackBinder;
        if (binder == null) {
            callbackBinder = null;
        } else {
            callbackBinder = ITrustedWebActivityCallback.Stub.asInterface(binder);
        }
        if (callbackBinder == null) {
            return null;
        }
        return new TrustedWebActivityCallbackRemote(callbackBinder);
    }

    public void runExtraCallback(String callbackName, Bundle args) throws RemoteException {
        this.mCallbackBinder.onExtraCallback(callbackName, args);
    }
}
