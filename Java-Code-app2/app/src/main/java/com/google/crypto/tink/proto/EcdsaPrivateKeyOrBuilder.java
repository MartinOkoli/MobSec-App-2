package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EcdsaPrivateKeyOrBuilder.smali */
public interface EcdsaPrivateKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getKeyValue();

    EcdsaPublicKey getPublicKey();

    int getVersion();

    boolean hasPublicKey();
}
