package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import java.util.List;

@Deprecated
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RegistryConfigOrBuilder.smali */
public interface RegistryConfigOrBuilder extends MessageLiteOrBuilder {
    String getConfigName();

    ByteString getConfigNameBytes();

    KeyTypeEntry getEntry(int index);

    int getEntryCount();

    List<KeyTypeEntry> getEntryList();
}
