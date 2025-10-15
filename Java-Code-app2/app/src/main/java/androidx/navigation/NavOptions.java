package androidx.navigation;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavOptions.smali */
public final class NavOptions {
    private int mEnterAnim;
    private int mExitAnim;
    private int mPopEnterAnim;
    private int mPopExitAnim;
    private int mPopUpTo;
    private boolean mPopUpToInclusive;
    private boolean mSingleTop;

    NavOptions(boolean singleTop, int popUpTo, boolean popUpToInclusive, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {
        this.mSingleTop = singleTop;
        this.mPopUpTo = popUpTo;
        this.mPopUpToInclusive = popUpToInclusive;
        this.mEnterAnim = enterAnim;
        this.mExitAnim = exitAnim;
        this.mPopEnterAnim = popEnterAnim;
        this.mPopExitAnim = popExitAnim;
    }

    public boolean shouldLaunchSingleTop() {
        return this.mSingleTop;
    }

    public int getPopUpTo() {
        return this.mPopUpTo;
    }

    public boolean isPopUpToInclusive() {
        return this.mPopUpToInclusive;
    }

    public int getEnterAnim() {
        return this.mEnterAnim;
    }

    public int getExitAnim() {
        return this.mExitAnim;
    }

    public int getPopEnterAnim() {
        return this.mPopEnterAnim;
    }

    public int getPopExitAnim() {
        return this.mPopExitAnim;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NavOptions that = (NavOptions) o;
        if (this.mSingleTop == that.mSingleTop && this.mPopUpTo == that.mPopUpTo && this.mPopUpToInclusive == that.mPopUpToInclusive && this.mEnterAnim == that.mEnterAnim && this.mExitAnim == that.mExitAnim && this.mPopEnterAnim == that.mPopEnterAnim && this.mPopExitAnim == that.mPopExitAnim) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((shouldLaunchSingleTop() ? 1 : 0) * 31) + getPopUpTo()) * 31) + (isPopUpToInclusive() ? 1 : 0)) * 31) + getEnterAnim()) * 31) + getExitAnim()) * 31) + getPopEnterAnim()) * 31) + getPopExitAnim();
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavOptions$Builder.smali */
    public static final class Builder {
        boolean mPopUpToInclusive;
        boolean mSingleTop;
        int mPopUpTo = -1;
        int mEnterAnim = -1;
        int mExitAnim = -1;
        int mPopEnterAnim = -1;
        int mPopExitAnim = -1;

        public Builder setLaunchSingleTop(boolean singleTop) {
            this.mSingleTop = singleTop;
            return this;
        }

        public Builder setPopUpTo(int destinationId, boolean inclusive) {
            this.mPopUpTo = destinationId;
            this.mPopUpToInclusive = inclusive;
            return this;
        }

        public Builder setEnterAnim(int enterAnim) {
            this.mEnterAnim = enterAnim;
            return this;
        }

        public Builder setExitAnim(int exitAnim) {
            this.mExitAnim = exitAnim;
            return this;
        }

        public Builder setPopEnterAnim(int popEnterAnim) {
            this.mPopEnterAnim = popEnterAnim;
            return this;
        }

        public Builder setPopExitAnim(int popExitAnim) {
            this.mPopExitAnim = popExitAnim;
            return this;
        }

        public NavOptions build() {
            return new NavOptions(this.mSingleTop, this.mPopUpTo, this.mPopUpToInclusive, this.mEnterAnim, this.mExitAnim, this.mPopEnterAnim, this.mPopExitAnim);
        }
    }
}
