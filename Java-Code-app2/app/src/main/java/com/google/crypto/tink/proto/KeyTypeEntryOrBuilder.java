package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\KeyTypeEntryOrBuilder.smali */
public interface KeyTypeEntryOrBuilder extends MessageLiteOrBuilder {
    String getCatalogueName();

    ByteString getCatalogueNameBytes();

    int getKeyManagerVersion();

    boolean getNewKeyAllowed();

    String getPrimitiveName();

    ByteString getPrimitiveNameBytes();

    String getTypeUrl();

    ByteString getTypeUrlBytes();
}
