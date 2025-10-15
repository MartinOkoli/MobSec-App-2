package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\ObjectSerialization.smali */
public class ObjectSerialization extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_object_serialization, container, false);
        final TextInputEditText username = (TextInputEditText) view.findViewById(R.id.username);
        final TextInputEditText password = (TextInputEditText) view.findViewById(R.id.password);
        Button save = (Button) view.findViewById(R.id.save);
        Button load = (Button) view.findViewById(R.id.load);
        final String path = requireActivity().getExternalFilesDir(null) + "/user.dat";
        save.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$ObjectSerialization$fySbmVUVK4Czj-7I53IVtpMDm7c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$ObjectSerialization(username, password, path, view2);
            }
        });
        load.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$ObjectSerialization$qj3XemmX2xNak41WOw_roCMX_yw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$1$ObjectSerialization(path, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$ObjectSerialization(TextInputEditText username, TextInputEditText password, String path, View v) {
        Editable text = username.getText();
        Objects.requireNonNull(text);
        if (!text.toString().isEmpty()) {
            Editable text2 = password.getText();
            Objects.requireNonNull(text2);
            if (!text2.toString().isEmpty()) {
                User user = new User(username.getText().toString(), password.getText().toString());
                try {
                    File file = new File(path);
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(user);
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    Log.d("ALLSAFE", e.getLocalizedMessage());
                }
                SnackUtil.INSTANCE.simpleMessage(requireActivity(), "User data successfully saved!");
                return;
            }
        }
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Fill out the fields!");
    }

    public /* synthetic */ void lambda$onCreateView$1$ObjectSerialization(String path, View v) {
        if (new File(path).exists()) {
            try {
                File file = new File(path);
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                User user = (User) ois.readObject();
                ois.close();
                fis.close();
                if (!user.role.equals("ROLE_EDITOR")) {
                    SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Sorry, only editors have access!");
                } else {
                    SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Good job!");
                    Toast.makeText(requireContext(), user.toString(), 0).show();
                }
                return;
            } catch (IOException | ClassNotFoundException e) {
                Log.d("ALLSAFE", e.getLocalizedMessage());
                return;
            }
        }
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "File not found!");
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\ObjectSerialization$User.smali */
    public static class User implements Serializable {
        String password;
        String role = "ROLE_AUTHOR";
        String username;

        public User() {
        }

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String toString() {
            return "User{username='" + this.username + "', password='" + this.password + "', role='" + this.role + "'}";
        }
    }
}
