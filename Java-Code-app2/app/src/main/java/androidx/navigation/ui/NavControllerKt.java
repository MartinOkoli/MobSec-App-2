package androidx.navigation.ui;

import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.AppBarConfigurationKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NavController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"navigateUp", "", "Landroidx/navigation/NavController;", "drawerLayout", "Landroidx/customview/widget/Openable;", "appBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "navigation-ui-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\ui\NavControllerKt.smali */
public final class NavControllerKt {
    public static final boolean navigateUp(NavController navigateUp, Openable drawerLayout) {
        Intrinsics.checkParameterIsNotNull(navigateUp, "$this$navigateUp");
        NavGraph graph = navigateUp.getGraph();
        Intrinsics.checkExpressionValueIsNotNull(graph, "graph");
        Function0 fallbackOnNavigateUpListener$iv = AppBarConfigurationKt.AnonymousClass1.INSTANCE;
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(graph).setOpenableLayout(drawerLayout).setFallbackOnNavigateUpListener((AppBarConfiguration.OnNavigateUpListener) (fallbackOnNavigateUpListener$iv != null ? new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(fallbackOnNavigateUpListener$iv) : fallbackOnNavigateUpListener$iv)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return NavigationUI.navigateUp(navigateUp, appBarConfigurationBuild);
    }

    public static final boolean navigateUp(NavController navigateUp, AppBarConfiguration appBarConfiguration) {
        Intrinsics.checkParameterIsNotNull(navigateUp, "$this$navigateUp");
        Intrinsics.checkParameterIsNotNull(appBarConfiguration, "appBarConfiguration");
        return NavigationUI.navigateUp(navigateUp, appBarConfiguration);
    }
}
