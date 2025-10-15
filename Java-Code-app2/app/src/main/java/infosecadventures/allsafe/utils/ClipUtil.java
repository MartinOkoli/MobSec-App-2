package infosecadventures.allsafe.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClipUtil.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Linfosecadventures/allsafe/utils/ClipUtil;", "", "()V", "copyToClipboard", "", "context", "Landroid/content/Context;", "text", "", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\utils\ClipUtil.smali */
public final class ClipUtil {
    public static final ClipUtil INSTANCE = new ClipUtil();

    private ClipUtil() {
    }

    public final void copyToClipboard(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        Object systemService = context.getSystemService("clipboard");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.content.ClipboardManager");
        }
        ClipboardManager clipboard = (ClipboardManager) systemService;
        ClipData clip = ClipData.newPlainText("result", text);
        Intrinsics.checkNotNullExpressionValue(clip, "ClipData.newPlainText(\"result\", text)");
        clipboard.setPrimaryClip(clip);
    }
}
