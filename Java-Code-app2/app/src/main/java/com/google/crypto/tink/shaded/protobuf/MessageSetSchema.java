package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.LazyField;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MessageSetSchema.smali */
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite defaultInstance) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.hasExtensions(defaultInstance);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
    }

    static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite defaultInstance) {
        return new MessageSetSchema<>(unknownFieldSchema, extensionSchema, defaultInstance);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        return (T) this.defaultInstance.newBuilderForType().buildPartial();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T message, T other) {
        Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
        Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
        if (!messageUnknown.equals(otherUnknown)) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(message).equals(this.extensionSchema.getExtensions(other));
        }
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions) {
            return (hashCode * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, T other) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        Iterator<?> iterator = this.extensionSchema.getExtensions(message).iterator();
        while (iterator.hasNext()) {
            Map.Entry<?, ?> extension = (Map.Entry) iterator.next();
            FieldSet.FieldDescriptorLite<?> fd = (FieldSet.FieldDescriptorLite) extension.getKey();
            if (fd.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fd.isRepeated() || fd.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if (extension instanceof LazyField.LazyEntry) {
                writer.writeMessageSetItem(fd.getNumber(), ((LazyField.LazyEntry) extension).getField().toByteString());
            } else {
                writer.writeMessageSetItem(fd.getNumber(), extension.getValue());
            }
        }
        writeUnknownFieldsHelper(this.unknownFieldSchema, message, writer);
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T message, Writer writer) throws IOException {
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(message), writer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f0 A[SYNTHETIC] */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mergeFrom(T r18, byte[] r19, int r20, int r21, com.google.crypto.tink.shaded.protobuf.ArrayDecoders.Registers r22) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.MessageSetSchema.mergeFrom(java.lang.Object, byte[], int, int, com.google.crypto.tink.shaded.protobuf.ArrayDecoders$Registers):void");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        UB unknownFields = unknownFieldSchema.getBuilderFromMessage(message);
        FieldSet<ET> extensions = extensionSchema.getMutableExtensions(message);
        do {
            try {
                int number = reader.getFieldNumber();
                if (number == Integer.MAX_VALUE) {
                    return;
                }
            } finally {
                unknownFieldSchema.setBuilderToMessage(message, unknownFields);
            }
        } while (parseMessageSetItemOrUnknownField(reader, extensionRegistry, extensionSchema, extensions, unknownFieldSchema, unknownFields));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T message) {
        this.unknownFieldSchema.makeImmutable(message);
        this.extensionSchema.makeImmutable(message);
    }

    /* JADX WARN: Incorrect condition in loop: B:16:0x0034 */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <UT, UB, ET extends com.google.crypto.tink.shaded.protobuf.FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(com.google.crypto.tink.shaded.protobuf.Reader r9, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite r10, com.google.crypto.tink.shaded.protobuf.ExtensionSchema<ET> r11, com.google.crypto.tink.shaded.protobuf.FieldSet<ET> r12, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema<UT, UB> r13, UB r14) throws java.io.IOException {
        /*
            r8 = this;
            int r0 = r9.getTag()
            int r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.MESSAGE_SET_ITEM_TAG
            r2 = 1
            if (r0 == r1) goto L2a
            int r1 = com.google.crypto.tink.shaded.protobuf.WireFormat.getTagWireType(r0)
            r3 = 2
            if (r1 != r3) goto L25
            com.google.crypto.tink.shaded.protobuf.MessageLite r1 = r8.defaultInstance
            int r3 = com.google.crypto.tink.shaded.protobuf.WireFormat.getTagFieldNumber(r0)
            java.lang.Object r1 = r11.findExtensionByNumber(r10, r1, r3)
            if (r1 == 0) goto L20
            r11.parseLengthPrefixedMessageSetItem(r9, r1, r10, r12)
            return r2
        L20:
            boolean r2 = r13.mergeOneFieldFrom(r14, r9)
            return r2
        L25:
            boolean r1 = r9.skipField()
            return r1
        L2a:
            r1 = 0
            r3 = 0
            r4 = 0
        L2d:
            int r5 = r9.getFieldNumber()
            r6 = 2147483647(0x7fffffff, float:NaN)
            if (r5 != r6) goto L37
            goto L60
        L37:
            int r6 = r9.getTag()
            int r7 = com.google.crypto.tink.shaded.protobuf.WireFormat.MESSAGE_SET_TYPE_ID_TAG
            if (r6 != r7) goto L4a
            int r1 = r9.readUInt32()
            com.google.crypto.tink.shaded.protobuf.MessageLite r7 = r8.defaultInstance
            java.lang.Object r4 = r11.findExtensionByNumber(r10, r7, r1)
            goto L2d
        L4a:
            int r7 = com.google.crypto.tink.shaded.protobuf.WireFormat.MESSAGE_SET_MESSAGE_TAG
            if (r6 != r7) goto L59
            if (r4 == 0) goto L54
            r11.parseLengthPrefixedMessageSetItem(r9, r4, r10, r12)
            goto L2d
        L54:
            com.google.crypto.tink.shaded.protobuf.ByteString r3 = r9.readBytes()
            goto L2d
        L59:
            boolean r7 = r9.skipField()
            if (r7 != 0) goto L79
        L60:
            int r5 = r9.getTag()
            int r6 = com.google.crypto.tink.shaded.protobuf.WireFormat.MESSAGE_SET_ITEM_END_TAG
            if (r5 != r6) goto L74
            if (r3 == 0) goto L73
            if (r4 == 0) goto L70
            r11.parseMessageSetItem(r3, r4, r10, r12)
            goto L73
        L70:
            r13.addLengthDelimited(r14, r1, r3)
        L73:
            return r2
        L74:
            com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException r2 = com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException.invalidEndTag()
            throw r2
        L79:
            goto L2d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.MessageSetSchema.parseMessageSetItemOrUnknownField(com.google.crypto.tink.shaded.protobuf.Reader, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite, com.google.crypto.tink.shaded.protobuf.ExtensionSchema, com.google.crypto.tink.shaded.protobuf.FieldSet, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema, java.lang.Object):boolean");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T message) {
        return this.extensionSchema.getExtensions(message).isInitialized();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T message) {
        int size = 0 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size + this.extensionSchema.getExtensions(message).getMessageSetSerializedSize();
        }
        return size;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSizeAsMessageSet(unknowns);
    }
}
