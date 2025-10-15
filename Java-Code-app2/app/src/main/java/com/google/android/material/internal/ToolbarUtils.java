package com.google.android.material.internal;

import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\internal\ToolbarUtils.smali */
public class ToolbarUtils {
    private ToolbarUtils() {
    }

    public static View getSecondaryActionMenuItemView(Toolbar toolbar) {
        ActionMenuView actionMenuView = getActionMenuView(toolbar);
        if (actionMenuView != null && actionMenuView.getChildCount() > 1) {
            return actionMenuView.getChildAt(0);
        }
        return null;
    }

    public static ActionMenuView getActionMenuView(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View child = toolbar.getChildAt(i);
            if (child instanceof ActionMenuView) {
                return (ActionMenuView) child;
            }
        }
        return null;
    }

    public static ImageButton getNavigationIconButton(Toolbar toolbar) {
        if (toolbar.getChildCount() > 0) {
            View child = toolbar.getChildAt(0);
            if (child instanceof ImageButton) {
                return (ImageButton) child;
            }
            return null;
        }
        return null;
    }

    public static ActionMenuItemView getActionMenuItemView(Toolbar toolbar, int menuItemId) {
        ActionMenuView actionMenuView = getActionMenuView(toolbar);
        if (actionMenuView != null) {
            for (int i = 0; i < actionMenuView.getChildCount(); i++) {
                View child = actionMenuView.getChildAt(i);
                if (child instanceof ActionMenuItemView) {
                    ActionMenuItemView actionMenuItemView = (ActionMenuItemView) child;
                    if (actionMenuItemView.getItemData().getItemId() == menuItemId) {
                        return actionMenuItemView;
                    }
                }
            }
            return null;
        }
        return null;
    }
}
