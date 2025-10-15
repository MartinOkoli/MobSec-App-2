package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPkcs1KeyFormatOrBuilder.smali */
public interface RsaSsaPkcs1KeyFormatOrBuilder extends MessageLiteOrBuilder {
    int getModulusSizeInBits();

    RsaSsaPkcs1Params getParams();

    ByteString getPublicExponent();

    boolean hasParams();
}
