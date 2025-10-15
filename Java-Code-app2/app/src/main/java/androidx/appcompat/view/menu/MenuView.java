package androidx.appcompat.view.menu;

import android.graphics.drawable.Drawable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\view\menu\MenuView.smali */
public interface MenuView {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\view\menu\MenuView$ItemView.smali */
    public interface ItemView {
        MenuItemImpl getItemData();

        void initialize(MenuItemImpl menuItemImpl, int i);

        boolean prefersCondensedTitle();

        void setCheckable(boolean z);

        void setChecked(boolean z);

        void setEnabled(boolean z);

        void setIcon(Drawable drawable);

        void setShortcut(boolean z, char c);

        void setTitle(CharSequence charSequence);

        boolean showsIcon();
    }

    int getWindowAnimations();

    void initialize(MenuBuilder menuBuilder);
}
