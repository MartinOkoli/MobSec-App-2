package androidx.core.content;

import android.content.SharedPreferences;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\SharedPreferencesCompat.smali */
public final class SharedPreferencesCompat {

    @Deprecated
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\SharedPreferencesCompat$EditorCompat.smali */
    public static final class EditorCompat {
        private static EditorCompat sInstance;
        private final Helper mHelper = new Helper();

        /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\content\SharedPreferencesCompat$EditorCompat$Helper.smali */
        private static class Helper {
            Helper() {
            }

            public void apply(SharedPreferences.Editor editor) {
                try {
                    editor.apply();
                } catch (AbstractMethodError e) {
                    editor.commit();
                }
            }
        }

        private EditorCompat() {
        }

        @Deprecated
        public static EditorCompat getInstance() {
            if (sInstance == null) {
                sInstance = new EditorCompat();
            }
            return sInstance;
        }

        @Deprecated
        public void apply(SharedPreferences.Editor editor) {
            this.mHelper.apply(editor);
        }
    }

    private SharedPreferencesCompat() {
    }
}
