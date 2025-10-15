package kotlinx.coroutines.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.Comparable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;

/* compiled from: ThreadSafeHeap.common.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\b\u0017\u0018\u0000*\u0012\b\u0000\u0010\u0003*\u00020\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0004j\u0002`\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\u000bJ.\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00028\u00002\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\u000e0\rH\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\t¢\u0006\u0004\b\u0012\u0010\u0007J\u0011\u0010\u0013\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0015\u0010\u0014J\u0017\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0016H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0015\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ&\u0010 \u001a\u0004\u0018\u00018\u00002\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000e0\rH\u0086\b¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\"\u0010\u0014J\u0018\u0010$\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u001bH\u0082\u0010¢\u0006\u0004\b$\u0010%J\u0018\u0010&\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u001bH\u0082\u0010¢\u0006\u0004\b&\u0010%J\u001f\u0010(\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u001bH\u0002¢\u0006\u0004\b(\u0010)R \u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0013\u0010,\u001a\u00020\u000e8F@\u0006¢\u0006\u0006\u001a\u0004\b,\u0010-R$\u00102\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u001b8F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b/\u00100\"\u0004\b1\u0010%¨\u00063"}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", ExifInterface.GPS_DIRECTION_TRUE, "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "<init>", "()V", "node", "", "addImpl", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "addLast", "Lkotlin/Function1;", "", "cond", "addLastIf", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function1;)Z", "clear", "firstImpl", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "peek", "", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "", "index", "removeAtImpl", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "predicate", "removeFirstIf", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "i", "siftDownFrom", "(I)V", "siftUpFrom", "j", "swap", "(II)V", "a", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "isEmpty", "()Z", "value", "getSize", "()I", "setSize", "size", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\internal\ThreadSafeHeap.smali */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {
    private static final AtomicIntegerFieldUpdater _size$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadSafeHeap.class, "_size");
    private volatile int _size = 0;
    private T[] a;

    /* renamed from: getSize, reason: from getter */
    public final int get_size() {
        return this._size;
    }

    private final void setSize(int value) {
        this._size = value;
    }

    public final boolean isEmpty() {
        return get_size() == 0;
    }

    public final void clear() {
        synchronized (this) {
            ThreadSafeHeapNode[] it = this.a;
            if (it != null) {
                ArraysKt.fill$default(it, (Object) null, 0, 0, 6, (Object) null);
            }
            this._size = 0;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final T peek() {
        T t;
        synchronized (this) {
            t = (T) firstImpl();
        }
        return t;
    }

    public final T removeFirstOrNull() {
        T t;
        synchronized (this) {
            if (get_size() > 0) {
                t = (T) removeAtImpl(0);
            } else {
                t = null;
            }
        }
        return t;
    }

    public final T removeFirstIf(Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        synchronized (this) {
            try {
                ThreadSafeHeapNode threadSafeHeapNodeFirstImpl = firstImpl();
                T t = null;
                if (threadSafeHeapNodeFirstImpl == null) {
                    InlineMarker.finallyStart(2);
                    InlineMarker.finallyEnd(2);
                    return null;
                }
                if (predicate.invoke(threadSafeHeapNodeFirstImpl).booleanValue()) {
                    t = (T) removeAtImpl(0);
                }
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                return t;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
    }

    public final void addLast(T node) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        synchronized (this) {
            addImpl(node);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean addLastIf(T node, Function1<? super T, Boolean> cond) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(node, "node");
        Intrinsics.checkParameterIsNotNull(cond, "cond");
        synchronized (this) {
            try {
                if (cond.invoke(firstImpl()).booleanValue()) {
                    addImpl(node);
                    z = true;
                } else {
                    z = false;
                }
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return z;
    }

    public final boolean remove(T node) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(node, "node");
        synchronized (this) {
            z = true;
            if (node.getHeap() == null) {
                z = false;
            } else {
                int index = node.getIndex();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(index >= 0)) {
                        throw new AssertionError();
                    }
                }
                removeAtImpl(index);
            }
        }
        return z;
    }

    public final T firstImpl() {
        T[] tArr = this.a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T removeAtImpl(int r9) {
        /*
            r8 = this;
            boolean r0 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L1d
            r0 = 0
            int r3 = r8.get_size()
            if (r3 <= 0) goto L11
            r0 = r2
            goto L12
        L11:
            r0 = r1
        L12:
            if (r0 == 0) goto L15
            goto L1d
        L15:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L1d:
            T extends kotlinx.coroutines.internal.ThreadSafeHeapNode & java.lang.Comparable<? super T>[] r0 = r8.a
            if (r0 != 0) goto L24
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L24:
            int r3 = r8.get_size()
            r4 = -1
            int r3 = r3 + r4
            r8.setSize(r3)
            int r3 = r8.get_size()
            if (r9 >= r3) goto L60
            int r3 = r8.get_size()
            r8.swap(r9, r3)
            int r3 = r9 + (-1)
            int r3 = r3 / 2
            if (r9 <= 0) goto L5d
            r5 = r0[r9]
            if (r5 != 0) goto L47
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L47:
            java.lang.Comparable r5 = (java.lang.Comparable) r5
            r6 = r0[r3]
            if (r6 != 0) goto L50
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L50:
            int r5 = r5.compareTo(r6)
            if (r5 >= 0) goto L5d
            r8.swap(r9, r3)
            r8.siftUpFrom(r3)
            goto L60
        L5d:
            r8.siftDownFrom(r9)
        L60:
            int r3 = r8.get_size()
            r3 = r0[r3]
            if (r3 != 0) goto L6c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L6c:
            boolean r5 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()
            if (r5 == 0) goto L88
            r5 = 0
            kotlinx.coroutines.internal.ThreadSafeHeap r6 = r3.getHeap()
            r7 = r8
            kotlinx.coroutines.internal.ThreadSafeHeap r7 = (kotlinx.coroutines.internal.ThreadSafeHeap) r7
            if (r6 != r7) goto L7d
            r1 = r2
        L7d:
            if (r1 == 0) goto L80
            goto L88
        L80:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L88:
            r1 = 0
            r2 = r1
            kotlinx.coroutines.internal.ThreadSafeHeap r2 = (kotlinx.coroutines.internal.ThreadSafeHeap) r2
            r3.setHeap(r2)
            r3.setIndex(r4)
            int r2 = r8.get_size()
            kotlinx.coroutines.internal.ThreadSafeHeapNode r1 = (kotlinx.coroutines.internal.ThreadSafeHeapNode) r1
            r0[r2] = r1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ThreadSafeHeap.removeAtImpl(int):kotlinx.coroutines.internal.ThreadSafeHeapNode");
    }

    public final void addImpl(T node) {
        Intrinsics.checkParameterIsNotNull(node, "node");
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(node.getHeap() == null)) {
                throw new AssertionError();
            }
        }
        node.setHeap(this);
        ThreadSafeHeapNode[] a = realloc();
        int i = get_size();
        setSize(i + 1);
        a[i] = node;
        node.setIndex(i);
        siftUpFrom(i);
    }

    private final void siftUpFrom(int i) {
        while (i > 0) {
            T[] tArr = this.a;
            if (tArr == null) {
                Intrinsics.throwNpe();
            }
            int j = (i - 1) / 2;
            T t = tArr[j];
            if (t == null) {
                Intrinsics.throwNpe();
            }
            Comparable comparable = (Comparable) t;
            T t2 = tArr[i];
            if (t2 == null) {
                Intrinsics.throwNpe();
            }
            if (comparable.compareTo(t2) <= 0) {
                return;
            }
            swap(i, j);
            i = j;
        }
    }

    private final void siftDownFrom(int i) {
        while (true) {
            int j = (i * 2) + 1;
            if (j >= get_size()) {
                return;
            }
            T[] tArr = this.a;
            if (tArr == null) {
                Intrinsics.throwNpe();
            }
            if (j + 1 < get_size()) {
                T t = tArr[j + 1];
                if (t == null) {
                    Intrinsics.throwNpe();
                }
                Comparable comparable = (Comparable) t;
                T t2 = tArr[j];
                if (t2 == null) {
                    Intrinsics.throwNpe();
                }
                if (comparable.compareTo(t2) < 0) {
                    j++;
                }
            }
            T t3 = tArr[i];
            if (t3 == null) {
                Intrinsics.throwNpe();
            }
            Comparable comparable2 = (Comparable) t3;
            T t4 = tArr[j];
            if (t4 == null) {
                Intrinsics.throwNpe();
            }
            if (comparable2.compareTo(t4) <= 0) {
                return;
            }
            swap(i, j);
            i = j;
        }
    }

    private final T[] realloc() {
        T[] tArr = this.a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new ThreadSafeHeapNode[4];
            this.a = tArr2;
            return tArr2;
        }
        if (get_size() < tArr.length) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, get_size() * 2);
        Intrinsics.checkExpressionValueIsNotNull(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        this.a = (T[]) ((ThreadSafeHeapNode[]) objArrCopyOf);
        return (T[]) ((ThreadSafeHeapNode[]) objArrCopyOf);
    }

    private final void swap(int i, int j) {
        ThreadSafeHeapNode[] a = this.a;
        if (a == null) {
            Intrinsics.throwNpe();
        }
        ThreadSafeHeapNode ni = a[j];
        if (ni == null) {
            Intrinsics.throwNpe();
        }
        ThreadSafeHeapNode nj = a[i];
        if (nj == null) {
            Intrinsics.throwNpe();
        }
        a[i] = ni;
        a[j] = nj;
        ni.setIndex(i);
        nj.setIndex(j);
    }
}
