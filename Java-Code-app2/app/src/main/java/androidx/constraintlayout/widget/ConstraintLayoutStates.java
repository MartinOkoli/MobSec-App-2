package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\widget\ConstraintLayoutStates.smali */
public class ConstraintLayoutStates {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    private final ConstraintLayout mConstraintLayout;
    ConstraintSet mDefaultConstraintSet;
    int mCurrentStateId = -1;
    int mCurrentConstraintNumber = -1;
    private SparseArray<State> mStateList = new SparseArray<>();
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;

    ConstraintLayoutStates(Context context, ConstraintLayout layout, int resourceID) throws XmlPullParserException, Resources.NotFoundException, IOException, NumberFormatException {
        this.mConstraintLayout = layout;
        load(context, resourceID);
    }

    public boolean needsToChange(int id, float width, float height) {
        int i = this.mCurrentStateId;
        if (i != id) {
            return true;
        }
        State state = id == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i);
        return (this.mCurrentConstraintNumber == -1 || !state.mVariants.get(this.mCurrentConstraintNumber).match(width, height)) && this.mCurrentConstraintNumber != state.findMatch(width, height);
    }

    public void updateConstraints(int id, float width, float height) {
        State state;
        int match;
        int i = this.mCurrentStateId;
        if (i == id) {
            if (id == -1) {
                state = this.mStateList.valueAt(0);
            } else {
                state = this.mStateList.get(i);
            }
            if ((this.mCurrentConstraintNumber != -1 && state.mVariants.get(this.mCurrentConstraintNumber).match(width, height)) || this.mCurrentConstraintNumber == (match = state.findMatch(width, height))) {
                return;
            }
            ConstraintSet constraintSet = match == -1 ? this.mDefaultConstraintSet : state.mVariants.get(match).mConstraintSet;
            int cid = match == -1 ? state.mConstraintID : state.mVariants.get(match).mConstraintID;
            if (constraintSet == null) {
                return;
            }
            this.mCurrentConstraintNumber = match;
            ConstraintsChangedListener constraintsChangedListener = this.mConstraintsChangedListener;
            if (constraintsChangedListener != null) {
                constraintsChangedListener.preLayoutChange(-1, cid);
            }
            constraintSet.applyTo(this.mConstraintLayout);
            ConstraintsChangedListener constraintsChangedListener2 = this.mConstraintsChangedListener;
            if (constraintsChangedListener2 != null) {
                constraintsChangedListener2.postLayoutChange(-1, cid);
                return;
            }
            return;
        }
        this.mCurrentStateId = id;
        State state2 = this.mStateList.get(id);
        int match2 = state2.findMatch(width, height);
        ConstraintSet constraintSet2 = match2 == -1 ? state2.mConstraintSet : state2.mVariants.get(match2).mConstraintSet;
        int cid2 = match2 == -1 ? state2.mConstraintID : state2.mVariants.get(match2).mConstraintID;
        if (constraintSet2 == null) {
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + id + ", dim =" + width + ", " + height);
            return;
        }
        this.mCurrentConstraintNumber = match2;
        ConstraintsChangedListener constraintsChangedListener3 = this.mConstraintsChangedListener;
        if (constraintsChangedListener3 != null) {
            constraintsChangedListener3.preLayoutChange(id, cid2);
        }
        constraintSet2.applyTo(this.mConstraintLayout);
        ConstraintsChangedListener constraintsChangedListener4 = this.mConstraintsChangedListener;
        if (constraintsChangedListener4 != null) {
            constraintsChangedListener4.postLayoutChange(id, cid2);
        }
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\widget\ConstraintLayoutStates$State.smali */
    static class State {
        int mConstraintID;
        ConstraintSet mConstraintSet;
        int mId;
        ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser parser) throws Resources.NotFoundException {
            this.mConstraintID = -1;
            AttributeSet attrs = Xml.asAttributeSet(parser);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.State);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.State_android_id) {
                    this.mId = a.getResourceId(attr, this.mId);
                } else if (attr == R.styleable.State_constraints) {
                    this.mConstraintID = a.getResourceId(attr, this.mConstraintID);
                    String type = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(type)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
                    }
                }
            }
            a.recycle();
        }

        void add(Variant size) {
            this.mVariants.add(size);
        }

        public int findMatch(float width, float height) {
            for (int i = 0; i < this.mVariants.size(); i++) {
                if (this.mVariants.get(i).match(width, height)) {
                    return i;
                }
            }
            return -1;
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\widget\ConstraintLayoutStates$Variant.smali */
    static class Variant {
        int mConstraintID;
        ConstraintSet mConstraintSet;
        int mId;
        float mMaxHeight;
        float mMaxWidth;
        float mMinHeight;
        float mMinWidth;

        public Variant(Context context, XmlPullParser parser) throws Resources.NotFoundException {
            this.mMinWidth = Float.NaN;
            this.mMinHeight = Float.NaN;
            this.mMaxWidth = Float.NaN;
            this.mMaxHeight = Float.NaN;
            this.mConstraintID = -1;
            AttributeSet attrs = Xml.asAttributeSet(parser);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Variant);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.Variant_constraints) {
                    this.mConstraintID = a.getResourceId(attr, this.mConstraintID);
                    String type = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(type)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.mConstraintSet = constraintSet;
                        constraintSet.clone(context, this.mConstraintID);
                    }
                } else if (attr == R.styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = a.getDimension(attr, this.mMaxHeight);
                } else if (attr == R.styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = a.getDimension(attr, this.mMinHeight);
                } else if (attr == R.styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = a.getDimension(attr, this.mMaxWidth);
                } else if (attr == R.styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = a.getDimension(attr, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            a.recycle();
        }

        boolean match(float widthDp, float heightDp) {
            if (!Float.isNaN(this.mMinWidth) && widthDp < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && heightDp < this.mMinHeight) {
                return false;
            }
            if (Float.isNaN(this.mMaxWidth) || widthDp <= this.mMaxWidth) {
                return Float.isNaN(this.mMaxHeight) || heightDp <= this.mMaxHeight;
            }
            return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void load(android.content.Context r13, int r14) throws org.xmlpull.v1.XmlPullParserException, android.content.res.Resources.NotFoundException, java.io.IOException, java.lang.NumberFormatException {
        /*
            r12 = this;
            android.content.res.Resources r0 = r13.getResources()
            android.content.res.XmlResourceParser r1 = r0.getXml(r14)
            r2 = 0
            r3 = 0
            r4 = 0
            int r5 = r1.getEventType()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
        Lf:
            r6 = 1
            if (r5 == r6) goto Lae
            if (r5 == 0) goto La1
            r7 = 3
            r8 = 2
            if (r5 == r8) goto L1f
            if (r5 == r7) goto L1c
            goto La7
        L1c:
            r3 = 0
            goto La7
        L1f:
            java.lang.String r9 = r1.getName()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r3 = r9
            r9 = -1
            int r10 = r3.hashCode()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r11 = 4
            switch(r10) {
                case -1349929691: goto L56;
                case 80204913: goto L4c;
                case 1382829617: goto L42;
                case 1657696882: goto L38;
                case 1901439077: goto L2e;
                default: goto L2d;
            }     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
        L2d:
            goto L5f
        L2e:
            java.lang.String r10 = "Variant"
            boolean r10 = r3.equals(r10)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            if (r10 == 0) goto L2d
            r9 = r7
            goto L5f
        L38:
            java.lang.String r10 = "layoutDescription"
            boolean r10 = r3.equals(r10)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            if (r10 == 0) goto L2d
            r9 = 0
            goto L5f
        L42:
            java.lang.String r10 = "StateSet"
            boolean r10 = r3.equals(r10)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            if (r10 == 0) goto L2d
            r9 = r6
            goto L5f
        L4c:
            java.lang.String r10 = "State"
            boolean r10 = r3.equals(r10)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            if (r10 == 0) goto L2d
            r9 = r8
            goto L5f
        L56:
            java.lang.String r10 = "ConstraintSet"
            boolean r10 = r3.equals(r10)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            if (r10 == 0) goto L2d
            r9 = r11
        L5f:
            if (r9 == 0) goto L9f
            if (r9 == r6) goto L9e
            if (r9 == r8) goto L90
            if (r9 == r7) goto L85
            if (r9 == r11) goto L81
            java.lang.String r6 = "ConstraintLayoutStates"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r7.<init>()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            java.lang.String r8 = "unknown tag "
            r7.append(r8)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r7.append(r3)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            java.lang.String r7 = r7.toString()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            android.util.Log.v(r6, r7)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            goto La0
        L81:
            r12.parseConstraintSet(r13, r1)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            goto La0
        L85:
            androidx.constraintlayout.widget.ConstraintLayoutStates$Variant r6 = new androidx.constraintlayout.widget.ConstraintLayoutStates$Variant     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r6.<init>(r13, r1)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            if (r4 == 0) goto La0
            r4.add(r6)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            goto La0
        L90:
            androidx.constraintlayout.widget.ConstraintLayoutStates$State r6 = new androidx.constraintlayout.widget.ConstraintLayoutStates$State     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r6.<init>(r13, r1)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r4 = r6
            android.util.SparseArray<androidx.constraintlayout.widget.ConstraintLayoutStates$State> r6 = r12.mStateList     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            int r7 = r4.mId     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r6.put(r7, r4)     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            goto La0
        L9e:
            goto La0
        L9f:
        La0:
            goto La7
        La1:
            java.lang.String r6 = r1.getName()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r2 = r6
        La7:
            int r6 = r1.next()     // Catch: java.io.IOException -> Laf org.xmlpull.v1.XmlPullParserException -> Lb4
            r5 = r6
            goto Lf
        Lae:
            goto Lb8
        Laf:
            r4 = move-exception
            r4.printStackTrace()
            goto Lb9
        Lb4:
            r4 = move-exception
            r4.printStackTrace()
        Lb8:
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayoutStates.load(android.content.Context, int):void");
    }

    private void parseConstraintSet(Context context, XmlPullParser parser) throws NumberFormatException {
        ConstraintSet set = new ConstraintSet();
        int count = parser.getAttributeCount();
        for (int i = 0; i < count; i++) {
            if ("id".equals(parser.getAttributeName(i))) {
                String s = parser.getAttributeValue(i);
                int id = -1;
                if (s.contains("/")) {
                    String tmp = s.substring(s.indexOf(47) + 1);
                    id = context.getResources().getIdentifier(tmp, "id", context.getPackageName());
                }
                if (id == -1) {
                    if (s != null && s.length() > 1) {
                        id = Integer.parseInt(s.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                set.load(context, parser);
                this.mConstraintSetMap.put(id, set);
                return;
            }
        }
    }
}
