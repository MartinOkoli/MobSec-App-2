package infosecadventures.allsafe.challenges;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputEditText;
import infosecadventures.allsafe.R;
import infosecadventures.allsafe.utils.SnackUtil;
import java.util.Objects;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\challenges\VulnerableWebView.smali */
public class VulnerableWebView extends Fragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vulnerable_web_view, container, false);
        final TextInputEditText payload = (TextInputEditText) view.findViewById(R.id.payload);
        final WebView webView = (WebView) view.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        view.findViewById(R.id.execute).setOnClickListener(new View.OnClickListener() { // from class: infosecadventures.allsafe.challenges.-$$Lambda$VulnerableWebView$pst9pjDhOndz7pjoqSmpLSa-FH4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onCreateView$0$VulnerableWebView(payload, webView, view2);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$VulnerableWebView(TextInputEditText payload, WebView webView, View v) {
        Editable text = payload.getText();
        Objects.requireNonNull(text);
        if (!text.toString().isEmpty()) {
            Editable text2 = payload.getText();
            Objects.requireNonNull(text2);
            if (URLUtil.isValidUrl(text2.toString())) {
                webView.loadUrl(payload.getText().toString());
                return;
            } else {
                webView.setWebChromeClient(new WebChromeClient());
                webView.loadData(payload.getText().toString(), "text/html", "UTF-8");
                return;
            }
        }
        SnackUtil.INSTANCE.simpleMessage(requireActivity(), "No payload provided!");
    }
}
