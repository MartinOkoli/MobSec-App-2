package infosecadventures.allsafe.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import infosecadventures.allsafe.Constants;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.ClipUtil;
import infosecadventures.allsafe.utils.SnackUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: About.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"Linfosecadventures/allsafe/about/About;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\about\About.smali */
public final class About extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        View viewFindViewById = view.findViewById(R.id.version);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById<TextView>(R.id.version)");
        ((TextView) viewFindViewById).setText("Version: 1.4");
        ((LinearLayout) view.findViewById(R.id.blog)).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.about.About.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Constants.MEDIUM)));
            }
        });
        ((LinearLayout) view.findViewById(R.id.github)).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.about.About.onCreateView.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Constants.GITHUB)));
            }
        });
        ((LinearLayout) view.findViewById(R.id.twitter)).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.about.About.onCreateView.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Constants.TWITTER)));
            }
        });
        ((LinearLayout) view.findViewById(R.id.bitcoin)).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.about.About.onCreateView.4
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ClipUtil clipUtil = ClipUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = About.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                clipUtil.copyToClipboard(fragmentActivityRequireActivity, Constants.BTC_ADDRESS);
                SnackUtil snackUtil = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity2 = About.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
                snackUtil.simpleMessage(fragmentActivityRequireActivity2, "Bitcoin address copied to clipboard!");
            }
        });
        ((LinearLayout) view.findViewById(R.id.ethereum)).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.about.About.onCreateView.5
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                ClipUtil clipUtil = ClipUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = About.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                clipUtil.copyToClipboard(fragmentActivityRequireActivity, Constants.ETH_ADDRESS);
                SnackUtil snackUtil = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity2 = About.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
                snackUtil.simpleMessage(fragmentActivityRequireActivity2, "Ethereum address copied to clipboard!");
            }
        });
        return view;
    }
}
