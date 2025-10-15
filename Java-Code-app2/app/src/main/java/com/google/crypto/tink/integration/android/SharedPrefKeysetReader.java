package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.Hex;
import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\integration\android\SharedPrefKeysetReader.smali */
public final class SharedPrefKeysetReader implements KeysetReader {
    private final String keysetName;
    private final SharedPreferences sharedPreferences;

    public SharedPrefKeysetReader(Context context, String keysetName, String prefFilename) throws IOException {
        if (keysetName == null) {
            throw new IllegalArgumentException("keysetName cannot be null");
        }
        this.keysetName = keysetName;
        Context appContext = context.getApplicationContext();
        if (prefFilename == null) {
            this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        } else {
            this.sharedPreferences = appContext.getSharedPreferences(prefFilename, 0);
        }
    }

    private byte[] readPref() throws IOException {
        try {
            String keysetHex = this.sharedPreferences.getString(this.keysetName, null);
            if (keysetHex == null) {
                throw new FileNotFoundException(String.format("can't read keyset; the pref value %s does not exist", this.keysetName));
            }
            return Hex.decode(keysetHex);
        } catch (ClassCastException | IllegalArgumentException e) {
            throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", this.keysetName));
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        return Keyset.parseFrom(readPref(), ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        return EncryptedKeyset.parseFrom(readPref(), ExtensionRegistryLite.getEmptyRegistry());
    }
}
