package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.FieldSet;
import com.google.crypto.tink.shaded.protobuf.Internal;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\SchemaUtil.smali */
final class SchemaUtil {
    private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;
    private static final Class<?> GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
    private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
    private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    private SchemaUtil() {
    }

    public static void requireGeneratedMessage(Class<?> messageType) {
        Class<?> cls;
        if (!GeneratedMessageLite.class.isAssignableFrom(messageType) && (cls = GENERATED_MESSAGE_CLASS) != null && !cls.isAssignableFrom(messageType)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void writeDouble(int fieldNumber, double value, Writer writer) throws IOException {
        if (Double.compare(value, 0.0d) != 0) {
            writer.writeDouble(fieldNumber, value);
        }
    }

    public static void writeFloat(int fieldNumber, float value, Writer writer) throws IOException {
        if (Float.compare(value, 0.0f) != 0) {
            writer.writeFloat(fieldNumber, value);
        }
    }

    public static void writeInt64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeInt64(fieldNumber, value);
        }
    }

    public static void writeUInt64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeUInt64(fieldNumber, value);
        }
    }

    public static void writeSInt64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSInt64(fieldNumber, value);
        }
    }

    public static void writeFixed64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeFixed64(fieldNumber, value);
        }
    }

    public static void writeSFixed64(int fieldNumber, long value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSFixed64(fieldNumber, value);
        }
    }

    public static void writeInt32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeInt32(fieldNumber, value);
        }
    }

    public static void writeUInt32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeUInt32(fieldNumber, value);
        }
    }

    public static void writeSInt32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSInt32(fieldNumber, value);
        }
    }

    public static void writeFixed32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeFixed32(fieldNumber, value);
        }
    }

    public static void writeSFixed32(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeSFixed32(fieldNumber, value);
        }
    }

    public static void writeEnum(int fieldNumber, int value, Writer writer) throws IOException {
        if (value != 0) {
            writer.writeEnum(fieldNumber, value);
        }
    }

    public static void writeBool(int fieldNumber, boolean value, Writer writer) throws IOException {
        if (value) {
            writer.writeBool(fieldNumber, true);
        }
    }

    public static void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writeStringInternal(fieldNumber, (String) value, writer);
        } else {
            writeBytes(fieldNumber, (ByteString) value, writer);
        }
    }

    private static void writeStringInternal(int fieldNumber, String value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeString(fieldNumber, value);
        }
    }

    public static void writeBytes(int fieldNumber, ByteString value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeBytes(fieldNumber, value);
        }
    }

    public static void writeMessage(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value != null) {
            writer.writeMessage(fieldNumber, value);
        }
    }

    public static void writeDoubleList(int fieldNumber, List<Double> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeDoubleList(fieldNumber, value, packed);
        }
    }

    public static void writeFloatList(int fieldNumber, List<Float> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeFloatList(fieldNumber, value, packed);
        }
    }

    public static void writeInt64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeInt64List(fieldNumber, value, packed);
        }
    }

    public static void writeUInt64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeUInt64List(fieldNumber, value, packed);
        }
    }

    public static void writeSInt64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeSInt64List(fieldNumber, value, packed);
        }
    }

    public static void writeFixed64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeFixed64List(fieldNumber, value, packed);
        }
    }

    public static void writeSFixed64List(int fieldNumber, List<Long> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeSFixed64List(fieldNumber, value, packed);
        }
    }

    public static void writeInt32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeInt32List(fieldNumber, value, packed);
        }
    }

    public static void writeUInt32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeUInt32List(fieldNumber, value, packed);
        }
    }

    public static void writeSInt32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeSInt32List(fieldNumber, value, packed);
        }
    }

    public static void writeFixed32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeFixed32List(fieldNumber, value, packed);
        }
    }

    public static void writeSFixed32List(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeSFixed32List(fieldNumber, value, packed);
        }
    }

    public static void writeEnumList(int fieldNumber, List<Integer> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeEnumList(fieldNumber, value, packed);
        }
    }

    public static void writeBoolList(int fieldNumber, List<Boolean> value, Writer writer, boolean packed) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeBoolList(fieldNumber, value, packed);
        }
    }

    public static void writeStringList(int fieldNumber, List<String> value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeStringList(fieldNumber, value);
        }
    }

    public static void writeBytesList(int fieldNumber, List<ByteString> value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeBytesList(fieldNumber, value);
        }
    }

    public static void writeMessageList(int fieldNumber, List<?> value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeMessageList(fieldNumber, value);
        }
    }

    public static void writeMessageList(int fieldNumber, List<?> value, Writer writer, Schema schema) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeMessageList(fieldNumber, value, schema);
        }
    }

    public static void writeLazyFieldList(int fieldNumber, List<?> value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            for (Object item : value) {
                ((LazyFieldLite) item).writeTo(writer, fieldNumber);
            }
        }
    }

    public static void writeGroupList(int fieldNumber, List<?> value, Writer writer) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeGroupList(fieldNumber, value);
        }
    }

    public static void writeGroupList(int fieldNumber, List<?> value, Writer writer, Schema schema) throws IOException {
        if (value != null && !value.isEmpty()) {
            writer.writeGroupList(fieldNumber, value, schema);
        }
    }

    static int computeSizeInt64ListNoTag(List<Long> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof LongArrayList) {
            LongArrayList primitiveList = (LongArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeInt64SizeNoTag(primitiveList.getLong(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeInt64SizeNoTag(list.get(i2).longValue());
            }
        }
        return size;
    }

    static int computeSizeInt64List(int fieldNumber, List<Long> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeInt64ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (list.size() * CodedOutputStream.computeTagSize(fieldNumber)) + size;
    }

    static int computeSizeUInt64ListNoTag(List<Long> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof LongArrayList) {
            LongArrayList primitiveList = (LongArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeUInt64SizeNoTag(primitiveList.getLong(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeUInt64SizeNoTag(list.get(i2).longValue());
            }
        }
        return size;
    }

    static int computeSizeUInt64List(int fieldNumber, List<Long> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeUInt64ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (CodedOutputStream.computeTagSize(fieldNumber) * length) + size;
    }

    static int computeSizeSInt64ListNoTag(List<Long> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof LongArrayList) {
            LongArrayList primitiveList = (LongArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeSInt64SizeNoTag(primitiveList.getLong(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeSInt64SizeNoTag(list.get(i2).longValue());
            }
        }
        return size;
    }

    static int computeSizeSInt64List(int fieldNumber, List<Long> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeSInt64ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (CodedOutputStream.computeTagSize(fieldNumber) * length) + size;
    }

    static int computeSizeEnumListNoTag(List<Integer> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeEnumSizeNoTag(primitiveList.getInt(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeEnumSizeNoTag(list.get(i2).intValue());
            }
        }
        return size;
    }

    static int computeSizeEnumList(int fieldNumber, List<Integer> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeEnumListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (CodedOutputStream.computeTagSize(fieldNumber) * length) + size;
    }

    static int computeSizeInt32ListNoTag(List<Integer> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeInt32SizeNoTag(primitiveList.getInt(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeInt32SizeNoTag(list.get(i2).intValue());
            }
        }
        return size;
    }

    static int computeSizeInt32List(int fieldNumber, List<Integer> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeInt32ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (CodedOutputStream.computeTagSize(fieldNumber) * length) + size;
    }

    static int computeSizeUInt32ListNoTag(List<Integer> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeUInt32SizeNoTag(primitiveList.getInt(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeUInt32SizeNoTag(list.get(i2).intValue());
            }
        }
        return size;
    }

    static int computeSizeUInt32List(int fieldNumber, List<Integer> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeUInt32ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (CodedOutputStream.computeTagSize(fieldNumber) * length) + size;
    }

    static int computeSizeSInt32ListNoTag(List<Integer> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        if (list instanceof IntArrayList) {
            IntArrayList primitiveList = (IntArrayList) list;
            for (int i = 0; i < length; i++) {
                size += CodedOutputStream.computeSInt32SizeNoTag(primitiveList.getInt(i));
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                size += CodedOutputStream.computeSInt32SizeNoTag(list.get(i2).intValue());
            }
        }
        return size;
    }

    static int computeSizeSInt32List(int fieldNumber, List<Integer> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = computeSizeSInt32ListNoTag(list);
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(size);
        }
        return (CodedOutputStream.computeTagSize(fieldNumber) * length) + size;
    }

    static int computeSizeFixed32ListNoTag(List<?> list) {
        return list.size() * 4;
    }

    static int computeSizeFixed32List(int fieldNumber, List<?> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        if (packed) {
            int dataSize = length * 4;
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(dataSize);
        }
        int dataSize2 = CodedOutputStream.computeFixed32Size(fieldNumber, 0);
        return dataSize2 * length;
    }

    static int computeSizeFixed64ListNoTag(List<?> list) {
        return list.size() * 8;
    }

    static int computeSizeFixed64List(int fieldNumber, List<?> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        if (packed) {
            int dataSize = length * 8;
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(dataSize);
        }
        return CodedOutputStream.computeFixed64Size(fieldNumber, 0L) * length;
    }

    static int computeSizeBoolListNoTag(List<?> list) {
        return list.size();
    }

    static int computeSizeBoolList(int fieldNumber, List<?> list, boolean packed) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        if (packed) {
            return CodedOutputStream.computeTagSize(fieldNumber) + CodedOutputStream.computeLengthDelimitedFieldSize(length);
        }
        return CodedOutputStream.computeBoolSize(fieldNumber, true) * length;
    }

    static int computeSizeStringList(int fieldNumber, List<?> list) {
        int iComputeStringSizeNoTag;
        int iComputeStringSizeNoTag2;
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = CodedOutputStream.computeTagSize(fieldNumber) * length;
        if (list instanceof LazyStringList) {
            LazyStringList lazyList = (LazyStringList) list;
            for (int i = 0; i < length; i++) {
                Object value = lazyList.getRaw(i);
                if (value instanceof ByteString) {
                    iComputeStringSizeNoTag2 = CodedOutputStream.computeBytesSizeNoTag((ByteString) value);
                } else {
                    iComputeStringSizeNoTag2 = CodedOutputStream.computeStringSizeNoTag((String) value);
                }
                size += iComputeStringSizeNoTag2;
            }
        } else {
            for (int i2 = 0; i2 < length; i2++) {
                Object value2 = list.get(i2);
                if (value2 instanceof ByteString) {
                    iComputeStringSizeNoTag = CodedOutputStream.computeBytesSizeNoTag((ByteString) value2);
                } else {
                    iComputeStringSizeNoTag = CodedOutputStream.computeStringSizeNoTag((String) value2);
                }
                size += iComputeStringSizeNoTag;
            }
        }
        return size;
    }

    static int computeSizeMessage(int fieldNumber, Object value, Schema schema) {
        if (value instanceof LazyFieldLite) {
            return CodedOutputStream.computeLazyFieldSize(fieldNumber, (LazyFieldLite) value);
        }
        return CodedOutputStream.computeMessageSize(fieldNumber, (MessageLite) value, schema);
    }

    static int computeSizeMessageList(int fieldNumber, List<?> list) {
        int iComputeMessageSizeNoTag;
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = CodedOutputStream.computeTagSize(fieldNumber) * length;
        for (int i = 0; i < length; i++) {
            Object value = list.get(i);
            if (value instanceof LazyFieldLite) {
                iComputeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) value);
            } else {
                iComputeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) value);
            }
            size += iComputeMessageSizeNoTag;
        }
        return size;
    }

    static int computeSizeMessageList(int fieldNumber, List<?> list, Schema schema) {
        int iComputeMessageSizeNoTag;
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = CodedOutputStream.computeTagSize(fieldNumber) * length;
        for (int i = 0; i < length; i++) {
            Object value = list.get(i);
            if (value instanceof LazyFieldLite) {
                iComputeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) value);
            } else {
                iComputeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) value, schema);
            }
            size += iComputeMessageSizeNoTag;
        }
        return size;
    }

    static int computeSizeByteStringList(int fieldNumber, List<ByteString> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = CodedOutputStream.computeTagSize(fieldNumber) * length;
        for (int i = 0; i < list.size(); i++) {
            size += CodedOutputStream.computeBytesSizeNoTag(list.get(i));
        }
        return size;
    }

    static int computeSizeGroupList(int fieldNumber, List<MessageLite> list) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < length; i++) {
            size += CodedOutputStream.computeGroupSize(fieldNumber, list.get(i));
        }
        return size;
    }

    static int computeSizeGroupList(int fieldNumber, List<MessageLite> list, Schema schema) {
        int length = list.size();
        if (length == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < length; i++) {
            size += CodedOutputStream.computeGroupSize(fieldNumber, list.get(i), schema);
        }
        return size;
    }

    public static boolean shouldUseTableSwitch(FieldInfo[] fields) {
        if (fields.length == 0) {
            return false;
        }
        int lo = fields[0].getFieldNumber();
        int hi = fields[fields.length - 1].getFieldNumber();
        return shouldUseTableSwitch(lo, hi, fields.length);
    }

    public static boolean shouldUseTableSwitch(int lo, int hi, int numFields) {
        if (hi < 40) {
            return true;
        }
        long tableSpaceCost = (hi - lo) + 1;
        long lookupSpaceCost = (numFields * 2) + 3;
        long lookupTimeCost = numFields + 3;
        return (3 * 3) + tableSpaceCost <= (3 * lookupTimeCost) + lookupSpaceCost;
    }

    public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
        return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
        return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean proto3) {
        try {
            Class<?> clz = getUnknownFieldSetSchemaClass();
            if (clz == null) {
                return null;
            }
            return (UnknownFieldSchema) clz.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(proto3));
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> getGeneratedMessageClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessageV3");
        } catch (Throwable th) {
            return null;
        }
    }

    private static Class<?> getUnknownFieldSetSchemaClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable th) {
            return null;
        }
    }

    static Object getMapDefaultEntry(Class<?> clazz, String name) {
        try {
            Class<?> holder = Class.forName(clazz.getName() + "$" + toCamelCase(name, true) + "DefaultEntryHolder");
            Field[] fields = holder.getDeclaredFields();
            if (fields.length != 1) {
                throw new IllegalStateException("Unable to look up map field default entry holder class for " + name + " in " + clazz.getName());
            }
            return UnsafeUtil.getStaticObject(fields[0]);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    static String toCamelCase(String name, boolean capNext) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ('a' <= c && c <= 'z') {
                if (capNext) {
                    sb.append((char) (c - ' '));
                } else {
                    sb.append(c);
                }
                capNext = false;
            } else if ('A' <= c && c <= 'Z') {
                if (i == 0 && !capNext) {
                    sb.append((char) (c + ' '));
                } else {
                    sb.append(c);
                }
                capNext = false;
            } else if ('0' <= c && c <= '9') {
                sb.append(c);
                capNext = true;
            } else {
                capNext = true;
            }
        }
        return sb.toString();
    }

    static boolean safeEquals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    static <T> void mergeMap(MapFieldSchema mapFieldSchema, T message, T o, long offset) {
        Object merged = mapFieldSchema.mergeFrom(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(o, offset));
        UnsafeUtil.putObject(message, offset, merged);
    }

    static <T, FT extends FieldSet.FieldDescriptorLite<FT>> void mergeExtensions(ExtensionSchema<FT> schema, T message, T other) {
        FieldSet<T> extensions = schema.getExtensions(other);
        if (!extensions.isEmpty()) {
            schema.getMutableExtensions(message).mergeFrom(extensions);
        }
    }

    static <T, UT, UB> void mergeUnknownFields(UnknownFieldSchema<UT, UB> schema, T message, T other) {
        UT messageUnknowns = schema.getFromMessage(message);
        UT otherUnknowns = schema.getFromMessage(other);
        UT merged = schema.merge(messageUnknowns, otherUnknowns);
        schema.setToMessage(message, merged);
    }

    static <UT, UB> UB filterUnknownEnumList(int i, List<Integer> list, Internal.EnumLiteMap<?> enumLiteMap, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumLiteMap == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int i2 = 0;
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (enumLiteMap.findValueByNumber(iIntValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) storeUnknownEnum(i, iIntValue, ub, unknownFieldSchema);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (enumLiteMap.findValueByNumber(iIntValue2) == null) {
                    ub = (UB) storeUnknownEnum(i, iIntValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB filterUnknownEnumList(int i, List<Integer> list, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumVerifier == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int i2 = 0;
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (enumVerifier.isInRange(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) storeUnknownEnum(i, iIntValue, ub, unknownFieldSchema);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!enumVerifier.isInRange(iIntValue2)) {
                    ub = (UB) storeUnknownEnum(i, iIntValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    static <UT, UB> UB storeUnknownEnum(int i, int i2, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (ub == null) {
            ub = unknownFieldSchema.newBuilder();
        }
        unknownFieldSchema.addVarint(ub, i, i2);
        return ub;
    }
}
