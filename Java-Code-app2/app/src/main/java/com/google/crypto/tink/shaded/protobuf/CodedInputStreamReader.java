package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\CodedInputStreamReader.smali */
final class CodedInputStreamReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;
    private static final int NEXT_TAG_UNSET = 0;
    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag = 0;
    private int tag;

    public static CodedInputStreamReader forCodedInput(CodedInputStream input) {
        if (input.wrapper != null) {
            return input.wrapper;
        }
        return new CodedInputStreamReader(input);
    }

    private CodedInputStreamReader(CodedInputStream input) {
        CodedInputStream codedInputStream = (CodedInputStream) Internal.checkNotNull(input, "input");
        this.input = codedInputStream;
        codedInputStream.wrapper = this;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean shouldDiscardUnknownFields() {
        return this.input.shouldDiscardUnknownFields();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int getFieldNumber() throws IOException {
        int i = this.nextTag;
        if (i != 0) {
            this.tag = i;
            this.nextTag = 0;
        } else {
            this.tag = this.input.readTag();
        }
        int i2 = this.tag;
        if (i2 == 0 || i2 == this.endGroupTag) {
            return Integer.MAX_VALUE;
        }
        return WireFormat.getTagFieldNumber(i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int getTag() {
        return this.tag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean skipField() throws IOException {
        int i;
        if (this.input.isAtEnd() || (i = this.tag) == this.endGroupTag) {
            return false;
        }
        return this.input.skipField(i);
    }

    private void requireWireType(int requiredWireType) throws IOException {
        if (WireFormat.getTagWireType(this.tag) != requiredWireType) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public double readDouble() throws IOException {
        requireWireType(1);
        return this.input.readDouble();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public float readFloat() throws IOException {
        requireWireType(5);
        return this.input.readFloat();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readUInt64() throws IOException {
        requireWireType(0);
        return this.input.readUInt64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readInt64() throws IOException {
        requireWireType(0);
        return this.input.readInt64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readInt32() throws IOException {
        requireWireType(0);
        return this.input.readInt32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readFixed64() throws IOException {
        requireWireType(1);
        return this.input.readFixed64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readFixed32() throws IOException {
        requireWireType(5);
        return this.input.readFixed32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean readBool() throws IOException {
        requireWireType(0);
        return this.input.readBool();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public String readString() throws IOException {
        requireWireType(2);
        return this.input.readString();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public String readStringRequireUtf8() throws IOException {
        requireWireType(2);
        return this.input.readStringRequireUtf8();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> T readMessage(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(2);
        return (T) readMessage(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> T readMessageBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(2);
        return (T) readMessage(schema, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> T readGroup(Class<T> cls, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(3);
        return (T) readGroup(Protobuf.getInstance().schemaFor((Class) cls), extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> T readGroupBySchemaWithCheck(Schema<T> schema, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        requireWireType(3);
        return (T) readGroup(schema, extensionRegistryLite);
    }

    private <T> T readMessage(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int size = this.input.readUInt32();
        if (this.input.recursionDepth >= this.input.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int prevLimit = this.input.pushLimit(size);
        T message = schema.newInstance();
        this.input.recursionDepth++;
        schema.mergeFrom(message, this, extensionRegistry);
        schema.makeImmutable(message);
        this.input.checkLastTagWas(0);
        CodedInputStream codedInputStream = this.input;
        codedInputStream.recursionDepth--;
        this.input.popLimit(prevLimit);
        return message;
    }

    private <T> T readGroup(Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int prevEndGroupTag = this.endGroupTag;
        this.endGroupTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.tag), 4);
        try {
            T message = schema.newInstance();
            schema.mergeFrom(message, this, extensionRegistry);
            schema.makeImmutable(message);
            if (this.tag != this.endGroupTag) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            return message;
        } finally {
            this.endGroupTag = prevEndGroupTag;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public ByteString readBytes() throws IOException {
        requireWireType(2);
        return this.input.readBytes();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readUInt32() throws IOException {
        requireWireType(0);
        return this.input.readUInt32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readEnum() throws IOException {
        requireWireType(0);
        return this.input.readEnum();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readSFixed32() throws IOException {
        requireWireType(5);
        return this.input.readSFixed32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readSFixed64() throws IOException {
        requireWireType(1);
        return this.input.readSFixed64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public int readSInt32() throws IOException {
        requireWireType(0);
        return this.input.readSInt32();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public long readSInt64() throws IOException {
        requireWireType(0);
        return this.input.readSInt64();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readDoubleList(List<Double> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof DoubleArrayList) {
            DoubleArrayList plist = (DoubleArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 1) {
                do {
                    plist.addDouble(this.input.readDouble());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                verifyPackedFixed64Length(bytes);
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addDouble(this.input.readDouble());
                } while (this.input.getTotalBytesRead() < endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 1) {
            do {
                target.add(Double.valueOf(this.input.readDouble()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            verifyPackedFixed64Length(bytes2);
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Double.valueOf(this.input.readDouble()));
            } while (this.input.getTotalBytesRead() < endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFloatList(List<Float> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof FloatArrayList) {
            FloatArrayList plist = (FloatArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                verifyPackedFixed32Length(bytes);
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addFloat(this.input.readFloat());
                } while (this.input.getTotalBytesRead() < endPos);
                return;
            }
            if (tagWireType == 5) {
                do {
                    plist.addFloat(this.input.readFloat());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            verifyPackedFixed32Length(bytes2);
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Float.valueOf(this.input.readFloat()));
            } while (this.input.getTotalBytesRead() < endPos2);
            return;
        }
        if (tagWireType2 == 5) {
            do {
                target.add(Float.valueOf(this.input.readFloat()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readUInt64List(List<Long> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addLong(this.input.readUInt64());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addLong(this.input.readUInt64());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Long.valueOf(this.input.readUInt64()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Long.valueOf(this.input.readUInt64()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readInt64List(List<Long> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addLong(this.input.readInt64());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addLong(this.input.readInt64());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Long.valueOf(this.input.readInt64()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Long.valueOf(this.input.readInt64()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readInt32List(List<Integer> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addInt(this.input.readInt32());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addInt(this.input.readInt32());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Integer.valueOf(this.input.readInt32()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Integer.valueOf(this.input.readInt32()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFixed64List(List<Long> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 1) {
                do {
                    plist.addLong(this.input.readFixed64());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                verifyPackedFixed64Length(bytes);
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addLong(this.input.readFixed64());
                } while (this.input.getTotalBytesRead() < endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 1) {
            do {
                target.add(Long.valueOf(this.input.readFixed64()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            verifyPackedFixed64Length(bytes2);
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Long.valueOf(this.input.readFixed64()));
            } while (this.input.getTotalBytesRead() < endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFixed32List(List<Integer> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                verifyPackedFixed32Length(bytes);
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addInt(this.input.readFixed32());
                } while (this.input.getTotalBytesRead() < endPos);
                return;
            }
            if (tagWireType == 5) {
                do {
                    plist.addInt(this.input.readFixed32());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            verifyPackedFixed32Length(bytes2);
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Integer.valueOf(this.input.readFixed32()));
            } while (this.input.getTotalBytesRead() < endPos2);
            return;
        }
        if (tagWireType2 == 5) {
            do {
                target.add(Integer.valueOf(this.input.readFixed32()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readBoolList(List<Boolean> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof BooleanArrayList) {
            BooleanArrayList plist = (BooleanArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addBoolean(this.input.readBool());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addBoolean(this.input.readBool());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Boolean.valueOf(this.input.readBool()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Boolean.valueOf(this.input.readBool()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readStringList(List<String> target) throws IOException {
        readStringListInternal(target, false);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readStringListRequireUtf8(List<String> target) throws IOException {
        readStringListInternal(target, true);
    }

    public void readStringListInternal(List<String> target, boolean requireUtf8) throws IOException {
        int nextTag;
        int nextTag2;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        if ((target instanceof LazyStringList) && !requireUtf8) {
            LazyStringList lazyList = (LazyStringList) target;
            do {
                lazyList.add(readBytes());
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag2 = this.input.readTag();
                }
            } while (nextTag2 == this.tag);
            this.nextTag = nextTag2;
            return;
        }
        do {
            target.add(requireUtf8 ? readStringRequireUtf8() : readString());
            if (this.input.isAtEnd()) {
                return;
            } else {
                nextTag = this.input.readTag();
            }
        } while (nextTag == this.tag);
        this.nextTag = nextTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void readMessageList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
        Schema<T> schema = Protobuf.getInstance().schemaFor((Class) targetType);
        readMessageList(target, schema, extensionRegistry);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void readMessageList(List<T> list, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int nextTag;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int listTag = this.tag;
        do {
            list.add(readMessage(schema, extensionRegistry));
            if (this.input.isAtEnd() || this.nextTag != 0) {
                return;
            } else {
                nextTag = this.input.readTag();
            }
        } while (nextTag == listTag);
        this.nextTag = nextTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void readGroupList(List<T> target, Class<T> targetType, ExtensionRegistryLite extensionRegistry) throws IOException {
        Schema<T> schema = Protobuf.getInstance().schemaFor((Class) targetType);
        readGroupList(target, schema, extensionRegistry);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public <T> void readGroupList(List<T> list, Schema<T> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        int nextTag;
        if (WireFormat.getTagWireType(this.tag) != 3) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int listTag = this.tag;
        do {
            list.add(readGroup(schema, extensionRegistry));
            if (this.input.isAtEnd() || this.nextTag != 0) {
                return;
            } else {
                nextTag = this.input.readTag();
            }
        } while (nextTag == listTag);
        this.nextTag = nextTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readBytesList(List<ByteString> target) throws IOException {
        int nextTag;
        if (WireFormat.getTagWireType(this.tag) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            target.add(readBytes());
            if (this.input.isAtEnd()) {
                return;
            } else {
                nextTag = this.input.readTag();
            }
        } while (nextTag == this.tag);
        this.nextTag = nextTag;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readUInt32List(List<Integer> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addInt(this.input.readUInt32());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addInt(this.input.readUInt32());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Integer.valueOf(this.input.readUInt32()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Integer.valueOf(this.input.readUInt32()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readEnumList(List<Integer> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addInt(this.input.readEnum());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addInt(this.input.readEnum());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Integer.valueOf(this.input.readEnum()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Integer.valueOf(this.input.readEnum()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSFixed32List(List<Integer> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                verifyPackedFixed32Length(bytes);
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addInt(this.input.readSFixed32());
                } while (this.input.getTotalBytesRead() < endPos);
                return;
            }
            if (tagWireType == 5) {
                do {
                    plist.addInt(this.input.readSFixed32());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            verifyPackedFixed32Length(bytes2);
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Integer.valueOf(this.input.readSFixed32()));
            } while (this.input.getTotalBytesRead() < endPos2);
            return;
        }
        if (tagWireType2 == 5) {
            do {
                target.add(Integer.valueOf(this.input.readSFixed32()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSFixed64List(List<Long> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 1) {
                do {
                    plist.addLong(this.input.readSFixed64());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                verifyPackedFixed64Length(bytes);
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addLong(this.input.readSFixed64());
                } while (this.input.getTotalBytesRead() < endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 1) {
            do {
                target.add(Long.valueOf(this.input.readSFixed64()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            verifyPackedFixed64Length(bytes2);
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Long.valueOf(this.input.readSFixed64()));
            } while (this.input.getTotalBytesRead() < endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSInt32List(List<Integer> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof IntArrayList) {
            IntArrayList plist = (IntArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addInt(this.input.readSInt32());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addInt(this.input.readSInt32());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Integer.valueOf(this.input.readSInt32()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Integer.valueOf(this.input.readSInt32()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSInt64List(List<Long> target) throws IOException {
        int nextTag;
        int nextTag2;
        if (target instanceof LongArrayList) {
            LongArrayList plist = (LongArrayList) target;
            int tagWireType = WireFormat.getTagWireType(this.tag);
            if (tagWireType == 0) {
                do {
                    plist.addLong(this.input.readSInt64());
                    if (this.input.isAtEnd()) {
                        return;
                    } else {
                        nextTag2 = this.input.readTag();
                    }
                } while (nextTag2 == this.tag);
                this.nextTag = nextTag2;
                return;
            }
            if (tagWireType == 2) {
                int bytes = this.input.readUInt32();
                int endPos = this.input.getTotalBytesRead() + bytes;
                do {
                    plist.addLong(this.input.readSInt64());
                } while (this.input.getTotalBytesRead() < endPos);
                requirePosition(endPos);
                return;
            }
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int tagWireType2 = WireFormat.getTagWireType(this.tag);
        if (tagWireType2 == 0) {
            do {
                target.add(Long.valueOf(this.input.readSInt64()));
                if (this.input.isAtEnd()) {
                    return;
                } else {
                    nextTag = this.input.readTag();
                }
            } while (nextTag == this.tag);
            this.nextTag = nextTag;
            return;
        }
        if (tagWireType2 == 2) {
            int bytes2 = this.input.readUInt32();
            int endPos2 = this.input.getTotalBytesRead() + bytes2;
            do {
                target.add(Long.valueOf(this.input.readSInt64()));
            } while (this.input.getTotalBytesRead() < endPos2);
            requirePosition(endPos2);
            return;
        }
        throw InvalidProtocolBufferException.invalidWireType();
    }

    private void verifyPackedFixed64Length(int bytes) throws IOException {
        if ((bytes & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
    
        r10.put(r3, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0069, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Reader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <K, V> void readMap(java.util.Map<K, V> r10, com.google.crypto.tink.shaded.protobuf.MapEntryLite.Metadata<K, V> r11, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite r12) throws java.io.IOException {
        /*
            r9 = this;
            r0 = 2
            r9.requireWireType(r0)
            com.google.crypto.tink.shaded.protobuf.CodedInputStream r1 = r9.input
            int r1 = r1.readUInt32()
            com.google.crypto.tink.shaded.protobuf.CodedInputStream r2 = r9.input
            int r2 = r2.pushLimit(r1)
            K r3 = r11.defaultKey
            V r4 = r11.defaultValue
        L14:
            int r5 = r9.getFieldNumber()     // Catch: java.lang.Throwable -> L6a
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r5 == r6) goto L60
            com.google.crypto.tink.shaded.protobuf.CodedInputStream r6 = r9.input     // Catch: java.lang.Throwable -> L6a
            boolean r6 = r6.isAtEnd()     // Catch: java.lang.Throwable -> L6a
            if (r6 == 0) goto L26
            goto L60
        L26:
            r6 = 1
            java.lang.String r7 = "Unable to parse map entry."
            if (r5 == r6) goto L48
            if (r5 == r0) goto L3a
            boolean r6 = r9.skipField()     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            if (r6 == 0) goto L34
            goto L51
        L34:
            com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException r6 = new com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            r6.<init>(r7)     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            throw r6     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
        L3a:
            com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r6 = r11.valueType     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            V r8 = r11.defaultValue     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            java.lang.Class r8 = r8.getClass()     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            java.lang.Object r6 = r9.readField(r6, r8, r12)     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            r4 = r6
            goto L51
        L48:
            com.google.crypto.tink.shaded.protobuf.WireFormat$FieldType r6 = r11.keyType     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            r8 = 0
            java.lang.Object r6 = r9.readField(r6, r8, r8)     // Catch: com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.InvalidWireTypeException -> L52 java.lang.Throwable -> L6a
            r3 = r6
        L51:
            goto L59
        L52:
            r6 = move-exception
            boolean r8 = r9.skipField()     // Catch: java.lang.Throwable -> L6a
            if (r8 == 0) goto L5a
        L59:
            goto L14
        L5a:
            com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException r0 = new com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException     // Catch: java.lang.Throwable -> L6a
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L6a
            throw r0     // Catch: java.lang.Throwable -> L6a
        L60:
            r10.put(r3, r4)     // Catch: java.lang.Throwable -> L6a
            com.google.crypto.tink.shaded.protobuf.CodedInputStream r0 = r9.input
            r0.popLimit(r2)
            return
        L6a:
            r0 = move-exception
            com.google.crypto.tink.shaded.protobuf.CodedInputStream r5 = r9.input
            r5.popLimit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.readMap(java.util.Map, com.google.crypto.tink.shaded.protobuf.MapEntryLite$Metadata, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite):void");
    }

    /* renamed from: com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\CodedInputStreamReader$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private Object readField(WireFormat.FieldType fieldType, Class<?> messageType, ExtensionRegistryLite extensionRegistry) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                return Boolean.valueOf(readBool());
            case 2:
                return readBytes();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(readEnum());
            case 5:
                return Integer.valueOf(readFixed32());
            case 6:
                return Long.valueOf(readFixed64());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(readInt32());
            case 9:
                return Long.valueOf(readInt64());
            case 10:
                return readMessage(messageType, extensionRegistry);
            case 11:
                return Integer.valueOf(readSFixed32());
            case 12:
                return Long.valueOf(readSFixed64());
            case 13:
                return Integer.valueOf(readSInt32());
            case 14:
                return Long.valueOf(readSInt64());
            case 15:
                return readStringRequireUtf8();
            case 16:
                return Integer.valueOf(readUInt32());
            case 17:
                return Long.valueOf(readUInt64());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private void verifyPackedFixed32Length(int bytes) throws IOException {
        if ((bytes & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    private void requirePosition(int expectedPosition) throws IOException {
        if (this.input.getTotalBytesRead() != expectedPosition) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
