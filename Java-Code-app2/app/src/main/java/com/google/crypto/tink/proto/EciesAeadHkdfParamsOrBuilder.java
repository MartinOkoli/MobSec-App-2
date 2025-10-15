package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EciesAeadHkdfParamsOrBuilder.smali */
public interface EciesAeadHkdfParamsOrBuilder extends MessageLiteOrBuilder {
    EciesAeadDemParams getDemParams();

    EcPointFormat getEcPointFormat();

    int getEcPointFormatValue();

    EciesHkdfKemParams getKemParams();

    boolean hasDemParams();

    boolean hasKemParams();
}
