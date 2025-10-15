package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.util.Base64;
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
import kotlin.text.Charsets;

/* compiled from: PinBypass.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Linfosecadventures/allsafe/challenges/PinBypass;", "Landroidx/fragment/app/Fragment;", "()V", "checkPin", "", "pin", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\PinBypass.smali */
public final class PinBypass extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_pin_bypass, container, false);
        final EditText pin = (EditText) view.findViewById(2131296612);
        view.findViewById(R.id.check).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.PinBypass.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                EditText pin2 = pin;
                Intrinsics.checkNotNullExpressionValue(pin2, "pin");
                if (!(pin2.getText().toString().length() == 0)) {
                    PinBypass pinBypass = PinBypass.this;
                    EditText pin3 = pin;
                    Intrinsics.checkNotNullExpressionValue(pin3, "pin");
                    if (pinBypass.checkPin(pin3.getText().toString())) {
                        SnackUtil snackUtil = SnackUtil.INSTANCE;
                        FragmentActivity fragmentActivityRequireActivity = PinBypass.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                        snackUtil.simpleMessage(fragmentActivityRequireActivity, "Access granted, good job!");
                        return;
                    }
                }
                SnackUtil snackUtil2 = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity2 = PinBypass.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
                snackUtil2.simpleMessage(fragmentActivityRequireActivity2, "Incorrect PIN, try harder!");
            }
        });
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkPin(String pin) {
        byte[] bArrDecode = Base64.decode("NDg2Mw==", 0);
        Intrinsics.checkNotNullExpressionValue(bArrDecode, "android.util.Base64.deco…roid.util.Base64.DEFAULT)");
        return Intrinsics.areEqual(pin, new String(bArrDecode, Charsets.UTF_8));
    }
}
