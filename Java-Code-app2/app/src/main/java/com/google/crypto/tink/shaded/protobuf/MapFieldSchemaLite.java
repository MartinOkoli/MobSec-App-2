package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.MapEntryLite;
import java.util.Map;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MapFieldSchemaLite.smali */
class MapFieldSchemaLite implements MapFieldSchema {
    MapFieldSchemaLite() {
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Map<?, ?> forMutableMapData(Object mapField) {
        return (MapFieldLite) mapField;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public MapEntryLite.Metadata<?, ?> forMapMetadata(Object mapDefaultEntry) {
        return ((MapEntryLite) mapDefaultEntry).getMetadata();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Map<?, ?> forMapData(Object mapField) {
        return (MapFieldLite) mapField;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public boolean isImmutable(Object mapField) {
        return !((MapFieldLite) mapField).isMutable();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object toImmutable(Object mapField) {
        ((MapFieldLite) mapField).makeImmutable();
        return mapField;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object newMapField(Object unused) {
        return MapFieldLite.emptyMapField().mutableCopy();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public Object mergeFrom(Object destMapField, Object srcMapField) {
        return mergeFromLite(destMapField, srcMapField);
    }

    private static <K, V> MapFieldLite<K, V> mergeFromLite(Object destMapField, Object srcMapField) {
        MapFieldLite<K, V> mine = (MapFieldLite) destMapField;
        MapFieldLite<K, V> other = (MapFieldLite) srcMapField;
        if (!other.isEmpty()) {
            if (!mine.isMutable()) {
                mine = mine.mutableCopy();
            }
            mine.mergeFrom(other);
        }
        return mine;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MapFieldSchema
    public int getSerializedSize(int fieldNumber, Object mapField, Object mapDefaultEntry) {
        return getSerializedSizeLite(fieldNumber, mapField, mapDefaultEntry);
    }

    private static <K, V> int getSerializedSizeLite(int fieldNumber, Object mapField, Object defaultEntry) {
        MapFieldLite<K, V> mapFieldLite = (MapFieldLite) mapField;
        MapEntryLite<K, V> defaultEntryLite = (MapEntryLite) defaultEntry;
        if (mapFieldLite.isEmpty()) {
            return 0;
        }
        int size = 0;
        for (Map.Entry<K, V> entry : mapFieldLite.entrySet()) {
            size += defaultEntryLite.computeMessageSize(fieldNumber, entry.getKey(), entry.getValue());
        }
        return size;
    }
}
