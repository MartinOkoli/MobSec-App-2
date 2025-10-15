package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import kotlin.text.Typography;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\com\google\crypto\tink\shaded\protobuf\MessageLiteToString.smali */
final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    MessageLiteToString() {
    }

    static String toString(MessageLite messageLite, String commentString) throws SecurityException {
        StringBuilder buffer = new StringBuilder();
        buffer.append("# ");
        buffer.append(commentString);
        reflectivePrintWithIndent(messageLite, buffer, 0);
        return buffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder sb, int i) throws SecurityException {
        HashMap map;
        int iBooleanValue;
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        int i2 = 0;
        for (Method method : messageLite.getClass().getDeclaredMethods()) {
            map3.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map2.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith(LIST_SUFFIX) && !strSubstring.endsWith(BUILDER_LIST_SUFFIX) && !strSubstring.equals(LIST_SUFFIX)) {
                String str2 = strSubstring.substring(i2, 1).toLowerCase() + strSubstring.substring(1, strSubstring.length() - LIST_SUFFIX.length());
                Method method2 = (Method) map2.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    printField(sb, i, camelCaseToSnakeCase(str2), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[i2]));
                }
            }
            if (strSubstring.endsWith(MAP_SUFFIX) && !strSubstring.equals(MAP_SUFFIX)) {
                String str3 = strSubstring.substring(i2, 1).toLowerCase() + strSubstring.substring(1, strSubstring.length() - MAP_SUFFIX.length());
                Method method3 = (Method) map2.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    printField(sb, i, camelCaseToSnakeCase(str3), GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[i2]));
                }
            }
            if (((Method) map3.get("set" + strSubstring)) != null) {
                if (strSubstring.endsWith(BYTES_SUFFIX)) {
                    if (map2.containsKey("get" + strSubstring.substring(i2, strSubstring.length() - BYTES_SUFFIX.length()))) {
                    }
                }
                String str4 = strSubstring.substring(i2, 1).toLowerCase() + strSubstring.substring(1);
                Method method4 = (Method) map2.get("get" + strSubstring);
                Method method5 = (Method) map2.get("has" + strSubstring);
                if (method4 != null) {
                    Object objInvokeOrDie = GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[i2]);
                    if (method5 == null) {
                        map = map2;
                        iBooleanValue = !isDefaultValue(objInvokeOrDie) ? 1 : i2;
                    } else {
                        map = map2;
                        iBooleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method5, messageLite, new Object[i2])).booleanValue();
                    }
                    if (iBooleanValue == 0) {
                        map2 = map;
                        i2 = 0;
                    } else {
                        printField(sb, i, camelCaseToSnakeCase(str4), objInvokeOrDie);
                        map2 = map;
                        i2 = 0;
                    }
                } else {
                    i2 = 0;
                }
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<T, Object>> it = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                printField(sb, i, "[" + ((GeneratedMessageLite.ExtensionDescriptor) entry.getKey()).getNumber() + "]", entry.getValue());
            }
        }
        if (((GeneratedMessageLite) messageLite).unknownFields != null) {
            ((GeneratedMessageLite) messageLite).unknownFields.printWithIndent(sb, i);
        }
    }

    private static boolean isDefaultValue(Object o) {
        if (o instanceof Boolean) {
            return !((Boolean) o).booleanValue();
        }
        if (o instanceof Integer) {
            return ((Integer) o).intValue() == 0;
        }
        if (o instanceof Float) {
            return ((Float) o).floatValue() == 0.0f;
        }
        if (o instanceof Double) {
            return ((Double) o).doubleValue() == 0.0d;
        }
        if (o instanceof String) {
            return o.equals("");
        }
        if (o instanceof ByteString) {
            return o.equals(ByteString.EMPTY);
        }
        return o instanceof MessageLite ? o == ((MessageLite) o).getDefaultInstanceForType() : (o instanceof Enum) && ((Enum) o).ordinal() == 0;
    }

    static final void printField(StringBuilder buffer, int indent, String name, Object object) throws SecurityException {
        if (object instanceof List) {
            List<?> list = (List) object;
            Iterator<?> it = list.iterator();
            while (it.hasNext()) {
                printField(buffer, indent, name, it.next());
            }
            return;
        }
        if (object instanceof Map) {
            Map<?, ?> map = (Map) object;
            Iterator<Map.Entry<?, ?>> it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                printField(buffer, indent, name, it2.next());
            }
            return;
        }
        buffer.append('\n');
        for (int i = 0; i < indent; i++) {
            buffer.append(' ');
        }
        buffer.append(name);
        if (object instanceof String) {
            buffer.append(": \"");
            buffer.append(TextFormatEscaper.escapeText((String) object));
            buffer.append(Typography.quote);
            return;
        }
        if (object instanceof ByteString) {
            buffer.append(": \"");
            buffer.append(TextFormatEscaper.escapeBytes((ByteString) object));
            buffer.append(Typography.quote);
            return;
        }
        if (object instanceof GeneratedMessageLite) {
            buffer.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite) object, buffer, indent + 2);
            buffer.append("\n");
            for (int i2 = 0; i2 < indent; i2++) {
                buffer.append(' ');
            }
            buffer.append("}");
            return;
        }
        if (object instanceof Map.Entry) {
            buffer.append(" {");
            Map.Entry<?, ?> entry = (Map.Entry) object;
            printField(buffer, indent + 2, "key", entry.getKey());
            printField(buffer, indent + 2, "value", entry.getValue());
            buffer.append("\n");
            for (int i3 = 0; i3 < indent; i3++) {
                buffer.append(' ');
            }
            buffer.append("}");
            return;
        }
        buffer.append(": ");
        buffer.append(object.toString());
    }

    private static final String camelCaseToSnakeCase(String camelCase) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                builder.append("_");
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString();
    }
}
