package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\lifecycle\OnLifecycleEvent.smali */
public @interface OnLifecycleEvent {
    Lifecycle.Event value();
}
