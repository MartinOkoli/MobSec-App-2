package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.firebase.FirebaseApp;
import java.util.Collections;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\GetNetworkRequest.smali */
public class GetNetworkRequest extends NetworkRequest {
    private static final String TAG = "GetNetworkRequest";

    public GetNetworkRequest(Uri gsUri, FirebaseApp app, long startByte) {
        super(gsUri, app);
        if (startByte != 0) {
            super.setCustomHeader("Range", "bytes=" + startByte + "-");
        }
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return ShareTarget.METHOD_GET;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        return Collections.singletonMap("alt", "media");
    }
}
