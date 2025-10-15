package androidx.core.os;

import java.util.Locale;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\core\os\LocaleListInterface.smali */
interface LocaleListInterface {
    Locale get(int i);

    Locale getFirstMatch(String[] strArr);

    Object getLocaleList();

    int indexOf(Locale locale);

    boolean isEmpty();

    int size();

    String toLanguageTags();
}
