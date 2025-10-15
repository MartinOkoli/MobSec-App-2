package kotlin.jvm.internal;

import java.io.Serializable;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref.smali */
public class Ref {
    private Ref() {
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$ObjectRef.smali */
    public static final class ObjectRef<T> implements Serializable {
        public T element;

        public String toString() {
            return String.valueOf(this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$ByteRef.smali */
    public static final class ByteRef implements Serializable {
        public byte element;

        public String toString() {
            return String.valueOf((int) this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$ShortRef.smali */
    public static final class ShortRef implements Serializable {
        public short element;

        public String toString() {
            return String.valueOf((int) this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$IntRef.smali */
    public static final class IntRef implements Serializable {
        public int element;

        public String toString() {
            return String.valueOf(this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$FloatRef.smali */
    public static final class FloatRef implements Serializable {
        public float element;

        public String toString() {
            return String.valueOf(this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$DoubleRef.smali */
    public static final class DoubleRef implements Serializable {
        public double element;

        public String toString() {
            return String.valueOf(this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$CharRef.smali */
    public static final class CharRef implements Serializable {
        public char element;

        public String toString() {
            return String.valueOf(this.element);
        }
    }

    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\jvm\internal\Ref$BooleanRef.smali */
    public static final class BooleanRef implements Serializable {
        public boolean element;

        public String toString() {
            return String.valueOf(this.element);
        }
    }
}
