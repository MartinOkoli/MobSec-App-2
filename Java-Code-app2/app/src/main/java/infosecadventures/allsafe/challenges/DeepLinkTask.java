package infosecadventures.allsafe.challenges;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\DeepLinkTask.smali */
public class DeepLinkTask extends AppCompatActivity {
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link_task);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        Log.d("ALLSAFE", "Action: " + action + " Data: " + data);
        try {
            if (data.getQueryParameter("key").equals(getString(R.string.key))) {
                findViewById(2131296383).setVisibility(0);
                SnackUtil.INSTANCE.simpleMessage(this, "Good job, you did it!");
            } else {
                SnackUtil.INSTANCE.simpleMessage(this, "Wrong key, try harder!");
            }
        } catch (Exception e) {
            SnackUtil.INSTANCE.simpleMessage(this, "No key provided!");
            Log.e("ALLSAFE", e.getMessage());
        }
    }
}
