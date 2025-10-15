package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Char.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0001H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\t\u001a\n\u0010\f\u001a\u00020\t*\u00020\u0001\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000eH\u0087\n¨\u0006\u000f"}, d2 = {"digitToChar", "", "", "radix", "digitToInt", "digitToIntOrNull", "(C)Ljava/lang/Integer;", "(CI)Ljava/lang/Integer;", "equals", "", "other", "ignoreCase", "isSurrogate", "plus", "", "kotlin-stdlib"}, k = 5, mv = {1, 4, 1}, xi = 1, xs = "kotlin/text/CharsKt")
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\text\CharsKt__CharKt.smali */
class CharsKt__CharKt extends CharsKt__CharJVMKt {
    public static final int digitToInt(char $this$digitToInt) {
        if ('0' <= $this$digitToInt && '9' >= $this$digitToInt) {
            return $this$digitToInt - '0';
        }
        throw new IllegalArgumentException("Char " + $this$digitToInt + " is not a decimal digit");
    }

    public static final int digitToInt(char $this$digitToInt, int radix) {
        Integer numDigitToIntOrNull = CharsKt.digitToIntOrNull($this$digitToInt, radix);
        if (numDigitToIntOrNull != null) {
            return numDigitToIntOrNull.intValue();
        }
        throw new IllegalArgumentException("Char " + $this$digitToInt + " is not a digit in the given radix=" + radix);
    }

    public static final Integer digitToIntOrNull(char $this$digitToIntOrNull) {
        if ('0' <= $this$digitToIntOrNull && '9' >= $this$digitToIntOrNull) {
            return Integer.valueOf($this$digitToIntOrNull - '0');
        }
        return null;
    }

    public static final Integer digitToIntOrNull(char $this$digitToIntOrNull, int radix) {
        if (2 > radix || 36 < radix) {
            throw new IllegalArgumentException("Invalid radix: " + radix + ". Valid radix values are in range 2..36");
        }
        if ('0' <= $this$digitToIntOrNull && '9' >= $this$digitToIntOrNull) {
            int digit = $this$digitToIntOrNull - 48;
            if (digit < radix) {
                return Integer.valueOf(digit);
            }
            return null;
        }
        char a = Intrinsics.compare((int) $this$digitToIntOrNull, 90) <= 0 ? 'A' : 'a';
        int digit2 = ($this$digitToIntOrNull - a) + 10;
        if (10 <= digit2 && radix > digit2) {
            return Integer.valueOf(digit2);
        }
        return null;
    }

    public static final char digitToChar(int $this$digitToChar) {
        if ($this$digitToChar >= 0 && 9 >= $this$digitToChar) {
            return (char) ($this$digitToChar + 48);
        }
        throw new IllegalArgumentException("Int " + $this$digitToChar + " is not a decimal digit");
    }

    public static final char digitToChar(int $this$digitToChar, int radix) {
        if (2 > radix || 36 < radix) {
            throw new IllegalArgumentException("Invalid radix: " + radix + ". Valid radix values are in range 2..36");
        }
        if ($this$digitToChar < 0 || $this$digitToChar >= radix) {
            throw new IllegalArgumentException("Digit " + $this$digitToChar + " does not represent a valid digit in radix " + radix);
        }
        if ($this$digitToChar >= 10) {
            return (char) (((char) ($this$digitToChar + 65)) - '\n');
        }
        return (char) ($this$digitToChar + 48);
    }

    private static final String plus(char $this$plus, String other) {
        return String.valueOf($this$plus) + other;
    }

    public static /* synthetic */ boolean equals$default(char c, char c2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return CharsKt.equals(c, c2, z);
    }

    public static final boolean equals(char $this$equals, char other, boolean ignoreCase) {
        if ($this$equals == other) {
            return true;
        }
        if (!ignoreCase) {
            return false;
        }
        if (Character.toUpperCase($this$equals) == Character.toUpperCase(other) || Character.toLowerCase($this$equals) == Character.toLowerCase(other)) {
            return true;
        }
        return false;
    }

    public static final boolean isSurrogate(char $this$isSurrogate) {
        return 55296 <= $this$isSurrogate && 57343 >= $this$isSurrogate;
    }
}
