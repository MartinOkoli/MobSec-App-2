package androidx.transition;

import android.view.ViewGroup;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\transition\TransitionPropagation.smali */
public abstract class TransitionPropagation {
    public abstract void captureValues(TransitionValues transitionValues);

    public abstract String[] getPropagationProperties();

    public abstract long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2);
}
