package infosecadventures.allsafe.challenges;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\InsecureBroadcastReceiver.smali */
public class InsecureBroadcastReceiver extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_broadcast_receiver, container, false);
        final TextInputEditText note = (TextInputEditText) view.findViewById(R.id.note);
        Button save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$InsecureBroadcastReceiver$5lTLCG-eKUgLPxH5YjaZt2HPfuk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$InsecureBroadcastReceiver(note, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$InsecureBroadcastReceiver(TextInputEditText note, View v) {
        if (!note.getText().toString().isEmpty()) {
            Intent intent = new Intent();
            intent.setAction("infosecadventures.allsafe.action.PROCESS_NOTE");
            intent.putExtra("server", "prod.allsafe.infosecadventures.io");
            intent.putExtra("note", note.getText().toString());
            intent.putExtra("notification_message", "Allsafe is processing your note...");
            PackageManager packageManager = requireActivity().getPackageManager();
            List<ResolveInfo> resolveInfos = packageManager.queryBroadcastReceivers(intent, 0);
            for (ResolveInfo info : resolveInfos) {
                ComponentName cn = new ComponentName(info.activityInfo.packageName, info.activityInfo.name);
                intent.setComponent(cn);
                requireActivity().sendBroadcast(intent);
            }
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Saving note...");
            return;
        }
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "The note field can't be empty!");
    }
}
