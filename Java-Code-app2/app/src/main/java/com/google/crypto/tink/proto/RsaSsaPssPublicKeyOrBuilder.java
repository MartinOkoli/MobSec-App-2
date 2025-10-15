package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPssPublicKeyOrBuilder.smali */
public interface RsaSsaPssPublicKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getE();

    ByteString getN();

    RsaSsaPssParams getParams();

    int getVersion();

    boolean hasParams();
}
