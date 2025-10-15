package androidx.browser.trusted;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\browser\trusted\TrustedWebActivityIntent.smali */
public final class TrustedWebActivityIntent {
    private final Intent mIntent;
    private final List<Uri> mSharedFileUris;

    TrustedWebActivityIntent(Intent intent, List<Uri> sharedFileUris) {
        this.mIntent = intent;
        this.mSharedFileUris = sharedFileUris;
    }

    public void launchTrustedWebActivity(Context context) {
        grantUriPermissionToProvider(context);
        ContextCompat.startActivity(context, this.mIntent, null);
    }

    private void grantUriPermissionToProvider(Context context) {
        for (Uri uri : this.mSharedFileUris) {
            context.grantUriPermission(this.mIntent.getPackage(), uri, 1);
        }
    }

    public Intent getIntent() {
        return this.mIntent;
    }
}
