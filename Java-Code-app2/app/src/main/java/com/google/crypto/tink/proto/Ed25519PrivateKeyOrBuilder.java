package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\Ed25519PrivateKeyOrBuilder.smali */
public interface Ed25519PrivateKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getKeyValue();

    Ed25519PublicKey getPublicKey();

    int getVersion();

    boolean hasPublicKey();
}
