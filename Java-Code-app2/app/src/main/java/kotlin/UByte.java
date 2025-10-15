package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;

/* compiled from: UByte.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 i2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001iB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u000fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0012J\u001b\u0010\u001b\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b'\u0010(J\u0016\u0010)\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010\u0005J\u001b\u0010-\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u000fJ\u001b\u0010-\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u0012J\u001b\u0010-\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u001fJ\u001b\u0010-\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u0018J\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b3\u0010\u000bJ\u001b\u00104\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u000fJ\u001b\u00104\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0012J\u001b\u00104\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u001fJ\u001b\u00104\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\u0018J\u001b\u00109\u001a\u00020:2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u0012J\u001b\u0010=\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001fJ\u001b\u0010=\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0018J\u001b\u0010B\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u000fJ\u001b\u0010B\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0010H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u0012J\u001b\u0010B\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\u0016H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u0018J\u0010\u0010G\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bH\u0010\u0005J\u0010\u0010I\u001a\u00020JH\u0087\b¢\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020NH\u0087\b¢\u0006\u0004\bO\u0010PJ\u0010\u0010Q\u001a\u00020\rH\u0087\b¢\u0006\u0004\bR\u0010(J\u0010\u0010S\u001a\u00020TH\u0087\b¢\u0006\u0004\bU\u0010VJ\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u000f\u0010[\u001a\u00020\\H\u0016¢\u0006\u0004\b]\u0010^J\u0016\u0010_\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b`\u0010\u0005J\u0016\u0010a\u001a\u00020\u0010H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bb\u0010(J\u0016\u0010c\u001a\u00020\u0013H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bd\u0010VJ\u0016\u0010e\u001a\u00020\u0016H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bf\u0010ZJ\u001b\u0010g\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bh\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006j"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 1})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\UByte.smali */
public final class UByte implements Comparable<UByte> {
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByte m26boximpl(byte b) {
        return new UByte(b);
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private int m27compareTo7apg3OU(byte b) {
        return m28compareTo7apg3OU(this.data, b);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m38equalsimpl(byte b, Object obj) {
        return (obj instanceof UByte) && b == ((UByte) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m39equalsimpl0(byte b, byte b2) {
        return b == b2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m40hashCodeimpl(byte b) {
        return b;
    }

    public boolean equals(Object obj) {
        return m38equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m40hashCodeimpl(this.data);
    }

    public String toString() {
        return m67toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ byte getData() {
        return this.data;
    }

    private /* synthetic */ UByte(byte data) {
        this.data = data;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static byte m32constructorimpl(byte data) {
        return data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return m27compareTo7apg3OU(uByte.getData());
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static int m28compareTo7apg3OU(byte $this, byte other) {
        return Intrinsics.compare($this & MAX_VALUE, other & MAX_VALUE);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m31compareToxj2QHRw(byte $this, short other) {
        return Intrinsics.compare($this & MAX_VALUE, 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m30compareToWZ4Q5Ns(byte $this, int other) {
        return UnsignedKt.uintCompare(UInt.m100constructorimpl($this & MAX_VALUE), other);
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m29compareToVKZWuLQ(byte $this, long other) {
        return UnsignedKt.ulongCompare(ULong.m170constructorimpl($this & 255), other);
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m48plus7apg3OU(byte $this, byte other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) + UInt.m100constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m51plusxj2QHRw(byte $this, short other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) + UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m50plusWZ4Q5Ns(byte $this, int other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) + other);
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m49plusVKZWuLQ(byte $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & 255) + other);
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m43minus7apg3OU(byte $this, byte other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) - UInt.m100constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m46minusxj2QHRw(byte $this, short other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) - UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m45minusWZ4Q5Ns(byte $this, int other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) - other);
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m44minusVKZWuLQ(byte $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & 255) - other);
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m57times7apg3OU(byte $this, byte other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) * UInt.m100constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m60timesxj2QHRw(byte $this, short other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) * UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m59timesWZ4Q5Ns(byte $this, int other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) * other);
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m58timesVKZWuLQ(byte $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & 255) * other);
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m34div7apg3OU(byte $this, byte other) {
        return UnsignedKt.m329uintDivideJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m37divxj2QHRw(byte $this, short other) {
        return UnsignedKt.m329uintDivideJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m36divWZ4Q5Ns(byte $this, int other) {
        return UnsignedKt.m329uintDivideJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), other);
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m35divVKZWuLQ(byte $this, long other) {
        return UnsignedKt.m331ulongDivideeb3DHEI(ULong.m170constructorimpl($this & 255), other);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m53rem7apg3OU(byte $this, byte other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(other & MAX_VALUE));
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m56remxj2QHRw(byte $this, short other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m55remWZ4Q5Ns(byte $this, int other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), other);
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m54remVKZWuLQ(byte $this, long other) {
        return UnsignedKt.m332ulongRemaindereb3DHEI(ULong.m170constructorimpl($this & 255), other);
    }

    /* renamed from: inc-w2LRezQ, reason: not valid java name */
    private static final byte m41incw2LRezQ(byte $this) {
        return m32constructorimpl((byte) ($this + 1));
    }

    /* renamed from: dec-w2LRezQ, reason: not valid java name */
    private static final byte m33decw2LRezQ(byte $this) {
        return m32constructorimpl((byte) ($this - 1));
    }

    /* renamed from: rangeTo-7apg3OU, reason: not valid java name */
    private static final UIntRange m52rangeTo7apg3OU(byte $this, byte other) {
        return new UIntRange(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(other & MAX_VALUE), null);
    }

    /* renamed from: and-7apg3OU, reason: not valid java name */
    private static final byte m25and7apg3OU(byte $this, byte other) {
        return m32constructorimpl((byte) ($this & other));
    }

    /* renamed from: or-7apg3OU, reason: not valid java name */
    private static final byte m47or7apg3OU(byte $this, byte other) {
        return m32constructorimpl((byte) ($this | other));
    }

    /* renamed from: xor-7apg3OU, reason: not valid java name */
    private static final byte m72xor7apg3OU(byte $this, byte other) {
        return m32constructorimpl((byte) ($this ^ other));
    }

    /* renamed from: inv-w2LRezQ, reason: not valid java name */
    private static final byte m42invw2LRezQ(byte $this) {
        return m32constructorimpl((byte) (~$this));
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m61toByteimpl(byte $this) {
        return $this;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m66toShortimpl(byte $this) {
        return (short) ($this & 255);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m64toIntimpl(byte $this) {
        return $this & MAX_VALUE;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m65toLongimpl(byte $this) {
        return $this & 255;
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m68toUBytew2LRezQ(byte $this) {
        return $this;
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m71toUShortMh2AYeg(byte $this) {
        return UShort.m268constructorimpl((short) ($this & 255));
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m69toUIntpVg5ArA(byte $this) {
        return UInt.m100constructorimpl($this & MAX_VALUE);
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m70toULongsVKNKU(byte $this) {
        return ULong.m170constructorimpl($this & 255);
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m63toFloatimpl(byte $this) {
        return $this & MAX_VALUE;
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m62toDoubleimpl(byte $this) {
        return $this & MAX_VALUE;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m67toStringimpl(byte $this) {
        return String.valueOf($this & MAX_VALUE);
    }
}
