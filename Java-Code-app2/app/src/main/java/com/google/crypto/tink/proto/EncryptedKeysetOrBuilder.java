package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EncryptedKeysetOrBuilder.smali */
public interface EncryptedKeysetOrBuilder extends MessageLiteOrBuilder {
    ByteString getEncryptedKeyset();

    KeysetInfo getKeysetInfo();

    boolean hasKeysetInfo();
}
