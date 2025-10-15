package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\database\sqlite\SQLiteCursorCompat.smali */
public final class SQLiteCursorCompat {
    private SQLiteCursorCompat() {
    }

    public static void setFillWindowForwardOnly(SQLiteCursor cursor, boolean fillWindowForwardOnly) {
        if (Build.VERSION.SDK_INT >= 28) {
            cursor.setFillWindowForwardOnly(fillWindowForwardOnly);
        }
    }
}
