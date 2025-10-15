package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPssParamsOrBuilder.smali */
public interface RsaSsaPssParamsOrBuilder extends MessageLiteOrBuilder {
    HashType getMgf1Hash();

    int getMgf1HashValue();

    int getSaltLength();

    HashType getSigHash();

    int getSigHashValue();
}
