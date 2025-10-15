package androidx.lifecycle;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\MutableLiveData.smali */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData(T value) {
        super(value);
    }

    public MutableLiveData() {
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(T value) {
        super.setValue(value);
    }
}
