package androidx.lifecycle;

import android.app.Application;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\AndroidViewModel.smali */
public class AndroidViewModel extends ViewModel {
    private Application mApplication;

    public AndroidViewModel(Application application) {
        this.mApplication = application;
    }

    public <T extends Application> T getApplication() {
        return (T) this.mApplication;
    }
}
