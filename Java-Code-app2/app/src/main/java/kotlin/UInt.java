package kotlin;

import kotlin.ranges.UIntRange;

/* compiled from: UInt.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 m2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001mB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b%\u0010\u0005J\u0016\u0010&\u001a\u00020\u0000H\u0087\nø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010\u0005J\u0016\u0010(\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010\u0005J\u001b\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b+\u0010\u000fJ\u001b\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b,\u0010\u000bJ\u001b\u0010*\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b-\u0010\u001dJ\u001b\u0010*\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b.\u0010\u0016J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b0\u0010\u000bJ\u001b\u00101\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u000fJ\u001b\u00101\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u000bJ\u001b\u00101\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b4\u0010\u001dJ\u001b\u00101\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b5\u0010\u0016J\u001b\u00106\u001a\u0002072\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b8\u00109J\u001b\u0010:\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b;\u0010\u000fJ\u001b\u0010:\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010:\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b=\u0010\u001dJ\u001b\u0010:\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u0016J\u001e\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u0003H\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bA\u0010\u000bJ\u001e\u0010B\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u0003H\u0087\fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010\u000bJ\u001b\u0010D\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bE\u0010\u000fJ\u001b\u0010D\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bF\u0010\u000bJ\u001b\u0010D\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010\u001dJ\u001b\u0010D\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bH\u0010\u0016J\u0010\u0010I\u001a\u00020JH\u0087\b¢\u0006\u0004\bK\u0010LJ\u0010\u0010M\u001a\u00020NH\u0087\b¢\u0006\u0004\bO\u0010PJ\u0010\u0010Q\u001a\u00020RH\u0087\b¢\u0006\u0004\bS\u0010TJ\u0010\u0010U\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bV\u0010\u0005J\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\\H\u0087\b¢\u0006\u0004\b]\u0010^J\u000f\u0010_\u001a\u00020`H\u0016¢\u0006\u0004\ba\u0010bJ\u0016\u0010c\u001a\u00020\rH\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bd\u0010LJ\u0016\u0010e\u001a\u00020\u0000H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bf\u0010\u0005J\u0016\u0010g\u001a\u00020\u0011H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bh\u0010ZJ\u0016\u0010i\u001a\u00020\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bj\u0010^J\u001b\u0010k\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bl\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006n"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 1})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\UInt.smali */
public final class UInt implements Comparable<UInt> {
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UInt m94boximpl(int i) {
        return new UInt(i);
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private int m97compareToWZ4Q5Ns(int i) {
        return m98compareToWZ4Q5Ns(this.data, i);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m106equalsimpl(int i, Object obj) {
        return (obj instanceof UInt) && i == ((UInt) obj).getData();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m107equalsimpl0(int i, int i2) {
        return i == i2;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m108hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m106equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m108hashCodeimpl(this.data);
    }

    public String toString() {
        return m137toStringimpl(this.data);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getData() {
        return this.data;
    }

    private /* synthetic */ UInt(int data) {
        this.data = data;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m100constructorimpl(int data) {
        return data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return m97compareToWZ4Q5Ns(uInt.getData());
    }

    /* renamed from: compareTo-7apg3OU, reason: not valid java name */
    private static final int m95compareTo7apg3OU(int $this, byte other) {
        return UnsignedKt.uintCompare($this, m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: compareTo-xj2QHRw, reason: not valid java name */
    private static final int m99compareToxj2QHRw(int $this, short other) {
        return UnsignedKt.uintCompare($this, m100constructorimpl(65535 & other));
    }

    /* renamed from: compareTo-WZ4Q5Ns, reason: not valid java name */
    private static int m98compareToWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.uintCompare($this, other);
    }

    /* renamed from: compareTo-VKZWuLQ, reason: not valid java name */
    private static final int m96compareToVKZWuLQ(int $this, long other) {
        return UnsignedKt.ulongCompare(ULong.m170constructorimpl($this & 4294967295L), other);
    }

    /* renamed from: plus-7apg3OU, reason: not valid java name */
    private static final int m116plus7apg3OU(int $this, byte other) {
        return m100constructorimpl(m100constructorimpl(other & UByte.MAX_VALUE) + $this);
    }

    /* renamed from: plus-xj2QHRw, reason: not valid java name */
    private static final int m119plusxj2QHRw(int $this, short other) {
        return m100constructorimpl(m100constructorimpl(65535 & other) + $this);
    }

    /* renamed from: plus-WZ4Q5Ns, reason: not valid java name */
    private static final int m118plusWZ4Q5Ns(int $this, int other) {
        return m100constructorimpl($this + other);
    }

    /* renamed from: plus-VKZWuLQ, reason: not valid java name */
    private static final long m117plusVKZWuLQ(int $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & 4294967295L) + other);
    }

    /* renamed from: minus-7apg3OU, reason: not valid java name */
    private static final int m111minus7apg3OU(int $this, byte other) {
        return m100constructorimpl($this - m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: minus-xj2QHRw, reason: not valid java name */
    private static final int m114minusxj2QHRw(int $this, short other) {
        return m100constructorimpl($this - m100constructorimpl(65535 & other));
    }

    /* renamed from: minus-WZ4Q5Ns, reason: not valid java name */
    private static final int m113minusWZ4Q5Ns(int $this, int other) {
        return m100constructorimpl($this - other);
    }

    /* renamed from: minus-VKZWuLQ, reason: not valid java name */
    private static final long m112minusVKZWuLQ(int $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & 4294967295L) - other);
    }

    /* renamed from: times-7apg3OU, reason: not valid java name */
    private static final int m127times7apg3OU(int $this, byte other) {
        return m100constructorimpl(m100constructorimpl(other & UByte.MAX_VALUE) * $this);
    }

    /* renamed from: times-xj2QHRw, reason: not valid java name */
    private static final int m130timesxj2QHRw(int $this, short other) {
        return m100constructorimpl(m100constructorimpl(65535 & other) * $this);
    }

    /* renamed from: times-WZ4Q5Ns, reason: not valid java name */
    private static final int m129timesWZ4Q5Ns(int $this, int other) {
        return m100constructorimpl($this * other);
    }

    /* renamed from: times-VKZWuLQ, reason: not valid java name */
    private static final long m128timesVKZWuLQ(int $this, long other) {
        return ULong.m170constructorimpl(ULong.m170constructorimpl($this & 4294967295L) * other);
    }

    /* renamed from: div-7apg3OU, reason: not valid java name */
    private static final int m102div7apg3OU(int $this, byte other) {
        return UnsignedKt.m329uintDivideJ1ME1BU($this, m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: div-xj2QHRw, reason: not valid java name */
    private static final int m105divxj2QHRw(int $this, short other) {
        return UnsignedKt.m329uintDivideJ1ME1BU($this, m100constructorimpl(65535 & other));
    }

    /* renamed from: div-WZ4Q5Ns, reason: not valid java name */
    private static final int m104divWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.m329uintDivideJ1ME1BU($this, other);
    }

    /* renamed from: div-VKZWuLQ, reason: not valid java name */
    private static final long m103divVKZWuLQ(int $this, long other) {
        return UnsignedKt.m331ulongDivideeb3DHEI(ULong.m170constructorimpl($this & 4294967295L), other);
    }

    /* renamed from: rem-7apg3OU, reason: not valid java name */
    private static final int m121rem7apg3OU(int $this, byte other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU($this, m100constructorimpl(other & UByte.MAX_VALUE));
    }

    /* renamed from: rem-xj2QHRw, reason: not valid java name */
    private static final int m124remxj2QHRw(int $this, short other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU($this, m100constructorimpl(65535 & other));
    }

    /* renamed from: rem-WZ4Q5Ns, reason: not valid java name */
    private static final int m123remWZ4Q5Ns(int $this, int other) {
        return UnsignedKt.m330uintRemainderJ1ME1BU($this, other);
    }

    /* renamed from: rem-VKZWuLQ, reason: not valid java name */
    private static final long m122remVKZWuLQ(int $this, long other) {
        return UnsignedKt.m332ulongRemaindereb3DHEI(ULong.m170constructorimpl($this & 4294967295L), other);
    }

    /* renamed from: inc-pVg5ArA, reason: not valid java name */
    private static final int m109incpVg5ArA(int $this) {
        return m100constructorimpl($this + 1);
    }

    /* renamed from: dec-pVg5ArA, reason: not valid java name */
    private static final int m101decpVg5ArA(int $this) {
        return m100constructorimpl($this - 1);
    }

    /* renamed from: rangeTo-WZ4Q5Ns, reason: not valid java name */
    private static final UIntRange m120rangeToWZ4Q5Ns(int $this, int other) {
        return new UIntRange($this, other, null);
    }

    /* renamed from: shl-pVg5ArA, reason: not valid java name */
    private static final int m125shlpVg5ArA(int $this, int bitCount) {
        return m100constructorimpl($this << bitCount);
    }

    /* renamed from: shr-pVg5ArA, reason: not valid java name */
    private static final int m126shrpVg5ArA(int $this, int bitCount) {
        return m100constructorimpl($this >>> bitCount);
    }

    /* renamed from: and-WZ4Q5Ns, reason: not valid java name */
    private static final int m93andWZ4Q5Ns(int $this, int other) {
        return m100constructorimpl($this & other);
    }

    /* renamed from: or-WZ4Q5Ns, reason: not valid java name */
    private static final int m115orWZ4Q5Ns(int $this, int other) {
        return m100constructorimpl($this | other);
    }

    /* renamed from: xor-WZ4Q5Ns, reason: not valid java name */
    private static final int m142xorWZ4Q5Ns(int $this, int other) {
        return m100constructorimpl($this ^ other);
    }

    /* renamed from: inv-pVg5ArA, reason: not valid java name */
    private static final int m110invpVg5ArA(int $this) {
        return m100constructorimpl(~$this);
    }

    /* renamed from: toByte-impl, reason: not valid java name */
    private static final byte m131toByteimpl(int $this) {
        return (byte) $this;
    }

    /* renamed from: toShort-impl, reason: not valid java name */
    private static final short m136toShortimpl(int $this) {
        return (short) $this;
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    private static final int m134toIntimpl(int $this) {
        return $this;
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    private static final long m135toLongimpl(int $this) {
        return $this & 4294967295L;
    }

    /* renamed from: toUByte-w2LRezQ, reason: not valid java name */
    private static final byte m138toUBytew2LRezQ(int $this) {
        return UByte.m32constructorimpl((byte) $this);
    }

    /* renamed from: toUShort-Mh2AYeg, reason: not valid java name */
    private static final short m141toUShortMh2AYeg(int $this) {
        return UShort.m268constructorimpl((short) $this);
    }

    /* renamed from: toUInt-pVg5ArA, reason: not valid java name */
    private static final int m139toUIntpVg5ArA(int $this) {
        return $this;
    }

    /* renamed from: toULong-s-VKNKU, reason: not valid java name */
    private static final long m140toULongsVKNKU(int $this) {
        return ULong.m170constructorimpl($this & 4294967295L);
    }

    /* renamed from: toFloat-impl, reason: not valid java name */
    private static final float m133toFloatimpl(int $this) {
        return (float) UnsignedKt.uintToDouble($this);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    private static final double m132toDoubleimpl(int $this) {
        return UnsignedKt.uintToDouble($this);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m137toStringimpl(int $this) {
        return String.valueOf($this & 4294967295L);
    }
}
