package infosecadventures.allsafe.challenges;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import infosecadventures.allsafe.R;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\InsecureService.smali */
public class InsecureService extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_service, container, false);
        view.findViewById(2131296684).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$InsecureService$iymH2PUit45weJQyVVE99mQziqc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$InsecureService(view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$InsecureService(View v) {
        if (ActivityCompat.checkSelfPermission(requireActivity(), "android.permission.RECORD_AUDIO") != 0 && ActivityCompat.checkSelfPermission(requireActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0 && ActivityCompat.checkSelfPermission(requireActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{"android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
        } else {
            requireActivity().startService(new Intent(requireActivity(), (Class<?>) RecorderService.class));
        }
    }
}
