package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.Metadata;

/* compiled from: AndroidLog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"androidLevel", "", "Ljava/util/logging/LogRecord;", "getAndroidLevel", "(Ljava/util/logging/LogRecord;)I", "okhttp"}, k = 2, mv = {1, 4, 0})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\okhttp3\internal\platform\android\AndroidLogKt.smali */
public final class AndroidLogKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int getAndroidLevel(LogRecord $this$androidLevel) {
        if ($this$androidLevel.getLevel().intValue() > Level.INFO.intValue()) {
            return 5;
        }
        return $this$androidLevel.getLevel().intValue() == Level.INFO.intValue() ? 4 : 3;
    }
}
