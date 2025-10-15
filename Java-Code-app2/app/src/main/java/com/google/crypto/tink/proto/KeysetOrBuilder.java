package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\KeysetOrBuilder.smali */
public interface KeysetOrBuilder extends MessageLiteOrBuilder {
    Keyset.Key getKey(int index);

    int getKeyCount();

    List<Keyset.Key> getKeyList();

    int getPrimaryKeyId();
}
