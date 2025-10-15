package androidx.navigation;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NavGraphBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0086\b\u001a:\u0010\u0000\u001a\u00020\t*\u00020\n2\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0086\b¨\u0006\u000b"}, d2 = {NotificationCompat.CATEGORY_NAVIGATION, "", "Landroidx/navigation/NavGraphBuilder;", "id", "", "startDestination", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Landroidx/navigation/NavGraph;", "Landroidx/navigation/NavigatorProvider;", "navigation-common-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\NavGraphBuilderKt.smali */
public final class NavGraphBuilderKt {
    public static /* synthetic */ NavGraph navigation$default(NavigatorProvider navigation, int id, int startDestination, Function1 builder, int i, Object obj) {
        if ((i & 1) != 0) {
            id = 0;
        }
        Intrinsics.checkParameterIsNotNull(navigation, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder(navigation, id, startDestination);
        builder.invoke(navGraphBuilder);
        return navGraphBuilder.build();
    }

    public static final NavGraph navigation(NavigatorProvider navigation, int id, int startDestination, Function1<? super NavGraphBuilder, Unit> builder) {
        Intrinsics.checkParameterIsNotNull(navigation, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder(navigation, id, startDestination);
        builder.invoke(navGraphBuilder);
        return navGraphBuilder.build();
    }

    public static final void navigation(NavGraphBuilder navigation, int id, int startDestination, Function1<? super NavGraphBuilder, Unit> builder) {
        Intrinsics.checkParameterIsNotNull(navigation, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder(navigation.getProvider(), id, startDestination);
        builder.invoke(navGraphBuilder);
        navigation.destination(navGraphBuilder);
    }
}
