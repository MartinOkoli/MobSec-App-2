package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BigDecimals.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\nH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000eH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u000fH\u0087\b\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u0001*\u00020\u0001H\u0087\n¨\u0006\u0011"}, d2 = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 4, 1}, xi = 1, xs = "kotlin/NumbersKt")
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\kotlin\NumbersKt__BigDecimalsKt.smali */
class NumbersKt__BigDecimalsKt {
    private static final BigDecimal plus(BigDecimal plus, BigDecimal other) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        BigDecimal bigDecimalAdd = plus.add(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
        return bigDecimalAdd;
    }

    private static final BigDecimal minus(BigDecimal minus, BigDecimal other) {
        Intrinsics.checkNotNullParameter(minus, "$this$minus");
        BigDecimal bigDecimalSubtract = minus.subtract(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalSubtract, "this.subtract(other)");
        return bigDecimalSubtract;
    }

    private static final BigDecimal times(BigDecimal times, BigDecimal other) {
        Intrinsics.checkNotNullParameter(times, "$this$times");
        BigDecimal bigDecimalMultiply = times.multiply(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalMultiply, "this.multiply(other)");
        return bigDecimalMultiply;
    }

    private static final BigDecimal div(BigDecimal div, BigDecimal other) {
        Intrinsics.checkNotNullParameter(div, "$this$div");
        BigDecimal bigDecimalDivide = div.divide(other, RoundingMode.HALF_EVEN);
        Intrinsics.checkNotNullExpressionValue(bigDecimalDivide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimalDivide;
    }

    private static final BigDecimal rem(BigDecimal rem, BigDecimal other) {
        Intrinsics.checkNotNullParameter(rem, "$this$rem");
        BigDecimal bigDecimalRemainder = rem.remainder(other);
        Intrinsics.checkNotNullExpressionValue(bigDecimalRemainder, "this.remainder(other)");
        return bigDecimalRemainder;
    }

    private static final BigDecimal unaryMinus(BigDecimal unaryMinus) {
        Intrinsics.checkNotNullParameter(unaryMinus, "$this$unaryMinus");
        BigDecimal bigDecimalNegate = unaryMinus.negate();
        Intrinsics.checkNotNullExpressionValue(bigDecimalNegate, "this.negate()");
        return bigDecimalNegate;
    }

    private static final BigDecimal inc(BigDecimal inc) {
        Intrinsics.checkNotNullParameter(inc, "$this$inc");
        BigDecimal bigDecimalAdd = inc.add(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(BigDecimal.ONE)");
        return bigDecimalAdd;
    }

    private static final BigDecimal dec(BigDecimal dec) {
        Intrinsics.checkNotNullParameter(dec, "$this$dec");
        BigDecimal bigDecimalSubtract = dec.subtract(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimalSubtract, "this.subtract(BigDecimal.ONE)");
        return bigDecimalSubtract;
    }

    private static final BigDecimal toBigDecimal(int $this$toBigDecimal) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf($this$toBigDecimal);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        return bigDecimalValueOf;
    }

    private static final BigDecimal toBigDecimal(int $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    private static final BigDecimal toBigDecimal(long $this$toBigDecimal) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf($this$toBigDecimal);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this)");
        return bigDecimalValueOf;
    }

    private static final BigDecimal toBigDecimal(long $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal($this$toBigDecimal, mathContext);
    }

    private static final BigDecimal toBigDecimal(float $this$toBigDecimal) {
        return new BigDecimal(String.valueOf($this$toBigDecimal));
    }

    private static final BigDecimal toBigDecimal(float $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal(String.valueOf($this$toBigDecimal), mathContext);
    }

    private static final BigDecimal toBigDecimal(double $this$toBigDecimal) {
        return new BigDecimal(String.valueOf($this$toBigDecimal));
    }

    private static final BigDecimal toBigDecimal(double $this$toBigDecimal, MathContext mathContext) {
        return new BigDecimal(String.valueOf($this$toBigDecimal), mathContext);
    }
}
