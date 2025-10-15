package kotlinx.coroutines.internal;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

/* compiled from: ExceptionsConstuctor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002\u001a1\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006j\u0002`\u00072\u0014\b\u0004\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0006H\u0082\b\u001a!\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\b\b\u0000\u0010\u0010*\u00020\u00052\u0006\u0010\u0011\u001a\u0002H\u0010H\u0000¢\u0006\u0002\u0010\u0012\u001a\u001b\u0010\u0013\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00042\b\b\u0002\u0010\u0014\u001a\u00020\tH\u0082\u0010\u001a\u0018\u0010\u0015\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0016\u001a\u00020\tH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"4\u0010\u0002\u001a(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006j\u0002`\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000*(\b\u0002\u0010\u0017\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00062\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0006¨\u0006\u0018"}, d2 = {"cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", "throwableFields", "", "createConstructor", "constructor", "Ljava/lang/reflect/Constructor;", "safeCtor", "block", "tryCopyException", ExifInterface.LONGITUDE_EAST, "exception", "(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "fieldsCount", "accumulator", "fieldsCountOrDefault", "defaultValue", "Ctor", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\internal\ExceptionsConstuctorKt.smali */
public final class ExceptionsConstuctorKt {
    private static final int throwableFields = fieldsCountOrDefault(Throwable.class, -1);
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap<Class<? extends Throwable>, Function1<Throwable, Throwable>> exceptionCtors = new WeakHashMap<>();

    public static final <E extends Throwable> E tryCopyException(E exception) {
        Object objM14constructorimpl;
        ReentrantReadWriteLock.ReadLock lock;
        int readHoldCount;
        ReentrantReadWriteLock.WriteLock writeLock;
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        if (exception instanceof CopyableThrowable) {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM14constructorimpl = Result.m14constructorimpl(((CopyableThrowable) exception).createCopy());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
            }
            return (E) (Result.m20isFailureimpl(objM14constructorimpl) ? null : objM14constructorimpl);
        }
        ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
        ReentrantReadWriteLock.ReadLock lock2 = reentrantReadWriteLock.readLock();
        lock2.lock();
        try {
            Function1<Throwable, Throwable> cachedCtor = exceptionCtors.get(exception.getClass());
            if (cachedCtor != null) {
                return (E) cachedCtor.invoke(exception);
            }
            int i = 0;
            if (throwableFields != fieldsCountOrDefault(exception.getClass(), 0)) {
                lock = reentrantReadWriteLock.readLock();
                readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                for (int i2 = 0; i2 < readHoldCount; i2++) {
                    lock.unlock();
                }
                writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    exceptionCtors.put(exception.getClass(), new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$4$1
                        @Override // kotlin.jvm.functions.Function1
                        public final Void invoke(Throwable it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return null;
                        }
                    });
                    Unit unit = Unit.INSTANCE;
                    return null;
                } finally {
                    while (i < readHoldCount) {
                        lock.lock();
                        i++;
                    }
                    writeLock.unlock();
                }
            }
            Function1<Throwable, Throwable> function1CreateConstructor = (Function1) null;
            Constructor<?>[] $this$sortedByDescending$iv = exception.getClass().getConstructors();
            Intrinsics.checkExpressionValueIsNotNull($this$sortedByDescending$iv, "exception.javaClass.constructors");
            List<Constructor> constructors = ArraysKt.sortedWith($this$sortedByDescending$iv, new Comparator<T>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Constructor it = (Constructor) t2;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    Integer numValueOf = Integer.valueOf(it.getParameterTypes().length);
                    Constructor it2 = (Constructor) t;
                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(it2.getParameterTypes().length));
                }
            });
            for (Constructor constructor : constructors) {
                Intrinsics.checkExpressionValueIsNotNull(constructor, "constructor");
                function1CreateConstructor = createConstructor(constructor);
                if (function1CreateConstructor != null) {
                    break;
                }
            }
            ReentrantReadWriteLock reentrantReadWriteLock2 = cacheLock;
            lock = reentrantReadWriteLock2.readLock();
            readHoldCount = reentrantReadWriteLock2.getWriteHoldCount() == 0 ? reentrantReadWriteLock2.getReadHoldCount() : 0;
            for (int i3 = 0; i3 < readHoldCount; i3++) {
                lock.unlock();
            }
            writeLock = reentrantReadWriteLock2.writeLock();
            writeLock.lock();
            try {
                exceptionCtors.put(exception.getClass(), function1CreateConstructor != null ? function1CreateConstructor : new Function1() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$tryCopyException$5$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Void invoke(Throwable it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return null;
                    }
                });
                Unit unit2 = Unit.INSTANCE;
                while (i < readHoldCount) {
                    lock.lock();
                    i++;
                }
                writeLock.unlock();
                if (function1CreateConstructor != null) {
                    return (E) function1CreateConstructor.invoke(exception);
                }
                return null;
            } finally {
                while (i < readHoldCount) {
                    lock.lock();
                    i++;
                }
                writeLock.unlock();
            }
        } finally {
            lock2.unlock();
        }
    }

    private static final Function1<Throwable, Throwable> createConstructor(final Constructor<?> constructor) {
        Class[] p = constructor.getParameterTypes();
        int length = p.length;
        if (length == 0) {
            return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Throwable invoke(Throwable e) {
                    Object objM14constructorimpl;
                    Object objNewInstance;
                    Intrinsics.checkParameterIsNotNull(e, "e");
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        objNewInstance = constructor.newInstance(new Object[0]);
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
                    }
                    if (objNewInstance != null) {
                        Throwable it = (Throwable) objNewInstance;
                        it.initCause(e);
                        objM14constructorimpl = Result.m14constructorimpl(it);
                        if (Result.m20isFailureimpl(objM14constructorimpl)) {
                            objM14constructorimpl = null;
                        }
                        return (Throwable) objM14constructorimpl;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
                }
            };
        }
        if (length != 1) {
            if (length == 2 && Intrinsics.areEqual(p[0], String.class) && Intrinsics.areEqual(p[1], Throwable.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Throwable invoke(Throwable e) {
                        Object objM14constructorimpl;
                        Object objNewInstance;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objNewInstance = constructor.newInstance(e.getMessage(), e);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
                        }
                        if (objNewInstance != null) {
                            objM14constructorimpl = Result.m14constructorimpl((Throwable) objNewInstance);
                            if (Result.m20isFailureimpl(objM14constructorimpl)) {
                                objM14constructorimpl = null;
                            }
                            return (Throwable) objM14constructorimpl;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                };
            }
            return null;
        }
        Class cls = p[0];
        if (!Intrinsics.areEqual(cls, Throwable.class)) {
            if (Intrinsics.areEqual(cls, String.class)) {
                return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Throwable invoke(Throwable e) {
                        Object objM14constructorimpl;
                        Object objNewInstance;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objNewInstance = constructor.newInstance(e.getMessage());
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
                        }
                        if (objNewInstance != null) {
                            Throwable it = (Throwable) objNewInstance;
                            it.initCause(e);
                            objM14constructorimpl = Result.m14constructorimpl(it);
                            if (Result.m20isFailureimpl(objM14constructorimpl)) {
                                objM14constructorimpl = null;
                            }
                            return (Throwable) objM14constructorimpl;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
                    }
                };
            }
            return null;
        }
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt$createConstructor$$inlined$safeCtor$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable e) {
                Object objM14constructorimpl;
                Object objNewInstance;
                Intrinsics.checkParameterIsNotNull(e, "e");
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objNewInstance = constructor.newInstance(e);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
                }
                if (objNewInstance != null) {
                    objM14constructorimpl = Result.m14constructorimpl((Throwable) objNewInstance);
                    if (Result.m20isFailureimpl(objM14constructorimpl)) {
                        objM14constructorimpl = null;
                    }
                    return (Throwable) objM14constructorimpl;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
            }
        };
    }

    private static final Function1<Throwable, Throwable> safeCtor(final Function1<? super Throwable, ? extends Throwable> function1) {
        return new Function1<Throwable, Throwable>() { // from class: kotlinx.coroutines.internal.ExceptionsConstuctorKt.safeCtor.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Throwable invoke(Throwable e) {
                Object objM14constructorimpl;
                Intrinsics.checkParameterIsNotNull(e, "e");
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM14constructorimpl = Result.m14constructorimpl((Throwable) function1.invoke(e));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m20isFailureimpl(objM14constructorimpl)) {
                    objM14constructorimpl = null;
                }
                return (Throwable) objM14constructorimpl;
            }
        };
    }

    private static final int fieldsCountOrDefault(Class<?> cls, int defaultValue) {
        Object objM14constructorimpl;
        JvmClassMappingKt.getKotlinClass(cls);
        try {
            Result.Companion companion = Result.INSTANCE;
            objM14constructorimpl = Result.m14constructorimpl(Integer.valueOf(fieldsCount$default(cls, 0, 1, null)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM14constructorimpl = Result.m14constructorimpl(ResultKt.createFailure(th));
        }
        Integer numValueOf = Integer.valueOf(defaultValue);
        if (Result.m20isFailureimpl(objM14constructorimpl)) {
            objM14constructorimpl = numValueOf;
        }
        return ((Number) objM14constructorimpl).intValue();
    }

    static /* synthetic */ int fieldsCount$default(Class cls, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return fieldsCount(cls, i);
    }

    private static final int fieldsCount(Class<?> cls, int accumulator) {
        while (true) {
            Field[] declaredFields = cls.getDeclaredFields();
            Intrinsics.checkExpressionValueIsNotNull(declaredFields, "declaredFields");
            int count$iv = 0;
            for (Field it : declaredFields) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (!Modifier.isStatic(it.getModifiers())) {
                    count$iv++;
                }
            }
            int fieldsCount = count$iv;
            int totalFields = accumulator + fieldsCount;
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass == null) {
                return totalFields;
            }
            accumulator = totalFields;
            cls = superclass;
        }
    }
}
