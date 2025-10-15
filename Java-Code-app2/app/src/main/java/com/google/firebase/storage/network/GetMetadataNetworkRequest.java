package com.google.firebase.storage.network;

import android.net.Uri;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.firebase.FirebaseApp;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\GetMetadataNetworkRequest.smali */
public class GetMetadataNetworkRequest extends NetworkRequest {
    public GetMetadataNetworkRequest(Uri gsUri, FirebaseApp app) {
        super(gsUri, app);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return ShareTarget.METHOD_GET;
    }
}
