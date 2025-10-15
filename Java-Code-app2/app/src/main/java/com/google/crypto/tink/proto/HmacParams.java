package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\HmacParams.smali */
public final class HmacParams extends GeneratedMessageLite<HmacParams, Builder> implements HmacParamsOrBuilder {
    private static final HmacParams DEFAULT_INSTANCE;
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile Parser<HmacParams> PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 2;
    private int hash_;
    private int tagSize_;

    private HmacParams() {
    }

    @Override // com.google.crypto.tink.proto.HmacParamsOrBuilder
    public int getHashValue() {
        return this.hash_;
    }

    @Override // com.google.crypto.tink.proto.HmacParamsOrBuilder
    public HashType getHash() {
        HashType result = HashType.forNumber(this.hash_);
        return result == null ? HashType.UNRECOGNIZED : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashValue(int value) {
        this.hash_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHash(HashType value) {
        this.hash_ = value.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHash() {
        this.hash_ = 0;
    }

    @Override // com.google.crypto.tink.proto.HmacParamsOrBuilder
    public int getTagSize() {
        return this.tagSize_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTagSize(int value) {
        this.tagSize_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTagSize() {
        this.tagSize_ = 0;
    }

    public static HmacParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HmacParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HmacParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HmacParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HmacParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static HmacParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static HmacParams parseFrom(InputStream input) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HmacParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HmacParams parseDelimitedFrom(InputStream input) throws IOException {
        return (HmacParams) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static HmacParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HmacParams) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static HmacParams parseFrom(CodedInputStream input) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static HmacParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(HmacParams prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\HmacParams$Builder.smali */
    public static final class Builder extends GeneratedMessageLite.Builder<HmacParams, Builder> implements HmacParamsOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(HmacParams.DEFAULT_INSTANCE);
        }

        @Override // com.google.crypto.tink.proto.HmacParamsOrBuilder
        public int getHashValue() {
            return ((HmacParams) this.instance).getHashValue();
        }

        public Builder setHashValue(int value) {
            copyOnWrite();
            ((HmacParams) this.instance).setHashValue(value);
            return this;
        }

        @Override // com.google.crypto.tink.proto.HmacParamsOrBuilder
        public HashType getHash() {
            return ((HmacParams) this.instance).getHash();
        }

        public Builder setHash(HashType value) {
            copyOnWrite();
            ((HmacParams) this.instance).setHash(value);
            return this;
        }

        public Builder clearHash() {
            copyOnWrite();
            ((HmacParams) this.instance).clearHash();
            return this;
        }

        @Override // com.google.crypto.tink.proto.HmacParamsOrBuilder
        public int getTagSize() {
            return ((HmacParams) this.instance).getTagSize();
        }

        public Builder setTagSize(int value) {
            copyOnWrite();
            ((HmacParams) this.instance).setTagSize(value);
            return this;
        }

        public Builder clearTagSize() {
            copyOnWrite();
            ((HmacParams) this.instance).clearTagSize();
            return this;
        }
    }

    /* renamed from: com.google.crypto.tink.proto.HmacParams$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\HmacParams$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
            case 1:
                return new HmacParams();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"hash_", "tagSize_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<HmacParams> parser = PARSER;
                if (parser == null) {
                    synchronized (HmacParams.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        HmacParams defaultInstance = new HmacParams();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(HmacParams.class, defaultInstance);
    }

    public static HmacParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HmacParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
