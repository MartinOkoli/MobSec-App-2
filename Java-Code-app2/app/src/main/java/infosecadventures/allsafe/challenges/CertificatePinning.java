package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.challenges.CertificatePinning;
import infosecadventures.allsafe.utils.SnackUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\CertificatePinning.smali */
public class CertificatePinning extends Fragment {
    private static final String INVALID_HASH = "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=";

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certificate_pinning, container, false);
        setHasOptionsMenu(true);
        final List<String> hashes = extractPeerCertificateChain();
        Button test = (Button) view.findViewById(R.id.execute);
        test.setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$CertificatePinning$uGV_PQZXWyNCsz2lruWXfIwtX0M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$CertificatePinning(hashes, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$CertificatePinning(List hashes, View v) {
        CertificatePinner.Builder certificatePinner = new CertificatePinner.Builder();
        Iterator it = hashes.iterator();
        while (it.hasNext()) {
            String hash = (String) it.next();
            Log.d("ALLSAFE", hash);
            certificatePinner.add("httpbing.org", hash);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().certificatePinner(certificatePinner.build()).build();
        Request request = new Request.Builder().url("https://httpbin.org/json").build();
        okHttpClient.newCall(request).enqueue(new AnonymousClass1());
    }

    /* renamed from: infosecadventures.allsafe.challenges.CertificatePinning$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\CertificatePinning$1.smali */
    class AnonymousClass1 implements Callback {
        AnonymousClass1() {
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, final IOException e) {
            Log.d("ALLSAFE", e.getMessage());
            CertificatePinning.this.requireActivity().runOnUiThread(new Runnable() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$CertificatePinning$1$j9FhURxmdGFv5Tw8pQP0Eu00owI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onFailure$0$CertificatePinning$1(e);
                }
            });
        }

        public /* synthetic */ void lambda$onFailure$0$CertificatePinning$1(IOException e) {
            SnackUtil.INSTANCE.simpleMessage(CertificatePinning.this.requireActivity(), e.getMessage());
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, final Response response) throws IOException {
            ResponseBody responseBodyBody = response.body();
            Objects.requireNonNull(responseBodyBody);
            Log.d("ALLSAFE", responseBodyBody.string());
            CertificatePinning.this.requireActivity().runOnUiThread(new Runnable() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$CertificatePinning$1$nihjGSfp1i0t9vVs1G7p-MrS1x4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onResponse$1$CertificatePinning$1(response);
                }
            });
        }

        public /* synthetic */ void lambda$onResponse$1$CertificatePinning$1(Response response) {
            if (response.isSuccessful()) {
                SnackUtil.INSTANCE.simpleMessage(CertificatePinning.this.requireActivity(), "Successful connection over HTTPS!");
            }
        }
    }

    private List<String> extractPeerCertificateChain() {
        List<String> chain = new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().certificatePinner(new CertificatePinner.Builder().add("httpbin.org", INVALID_HASH).build()).build();
        Request request = new Request.Builder().url("https://httpbin.org/json").build();
        okHttpClient.newCall(request).enqueue(new AnonymousClass2(chain));
        return chain;
    }

    /* renamed from: infosecadventures.allsafe.challenges.CertificatePinning$2, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\CertificatePinning$2.smali */
    class AnonymousClass2 implements Callback {
        final /* synthetic */ List val$chain;

        AnonymousClass2(List list) {
            this.val$chain = list;
        }

        @Override // okhttp3.Callback
        public void onFailure(Call call, final IOException e) {
            FragmentActivity fragmentActivityRequireActivity = CertificatePinning.this.requireActivity();
            final List list = this.val$chain;
            fragmentActivityRequireActivity.runOnUiThread(new Runnable() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$CertificatePinning$2$HXzbC8ZJ0OCVDn8ZzaKedVDr6-Y
                @Override // java.lang.Runnable
                public final void run() {
                    CertificatePinning.AnonymousClass2.lambda$onFailure$0(e, list);
                }
            });
        }

        static /* synthetic */ void lambda$onFailure$0(IOException e, List chain) {
            String[] lines = e.getMessage().split(System.getProperty("line.separator"));
            for (String line : lines) {
                if (!line.trim().equals(CertificatePinning.INVALID_HASH) && line.trim().startsWith("sha256")) {
                    String pin = line.trim().split(":")[0].trim();
                    chain.add(pin);
                }
            }
        }

        @Override // okhttp3.Callback
        public void onResponse(Call call, Response response) {
        }
    }
}
