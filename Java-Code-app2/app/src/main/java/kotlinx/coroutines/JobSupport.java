package kotlinx.coroutines;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;

/* compiled from: JobSupport.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "This is internal API and may be removed in the future releases")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0006Ð\u0001Ñ\u0001Ò\u0001B\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ+\u0010\u000f\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J%\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J!\u0010\u001b\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\u0015\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u0002¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010#\u001a\u0004\u0018\u00010\tH\u0080@ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J\u0015\u0010$\u001a\u0004\u0018\u00010\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b$\u0010\"J\u0019\u0010&\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0004\b&\u0010'J\u001f\u0010&\u001a\u00020\u00152\u000e\u0010%\u001a\n\u0018\u00010(j\u0004\u0018\u0001`)H\u0016¢\u0006\u0004\b&\u0010*J\u0017\u0010+\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b+\u0010'J\u0019\u0010.\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\b,\u0010-J\u0019\u0010/\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0004\b/\u0010'J\u0019\u00100\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b0\u0010-J\u0017\u00101\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0011H\u0002¢\u0006\u0004\b1\u0010'J\u0017\u00102\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u0011H\u0016¢\u0006\u0004\b2\u0010'J)\u00105\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b5\u00106J)\u0010;\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u0002072\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b;\u0010<J\u0019\u0010=\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b=\u0010>J\u000f\u0010@\u001a\u00020?H\u0002¢\u0006\u0004\b@\u0010AJ\u0019\u0010B\u001a\u0004\u0018\u0001082\u0006\u0010\u0018\u001a\u000203H\u0002¢\u0006\u0004\bB\u0010CJ\u0011\u0010D\u001a\u00060(j\u0002`)¢\u0006\u0004\bD\u0010EJ\u0013\u0010F\u001a\u00060(j\u0002`)H\u0016¢\u0006\u0004\bF\u0010EJ\u0011\u0010I\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\bG\u0010HJ\u000f\u0010J\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\bJ\u0010KJ'\u0010L\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0018\u001a\u0002072\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0004\bL\u0010MJ\u0019\u0010N\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u000203H\u0002¢\u0006\u0004\bN\u0010OJ\u0017\u0010Q\u001a\u00020\u00052\u0006\u0010P\u001a\u00020\u0011H\u0014¢\u0006\u0004\bQ\u0010'J\u0017\u0010T\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u0011H\u0010¢\u0006\u0004\bR\u0010SJ\u0019\u0010X\u001a\u00020\u00152\b\u0010U\u001a\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0004\bV\u0010WJF\u0010a\u001a\u00020`2\u0006\u0010Y\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\u00052'\u0010_\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00150[j\u0002`^¢\u0006\u0004\ba\u0010bJ6\u0010a\u001a\u00020`2'\u0010_\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00150[j\u0002`^¢\u0006\u0004\ba\u0010cJ\u0013\u0010d\u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0004\bd\u0010\"J\u000f\u0010e\u001a\u00020\u0005H\u0002¢\u0006\u0004\be\u0010fJ\u0013\u0010g\u001a\u00020\u0015H\u0082@ø\u0001\u0000¢\u0006\u0004\bg\u0010\"J&\u0010j\u001a\u00020i2\u0014\u0010h\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00150[H\u0082\b¢\u0006\u0004\bj\u0010kJ\u0019\u0010l\u001a\u00020\u00052\b\u0010%\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\bl\u0010-J\u0019\u0010n\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\bm\u0010-J!\u0010q\u001a\u00020\u00052\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\bo\u0010pJD\u0010r\u001a\u0006\u0012\u0002\b\u00030\r2'\u0010_\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020\u00150[j\u0002`^2\u0006\u0010Y\u001a\u00020\u0005H\u0002¢\u0006\u0004\br\u0010sJ\u000f\u0010w\u001a\u00020tH\u0010¢\u0006\u0004\bu\u0010vJ\u001f\u0010x\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u0011H\u0002¢\u0006\u0004\bx\u0010yJ2\u0010{\u001a\u00020\u0015\"\u000e\b\u0000\u0010z\u0018\u0001*\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0082\b¢\u0006\u0004\b{\u0010yJ\u0019\u0010Y\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0014¢\u0006\u0004\bY\u0010SJ\u0019\u0010|\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0014¢\u0006\u0004\b|\u0010}J\u0010\u0010\u0080\u0001\u001a\u00020\u0015H\u0010¢\u0006\u0004\b~\u0010\u007fJ\u0019\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010\u0081\u0001\u001a\u00020\u0003¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J\u001b\u0010\u0085\u0001\u001a\u00020\u00152\u0007\u0010\u0018\u001a\u00030\u0084\u0001H\u0002¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J\u001e\u0010\u0087\u0001\u001a\u00020\u00152\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001JI\u0010\u008d\u0001\u001a\u00020\u0015\"\u0005\b\u0000\u0010\u0089\u00012\u000e\u0010\u008b\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u008a\u00012\u001d\u0010h\u001a\u0019\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00000\u008c\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t0[ø\u0001\u0000¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001JX\u0010\u0092\u0001\u001a\u00020\u0015\"\u0004\b\u0000\u0010z\"\u0005\b\u0001\u0010\u0089\u00012\u000e\u0010\u008b\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u008a\u00012$\u0010h\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008c\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t0\u008f\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J\u001e\u0010\u0094\u0001\u001a\u00020\u00152\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0000¢\u0006\u0006\b\u0093\u0001\u0010\u0088\u0001JX\u0010\u0096\u0001\u001a\u00020\u0015\"\u0004\b\u0000\u0010z\"\u0005\b\u0001\u0010\u0089\u00012\u000e\u0010\u008b\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u008a\u00012$\u0010h\u001a \b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008c\u0001\u0012\u0006\u0012\u0004\u0018\u00010\t0\u008f\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0095\u0001\u0010\u0091\u0001J\u000f\u0010\u0097\u0001\u001a\u00020\u0005¢\u0006\u0005\b\u0097\u0001\u0010fJ\u001c\u0010\u0098\u0001\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u001c\u0010\u009a\u0001\u001a\u00020t2\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0011\u0010\u009c\u0001\u001a\u00020tH\u0007¢\u0006\u0005\b\u009c\u0001\u0010vJ\u0011\u0010\u009d\u0001\u001a\u00020tH\u0016¢\u0006\u0005\b\u009d\u0001\u0010vJ,\u0010\u009e\u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J,\u0010 \u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001J\"\u0010¢\u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002032\u0006\u0010\u0012\u001a\u00020\u0011H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J.\u0010¤\u0001\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\t2\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J,\u0010¦\u0001\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u0002032\b\u0010:\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0006\b¦\u0001\u0010§\u0001J-\u0010¨\u0001\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u0002072\u0006\u0010\u001d\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\tH\u0082\u0010¢\u0006\u0006\b¨\u0001\u0010©\u0001J\u0019\u0010«\u0001\u001a\u0004\u0018\u000108*\u00030ª\u0001H\u0002¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u001f\u0010\u00ad\u0001\u001a\u00020\u0015*\u00020\u000b2\b\u0010%\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0005\b\u00ad\u0001\u0010yJ'\u0010¯\u0001\u001a\u00060(j\u0002`)*\u00020\u00112\u000b\b\u0002\u0010®\u0001\u001a\u0004\u0018\u00010tH\u0004¢\u0006\u0006\b¯\u0001\u0010°\u0001R\u001d\u0010´\u0001\u001a\t\u0012\u0004\u0012\u00020\u00010±\u00018F@\u0006¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001R\u001a\u0010¶\u0001\u001a\u0004\u0018\u00010\u00118D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\bµ\u0001\u0010KR\u0018\u0010¸\u0001\u001a\u00020\u00058D@\u0004X\u0084\u0004¢\u0006\u0007\u001a\u0005\b·\u0001\u0010fR\u0018\u0010º\u0001\u001a\u00020\u00058P@\u0010X\u0090\u0004¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010fR\u0018\u0010»\u0001\u001a\u00020\u00058V@\u0016X\u0096\u0004¢\u0006\u0007\u001a\u0005\b»\u0001\u0010fR\u0015\u0010¼\u0001\u001a\u00020\u00058F@\u0006¢\u0006\u0007\u001a\u0005\b¼\u0001\u0010fR\u0015\u0010½\u0001\u001a\u00020\u00058F@\u0006¢\u0006\u0007\u001a\u0005\b½\u0001\u0010fR\u0015\u0010¾\u0001\u001a\u00020\u00058F@\u0006¢\u0006\u0007\u001a\u0005\b¾\u0001\u0010fR\u0018\u0010¿\u0001\u001a\u00020\u00058T@\u0014X\u0094\u0004¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010fR\u001b\u0010Ã\u0001\u001a\u0007\u0012\u0002\b\u00030À\u00018F@\u0006¢\u0006\b\u001a\u0006\bÁ\u0001\u0010Â\u0001R\u0018\u0010Å\u0001\u001a\u00020\u00058P@\u0010X\u0090\u0004¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010fR\u0016\u0010È\u0001\u001a\u00020\u00048F@\u0006¢\u0006\b\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u001b\u0010É\u0001\u001a\u0004\u0018\u00010\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010Ê\u0001R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\t8@@\u0000X\u0080\u0004¢\u0006\u0007\u001a\u0005\bË\u0001\u0010HR \u0010Í\u0001\u001a\u0004\u0018\u00010\u0011*\u0004\u0018\u00010\t8B@\u0002X\u0082\u0004¢\u0006\u0007\u001a\u0005\bÌ\u0001\u0010>R\u001d\u0010Î\u0001\u001a\u00020\u0005*\u0002038B@\u0002X\u0082\u0004¢\u0006\b\u001a\u0006\bÎ\u0001\u0010Ï\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Ó\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/ChildJob;", "Lkotlinx/coroutines/ParentJob;", "Lkotlinx/coroutines/selects/SelectClause0;", "", "active", "<init>", "(Z)V", "", "expect", "Lkotlinx/coroutines/NodeList;", "list", "Lkotlinx/coroutines/JobNode;", "node", "addLastAtomic", "(Ljava/lang/Object;Lkotlinx/coroutines/NodeList;Lkotlinx/coroutines/JobNode;)Z", "", "rootCause", "", "exceptions", "", "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "state", "", "mode", "afterCompletionInternal", "(Ljava/lang/Object;I)V", "child", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", "awaitInternal$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitInternal", "awaitSuspend", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "cancelInternal", "cancelMakeCompleting", "cancelParent", "childCancelled", "Lkotlinx/coroutines/Incomplete;", "update", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;I)V", "Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/ChildHandleNode;", "lastChild", "proposedUpdate", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "Lkotlinx/coroutines/JobCancellationException;", "createJobCancellationException", "()Lkotlinx/coroutines/JobCancellationException;", "firstChild", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/ChildHandleNode;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "getChildJobCancellationCause", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", "exception", "handleJobException", "handleOnCompletionException$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "handleOnCompletionException", "parent", "initParentJobInternal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/Job;)V", "initParentJobInternal", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "join", "joinInternal", "()Z", "joinSuspend", "block", "", "loopOnState", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "makeCancelling", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "(Ljava/lang/Object;I)Z", "makeCompletingOnce", "makeNode", "(Lkotlin/jvm/functions/Function1;Z)Lkotlinx/coroutines/JobNode;", "", "nameString$kotlinx_coroutines_core", "()Ljava/lang/String;", "nameString", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", ExifInterface.GPS_DIRECTION_TRUE, "notifyHandlers", "onCompletionInternal", "(Ljava/lang/Object;)V", "onStartInternal$kotlinx_coroutines_core", "()V", "onStartInternal", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/coroutines/Continuation;", "registerSelectClause0", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectClause1Internal", "removeNode$kotlinx_coroutines_core", "removeNode", "selectAwaitCompletion$kotlinx_coroutines_core", "selectAwaitCompletion", "start", "startInternal", "(Ljava/lang/Object;)I", "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "toDebugString", "toString", "tryFinalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;I)Z", "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;I)Z", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;I)I", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;I)I", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", "notifyCompletion", "message", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "getCompletionCause", "completionCause", "getCompletionCauseHandled", "completionCauseHandled", "getHandlesException$kotlinx_coroutines_core", "handlesException", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "key", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onJoin", "parentHandle", "Lkotlinx/coroutines/ChildHandle;", "getState$kotlinx_coroutines_core", "getExceptionOrNull", "exceptionOrNull", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\JobSupport.smali */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
    private volatile Object _state;
    public volatile ChildHandle parentHandle;

    public JobSupport(boolean active) {
        this._state = active ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        return (R) Job.DefaultImpls.fold(this, r, operation);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return (E) Job.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return Job.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return Job.DefaultImpls.plus(this, context);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
    public Job plus(Job other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return Job.DefaultImpls.plus((Job) this, other);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.INSTANCE;
    }

    public final void initParentJobInternal$kotlinx_coroutines_core(Job parent) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.parentHandle == null)) {
                throw new AssertionError();
            }
        }
        if (parent == null) {
            this.parentHandle = NonDisposableHandle.INSTANCE;
            return;
        }
        parent.start();
        ChildHandle handle = parent.attachChild(this);
        this.parentHandle = handle;
        if (isCompleted()) {
            handle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object state = this._state;
            if (!(state instanceof OpDescriptor)) {
                return state;
            }
            ((OpDescriptor) state).perform(this);
        }
    }

    private final Void loopOnState(Function1<Object, Unit> block) {
        while (true) {
            block.invoke(getState$kotlinx_coroutines_core());
        }
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state = getState$kotlinx_coroutines_core();
        return (state instanceof Incomplete) && ((Incomplete) state).getIsActive();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object state = getState$kotlinx_coroutines_core();
        return (state instanceof CompletedExceptionally) || ((state instanceof Finishing) && ((Finishing) state).isCancelling());
    }

    private final boolean tryFinalizeFinishingState(Finishing state, Object proposedUpdate, int mode) throws Throwable {
        boolean wasCancelling;
        Throwable finalCause;
        Object completedExceptionally;
        if (!(getState$kotlinx_coroutines_core() == state)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!state.isSealed())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!state.completing) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        CompletedExceptionally completedExceptionally2 = (CompletedExceptionally) (!(proposedUpdate instanceof CompletedExceptionally) ? null : proposedUpdate);
        Throwable proposedException = completedExceptionally2 != null ? completedExceptionally2.cause : null;
        synchronized (state) {
            wasCancelling = state.isCancelling();
            List exceptions = state.sealLocked(proposedException);
            finalCause = getFinalRootCause(state, exceptions);
            if (finalCause != null) {
                addSuppressedExceptions(finalCause, exceptions);
            }
        }
        if (finalCause != null && finalCause != proposedException) {
            completedExceptionally = new CompletedExceptionally(finalCause, false, 2, null);
        } else {
            completedExceptionally = proposedUpdate;
        }
        Object finalState = completedExceptionally;
        if (finalCause != null) {
            boolean handled = cancelParent(finalCause) || handleJobException(finalCause);
            if (handled) {
                if (finalState == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                }
                ((CompletedExceptionally) finalState).makeHandled();
            }
        }
        if (!wasCancelling) {
            onCancelling(finalCause);
        }
        onCompletionInternal(finalState);
        if (_state$FU.compareAndSet(this, state, JobSupportKt.boxIncomplete(finalState))) {
            completeStateFinalization(state, finalState, mode);
            return true;
        }
        throw new IllegalArgumentException(("Unexpected state: " + this._state + ", expected: " + state + ", update: " + finalState).toString());
    }

    private final Throwable getFinalRootCause(Finishing state, List<? extends Throwable> exceptions) {
        Object obj = null;
        if (exceptions.isEmpty()) {
            if (state.isCancelling()) {
                return createJobCancellationException();
            }
            return null;
        }
        List<? extends Throwable> $this$firstOrNull$iv = exceptions;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object element$iv = it.next();
            Throwable it2 = (Throwable) element$iv;
            if (!(it2 instanceof CancellationException)) {
                obj = element$iv;
                break;
            }
        }
        Throwable th = (Throwable) obj;
        return th != null ? th : exceptions.get(0);
    }

    private final void addSuppressedExceptions(Throwable rootCause, List<? extends Throwable> exceptions) {
        if (exceptions.size() <= 1) {
            return;
        }
        Set seenExceptions = ConcurrentKt.identitySet(exceptions.size());
        Throwable unwrappedCause = StackTraceRecoveryKt.unwrap(rootCause);
        for (Throwable exception : exceptions) {
            Throwable unwrapped = StackTraceRecoveryKt.unwrap(exception);
            if (unwrapped != rootCause && unwrapped != unwrappedCause && !(unwrapped instanceof CancellationException) && seenExceptions.add(unwrapped)) {
                kotlin.ExceptionsKt.addSuppressed(rootCause, unwrapped);
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete state, Object update, int mode) throws Throwable {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if ((((state instanceof Empty) || (state instanceof JobNode)) ? 1 : 0) == 0) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(update instanceof CompletedExceptionally))) {
            throw new AssertionError();
        }
        if (!_state$FU.compareAndSet(this, state, JobSupportKt.boxIncomplete(update))) {
            return false;
        }
        onCancelling(null);
        onCompletionInternal(update);
        completeStateFinalization(state, update, mode);
        return true;
    }

    private final void completeStateFinalization(Incomplete state, Object update, int mode) throws Throwable {
        ChildHandle it = this.parentHandle;
        if (it != null) {
            it.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!(update instanceof CompletedExceptionally) ? null : update);
        Throwable cause = completedExceptionally != null ? completedExceptionally.cause : null;
        if (state instanceof JobNode) {
            try {
                ((JobNode) state).invoke(cause);
            } catch (Throwable ex) {
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler " + state + " for " + this, ex));
            }
        } else {
            NodeList list = state.getList();
            if (list != null) {
                notifyCompletion(list, cause);
            }
        }
        afterCompletionInternal(update, mode);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void notifyCancelling(kotlinx.coroutines.NodeList r17, java.lang.Throwable r18) throws java.lang.Throwable {
        /*
            r16 = this;
            r1 = r16
            r2 = r18
            r1.onCancelling(r2)
            r3 = r16
            r4 = 0
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r5 = r17
            r6 = 0
            java.lang.Object r7 = r5.getNext()
            if (r7 == 0) goto L7f
            kotlinx.coroutines.internal.LockFreeLinkedListNode r7 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r7
            r8 = r7
            r7 = r0
        L1a:
            r0 = r5
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r0
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L72
            boolean r0 = r8 instanceof kotlinx.coroutines.JobCancellingNode
            if (r0 == 0) goto L6d
            r9 = r8
            kotlinx.coroutines.JobNode r9 = (kotlinx.coroutines.JobNode) r9
            r10 = 0
            r9.invoke(r2)     // Catch: java.lang.Throwable -> L32
            goto L6b
        L32:
            r0 = move-exception
            r11 = r0
            r0 = r11
            if (r7 == 0) goto L43
            r11 = r7
            r12 = 0
            r13 = r0
            r14 = r11
            r15 = 0
            kotlin.ExceptionsKt.addSuppressed(r14, r13)
            if (r7 == 0) goto L43
            goto L6a
        L43:
            r11 = r3
            kotlinx.coroutines.JobSupport r11 = (kotlinx.coroutines.JobSupport) r11
            r12 = 0
            kotlinx.coroutines.CompletionHandlerException r13 = new kotlinx.coroutines.CompletionHandlerException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Exception in completion handler "
            r14.append(r15)
            r14.append(r9)
            java.lang.String r15 = " for "
            r14.append(r15)
            r14.append(r11)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14, r0)
            r7 = r13
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
        L6a:
        L6b:
        L6d:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r8 = r8.getNextNode()
            goto L1a
        L72:
            if (r7 == 0) goto L7a
            r0 = r7
            r5 = 0
            r3.handleOnCompletionException$kotlinx_coroutines_core(r0)
        L7a:
            r1.cancelParent(r2)
            return
        L7f:
            kotlin.TypeCastException r7 = new kotlin.TypeCastException
        */
        //  java.lang.String r8 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.notifyCancelling(kotlinx.coroutines.NodeList, java.lang.Throwable):void");
    }

    private final boolean cancelParent(Throwable cause) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean isCancellation = cause instanceof CancellationException;
        ChildHandle parent = this.parentHandle;
        if (parent == null || parent == NonDisposableHandle.INSTANCE) {
            return isCancellation;
        }
        return parent.childCancelled(cause) || isCancellation;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void notifyCompletion(kotlinx.coroutines.NodeList r16, java.lang.Throwable r17) throws java.lang.Throwable {
        /*
            r15 = this;
            r1 = r15
            r2 = 0
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r3 = r16
            r4 = 0
            java.lang.Object r5 = r3.getNext()
            if (r5 == 0) goto L7a
            kotlinx.coroutines.internal.LockFreeLinkedListNode r5 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r5
            r6 = r5
            r5 = r0
        L12:
            r0 = r3
            kotlinx.coroutines.internal.LockFreeLinkedListHead r0 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r0
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L6f
            boolean r0 = r6 instanceof kotlinx.coroutines.JobNode
            if (r0 == 0) goto L68
            r7 = r6
            kotlinx.coroutines.JobNode r7 = (kotlinx.coroutines.JobNode) r7
            r8 = 0
            r9 = r17
            r7.invoke(r9)     // Catch: java.lang.Throwable -> L2c
            goto L65
        L2c:
            r0 = move-exception
            r10 = r0
            r0 = r10
            if (r5 == 0) goto L3d
            r10 = r5
            r11 = 0
            r12 = r0
            r13 = r10
            r14 = 0
            kotlin.ExceptionsKt.addSuppressed(r13, r12)
            if (r5 == 0) goto L3d
            goto L64
        L3d:
            r10 = r1
            kotlinx.coroutines.JobSupport r10 = (kotlinx.coroutines.JobSupport) r10
            r11 = 0
            kotlinx.coroutines.CompletionHandlerException r12 = new kotlinx.coroutines.CompletionHandlerException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "Exception in completion handler "
            r13.append(r14)
            r13.append(r7)
            java.lang.String r14 = " for "
            r13.append(r14)
            r13.append(r10)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13, r0)
            r5 = r12
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
        L64:
        L65:
            goto L6a
        L68:
            r9 = r17
        L6a:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r6 = r6.getNextNode()
            goto L12
        L6f:
            r9 = r17
            if (r5 == 0) goto L78
            r0 = r5
            r3 = 0
            r1.handleOnCompletionException$kotlinx_coroutines_core(r0)
        L78:
            return
        L7a:
            r9 = r17
            kotlin.TypeCastException r5 = new kotlin.TypeCastException
        */
        //  java.lang.String r6 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.notifyCompletion(kotlinx.coroutines.NodeList, java.lang.Throwable):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final /* synthetic */ <T extends kotlinx.coroutines.JobNode<?>> void notifyHandlers(kotlinx.coroutines.NodeList r14, java.lang.Throwable r15) throws java.lang.Throwable {
        /*
            r13 = this;
            r0 = 0
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r14
            r3 = 0
            java.lang.Object r4 = r2.getNext()
            if (r4 == 0) goto L73
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r4
        Le:
            r5 = r2
            kotlinx.coroutines.internal.LockFreeLinkedListHead r5 = (kotlinx.coroutines.internal.LockFreeLinkedListHead) r5
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)
            r5 = r5 ^ 1
            if (r5 == 0) goto L6a
            r5 = 3
            java.lang.String r6 = "T"
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r5, r6)
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.LockFreeLinkedListNode
            if (r5 == 0) goto L65
            r5 = r4
            kotlinx.coroutines.JobNode r5 = (kotlinx.coroutines.JobNode) r5
            r6 = 0
            r5.invoke(r15)     // Catch: java.lang.Throwable -> L2c
            goto L63
        L2c:
            r7 = move-exception
            if (r1 == 0) goto L3b
            r8 = r1
            r9 = 0
            r10 = r7
            r11 = r8
            r12 = 0
            kotlin.ExceptionsKt.addSuppressed(r11, r10)
            if (r1 == 0) goto L3b
            goto L62
        L3b:
            r8 = r13
            kotlinx.coroutines.JobSupport r8 = (kotlinx.coroutines.JobSupport) r8
            r9 = 0
            kotlinx.coroutines.CompletionHandlerException r10 = new kotlinx.coroutines.CompletionHandlerException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Exception in completion handler "
            r11.append(r12)
            r11.append(r5)
            java.lang.String r12 = " for "
            r11.append(r12)
            r11.append(r8)
            java.lang.String r11 = r11.toString()
            r10.<init>(r11, r7)
            r1 = r10
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L62:
        L63:
        L65:
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = r4.getNextNode()
            goto Le
        L6a:
            if (r1 == 0) goto L72
            r2 = r1
            r3 = 0
            r13.handleOnCompletionException$kotlinx_coroutines_core(r2)
        L72:
            return
        L73:
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
        */
        //  java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */"
        /*
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.notifyHandlers(kotlinx.coroutines.NodeList, java.lang.Throwable):void");
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int iStartInternal;
        do {
            Object state = getState$kotlinx_coroutines_core();
            iStartInternal = startInternal(state);
            if (iStartInternal == 0) {
                return false;
            }
        } while (iStartInternal != 1);
        return true;
    }

    private final int startInternal(Object state) {
        if (state instanceof Empty) {
            if (((Empty) state).getIsActive()) {
                return 0;
            }
            if (!_state$FU.compareAndSet(this, state, JobSupportKt.EMPTY_ACTIVE)) {
                return -1;
            }
            onStartInternal$kotlinx_coroutines_core();
            return 1;
        }
        if (!(state instanceof InactiveNodeList)) {
            return 0;
        }
        if (!_state$FU.compareAndSet(this, state, ((InactiveNodeList) state).getList())) {
            return -1;
        }
        onStartInternal$kotlinx_coroutines_core();
        return 1;
    }

    public void onStartInternal$kotlinx_coroutines_core() {
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof Finishing) {
            Throwable th = ((Finishing) state).rootCause;
            if (th != null) {
                CancellationException cancellationException = toCancellationException(th, DebugStringsKt.getClassSimpleName(this) + " is cancelling");
                if (cancellationException != null) {
                    return cancellationException;
                }
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (state instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (state instanceof CompletedExceptionally) {
            return toCancellationException$default(this, ((CompletedExceptionally) state).cause, null, 1, null);
        }
        return new JobCancellationException(DebugStringsKt.getClassSimpleName(this) + " has completed normally", null, this);
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i & 1) != 0) {
            str = (String) null;
        }
        return jobSupport.toCancellationException(th, str);
    }

    protected final CancellationException toCancellationException(Throwable toCancellationException, String message) {
        String str;
        Intrinsics.checkParameterIsNotNull(toCancellationException, "$this$toCancellationException");
        CancellationException cancellationException = (CancellationException) (!(toCancellationException instanceof CancellationException) ? null : toCancellationException);
        if (cancellationException != null) {
            return cancellationException;
        }
        if (message != null) {
            str = message;
        } else {
            str = DebugStringsKt.getClassSimpleName(toCancellationException) + " was cancelled";
        }
        return new JobCancellationException(str, toCancellationException, this);
    }

    protected final Throwable getCompletionCause() {
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof Finishing) {
            Throwable th = ((Finishing) state).rootCause;
            if (th == null) {
                throw new IllegalStateException(("Job is still new or active: " + this).toString());
            }
            return th;
        }
        if (state instanceof Incomplete) {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
        if (state instanceof CompletedExceptionally) {
            return ((CompletedExceptionally) state).cause;
        }
        return null;
    }

    protected final boolean getCompletionCauseHandled() {
        Object it = getState$kotlinx_coroutines_core();
        return (it instanceof CompletedExceptionally) && ((CompletedExceptionally) it).getHandled();
    }

    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return invokeOnCompletion(false, true, handler);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a6, code lost:
    
        r4 = r14;
     */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00c1 A[SYNTHETIC] */
    @Override // kotlinx.coroutines.Job
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.DisposableHandle invokeOnCompletion(boolean r18, boolean r19, kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.invokeOnCompletion(boolean, boolean, kotlin.jvm.functions.Function1):kotlinx.coroutines.DisposableHandle");
    }

    private final JobNode<?> makeNode(Function1<? super Throwable, Unit> handler, boolean onCancelling) {
        if (onCancelling) {
            JobCancellingNode it = (JobCancellingNode) (handler instanceof JobCancellingNode ? handler : null);
            if (it != null) {
                if (!(it.job == this)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (it != null) {
                    return it;
                }
            }
            return new InvokeOnCancelling(this, handler);
        }
        JobNode it2 = (JobNode) (handler instanceof JobNode ? handler : null);
        if (it2 != null) {
            if (!(it2.job == this && !(it2 instanceof JobCancellingNode))) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            if (it2 != null) {
                return it2;
            }
        }
        return new InvokeOnCompletion(this, handler);
    }

    private final boolean addLastAtomic(final Object expect, NodeList list, JobNode<?> node) {
        int iTryCondAddNext;
        final JobNode<?> jobNode = node;
        final JobNode<?> jobNode2 = node;
        LockFreeLinkedListNode.CondAddOp condAdd$iv = new LockFreeLinkedListNode.CondAddOp(jobNode2) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode affected) {
                Intrinsics.checkParameterIsNotNull(affected, "affected");
                if (this.getState$kotlinx_coroutines_core() == expect) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        do {
            Object prev = list.getPrev();
            if (prev != null) {
                LockFreeLinkedListNode prev$iv = (LockFreeLinkedListNode) prev;
                iTryCondAddNext = prev$iv.tryCondAddNext(node, list, condAdd$iv);
                if (iTryCondAddNext == 1) {
                    return true;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
            }
        } while (iTryCondAddNext != 2);
        return false;
    }

    private final void promoteEmptyToNodeList(Empty state) {
        NodeList list = new NodeList();
        Incomplete update = state.getIsActive() ? list : new InactiveNodeList(list);
        _state$FU.compareAndSet(this, state, update);
    }

    private final void promoteSingleToNodeList(JobNode<?> state) {
        state.addOneIfEmpty(new NodeList());
        LockFreeLinkedListNode list = state.getNextNode();
        _state$FU.compareAndSet(this, state, list);
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation<? super Unit> continuation) {
        if (!joinInternal()) {
            YieldKt.checkCompletion(continuation.getContext());
            return Unit.INSTANCE;
        }
        return joinSuspend(continuation);
    }

    private final boolean joinInternal() {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (!(state instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state) < 0);
        return true;
    }

    final /* synthetic */ Object joinSuspend(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        CancellableContinuationImpl cont = cancellable$iv;
        CompletionHandlerBase $this$asHandler$iv = new ResumeOnCompletion(this, cont);
        CancellableContinuationKt.disposeOnCancellation(cont, invokeOnCompletion($this$asHandler$iv));
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // kotlinx.coroutines.Job
    public final SelectClause0 getOnJoin() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause0
    public final <R> void registerSelectClause0(SelectInstance<? super R> select, Function1<? super Continuation<? super R>, ? extends Object> block) {
        Object state;
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(block, "block");
        do {
            state = getState$kotlinx_coroutines_core();
            if (select.isSelected()) {
                return;
            }
            if (!(state instanceof Incomplete)) {
                if (select.trySelect(null)) {
                    UndispatchedKt.startCoroutineUnintercepted(block, select.getCompletion());
                    return;
                }
                return;
            }
        } while (startInternal(state) != 0);
        CompletionHandlerBase $this$asHandler$iv = new SelectJoinOnCompletion(this, select, block);
        select.disposeOnSelect(invokeOnCompletion($this$asHandler$iv));
    }

    public final void removeNode$kotlinx_coroutines_core(JobNode<?> node) {
        Object state;
        Intrinsics.checkParameterIsNotNull(node, "node");
        do {
            state = getState$kotlinx_coroutines_core();
            if (state instanceof JobNode) {
                if (state != node) {
                    return;
                }
            } else {
                if (!(state instanceof Incomplete) || ((Incomplete) state).getList() == null) {
                    return;
                }
                node.remove();
                return;
            }
        } while (!_state$FU.compareAndSet(this, state, JobSupportKt.EMPTY_ACTIVE));
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cause) {
        cancel(cause);
    }

    @Override // kotlinx.coroutines.Job
    /* renamed from: cancelInternal, reason: merged with bridge method [inline-methods] */
    public boolean cancel(Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause) && getHandlesException();
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob parentJob) {
        Intrinsics.checkParameterIsNotNull(parentJob, "parentJob");
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    public boolean childCancelled(Throwable cause) {
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        if (cause instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(cause) && getHandlesException();
    }

    public final boolean cancelCoroutine(Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(Object cause) {
        if (getOnCancelComplete$kotlinx_coroutines_core() && cancelMakeCompleting(cause)) {
            return true;
        }
        return makeCancelling(cause);
    }

    private final boolean cancelMakeCompleting(Object cause) {
        int iTryMakeCompleting;
        do {
            Object state = getState$kotlinx_coroutines_core();
            if (!(state instanceof Incomplete) || ((state instanceof Finishing) && ((Finishing) state).completing)) {
                return false;
            }
            CompletedExceptionally proposedUpdate = new CompletedExceptionally(createCauseException(cause), false, 2, null);
            iTryMakeCompleting = tryMakeCompleting(state, proposedUpdate, 0);
            if (iTryMakeCompleting == 0) {
                return false;
            }
            if (iTryMakeCompleting == 1 || iTryMakeCompleting == 2) {
                return true;
            }
        } while (iTryMakeCompleting == 3);
        throw new IllegalStateException("unexpected result".toString());
    }

    private final JobCancellationException createJobCancellationException() {
        return new JobCancellationException("Job was cancelled", null, this);
    }

    @Override // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        Throwable rootCause;
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof Finishing) {
            rootCause = ((Finishing) state).rootCause;
        } else if (state instanceof CompletedExceptionally) {
            rootCause = ((CompletedExceptionally) state).cause;
        } else {
            if (state instanceof Incomplete) {
                throw new IllegalStateException(("Cannot be cancelling child in this state: " + state).toString());
            }
            rootCause = null;
        }
        CancellationException cancellationException = (CancellationException) (rootCause instanceof CancellationException ? rootCause : null);
        if (cancellationException != null) {
            return cancellationException;
        }
        return new JobCancellationException("Parent job is " + stateString(state), rootCause, this);
    }

    private final Throwable createCauseException(Object cause) {
        if (cause != null ? cause instanceof Throwable : true) {
            return cause != null ? (Throwable) cause : createJobCancellationException();
        }
        if (cause != null) {
            return ((ParentJob) cause).getChildJobCancellationCause();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b4, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean makeCancelling(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.makeCancelling(java.lang.Object):boolean");
    }

    private final NodeList getOrPromoteCancellingList(Incomplete state) {
        NodeList list = state.getList();
        if (list == null) {
            if (state instanceof Empty) {
                return new NodeList();
            }
            if (state instanceof JobNode) {
                promoteSingleToNodeList((JobNode) state);
                return null;
            }
            throw new IllegalStateException(("State should have list: " + state).toString());
        }
        return list;
    }

    private final boolean tryMakeCancelling(Incomplete state, Throwable rootCause) throws Throwable {
        if (DebugKt.getASSERTIONS_ENABLED() && !(!(state instanceof Finishing))) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !state.getIsActive()) {
            throw new AssertionError();
        }
        NodeList list = getOrPromoteCancellingList(state);
        if (list == null) {
            return false;
        }
        Finishing cancelling = new Finishing(list, false, rootCause);
        if (!_state$FU.compareAndSet(this, state, cancelling)) {
            return false;
        }
        notifyCancelling(list, rootCause);
        return true;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object proposedUpdate) {
        int iTryMakeCompleting;
        do {
            Object state = getState$kotlinx_coroutines_core();
            iTryMakeCompleting = tryMakeCompleting(state, proposedUpdate, 0);
            if (iTryMakeCompleting == 0) {
                return false;
            }
            if (iTryMakeCompleting == 1 || iTryMakeCompleting == 2) {
                return true;
            }
        } while (iTryMakeCompleting == 3);
        throw new IllegalStateException("unexpected result".toString());
    }

    public final boolean makeCompletingOnce$kotlinx_coroutines_core(Object proposedUpdate, int mode) {
        int iTryMakeCompleting;
        do {
            Object state = getState$kotlinx_coroutines_core();
            iTryMakeCompleting = tryMakeCompleting(state, proposedUpdate, mode);
            if (iTryMakeCompleting == 0) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + proposedUpdate, getExceptionOrNull(proposedUpdate));
            }
            if (iTryMakeCompleting == 1) {
                return true;
            }
            if (iTryMakeCompleting == 2) {
                return false;
            }
        } while (iTryMakeCompleting == 3);
        throw new IllegalStateException("unexpected result".toString());
    }

    private final int tryMakeCompleting(Object state, Object proposedUpdate, int mode) {
        if (!(state instanceof Incomplete)) {
            return 0;
        }
        if ((!(state instanceof Empty) && !(state instanceof JobNode)) || (state instanceof ChildHandleNode) || (proposedUpdate instanceof CompletedExceptionally)) {
            return tryMakeCompletingSlowPath((Incomplete) state, proposedUpdate, mode);
        }
        return !tryFinalizeSimpleState((Incomplete) state, proposedUpdate, mode) ? 3 : 1;
    }

    private final int tryMakeCompletingSlowPath(Incomplete state, Object proposedUpdate, int mode) throws Throwable {
        NodeList list = getOrPromoteCancellingList(state);
        if (list == null) {
            return 3;
        }
        Finishing finishing = (Finishing) (!(state instanceof Finishing) ? null : state);
        if (finishing == null) {
            finishing = new Finishing(list, false, null);
        }
        Finishing finishing2 = finishing;
        synchronized (finishing2) {
            try {
                if (finishing2.completing) {
                    return 0;
                }
                finishing2.completing = true;
                if (finishing2 != state && !_state$FU.compareAndSet(this, state, finishing2)) {
                    return 3;
                }
                if (!finishing2.isSealed()) {
                    boolean wasCancelling = finishing2.isCancelling();
                    CompletedExceptionally it = (CompletedExceptionally) (!(proposedUpdate instanceof CompletedExceptionally) ? null : proposedUpdate);
                    if (it != null) {
                        finishing2.addExceptionLocked(it.cause);
                    }
                    Throwable th = wasCancelling ? false : true ? finishing2.rootCause : null;
                    Unit unit = Unit.INSTANCE;
                    if (th != null) {
                        notifyCancelling(list, th);
                    }
                    ChildHandleNode child = firstChild(state);
                    if (child == null || !tryWaitForChild(finishing2, child, proposedUpdate)) {
                        return tryFinalizeFinishingState(finishing2, proposedUpdate, mode) ? 1 : 3;
                    }
                    return 2;
                }
                try {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    private final Throwable getExceptionOrNull(Object $this$exceptionOrNull) {
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) (!($this$exceptionOrNull instanceof CompletedExceptionally) ? null : $this$exceptionOrNull);
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    private final ChildHandleNode firstChild(Incomplete state) {
        ChildHandleNode childHandleNode = (ChildHandleNode) (!(state instanceof ChildHandleNode) ? null : state);
        if (childHandleNode != null) {
            return childHandleNode;
        }
        NodeList list = state.getList();
        if (list != null) {
            return nextChild(list);
        }
        return null;
    }

    private final boolean tryWaitForChild(Finishing state, ChildHandleNode child, Object proposedUpdate) {
        while (true) {
            ChildJob childJob = child.childJob;
            CompletionHandlerBase $this$asHandler$iv = new ChildCompletion(this, state, child, proposedUpdate);
            DisposableHandle handle = Job.DefaultImpls.invokeOnCompletion$default(childJob, false, false, $this$asHandler$iv, 1, null);
            if (handle != NonDisposableHandle.INSTANCE) {
                return true;
            }
            ChildHandleNode nextChild = nextChild(child);
            if (nextChild == null) {
                return false;
            }
            child = nextChild;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void continueCompleting(Finishing state, ChildHandleNode lastChild, Object proposedUpdate) {
        if (!(getState$kotlinx_coroutines_core() == state)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ChildHandleNode waitChild = nextChild(lastChild);
        if ((waitChild == null || !tryWaitForChild(state, waitChild, proposedUpdate)) && tryFinalizeFinishingState(state, proposedUpdate, 0)) {
        }
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode $this$nextChild) {
        LockFreeLinkedListNode cur = $this$nextChild;
        while (cur.isRemoved()) {
            cur = cur.getPrevNode();
        }
        while (true) {
            cur = cur.getNextNode();
            if (!cur.isRemoved()) {
                if (cur instanceof ChildHandleNode) {
                    return (ChildHandleNode) cur;
                }
                if (cur instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    public final Sequence<Job> getChildren() {
        return SequencesKt.sequence(new JobSupport$children$1(this, null));
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        CompletionHandlerBase $this$asHandler$iv = new ChildHandleNode(this, child);
        DisposableHandle disposableHandleInvokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(this, true, false, $this$asHandler$iv, 2, null);
        if (disposableHandleInvokeOnCompletion$default != null) {
            return (ChildHandle) disposableHandleInvokeOnCompletion$default;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.ChildHandle");
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable exception) throws Throwable {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        throw exception;
    }

    protected void onCancelling(Throwable cause) {
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    /* renamed from: getHandlesException$kotlinx_coroutines_core */
    public boolean getHandlesException() {
        return true;
    }

    protected boolean handleJobException(Throwable exception) {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        return false;
    }

    protected void onCompletionInternal(Object state) {
    }

    protected void afterCompletionInternal(Object state, int mode) {
    }

    public String toString() {
        return toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
    }

    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    private final String stateString(Object state) {
        return state instanceof Finishing ? ((Finishing) state).isCancelling() ? "Cancelling" : ((Finishing) state).completing ? "Completing" : "Active" : state instanceof Incomplete ? ((Incomplete) state).getIsActive() ? "Active" : "New" : state instanceof CompletedExceptionally ? "Cancelled" : "Completed";
    }

    /* compiled from: JobSupport.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u0018\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0016j\b\u0012\u0004\u0012\u00020\t`\u0017H\u0002J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\tJ\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;", "list", "Lkotlinx/coroutines/NodeList;", "isCompleting", "", "rootCause", "", "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "_exceptionsHolder", "isActive", "()Z", "isCancelling", "isSealed", "getList", "()Lkotlinx/coroutines/NodeList;", "addExceptionLocked", "", "exception", "allocateList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "sealLocked", "", "proposedException", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\JobSupport$Finishing.smali */
    private static final class Finishing implements Incomplete {

        /* renamed from: _exceptionsHolder, reason: from kotlin metadata and from toString */
        private volatile Object exceptions;

        /* renamed from: isCompleting, reason: from kotlin metadata and from toString */
        public volatile boolean completing;
        private final NodeList list;
        public volatile Throwable rootCause;

        public Finishing(NodeList list, boolean isCompleting, Throwable rootCause) {
            Intrinsics.checkParameterIsNotNull(list, "list");
            this.list = list;
            this.completing = isCompleting;
            this.rootCause = rootCause;
        }

        @Override // kotlinx.coroutines.Incomplete
        public NodeList getList() {
            return this.list;
        }

        public final boolean isSealed() {
            return this.exceptions == JobSupportKt.SEALED;
        }

        public final boolean isCancelling() {
            return this.rootCause != null;
        }

        @Override // kotlinx.coroutines.Incomplete
        /* renamed from: isActive */
        public boolean getIsActive() {
            return this.rootCause == null;
        }

        public final List<Throwable> sealLocked(Throwable proposedException) {
            ArrayList it;
            Object eh = this.exceptions;
            if (eh == null) {
                it = allocateList();
            } else if (eh instanceof Throwable) {
                it = allocateList();
                it.add(eh);
            } else {
                if (!(eh instanceof ArrayList)) {
                    throw new IllegalStateException(("State is " + eh).toString());
                }
                it = (ArrayList) eh;
            }
            ArrayList list = it;
            Throwable rootCause = this.rootCause;
            if (rootCause != null) {
                list.add(0, rootCause);
            }
            if (proposedException != null && (!Intrinsics.areEqual(proposedException, rootCause))) {
                list.add(proposedException);
            }
            this.exceptions = JobSupportKt.SEALED;
            return list;
        }

        public final void addExceptionLocked(Throwable exception) {
            Intrinsics.checkParameterIsNotNull(exception, "exception");
            Throwable rootCause = this.rootCause;
            if (rootCause == null) {
                this.rootCause = exception;
                return;
            }
            if (exception == rootCause) {
                return;
            }
            Object eh = this.exceptions;
            if (eh == null) {
                this.exceptions = exception;
                return;
            }
            if (eh instanceof Throwable) {
                if (exception == eh) {
                    return;
                }
                ArrayList $this$apply = allocateList();
                $this$apply.add(eh);
                $this$apply.add(exception);
                this.exceptions = $this$apply;
                return;
            }
            if (!(eh instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + eh).toString());
            }
            ((ArrayList) eh).add(exception);
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        public String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + this.completing + ", rootCause=" + this.rootCause + ", exceptions=" + this.exceptions + ", list=" + getList() + ']';
        }
    }

    private final boolean isCancelling(Incomplete $this$isCancelling) {
        return ($this$isCancelling instanceof Finishing) && ((Finishing) $this$isCancelling).isCancelling();
    }

    /* compiled from: JobSupport.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/Job;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\JobSupport$ChildCompletion.smali */
    private static final class ChildCompletion extends JobNode<Job> {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChildCompletion(JobSupport parent, Finishing state, ChildHandleNode child, Object proposedUpdate) {
            super(child.childJob);
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Intrinsics.checkParameterIsNotNull(child, "child");
            this.parent = parent;
            this.state = state;
            this.child = child;
            this.proposedUpdate = proposedUpdate;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2(Throwable cause) {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            return "ChildCompletion[" + this.child + ", " + this.proposedUpdate + ']';
        }
    }

    /* compiled from: JobSupport.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\JobSupport$AwaitContinuation.smali */
    private static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        private final JobSupport job;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AwaitContinuation(Continuation<? super T> delegate, JobSupport job) {
            super(delegate, 1);
            Intrinsics.checkParameterIsNotNull(delegate, "delegate");
            Intrinsics.checkParameterIsNotNull(job, "job");
            this.job = job;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public Throwable getContinuationCancellationCause(Job parent) {
            Throwable it;
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Object state = this.job.getState$kotlinx_coroutines_core();
            return (!(state instanceof Finishing) || (it = ((Finishing) state).rootCause) == null) ? state instanceof CompletedExceptionally ? ((CompletedExceptionally) state).cause : parent.getCancellationException() : it;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        protected String nameString() {
            return "AwaitContinuation";
        }
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object state = getState$kotlinx_coroutines_core();
        if (!(state instanceof Incomplete)) {
            return getExceptionOrNull(state);
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    public final Object getCompletedInternal$kotlinx_coroutines_core() throws Throwable {
        Object state = getState$kotlinx_coroutines_core();
        if (!(!(state instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        if (state instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) state).cause;
        }
        return JobSupportKt.unboxState(state);
    }

    public final Object awaitInternal$kotlinx_coroutines_core(Continuation<Object> continuation) throws Throwable {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (!(state instanceof Incomplete)) {
                if (state instanceof CompletedExceptionally) {
                    Throwable exception$iv = ((CompletedExceptionally) state).cause;
                    if (!DebugKt.getRECOVER_STACK_TRACES()) {
                        throw exception$iv;
                    }
                    InlineMarker.mark(0);
                    if (continuation instanceof CoroutineStackFrame) {
                        throw StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) continuation);
                    }
                    throw exception$iv;
                }
                return JobSupportKt.unboxState(state);
            }
        } while (startInternal(state) < 0);
        return awaitSuspend(continuation);
    }

    final /* synthetic */ Object awaitSuspend(Continuation<Object> continuation) {
        AwaitContinuation cont = new AwaitContinuation(IntrinsicsKt.intercepted(continuation), this);
        CompletionHandlerBase $this$asHandler$iv = new ResumeAwaitOnCompletion(this, cont);
        CancellableContinuationKt.disposeOnCancellation(cont, invokeOnCompletion($this$asHandler$iv));
        Object result = cont.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(SelectInstance<? super R> select, Function2<? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Object state;
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(block, "block");
        do {
            state = getState$kotlinx_coroutines_core();
            if (select.isSelected()) {
                return;
            }
            if (!(state instanceof Incomplete)) {
                if (select.trySelect(null)) {
                    if (state instanceof CompletedExceptionally) {
                        select.resumeSelectCancellableWithException(((CompletedExceptionally) state).cause);
                        return;
                    } else {
                        UndispatchedKt.startCoroutineUnintercepted(block, JobSupportKt.unboxState(state), select.getCompletion());
                        return;
                    }
                }
                return;
            }
        } while (startInternal(state) != 0);
        CompletionHandlerBase $this$asHandler$iv = new SelectAwaitOnCompletion(this, select, block);
        select.disposeOnSelect(invokeOnCompletion($this$asHandler$iv));
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(SelectInstance<? super R> select, Function2<? super T, ? super Continuation<? super R>, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(select, "select");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof CompletedExceptionally) {
            select.resumeSelectCancellableWithException(((CompletedExceptionally) state).cause);
        } else {
            CancellableKt.startCoroutineCancellable(block, JobSupportKt.unboxState(state), select.getCompletion());
        }
    }
}
