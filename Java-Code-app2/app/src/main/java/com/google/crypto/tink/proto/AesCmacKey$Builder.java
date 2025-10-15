package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\AesCmacKey$Builder.smali */
public final class AesCmacKey$Builder extends GeneratedMessageLite.Builder<AesCmacKey, AesCmacKey$Builder> implements AesCmacKeyOrBuilder {
    /* synthetic */ AesCmacKey$Builder(AesCmacKey$1 x0) {
        this();
    }

    private AesCmacKey$Builder() {
        super(AesCmacKey.access$000());
    }

    @Override // com.google.crypto.tink.proto.AesCmacKeyOrBuilder
    public int getVersion() {
        return this.instance.getVersion();
    }

    public AesCmacKey$Builder setVersion(int value) {
        copyOnWrite();
        AesCmacKey.access$100(this.instance, value);
        return this;
    }

    public AesCmacKey$Builder clearVersion() {
        copyOnWrite();
        AesCmacKey.access$200(this.instance);
        return this;
    }

    @Override // com.google.crypto.tink.proto.AesCmacKeyOrBuilder
    public ByteString getKeyValue() {
        return this.instance.getKeyValue();
    }

    public AesCmacKey$Builder setKeyValue(ByteString value) {
        copyOnWrite();
        AesCmacKey.access$300(this.instance, value);
        return this;
    }

    public AesCmacKey$Builder clearKeyValue() {
        copyOnWrite();
        AesCmacKey.access$400(this.instance);
        return this;
    }

    @Override // com.google.crypto.tink.proto.AesCmacKeyOrBuilder
    public boolean hasParams() {
        return this.instance.hasParams();
    }

    @Override // com.google.crypto.tink.proto.AesCmacKeyOrBuilder
    public AesCmacParams getParams() {
        return this.instance.getParams();
    }

    public AesCmacKey$Builder setParams(AesCmacParams value) {
        copyOnWrite();
        AesCmacKey.access$500(this.instance, value);
        return this;
    }

    public AesCmacKey$Builder setParams(AesCmacParams.Builder builderForValue) {
        copyOnWrite();
        AesCmacKey.access$500(this.instance, builderForValue.build());
        return this;
    }

    public AesCmacKey$Builder mergeParams(AesCmacParams value) {
        copyOnWrite();
        AesCmacKey.access$600(this.instance, value);
        return this;
    }

    public AesCmacKey$Builder clearParams() {
        copyOnWrite();
        AesCmacKey.access$700(this.instance);
        return this;
    }
}
