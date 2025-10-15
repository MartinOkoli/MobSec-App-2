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

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\ChaCha20Poly1305KeyFormat.smali */
public final class ChaCha20Poly1305KeyFormat extends GeneratedMessageLite<ChaCha20Poly1305KeyFormat, Builder> implements ChaCha20Poly1305KeyFormatOrBuilder {
    private static final ChaCha20Poly1305KeyFormat DEFAULT_INSTANCE;
    private static volatile Parser<ChaCha20Poly1305KeyFormat> PARSER;

    private ChaCha20Poly1305KeyFormat() {
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(InputStream input) throws IOException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream input) throws IOException {
        return (ChaCha20Poly1305KeyFormat) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static ChaCha20Poly1305KeyFormat parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ChaCha20Poly1305KeyFormat) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(CodedInputStream input) throws IOException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static ChaCha20Poly1305KeyFormat parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (ChaCha20Poly1305KeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ChaCha20Poly1305KeyFormat prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\ChaCha20Poly1305KeyFormat$Builder.smali */
    public static final class Builder extends GeneratedMessageLite.Builder<ChaCha20Poly1305KeyFormat, Builder> implements ChaCha20Poly1305KeyFormatOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(ChaCha20Poly1305KeyFormat.DEFAULT_INSTANCE);
        }
    }

    /* renamed from: com.google.crypto.tink.proto.ChaCha20Poly1305KeyFormat$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\ChaCha20Poly1305KeyFormat$1.smali */
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
                return new ChaCha20Poly1305KeyFormat();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ChaCha20Poly1305KeyFormat> parser = PARSER;
                if (parser == null) {
                    synchronized (ChaCha20Poly1305KeyFormat.class) {
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
        ChaCha20Poly1305KeyFormat defaultInstance = new ChaCha20Poly1305KeyFormat();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(ChaCha20Poly1305KeyFormat.class, defaultInstance);
    }

    public static ChaCha20Poly1305KeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ChaCha20Poly1305KeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
