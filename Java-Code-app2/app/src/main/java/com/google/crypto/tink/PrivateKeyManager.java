package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import java.security.GeneralSecurityException;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\PrivateKeyManager.smali */
public interface PrivateKeyManager<P> extends KeyManager<P> {
    KeyData getPublicKeyData(ByteString serializedKey) throws GeneralSecurityException;
}
