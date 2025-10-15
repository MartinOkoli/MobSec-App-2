package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: UShort.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 i2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001iB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u001b\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u0010J\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0013J\u001b\u0010\u001b\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010\u001b\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b \u0010\u0018J\u001a\u0010!\u001a\u00020\"2\b\u0010\t\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b'\u0010(J\u0016\u0010)\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b,\u0010\u0005J\u001b\u0010-\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u0010J\u001b\u0010-\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b/\u0010\u0013J\u001b\u0010-\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u001fJ\u001b\u0010-\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u0018J\u001b\u00102\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b3\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u0010J\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b6\u0010\u0013J\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b7\u0010\u001fJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u0010\u0018J\u001b\u00109\u001a\u00020:2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b;\u0010<J\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u0010J\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u0013J\u001b\u0010=\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001fJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0018J\u001b\u0010B\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u000eH\u0087\nø\u0001\u0000¢\u0006\u0004\bC\u0010\u0010J\u001b\u0010B\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010\u0013J\u001b\u0010B\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u001fJ\u001b\u0010B\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u0018J\u0010\u0010G\u001a\u00020HH\u0087\b¢\u0006\u0004\bI\u0010JJ\u0010\u0010K\u001a\u00020LH\u0087\b¢\u0006\u0004\bM\u0010NJ\u0010\u0010O\u001a\u00020PH\u0087\b¢\u0006\u0004\bQ\u0010RJ\u0010\u0010S\u001a\u00020\rH\u0087\b¢\u0006\u0004\bT\u0010(J\u0010\u0010U\u001a\u00020VH\u0087\b¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bZ\u0010\u0005J\u000f\u0010[\u001a\u00020\\H\u0016¢\u0006\u0004\b]\u0010^J\u0016\u0010_\u001a\u00020\u000eH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b`\u0010JJ\u0016\u0010a\u001a\u00020\u0011H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bb\u0010(J\u0016\u0010c\u001a\u00020\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bd\u0010XJ\u0016\u0010e\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bf\u0010\u0005J\u001b\u0010g\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bh\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006j"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(SLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "(S)I", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 1})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\UShort.smali */
public final class UShort implements Comparable<UShort> {
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShort m262boximpl(short s) {
        return new UShort(s);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private int m266compareToxj2QHRw(short s) {
        return m267compareToxj2QHRw(this.data, s);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m274equalsimpl(short s, Object obj) {
        return (obj instanceof UShort) && s == ((UShort) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m275equalsimpl0(short s, short s2) {
        return s == s2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m276hashCodeimpl(short s) {
        return s;
    }

    public boolean equals(Object obj) {
        return m274equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m276hashCodeimpl(this.data);
    }

    public String toString() {
        return m303toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ short getData() {
        return this.data;
    }

    private /* synthetic */ UShort(short data) {
        this.data = data;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static short m268constructorimpl(short data) {
        return data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return m266compareToxj2QHRw(uShort.getData());
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m263compareTo7apg3OU(short $this, byte other) {
        return Intrinsics.compare(65535 & $this, other & UByte.MAX_VALUE);
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static int m267compareToxj2QHRw(short $this, short other) {
        return Intrinsics.compare($this & MAX_VALUE, 65535 & other);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static final int m265compareToWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.uintCompare(UInt.m100constructorimpl(65535 & $this), other);
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m264compareToVKZWuLQ(short $this, long other) {
        return UnsignedKt.ulongCompare(ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m284plus7apg3OU(short $this, byte other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl(65535 & $this) + UInt.m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m287plusxj2QHRw(short $this, short other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) + UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m286plusWZ4Q5Ns(short $this, int other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl(65535 & $this) + other);
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m285plusVKZWuLQ(short $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX) + other);
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m279minus7apg3OU(short $this, byte other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl(65535 & $this) - UInt.m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m282minusxj2QHRw(short $this, short other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) - UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m281minusWZ4Q5Ns(short $this, int other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl(65535 & $this) - other);
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m280minusVKZWuLQ(short $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX) - other);
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m293times7apg3OU(short $this, byte other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl(65535 & $this) * UInt.m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m296timesxj2QHRw(short $this, short other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl($this & MAX_VALUE) * UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m295timesWZ4Q5Ns(short $this, int other) {
        return UInt.m100constructorimpl(UInt.m100constructorimpl(65535 & $this) * other);
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m294timesVKZWuLQ(short $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX) * other);
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m270div7apg3OU(short $this, byte other) {
        return UnsignedKt.m329uintDivideJ1ME1BU(UInt.m100constructorimpl(65535 & $this), UInt.m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m273divxj2QHRw(short $this, short other) {
        return UnsignedKt.m329uintDivideJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m272divWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.m329uintDivideJ1ME1BU(UInt.m100constructorimpl(65535 & $this), other);
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m271divVKZWuLQ(short $this, long other) {
        return UnsignedKt.m331ulongDivideeb3DHEI(ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m289rem7apg3OU(short $this, byte other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU(UInt.m100constructorimpl(65535 & $this), UInt.m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m292remxj2QHRw(short $this, short other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m291remWZ4Q5Ns(short $this, int other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU(UInt.m100constructorimpl(65535 & $this), other);
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m290remVKZWuLQ(short $this, long other) {
        return UnsignedKt.m332ulongRemaindereb3DHEI(ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX), other);
    }

    /* renamed from: inc-Mh2AYeg, reason: not valid java name */
    private static final short m277incMh2AYeg(short $this) {
        return m268constructorimpl((short) ($this + 1));
    }

    /* renamed from: dec-Mh2AYeg, reason: not valid java name */
    private static final short m269decMh2AYeg(short $this) {
        return m268constructorimpl((short) ($this - 1));
    }

    /* renamed from: rangeTo-xj2QHRw, reason: not valid java name */
    private static final UIntRange m288rangeToxj2QHRw(short $this, short other) {
        return new UIntRange(UInt.m100constructorimpl($this & MAX_VALUE), UInt.m100constructorimpl(65535 & other), null);
    }

    /* renamed from: and-xj2QHRw, reason: not valid java name */
    private static final short m261andxj2QHRw(short $this, short other) {
        return m268constructorimpl((short) ($this & other));
    }

    /* renamed from: or-xj2QHRw, reason: not valid java name */
    private static final short m283orxj2QHRw(short $this, short other) {
        return m268constructorimpl((short) ($this | other));
    }

    /* renamed from: xor-xj2QHRw, reason: not valid java name */
    private static final short m308xorxj2QHRw(short $this, short other) {
        return m268constructorimpl((short) ($this ^ other));
    }

    /* renamed from: inv-Mh2AYeg, reason: not valid java name */
    private static final short m278invMh2AYeg(short $this) {
        return m268constructorimpl((short) (~$this));
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m297toByteimpl(short $this) {
        return (byte) $this;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m302toShortimpl(short $this) {
        return $this;
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m300toIntimpl(short $this) {
        return 65535 & $this;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m301toLongimpl(short $this) {
        return $this & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m304toUBytew2LRezQ(short $this) {
        return UByte.m32constructorimpl((byte) $this);
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m307toUShortMh2AYeg(short $this) {
        return $this;
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m305toUIntpVg5ArA(short $this) {
        return UInt.m100constructorimpl(65535 & $this);
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m306toULongsVKNKU(short $this) {
        return ULong.m170constructorimpl($this & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m299toFloatimpl(short $this) {
        return 65535 & $this;
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m298toDoubleimpl(short $this) {
        return 65535 & $this;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m303toStringimpl(short $this) {
        return String.valueOf(65535 & $this);
    }
}
