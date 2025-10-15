package infosecadventures.allsafe.challenges;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\InsecureSharedPreferences.smali */
public class InsecureSharedPreferences extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insecure_shared_preferences, container, false);
        final EditText username = (EditText) view.findViewById(R.id.username);
        final EditText password = (EditText) view.findViewById(R.id.password);
        final EditText passwordAgain = (EditText) view.findViewById(R.id.passwordAgain);
        Button register = (Button) view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$InsecureSharedPreferences$5dZbriop0s0VnEC1cF0idGfusd4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$InsecureSharedPreferences(username, password, passwordAgain, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$InsecureSharedPreferences(EditText username, EditText password, EditText passwordAgain, View v) {
        if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || passwordAgain.getText().toString().isEmpty()) {
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Please, fill out the form!");
            return;
        }
        if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Passwords don't match!");
            return;
        }
        SharedPreferences sharedpreferences = requireActivity().getSharedPreferences("user", 0);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.apply();
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Successful registration!");
    }
}
