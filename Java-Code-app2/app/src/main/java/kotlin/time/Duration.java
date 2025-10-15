package kotlin.time;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* compiled from: Duration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0087@\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,J\u001e\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010-J\u001b\u0010)\u001a\u00020\u00032\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010,J\u001a\u0010/\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u000101HÖ\u0003¢\u0006\u0004\b2\u00103J\u0010\u00104\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b5\u0010\rJ\r\u00106\u001a\u000200¢\u0006\u0004\b7\u00108J\r\u00109\u001a\u000200¢\u0006\u0004\b:\u00108J\r\u0010;\u001a\u000200¢\u0006\u0004\b<\u00108J\r\u0010=\u001a\u000200¢\u0006\u0004\b>\u00108J\u001b\u0010?\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b@\u0010,J\u001b\u0010A\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bB\u0010,J\u0017\u0010C\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¢\u0006\u0004\bD\u0010(J\u001e\u0010E\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\u0003H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010,J\u001e\u0010E\u001a\u00020\u00002\u0006\u0010*\u001a\u00020\tH\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010-J\u009d\u0001\u0010G\u001a\u0002HH\"\u0004\b\u0000\u0010H2u\u0010I\u001aq\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(M\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(O\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(P\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002HH0JH\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bR\u0010SJ\u0088\u0001\u0010G\u001a\u0002HH\"\u0004\b\u0000\u0010H2`\u0010I\u001a\\\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(N\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(O\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(P\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002HH0TH\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bR\u0010UJs\u0010G\u001a\u0002HH\"\u0004\b\u0000\u0010H2K\u0010I\u001aG\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(O\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(P\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002HH0VH\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bR\u0010WJ^\u0010G\u001a\u0002HH\"\u0004\b\u0000\u0010H26\u0010I\u001a2\u0012\u0013\u0012\u00110Y¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(P\u0012\u0013\u0012\u00110\t¢\u0006\f\bK\u0012\b\bL\u0012\u0004\b\b(Q\u0012\u0004\u0012\u0002HH0XH\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bR\u0010ZJ\u0019\u0010[\u001a\u00020\u00032\n\u0010\\\u001a\u00060]j\u0002`^¢\u0006\u0004\b_\u0010`J\u0019\u0010a\u001a\u00020\t2\n\u0010\\\u001a\u00060]j\u0002`^¢\u0006\u0004\bb\u0010cJ\r\u0010d\u001a\u00020e¢\u0006\u0004\bf\u0010gJ\u0019\u0010h\u001a\u00020Y2\n\u0010\\\u001a\u00060]j\u0002`^¢\u0006\u0004\bi\u0010jJ\r\u0010k\u001a\u00020Y¢\u0006\u0004\bl\u0010mJ\r\u0010n\u001a\u00020Y¢\u0006\u0004\bo\u0010mJ\u000f\u0010p\u001a\u00020eH\u0016¢\u0006\u0004\bq\u0010gJ#\u0010p\u001a\u00020e2\n\u0010\\\u001a\u00060]j\u0002`^2\b\b\u0002\u0010r\u001a\u00020\t¢\u0006\u0004\bq\u0010sJ\u0016\u0010t\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bu\u0010\u0005R\u0017\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005R\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0011\u0010\u0018\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0005R\u0011\u0010\u001a\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005R\u001a\u0010\u001c\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\rR\u001a\u0010\u001f\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\rR\u001a\u0010\"\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006w"}, d2 = {"Lkotlin/time/Duration;", "", "value", "", "constructor-impl", "(D)D", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(D)I", "inDays", "getInDays-impl", "inHours", "getInHours-impl", "inMicroseconds", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds-impl", "inMinutes", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds-impl", "inSeconds", "getInSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "compareTo", "other", "compareTo-LRDsOJo", "(DD)I", "div", "scale", "div-UwyO8pc", "(DD)D", "(DI)D", "div-LRDsOJo", "equals", "", "", "equals-impl", "(DLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "isFinite", "isFinite-impl", "(D)Z", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "plus", "plus-LRDsOJo", "precision", "precision-impl", "times", "times-UwyO8pc", "toComponents", ExifInterface.GPS_DIRECTION_TRUE, "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(DLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(DLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(DLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "", "(DLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(DLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(DLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(D)Ljava/lang/String;", "toLong", "toLong-impl", "(DLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "(D)J", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(DLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 4, 1})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\time\Duration.smali */
public final class Duration implements Comparable<Duration> {
    private final double value;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double ZERO = m1272constructorimpl(0.0d);
    private static final double INFINITE = m1272constructorimpl(Double.POSITIVE_INFINITY);

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m1270boximpl(double d) {
        return new Duration(d);
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m1276equalsimpl(double d, Object obj) {
        return (obj instanceof Duration) && Double.compare(d, ((Duration) obj).getValue()) == 0;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m1277equalsimpl0(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m1290hashCodeimpl(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m1314compareToLRDsOJo(double d) {
        return m1271compareToLRDsOJo(this.value, d);
    }

    public boolean equals(Object obj) {
        return m1276equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m1290hashCodeimpl(this.value);
    }

    public String toString() {
        return m1310toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ double getValue() {
        return this.value;
    }

    private /* synthetic */ Duration(double value) {
        this.value = value;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static double m1272constructorimpl(double value) {
        return value;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m1314compareToLRDsOJo(duration.getValue());
    }

    /* compiled from: Duration.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000f2\n\u0010\u0010\u001a\u00060\u000ej\u0002`\u000fR\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0011"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()D", "D", "ZERO", "getZERO-UwyO8pc", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "kotlin-stdlib"}, k = 1, mv = {1, 4, 1})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\time\Duration$Companion.smali */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* renamed from: getZERO-UwyO8pc, reason: not valid java name */
        public final double m1317getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* renamed from: getINFINITE-UwyO8pc, reason: not valid java name */
        public final double m1316getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        public final double convert(double value, TimeUnit sourceUnit, TimeUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }
    }

    /* renamed from: unaryMinus-UwyO8pc, reason: not valid java name */
    public static final double m1313unaryMinusUwyO8pc(double $this) {
        return m1272constructorimpl(-$this);
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final double m1296plusLRDsOJo(double $this, double other) {
        return m1272constructorimpl($this + other);
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public static final double m1295minusLRDsOJo(double $this, double other) {
        return m1272constructorimpl($this - other);
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final double m1299timesUwyO8pc(double $this, int scale) {
        return m1272constructorimpl(scale * $this);
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final double m1298timesUwyO8pc(double $this, double scale) {
        return m1272constructorimpl($this * scale);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final double m1275divUwyO8pc(double $this, int scale) {
        return m1272constructorimpl($this / scale);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final double m1274divUwyO8pc(double $this, double scale) {
        return m1272constructorimpl($this / scale);
    }

    /* renamed from: div-LRDsOJo, reason: not valid java name */
    public static final double m1273divLRDsOJo(double $this, double other) {
        return $this / other;
    }

    /* renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m1293isNegativeimpl(double $this) {
        return $this < ((double) 0);
    }

    /* renamed from: isPositive-impl, reason: not valid java name */
    public static final boolean m1294isPositiveimpl(double $this) {
        return $this > ((double) 0);
    }

    /* renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m1292isInfiniteimpl(double $this) {
        return Double.isInfinite($this);
    }

    /* renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m1291isFiniteimpl(double $this) {
        return (Double.isInfinite($this) || Double.isNaN($this)) ? false : true;
    }

    /* renamed from: getAbsoluteValue-UwyO8pc, reason: not valid java name */
    public static final double m1278getAbsoluteValueUwyO8pc(double $this) {
        return m1293isNegativeimpl($this) ? m1313unaryMinusUwyO8pc($this) : $this;
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m1271compareToLRDsOJo(double $this, double other) {
        return Double.compare($this, other);
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1303toComponentsimpl(double $this, Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Integer.valueOf((int) m1280getInDaysimpl($this)), Integer.valueOf(m1279getHoursComponentimpl($this)), Integer.valueOf(m1287getMinutesComponentimpl($this)), Integer.valueOf(m1289getSecondsComponentimpl($this)), Integer.valueOf(m1288getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1302toComponentsimpl(double $this, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Integer.valueOf((int) m1281getInHoursimpl($this)), Integer.valueOf(m1287getMinutesComponentimpl($this)), Integer.valueOf(m1289getSecondsComponentimpl($this)), Integer.valueOf(m1288getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1301toComponentsimpl(double $this, Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Integer.valueOf((int) m1284getInMinutesimpl($this)), Integer.valueOf(m1289getSecondsComponentimpl($this)), Integer.valueOf(m1288getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m1300toComponentsimpl(double $this, Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf((long) m1286getInSecondsimpl($this)), Integer.valueOf(m1288getNanosecondsComponentimpl($this)));
    }

    /* renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m1279getHoursComponentimpl(double $this) {
        return (int) (m1281getInHoursimpl($this) % 24);
    }

    /* renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m1287getMinutesComponentimpl(double $this) {
        return (int) (m1284getInMinutesimpl($this) % 60);
    }

    /* renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m1289getSecondsComponentimpl(double $this) {
        return (int) (m1286getInSecondsimpl($this) % 60);
    }

    /* renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m1288getNanosecondsComponentimpl(double $this) {
        return (int) (m1285getInNanosecondsimpl($this) % 1.0E9d);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    public static final double m1304toDoubleimpl(double $this, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return DurationUnitKt.convertDurationUnit($this, DurationKt.getStorageUnit(), unit);
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    public static final long m1307toLongimpl(double $this, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (long) m1304toDoubleimpl($this, unit);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    public static final int m1305toIntimpl(double $this, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (int) m1304toDoubleimpl($this, unit);
    }

    /* renamed from: getInDays-impl, reason: not valid java name */
    public static final double m1280getInDaysimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl, reason: not valid java name */
    public static final double m1281getInHoursimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl, reason: not valid java name */
    public static final double m1284getInMinutesimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl, reason: not valid java name */
    public static final double m1286getInSecondsimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl, reason: not valid java name */
    public static final double m1283getInMillisecondsimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl, reason: not valid java name */
    public static final double m1282getInMicrosecondsimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl, reason: not valid java name */
    public static final double m1285getInNanosecondsimpl(double $this) {
        return m1304toDoubleimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongNanoseconds-impl, reason: not valid java name */
    public static final long m1309toLongNanosecondsimpl(double $this) {
        return m1307toLongimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: toLongMilliseconds-impl, reason: not valid java name */
    public static final long m1308toLongMillisecondsimpl(double $this) {
        return m1307toLongimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m1310toStringimpl(double $this) {
        TimeUnit unit;
        String upToDecimals;
        if (m1292isInfiniteimpl($this)) {
            return String.valueOf($this);
        }
        if ($this == 0.0d) {
            return "0s";
        }
        double absNs = m1285getInNanosecondsimpl(m1278getAbsoluteValueUwyO8pc($this));
        boolean scientific = false;
        int maxDecimals = 0;
        if (absNs < 1.0E-6d) {
            unit = TimeUnit.SECONDS;
            scientific = true;
        } else if (absNs < 1) {
            unit = TimeUnit.NANOSECONDS;
            maxDecimals = 7;
        } else if (absNs < 1000.0d) {
            unit = TimeUnit.NANOSECONDS;
        } else if (absNs < 1000000.0d) {
            unit = TimeUnit.MICROSECONDS;
        } else if (absNs < 1.0E9d) {
            unit = TimeUnit.MILLISECONDS;
        } else if (absNs < 1.0E12d) {
            unit = TimeUnit.SECONDS;
        } else if (absNs < 6.0E13d) {
            unit = TimeUnit.MINUTES;
        } else if (absNs < 3.6E15d) {
            unit = TimeUnit.HOURS;
        } else if (absNs < 8.64E20d) {
            unit = TimeUnit.DAYS;
        } else {
            unit = TimeUnit.DAYS;
            scientific = true;
        }
        double value = m1304toDoubleimpl($this, unit);
        StringBuilder sb = new StringBuilder();
        if (scientific) {
            upToDecimals = FormatToDecimalsKt.formatScientific(value);
        } else {
            upToDecimals = maxDecimals > 0 ? FormatToDecimalsKt.formatUpToDecimals(value, maxDecimals) : FormatToDecimalsKt.formatToExactDecimals(value, m1297precisionimpl($this, Math.abs(value)));
        }
        sb.append(upToDecimals);
        sb.append(DurationUnitKt.shortName(unit));
        return sb.toString();
    }

    /* renamed from: precision-impl, reason: not valid java name */
    private static final int m1297precisionimpl(double $this, double value) {
        if (value < 1) {
            return 3;
        }
        if (value < 10) {
            return 2;
        }
        if (value < 100) {
            return 1;
        }
        return 0;
    }

    /* renamed from: toString-impl$default, reason: not valid java name */
    public static /* synthetic */ String m1312toStringimpl$default(double d, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m1311toStringimpl(d, timeUnit, i);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static final String m1311toStringimpl(double $this, TimeUnit unit, int decimals) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (!(decimals >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + decimals).toString());
        }
        if (m1292isInfiniteimpl($this)) {
            return String.valueOf($this);
        }
        double number = m1304toDoubleimpl($this, unit);
        StringBuilder sb = new StringBuilder();
        sb.append(Math.abs(number) < 1.0E14d ? FormatToDecimalsKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12)) : FormatToDecimalsKt.formatScientific(number));
        sb.append(DurationUnitKt.shortName(unit));
        return sb.toString();
    }

    /* renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m1306toIsoStringimpl(double $this) {
        StringBuilder $this$buildString = new StringBuilder();
        if (m1293isNegativeimpl($this)) {
            $this$buildString.append('-');
        }
        $this$buildString.append("PT");
        double $this$iv = m1278getAbsoluteValueUwyO8pc($this);
        int hours = (int) m1281getInHoursimpl($this$iv);
        int minutes = m1287getMinutesComponentimpl($this$iv);
        int seconds = m1289getSecondsComponentimpl($this$iv);
        int nanoseconds = m1288getNanosecondsComponentimpl($this$iv);
        boolean hasMinutes = true;
        boolean hasHours = hours != 0;
        boolean hasSeconds = (seconds == 0 && nanoseconds == 0) ? false : true;
        if (minutes == 0 && (!hasSeconds || !hasHours)) {
            hasMinutes = false;
        }
        if (hasHours) {
            $this$buildString.append(hours);
            $this$buildString.append('H');
        }
        if (hasMinutes) {
            $this$buildString.append(minutes);
            $this$buildString.append('M');
        }
        if (hasSeconds || (!hasHours && !hasMinutes)) {
            $this$buildString.append(seconds);
            if (nanoseconds != 0) {
                $this$buildString.append('.');
                String nss = StringsKt.padStart(String.valueOf(nanoseconds), 9, '0');
                if (nanoseconds % 1000000 == 0) {
                    $this$buildString.append((CharSequence) nss, 0, 3);
                    Intrinsics.checkNotNullExpressionValue($this$buildString, "this.append(value, startIndex, endIndex)");
                } else if (nanoseconds % 1000 == 0) {
                    $this$buildString.append((CharSequence) nss, 0, 6);
                    Intrinsics.checkNotNullExpressionValue($this$buildString, "this.append(value, startIndex, endIndex)");
                } else {
                    $this$buildString.append(nss);
                }
            }
            $this$buildString.append('S');
        }
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
