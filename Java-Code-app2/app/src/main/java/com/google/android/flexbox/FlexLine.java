package com.google.android.flexbox;

import java.util.ArrayList;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexLine.smali */
public class FlexLine {
    int mCrossSize;
    int mDividerLengthInMainSize;
    int mItemCount;
    int mMainSize;
    int mMaxBaseline;
    float mTotalFlexGrow;
    float mTotalFlexShrink;
    int mLeft = Integer.MAX_VALUE;
    int mTop = Integer.MAX_VALUE;
    int mRight = Integer.MIN_VALUE;
    int mBottom = Integer.MIN_VALUE;
    List<Integer> mIndicesAlignSelfStretch = new ArrayList();

    FlexLine() {
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getTop() {
        return this.mTop;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getBottom() {
        return this.mBottom;
    }

    public int getMainSize() {
        return this.mMainSize;
    }

    public int getCrossSize() {
        return this.mCrossSize;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public float getTotalFlexGrow() {
        return this.mTotalFlexGrow;
    }

    public float getTotalFlexShrink() {
        return this.mTotalFlexShrink;
    }
}
