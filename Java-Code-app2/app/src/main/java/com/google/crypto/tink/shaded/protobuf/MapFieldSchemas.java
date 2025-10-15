package com.google.crypto.tink.shaded.protobuf;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MapFieldSchemas.smali */
final class MapFieldSchemas {
    private static final MapFieldSchema FULL_SCHEMA = loadSchemaForFullRuntime();
    private static final MapFieldSchema LITE_SCHEMA = new MapFieldSchemaLite();

    MapFieldSchemas() {
    }

    static MapFieldSchema full() {
        return FULL_SCHEMA;
    }

    static MapFieldSchema lite() {
        return LITE_SCHEMA;
    }

    private static MapFieldSchema loadSchemaForFullRuntime() {
        try {
            Class<?> clazz = Class.forName("com.google.crypto.tink.shaded.protobuf.MapFieldSchemaFull");
            return (MapFieldSchema) clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
