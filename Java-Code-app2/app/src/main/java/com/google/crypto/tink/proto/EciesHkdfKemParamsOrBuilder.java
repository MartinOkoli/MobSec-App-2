package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EciesHkdfKemParamsOrBuilder.smali */
public interface EciesHkdfKemParamsOrBuilder extends MessageLiteOrBuilder {
    EllipticCurveType getCurveType();

    int getCurveTypeValue();

    HashType getHkdfHashType();

    int getHkdfHashTypeValue();

    ByteString getHkdfSalt();
}
