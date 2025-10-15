package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout.smali */
public class FlexboxLayout extends ViewGroup {
    public static final int ALIGN_CONTENT_CENTER = 2;
    public static final int ALIGN_CONTENT_FLEX_END = 1;
    public static final int ALIGN_CONTENT_FLEX_START = 0;
    public static final int ALIGN_CONTENT_SPACE_AROUND = 4;
    public static final int ALIGN_CONTENT_SPACE_BETWEEN = 3;
    public static final int ALIGN_CONTENT_STRETCH = 5;
    public static final int ALIGN_ITEMS_BASELINE = 3;
    public static final int ALIGN_ITEMS_CENTER = 2;
    public static final int ALIGN_ITEMS_FLEX_END = 1;
    public static final int ALIGN_ITEMS_FLEX_START = 0;
    public static final int ALIGN_ITEMS_STRETCH = 4;
    public static final int FLEX_DIRECTION_COLUMN = 2;
    public static final int FLEX_DIRECTION_COLUMN_REVERSE = 3;
    public static final int FLEX_DIRECTION_ROW = 0;
    public static final int FLEX_DIRECTION_ROW_REVERSE = 1;
    public static final int FLEX_WRAP_NOWRAP = 0;
    public static final int FLEX_WRAP_WRAP = 1;
    public static final int FLEX_WRAP_WRAP_REVERSE = 2;
    public static final int JUSTIFY_CONTENT_CENTER = 2;
    public static final int JUSTIFY_CONTENT_FLEX_END = 1;
    public static final int JUSTIFY_CONTENT_FLEX_START = 0;
    public static final int JUSTIFY_CONTENT_SPACE_AROUND = 4;
    public static final int JUSTIFY_CONTENT_SPACE_BETWEEN = 3;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    private int mAlignContent;
    private int mAlignItems;
    private boolean[] mChildrenFrozen;
    private Drawable mDividerDrawableHorizontal;
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private int mFlexWrap;
    private int mJustifyContent;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$AlignContent.smali */
    public @interface AlignContent {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$AlignItems.smali */
    public @interface AlignItems {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$DividerMode.smali */
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$FlexDirection.smali */
    public @interface FlexDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$FlexWrap.smali */
    public @interface FlexWrap {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$JustifyContent.smali */
    public @interface JustifyContent {
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFlexLines = new ArrayList();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlexboxLayout, defStyleAttr, 0);
        this.mFlexDirection = a.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = a.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = a.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = a.getInt(R.styleable.FlexboxLayout_alignItems, 4);
        this.mAlignContent = a.getInt(R.styleable.FlexboxLayout_alignContent, 5);
        Drawable drawable = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawableHorizontal = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawableHorizontal != null) {
            setDividerDrawableHorizontal(drawableHorizontal);
        }
        Drawable drawableVertical = a.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawableVertical != null) {
            setDividerDrawableVertical(drawableVertical);
        }
        int dividerMode = a.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (dividerMode != 0) {
            this.mShowDividerVertical = dividerMode;
            this.mShowDividerHorizontal = dividerMode;
        }
        int dividerModeVertical = a.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (dividerModeVertical != 0) {
            this.mShowDividerVertical = dividerModeVertical;
        }
        int dividerModeHorizontal = a.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (dividerModeHorizontal != 0) {
            this.mShowDividerHorizontal = dividerModeHorizontal;
        }
        a.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (isOrderChangedFromLastMeasurement()) {
            this.mReorderedIndices = createReorderedIndices();
        }
        boolean[] zArr = this.mChildrenFrozen;
        if (zArr == null || zArr.length < getChildCount()) {
            this.mChildrenFrozen = new boolean[getChildCount()];
        }
        int i = this.mFlexDirection;
        if (i == 0 || i == 1) {
            measureHorizontal(widthMeasureSpec, heightMeasureSpec);
        } else if (i == 2 || i == 3) {
            measureVertical(widthMeasureSpec, heightMeasureSpec);
        } else {
            throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        }
        Arrays.fill(this.mChildrenFrozen, false);
    }

    public View getReorderedChildAt(int index) {
        if (index < 0) {
            return null;
        }
        int[] iArr = this.mReorderedIndices;
        if (index >= iArr.length) {
            return null;
        }
        return getChildAt(iArr[index]);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        this.mReorderedIndices = createReorderedIndices(child, index, params);
        super.addView(child, index, params);
    }

    private int[] createReorderedIndices(View viewBeforeAdded, int indexForViewBeforeAdded, ViewGroup.LayoutParams paramsForViewBeforeAdded) {
        int childCount = getChildCount();
        List<Order> orders = createOrders(childCount);
        Order orderForViewToBeAdded = new Order();
        if (viewBeforeAdded != null && (paramsForViewBeforeAdded instanceof LayoutParams)) {
            orderForViewToBeAdded.order = ((LayoutParams) paramsForViewBeforeAdded).order;
        } else {
            orderForViewToBeAdded.order = 1;
        }
        if (indexForViewBeforeAdded != -1 && indexForViewBeforeAdded != childCount && indexForViewBeforeAdded < getChildCount()) {
            orderForViewToBeAdded.index = indexForViewBeforeAdded;
            for (int i = indexForViewBeforeAdded; i < childCount; i++) {
                orders.get(i).index++;
            }
        } else {
            orderForViewToBeAdded.index = childCount;
        }
        orders.add(orderForViewToBeAdded);
        return sortOrdersIntoReorderedIndices(childCount + 1, orders);
    }

    private int[] createReorderedIndices() {
        int childCount = getChildCount();
        List<Order> orders = createOrders(childCount);
        return sortOrdersIntoReorderedIndices(childCount, orders);
    }

    private int[] sortOrdersIntoReorderedIndices(int childCount, List<Order> orders) {
        Collections.sort(orders);
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(childCount);
        }
        this.mOrderCache.clear();
        int[] reorderedIndices = new int[childCount];
        int i = 0;
        for (Order order : orders) {
            reorderedIndices[i] = order.index;
            this.mOrderCache.append(i, order.order);
            i++;
        }
        return reorderedIndices;
    }

    private List<Order> createOrders(int childCount) {
        List<Order> orders = new ArrayList<>(childCount);
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            Order order = new Order();
            order.order = params.order;
            order.index = i;
            orders.add(order);
        }
        return orders;
    }

    private boolean isOrderChangedFromLastMeasurement() {
        int childCount = getChildCount();
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(childCount);
        }
        if (this.mOrderCache.size() != childCount) {
            return true;
        }
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view != null) {
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (lp.order != this.mOrderCache.get(i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void measureHorizontal(int widthMeasureSpec, int heightMeasureSpec) {
        int paddingStart;
        int childWidth;
        int widthMode;
        LayoutParams lp;
        int indexInFlexLine;
        int largestHeightInRow;
        int i;
        int widthMode2 = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int childState = 0;
        this.mFlexLines.clear();
        int childCount = getChildCount();
        int paddingStart2 = ViewCompat.getPaddingStart(this);
        int paddingEnd = ViewCompat.getPaddingEnd(this);
        int largestHeightInRow2 = Integer.MIN_VALUE;
        FlexLine flexLine = new FlexLine();
        flexLine.mMainSize = paddingStart2 + paddingEnd;
        FlexLine flexLine2 = flexLine;
        int indexInFlexLine2 = 0;
        int i2 = 0;
        while (i2 < childCount) {
            View child = getReorderedChildAt(i2);
            if (child == null) {
                addFlexLineIfLastFlexItem(i2, childCount, flexLine2);
                paddingStart = paddingStart2;
            } else {
                paddingStart = paddingStart2;
                if (child.getVisibility() == 8) {
                    flexLine2.mItemCount++;
                    addFlexLineIfLastFlexItem(i2, childCount, flexLine2);
                } else {
                    LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                    if (lp2.alignSelf == 4) {
                        flexLine2.mIndicesAlignSelfStretch.add(Integer.valueOf(i2));
                    }
                    int childWidth2 = lp2.width;
                    if (lp2.flexBasisPercent != -1.0f && widthMode2 == 1073741824) {
                        int childWidth3 = Math.round(widthSize * lp2.flexBasisPercent);
                        childWidth = childWidth3;
                    } else {
                        childWidth = childWidth2;
                    }
                    int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp2.leftMargin + lp2.rightMargin, childWidth);
                    int paddingTop = getPaddingTop() + getPaddingBottom();
                    int i3 = i2;
                    int i4 = lp2.topMargin;
                    int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + i4 + lp2.bottomMargin, lp2.height);
                    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    checkSizeConstraints(child);
                    int childState2 = ViewCompat.combineMeasuredStates(childState, ViewCompat.getMeasuredState(child));
                    int largestHeightInRow3 = Math.max(largestHeightInRow2, child.getMeasuredHeight() + lp2.topMargin + lp2.bottomMargin);
                    int i5 = flexLine2.mMainSize;
                    int widthMode3 = child.getMeasuredWidth() + lp2.leftMargin + lp2.rightMargin;
                    int i6 = widthMode2;
                    widthMode = widthMode2;
                    FlexLine flexLine3 = flexLine2;
                    if (isWrapRequired(i6, widthSize, i5, widthMode3, lp2, i3, indexInFlexLine2)) {
                        if (flexLine3.mItemCount > 0) {
                            addFlexLine(flexLine3);
                        }
                        flexLine2 = new FlexLine();
                        flexLine2.mItemCount = 1;
                        flexLine2.mMainSize = paddingStart + paddingEnd;
                        lp = lp2;
                        int largestHeightInRow4 = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                        indexInFlexLine = 0;
                        largestHeightInRow = largestHeightInRow4;
                    } else {
                        lp = lp2;
                        flexLine3.mItemCount++;
                        indexInFlexLine = indexInFlexLine2 + 1;
                        flexLine2 = flexLine3;
                        largestHeightInRow = largestHeightInRow3;
                    }
                    flexLine2.mMainSize += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    flexLine2.mTotalFlexGrow += lp.flexGrow;
                    flexLine2.mTotalFlexShrink += lp.flexShrink;
                    flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, largestHeightInRow);
                    i = i3;
                    if (hasDividerBeforeChildAtAlongMainAxis(i, indexInFlexLine)) {
                        flexLine2.mMainSize += this.mDividerVerticalWidth;
                        flexLine2.mDividerLengthInMainSize += this.mDividerVerticalWidth;
                    }
                    if (this.mFlexWrap != 2) {
                        flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, child.getBaseline() + lp.topMargin);
                    } else {
                        flexLine2.mMaxBaseline = Math.max(flexLine2.mMaxBaseline, (child.getMeasuredHeight() - child.getBaseline()) + lp.bottomMargin);
                    }
                    addFlexLineIfLastFlexItem(i, childCount, flexLine2);
                    indexInFlexLine2 = indexInFlexLine;
                    largestHeightInRow2 = largestHeightInRow;
                    childState = childState2;
                    i2 = i + 1;
                    paddingStart2 = paddingStart;
                    widthMode2 = widthMode;
                }
            }
            i = i2;
            widthMode = widthMode2;
            i2 = i + 1;
            paddingStart2 = paddingStart;
            widthMode2 = widthMode;
        }
        int largestHeightInRow5 = this.mFlexDirection;
        determineMainSize(largestHeightInRow5, widthMeasureSpec, heightMeasureSpec);
        if (this.mAlignItems == 3) {
            int viewIndex = 0;
            for (FlexLine flexLine4 : this.mFlexLines) {
                int largestHeightInLine = Integer.MIN_VALUE;
                for (int i7 = viewIndex; i7 < flexLine4.mItemCount + viewIndex; i7++) {
                    View child2 = getReorderedChildAt(i7);
                    LayoutParams lp3 = (LayoutParams) child2.getLayoutParams();
                    if (this.mFlexWrap != 2) {
                        int marginTop = flexLine4.mMaxBaseline - child2.getBaseline();
                        largestHeightInLine = Math.max(largestHeightInLine, child2.getHeight() + Math.max(marginTop, lp3.topMargin) + lp3.bottomMargin);
                    } else {
                        int marginBottom = (flexLine4.mMaxBaseline - child2.getMeasuredHeight()) + child2.getBaseline();
                        largestHeightInLine = Math.max(largestHeightInLine, child2.getHeight() + lp3.topMargin + Math.max(marginBottom, lp3.bottomMargin));
                    }
                }
                flexLine4.mCrossSize = largestHeightInLine;
                viewIndex += flexLine4.mItemCount;
            }
        }
        int viewIndex2 = this.mFlexDirection;
        determineCrossSize(viewIndex2, widthMeasureSpec, heightMeasureSpec, getPaddingTop() + getPaddingBottom());
        stretchViews(this.mFlexDirection, this.mAlignItems);
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, childState);
    }

    private void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
        int childHeight;
        LayoutParams lp;
        int indexInFlexLine;
        int largestWidthInColumn;
        int i;
        int i2 = widthMeasureSpec;
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int childState = 0;
        this.mFlexLines.clear();
        int childCount = getChildCount();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int largestWidthInColumn2 = Integer.MIN_VALUE;
        FlexLine flexLine = new FlexLine();
        flexLine.mMainSize = paddingTop + paddingBottom;
        FlexLine flexLine2 = flexLine;
        int indexInFlexLine2 = 0;
        int i3 = 0;
        while (i3 < childCount) {
            View child = getReorderedChildAt(i3);
            if (child == null) {
                addFlexLineIfLastFlexItem(i3, childCount, flexLine2);
            } else if (child.getVisibility() == 8) {
                flexLine2.mItemCount++;
                addFlexLineIfLastFlexItem(i3, childCount, flexLine2);
            } else {
                LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                if (lp2.alignSelf == 4) {
                    flexLine2.mIndicesAlignSelfStretch.add(Integer.valueOf(i3));
                }
                int childHeight2 = lp2.height;
                if (lp2.flexBasisPercent != -1.0f && heightMode == 1073741824) {
                    childHeight = Math.round(heightSize * lp2.flexBasisPercent);
                } else {
                    childHeight = childHeight2;
                }
                int paddingLeft = getPaddingLeft() + getPaddingRight();
                int i4 = i3;
                int i5 = lp2.leftMargin;
                int childWidthMeasureSpec = getChildMeasureSpec(i2, paddingLeft + i5 + lp2.rightMargin, lp2.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp2.topMargin + lp2.bottomMargin, childHeight);
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                checkSizeConstraints(child);
                int childState2 = ViewCompat.combineMeasuredStates(childState, ViewCompat.getMeasuredState(child));
                int largestWidthInColumn3 = Math.max(largestWidthInColumn2, child.getMeasuredWidth() + lp2.leftMargin + lp2.rightMargin);
                FlexLine flexLine3 = flexLine2;
                if (isWrapRequired(heightMode, heightSize, flexLine2.mMainSize, child.getMeasuredHeight() + lp2.topMargin + lp2.bottomMargin, lp2, i4, indexInFlexLine2)) {
                    if (flexLine3.mItemCount > 0) {
                        addFlexLine(flexLine3);
                    }
                    flexLine2 = new FlexLine();
                    flexLine2.mItemCount = 1;
                    flexLine2.mMainSize = paddingTop + paddingBottom;
                    lp = lp2;
                    int largestWidthInColumn4 = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    indexInFlexLine = 0;
                    largestWidthInColumn = largestWidthInColumn4;
                } else {
                    lp = lp2;
                    flexLine3.mItemCount++;
                    indexInFlexLine = indexInFlexLine2 + 1;
                    flexLine2 = flexLine3;
                    largestWidthInColumn = largestWidthInColumn3;
                }
                flexLine2.mMainSize += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                flexLine2.mTotalFlexGrow += lp.flexGrow;
                flexLine2.mTotalFlexShrink += lp.flexShrink;
                flexLine2.mCrossSize = Math.max(flexLine2.mCrossSize, largestWidthInColumn);
                i = i4;
                if (hasDividerBeforeChildAtAlongMainAxis(i, indexInFlexLine)) {
                    flexLine2.mMainSize += this.mDividerHorizontalHeight;
                }
                addFlexLineIfLastFlexItem(i, childCount, flexLine2);
                indexInFlexLine2 = indexInFlexLine;
                largestWidthInColumn2 = largestWidthInColumn;
                childState = childState2;
                i3 = i + 1;
                i2 = widthMeasureSpec;
            }
            i = i3;
            i3 = i + 1;
            i2 = widthMeasureSpec;
        }
        determineMainSize(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec);
        determineCrossSize(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, getPaddingLeft() + getPaddingRight());
        stretchViews(this.mFlexDirection, this.mAlignItems);
        setMeasuredDimensionForFlex(this.mFlexDirection, widthMeasureSpec, heightMeasureSpec, childState);
    }

    private void checkSizeConstraints(View view) {
        boolean needsMeasure = false;
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        int childWidth = view.getMeasuredWidth();
        int childHeight = view.getMeasuredHeight();
        if (view.getMeasuredWidth() < lp.minWidth) {
            needsMeasure = true;
            childWidth = lp.minWidth;
        } else if (view.getMeasuredWidth() > lp.maxWidth) {
            needsMeasure = true;
            childWidth = lp.maxWidth;
        }
        if (childHeight < lp.minHeight) {
            needsMeasure = true;
            childHeight = lp.minHeight;
        } else if (childHeight > lp.maxHeight) {
            needsMeasure = true;
            childHeight = lp.maxHeight;
        }
        if (needsMeasure) {
            view.measure(View.MeasureSpec.makeMeasureSpec(childWidth, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(childHeight, BasicMeasure.EXACTLY));
        }
    }

    private void addFlexLineIfLastFlexItem(int childIndex, int childCount, FlexLine flexLine) {
        if (childIndex == childCount - 1 && flexLine.mItemCount != 0) {
            addFlexLine(flexLine);
        }
    }

    private void addFlexLine(FlexLine flexLine) {
        if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if ((this.mShowDividerVertical & 4) > 0) {
                flexLine.mMainSize += this.mDividerVerticalWidth;
                flexLine.mDividerLengthInMainSize += this.mDividerVerticalWidth;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            flexLine.mMainSize += this.mDividerHorizontalHeight;
            flexLine.mDividerLengthInMainSize += this.mDividerHorizontalHeight;
        }
        this.mFlexLines.add(flexLine);
    }

    private void determineMainSize(int flexDirection, int widthMeasureSpec, int heightMeasureSpec) {
        int mainSize;
        int mainSize2;
        int paddingAlongMainAxis;
        int mainSize3;
        if (flexDirection != 0 && flexDirection != 1) {
            if (flexDirection != 2 && flexDirection != 3) {
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
            }
            int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
            if (heightMode == 1073741824) {
                mainSize3 = heightSize;
            } else {
                mainSize3 = getLargestMainSize();
            }
            int paddingAlongMainAxis2 = getPaddingTop() + getPaddingBottom();
            mainSize2 = mainSize3;
            paddingAlongMainAxis = paddingAlongMainAxis2;
        } else {
            int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
            if (widthMode == 1073741824) {
                mainSize = widthSize;
            } else {
                mainSize = getLargestMainSize();
            }
            int paddingAlongMainAxis3 = getPaddingLeft() + getPaddingRight();
            mainSize2 = mainSize;
            paddingAlongMainAxis = paddingAlongMainAxis3;
        }
        int childIndex = 0;
        Iterator<FlexLine> it = this.mFlexLines.iterator();
        while (true) {
            int childIndex2 = childIndex;
            if (it.hasNext()) {
                FlexLine flexLine = it.next();
                if (flexLine.mMainSize < mainSize2) {
                    childIndex = expandFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, flexDirection, mainSize2, paddingAlongMainAxis, childIndex2, false);
                } else {
                    childIndex = shrinkFlexItems(widthMeasureSpec, heightMeasureSpec, flexLine, flexDirection, mainSize2, paddingAlongMainAxis, childIndex2, false);
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:12:0x0037 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int expandFlexItems(int r24, int r25, com.google.android.flexbox.FlexLine r26, int r27, int r28, int r29, int r30, boolean r31) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.expandFlexItems(int, int, com.google.android.flexbox.FlexLine, int, int, int, int, boolean):int");
    }

    /* JADX WARN: Incorrect condition in loop: B:12:0x0036 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int shrinkFlexItems(int r23, int r24, com.google.android.flexbox.FlexLine r25, int r26, int r27, int r28, int r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.shrinkFlexItems(int, int, com.google.android.flexbox.FlexLine, int, int, int, int, boolean):int");
    }

    private int getChildWidthMeasureSpec(int widthMeasureSpec, LayoutParams lp) {
        int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width);
        int childWidth = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (childWidth > lp.maxWidth) {
            return View.MeasureSpec.makeMeasureSpec(lp.maxWidth, View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        if (childWidth < lp.minWidth) {
            return View.MeasureSpec.makeMeasureSpec(lp.minWidth, View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return childWidthMeasureSpec;
    }

    private int getChildHeightMeasureSpec(int heightMeasureSpec, LayoutParams lp) {
        int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin, lp.height);
        int childHeight = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (childHeight > lp.maxHeight) {
            return View.MeasureSpec.makeMeasureSpec(lp.maxHeight, View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        if (childHeight < lp.minHeight) {
            return View.MeasureSpec.makeMeasureSpec(lp.minHeight, View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return childHeightMeasureSpec;
    }

    private void determineCrossSize(int flexDirection, int widthMeasureSpec, int heightMeasureSpec, int paddingAlongCrossAxis) {
        int mode;
        int size;
        int i = 1;
        if (flexDirection == 0 || flexDirection == 1) {
            mode = View.MeasureSpec.getMode(heightMeasureSpec);
            size = View.MeasureSpec.getSize(heightMeasureSpec);
        } else if (flexDirection == 2 || flexDirection == 3) {
            mode = View.MeasureSpec.getMode(widthMeasureSpec);
            size = View.MeasureSpec.getSize(widthMeasureSpec);
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        if (mode == 1073741824) {
            int totalCrossSize = getSumOfCrossSize() + paddingAlongCrossAxis;
            if (this.mFlexLines.size() == 1) {
                this.mFlexLines.get(0).mCrossSize = size - paddingAlongCrossAxis;
                return;
            }
            if (this.mFlexLines.size() >= 2 && totalCrossSize < size) {
                int i2 = this.mAlignContent;
                if (i2 == 1) {
                    int spaceAboveAndBottom = size - totalCrossSize;
                    FlexLine dummySpaceFlexLine = new FlexLine();
                    dummySpaceFlexLine.mCrossSize = spaceAboveAndBottom;
                    this.mFlexLines.add(0, dummySpaceFlexLine);
                    return;
                }
                if (i2 == 2) {
                    int spaceAboveAndBottom2 = size - totalCrossSize;
                    List<FlexLine> newFlexLines = new ArrayList<>();
                    FlexLine dummySpaceFlexLine2 = new FlexLine();
                    dummySpaceFlexLine2.mCrossSize = spaceAboveAndBottom2 / 2;
                    int flexLineSize = this.mFlexLines.size();
                    for (int i3 = 0; i3 < flexLineSize; i3++) {
                        if (i3 == 0) {
                            newFlexLines.add(dummySpaceFlexLine2);
                        }
                        FlexLine flexLine = this.mFlexLines.get(i3);
                        newFlexLines.add(flexLine);
                        if (i3 == this.mFlexLines.size() - 1) {
                            newFlexLines.add(dummySpaceFlexLine2);
                        }
                    }
                    this.mFlexLines = newFlexLines;
                    return;
                }
                if (i2 == 3) {
                    int spaceTopAndBottom = size - totalCrossSize;
                    int numberOfSpaces = this.mFlexLines.size() - 1;
                    float spaceBetweenFlexLine = spaceTopAndBottom / numberOfSpaces;
                    float accumulatedError = 0.0f;
                    List<FlexLine> newFlexLines2 = new ArrayList<>();
                    int i4 = 0;
                    int flexLineSize2 = this.mFlexLines.size();
                    while (i4 < flexLineSize2) {
                        FlexLine flexLine2 = this.mFlexLines.get(i4);
                        newFlexLines2.add(flexLine2);
                        if (i4 != this.mFlexLines.size() - i) {
                            FlexLine dummySpaceFlexLine3 = new FlexLine();
                            if (i4 == this.mFlexLines.size() - 2) {
                                dummySpaceFlexLine3.mCrossSize = Math.round(spaceBetweenFlexLine + accumulatedError);
                                accumulatedError = 0.0f;
                            } else {
                                dummySpaceFlexLine3.mCrossSize = Math.round(spaceBetweenFlexLine);
                            }
                            accumulatedError += spaceBetweenFlexLine - dummySpaceFlexLine3.mCrossSize;
                            if (accumulatedError > 1.0f) {
                                dummySpaceFlexLine3.mCrossSize++;
                                accumulatedError -= 1.0f;
                            } else if (accumulatedError < -1.0f) {
                                dummySpaceFlexLine3.mCrossSize--;
                                accumulatedError += 1.0f;
                            }
                            newFlexLines2.add(dummySpaceFlexLine3);
                        }
                        i4++;
                        i = 1;
                    }
                    this.mFlexLines = newFlexLines2;
                    return;
                }
                if (i2 == 4) {
                    int spaceTopAndBottom2 = size - totalCrossSize;
                    int numberOfSpaces2 = this.mFlexLines.size() * 2;
                    List<FlexLine> newFlexLines3 = new ArrayList<>();
                    FlexLine dummySpaceFlexLine4 = new FlexLine();
                    dummySpaceFlexLine4.mCrossSize = spaceTopAndBottom2 / numberOfSpaces2;
                    for (FlexLine flexLine3 : this.mFlexLines) {
                        newFlexLines3.add(dummySpaceFlexLine4);
                        newFlexLines3.add(flexLine3);
                        newFlexLines3.add(dummySpaceFlexLine4);
                    }
                    this.mFlexLines = newFlexLines3;
                    return;
                }
                if (i2 == 5) {
                    float freeSpaceUnit = (size - totalCrossSize) / this.mFlexLines.size();
                    float accumulatedError2 = 0.0f;
                    int flexLinesSize = this.mFlexLines.size();
                    for (int i5 = 0; i5 < flexLinesSize; i5++) {
                        FlexLine flexLine4 = this.mFlexLines.get(i5);
                        float newCrossSizeAsFloat = flexLine4.mCrossSize + freeSpaceUnit;
                        if (i5 == this.mFlexLines.size() - 1) {
                            newCrossSizeAsFloat += accumulatedError2;
                            accumulatedError2 = 0.0f;
                        }
                        int newCrossSize = Math.round(newCrossSizeAsFloat);
                        accumulatedError2 += newCrossSizeAsFloat - newCrossSize;
                        if (accumulatedError2 > 1.0f) {
                            newCrossSize++;
                            accumulatedError2 -= 1.0f;
                        } else if (accumulatedError2 < -1.0f) {
                            newCrossSize--;
                            accumulatedError2 += 1.0f;
                        }
                        flexLine4.mCrossSize = newCrossSize;
                    }
                }
            }
        }
    }

    private void stretchViews(int flexDirection, int alignItems) {
        if (alignItems == 4) {
            int viewIndex = 0;
            for (FlexLine flexLine : this.mFlexLines) {
                int i = 0;
                while (i < flexLine.mItemCount) {
                    View view = getReorderedChildAt(viewIndex);
                    LayoutParams lp = (LayoutParams) view.getLayoutParams();
                    if (lp.alignSelf == -1 || lp.alignSelf == 4) {
                        if (flexDirection == 0 || flexDirection == 1) {
                            stretchViewVertically(view, flexLine.mCrossSize);
                        } else {
                            if (flexDirection != 2 && flexDirection != 3) {
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                            stretchViewHorizontally(view, flexLine.mCrossSize);
                        }
                    }
                    i++;
                    viewIndex++;
                }
            }
            return;
        }
        for (FlexLine flexLine2 : this.mFlexLines) {
            for (Integer index : flexLine2.mIndicesAlignSelfStretch) {
                View view2 = getReorderedChildAt(index.intValue());
                if (flexDirection == 0 || flexDirection == 1) {
                    stretchViewVertically(view2, flexLine2.mCrossSize);
                } else {
                    if (flexDirection != 2 && flexDirection != 3) {
                        throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                    }
                    stretchViewHorizontally(view2, flexLine2.mCrossSize);
                }
            }
        }
    }

    private void stretchViewVertically(View view, int crossSize) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        int newHeight = (crossSize - lp.topMargin) - lp.bottomMargin;
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(Math.max(newHeight, 0), BasicMeasure.EXACTLY));
    }

    private void stretchViewHorizontally(View view, int crossSize) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        int newWidth = (crossSize - lp.leftMargin) - lp.rightMargin;
        view.measure(View.MeasureSpec.makeMeasureSpec(Math.max(newWidth, 0), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), BasicMeasure.EXACTLY));
    }

    private void setMeasuredDimensionForFlex(int flexDirection, int widthMeasureSpec, int heightMeasureSpec, int childState) {
        int calculatedMaxHeight;
        int calculatedMaxWidth;
        int widthSizeAndState;
        int heightSizeAndState;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        if (flexDirection == 0 || flexDirection == 1) {
            int calculatedMaxHeight2 = getSumOfCrossSize();
            calculatedMaxHeight = calculatedMaxHeight2 + getPaddingTop() + getPaddingBottom();
            calculatedMaxWidth = getLargestMainSize();
        } else if (flexDirection == 2 || flexDirection == 3) {
            calculatedMaxHeight = getLargestMainSize();
            calculatedMaxWidth = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
        } else {
            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        if (widthMode == Integer.MIN_VALUE) {
            if (widthSize < calculatedMaxWidth) {
                childState = ViewCompat.combineMeasuredStates(childState, 16777216);
            } else {
                widthSize = calculatedMaxWidth;
            }
            widthSizeAndState = ViewCompat.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
        } else if (widthMode == 0) {
            widthSizeAndState = ViewCompat.resolveSizeAndState(calculatedMaxWidth, widthMeasureSpec, childState);
        } else if (widthMode == 1073741824) {
            if (widthSize < calculatedMaxWidth) {
                childState = ViewCompat.combineMeasuredStates(childState, 16777216);
            }
            widthSizeAndState = ViewCompat.resolveSizeAndState(widthSize, widthMeasureSpec, childState);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + widthMode);
        }
        if (heightMode == Integer.MIN_VALUE) {
            if (heightSize < calculatedMaxHeight) {
                childState = ViewCompat.combineMeasuredStates(childState, 256);
            } else {
                heightSize = calculatedMaxHeight;
            }
            heightSizeAndState = ViewCompat.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
        } else if (heightMode == 0) {
            heightSizeAndState = ViewCompat.resolveSizeAndState(calculatedMaxHeight, heightMeasureSpec, childState);
        } else if (heightMode == 1073741824) {
            if (heightSize < calculatedMaxHeight) {
                childState = ViewCompat.combineMeasuredStates(childState, 256);
            }
            heightSizeAndState = ViewCompat.resolveSizeAndState(heightSize, heightMeasureSpec, childState);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + heightMode);
        }
        setMeasuredDimension(widthSizeAndState, heightSizeAndState);
    }

    private boolean isWrapRequired(int mode, int maxSize, int currentLength, int childLength, LayoutParams lp, int childAbsoluteIndex, int childRelativeIndexInFlexLine) {
        if (this.mFlexWrap == 0) {
            return false;
        }
        if (lp.wrapBefore) {
            return true;
        }
        if (mode == 0) {
            return false;
        }
        if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
            if (hasDividerBeforeChildAtAlongMainAxis(childAbsoluteIndex, childRelativeIndexInFlexLine)) {
                childLength += this.mDividerVerticalWidth;
            }
            if ((this.mShowDividerVertical & 4) > 0) {
                childLength += this.mDividerVerticalWidth;
            }
        } else {
            if (hasDividerBeforeChildAtAlongMainAxis(childAbsoluteIndex, childRelativeIndexInFlexLine)) {
                childLength += this.mDividerHorizontalHeight;
            }
            if ((this.mShowDividerHorizontal & 4) > 0) {
                childLength += this.mDividerHorizontalHeight;
            }
        }
        return maxSize < currentLength + childLength;
    }

    private int getLargestMainSize() {
        int largestSize = Integer.MIN_VALUE;
        for (FlexLine flexLine : this.mFlexLines) {
            largestSize = Math.max(largestSize, flexLine.mMainSize);
        }
        return largestSize;
    }

    private int getSumOfCrossSize() {
        int sum = 0;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            if (hasDividerBeforeFlexLine(i)) {
                if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            if (hasEndDividerAfterFlexLine(i)) {
                if (isMainAxisDirectionHorizontal(this.mFlexDirection)) {
                    sum += this.mDividerHorizontalHeight;
                } else {
                    sum += this.mDividerVerticalWidth;
                }
            }
            sum += flexLine.mCrossSize;
        }
        return sum;
    }

    private boolean isMainAxisDirectionHorizontal(int flexDirection) {
        return flexDirection == 0 || flexDirection == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean isRtl;
        boolean isRtl2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int i = this.mFlexDirection;
        if (i == 0) {
            layoutHorizontal(layoutDirection == 1, left, top, right, bottom);
            return;
        }
        if (i == 1) {
            layoutHorizontal(layoutDirection != 1, left, top, right, bottom);
            return;
        }
        if (i == 2) {
            boolean isRtl3 = layoutDirection == 1;
            if (this.mFlexWrap != 2) {
                isRtl = isRtl3;
            } else {
                isRtl = isRtl3 ? false : true;
            }
            layoutVertical(isRtl, false, left, top, right, bottom);
            return;
        }
        if (i == 3) {
            boolean isRtl4 = layoutDirection == 1;
            if (this.mFlexWrap != 2) {
                isRtl2 = isRtl4;
            } else {
                isRtl2 = isRtl4 ? false : true;
            }
            layoutVertical(isRtl2, true, left, top, right, bottom);
            return;
        }
        throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
    }

    /* JADX WARN: Incorrect condition in loop: B:34:0x00c9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void layoutHorizontal(boolean r28, int r29, int r30, int r31, int r32) {
        /*
            Method dump skipped, instructions count: 586
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.layoutHorizontal(boolean, int, int, int, int):void");
    }

    private void layoutSingleChildHorizontal(View view, FlexLine flexLine, int flexWrap, int alignItems, int left, int top, int right, int bottom) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp.alignSelf != -1) {
            alignItems = lp.alignSelf;
        }
        int crossSize = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems != 1) {
                if (alignItems == 2) {
                    int topFromCrossAxis = (((crossSize - view.getMeasuredHeight()) + lp.topMargin) - lp.bottomMargin) / 2;
                    if (flexWrap != 2) {
                        view.layout(left, top + topFromCrossAxis, right, top + topFromCrossAxis + view.getMeasuredHeight());
                        return;
                    } else {
                        view.layout(left, top - topFromCrossAxis, right, (top - topFromCrossAxis) + view.getMeasuredHeight());
                        return;
                    }
                }
                if (alignItems == 3) {
                    if (flexWrap != 2) {
                        int marginTop = Math.max(flexLine.mMaxBaseline - view.getBaseline(), lp.topMargin);
                        view.layout(left, top + marginTop, right, bottom + marginTop);
                        return;
                    } else {
                        int marginBottom = Math.max((flexLine.mMaxBaseline - view.getMeasuredHeight()) + view.getBaseline(), lp.bottomMargin);
                        view.layout(left, top - marginBottom, right, bottom - marginBottom);
                        return;
                    }
                }
                if (alignItems != 4) {
                    return;
                }
            } else if (flexWrap != 2) {
                view.layout(left, ((top + crossSize) - view.getMeasuredHeight()) - lp.bottomMargin, right, (top + crossSize) - lp.bottomMargin);
                return;
            } else {
                view.layout(left, (top - crossSize) + view.getMeasuredHeight() + lp.topMargin, right, (bottom - crossSize) + view.getMeasuredHeight() + lp.topMargin);
                return;
            }
        }
        if (flexWrap != 2) {
            view.layout(left, lp.topMargin + top, right, lp.topMargin + bottom);
        } else {
            view.layout(left, top - lp.bottomMargin, right, bottom - lp.bottomMargin);
        }
    }

    private void layoutVertical(boolean isRtl, boolean fromBottomToTop, int left, int top, int right, int bottom) {
        int childLeft;
        int childRight;
        float childTop;
        float childBottom;
        float childTop2;
        float childBottom2;
        LayoutParams lp;
        int currentViewIndex;
        int j;
        FlexLine flexLine;
        int i;
        FlexLine flexLine2;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int childRight2 = getPaddingLeft();
        int childLeft2 = 0;
        int width = right - left;
        int height = bottom - top;
        int childRight3 = width - paddingRight;
        int size = this.mFlexLines.size();
        int i2 = 0;
        while (i2 < size) {
            FlexLine flexLine3 = this.mFlexLines.get(i2);
            if (!hasDividerBeforeFlexLine(i2)) {
                childLeft = childRight2;
                childRight = childRight3;
            } else {
                int i3 = this.mDividerVerticalWidth;
                int childLeft3 = childRight2 + i3;
                childLeft = childLeft3;
                childRight = childRight3 - i3;
            }
            float spaceBetweenItem = 0.0f;
            int i4 = this.mJustifyContent;
            if (i4 == 0) {
                childTop = paddingTop;
                childBottom = height - paddingBottom;
            } else if (i4 == 1) {
                childTop = (height - flexLine3.mMainSize) + paddingBottom;
                childBottom = flexLine3.mMainSize - paddingTop;
            } else if (i4 == 2) {
                float childTop3 = paddingTop;
                childTop = childTop3 + ((height - flexLine3.mMainSize) / 2.0f);
                childBottom = (height - paddingBottom) - ((height - flexLine3.mMainSize) / 2.0f);
            } else if (i4 == 3) {
                childTop = paddingTop;
                float denominator = flexLine3.mItemCount != 1 ? flexLine3.mItemCount - 1 : 1.0f;
                spaceBetweenItem = (height - flexLine3.mMainSize) / denominator;
                float childBottom3 = height - paddingBottom;
                childBottom = childBottom3;
            } else {
                if (i4 != 4) {
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
                }
                if (flexLine3.mItemCount != 0) {
                    spaceBetweenItem = (height - flexLine3.mMainSize) / flexLine3.mItemCount;
                }
                childTop = paddingTop + (spaceBetweenItem / 2.0f);
                childBottom = (height - paddingBottom) - (spaceBetweenItem / 2.0f);
            }
            float spaceBetweenItem2 = Math.max(spaceBetweenItem, 0.0f);
            int j2 = 0;
            int currentViewIndex2 = childLeft2;
            while (j2 < flexLine3.mItemCount) {
                View child = getReorderedChildAt(currentViewIndex2);
                if (child == null) {
                    j = j2;
                    flexLine2 = flexLine3;
                    i = i2;
                } else if (child.getVisibility() == 8) {
                    currentViewIndex2++;
                    j = j2;
                    flexLine2 = flexLine3;
                    i = i2;
                } else {
                    LayoutParams lp2 = (LayoutParams) child.getLayoutParams();
                    float childTop4 = childTop + lp2.topMargin;
                    float childBottom4 = childBottom - lp2.bottomMargin;
                    if (!hasDividerBeforeChildAtAlongMainAxis(currentViewIndex2, j2)) {
                        childTop2 = childTop4;
                        childBottom2 = childBottom4;
                    } else {
                        int i5 = this.mDividerHorizontalHeight;
                        childTop2 = childTop4 + i5;
                        childBottom2 = childBottom4 - i5;
                    }
                    if (isRtl) {
                        if (fromBottomToTop) {
                            int i6 = this.mAlignItems;
                            int j3 = childRight - child.getMeasuredWidth();
                            lp = lp2;
                            currentViewIndex = currentViewIndex2;
                            j = j2;
                            flexLine = flexLine3;
                            i = i2;
                            layoutSingleChildVertical(child, flexLine3, true, i6, j3, Math.round(childBottom2) - child.getMeasuredHeight(), childRight, Math.round(childBottom2));
                        } else {
                            lp = lp2;
                            currentViewIndex = currentViewIndex2;
                            j = j2;
                            flexLine = flexLine3;
                            i = i2;
                            layoutSingleChildVertical(child, flexLine, true, this.mAlignItems, childRight - child.getMeasuredWidth(), Math.round(childTop2), childRight, Math.round(childTop2) + child.getMeasuredHeight());
                        }
                    } else {
                        lp = lp2;
                        currentViewIndex = currentViewIndex2;
                        j = j2;
                        flexLine = flexLine3;
                        i = i2;
                        if (fromBottomToTop) {
                            layoutSingleChildVertical(child, flexLine, false, this.mAlignItems, childLeft, Math.round(childBottom2) - child.getMeasuredHeight(), childLeft + child.getMeasuredWidth(), Math.round(childBottom2));
                        } else {
                            layoutSingleChildVertical(child, flexLine, false, this.mAlignItems, childLeft, Math.round(childTop2), childLeft + child.getMeasuredWidth(), Math.round(childTop2) + child.getMeasuredHeight());
                        }
                    }
                    LayoutParams lp3 = lp;
                    currentViewIndex2 = currentViewIndex + 1;
                    flexLine2 = flexLine;
                    flexLine2.mLeft = Math.min(flexLine2.mLeft, child.getLeft() - lp3.leftMargin);
                    flexLine2.mTop = Math.min(flexLine2.mTop, child.getTop() - lp3.topMargin);
                    flexLine2.mRight = Math.max(flexLine2.mRight, child.getRight() + lp3.rightMargin);
                    flexLine2.mBottom = Math.max(flexLine2.mBottom, child.getBottom() + lp3.bottomMargin);
                    childTop = childTop2 + child.getMeasuredHeight() + spaceBetweenItem2 + lp3.bottomMargin;
                    childBottom = childBottom2 - ((child.getMeasuredHeight() + spaceBetweenItem2) + lp3.topMargin);
                }
                j2 = j + 1;
                flexLine3 = flexLine2;
                i2 = i;
            }
            FlexLine flexLine4 = flexLine3;
            int childLeft4 = childLeft + flexLine4.mCrossSize;
            i2++;
            childRight3 = childRight - flexLine4.mCrossSize;
            childRight2 = childLeft4;
            childLeft2 = currentViewIndex2;
        }
    }

    private void layoutSingleChildVertical(View view, FlexLine flexLine, boolean isRtl, int alignItems, int left, int top, int right, int bottom) {
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        if (lp.alignSelf != -1) {
            alignItems = lp.alignSelf;
        }
        int crossSize = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (!isRtl) {
                    view.layout(((left + crossSize) - view.getMeasuredWidth()) - lp.rightMargin, top, ((right + crossSize) - view.getMeasuredWidth()) - lp.rightMargin, bottom);
                    return;
                } else {
                    view.layout((left - crossSize) + view.getMeasuredWidth() + lp.leftMargin, top, (right - crossSize) + view.getMeasuredWidth() + lp.leftMargin, bottom);
                    return;
                }
            }
            if (alignItems == 2) {
                int leftFromCrossAxis = (((crossSize - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(lp)) - MarginLayoutParamsCompat.getMarginEnd(lp)) / 2;
                if (!isRtl) {
                    view.layout(left + leftFromCrossAxis, top, right + leftFromCrossAxis, bottom);
                    return;
                } else {
                    view.layout(left - leftFromCrossAxis, top, right - leftFromCrossAxis, bottom);
                    return;
                }
            }
            if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (!isRtl) {
            view.layout(lp.leftMargin + left, top, lp.leftMargin + right, bottom);
        } else {
            view.layout(left - lp.rightMargin, top, right - lp.rightMargin, bottom);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDividerDrawableVertical == null && this.mDividerDrawableHorizontal == null) {
            return;
        }
        if (this.mShowDividerHorizontal == 0 && this.mShowDividerVertical == 0) {
            return;
        }
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        boolean fromBottomToTop = false;
        int i = this.mFlexDirection;
        if (i == 0) {
            boolean isRtl = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                fromBottomToTop = true;
            }
            drawDividersHorizontal(canvas, isRtl, fromBottomToTop);
            return;
        }
        if (i == 1) {
            boolean isRtl2 = layoutDirection != 1;
            if (this.mFlexWrap == 2) {
                fromBottomToTop = true;
            }
            drawDividersHorizontal(canvas, isRtl2, fromBottomToTop);
            return;
        }
        if (i == 2) {
            boolean isRtl3 = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                isRtl3 = isRtl3 ? false : true;
            }
            drawDividersVertical(canvas, isRtl3, false);
            return;
        }
        if (i == 3) {
            boolean isRtl4 = layoutDirection == 1;
            if (this.mFlexWrap == 2) {
                isRtl4 = isRtl4 ? false : true;
            }
            drawDividersVertical(canvas, isRtl4, true);
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int horizontalDividerTop;
        int horizontalDividerTop2;
        int dividerLeft;
        int dividerLeft2;
        int currentViewIndex = 0;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int horizontalDividerLength = Math.max(0, (getWidth() - paddingRight) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                View view = getReorderedChildAt(currentViewIndex);
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (hasDividerBeforeChildAtAlongMainAxis(currentViewIndex, j)) {
                    if (isRtl) {
                        dividerLeft2 = view.getRight() + lp.rightMargin;
                    } else {
                        int dividerLeft3 = view.getLeft();
                        dividerLeft2 = (dividerLeft3 - lp.leftMargin) - this.mDividerVerticalWidth;
                    }
                    drawVerticalDivider(canvas, dividerLeft2, flexLine.mTop, flexLine.mCrossSize);
                }
                int dividerLeft4 = flexLine.mItemCount;
                if (j == dividerLeft4 - 1 && (this.mShowDividerVertical & 4) > 0) {
                    if (isRtl) {
                        dividerLeft = (view.getLeft() - lp.leftMargin) - this.mDividerVerticalWidth;
                    } else {
                        int dividerLeft5 = view.getRight();
                        dividerLeft = dividerLeft5 + lp.rightMargin;
                    }
                    drawVerticalDivider(canvas, dividerLeft, flexLine.mTop, flexLine.mCrossSize);
                }
                currentViewIndex++;
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (fromBottomToTop) {
                    horizontalDividerTop2 = flexLine.mBottom;
                } else {
                    int horizontalDividerTop3 = flexLine.mTop;
                    horizontalDividerTop2 = horizontalDividerTop3 - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop2, horizontalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerHorizontal & 4) > 0) {
                if (fromBottomToTop) {
                    horizontalDividerTop = flexLine.mTop - this.mDividerHorizontalHeight;
                } else {
                    horizontalDividerTop = flexLine.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, horizontalDividerTop, horizontalDividerLength);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean isRtl, boolean fromBottomToTop) {
        int verticalDividerLeft;
        int verticalDividerLeft2;
        int dividerTop;
        int dividerTop2;
        int currentViewIndex = 0;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int verticalDividerLength = Math.max(0, (getHeight() - paddingBottom) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            for (int j = 0; j < flexLine.mItemCount; j++) {
                View view = getReorderedChildAt(currentViewIndex);
                LayoutParams lp = (LayoutParams) view.getLayoutParams();
                if (hasDividerBeforeChildAtAlongMainAxis(currentViewIndex, j)) {
                    if (fromBottomToTop) {
                        dividerTop2 = view.getBottom() + lp.bottomMargin;
                    } else {
                        int dividerTop3 = view.getTop();
                        dividerTop2 = (dividerTop3 - lp.topMargin) - this.mDividerHorizontalHeight;
                    }
                    drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop2, flexLine.mCrossSize);
                }
                int dividerTop4 = flexLine.mItemCount;
                if (j == dividerTop4 - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                    if (fromBottomToTop) {
                        dividerTop = (view.getTop() - lp.topMargin) - this.mDividerHorizontalHeight;
                    } else {
                        int dividerTop5 = view.getBottom();
                        dividerTop = dividerTop5 + lp.bottomMargin;
                    }
                    drawHorizontalDivider(canvas, flexLine.mLeft, dividerTop, flexLine.mCrossSize);
                }
                currentViewIndex++;
            }
            if (hasDividerBeforeFlexLine(i)) {
                if (isRtl) {
                    verticalDividerLeft2 = flexLine.mRight;
                } else {
                    int verticalDividerLeft3 = flexLine.mLeft;
                    verticalDividerLeft2 = verticalDividerLeft3 - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, verticalDividerLeft2, paddingTop, verticalDividerLength);
            }
            if (hasEndDividerAfterFlexLine(i) && (this.mShowDividerVertical & 4) > 0) {
                if (isRtl) {
                    verticalDividerLeft = flexLine.mLeft - this.mDividerVerticalWidth;
                } else {
                    verticalDividerLeft = flexLine.mRight;
                }
                drawVerticalDivider(canvas, verticalDividerLeft, paddingTop, verticalDividerLength);
            }
        }
    }

    private void drawVerticalDivider(Canvas canvas, int left, int top, int length) {
        Drawable drawable = this.mDividerDrawableVertical;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(left, top, this.mDividerVerticalWidth + left, top + length);
        this.mDividerDrawableVertical.draw(canvas);
    }

    private void drawHorizontalDivider(Canvas canvas, int left, int top, int length) {
        Drawable drawable = this.mDividerDrawableHorizontal;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(left, top, left + length, this.mDividerHorizontalHeight + top);
        this.mDividerDrawableHorizontal.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int flexDirection) {
        if (this.mFlexDirection != flexDirection) {
            this.mFlexDirection = flexDirection;
            requestLayout();
        }
    }

    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int flexWrap) {
        if (this.mFlexWrap != flexWrap) {
            this.mFlexWrap = flexWrap;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public void setJustifyContent(int justifyContent) {
        if (this.mJustifyContent != justifyContent) {
            this.mJustifyContent = justifyContent;
            requestLayout();
        }
    }

    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int alignItems) {
        if (this.mAlignItems != alignItems) {
            this.mAlignItems = alignItems;
            requestLayout();
        }
    }

    public int getAlignContent() {
        return this.mAlignContent;
    }

    public void setAlignContent(int alignContent) {
        if (this.mAlignContent != alignContent) {
            this.mAlignContent = alignContent;
            requestLayout();
        }
    }

    public List<FlexLine> getFlexLines() {
        List<FlexLine> result = new ArrayList<>(this.mFlexLines.size());
        for (FlexLine flexLine : this.mFlexLines) {
            if (flexLine.getItemCount() != 0) {
                result.add(flexLine);
            }
        }
        return result;
    }

    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public void setDividerDrawable(Drawable divider) {
        setDividerDrawableHorizontal(divider);
        setDividerDrawableVertical(divider);
    }

    public void setDividerDrawableHorizontal(Drawable divider) {
        if (divider == this.mDividerDrawableHorizontal) {
            return;
        }
        this.mDividerDrawableHorizontal = divider;
        if (divider != null) {
            this.mDividerHorizontalHeight = divider.getIntrinsicHeight();
        } else {
            this.mDividerHorizontalHeight = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public void setDividerDrawableVertical(Drawable divider) {
        if (divider == this.mDividerDrawableVertical) {
            return;
        }
        this.mDividerDrawableVertical = divider;
        if (divider != null) {
            this.mDividerVerticalWidth = divider.getIntrinsicWidth();
        } else {
            this.mDividerVerticalWidth = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public void setShowDivider(int dividerMode) {
        setShowDividerVertical(dividerMode);
        setShowDividerHorizontal(dividerMode);
    }

    public void setShowDividerVertical(int dividerMode) {
        if (dividerMode != this.mShowDividerVertical) {
            this.mShowDividerVertical = dividerMode;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int dividerMode) {
        if (dividerMode != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = dividerMode;
            requestLayout();
        }
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int childAbsoluteIndex, int childRelativeIndexInFlexLine) {
        return allViewsAreGoneBefore(childAbsoluteIndex, childRelativeIndexInFlexLine) ? isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerVertical & 1) != 0 : (this.mShowDividerHorizontal & 1) != 0 : isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerVertical & 2) != 0 : (this.mShowDividerHorizontal & 2) != 0;
    }

    private boolean allViewsAreGoneBefore(int childAbsoluteIndex, int childRelativeIndexInFlexLine) {
        for (int i = 1; i <= childRelativeIndexInFlexLine; i++) {
            View view = getReorderedChildAt(childAbsoluteIndex - i);
            if (view != null && view.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDividerBeforeFlexLine(int flexLineIndex) {
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        return allFlexLinesAreDummyBefore(flexLineIndex) ? isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerHorizontal & 1) != 0 : (this.mShowDividerVertical & 1) != 0 : isMainAxisDirectionHorizontal(this.mFlexDirection) ? (this.mShowDividerHorizontal & 2) != 0 : (this.mShowDividerVertical & 2) != 0;
    }

    private boolean allFlexLinesAreDummyBefore(int flexLineIndex) {
        for (int i = 0; i < flexLineIndex; i++) {
            if (this.mFlexLines.get(i).mItemCount > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasEndDividerAfterFlexLine(int flexLineIndex) {
        if (flexLineIndex < 0 || flexLineIndex >= this.mFlexLines.size()) {
            return false;
        }
        for (int i = flexLineIndex + 1; i < this.mFlexLines.size(); i++) {
            if (this.mFlexLines.get(i).mItemCount > 0) {
                return false;
            }
        }
        int i2 = this.mFlexDirection;
        return isMainAxisDirectionHorizontal(i2) ? (this.mShowDividerHorizontal & 4) != 0 : (this.mShowDividerVertical & 4) != 0;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$LayoutParams.smali */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int ALIGN_SELF_AUTO = -1;
        public static final int ALIGN_SELF_BASELINE = 3;
        public static final int ALIGN_SELF_CENTER = 2;
        public static final int ALIGN_SELF_FLEX_END = 1;
        public static final int ALIGN_SELF_FLEX_START = 0;
        public static final int ALIGN_SELF_STRETCH = 4;
        public static final float FLEX_BASIS_PERCENT_DEFAULT = -1.0f;
        private static final float FLEX_GROW_DEFAULT = 0.0f;
        private static final float FLEX_SHRINK_DEFAULT = 1.0f;
        private static final int MAX_SIZE = 16777215;
        private static final int ORDER_DEFAULT = 1;
        public int alignSelf;
        public float flexBasisPercent;
        public float flexGrow;
        public float flexShrink;
        public int maxHeight;
        public int maxWidth;
        public int minHeight;
        public int minWidth;
        public int order;
        public boolean wrapBefore;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlexboxLayout_Layout);
            this.order = a.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.flexGrow = a.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.flexShrink = a.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.alignSelf = a.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.flexBasisPercent = a.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.minWidth = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.minHeight = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.maxWidth = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.maxHeight = a.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.wrapBefore = a.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            a.recycle();
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
            this.order = source.order;
            this.flexGrow = source.flexGrow;
            this.flexShrink = source.flexShrink;
            this.alignSelf = source.alignSelf;
            this.flexBasisPercent = source.flexBasisPercent;
            this.minWidth = source.minWidth;
            this.minHeight = source.minHeight;
            this.maxWidth = source.maxWidth;
            this.maxHeight = source.maxHeight;
            this.wrapBefore = source.wrapBefore;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
        }

        public LayoutParams(int width, int height) {
            super(new ViewGroup.LayoutParams(width, height));
            this.order = 1;
            this.flexGrow = 0.0f;
            this.flexShrink = 1.0f;
            this.alignSelf = -1;
            this.flexBasisPercent = -1.0f;
            this.maxWidth = 16777215;
            this.maxHeight = 16777215;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\flexbox\FlexboxLayout$Order.smali */
    private static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        @Override // java.lang.Comparable
        public int compareTo(Order another) {
            int i = this.order;
            int i2 = another.order;
            if (i != i2) {
                return i - i2;
            }
            return this.index - another.index;
        }

        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }
    }
}
