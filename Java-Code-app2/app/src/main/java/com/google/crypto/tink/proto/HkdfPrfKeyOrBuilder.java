package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\HkdfPrfKeyOrBuilder.smali */
public interface HkdfPrfKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getKeyValue();

    HkdfPrfParams getParams();

    int getVersion();

    boolean hasParams();
}
