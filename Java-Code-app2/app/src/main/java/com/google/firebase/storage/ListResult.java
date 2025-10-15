package com.google.firebase.storage;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\firebase\storage\ListResult.smali */
public final class ListResult {
    private static final String ITEMS_KEY = "items";
    private static final String NAME_KEY = "name";
    private static final String PAGE_TOKEN_KEY = "nextPageToken";
    private static final String PREFIXES_KEY = "prefixes";
    private final List<StorageReference> items;
    private final String pageToken;
    private final List<StorageReference> prefixes;

    ListResult(List<StorageReference> prefixes, List<StorageReference> items, String pageToken) {
        this.prefixes = prefixes;
        this.items = items;
        this.pageToken = pageToken;
    }

    static ListResult fromJSON(FirebaseStorage storage, JSONObject resultBody) throws JSONException {
        List<StorageReference> prefixes = new ArrayList<>();
        List<StorageReference> items = new ArrayList<>();
        if (resultBody.has(PREFIXES_KEY)) {
            JSONArray prefixEntries = resultBody.getJSONArray(PREFIXES_KEY);
            for (int i = 0; i < prefixEntries.length(); i++) {
                String pathWithoutTrailingSlash = prefixEntries.getString(i);
                if (pathWithoutTrailingSlash.endsWith("/")) {
                    pathWithoutTrailingSlash = pathWithoutTrailingSlash.substring(0, pathWithoutTrailingSlash.length() - 1);
                }
                prefixes.add(storage.getReference(pathWithoutTrailingSlash));
            }
        }
        if (resultBody.has(ITEMS_KEY)) {
            JSONArray itemEntries = resultBody.getJSONArray(ITEMS_KEY);
            for (int i2 = 0; i2 < itemEntries.length(); i2++) {
                JSONObject metadata = itemEntries.getJSONObject(i2);
                items.add(storage.getReference(metadata.getString(NAME_KEY)));
            }
        }
        String pageToken = resultBody.optString(PAGE_TOKEN_KEY, null);
        return new ListResult(prefixes, items, pageToken);
    }

    public List<StorageReference> getPrefixes() {
        return this.prefixes;
    }

    public List<StorageReference> getItems() {
        return this.items;
    }

    public String getPageToken() {
        return this.pageToken;
    }
}
