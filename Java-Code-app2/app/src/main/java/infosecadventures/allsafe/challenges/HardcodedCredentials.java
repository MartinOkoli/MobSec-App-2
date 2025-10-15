package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: HardcodedCredentials.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\f"}, d2 = {"Linfosecadventures/allsafe/challenges/HardcodedCredentials;", "Landroidx/fragment/app/Fragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\HardcodedCredentials.smali */
public final class HardcodedCredentials extends Fragment {
    public static final String BODY = "\n            <soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n            <soap:Header>\n                 <UsernameToken xmlns=\"http://siebel.com/webservices\">superadmin</UsernameToken>\n                 <PasswordText xmlns=\"http://siebel.com/webservices\">supersecurepassword</PasswordText>\n                 <SessionType xmlns=\"http://siebel.com/webservices\">None</SessionType>\n            </soap:Header>\n            <soap:Body>\n                 <!-- data goes here -->\n            </soap:Body>\n            </soap:Envelope>\n        ";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MediaType SOAP = MediaType.INSTANCE.parse("application/soap+xml; charset=utf-8");

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(R.layout.fragment_hardcoded_credentials, container, false);
        View viewFindViewById = view.findViewById(R.id.request);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.request)");
        Button request = (Button) viewFindViewById;
        request.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.HardcodedCredentials.onCreateView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.INSTANCE.create(HardcodedCredentials.BODY, HardcodedCredentials.INSTANCE.getSOAP());
                Request.Builder builder = new Request.Builder();
                String string = HardcodedCredentials.this.getString(R.string.dev_env);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dev_env)");
                Request req = builder.url(string).post(body).build();
                client.newCall(req).enqueue(new Callback() { // from class: infosecadventures.allsafe.challenges.HardcodedCredentials.onCreateView.1.1
                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(response, "response");
                    }

                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException e) {
                        Intrinsics.checkNotNullParameter(call, "call");
                        Intrinsics.checkNotNullParameter(e, "e");
                    }
                });
                SnackUtil snackUtil = SnackUtil.INSTANCE;
                FragmentActivity fragmentActivityRequireActivity = HardcodedCredentials.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
                snackUtil.simpleMessage(fragmentActivityRequireActivity, "Under development!");
            }
        });
        return view;
    }

    /* compiled from: HardcodedCredentials.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Linfosecadventures/allsafe/challenges/HardcodedCredentials$Companion;", "", "()V", "BODY", "", "SOAP", "Lokhttp3/MediaType;", "getSOAP", "()Lokhttp3/MediaType;", "app_debug"}, k = 1, mv = {1, 4, 2})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\HardcodedCredentials$Companion.smali */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public final MediaType getSOAP() {
            return HardcodedCredentials.SOAP;
        }
    }
}
