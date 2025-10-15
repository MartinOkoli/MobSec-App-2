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

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPkcs1Params.smali */
public final class RsaSsaPkcs1Params extends GeneratedMessageLite<RsaSsaPkcs1Params, Builder> implements RsaSsaPkcs1ParamsOrBuilder {
    private static final RsaSsaPkcs1Params DEFAULT_INSTANCE;
    public static final int HASH_TYPE_FIELD_NUMBER = 1;
    private static volatile Parser<RsaSsaPkcs1Params> PARSER;
    private int hashType_;

    private RsaSsaPkcs1Params() {
    }

    @Override // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
    public int getHashTypeValue() {
        return this.hashType_;
    }

    @Override // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
    public HashType getHashType() {
        HashType result = HashType.forNumber(this.hashType_);
        return result == null ? HashType.UNRECOGNIZED : result;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashTypeValue(int value) {
        this.hashType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHashType(HashType value) {
        this.hashType_ = value.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHashType() {
        this.hashType_ = 0;
    }

    public static RsaSsaPkcs1Params parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RsaSsaPkcs1Params parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RsaSsaPkcs1Params parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RsaSsaPkcs1Params parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RsaSsaPkcs1Params parseFrom(InputStream input) throws IOException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RsaSsaPkcs1Params parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RsaSsaPkcs1Params parseDelimitedFrom(InputStream input) throws IOException {
        return (RsaSsaPkcs1Params) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RsaSsaPkcs1Params parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RsaSsaPkcs1Params) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RsaSsaPkcs1Params parseFrom(CodedInputStream input) throws IOException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RsaSsaPkcs1Params parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RsaSsaPkcs1Params) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RsaSsaPkcs1Params prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPkcs1Params$Builder.smali */
    public static final class Builder extends GeneratedMessageLite.Builder<RsaSsaPkcs1Params, Builder> implements RsaSsaPkcs1ParamsOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(RsaSsaPkcs1Params.DEFAULT_INSTANCE);
        }

        @Override // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
        public int getHashTypeValue() {
            return ((RsaSsaPkcs1Params) this.instance).getHashTypeValue();
        }

        public Builder setHashTypeValue(int value) {
            copyOnWrite();
            ((RsaSsaPkcs1Params) this.instance).setHashTypeValue(value);
            return this;
        }

        @Override // com.google.crypto.tink.proto.RsaSsaPkcs1ParamsOrBuilder
        public HashType getHashType() {
            return ((RsaSsaPkcs1Params) this.instance).getHashType();
        }

        public Builder setHashType(HashType value) {
            copyOnWrite();
            ((RsaSsaPkcs1Params) this.instance).setHashType(value);
            return this;
        }

        public Builder clearHashType() {
            copyOnWrite();
            ((RsaSsaPkcs1Params) this.instance).clearHashType();
            return this;
        }
    }

    /* renamed from: com.google.crypto.tink.proto.RsaSsaPkcs1Params$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\RsaSsaPkcs1Params$1.smali */
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
                return new RsaSsaPkcs1Params();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"hashType_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RsaSsaPkcs1Params> parser = PARSER;
                if (parser == null) {
                    synchronized (RsaSsaPkcs1Params.class) {
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
        RsaSsaPkcs1Params defaultInstance = new RsaSsaPkcs1Params();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(RsaSsaPkcs1Params.class, defaultInstance);
    }

    public static RsaSsaPkcs1Params getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RsaSsaPkcs1Params> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
