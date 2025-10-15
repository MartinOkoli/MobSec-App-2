package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPssPrivateKeyOrBuilder.smali */
public interface RsaSsaPssPrivateKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getCrt();

    ByteString getD();

    ByteString getDp();

    ByteString getDq();

    ByteString getP();

    RsaSsaPssPublicKey getPublicKey();

    ByteString getQ();

    int getVersion();

    boolean hasPublicKey();
}
