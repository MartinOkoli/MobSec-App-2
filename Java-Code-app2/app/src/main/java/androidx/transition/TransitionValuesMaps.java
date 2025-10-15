package androidx.transition;

import android.util.SparseArray;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\TransitionValuesMaps.smali */
class TransitionValuesMaps {
    final ArrayMap<View, TransitionValues> mViewValues = new ArrayMap<>();
    final SparseArray<View> mIdValues = new SparseArray<>();
    final LongSparseArray<View> mItemIdValues = new LongSparseArray<>();
    final ArrayMap<String, View> mNameValues = new ArrayMap<>();

    TransitionValuesMaps() {
    }
}
