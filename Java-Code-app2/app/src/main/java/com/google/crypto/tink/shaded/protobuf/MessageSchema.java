package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.Internal;
import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MessageSchema.smali */
final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean proto3, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.proto3 = proto3;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <T> com.google.crypto.tink.shaded.protobuf.MessageSchema<T> newSchemaForRawMessageInfo(com.google.crypto.tink.shaded.protobuf.RawMessageInfo r43, com.google.crypto.tink.shaded.protobuf.NewInstanceSchema r44, com.google.crypto.tink.shaded.protobuf.ListFieldSchema r45, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema<?, ?> r46, com.google.crypto.tink.shaded.protobuf.ExtensionSchema<?> r47, com.google.crypto.tink.shaded.protobuf.MapFieldSchema r48) {
        /*
            Method dump skipped, instructions count: 1117
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.MessageSchema.newSchemaForRawMessageInfo(com.google.crypto.tink.shaded.protobuf.RawMessageInfo, com.google.crypto.tink.shaded.protobuf.NewInstanceSchema, com.google.crypto.tink.shaded.protobuf.ListFieldSchema, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema, com.google.crypto.tink.shaded.protobuf.ExtensionSchema, com.google.crypto.tink.shaded.protobuf.MapFieldSchema):com.google.crypto.tink.shaded.protobuf.MessageSchema");
    }

    private static Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Field[] fields = messageClass.getDeclaredFields();
            for (Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(fields));
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:34:0x008b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static <T> com.google.crypto.tink.shaded.protobuf.MessageSchema<T> newSchemaForMessageInfo(com.google.crypto.tink.shaded.protobuf.StructuralMessageInfo r33, com.google.crypto.tink.shaded.protobuf.NewInstanceSchema r34, com.google.crypto.tink.shaded.protobuf.ListFieldSchema r35, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema<?, ?> r36, com.google.crypto.tink.shaded.protobuf.ExtensionSchema<?> r37, com.google.crypto.tink.shaded.protobuf.MapFieldSchema r38) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.MessageSchema.newSchemaForMessageInfo(com.google.crypto.tink.shaded.protobuf.StructuralMessageInfo, com.google.crypto.tink.shaded.protobuf.NewInstanceSchema, com.google.crypto.tink.shaded.protobuf.ListFieldSchema, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema, com.google.crypto.tink.shaded.protobuf.ExtensionSchema, com.google.crypto.tink.shaded.protobuf.MapFieldSchema):com.google.crypto.tink.shaded.protobuf.MessageSchema");
    }

    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, boolean proto3, Object[] objects) {
        int fieldOffset;
        int typeId;
        int typeId2;
        int presenceFieldOffset;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            typeId = fi.getType().id() + 51;
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            typeId2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            presenceFieldOffset = 0;
        } else {
            FieldType type = fi.getType();
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            int typeId3 = type.id();
            if (!proto3 && !type.isList() && !type.isMap()) {
                int presenceFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(fi.getPresenceField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset2;
                presenceFieldOffset = Integer.numberOfTrailingZeros(fi.getPresenceMask());
            } else if (fi.getCachedSizeField() == null) {
                typeId = typeId3;
                typeId2 = 0;
                presenceFieldOffset = 0;
            } else {
                int presenceFieldOffset3 = (int) UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset3;
                presenceFieldOffset = 0;
            }
        }
        buffer[bufferIndex] = fi.getFieldNumber();
        buffer[bufferIndex + 1] = (fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (fi.isRequired() ? REQUIRED_MASK : 0) | (typeId << 20) | fieldOffset;
        buffer[bufferIndex + 2] = (presenceFieldOffset << 20) | typeId2;
        Object messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            objects[(bufferIndex / 3) * 2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        if (messageFieldClass != null) {
            objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T message, T other) {
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            if (!equals(message, other, pos)) {
                return false;
            }
        }
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

    private boolean equals(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        switch (type(typeAndOffset)) {
            case 0:
                if (arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset))) {
                    break;
                }
                break;
            case 1:
                if (arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset))) {
                    break;
                }
                break;
            case 2:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 3:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 4:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 5:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 6:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 7:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset)) {
                    break;
                }
                break;
            case 8:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 9:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 10:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 11:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 12:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 13:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 14:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 15:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 16:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 17:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                if (isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = 0;
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int entryNumber = numberAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    break;
                case 1:
                    hashCode = (hashCode * 53) + Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    break;
                case 2:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 3:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 4:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 5:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 6:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 7:
                    hashCode = (hashCode * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    break;
                case 8:
                    int protoHash = hashCode * 53;
                    int hashCode2 = protoHash + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    hashCode = hashCode2;
                    break;
                case 9:
                    int protoHash2 = 37;
                    Object submessage = UnsafeUtil.getObject(message, offset);
                    if (submessage != null) {
                        protoHash2 = submessage.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash2;
                    break;
                case 10:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 11:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 12:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 13:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 14:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 15:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 16:
                    int protoHash3 = hashCode * 53;
                    int hashCode3 = protoHash3 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    hashCode = hashCode3;
                    break;
                case 17:
                    int protoHash4 = 37;
                    Object submessage2 = UnsafeUtil.getObject(message, offset);
                    if (submessage2 != null) {
                        protoHash4 = submessage2.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 50:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Float.floatToIntBits(oneofFloatAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashBoolean(oneofBooleanAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int pos2 = hashCode * 53;
        int hashCode4 = pos2 + this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions) {
            return (hashCode4 * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode4;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, T other) {
        if (other == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        int number = numberAt(pos);
        switch (type(typeAndOffset)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                    break;
                }
                break;
            case 60:
                mergeOneofMessage(message, other, pos);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                    break;
                }
                break;
            case 68:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    private void mergeMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        if (!isFieldPresent(other, pos)) {
            return;
        }
        Object mine = UnsafeUtil.getObject(message, offset);
        Object theirs = UnsafeUtil.getObject(other, offset);
        if (mine != null && theirs != null) {
            Object merged = Internal.mergeMessage(mine, theirs);
            UnsafeUtil.putObject(message, offset, merged);
            setFieldPresent(message, pos);
        } else if (theirs != null) {
            UnsafeUtil.putObject(message, offset, theirs);
            setFieldPresent(message, pos);
        }
    }

    private void mergeOneofMessage(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        int number = numberAt(pos);
        long offset = offset(typeAndOffset);
        if (!isOneofPresent(other, number, pos)) {
            return;
        }
        Object mine = UnsafeUtil.getObject(message, offset);
        Object theirs = UnsafeUtil.getObject(other, offset);
        if (mine != null && theirs != null) {
            Object merged = Internal.mergeMessage(mine, theirs);
            UnsafeUtil.putObject(message, offset, merged);
            setOneofPresent(message, number, pos);
        } else if (theirs != null) {
            UnsafeUtil.putObject(message, offset, theirs);
            setOneofPresent(message, number, pos);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T message) {
        return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
    }

    private int getSerializedSizeProto2(T message) {
        int currentPresenceFieldOffset;
        int size = 0;
        Unsafe unsafe = UNSAFE;
        int currentPresenceFieldOffset2 = -1;
        int currentPresenceField = 0;
        int i = 0;
        while (i < this.buffer.length) {
            int typeAndOffset = typeAndOffsetAt(i);
            int number = numberAt(i);
            int fieldType = type(typeAndOffset);
            int presenceMaskAndOffset = 0;
            int presenceMask = 0;
            if (fieldType > 17) {
                if (this.useCachedSizeField && fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                    presenceMaskAndOffset = this.buffer[i + 2] & OFFSET_MASK;
                }
            } else {
                presenceMaskAndOffset = this.buffer[i + 2];
                int presenceFieldOffset = presenceMaskAndOffset & OFFSET_MASK;
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                if (presenceFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceFieldOffset2 = presenceFieldOffset;
                    currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
                }
            }
            long offset = offset(typeAndOffset);
            switch (fieldType) {
                case 0:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset3 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset3 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    }
                case 1:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset4 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset4 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case 2:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset5 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset5 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 3:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset6 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset6 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 4:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset7 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset7 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 5:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset8 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset8 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    }
                case 6:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset9 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset9 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 7:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset10 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset10 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 8:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset11 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset11 == 0) {
                        break;
                    } else {
                        Object value = unsafe.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                            break;
                        }
                    }
                case 9:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset12 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset12 == 0) {
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 10:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset13 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset13 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 11:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset14 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset14 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 12:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset15 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset15 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 13:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset16 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset16 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 14:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset17 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset17 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    }
                case 15:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset18 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset18 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 16:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset19 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset19 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 17:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int currentPresenceFieldOffset20 = currentPresenceField & presenceMask;
                    if (currentPresenceFieldOffset20 == 0) {
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    }
                case 18:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 19:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 20:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 21:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeUInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 22:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 23:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 24:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 25:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeBoolList(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 26:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeStringList(number, (List) unsafe.getObject(message, offset));
                    break;
                case 27:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeMessageList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeByteStringList(number, (List) unsafe.getObject(message, offset));
                    break;
                case 29:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeUInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 30:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeEnumList(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 31:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 32:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 33:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeSInt32List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 34:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeSInt64List(number, (List) unsafe.getObject(message, offset), false);
                    break;
                case 35:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += SchemaUtil.computeSizeGroupList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    size += this.mapFieldSchema.getSerializedSize(number, unsafe.getObject(message, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 52:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 53:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 54:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 55:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 56:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 57:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 58:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 59:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                        }
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 60:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 61:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 62:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 63:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 64:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 65:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 66:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 67:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, number, i)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    } else {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        currentPresenceFieldOffset = currentPresenceFieldOffset2;
                        break;
                    }
                default:
                    currentPresenceFieldOffset = currentPresenceFieldOffset2;
                    break;
            }
            i += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
        }
        int size2 = size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size2 + this.extensionSchema.getExtensions(message).getSerializedSize();
        }
        return size2;
    }

    private int getSerializedSizeProto3(T message) {
        int cachedSizeOffset;
        Unsafe unsafe = UNSAFE;
        int size = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int fieldType = type(typeAndOffset);
            int number = numberAt(i);
            long offset = offset(typeAndOffset);
            if (fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                cachedSizeOffset = this.buffer[i + 2] & OFFSET_MASK;
            } else {
                cachedSizeOffset = 0;
            }
            switch (fieldType) {
                case 0:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt64Size(number, UnsafeUtil.getLong(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, UnsafeUtil.getLong(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt32Size(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(message, i)) {
                        Object value = UnsafeUtil.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(message, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeEnumSize(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, UnsafeUtil.getLong(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 19:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 20:
                    size += SchemaUtil.computeSizeInt64List(number, listAt(message, offset), false);
                    break;
                case 21:
                    size += SchemaUtil.computeSizeUInt64List(number, listAt(message, offset), false);
                    break;
                case 22:
                    size += SchemaUtil.computeSizeInt32List(number, listAt(message, offset), false);
                    break;
                case 23:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 24:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 25:
                    size += SchemaUtil.computeSizeBoolList(number, listAt(message, offset), false);
                    break;
                case 26:
                    size += SchemaUtil.computeSizeStringList(number, listAt(message, offset));
                    break;
                case 27:
                    size += SchemaUtil.computeSizeMessageList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    size += SchemaUtil.computeSizeByteStringList(number, listAt(message, offset));
                    break;
                case 29:
                    size += SchemaUtil.computeSizeUInt32List(number, listAt(message, offset), false);
                    break;
                case 30:
                    size += SchemaUtil.computeSizeEnumList(number, listAt(message, offset), false);
                    break;
                case 31:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 32:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 33:
                    size += SchemaUtil.computeSizeSInt32List(number, listAt(message, offset), false);
                    break;
                case 34:
                    size += SchemaUtil.computeSizeSInt64List(number, listAt(message, offset), false);
                    break;
                case 35:
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    }
                case 36:
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    }
                case 37:
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    }
                case 38:
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    }
                case 39:
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    }
                case 40:
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    }
                case 41:
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    }
                case 42:
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    }
                case 43:
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    }
                case 44:
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    }
                case 45:
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    }
                case 46:
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    }
                case 47:
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    }
                case 48:
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    }
                case 49:
                    size += SchemaUtil.computeSizeGroupList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    size += this.mapFieldSchema.getSerializedSize(number, UnsafeUtil.getObject(message, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, number, i)) {
                        Object value2 = UnsafeUtil.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                            break;
                        }
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(message, number, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
            }
        }
        return size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSize(unknowns);
    }

    private static List<?> listAt(Object message, long offset) {
        return (List) UnsafeUtil.getObject(message, offset);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(message, writer);
        } else {
            writeFieldsInAscendingOrderProto2(message, writer);
        }
    }

    private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
        Map.Entry nextExtension;
        int currentPresenceFieldOffset;
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension2 = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                nextExtension2 = extensionIterator.next();
            }
        }
        int currentPresenceFieldOffset2 = -1;
        int currentPresenceField = 0;
        int bufferLength = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int pos = 0;
        while (pos < bufferLength) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            int fieldType = type(typeAndOffset);
            int presenceMask = 0;
            Map.Entry nextExtension3 = nextExtension2;
            if (!this.proto3 && fieldType <= 17) {
                int presenceMaskAndOffset = this.buffer[pos + 2];
                int presenceFieldOffset = OFFSET_MASK & presenceMaskAndOffset;
                if (presenceFieldOffset != currentPresenceFieldOffset2) {
                    currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
                    currentPresenceFieldOffset2 = presenceFieldOffset;
                }
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                nextExtension = nextExtension3;
            } else {
                nextExtension = nextExtension3;
            }
            while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
                this.extensionSchema.serializeExtension(writer, nextExtension);
                nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
            }
            Map.Entry nextExtension4 = nextExtension;
            int currentPresenceFieldOffset3 = currentPresenceFieldOffset2;
            long offset = offset(typeAndOffset);
            int bufferLength2 = bufferLength;
            switch (fieldType) {
                case 0:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeDouble(number, doubleAt(message, offset));
                        break;
                    }
                case 1:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeFloat(number, floatAt(message, offset));
                        break;
                    }
                case 2:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeInt64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 3:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeUInt64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 4:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeInt32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 5:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeFixed64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 6:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeFixed32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 7:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeBool(number, booleanAt(message, offset));
                        break;
                    }
                case 8:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writeString(number, unsafe.getObject(message, offset), writer);
                        break;
                    }
                case 9:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        Object value = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                        break;
                    }
                case 10:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 11:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeUInt32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 12:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeEnum(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 13:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSFixed32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 14:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSFixed64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 15:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSInt32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 16:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSInt64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 17:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                        break;
                    }
                case 18:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset4 = numberAt(pos);
                    SchemaUtil.writeDoubleList(currentPresenceFieldOffset4, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 19:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset5 = numberAt(pos);
                    SchemaUtil.writeFloatList(currentPresenceFieldOffset5, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 20:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset6 = numberAt(pos);
                    SchemaUtil.writeInt64List(currentPresenceFieldOffset6, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 21:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset7 = numberAt(pos);
                    SchemaUtil.writeUInt64List(currentPresenceFieldOffset7, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 22:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset8 = numberAt(pos);
                    SchemaUtil.writeInt32List(currentPresenceFieldOffset8, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 23:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset9 = numberAt(pos);
                    SchemaUtil.writeFixed64List(currentPresenceFieldOffset9, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 24:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset10 = numberAt(pos);
                    SchemaUtil.writeFixed32List(currentPresenceFieldOffset10, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 25:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset11 = numberAt(pos);
                    SchemaUtil.writeBoolList(currentPresenceFieldOffset11, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 26:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeStringList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case 27:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeMessageList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeBytesList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case 29:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset12 = numberAt(pos);
                    SchemaUtil.writeUInt32List(currentPresenceFieldOffset12, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 30:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset13 = numberAt(pos);
                    SchemaUtil.writeEnumList(currentPresenceFieldOffset13, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 31:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset14 = numberAt(pos);
                    SchemaUtil.writeSFixed32List(currentPresenceFieldOffset14, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 32:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset15 = numberAt(pos);
                    SchemaUtil.writeSFixed64List(currentPresenceFieldOffset15, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 33:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset16 = numberAt(pos);
                    SchemaUtil.writeSInt32List(currentPresenceFieldOffset16, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 34:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    int currentPresenceFieldOffset17 = numberAt(pos);
                    SchemaUtil.writeSInt64List(currentPresenceFieldOffset17, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 35:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 36:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 37:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 38:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 39:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 40:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 41:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 42:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 43:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 44:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 45:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 46:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 47:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 48:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, true);
                    break;
                case 49:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    SchemaUtil.writeGroupList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    writeMapHelper(writer, number, unsafe.getObject(message, offset), pos);
                    break;
                case 51:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeDouble(number, oneofDoubleAt(message, offset));
                        break;
                    }
                case 52:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeFloat(number, oneofFloatAt(message, offset));
                        break;
                    }
                case 53:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeInt64(number, oneofLongAt(message, offset));
                        break;
                    }
                case 54:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeUInt64(number, oneofLongAt(message, offset));
                        break;
                    }
                case 55:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeInt32(number, oneofIntAt(message, offset));
                        break;
                    }
                case 56:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeFixed64(number, oneofLongAt(message, offset));
                        break;
                    }
                case 57:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeFixed32(number, oneofIntAt(message, offset));
                        break;
                    }
                case 58:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeBool(number, oneofBooleanAt(message, offset));
                        break;
                    }
                case 59:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writeString(number, unsafe.getObject(message, offset), writer);
                        break;
                    }
                case 60:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        break;
                    }
                case 61:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 62:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeUInt32(number, oneofIntAt(message, offset));
                        break;
                    }
                case 63:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeEnum(number, oneofIntAt(message, offset));
                        break;
                    }
                case 64:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSFixed32(number, oneofIntAt(message, offset));
                        break;
                    }
                case 65:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSFixed64(number, oneofLongAt(message, offset));
                        break;
                    }
                case 66:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSInt32(number, oneofIntAt(message, offset));
                        break;
                    }
                case 67:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    if (!isOneofPresent(message, number, pos)) {
                        break;
                    } else {
                        writer.writeSInt64(number, oneofLongAt(message, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, number, pos)) {
                        currentPresenceFieldOffset = currentPresenceFieldOffset3;
                        break;
                    } else {
                        currentPresenceFieldOffset = currentPresenceFieldOffset3;
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                        break;
                    }
                default:
                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                    break;
            }
            pos += 3;
            currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            nextExtension2 = nextExtension4;
            bufferLength = bufferLength2;
        }
        while (nextExtension2 != null) {
            this.extensionSchema.serializeExtension(writer, nextExtension2);
            nextExtension2 = extensionIterator.hasNext() ? extensionIterator.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                nextExtension = extensionIterator.next();
            }
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
                this.extensionSchema.serializeExtension(writer, nextExtension);
                nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
            }
            switch (type(typeAndOffset)) {
                case 0:
                    if (isFieldPresent(message, pos)) {
                        writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(message, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(message, pos)) {
                        Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(message, pos)) {
                        writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(message, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                    break;
                case 51:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(message, number, pos)) {
                        Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
            }
        }
        while (nextExtension != null) {
            this.extensionSchema.serializeExtension(writer, nextExtension);
            nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.descendingIterator();
                nextExtension = extensionIterator.next();
            }
        }
        int pos = this.buffer.length;
        while (true) {
            pos -= 3;
            if (pos >= 0) {
                int typeAndOffset = typeAndOffsetAt(pos);
                int number = numberAt(pos);
                while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) > number) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                }
                switch (type(typeAndOffset)) {
                    case 0:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 1:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 2:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 3:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 4:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 5:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 6:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 7:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 8:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case 9:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value, getMessageFieldSchema(pos));
                            break;
                        }
                    case 10:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                            break;
                        }
                    case 11:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 12:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 13:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 14:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 15:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 16:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 17:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                    case 18:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 19:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 29:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 31:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 48:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 49:
                        SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 50:
                        writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                        break;
                    case 51:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 52:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 53:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 54:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 55:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 56:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 57:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 58:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 59:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case 60:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                            break;
                        }
                    case 61:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                            break;
                        }
                    case 62:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 63:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 64:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 65:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 66:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 67:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 68:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                }
            } else {
                while (nextExtension != null) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                }
                return;
            }
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws Throwable {
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /* JADX WARN: Code restructure failed: missing block: B:247:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0091, code lost:
    
        r1 = r18.checkInitializedCount;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0095, code lost:
    
        if (r1 >= r18.repeatedFieldOffsetStart) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0097, code lost:
    
        r13 = filterMapUnknownEnumValues(r21, r18.intArray[r1], r13, r19);
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a2, code lost:
    
        if (r13 == null) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00a4, code lost:
    
        r19.setBuilderToMessage(r21, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a7, code lost:
    
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:175:0x06ac A[Catch: all -> 0x06f1, TRY_LEAVE, TryCatch #1 {all -> 0x06f1, blocks: (B:27:0x0066, B:28:0x006e, B:30:0x0077, B:34:0x0082, B:35:0x0087, B:46:0x00ab, B:48:0x00b0, B:52:0x00ba, B:161:0x0685, B:173:0x06a6, B:175:0x06ac, B:185:0x06cb, B:186:0x06d0, B:56:0x00d6, B:57:0x00ec, B:58:0x0102, B:59:0x0118, B:60:0x012e, B:62:0x0138, B:65:0x013f, B:66:0x0148, B:67:0x0159, B:68:0x016f, B:69:0x0180, B:71:0x0187, B:73:0x01b6, B:72:0x01a3, B:74:0x01bc, B:75:0x01c5, B:76:0x01db, B:77:0x01f1, B:78:0x0207, B:79:0x021d, B:80:0x0233, B:81:0x0249, B:82:0x025f, B:83:0x0275, B:93:0x02ac, B:94:0x02bf, B:95:0x02d2, B:96:0x02e5, B:97:0x02f8, B:98:0x0315, B:99:0x0328, B:100:0x033b, B:101:0x034e, B:102:0x0361, B:103:0x0374, B:104:0x0387, B:105:0x039a, B:106:0x03ad, B:107:0x03c0, B:108:0x03d3, B:109:0x03e6, B:110:0x03f9, B:111:0x040c, B:112:0x0429, B:113:0x043c, B:114:0x044f, B:119:0x0470, B:120:0x0475, B:121:0x0485, B:122:0x0495, B:123:0x04a5, B:124:0x04b5, B:125:0x04c5, B:126:0x04d5, B:127:0x04e5, B:128:0x04f5, B:130:0x04fd, B:131:0x051a, B:132:0x052f, B:133:0x0540, B:134:0x0551, B:135:0x0562, B:136:0x0573, B:138:0x057e, B:141:0x0585, B:142:0x058d, B:143:0x0599, B:144:0x05aa, B:145:0x05bb, B:147:0x05c3, B:148:0x05e0, B:149:0x05f5, B:150:0x05fe, B:151:0x060f, B:152:0x0620, B:153:0x0631, B:154:0x0641, B:155:0x0651, B:156:0x0661, B:157:0x0671, B:55:0x00c1, B:88:0x028e, B:90:0x02a0), top: B:209:0x0066 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0700 A[LOOP:5: B:201:0x06fc->B:203:0x0700, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:251:? A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r16v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r16v100 */
    /* JADX WARN: Type inference failed for: r16v101 */
    /* JADX WARN: Type inference failed for: r16v102 */
    /* JADX WARN: Type inference failed for: r16v103 */
    /* JADX WARN: Type inference failed for: r16v104 */
    /* JADX WARN: Type inference failed for: r16v105 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v31 */
    /* JADX WARN: Type inference failed for: r16v32 */
    /* JADX WARN: Type inference failed for: r16v33 */
    /* JADX WARN: Type inference failed for: r16v34 */
    /* JADX WARN: Type inference failed for: r16v35 */
    /* JADX WARN: Type inference failed for: r16v36 */
    /* JADX WARN: Type inference failed for: r16v37 */
    /* JADX WARN: Type inference failed for: r16v38 */
    /* JADX WARN: Type inference failed for: r16v39 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v40 */
    /* JADX WARN: Type inference failed for: r16v41 */
    /* JADX WARN: Type inference failed for: r16v42 */
    /* JADX WARN: Type inference failed for: r16v43 */
    /* JADX WARN: Type inference failed for: r16v44 */
    /* JADX WARN: Type inference failed for: r16v45 */
    /* JADX WARN: Type inference failed for: r16v46 */
    /* JADX WARN: Type inference failed for: r16v47 */
    /* JADX WARN: Type inference failed for: r16v48 */
    /* JADX WARN: Type inference failed for: r16v49 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v50 */
    /* JADX WARN: Type inference failed for: r16v51 */
    /* JADX WARN: Type inference failed for: r16v52 */
    /* JADX WARN: Type inference failed for: r16v53 */
    /* JADX WARN: Type inference failed for: r16v54 */
    /* JADX WARN: Type inference failed for: r16v55 */
    /* JADX WARN: Type inference failed for: r16v56 */
    /* JADX WARN: Type inference failed for: r16v57 */
    /* JADX WARN: Type inference failed for: r16v58 */
    /* JADX WARN: Type inference failed for: r16v59 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v60 */
    /* JADX WARN: Type inference failed for: r16v61 */
    /* JADX WARN: Type inference failed for: r16v62 */
    /* JADX WARN: Type inference failed for: r16v63 */
    /* JADX WARN: Type inference failed for: r16v64 */
    /* JADX WARN: Type inference failed for: r16v65 */
    /* JADX WARN: Type inference failed for: r16v66 */
    /* JADX WARN: Type inference failed for: r16v67 */
    /* JADX WARN: Type inference failed for: r16v68 */
    /* JADX WARN: Type inference failed for: r16v69 */
    /* JADX WARN: Type inference failed for: r16v7, types: [int] */
    /* JADX WARN: Type inference failed for: r16v70 */
    /* JADX WARN: Type inference failed for: r16v71 */
    /* JADX WARN: Type inference failed for: r16v72 */
    /* JADX WARN: Type inference failed for: r16v73 */
    /* JADX WARN: Type inference failed for: r16v74 */
    /* JADX WARN: Type inference failed for: r16v75 */
    /* JADX WARN: Type inference failed for: r16v76 */
    /* JADX WARN: Type inference failed for: r16v77 */
    /* JADX WARN: Type inference failed for: r16v78 */
    /* JADX WARN: Type inference failed for: r16v79 */
    /* JADX WARN: Type inference failed for: r16v80 */
    /* JADX WARN: Type inference failed for: r16v81 */
    /* JADX WARN: Type inference failed for: r16v82 */
    /* JADX WARN: Type inference failed for: r16v83 */
    /* JADX WARN: Type inference failed for: r16v84 */
    /* JADX WARN: Type inference failed for: r16v85 */
    /* JADX WARN: Type inference failed for: r16v86 */
    /* JADX WARN: Type inference failed for: r16v87 */
    /* JADX WARN: Type inference failed for: r16v88 */
    /* JADX WARN: Type inference failed for: r16v89 */
    /* JADX WARN: Type inference failed for: r16v90 */
    /* JADX WARN: Type inference failed for: r16v91 */
    /* JADX WARN: Type inference failed for: r16v92 */
    /* JADX WARN: Type inference failed for: r16v93 */
    /* JADX WARN: Type inference failed for: r16v94 */
    /* JADX WARN: Type inference failed for: r16v95 */
    /* JADX WARN: Type inference failed for: r16v96 */
    /* JADX WARN: Type inference failed for: r16v97 */
    /* JADX WARN: Type inference failed for: r16v98 */
    /* JADX WARN: Type inference failed for: r16v99 */
    /* JADX WARN: Type inference failed for: r19v0, types: [com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema, com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema<UT, UB>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <UT, UB, ET extends com.google.crypto.tink.shaded.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema<UT, UB> r19, com.google.crypto.tink.shaded.protobuf.ExtensionSchema<ET> r20, T r21, com.google.crypto.tink.shaded.protobuf.Reader r22, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1952
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.MessageSchema.mergeFromHelper(com.google.crypto.tink.shaded.protobuf.UnknownFieldSchema, com.google.crypto.tink.shaded.protobuf.ExtensionSchema, java.lang.Object, com.google.crypto.tink.shaded.protobuf.Reader, com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
        if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite unknownFields2 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite) message).unknownFields = unknownFields2;
            return unknownFields2;
        }
        return unknownFields;
    }

    /* renamed from: com.google.crypto.tink.shaded.protobuf.MessageSchema$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MessageSchema$1.smali */
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
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int position2 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return position2;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int position3 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return position3;
            case 12:
            case 13:
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return position4;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return position5;
            case 16:
                int position6 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return position6;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        int position2;
        int position3;
        int length;
        int position4 = ArrayDecoders.decodeVarint32(data, position, registers);
        int wireType = registers.int1;
        if (wireType < 0 || wireType > limit - position4) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int end = position4 + wireType;
        K key = metadata.defaultKey;
        Object obj = key;
        Object obj2 = metadata.defaultValue;
        while (position4 < end) {
            int position5 = position4 + 1;
            int tag2 = data[position4];
            if (tag2 >= 0) {
                tag = tag2;
                position2 = position5;
            } else {
                int position6 = ArrayDecoders.decodeVarint32(tag2, data, position5, registers);
                tag = registers.int1;
                position2 = position6;
            }
            int fieldNumber = tag >>> 3;
            int wireType2 = tag & 7;
            if (fieldNumber != 1) {
                if (fieldNumber != 2) {
                    position3 = position2;
                    length = wireType;
                } else if (wireType2 == metadata.valueType.getWireType()) {
                    int length2 = wireType;
                    position4 = decodeMapEntryValue(data, position2, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                    wireType = length2;
                } else {
                    position3 = position2;
                    length = wireType;
                }
                position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                wireType = length;
            } else {
                position3 = position2;
                length = wireType;
                if (wireType2 != metadata.keyType.getWireType()) {
                    position4 = ArrayDecoders.skipField(tag, data, position3, limit, registers);
                    wireType = length;
                } else {
                    position4 = decodeMapEntryValue(data, position3, limit, metadata.keyType, null, registers);
                    obj = registers.object1;
                    wireType = length;
                }
            }
        }
        if (position4 != end) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return end;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int parseRepeatedField(T t, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Internal.ProtobufList<?> list;
        int position2;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList<?> list2 = (Internal.ProtobufList) unsafe.getObject(t, fieldOffset);
        if (list2.isModifiable()) {
            list = list2;
        } else {
            int size = list2.size();
            Internal.ProtobufList<?> list3 = list2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(t, fieldOffset, list3);
            list = list3;
        }
        switch (fieldType) {
            case 18:
            case 35:
                Internal.ProtobufList<?> list4 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, list4, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, list4, registers);
                }
                break;
            case 19:
            case 36:
                Internal.ProtobufList<?> list5 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, list5, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, list5, registers);
                }
                break;
            case 20:
            case 21:
            case 37:
            case 38:
                Internal.ProtobufList<?> list6 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, list6, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, list6, registers);
                }
                break;
            case 22:
            case 29:
            case 39:
            case 43:
                Internal.ProtobufList<?> list7 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, list7, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, list7, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                Internal.ProtobufList<?> list8 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, list8, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, list8, registers);
                }
                break;
            case 24:
            case 31:
            case 41:
            case 45:
                Internal.ProtobufList<?> list9 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, list9, registers);
                }
                if (wireType == 5) {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, list9, registers);
                }
                break;
            case 25:
            case 42:
                Internal.ProtobufList<?> list10 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, list10, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, list10, registers);
                }
                break;
            case 26:
                Internal.ProtobufList<?> list11 = list;
                if (wireType == 2) {
                    if ((typeAndOffset & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, list11, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list11, registers);
                }
                break;
            case 27:
                Internal.ProtobufList<?> list12 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list12, registers);
                }
                break;
            case 28:
                Internal.ProtobufList<?> list13 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, list13, registers);
                }
                break;
            case 30:
            case 44:
                Internal.ProtobufList<?> list14 = list;
                if (wireType == 2) {
                    position2 = ArrayDecoders.decodePackedVarint32List(data, position, list14, registers);
                } else if (wireType != 0) {
                    break;
                } else {
                    position2 = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list14, registers);
                }
                UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) t).unknownFields;
                if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
                    unknownFields = null;
                }
                UnknownFieldSetLite unknownFields2 = (UnknownFieldSetLite) SchemaUtil.filterUnknownEnumList(number, (List<Integer>) list14, getEnumFieldVerifier(bufferPosition), unknownFields, (UnknownFieldSchema<UT, UnknownFieldSetLite>) this.unknownFieldSchema);
                if (unknownFields2 == null) {
                    return position2;
                }
                ((GeneratedMessageLite) t).unknownFields = unknownFields2;
                return position2;
            case 33:
            case 47:
                Internal.ProtobufList<?> list15 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, list15, registers);
                }
                if (wireType != 0) {
                    break;
                } else {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, list15, registers);
                }
            case 34:
            case 48:
                Internal.ProtobufList<?> list16 = list;
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, list16, registers);
                }
                if (wireType != 0) {
                    break;
                } else {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, list16, registers);
                }
            case 49:
                if (wireType != 3) {
                    break;
                } else {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
        }
        return position;
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Object mapField;
        Unsafe unsafe = UNSAFE;
        Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object mapField2 = unsafe.getObject(message, fieldOffset);
        if (!this.mapFieldSchema.isImmutable(mapField2)) {
            mapField = mapField2;
        } else {
            Object mapField3 = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField3, mapField2);
            unsafe.putObject(message, fieldOffset, mapField3);
            mapField = mapField3;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapDefaultEntry), this.mapFieldSchema.forMutableMapData(mapField), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        int position2;
        Unsafe unsafe;
        Unsafe unsafe2 = UNSAFE;
        long oneofCaseOffset = this.buffer[bufferPosition + 2] & OFFSET_MASK;
        Object object = null;
        switch (fieldType) {
            case 51:
                position2 = position;
                if (wireType == 1) {
                    unsafe2.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                    int position3 = position2 + 8;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position3;
                }
                break;
            case 52:
                position2 = position;
                if (wireType == 5) {
                    unsafe2.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                    int position4 = position2 + 4;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position4;
                }
                break;
            case 53:
            case 54:
                position2 = position;
                if (wireType == 0) {
                    int position5 = ArrayDecoders.decodeVarint64(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position5;
                }
                break;
            case 55:
            case 62:
                position2 = position;
                if (wireType == 0) {
                    int position6 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position6;
                }
                break;
            case 56:
            case 65:
                position2 = position;
                if (wireType == 1) {
                    unsafe2.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                    int position7 = position2 + 8;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position7;
                }
                break;
            case 57:
            case 64:
                position2 = position;
                if (wireType == 5) {
                    unsafe2.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                    int position8 = position2 + 4;
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position8;
                }
                break;
            case 58:
                position2 = position;
                if (wireType == 0) {
                    int position9 = ArrayDecoders.decodeVarint64(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position9;
                }
                break;
            case 59:
                position2 = position;
                if (wireType == 2) {
                    int position10 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    int length = registers.int1;
                    if (length == 0) {
                        unsafe2.putObject(message, fieldOffset, "");
                    } else {
                        if ((typeAndOffset & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(data, position10, position10 + length)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        String value = new String(data, position10, length, Internal.UTF_8);
                        unsafe2.putObject(message, fieldOffset, value);
                        position10 += length;
                    }
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position10;
                }
                break;
            case 60:
                position2 = position;
                if (wireType != 2) {
                    break;
                } else {
                    int position11 = ArrayDecoders.decodeMessageField(getMessageFieldSchema(bufferPosition), data, position2, limit, registers);
                    if (unsafe2.getInt(message, oneofCaseOffset) == number) {
                        object = unsafe2.getObject(message, fieldOffset);
                    }
                    Object oldValue = object;
                    if (oldValue == null) {
                        unsafe2.putObject(message, fieldOffset, registers.object1);
                    } else {
                        unsafe2.putObject(message, fieldOffset, Internal.mergeMessage(oldValue, registers.object1));
                    }
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position11;
                }
            case 61:
                position2 = position;
                if (wireType != 2) {
                    break;
                } else {
                    int position12 = ArrayDecoders.decodeBytes(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, registers.object1);
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position12;
                }
            case 63:
                position2 = position;
                if (wireType != 0) {
                    break;
                } else {
                    int position13 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    int enumValue = registers.int1;
                    Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(bufferPosition);
                    if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                        unsafe = unsafe2;
                        unsafe.putObject(message, fieldOffset, Integer.valueOf(enumValue));
                        unsafe.putInt(message, oneofCaseOffset, number);
                        return position13;
                    }
                    unsafe = unsafe2;
                    getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
                    return position13;
                }
            case 66:
                position2 = position;
                if (wireType != 0) {
                    break;
                } else {
                    int position14 = ArrayDecoders.decodeVarint32(data, position2, registers);
                    unsafe2.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position14;
                }
            case 67:
                if (wireType != 0) {
                    position2 = position;
                    break;
                } else {
                    int position15 = ArrayDecoders.decodeVarint64(data, position, registers);
                    unsafe2.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position15;
                }
            case 68:
                if (wireType != 3) {
                    position2 = position;
                    break;
                } else {
                    int endTag = (tag & (-8)) | 4;
                    int position16 = ArrayDecoders.decodeGroupField(getMessageFieldSchema(bufferPosition), data, position, limit, endTag, registers);
                    if (unsafe2.getInt(message, oneofCaseOffset) == number) {
                        object = unsafe2.getObject(message, fieldOffset);
                    }
                    Object oldValue2 = object;
                    if (oldValue2 == null) {
                        unsafe2.putObject(message, fieldOffset, registers.object1);
                    } else {
                        unsafe2.putObject(message, fieldOffset, Internal.mergeMessage(oldValue2, registers.object1));
                    }
                    unsafe2.putInt(message, oneofCaseOffset, number);
                    return position16;
                }
            default:
                position2 = position;
                break;
        }
        return position2;
    }

    private Schema getMessageFieldSchema(int pos) {
        int index = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[index];
        if (schema != null) {
            return schema;
        }
        Schema schema2 = Protobuf.getInstance().schemaFor((Class) this.objects[index + 1]);
        this.objects[index] = schema2;
        return schema2;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:26:0x0092. Please report as an issue. */
    int parseProto2Message(T message, byte[] data, int position, int limit, int endGroup, ArrayDecoders.Registers registers) throws IOException {
        Unsafe unsafe;
        int i;
        MessageSchema<T> messageSchema;
        T t;
        int tag;
        int position2;
        int pos;
        int tag2;
        int pos2;
        int position3;
        int currentPresenceField;
        ArrayDecoders.Registers registers2;
        int position4;
        int currentPresenceFieldOffset;
        int currentPresenceFieldOffset2;
        int typeAndOffset;
        int pos3;
        Unsafe unsafe2;
        int wireType;
        MessageSchema<T> messageSchema2 = this;
        T t2 = message;
        byte[] bArr = data;
        int wireType2 = limit;
        int i2 = endGroup;
        ArrayDecoders.Registers registers3 = registers;
        Unsafe unsafe3 = UNSAFE;
        int tag3 = 0;
        int oldNumber = -1;
        int pos4 = 0;
        int currentPresenceFieldOffset3 = -1;
        int currentPresenceField2 = 0;
        int position5 = position;
        while (true) {
            if (position5 < wireType2) {
                int position6 = position5 + 1;
                int tag4 = bArr[position5];
                if (tag4 >= 0) {
                    tag = tag4;
                    position2 = position6;
                } else {
                    int position7 = ArrayDecoders.decodeVarint32(tag4, bArr, position6, registers3);
                    int tag5 = registers3.int1;
                    tag = tag5;
                    position2 = position7;
                }
                int position8 = tag >>> 3;
                int wireType3 = tag & 7;
                if (position8 > oldNumber) {
                    pos = messageSchema2.positionForFieldNumber(position8, pos4 / 3);
                } else {
                    int pos5 = messageSchema2.positionForFieldNumber(position8);
                    pos = pos5;
                }
                if (pos == -1) {
                    tag2 = tag;
                    pos2 = 0;
                    position3 = position2;
                    currentPresenceField = currentPresenceField2;
                    unsafe = unsafe3;
                } else {
                    int typeAndOffset2 = messageSchema2.buffer[pos + 1];
                    int fieldType = type(typeAndOffset2);
                    long fieldOffset = offset(typeAndOffset2);
                    int tag6 = tag;
                    if (fieldType <= 17) {
                        int presenceMaskAndOffset = messageSchema2.buffer[pos + 2];
                        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                        int presenceFieldOffset = presenceMaskAndOffset & OFFSET_MASK;
                        if (presenceFieldOffset == currentPresenceFieldOffset3) {
                            position4 = position2;
                        } else {
                            if (currentPresenceFieldOffset3 == -1) {
                                position4 = position2;
                            } else {
                                position4 = position2;
                                unsafe3.putInt(t2, currentPresenceFieldOffset3, currentPresenceField2);
                            }
                            currentPresenceFieldOffset3 = presenceFieldOffset;
                            currentPresenceField2 = unsafe3.getInt(t2, presenceFieldOffset);
                        }
                        switch (fieldType) {
                            case 0:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                ArrayDecoders.Registers registers4 = registers3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                wireType = wireType3;
                                typeAndOffset = tag6;
                                if (wireType == 1) {
                                    UnsafeUtil.putDouble(t2, fieldOffset, ArrayDecoders.decodeDouble(bArr, currentPresenceFieldOffset2));
                                    position5 = currentPresenceFieldOffset2 + 8;
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    registers3 = registers4;
                                    oldNumber = position8;
                                    tag3 = typeAndOffset;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    pos4 = pos3;
                                    wireType2 = limit;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 1:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                ArrayDecoders.Registers registers5 = registers3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                wireType = wireType3;
                                typeAndOffset = tag6;
                                if (wireType == 5) {
                                    UnsafeUtil.putFloat(t2, fieldOffset, ArrayDecoders.decodeFloat(bArr, currentPresenceFieldOffset2));
                                    position5 = currentPresenceFieldOffset2 + 4;
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    registers3 = registers5;
                                    oldNumber = position8;
                                    tag3 = typeAndOffset;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    pos4 = pos3;
                                    wireType2 = limit;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 2:
                            case 3:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                ArrayDecoders.Registers registers6 = registers3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                wireType = wireType3;
                                typeAndOffset = tag6;
                                if (wireType == 0) {
                                    int position9 = ArrayDecoders.decodeVarint64(bArr, currentPresenceFieldOffset2, registers6);
                                    unsafe2.putLong(message, fieldOffset, registers6.long1);
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    position5 = position9;
                                    registers3 = registers6;
                                    oldNumber = position8;
                                    tag3 = typeAndOffset;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    pos4 = pos3;
                                    wireType2 = limit;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 4:
                            case 11:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                ArrayDecoders.Registers registers7 = registers3;
                                unsafe2 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                wireType = wireType3;
                                typeAndOffset = tag6;
                                if (wireType == 0) {
                                    position5 = ArrayDecoders.decodeVarint32(bArr, currentPresenceFieldOffset2, registers7);
                                    unsafe2.putInt(t2, fieldOffset, registers7.int1);
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe2;
                                    registers3 = registers7;
                                    oldNumber = position8;
                                    tag3 = typeAndOffset;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    pos4 = pos3;
                                    wireType2 = limit;
                                } else {
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 5:
                            case 14:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe4 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                ArrayDecoders.Registers registers8 = registers3;
                                if (wireType3 == 1) {
                                    unsafe4.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(bArr, currentPresenceFieldOffset2));
                                    position5 = currentPresenceFieldOffset2 + 8;
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe4;
                                    registers3 = registers8;
                                    oldNumber = position8;
                                    tag3 = tag6;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    pos4 = pos;
                                    wireType2 = limit;
                                } else {
                                    typeAndOffset = tag6;
                                    wireType = wireType3;
                                    pos3 = pos;
                                    unsafe2 = unsafe4;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 6:
                            case 13:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe5 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                ArrayDecoders.Registers registers9 = registers3;
                                if (wireType3 == 5) {
                                    unsafe5.putInt(t2, fieldOffset, ArrayDecoders.decodeFixed32(bArr, currentPresenceFieldOffset2));
                                    position5 = currentPresenceFieldOffset2 + 4;
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe5;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    registers3 = registers9;
                                    tag3 = tag6;
                                } else {
                                    typeAndOffset = tag6;
                                    wireType = wireType3;
                                    pos3 = pos;
                                    unsafe2 = unsafe5;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 7:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe6 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                ArrayDecoders.Registers registers10 = registers3;
                                if (wireType3 == 0) {
                                    int position10 = ArrayDecoders.decodeVarint64(bArr, currentPresenceFieldOffset2, registers10);
                                    UnsafeUtil.putBoolean(t2, fieldOffset, registers10.long1 != 0);
                                    currentPresenceField2 |= presenceMask;
                                    position5 = position10;
                                    i2 = endGroup;
                                    unsafe3 = unsafe6;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    registers3 = registers10;
                                    tag3 = tag6;
                                } else {
                                    typeAndOffset = tag6;
                                    wireType = wireType3;
                                    pos3 = pos;
                                    unsafe2 = unsafe6;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 8:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe7 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                ArrayDecoders.Registers registers11 = registers3;
                                if (wireType3 == 2) {
                                    if ((ENFORCE_UTF8_MASK & typeAndOffset2) == 0) {
                                        position5 = ArrayDecoders.decodeString(bArr, currentPresenceFieldOffset2, registers11);
                                    } else {
                                        position5 = ArrayDecoders.decodeStringRequireUtf8(bArr, currentPresenceFieldOffset2, registers11);
                                    }
                                    unsafe7.putObject(t2, fieldOffset, registers11.object1);
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe7;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    registers3 = registers11;
                                    tag3 = tag6;
                                } else {
                                    pos3 = pos;
                                    typeAndOffset = tag6;
                                    wireType = wireType3;
                                    unsafe2 = unsafe7;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                }
                            case 9:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe8 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                ArrayDecoders.Registers registers12 = registers3;
                                if (wireType3 != 2) {
                                    typeAndOffset = tag6;
                                    wireType = wireType3;
                                    pos3 = pos;
                                    unsafe2 = unsafe8;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                } else {
                                    wireType2 = limit;
                                    position5 = ArrayDecoders.decodeMessageField(messageSchema2.getMessageFieldSchema(pos), bArr, currentPresenceFieldOffset2, wireType2, registers12);
                                    if ((currentPresenceField2 & presenceMask) == 0) {
                                        unsafe8.putObject(t2, fieldOffset, registers12.object1);
                                    } else {
                                        unsafe8.putObject(t2, fieldOffset, Internal.mergeMessage(unsafe8.getObject(t2, fieldOffset), registers12.object1));
                                    }
                                    currentPresenceField2 |= presenceMask;
                                    i2 = endGroup;
                                    unsafe3 = unsafe8;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    registers3 = registers12;
                                    tag3 = tag6;
                                }
                            case 10:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe9 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                if (wireType3 != 2) {
                                    typeAndOffset = tag6;
                                    wireType = wireType3;
                                    pos3 = pos;
                                    unsafe2 = unsafe9;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                } else {
                                    position5 = ArrayDecoders.decodeBytes(bArr, currentPresenceFieldOffset2, registers);
                                    unsafe9.putObject(t2, fieldOffset, registers.object1);
                                    currentPresenceField2 |= presenceMask;
                                    wireType2 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe9;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    registers3 = registers;
                                    tag3 = tag6;
                                }
                            case 12:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe10 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                typeAndOffset = tag6;
                                if (wireType3 != 0) {
                                    wireType = wireType3;
                                    pos3 = pos;
                                    unsafe2 = unsafe10;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                } else {
                                    position5 = ArrayDecoders.decodeVarint32(bArr, currentPresenceFieldOffset2, registers3);
                                    int enumValue = registers3.int1;
                                    Internal.EnumVerifier enumVerifier = messageSchema2.getEnumFieldVerifier(pos);
                                    if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                                        unsafe10.putInt(t2, fieldOffset, enumValue);
                                        currentPresenceField2 |= presenceMask;
                                        wireType2 = limit;
                                        i2 = endGroup;
                                        unsafe3 = unsafe10;
                                        pos4 = pos;
                                        tag3 = typeAndOffset;
                                        oldNumber = position8;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                        registers3 = registers;
                                    } else {
                                        getMutableUnknownFields(message).storeField(typeAndOffset, Long.valueOf(enumValue));
                                        wireType2 = limit;
                                        i2 = endGroup;
                                        unsafe3 = unsafe10;
                                        pos4 = pos;
                                        tag3 = typeAndOffset;
                                        oldNumber = position8;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                        registers3 = registers;
                                    }
                                }
                                break;
                            case 15:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                Unsafe unsafe11 = unsafe3;
                                bArr = data;
                                currentPresenceFieldOffset2 = position4;
                                typeAndOffset = tag6;
                                if (wireType3 != 0) {
                                    pos3 = pos;
                                    unsafe2 = unsafe11;
                                    wireType = wireType3;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                } else {
                                    position5 = ArrayDecoders.decodeVarint32(bArr, currentPresenceFieldOffset2, registers3);
                                    unsafe11.putInt(t2, fieldOffset, CodedInputStream.decodeZigZag32(registers3.int1));
                                    currentPresenceField2 |= presenceMask;
                                    wireType2 = limit;
                                    i2 = endGroup;
                                    unsafe3 = unsafe11;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    tag3 = typeAndOffset;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                }
                            case 16:
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                currentPresenceFieldOffset2 = position4;
                                typeAndOffset = tag6;
                                if (wireType3 != 0) {
                                    pos3 = pos;
                                    unsafe2 = unsafe3;
                                    wireType = wireType3;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                } else {
                                    bArr = data;
                                    int position11 = ArrayDecoders.decodeVarint64(bArr, currentPresenceFieldOffset2, registers3);
                                    unsafe3.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers3.long1));
                                    currentPresenceField2 |= presenceMask;
                                    wireType2 = limit;
                                    i2 = endGroup;
                                    pos4 = pos;
                                    position5 = position11;
                                    oldNumber = position8;
                                    tag3 = typeAndOffset;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    unsafe3 = unsafe3;
                                }
                            case 17:
                                if (wireType3 != 3) {
                                    currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                    currentPresenceFieldOffset2 = position4;
                                    typeAndOffset = tag6;
                                    pos3 = pos;
                                    unsafe2 = unsafe3;
                                    wireType = wireType3;
                                    unsafe = unsafe2;
                                    currentPresenceField = currentPresenceField2;
                                    tag2 = typeAndOffset;
                                    pos2 = pos3;
                                    position3 = currentPresenceFieldOffset2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                    break;
                                } else {
                                    int endTag = (position8 << 3) | 4;
                                    int currentPresenceFieldOffset4 = currentPresenceFieldOffset3;
                                    position5 = ArrayDecoders.decodeGroupField(messageSchema2.getMessageFieldSchema(pos), data, position4, limit, endTag, registers);
                                    if ((currentPresenceField2 & presenceMask) == 0) {
                                        unsafe3.putObject(t2, fieldOffset, registers3.object1);
                                    } else {
                                        unsafe3.putObject(t2, fieldOffset, Internal.mergeMessage(unsafe3.getObject(t2, fieldOffset), registers3.object1));
                                    }
                                    currentPresenceField2 |= presenceMask;
                                    bArr = data;
                                    wireType2 = limit;
                                    i2 = endGroup;
                                    pos4 = pos;
                                    oldNumber = position8;
                                    tag3 = tag6;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset4;
                                }
                            default:
                                pos3 = pos;
                                currentPresenceFieldOffset = currentPresenceFieldOffset3;
                                unsafe2 = unsafe3;
                                currentPresenceFieldOffset2 = position4;
                                wireType = wireType3;
                                typeAndOffset = tag6;
                                unsafe = unsafe2;
                                currentPresenceField = currentPresenceField2;
                                tag2 = typeAndOffset;
                                pos2 = pos3;
                                position3 = currentPresenceFieldOffset2;
                                currentPresenceFieldOffset3 = currentPresenceFieldOffset;
                                break;
                        }
                    } else {
                        int pos6 = pos;
                        int currentPresenceFieldOffset5 = currentPresenceFieldOffset3;
                        ArrayDecoders.Registers registers13 = registers3;
                        Unsafe unsafe12 = unsafe3;
                        bArr = data;
                        int currentPresenceFieldOffset6 = position2;
                        if (fieldType != 27) {
                            pos2 = pos6;
                            if (fieldType <= 49) {
                                currentPresenceField = currentPresenceField2;
                                tag2 = tag6;
                                unsafe = unsafe12;
                                position5 = parseRepeatedField(message, data, currentPresenceFieldOffset6, limit, tag6, position8, wireType3, pos2, typeAndOffset2, fieldType, fieldOffset, registers);
                                if (position5 != currentPresenceFieldOffset6) {
                                    messageSchema2 = this;
                                    t2 = message;
                                    bArr = data;
                                    wireType2 = limit;
                                    i2 = endGroup;
                                    registers3 = registers;
                                    oldNumber = position8;
                                    currentPresenceField2 = currentPresenceField;
                                    pos4 = pos2;
                                    tag3 = tag2;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                    unsafe3 = unsafe;
                                } else {
                                    position3 = position5;
                                    currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                }
                            } else {
                                currentPresenceField = currentPresenceField2;
                                unsafe = unsafe12;
                                tag2 = tag6;
                                position3 = currentPresenceFieldOffset6;
                                if (fieldType == 50) {
                                    if (wireType3 == 2) {
                                        position5 = parseMapField(message, data, position3, limit, pos2, fieldOffset, registers);
                                        if (position5 != position3) {
                                            messageSchema2 = this;
                                            t2 = message;
                                            bArr = data;
                                            wireType2 = limit;
                                            i2 = endGroup;
                                            registers3 = registers;
                                            oldNumber = position8;
                                            currentPresenceField2 = currentPresenceField;
                                            pos4 = pos2;
                                            tag3 = tag2;
                                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                            unsafe3 = unsafe;
                                        } else {
                                            position3 = position5;
                                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                        }
                                    } else {
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                    }
                                } else {
                                    position5 = parseOneofField(message, data, position3, limit, tag2, position8, wireType3, typeAndOffset2, fieldType, fieldOffset, pos2, registers);
                                    if (position5 == position3) {
                                        position3 = position5;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                    } else {
                                        messageSchema2 = this;
                                        t2 = message;
                                        bArr = data;
                                        wireType2 = limit;
                                        i2 = endGroup;
                                        registers3 = registers;
                                        oldNumber = position8;
                                        currentPresenceField2 = currentPresenceField;
                                        pos4 = pos2;
                                        tag3 = tag2;
                                        currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                                        unsafe3 = unsafe;
                                    }
                                }
                            }
                        } else if (wireType3 == 2) {
                            Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe12.getObject(t2, fieldOffset);
                            if (!list.isModifiable()) {
                                int size = list.size();
                                list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe12.putObject(t2, fieldOffset, list);
                            }
                            position5 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(pos6), tag6, data, currentPresenceFieldOffset6, limit, list, registers);
                            unsafe3 = unsafe12;
                            registers3 = registers13;
                            oldNumber = position8;
                            tag3 = tag6;
                            pos4 = pos6;
                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                            wireType2 = limit;
                            i2 = endGroup;
                        } else {
                            pos2 = pos6;
                            currentPresenceField = currentPresenceField2;
                            unsafe = unsafe12;
                            tag2 = tag6;
                            position3 = currentPresenceFieldOffset6;
                            currentPresenceFieldOffset3 = currentPresenceFieldOffset5;
                        }
                    }
                }
                i = endGroup;
                int tag7 = tag2;
                if (tag7 == i && i != 0) {
                    messageSchema = this;
                    tag3 = tag7;
                    currentPresenceField2 = currentPresenceField;
                    position5 = position3;
                } else {
                    if (!this.hasExtensions) {
                        registers2 = registers;
                    } else {
                        registers2 = registers;
                        if (registers2.extensionRegistry != ExtensionRegistryLite.getEmptyRegistry()) {
                            position5 = ArrayDecoders.decodeExtensionOrUnknownField(tag7, data, position3, limit, message, this.defaultInstance, this.unknownFieldSchema, registers);
                        }
                        t2 = message;
                        wireType2 = limit;
                        tag3 = tag7;
                        messageSchema2 = this;
                        oldNumber = position8;
                        currentPresenceField2 = currentPresenceField;
                        pos4 = pos2;
                        unsafe3 = unsafe;
                        i2 = i;
                        registers3 = registers2;
                        bArr = data;
                    }
                    position5 = ArrayDecoders.decodeUnknownField(tag7, data, position3, limit, getMutableUnknownFields(message), registers);
                    t2 = message;
                    wireType2 = limit;
                    tag3 = tag7;
                    messageSchema2 = this;
                    oldNumber = position8;
                    currentPresenceField2 = currentPresenceField;
                    pos4 = pos2;
                    unsafe3 = unsafe;
                    i2 = i;
                    registers3 = registers2;
                    bArr = data;
                }
            } else {
                unsafe = unsafe3;
                i = i2;
                messageSchema = messageSchema2;
            }
        }
        if (currentPresenceFieldOffset3 == -1) {
            t = message;
        } else {
            t = message;
            unsafe.putInt(t, currentPresenceFieldOffset3, currentPresenceField2);
        }
        UnknownFieldSetLite unknownFields = null;
        for (int i3 = messageSchema.checkInitializedCount; i3 < messageSchema.repeatedFieldOffsetStart; i3++) {
            unknownFields = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t, messageSchema.intArray[i3], unknownFields, messageSchema.unknownFieldSchema);
        }
        if (unknownFields != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t, unknownFields);
        }
        if (i == 0) {
            if (position5 != limit) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (position5 > limit || tag3 != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position5;
    }

    private int parseProto3Message(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        int position2;
        int pos;
        int pos2;
        Unsafe unsafe;
        int wireType;
        int position3;
        MessageSchema<T> messageSchema = this;
        T t = message;
        byte[] bArr = data;
        int i = limit;
        ArrayDecoders.Registers registers2 = registers;
        Unsafe unsafe2 = UNSAFE;
        int pos3 = 0;
        int oldNumber = -1;
        int position4 = position;
        while (position4 < i) {
            int position5 = position4 + 1;
            int tag2 = bArr[position4];
            if (tag2 >= 0) {
                tag = tag2;
                position2 = position5;
            } else {
                int position6 = ArrayDecoders.decodeVarint32(tag2, bArr, position5, registers2);
                tag = registers2.int1;
                position2 = position6;
            }
            int number = tag >>> 3;
            int wireType2 = tag & 7;
            if (number > oldNumber) {
                pos = messageSchema.positionForFieldNumber(number, pos3 / 3);
            } else {
                int pos4 = messageSchema.positionForFieldNumber(number);
                pos = pos4;
            }
            if (pos == -1) {
                pos2 = 0;
                unsafe = unsafe2;
            } else {
                int typeAndOffset = messageSchema.buffer[pos + 1];
                int fieldType = type(typeAndOffset);
                long fieldOffset = offset(typeAndOffset);
                if (fieldType <= 17) {
                    switch (fieldType) {
                        case 0:
                            Unsafe unsafe3 = unsafe2;
                            if (wireType2 != 1) {
                                pos2 = pos;
                                wireType = wireType2;
                                position3 = position2;
                                unsafe = unsafe3;
                                position2 = position3;
                                break;
                            } else {
                                UnsafeUtil.putDouble(t, fieldOffset, ArrayDecoders.decodeDouble(bArr, position2));
                                position4 = position2 + 8;
                                pos3 = pos;
                                unsafe2 = unsafe3;
                                oldNumber = number;
                                registers2 = registers;
                                break;
                            }
                        case 1:
                            Unsafe unsafe4 = unsafe2;
                            if (wireType2 != 5) {
                                pos2 = pos;
                                wireType = wireType2;
                                position3 = position2;
                                unsafe = unsafe4;
                                position2 = position3;
                                break;
                            } else {
                                UnsafeUtil.putFloat(t, fieldOffset, ArrayDecoders.decodeFloat(bArr, position2));
                                position4 = position2 + 4;
                                pos3 = pos;
                                unsafe2 = unsafe4;
                                oldNumber = number;
                                registers2 = registers;
                                break;
                            }
                        case 2:
                        case 3:
                            Unsafe unsafe5 = unsafe2;
                            if (wireType2 != 0) {
                                pos2 = pos;
                                wireType = wireType2;
                                position3 = position2;
                                unsafe = unsafe5;
                                position2 = position3;
                                break;
                            } else {
                                int position7 = ArrayDecoders.decodeVarint64(bArr, position2, registers2);
                                unsafe5.putLong(message, fieldOffset, registers2.long1);
                                pos3 = pos;
                                position4 = position7;
                                unsafe2 = unsafe5;
                                oldNumber = number;
                                registers2 = registers;
                                break;
                            }
                        case 4:
                        case 11:
                            Unsafe unsafe6 = unsafe2;
                            if (wireType2 != 0) {
                                unsafe = unsafe6;
                                pos2 = pos;
                                wireType = wireType2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                position4 = ArrayDecoders.decodeVarint32(bArr, position2, registers2);
                                unsafe6.putInt(t, fieldOffset, registers2.int1);
                                unsafe2 = unsafe6;
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 5:
                        case 14:
                            if (wireType2 != 1) {
                                pos2 = pos;
                                wireType = wireType2;
                                position3 = position2;
                                unsafe = unsafe2;
                                position2 = position3;
                                break;
                            } else {
                                unsafe2.putLong(message, fieldOffset, ArrayDecoders.decodeFixed64(bArr, position2));
                                position4 = position2 + 8;
                                pos3 = pos;
                                oldNumber = number;
                                unsafe2 = unsafe2;
                                break;
                            }
                        case 6:
                        case 13:
                            if (wireType2 != 5) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                unsafe2.putInt(t, fieldOffset, ArrayDecoders.decodeFixed32(bArr, position2));
                                position4 = position2 + 4;
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 7:
                            if (wireType2 != 0) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                int position8 = ArrayDecoders.decodeVarint64(bArr, position2, registers2);
                                UnsafeUtil.putBoolean(t, fieldOffset, registers2.long1 != 0);
                                position4 = position8;
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 8:
                            if (wireType2 != 2) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                if ((ENFORCE_UTF8_MASK & typeAndOffset) == 0) {
                                    position4 = ArrayDecoders.decodeString(bArr, position2, registers2);
                                } else {
                                    position4 = ArrayDecoders.decodeStringRequireUtf8(bArr, position2, registers2);
                                }
                                unsafe2.putObject(t, fieldOffset, registers2.object1);
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 9:
                            if (wireType2 != 2) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                position4 = ArrayDecoders.decodeMessageField(messageSchema.getMessageFieldSchema(pos), bArr, position2, i, registers2);
                                Object oldValue = unsafe2.getObject(t, fieldOffset);
                                if (oldValue == null) {
                                    unsafe2.putObject(t, fieldOffset, registers2.object1);
                                } else {
                                    unsafe2.putObject(t, fieldOffset, Internal.mergeMessage(oldValue, registers2.object1));
                                }
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 10:
                            if (wireType2 != 2) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                position4 = ArrayDecoders.decodeBytes(bArr, position2, registers2);
                                unsafe2.putObject(t, fieldOffset, registers2.object1);
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 12:
                            if (wireType2 != 0) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                position4 = ArrayDecoders.decodeVarint32(bArr, position2, registers2);
                                unsafe2.putInt(t, fieldOffset, registers2.int1);
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 15:
                            if (wireType2 != 0) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                position4 = ArrayDecoders.decodeVarint32(bArr, position2, registers2);
                                unsafe2.putInt(t, fieldOffset, CodedInputStream.decodeZigZag32(registers2.int1));
                                pos3 = pos;
                                oldNumber = number;
                                break;
                            }
                        case 16:
                            if (wireType2 != 0) {
                                pos2 = pos;
                                wireType = wireType2;
                                unsafe = unsafe2;
                                position3 = position2;
                                position2 = position3;
                                break;
                            } else {
                                int position9 = ArrayDecoders.decodeVarint64(bArr, position2, registers2);
                                unsafe2.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers2.long1));
                                pos3 = pos;
                                position4 = position9;
                                oldNumber = number;
                                break;
                            }
                        default:
                            pos2 = pos;
                            wireType = wireType2;
                            position3 = position2;
                            unsafe = unsafe2;
                            position2 = position3;
                            break;
                    }
                } else {
                    Unsafe unsafe7 = unsafe2;
                    if (fieldType == 27) {
                        if (wireType2 == 2) {
                            Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe7.getObject(t, fieldOffset);
                            if (!list.isModifiable()) {
                                int size = list.size();
                                list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                unsafe7.putObject(t, fieldOffset, list);
                            }
                            position4 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(pos), tag, data, position2, limit, list, registers);
                            messageSchema = this;
                            unsafe2 = unsafe7;
                            oldNumber = number;
                            pos3 = pos;
                            registers2 = registers;
                        } else {
                            pos2 = pos;
                            wireType = wireType2;
                            position3 = position2;
                            unsafe = unsafe7;
                            position2 = position3;
                        }
                    } else {
                        pos2 = pos;
                        if (fieldType > 49) {
                            position3 = position2;
                            unsafe = unsafe7;
                            if (fieldType == 50) {
                                wireType = wireType2;
                                if (wireType == 2) {
                                    position4 = parseMapField(message, data, position3, limit, pos2, fieldOffset, registers);
                                    if (position4 != position3) {
                                        messageSchema = this;
                                        t = message;
                                        bArr = data;
                                        i = limit;
                                        registers2 = registers;
                                        oldNumber = number;
                                        pos3 = pos2;
                                        unsafe2 = unsafe;
                                    } else {
                                        position2 = position4;
                                    }
                                } else {
                                    position2 = position3;
                                }
                            } else {
                                position4 = parseOneofField(message, data, position3, limit, tag, number, wireType2, typeAndOffset, fieldType, fieldOffset, pos2, registers);
                                if (position4 == position3) {
                                    position2 = position4;
                                } else {
                                    messageSchema = this;
                                    t = message;
                                    bArr = data;
                                    i = limit;
                                    registers2 = registers;
                                    oldNumber = number;
                                    pos3 = pos2;
                                    unsafe2 = unsafe;
                                }
                            }
                        } else {
                            int oldPosition = position2;
                            unsafe = unsafe7;
                            position4 = parseRepeatedField(message, data, position2, limit, tag, number, wireType2, pos2, typeAndOffset, fieldType, fieldOffset, registers);
                            if (position4 != oldPosition) {
                                messageSchema = this;
                                t = message;
                                bArr = data;
                                i = limit;
                                registers2 = registers;
                                oldNumber = number;
                                pos3 = pos2;
                                unsafe2 = unsafe;
                            } else {
                                position2 = position4;
                            }
                        }
                    }
                }
            }
            position4 = ArrayDecoders.decodeUnknownField(tag, data, position2, limit, getMutableUnknownFields(message), registers);
            messageSchema = this;
            t = message;
            bArr = data;
            i = limit;
            registers2 = registers;
            oldNumber = number;
            pos3 = pos2;
            unsafe2 = unsafe;
        }
        if (position4 != limit) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position4;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(message, data, position, limit, registers);
        } else {
            parseProto2Message(message, data, position, limit, 0, registers);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T message) {
        for (int i = this.checkInitializedCount; i < this.repeatedFieldOffsetStart; i++) {
            long offset = offset(typeAndOffsetAt(this.intArray[i]));
            Object mapField = UnsafeUtil.getObject(message, offset);
            if (mapField != null) {
                UnsafeUtil.putObject(message, offset, this.mapFieldSchema.toImmutable(mapField));
            }
        }
        int length = this.intArray.length;
        for (int i2 = this.repeatedFieldOffsetStart; i2 < length; i2++) {
            this.listFieldSchema.makeImmutableListAt(message, this.intArray[i2]);
        }
        this.unknownFieldSchema.makeImmutable(message);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(message);
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object mapField = UnsafeUtil.getObject(message, offset);
        if (mapField == null) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, mapField);
        } else if (this.mapFieldSchema.isImmutable(mapField)) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField, mapField);
            UnsafeUtil.putObject(message, offset, mapField);
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(mapField), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private final <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        Internal.EnumVerifier enumFieldVerifier;
        int iNumberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null || (enumFieldVerifier = getEnumFieldVerifier(i)) == null) {
            return ub;
        }
        return (UB) filterUnknownEnumMap(i, iNumberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema);
    }

    private final <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.newBuilder();
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilderNewCodedBuilder.getCodedOutput(), metadataForMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0079  */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isInitialized(T r14) {
        /*
            r13 = this;
            r0 = -1
            r1 = 0
            r2 = 0
        L3:
            int r3 = r13.checkInitializedCount
            r4 = 1
            r5 = 0
            if (r2 >= r3) goto L95
            int[] r3 = r13.intArray
            r3 = r3[r2]
            int r6 = r13.numberAt(r3)
            int r7 = r13.typeAndOffsetAt(r3)
            r8 = 0
            r9 = 0
            boolean r10 = r13.proto3
            if (r10 != 0) goto L33
            int[] r10 = r13.buffer
            int r11 = r3 + 2
            r8 = r10[r11]
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r10 = r10 & r8
            int r11 = r8 >>> 20
            int r9 = r4 << r11
            if (r10 == r0) goto L33
            r0 = r10
            sun.misc.Unsafe r4 = com.google.crypto.tink.shaded.protobuf.MessageSchema.UNSAFE
            long r11 = (long) r10
            int r1 = r4.getInt(r14, r11)
        L33:
            boolean r4 = isRequired(r7)
            if (r4 == 0) goto L40
            boolean r4 = r13.isFieldPresent(r14, r3, r1, r9)
            if (r4 != 0) goto L40
            return r5
        L40:
            int r4 = type(r7)
            r10 = 9
            if (r4 == r10) goto L80
            r10 = 17
            if (r4 == r10) goto L80
            r10 = 27
            if (r4 == r10) goto L79
            r10 = 60
            if (r4 == r10) goto L68
            r10 = 68
            if (r4 == r10) goto L68
            r10 = 49
            if (r4 == r10) goto L79
            r10 = 50
            if (r4 == r10) goto L61
            goto L91
        L61:
            boolean r4 = r13.isMapInitialized(r14, r7, r3)
            if (r4 != 0) goto L91
            return r5
        L68:
            boolean r4 = r13.isOneofPresent(r14, r6, r3)
            if (r4 == 0) goto L91
            com.google.crypto.tink.shaded.protobuf.Schema r4 = r13.getMessageFieldSchema(r3)
            boolean r4 = isInitialized(r14, r7, r4)
            if (r4 != 0) goto L91
            return r5
        L79:
            boolean r4 = r13.isListInitialized(r14, r7, r3)
            if (r4 != 0) goto L91
            return r5
        L80:
            boolean r4 = r13.isFieldPresent(r14, r3, r1, r9)
            if (r4 == 0) goto L91
            com.google.crypto.tink.shaded.protobuf.Schema r4 = r13.getMessageFieldSchema(r3)
            boolean r4 = isInitialized(r14, r7, r4)
            if (r4 != 0) goto L91
            return r5
        L91:
            int r2 = r2 + 1
            goto L3
        L95:
            boolean r2 = r13.hasExtensions
            if (r2 == 0) goto La6
            com.google.crypto.tink.shaded.protobuf.ExtensionSchema<?> r2 = r13.extensionSchema
            com.google.crypto.tink.shaded.protobuf.FieldSet r2 = r2.getExtensions(r14)
            boolean r2 = r2.isInitialized()
            if (r2 != 0) goto La6
            return r5
        La6:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.shaded.protobuf.MessageSchema.isInitialized(java.lang.Object):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        Object nested = UnsafeUtil.getObject(message, offset(typeAndOffset));
        return schema.isInitialized(nested);
    }

    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List<N> list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema schema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            N nested = list.get(i);
            if (!schema.isInitialized(nested)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.google.crypto.tink.shaded.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            SchemaFor = SchemaFor;
            if (SchemaFor == 0) {
                SchemaFor = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        long offset = offset(typeAndOffset);
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static int type(int value) {
        return (FIELD_TYPE_MASK & value) >>> 20;
    }

    private static boolean isRequired(int value) {
        return (REQUIRED_MASK & value) != 0;
    }

    private static boolean isEnforceUtf8(int value) {
        return (ENFORCE_UTF8_MASK & value) != 0;
    }

    private static long offset(int value) {
        return OFFSET_MASK & value;
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceField, int presenceMask) {
        if (this.proto3) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        if (this.proto3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    return UnsafeUtil.getDouble(message, offset) != 0.0d;
                case 1:
                    return UnsafeUtil.getFloat(message, offset) != 0.0f;
                case 2:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 3:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 4:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 5:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 6:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean(message, offset);
                case 8:
                    Object value = UnsafeUtil.getObject(message, offset);
                    if (value instanceof String) {
                        return true ^ ((String) value).isEmpty();
                    }
                    if (value instanceof ByteString) {
                        return true ^ ByteString.EMPTY.equals(value);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject(message, offset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
                case 11:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 12:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 13:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 14:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 15:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 16:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 17:
                    return UnsafeUtil.getObject(message, offset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        return (UnsafeUtil.getInt(message, (long) (OFFSET_MASK & presenceMaskAndOffset)) & presenceMask) != 0;
    }

    private void setFieldPresent(T message, int pos) {
        if (this.proto3) {
            return;
        }
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        long presenceFieldOffset = OFFSET_MASK & presenceMaskAndOffset;
        UnsafeUtil.putInt(message, presenceFieldOffset, UnsafeUtil.getInt(message, presenceFieldOffset) | presenceMask);
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (OFFSET_MASK & presenceMaskAndOffset)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffset & OFFSET_MASK)) == UnsafeUtil.getInt(other, (long) (OFFSET_MASK & presenceMaskAndOffset));
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        UnsafeUtil.putInt(message, OFFSET_MASK & presenceMaskAndOffset, fieldNumber);
    }

    private int positionForFieldNumber(int number) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, 0);
        }
        return -1;
    }

    private int positionForFieldNumber(int number, int min) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, min);
        }
        return -1;
    }

    private int slowPositionForFieldNumber(int number, int min) {
        int max = (this.buffer.length / 3) - 1;
        while (min <= max) {
            int mid = (max + min) >>> 1;
            int pos = mid * 3;
            int midFieldNumber = numberAt(pos);
            if (number == midFieldNumber) {
                return pos;
            }
            if (number < midFieldNumber) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
