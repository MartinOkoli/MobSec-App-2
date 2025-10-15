package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPkcs1PrivateKeyOrBuilder.smali */
public interface RsaSsaPkcs1PrivateKeyOrBuilder extends MessageLiteOrBuilder {
    ByteString getCrt();

    ByteString getD();

    ByteString getDp();

    ByteString getDq();

    ByteString getP();

    RsaSsaPkcs1PublicKey getPublicKey();

    ByteString getQ();

    int getVersion();

    boolean hasPublicKey();
}
