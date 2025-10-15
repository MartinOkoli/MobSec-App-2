package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;
import org.json.JSONObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\network\UpdateMetadataNetworkRequest.smali */
public class UpdateMetadataNetworkRequest extends NetworkRequest {
    private final JSONObject metadata;

    public UpdateMetadataNetworkRequest(Uri gsUri, FirebaseApp app, JSONObject metadata) {
        super(gsUri, app);
        this.metadata = metadata;
        setCustomHeader("X-HTTP-Method-Override", "PATCH");
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "PUT";
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected JSONObject getOutputJSON() {
        return this.metadata;
    }
}
