package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\AesGcmHkdfStreamingParamsOrBuilder.smali */
public interface AesGcmHkdfStreamingParamsOrBuilder extends MessageLiteOrBuilder {
    int getCiphertextSegmentSize();

    int getDerivedKeySize();

    HashType getHkdfHashType();

    int getHkdfHashTypeValue();
}
