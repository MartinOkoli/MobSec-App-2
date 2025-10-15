package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\WeakCryptography.smali */
public class WeakCryptography extends Fragment {
    public static final String KEY = "1nf053c4dv3n7ur3";

    public static String encrypt(String value) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(1, secretKeySpec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return new String(encrypted);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String md5Hash(String text) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            byte[] messageDigest = digest.digest();
            stringBuilder.append(String.format("%032X", new BigInteger(1, messageDigest)));
        } catch (Exception e) {
            Log.d("ALLSAFE", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    public static String randomNumber() {
        Random rnd = new Random();
        int n = rnd.nextInt(100000) + 1;
        return Integer.toString(n);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weak_cryptography, container, false);
        final EditText secret = (EditText) view.findViewById(R.id.secret);
        view.findViewById(R.id.encrypt).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$WeakCryptography$CxNZqC-nBjpv0MX4YbGJRJoVQqk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$WeakCryptography(secret, view2);
            }
        });
        view.findViewById(R.id.hash).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$WeakCryptography$Q5AJT6A8d2ZymKsTGjyxUsn1BZo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$1$WeakCryptography(secret, view2);
            }
        });
        view.findViewById(R.id.random).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$WeakCryptography$drDyzFsKtND06CpQGTJDLtRwqzE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$2$WeakCryptography(view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$WeakCryptography(EditText secret, View v) {
        String plain_text = secret.getText().toString();
        if (!plain_text.isEmpty()) {
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Result: " + encrypt(plain_text));
            return;
        }
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "First, you have to enter your secrets!");
    }

    public /* synthetic */ void lambda$onCreateView$1$WeakCryptography(EditText secret, View v) {
        String plain_text = secret.getText().toString();
        if (!plain_text.isEmpty()) {
            SnackUtil.INSTANCE.simpleMessage(requireActivity(), "MD5 Hash: " + md5Hash(plain_text));
            return;
        }
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "First, you have to enter your secrets!");
    }

    public /* synthetic */ void lambda$onCreateView$2$WeakCryptography(View v) {
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "Random: " + randomNumber());
    }
}
