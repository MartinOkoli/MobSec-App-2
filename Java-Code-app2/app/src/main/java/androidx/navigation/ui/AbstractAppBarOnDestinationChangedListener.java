package androidx.navigation.ui;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.app.NotificationCompat;
import androidx.customview.widget.Openable;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\ui\AbstractAppBarOnDestinationChangedListener.smali */
abstract class AbstractAppBarOnDestinationChangedListener implements NavController.OnDestinationChangedListener {
    private ValueAnimator mAnimator;
    private DrawerArrowDrawable mArrowDrawable;
    private final Context mContext;
    private final WeakReference<Openable> mOpenableLayoutWeakReference;
    private final Set<Integer> mTopLevelDestinations;

    protected abstract void setNavigationIcon(Drawable drawable, int i);

    protected abstract void setTitle(CharSequence charSequence);

    AbstractAppBarOnDestinationChangedListener(Context context, AppBarConfiguration configuration) {
        this.mContext = context;
        this.mTopLevelDestinations = configuration.getTopLevelDestinations();
        Openable openableLayout = configuration.getOpenableLayout();
        if (openableLayout != null) {
            this.mOpenableLayoutWeakReference = new WeakReference<>(openableLayout);
        } else {
            this.mOpenableLayoutWeakReference = null;
        }
    }

    @Override // androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
        Openable openableLayout;
        if (destination instanceof FloatingWindow) {
            return;
        }
        WeakReference<Openable> weakReference = this.mOpenableLayoutWeakReference;
        if (weakReference != null) {
            openableLayout = weakReference.get();
        } else {
            openableLayout = null;
        }
        if (this.mOpenableLayoutWeakReference != null && openableLayout == null) {
            controller.removeOnDestinationChangedListener(this);
            return;
        }
        CharSequence label = destination.getLabel();
        boolean z = true;
        if (label != null) {
            StringBuffer title = new StringBuffer();
            Pattern fillInPattern = Pattern.compile("\\{(.+?)\\}");
            Matcher matcher = fillInPattern.matcher(label);
            while (matcher.find()) {
                String argName = matcher.group(1);
                if (arguments != null && arguments.containsKey(argName)) {
                    matcher.appendReplacement(title, "");
                    title.append(arguments.get(argName).toString());
                } else {
                    throw new IllegalArgumentException("Could not find " + argName + " in " + arguments + " to fill label " + ((Object) label));
                }
            }
            matcher.appendTail(title);
            setTitle(title);
        }
        boolean isTopLevelDestination = NavigationUI.matchDestinations(destination, this.mTopLevelDestinations);
        if (openableLayout == null && isTopLevelDestination) {
            setNavigationIcon(null, 0);
            return;
        }
        if (openableLayout == null || !isTopLevelDestination) {
            z = false;
        }
        setActionBarUpIndicator(z);
    }

    private void setActionBarUpIndicator(boolean showAsDrawerIndicator) {
        int i;
        boolean animate = true;
        if (this.mArrowDrawable == null) {
            this.mArrowDrawable = new DrawerArrowDrawable(this.mContext);
            animate = false;
        }
        DrawerArrowDrawable drawerArrowDrawable = this.mArrowDrawable;
        if (showAsDrawerIndicator) {
            i = R.string.nav_app_bar_open_drawer_description;
        } else {
            i = R.string.nav_app_bar_navigate_up_description;
        }
        setNavigationIcon(drawerArrowDrawable, i);
        float endValue = showAsDrawerIndicator ? 0.0f : 1.0f;
        if (animate) {
            float startValue = this.mArrowDrawable.getProgress();
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.mArrowDrawable, NotificationCompat.CATEGORY_PROGRESS, startValue, endValue);
            this.mAnimator = objectAnimatorOfFloat;
            objectAnimatorOfFloat.start();
            return;
        }
        this.mArrowDrawable.setProgress(endValue);
    }
}
