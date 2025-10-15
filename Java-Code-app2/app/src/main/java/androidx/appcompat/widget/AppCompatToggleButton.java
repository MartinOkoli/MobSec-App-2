package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\appcompat\widget\AppCompatToggleButton.smali */
public class AppCompatToggleButton extends ToggleButton {
    private final AppCompatTextHelper mTextHelper;

    public AppCompatToggleButton(Context context) {
        this(context, null);
    }

    public AppCompatToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyleToggle);
    }

    public AppCompatToggleButton(Context context, AttributeSet attrs, int defStyleAttr) throws Resources.NotFoundException, IllegalArgumentException {
        super(context, attrs, defStyleAttr);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper;
        appCompatTextHelper.loadFromAttributes(attrs, defStyleAttr);
    }
}
