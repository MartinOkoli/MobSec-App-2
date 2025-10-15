package com.google.crypto.tink.proto;

import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EncryptedKeyset.smali */
public final class EncryptedKeyset extends GeneratedMessageLite<EncryptedKeyset, Builder> implements EncryptedKeysetOrBuilder {
    private static final EncryptedKeyset DEFAULT_INSTANCE;
    public static final int ENCRYPTED_KEYSET_FIELD_NUMBER = 2;
    public static final int KEYSET_INFO_FIELD_NUMBER = 3;
    private static volatile Parser<EncryptedKeyset> PARSER;
    private ByteString encryptedKeyset_ = ByteString.EMPTY;
    private KeysetInfo keysetInfo_;

    private EncryptedKeyset() {
    }

    @Override // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
    public ByteString getEncryptedKeyset() {
        return this.encryptedKeyset_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEncryptedKeyset(ByteString value) {
        value.getClass();
        this.encryptedKeyset_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEncryptedKeyset() {
        this.encryptedKeyset_ = getDefaultInstance().getEncryptedKeyset();
    }

    @Override // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
    public boolean hasKeysetInfo() {
        return this.keysetInfo_ != null;
    }

    @Override // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
    public KeysetInfo getKeysetInfo() {
        KeysetInfo keysetInfo = this.keysetInfo_;
        return keysetInfo == null ? KeysetInfo.getDefaultInstance() : keysetInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setKeysetInfo(KeysetInfo value) {
        value.getClass();
        this.keysetInfo_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeKeysetInfo(KeysetInfo value) {
        value.getClass();
        KeysetInfo keysetInfo = this.keysetInfo_;
        if (keysetInfo != null && keysetInfo != KeysetInfo.getDefaultInstance()) {
            this.keysetInfo_ = KeysetInfo.newBuilder(this.keysetInfo_).mergeFrom((KeysetInfo.Builder) value).buildPartial();
        } else {
            this.keysetInfo_ = value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearKeysetInfo() {
        this.keysetInfo_ = null;
    }

    public static EncryptedKeyset parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EncryptedKeyset parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EncryptedKeyset parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EncryptedKeyset parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EncryptedKeyset parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static EncryptedKeyset parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static EncryptedKeyset parseFrom(InputStream input) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static EncryptedKeyset parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static EncryptedKeyset parseDelimitedFrom(InputStream input) throws IOException {
        return (EncryptedKeyset) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static EncryptedKeyset parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EncryptedKeyset) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static EncryptedKeyset parseFrom(CodedInputStream input) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static EncryptedKeyset parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(EncryptedKeyset prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EncryptedKeyset$Builder.smali */
    public static final class Builder extends GeneratedMessageLite.Builder<EncryptedKeyset, Builder> implements EncryptedKeysetOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(EncryptedKeyset.DEFAULT_INSTANCE);
        }

        @Override // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
        public ByteString getEncryptedKeyset() {
            return ((EncryptedKeyset) this.instance).getEncryptedKeyset();
        }

        public Builder setEncryptedKeyset(ByteString value) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).setEncryptedKeyset(value);
            return this;
        }

        public Builder clearEncryptedKeyset() {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).clearEncryptedKeyset();
            return this;
        }

        @Override // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
        public boolean hasKeysetInfo() {
            return ((EncryptedKeyset) this.instance).hasKeysetInfo();
        }

        @Override // com.google.crypto.tink.proto.EncryptedKeysetOrBuilder
        public KeysetInfo getKeysetInfo() {
            return ((EncryptedKeyset) this.instance).getKeysetInfo();
        }

        public Builder setKeysetInfo(KeysetInfo value) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).setKeysetInfo(value);
            return this;
        }

        public Builder setKeysetInfo(KeysetInfo.Builder builderForValue) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).setKeysetInfo(builderForValue.build());
            return this;
        }

        public Builder mergeKeysetInfo(KeysetInfo value) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).mergeKeysetInfo(value);
            return this;
        }

        public Builder clearKeysetInfo() {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).clearKeysetInfo();
            return this;
        }
    }

    /* renamed from: com.google.crypto.tink.proto.EncryptedKeyset$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\proto\EncryptedKeyset$1.smali */
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
                return new EncryptedKeyset();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"encryptedKeyset_", "keysetInfo_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0002\u0003\u0002\u0000\u0000\u0000\u0002\n\u0003\t", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<EncryptedKeyset> parser = PARSER;
                if (parser == null) {
                    synchronized (EncryptedKeyset.class) {
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
        EncryptedKeyset defaultInstance = new EncryptedKeyset();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(EncryptedKeyset.class, defaultInstance);
    }

    public static EncryptedKeyset getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EncryptedKeyset> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
