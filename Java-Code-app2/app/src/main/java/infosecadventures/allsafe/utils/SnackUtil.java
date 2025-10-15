package infosecadventures.allsafe.utils;

import android.R;
import android.app.Activity;
import android.view.View;
import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SnackUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Linfosecadventures/allsafe/utils/SnackUtil;", "", "()V", "confirmExit", "", "activity", "Landroid/app/Activity;", "simpleMessage", "message", "", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\utils\SnackUtil.smali */
public final class SnackUtil {
    public static final SnackUtil INSTANCE = new SnackUtil();

    private SnackUtil() {
    }

    public final void simpleMessage(Activity activity, String message) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(message, "message");
        final Snackbar s = Snackbar.make(activity.findViewById(R.id.content), message, 0);
        Intrinsics.checkNotNullExpressionValue(s, "Snackbar.make(activity.f…ge, Snackbar.LENGTH_LONG)");
        s.setAction(R.string.ok, new View.OnClickListener() { // from class: infosecadventures.allsafe.utils.SnackUtil.simpleMessage.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                s.dismiss();
            }
        });
        s.setBackgroundTint(activity.getColor(infosecadventures.allsafe.R.color.primaryColor));
        s.setTextColor(activity.getColor(infosecadventures.allsafe.R.color.white));
        s.setActionTextColor(activity.getColor(infosecadventures.allsafe.R.color.secondaryLightColor));
        s.show();
    }

    public final void confirmExit(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Snackbar s = Snackbar.make(activity.findViewById(R.id.content), "Press EXIT, to close the application.", -1);
        Intrinsics.checkNotNullExpressionValue(s, "Snackbar.make(activity.f…\", Snackbar.LENGTH_SHORT)");
        s.setAction("EXIT", new View.OnClickListener() { // from class: infosecadventures.allsafe.utils.SnackUtil.confirmExit.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                activity.finish();
            }
        });
        s.setBackgroundTint(activity.getColor(infosecadventures.allsafe.R.color.primaryColor));
        s.setTextColor(activity.getColor(infosecadventures.allsafe.R.color.white));
        s.setActionTextColor(activity.getColor(infosecadventures.allsafe.R.color.secondaryLightColor));
        s.show();
    }
}
