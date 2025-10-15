package androidx.appcompat.app;

import androidx.appcompat.view.ActionMode;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\app\AppCompatCallback.smali */
public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback);
}
