package androidx.constraintlayout.solver.state;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\solver\state\Reference.smali */
public interface Reference {
    void apply();

    ConstraintWidget getConstraintWidget();

    Object getKey();

    void setConstraintWidget(ConstraintWidget constraintWidget);

    void setKey(Object obj);
}
