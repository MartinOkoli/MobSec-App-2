package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\SmaliPatch.smali */
public class SmaliPatch extends Fragment {

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\SmaliPatch$Firewall.smali */
    public enum Firewall {
        ACTIVE,
        INACTIVE
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smali_patch, container, false);
        final Firewall firewall = Firewall.INACTIVE;
        Button check = (Button) view.findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$SmaliPatch$j9OKxHnrQTxs2rjDby2oMr2fUsc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$SmaliPatch(firewall, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$SmaliPatch(Firewall firewall, View v) {
        if (firewall.equals(Firewall.ACTIVE)) {
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Firewall is now activated, good job! 👍");
            Toast.makeText(requireContext(), "GOOD JOB!", 1).show();
        } else {
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Firewall is down, try harder!");
        }
    }
}
