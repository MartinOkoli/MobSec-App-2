package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.transition.VisibilityAnimatorProvider;
import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\transition\MaterialVisibility.smali */
abstract class MaterialVisibility<P extends VisibilityAnimatorProvider> extends Visibility {
    private final List<VisibilityAnimatorProvider> additionalAnimatorProviders = new ArrayList();
    private final P primaryAnimatorProvider;
    private VisibilityAnimatorProvider secondaryAnimatorProvider;

    protected MaterialVisibility(P primaryAnimatorProvider, VisibilityAnimatorProvider secondaryAnimatorProvider) {
        this.primaryAnimatorProvider = primaryAnimatorProvider;
        this.secondaryAnimatorProvider = secondaryAnimatorProvider;
        setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    }

    public P getPrimaryAnimatorProvider() {
        return this.primaryAnimatorProvider;
    }

    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.secondaryAnimatorProvider;
    }

    public void setSecondaryAnimatorProvider(VisibilityAnimatorProvider secondaryAnimatorProvider) {
        this.secondaryAnimatorProvider = secondaryAnimatorProvider;
    }

    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider additionalAnimatorProvider) {
        this.additionalAnimatorProviders.add(additionalAnimatorProvider);
    }

    public boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider additionalAnimatorProvider) {
        return this.additionalAnimatorProviders.remove(additionalAnimatorProvider);
    }

    public void clearAdditionalAnimatorProvider() {
        this.additionalAnimatorProviders.clear();
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createAnimator(sceneRoot, view, true);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createAnimator(sceneRoot, view, false);
    }

    private Animator createAnimator(ViewGroup sceneRoot, View view, boolean appearing) {
        AnimatorSet set = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        addAnimatorIfNeeded(animators, this.primaryAnimatorProvider, sceneRoot, view, appearing);
        addAnimatorIfNeeded(animators, this.secondaryAnimatorProvider, sceneRoot, view, appearing);
        for (VisibilityAnimatorProvider additionalAnimatorProvider : this.additionalAnimatorProviders) {
            addAnimatorIfNeeded(animators, additionalAnimatorProvider, sceneRoot, view, appearing);
        }
        AnimatorSetCompat.playTogether(set, animators);
        return set;
    }

    private static void addAnimatorIfNeeded(List<Animator> animators, VisibilityAnimatorProvider animatorProvider, ViewGroup sceneRoot, View view, boolean appearing) {
        Animator animator;
        if (animatorProvider == null) {
            return;
        }
        if (appearing) {
            animator = animatorProvider.createAppear(sceneRoot, view);
        } else {
            animator = animatorProvider.createDisappear(sceneRoot, view);
        }
        if (animator != null) {
            animators.add(animator);
        }
    }
}
