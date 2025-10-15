package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.subtle.Hex;
import java.io.IOException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\SharedPrefKeysetWriter.smali */
public final class SharedPrefKeysetWriter implements KeysetWriter {
    private final SharedPreferences.Editor editor;
    private final String keysetName;

    public SharedPrefKeysetWriter(Context context, String keysetName, String prefFileName) {
        if (keysetName == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.keysetName = keysetName;
        Context appContext = context.getApplicationContext();
        if (prefFileName == null) {
            this.editor = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        } else {
            this.editor = appContext.getSharedPreferences(prefFileName, 0).edit();
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        boolean success = this.editor.putString(this.keysetName, Hex.encode(keyset.toByteArray())).commit();
        if (!success) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset keyset) throws IOException {
        boolean success = this.editor.putString(this.keysetName, Hex.encode(keyset.toByteArray())).commit();
        if (!success) {
            throw new IOException("Failed to write to SharedPreferences");
        }
    }
}
