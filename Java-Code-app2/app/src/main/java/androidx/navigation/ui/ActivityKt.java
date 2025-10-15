package androidx.navigation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.AppBarConfigurationKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Activity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"setupActionBarWithNavController", "", "Landroidx/appcompat/app/AppCompatActivity;", "navController", "Landroidx/navigation/NavController;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "configuration", "Landroidx/navigation/ui/AppBarConfiguration;", "navigation-ui-ktx_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\navigation\ui\ActivityKt.smali */
public final class ActivityKt {
    public static final void setupActionBarWithNavController(AppCompatActivity setupActionBarWithNavController, NavController navController, DrawerLayout drawerLayout) {
        Intrinsics.checkParameterIsNotNull(setupActionBarWithNavController, "$this$setupActionBarWithNavController");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        NavGraph navGraph$iv = navController.getGraph();
        Intrinsics.checkExpressionValueIsNotNull(navGraph$iv, "navController.graph");
        Function0 fallbackOnNavigateUpListener$iv = AppBarConfigurationKt.AnonymousClass1.INSTANCE;
        AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(navGraph$iv).setOpenableLayout(drawerLayout).setFallbackOnNavigateUpListener((AppBarConfiguration.OnNavigateUpListener) (fallbackOnNavigateUpListener$iv != null ? new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(fallbackOnNavigateUpListener$iv) : fallbackOnNavigateUpListener$iv)).build();
        Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        NavigationUI.setupActionBarWithNavController(setupActionBarWithNavController, navController, appBarConfigurationBuild);
    }

    public static /* synthetic */ void setupActionBarWithNavController$default(AppCompatActivity appCompatActivity, NavController navController, AppBarConfiguration appBarConfiguration, int i, Object obj) {
        if ((i & 2) != 0) {
            NavGraph navGraph$iv = navController.getGraph();
            Intrinsics.checkExpressionValueIsNotNull(navGraph$iv, "navController.graph");
            Openable drawerLayout$iv = (Openable) null;
            Function0 fallbackOnNavigateUpListener$iv = AppBarConfigurationKt.AnonymousClass1.INSTANCE;
            AppBarConfiguration appBarConfigurationBuild = new AppBarConfiguration.Builder(navGraph$iv).setOpenableLayout(drawerLayout$iv).setFallbackOnNavigateUpListener((AppBarConfiguration.OnNavigateUpListener) (fallbackOnNavigateUpListener$iv != null ? new AppBarConfigurationKt$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(fallbackOnNavigateUpListener$iv) : fallbackOnNavigateUpListener$iv)).build();
            Intrinsics.checkExpressionValueIsNotNull(appBarConfigurationBuild, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
            appBarConfiguration = appBarConfigurationBuild;
        }
        setupActionBarWithNavController(appCompatActivity, navController, appBarConfiguration);
    }

    public static final void setupActionBarWithNavController(AppCompatActivity setupActionBarWithNavController, NavController navController, AppBarConfiguration configuration) {
        Intrinsics.checkParameterIsNotNull(setupActionBarWithNavController, "$this$setupActionBarWithNavController");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        NavigationUI.setupActionBarWithNavController(setupActionBarWithNavController, navController, configuration);
    }
}
