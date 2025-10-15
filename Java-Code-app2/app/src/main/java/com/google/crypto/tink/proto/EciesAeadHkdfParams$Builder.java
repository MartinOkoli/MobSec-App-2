package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.EciesAeadDemParams;
import com.google.crypto.tink.proto.EciesHkdfKemParams;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EciesAeadHkdfParams$Builder.smali */
public final class EciesAeadHkdfParams$Builder extends GeneratedMessageLite.Builder<EciesAeadHkdfParams, EciesAeadHkdfParams$Builder> implements EciesAeadHkdfParamsOrBuilder {
    /* synthetic */ EciesAeadHkdfParams$Builder(EciesAeadHkdfParams$1 x0) {
        this();
    }

    private EciesAeadHkdfParams$Builder() {
        super(EciesAeadHkdfParams.access$000());
    }

    @Override // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public boolean hasKemParams() {
        return this.instance.hasKemParams();
    }

    @Override // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public EciesHkdfKemParams getKemParams() {
        return this.instance.getKemParams();
    }

    public EciesAeadHkdfParams$Builder setKemParams(EciesHkdfKemParams value) {
        copyOnWrite();
        EciesAeadHkdfParams.access$100(this.instance, value);
        return this;
    }

    public EciesAeadHkdfParams$Builder setKemParams(EciesHkdfKemParams.Builder builderForValue) {
        copyOnWrite();
        EciesAeadHkdfParams.access$100(this.instance, builderForValue.build());
        return this;
    }

    public EciesAeadHkdfParams$Builder mergeKemParams(EciesHkdfKemParams value) {
        copyOnWrite();
        EciesAeadHkdfParams.access$200(this.instance, value);
        return this;
    }

    public EciesAeadHkdfParams$Builder clearKemParams() {
        copyOnWrite();
        EciesAeadHkdfParams.access$300(this.instance);
        return this;
    }

    @Override // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public boolean hasDemParams() {
        return this.instance.hasDemParams();
    }

    @Override // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public EciesAeadDemParams getDemParams() {
        return this.instance.getDemParams();
    }

    public EciesAeadHkdfParams$Builder setDemParams(EciesAeadDemParams value) {
        copyOnWrite();
        EciesAeadHkdfParams.access$400(this.instance, value);
        return this;
    }

    public EciesAeadHkdfParams$Builder setDemParams(EciesAeadDemParams.Builder builderForValue) {
        copyOnWrite();
        EciesAeadHkdfParams.access$400(this.instance, builderForValue.build());
        return this;
    }

    public EciesAeadHkdfParams$Builder mergeDemParams(EciesAeadDemParams value) {
        copyOnWrite();
        EciesAeadHkdfParams.access$500(this.instance, value);
        return this;
    }

    public EciesAeadHkdfParams$Builder clearDemParams() {
        copyOnWrite();
        EciesAeadHkdfParams.access$600(this.instance);
        return this;
    }

    @Override // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public int getEcPointFormatValue() {
        return this.instance.getEcPointFormatValue();
    }

    public EciesAeadHkdfParams$Builder setEcPointFormatValue(int value) {
        copyOnWrite();
        EciesAeadHkdfParams.access$700(this.instance, value);
        return this;
    }

    @Override // com.google.crypto.tink.proto.EciesAeadHkdfParamsOrBuilder
    public EcPointFormat getEcPointFormat() {
        return this.instance.getEcPointFormat();
    }

    public EciesAeadHkdfParams$Builder setEcPointFormat(EcPointFormat value) {
        copyOnWrite();
        EciesAeadHkdfParams.access$800(this.instance, value);
        return this;
    }

    public EciesAeadHkdfParams$Builder clearEcPointFormat() {
        copyOnWrite();
        EciesAeadHkdfParams.access$900(this.instance);
        return this;
    }
}
