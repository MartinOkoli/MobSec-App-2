package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import infosecadventures.allsafe.R;
import java.util.Objects;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\InsecureLogging.smali */
public class InsecureLogging extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_logging, container, false);
        setHasOptionsMenu(true);
        final TextInputEditText secret = (TextInputEditText) view.findViewById(R.id.secret);
        secret.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$InsecureLogging$dJzOqz8udy_kOEjW2jTqOEk7ckc
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return InsecureLogging.lambda$onCreateView$0(secret, textView, i, keyEvent);
            }
        });
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(TextInputEditText secret, TextView v, int actionId, KeyEvent event) {
        if (actionId != 6) {
            return false;
        }
        Editable text = secret.getText();
        Objects.requireNonNull(text);
        if (!text.toString().equals("")) {
            Log.d("ALLSAFE", "User entered secret: " + secret.getText().toString());
            return false;
        }
        return false;
    }
}
