package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\ExtensionSchemaLite.smali */
final class ExtensionSchemaLite extends ExtensionSchema<GeneratedMessageLite.ExtensionDescriptor> {
    ExtensionSchemaLite() {
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    boolean hasExtensions(MessageLite prototype) {
        return prototype instanceof GeneratedMessageLite.ExtendableMessage;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    FieldSet<GeneratedMessageLite.ExtensionDescriptor> getExtensions(Object message) {
        return ((GeneratedMessageLite.ExtendableMessage) message).extensions;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void setExtensions(Object message, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions) {
        ((GeneratedMessageLite.ExtendableMessage) message).extensions = extensions;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    FieldSet<GeneratedMessageLite.ExtensionDescriptor> getMutableExtensions(Object message) {
        return ((GeneratedMessageLite.ExtendableMessage) message).ensureExtensionsAreMutable();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void makeImmutable(Object message) {
        getExtensions(message).makeImmutable();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    <UT, UB> UB parseExtension(Reader reader, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSet, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) throws IOException {
        Object field;
        ArrayList arrayList;
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        int number = generatedExtension.getNumber();
        if (generatedExtension.descriptor.isRepeated() && generatedExtension.descriptor.isPacked()) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedExtension.getLiteType().ordinal()]) {
                case 1:
                    ArrayList arrayList2 = new ArrayList();
                    reader.readDoubleList(arrayList2);
                    arrayList = arrayList2;
                    break;
                case 2:
                    ArrayList arrayList3 = new ArrayList();
                    reader.readFloatList(arrayList3);
                    arrayList = arrayList3;
                    break;
                case 3:
                    ArrayList arrayList4 = new ArrayList();
                    reader.readInt64List(arrayList4);
                    arrayList = arrayList4;
                    break;
                case 4:
                    ArrayList arrayList5 = new ArrayList();
                    reader.readUInt64List(arrayList5);
                    arrayList = arrayList5;
                    break;
                case 5:
                    ArrayList arrayList6 = new ArrayList();
                    reader.readInt32List(arrayList6);
                    arrayList = arrayList6;
                    break;
                case 6:
                    ArrayList arrayList7 = new ArrayList();
                    reader.readFixed64List(arrayList7);
                    arrayList = arrayList7;
                    break;
                case 7:
                    ArrayList arrayList8 = new ArrayList();
                    reader.readFixed32List(arrayList8);
                    arrayList = arrayList8;
                    break;
                case 8:
                    ArrayList arrayList9 = new ArrayList();
                    reader.readBoolList(arrayList9);
                    arrayList = arrayList9;
                    break;
                case 9:
                    ArrayList arrayList10 = new ArrayList();
                    reader.readUInt32List(arrayList10);
                    arrayList = arrayList10;
                    break;
                case 10:
                    ArrayList arrayList11 = new ArrayList();
                    reader.readSFixed32List(arrayList11);
                    arrayList = arrayList11;
                    break;
                case 11:
                    ArrayList arrayList12 = new ArrayList();
                    reader.readSFixed64List(arrayList12);
                    arrayList = arrayList12;
                    break;
                case 12:
                    ArrayList arrayList13 = new ArrayList();
                    reader.readSInt32List(arrayList13);
                    arrayList = arrayList13;
                    break;
                case 13:
                    ArrayList arrayList14 = new ArrayList();
                    reader.readSInt64List(arrayList14);
                    arrayList = arrayList14;
                    break;
                case 14:
                    ArrayList arrayList15 = new ArrayList();
                    reader.readEnumList(arrayList15);
                    ub = (UB) SchemaUtil.filterUnknownEnumList(number, arrayList15, generatedExtension.descriptor.getEnumType(), ub, unknownFieldSchema);
                    arrayList = arrayList15;
                    break;
                default:
                    throw new IllegalStateException("Type cannot be packed: " + generatedExtension.descriptor.getLiteType());
            }
            fieldSet.setField(generatedExtension.descriptor, arrayList);
        } else {
            Object objValueOf = null;
            if (generatedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
                int int32 = reader.readInt32();
                if (generatedExtension.descriptor.getEnumType().findValueByNumber(int32) == null) {
                    return (UB) SchemaUtil.storeUnknownEnum(number, int32, ub, unknownFieldSchema);
                }
                objValueOf = Integer.valueOf(int32);
            } else {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedExtension.getLiteType().ordinal()]) {
                    case 1:
                        objValueOf = Double.valueOf(reader.readDouble());
                        break;
                    case 2:
                        objValueOf = Float.valueOf(reader.readFloat());
                        break;
                    case 3:
                        objValueOf = Long.valueOf(reader.readInt64());
                        break;
                    case 4:
                        objValueOf = Long.valueOf(reader.readUInt64());
                        break;
                    case 5:
                        objValueOf = Integer.valueOf(reader.readInt32());
                        break;
                    case 6:
                        objValueOf = Long.valueOf(reader.readFixed64());
                        break;
                    case 7:
                        objValueOf = Integer.valueOf(reader.readFixed32());
                        break;
                    case 8:
                        objValueOf = Boolean.valueOf(reader.readBool());
                        break;
                    case 9:
                        objValueOf = Integer.valueOf(reader.readUInt32());
                        break;
                    case 10:
                        objValueOf = Integer.valueOf(reader.readSFixed32());
                        break;
                    case 11:
                        objValueOf = Long.valueOf(reader.readSFixed64());
                        break;
                    case 12:
                        objValueOf = Integer.valueOf(reader.readSInt32());
                        break;
                    case 13:
                        objValueOf = Long.valueOf(reader.readSInt64());
                        break;
                    case 14:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case 15:
                        objValueOf = reader.readBytes();
                        break;
                    case 16:
                        objValueOf = reader.readString();
                        break;
                    case 17:
                        objValueOf = reader.readGroup(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                    case 18:
                        objValueOf = reader.readMessage(generatedExtension.getMessageDefaultInstance().getClass(), extensionRegistryLite);
                        break;
                }
            }
            if (generatedExtension.isRepeated()) {
                fieldSet.addRepeatedField(generatedExtension.descriptor, objValueOf);
            } else {
                int i = AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedExtension.getLiteType().ordinal()];
                if ((i == 17 || i == 18) && (field = fieldSet.getField(generatedExtension.descriptor)) != null) {
                    objValueOf = Internal.mergeMessage(field, objValueOf);
                }
                fieldSet.setField(generatedExtension.descriptor, objValueOf);
            }
        }
        return ub;
    }

    /* renamed from: com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\ExtensionSchemaLite$1.smali */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.GROUP.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    int extensionNumber(Map.Entry<?, ?> extension) {
        GeneratedMessageLite.ExtensionDescriptor descriptor = (GeneratedMessageLite.ExtensionDescriptor) extension.getKey();
        return descriptor.getNumber();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void serializeExtension(Writer writer, Map.Entry<?, ?> extension) throws IOException {
        GeneratedMessageLite.ExtensionDescriptor descriptor = (GeneratedMessageLite.ExtensionDescriptor) extension.getKey();
        if (descriptor.isRepeated()) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[descriptor.getLiteType().ordinal()]) {
                case 1:
                    SchemaUtil.writeDoubleList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 2:
                    SchemaUtil.writeFloatList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 3:
                    SchemaUtil.writeInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 4:
                    SchemaUtil.writeUInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 5:
                    SchemaUtil.writeInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 6:
                    SchemaUtil.writeFixed64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 7:
                    SchemaUtil.writeFixed32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 8:
                    SchemaUtil.writeBoolList(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 9:
                    SchemaUtil.writeUInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 10:
                    SchemaUtil.writeSFixed32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 11:
                    SchemaUtil.writeSFixed64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 12:
                    SchemaUtil.writeSInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 13:
                    SchemaUtil.writeSInt64List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 14:
                    SchemaUtil.writeInt32List(descriptor.getNumber(), (List) extension.getValue(), writer, descriptor.isPacked());
                    break;
                case 15:
                    SchemaUtil.writeBytesList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    break;
                case 16:
                    SchemaUtil.writeStringList(descriptor.getNumber(), (List) extension.getValue(), writer);
                    break;
                case 17:
                    List<?> data = (List) extension.getValue();
                    if (data != null && !data.isEmpty()) {
                        SchemaUtil.writeGroupList(descriptor.getNumber(), (List) extension.getValue(), writer, Protobuf.getInstance().schemaFor((Class) data.get(0).getClass()));
                        break;
                    }
                    break;
                case 18:
                    List<?> data2 = (List) extension.getValue();
                    if (data2 != null && !data2.isEmpty()) {
                        SchemaUtil.writeMessageList(descriptor.getNumber(), (List) extension.getValue(), writer, Protobuf.getInstance().schemaFor((Class) data2.get(0).getClass()));
                        break;
                    }
                    break;
            }
        }
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[descriptor.getLiteType().ordinal()]) {
            case 1:
                writer.writeDouble(descriptor.getNumber(), ((Double) extension.getValue()).doubleValue());
                break;
            case 2:
                writer.writeFloat(descriptor.getNumber(), ((Float) extension.getValue()).floatValue());
                break;
            case 3:
                writer.writeInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 4:
                writer.writeUInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 5:
                writer.writeInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 6:
                writer.writeFixed64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 7:
                writer.writeFixed32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 8:
                writer.writeBool(descriptor.getNumber(), ((Boolean) extension.getValue()).booleanValue());
                break;
            case 9:
                writer.writeUInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 10:
                writer.writeSFixed32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 11:
                writer.writeSFixed64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 12:
                writer.writeSInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 13:
                writer.writeSInt64(descriptor.getNumber(), ((Long) extension.getValue()).longValue());
                break;
            case 14:
                writer.writeInt32(descriptor.getNumber(), ((Integer) extension.getValue()).intValue());
                break;
            case 15:
                writer.writeBytes(descriptor.getNumber(), (ByteString) extension.getValue());
                break;
            case 16:
                writer.writeString(descriptor.getNumber(), (String) extension.getValue());
                break;
            case 17:
                writer.writeGroup(descriptor.getNumber(), extension.getValue(), Protobuf.getInstance().schemaFor((Class) extension.getValue().getClass()));
                break;
            case 18:
                writer.writeMessage(descriptor.getNumber(), extension.getValue(), Protobuf.getInstance().schemaFor((Class) extension.getValue().getClass()));
                break;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    Object findExtensionByNumber(ExtensionRegistryLite extensionRegistry, MessageLite defaultInstance, int number) {
        return extensionRegistry.findLiteExtensionByNumber(defaultInstance, number);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void parseLengthPrefixedMessageSetItem(Reader reader, Object extensionObject, ExtensionRegistryLite extensionRegistry, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions) throws IOException {
        GeneratedMessageLite.GeneratedExtension<?, ?> extension = (GeneratedMessageLite.GeneratedExtension) extensionObject;
        Object value = reader.readMessage(extension.getMessageDefaultInstance().getClass(), extensionRegistry);
        extensions.setField(extension.descriptor, value);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void parseMessageSetItem(ByteString data, Object extensionObject, ExtensionRegistryLite extensionRegistry, FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions) throws IOException {
        GeneratedMessageLite.GeneratedExtension<?, ?> extension = (GeneratedMessageLite.GeneratedExtension) extensionObject;
        MessageLite messageLiteBuildPartial = extension.getMessageDefaultInstance().newBuilderForType().buildPartial();
        Reader reader = BinaryReader.newInstance(ByteBuffer.wrap(data.toByteArray()), true);
        Protobuf.getInstance().mergeFrom(messageLiteBuildPartial, reader, extensionRegistry);
        extensions.setField(extension.descriptor, messageLiteBuildPartial);
        if (reader.getFieldNumber() != Integer.MAX_VALUE) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }
}
