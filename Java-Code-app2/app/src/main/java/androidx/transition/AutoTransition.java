package androidx.transition;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\AutoTransition.smali */
public class AutoTransition extends TransitionSet {
    public AutoTransition() {
        init();
    }

    public AutoTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrdering(1);
        addTransition(new Fade(2)).addTransition(new ChangeBounds()).addTransition(new Fade(1));
    }
}
