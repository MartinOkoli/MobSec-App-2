package infosecadventures.allsafe;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDelegate;
import dalvik.system.DexClassLoader;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ArbitraryCodeExecution.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\u0007"}, d2 = {"Linfosecadventures/allsafe/ArbitraryCodeExecution;", "Landroid/app/Application;", "()V", "invokePlugins", "", "invokeUpdate", "onCreate", "app_debug"}, k = 1, mv = {1, 4, 2})
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali_classes2\infosecadventures\allsafe\ArbitraryCodeExecution.smali */
public final class ArbitraryCodeExecution extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(2);
        invokePlugins();
        invokeUpdate();
    }

    private final void invokePlugins() {
        for (PackageInfo packageInfo : getPackageManager().getInstalledPackages(0)) {
            String packageName = packageInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(packageName, "packageInfo.packageName");
            if (StringsKt.startsWith$default(packageName, BuildConfig.APPLICATION_ID, false, 2, (Object) null)) {
                try {
                    Context packageContext = createPackageContext(packageName, 3);
                    Intrinsics.checkNotNullExpressionValue(packageContext, "packageContext");
                    packageContext.getClassLoader().loadClass("infosecadventures.allsafe.plugin.Loader").getMethod("loadPlugin", new Class[0]).invoke(null, new Object[0]);
                } catch (Exception e) {
                }
            }
        }
    }

    private final void invokeUpdate() {
        try {
            File file = new File(Environment.DIRECTORY_DOWNLOADS + "/allsafe_updater.apk");
            if (file.exists() && file.isFile()) {
                String absolutePath = file.getAbsolutePath();
                File cacheDir = getCacheDir();
                Intrinsics.checkNotNullExpressionValue(cacheDir, "cacheDir");
                DexClassLoader dexClassLoader = new DexClassLoader(absolutePath, cacheDir.getAbsolutePath(), null, getClassLoader());
                Object objInvoke = dexClassLoader.loadClass("infosecadventures.allsafe.updater.VersionCheck").getDeclaredMethod("getLatestVersion", new Class[0]).invoke(null, new Object[0]);
                if (objInvoke == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                }
                int version = ((Integer) objInvoke).intValue();
                if (Build.VERSION.SDK_INT < version) {
                    Toast.makeText(this, "Update required!", 1).show();
                }
            }
        } catch (Exception e) {
        }
    }
}
