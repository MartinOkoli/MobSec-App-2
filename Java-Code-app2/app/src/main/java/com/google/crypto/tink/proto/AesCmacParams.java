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

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\AesCmacParams.smali */
public final class AesCmacParams extends GeneratedMessageLite<AesCmacParams, Builder> implements AesCmacParamsOrBuilder {
    private static final AesCmacParams DEFAULT_INSTANCE;
    private static volatile Parser<AesCmacParams> PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 1;
    private int tagSize_;

    private AesCmacParams() {
    }

    @Override // com.google.crypto.tink.proto.AesCmacParamsOrBuilder
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

    public static AesCmacParams parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AesCmacParams parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AesCmacParams parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AesCmacParams parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AesCmacParams parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static AesCmacParams parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static AesCmacParams parseFrom(InputStream input) throws IOException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AesCmacParams parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AesCmacParams parseDelimitedFrom(InputStream input) throws IOException {
        return (AesCmacParams) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static AesCmacParams parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AesCmacParams) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static AesCmacParams parseFrom(CodedInputStream input) throws IOException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static AesCmacParams parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (AesCmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(AesCmacParams prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\AesCmacParams$Builder.smali */
    public static final class Builder extends GeneratedMessageLite.Builder<AesCmacParams, Builder> implements AesCmacParamsOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(AesCmacParams.DEFAULT_INSTANCE);
        }

        @Override // com.google.crypto.tink.proto.AesCmacParamsOrBuilder
        public int getTagSize() {
            return ((AesCmacParams) this.instance).getTagSize();
        }

        public Builder setTagSize(int value) {
            copyOnWrite();
            ((AesCmacParams) this.instance).setTagSize(value);
            return this;
        }

        public Builder clearTagSize() {
            copyOnWrite();
            ((AesCmacParams) this.instance).clearTagSize();
            return this;
        }
    }

    /* renamed from: com.google.crypto.tink.proto.AesCmacParams$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\AesCmacParams$1.smali */
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
                return new AesCmacParams();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"tagSize_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<AesCmacParams> parser = PARSER;
                if (parser == null) {
                    synchronized (AesCmacParams.class) {
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
        AesCmacParams defaultInstance = new AesCmacParams();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(AesCmacParams.class, defaultInstance);
    }

    public static AesCmacParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCmacParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
