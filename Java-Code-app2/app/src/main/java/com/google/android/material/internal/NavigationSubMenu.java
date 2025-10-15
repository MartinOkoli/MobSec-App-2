package com.google.android.material.internal;

import android.content.Context;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\android\material\internal\NavigationSubMenu.smali */
public class NavigationSubMenu extends SubMenuBuilder {
    public NavigationSubMenu(Context context, NavigationMenu menu, MenuItemImpl item) {
        super(context, menu, item);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder
    public void onItemsChanged(boolean structureChanged) {
        super.onItemsChanged(structureChanged);
        ((MenuBuilder) getParentMenu()).onItemsChanged(structureChanged);
    }
}
