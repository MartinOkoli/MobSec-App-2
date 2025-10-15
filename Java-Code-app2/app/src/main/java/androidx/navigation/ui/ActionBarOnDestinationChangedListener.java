package androidx.navigation.ui;

import android.graphics.drawable.Drawable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\ui\ActionBarOnDestinationChangedListener.smali */
class ActionBarOnDestinationChangedListener extends AbstractAppBarOnDestinationChangedListener {
    private final AppCompatActivity mActivity;

    ActionBarOnDestinationChangedListener(AppCompatActivity activity, AppBarConfiguration configuration) {
        super(activity.getDrawerToggleDelegate().getActionBarThemedContext(), configuration);
        this.mActivity = activity;
    }

    @Override // androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener
    protected void setTitle(CharSequence title) {
        ActionBar actionBar = this.mActivity.getSupportActionBar();
        actionBar.setTitle(title);
    }

    @Override // androidx.navigation.ui.AbstractAppBarOnDestinationChangedListener
    protected void setNavigationIcon(Drawable icon, int contentDescription) {
        ActionBar actionBar = this.mActivity.getSupportActionBar();
        if (icon == null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle.Delegate delegate = this.mActivity.getDrawerToggleDelegate();
        delegate.setActionBarUpIndicator(icon, contentDescription);
    }
}
