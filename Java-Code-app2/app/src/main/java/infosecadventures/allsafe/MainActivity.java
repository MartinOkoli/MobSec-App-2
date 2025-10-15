package infosecadventures.allsafe;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import infosecadventures.allsafe.utils.SnackUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainActivity.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Linfosecadventures/allsafe/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mAppBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\MainActivity.smali */
public final class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(8192, 8192);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_main, R.id.nav_insecure_logging, R.id.nav_hardcoded_credentials, R.id.nav_firebase_database, R.id.nav_insecure_shared_preferences, R.id.nav_sql_injection, R.id.nav_deep_link_exploitation, R.id.nav_insecure_broadcast_receiver, R.id.nav_weak_cryptography, R.id.nav_insecure_service, R.id.nav_object_serialization, R.id.nav_insecure_providers, R.id.nav_certificate_pinning, R.id.nav_pin_bypass, R.id.nav_root_detection, R.id.nav_secure_flag_bypass, R.id.nav_vulnerable_web_view, R.id.nav_arbitrary_code_execution, R.id.nav_smali_patch, R.id.nav_native_library, R.id.nav_about).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        Intrinsics.checkNotNullExpressionValue(navController, "Navigation.findNavContro…, R.id.nav_host_fragment)");
        AppBarConfiguration appBarConfiguration = this.mAppBarConfiguration;
        Intrinsics.checkNotNull(appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        Intrinsics.checkNotNullExpressionValue(navController, "Navigation.findNavContro…, R.id.nav_host_fragment)");
        AppBarConfiguration appBarConfiguration = this.mAppBarConfiguration;
        Intrinsics.checkNotNull(appBarConfiguration);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Openable openableLayout;
        AppBarConfiguration appBarConfiguration = this.mAppBarConfiguration;
        Boolean boolValueOf = (appBarConfiguration == null || (openableLayout = appBarConfiguration.getOpenableLayout()) == null) ? null : Boolean.valueOf(openableLayout.isOpen());
        Intrinsics.checkNotNull(boolValueOf);
        if (boolValueOf.booleanValue()) {
            AppBarConfiguration appBarConfiguration2 = this.mAppBarConfiguration;
            Intrinsics.checkNotNull(appBarConfiguration2);
            Openable openableLayout2 = appBarConfiguration2.getOpenableLayout();
            if (openableLayout2 != null) {
                openableLayout2.close();
                return;
            }
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
        if (supportFragmentManager.getBackStackEntryCount() <= 1) {
            SnackUtil.INSTANCE.confirmExit(this);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
