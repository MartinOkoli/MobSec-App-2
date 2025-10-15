package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\KeyTemplateOrBuilder.smali */
public interface KeyTemplateOrBuilder extends MessageLiteOrBuilder {
    OutputPrefixType getOutputPrefixType();

    int getOutputPrefixTypeValue();

    String getTypeUrl();

    ByteString getTypeUrlBytes();

    ByteString getValue();
}
