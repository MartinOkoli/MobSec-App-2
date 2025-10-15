package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\KeysetInfoOrBuilder.smali */
public interface KeysetInfoOrBuilder extends MessageLiteOrBuilder {
    KeysetInfo.KeyInfo getKeyInfo(int index);

    int getKeyInfoCount();

    List<KeysetInfo.KeyInfo> getKeyInfoList();

    int getPrimaryKeyId();
}
