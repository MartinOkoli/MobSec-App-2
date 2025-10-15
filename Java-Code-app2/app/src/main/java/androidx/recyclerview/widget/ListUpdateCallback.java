package androidx.recyclerview.widget;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\recyclerview\widget\ListUpdateCallback.smali */
public interface ListUpdateCallback {
    void onChanged(int i, int i2, Object obj);

    void onInserted(int i, int i2);

    void onMoved(int i, int i2);

    void onRemoved(int i, int i2);
}
