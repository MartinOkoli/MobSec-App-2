package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeTaskQueue;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* compiled from: Tasks.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/scheduling/GlobalQueue;", "Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "Lkotlinx/coroutines/scheduling/Task;", "()V", "removeFirstWithModeOrNull", "mode", "Lkotlinx/coroutines/scheduling/TaskMode;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 15})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes3\kotlinx\coroutines\scheduling\GlobalQueue.smali */
public class GlobalQueue extends LockFreeTaskQueue<Task> {
    public GlobalQueue() {
        super(false);
    }

    public final Task removeFirstWithModeOrNull(TaskMode mode) {
        Object obj;
        int $i$f$removeFirstOrNullIf;
        LockFreeTaskQueue $this$loop$iv$iv;
        Object element$iv$iv;
        Object mode2 = mode;
        Intrinsics.checkParameterIsNotNull(mode2, "mode");
        int $i$f$removeFirstOrNullIf2 = 0;
        LockFreeTaskQueue $this$loop$iv$iv2 = this;
        while (true) {
            LockFreeTaskQueueCore cur$iv = (LockFreeTaskQueueCore) $this$loop$iv$iv2._cur$internal;
            while (true) {
                long state$iv$iv = cur$iv._state$internal;
                if ((LockFreeTaskQueueCore.FROZEN_MASK & state$iv$iv) == 0) {
                    LockFreeTaskQueueCore.Companion companion = LockFreeTaskQueueCore.INSTANCE;
                    int head$iv$iv$iv = (int) ((state$iv$iv & LockFreeTaskQueueCore.HEAD_MASK) >> 0);
                    int tail$iv$iv$iv = (int) ((state$iv$iv & LockFreeTaskQueueCore.TAIL_MASK) >> 30);
                    int i = tail$iv$iv$iv & cur$iv.mask;
                    $i$f$removeFirstOrNullIf = $i$f$removeFirstOrNullIf2;
                    int $i$f$removeFirstOrNullIf3 = head$iv$iv$iv & cur$iv.mask;
                    if (i != $i$f$removeFirstOrNullIf3) {
                        element$iv$iv = cur$iv.array$internal.get(cur$iv.mask & head$iv$iv$iv);
                        if (element$iv$iv == null) {
                            if (cur$iv.singleConsumer) {
                                $this$loop$iv$iv = $this$loop$iv$iv2;
                                obj = null;
                                break;
                            }
                            $this$loop$iv$iv = $this$loop$iv$iv2;
                            mode2 = mode;
                            $i$f$removeFirstOrNullIf2 = $i$f$removeFirstOrNullIf;
                            $this$loop$iv$iv2 = $this$loop$iv$iv;
                        } else if (!(element$iv$iv instanceof LockFreeTaskQueueCore.Placeholder)) {
                            Task it = (Task) element$iv$iv;
                            if (!(it.getMode() == mode2)) {
                                $this$loop$iv$iv = $this$loop$iv$iv2;
                                obj = null;
                                break;
                            }
                            int newHead$iv$iv = (head$iv$iv$iv + 1) & LockFreeTaskQueueCore.MAX_CAPACITY_MASK;
                            $this$loop$iv$iv = $this$loop$iv$iv2;
                            if (!LockFreeTaskQueueCore._state$FU$internal.compareAndSet(cur$iv, state$iv$iv, LockFreeTaskQueueCore.INSTANCE.updateHead(state$iv$iv, newHead$iv$iv))) {
                                if (!cur$iv.singleConsumer) {
                                    mode2 = mode;
                                    $i$f$removeFirstOrNullIf2 = $i$f$removeFirstOrNullIf;
                                    $this$loop$iv$iv2 = $this$loop$iv$iv;
                                } else {
                                    LockFreeTaskQueueCore cur$iv$iv = cur$iv;
                                    while (true) {
                                        LockFreeTaskQueueCore lockFreeTaskQueueCoreRemoveSlowPath = cur$iv$iv.removeSlowPath(head$iv$iv$iv, newHead$iv$iv);
                                        if (lockFreeTaskQueueCoreRemoveSlowPath == null) {
                                            break;
                                        }
                                        cur$iv$iv = lockFreeTaskQueueCoreRemoveSlowPath;
                                    }
                                }
                            } else {
                                cur$iv.array$internal.set(cur$iv.mask & head$iv$iv$iv, null);
                                break;
                            }
                        } else {
                            $this$loop$iv$iv = $this$loop$iv$iv2;
                            obj = null;
                            break;
                        }
                    } else {
                        $this$loop$iv$iv = $this$loop$iv$iv2;
                        obj = null;
                        break;
                    }
                } else {
                    obj = LockFreeTaskQueueCore.REMOVE_FROZEN;
                    $i$f$removeFirstOrNullIf = $i$f$removeFirstOrNullIf2;
                    $this$loop$iv$iv = $this$loop$iv$iv2;
                    break;
                }
            }
            obj = element$iv$iv;
            Object result$iv = obj;
            if (result$iv == LockFreeTaskQueueCore.REMOVE_FROZEN) {
                LockFreeTaskQueue._cur$FU$internal.compareAndSet(this, cur$iv, cur$iv.next());
                mode2 = mode;
                $i$f$removeFirstOrNullIf2 = $i$f$removeFirstOrNullIf;
                $this$loop$iv$iv2 = $this$loop$iv$iv;
            } else {
                return (Task) result$iv;
            }
        }
    }
}
