package androidx.navigation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: NavGraphViewModelLazy.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a;\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0010\b\n\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0087\b¨\u0006\n"}, d2 = {"navGraphViewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/fragment/app/Fragment;", "navGraphId", "", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "navigation-fragment-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavGraphViewModelLazyKt.smali */
public final class NavGraphViewModelLazyKt {
    public static /* synthetic */ Lazy navGraphViewModels$default(Fragment navGraphViewModels, int navGraphId, Function0 factoryProducer, int i, Object obj) {
        if ((i & 2) != 0) {
            factoryProducer = (Function0) null;
        }
        Intrinsics.checkParameterIsNotNull(navGraphViewModels, "$this$navGraphViewModels");
        Lazy backStackEntry = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$2(navGraphViewModels, navGraphId));
        Function0 storeProducer = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(backStackEntry, null);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(navGraphViewModels, Reflection.getOrCreateKotlinClass(ViewModel.class), storeProducer, new AnonymousClass1(factoryProducer, backStackEntry, null));
    }

    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> navGraphViewModels(Fragment navGraphViewModels, int navGraphId, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkParameterIsNotNull(navGraphViewModels, "$this$navGraphViewModels");
        Lazy backStackEntry = LazyKt.lazy(new NavGraphViewModelLazyKt$navGraphViewModels$backStackEntry$2(navGraphViewModels, navGraphId));
        Function0 storeProducer = new NavGraphViewModelLazyKt$navGraphViewModels$storeProducer$1(backStackEntry, null);
        Intrinsics.reifiedOperationMarker(4, "VM");
        return FragmentViewModelLazyKt.createViewModelLazy(navGraphViewModels, Reflection.getOrCreateKotlinClass(ViewModel.class), storeProducer, new AnonymousClass1(function0, backStackEntry, null));
    }

    /* compiled from: NavGraphViewModelLazy.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelProvider$Factory;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: androidx.navigation.NavGraphViewModelLazyKt$navGraphViewModels$1, reason: invalid class name */
    /* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavGraphViewModelLazyKt$navGraphViewModels$1.smali */
    public static final class AnonymousClass1 extends Lambda implements Function0<ViewModelProvider.Factory> {
        final /* synthetic */ Lazy $backStackEntry;
        final /* synthetic */ KProperty $backStackEntry$metadata;
        final /* synthetic */ Function0 $factoryProducer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Function0 function0, Lazy lazy, KProperty kProperty) {
            super(0);
            this.$factoryProducer = function0;
            this.$backStackEntry = lazy;
            this.$backStackEntry$metadata = kProperty;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory factory;
            Function0 function0 = this.$factoryProducer;
            if (function0 != null && (factory = (ViewModelProvider.Factory) function0.invoke()) != null) {
                return factory;
            }
            NavBackStackEntry backStackEntry = (NavBackStackEntry) this.$backStackEntry.getValue();
            Intrinsics.checkExpressionValueIsNotNull(backStackEntry, "backStackEntry");
            ViewModelProvider.Factory defaultViewModelProviderFactory = backStackEntry.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "backStackEntry.defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    }
}
