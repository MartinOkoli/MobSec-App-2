package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\DeleteNetworkRequest.smali */
public class DeleteNetworkRequest extends NetworkRequest {
    public DeleteNetworkRequest(Uri gsUri, FirebaseApp app) {
        super(gsUri, app);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "DELETE";
    }
}
