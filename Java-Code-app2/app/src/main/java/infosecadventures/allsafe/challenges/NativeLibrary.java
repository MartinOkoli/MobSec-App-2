package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NativeLibrary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0082 J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u0010"}, d2 = {"Linfosecadventures/allsafe/challenges/NativeLibrary;", "Landroidx/fragment/app/Fragment;", "()V", "checkPassword", "", "password", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\NativeLibrary.smali */
public final class NativeLibrary extends Fragment {
    /* JADX INFO: Access modifiers changed from: private */
    public final native boolean checkPassword(String password);

    static {
        System.loadLibrary("native_library");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_native_library, container, false);
        final EditText password = (EditText) view.findViewById(R.id.password);
        view.findViewById(R.id.check).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.NativeLibrary.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                EditText password2 = password;
                Intrinsics.checkNotNullExpressionValue(password2, "password");
                if (!(password2.getText().toString().length() == 0)) {
                    NativeLibrary nativeLibrary = NativeLibrary.this;
                    EditText password3 = password;
                    Intrinsics.checkNotNullExpressionValue(password3, "password");
                    if (nativeLibrary.checkPassword(password3.getText().toString())) {
                        SnackUtil snackUtil = SnackUtil.INSTANCE;
                        FragmentActivity fragmentActivityRequireActivity = NativeLibrary.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                        snackUtil.simpleMessage(fragmentActivityRequireActivity, "That's it! Excellent work!");
                        return;
                    }
                }
                SnackUtil snackUtil2 = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity2 = NativeLibrary.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
                snackUtil2.simpleMessage(fragmentActivityRequireActivity2, "Wrong password, try harder!");
            }
        });
        return view;
    }
}
