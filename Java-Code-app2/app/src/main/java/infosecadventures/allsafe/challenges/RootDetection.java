package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.scottyab.rootbeer.RootBeer;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RootDetection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Linfosecadventures/allsafe/challenges/RootDetection;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\RootDetection.smali */
public final class RootDetection extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_root_detection, container, false);
        view.findViewById(R.id.check).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.RootDetection.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                if (new RootBeer(RootDetection.this.getContext()).isRooted()) {
                    SnackUtil snackUtil = SnackUtil.INSTANCE;
                    FragmentActivity fragmentActivityRequireActivity = RootDetection.this.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                    snackUtil.simpleMessage(fragmentActivityRequireActivity, "Sorry, your device is rooted!");
                    return;
                }
                SnackUtil snackUtil2 = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity2 = RootDetection.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
                snackUtil2.simpleMessage(fragmentActivityRequireActivity2, "Congrats, root is not detected!");
            }
        });
        return view;
    }
}
