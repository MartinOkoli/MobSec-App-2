package kotlin.collections;

import java.util.Arrays;
import java.util.List;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\collections\ArraysUtilJVM.smali */
class ArraysUtilJVM {
    ArraysUtilJVM() {
    }

    static <T> List<T> asList(T[] array) {
        return Arrays.asList(array);
    }
}
