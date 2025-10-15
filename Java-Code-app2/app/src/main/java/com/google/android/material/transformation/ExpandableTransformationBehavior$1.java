package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\transformation\ExpandableTransformationBehavior$1.smali */
class ExpandableTransformationBehavior$1 extends AnimatorListenerAdapter {
    final /* synthetic */ ExpandableTransformationBehavior this$0;

    ExpandableTransformationBehavior$1(ExpandableTransformationBehavior this$0) {
        this.this$0 = this$0;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animation) {
        ExpandableTransformationBehavior.access$002(this.this$0, (AnimatorSet) null);
    }
}
