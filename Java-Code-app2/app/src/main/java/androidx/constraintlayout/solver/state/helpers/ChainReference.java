package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\solver\state\helpers\ChainReference.smali */
public class ChainReference extends HelperReference {
    protected float mBias;
    protected State.Chain mStyle;

    public ChainReference(State state, State.Helper type) {
        super(state, type);
        this.mBias = 0.5f;
        this.mStyle = State.Chain.SPREAD;
    }

    public State.Chain getStyle() {
        return State.Chain.SPREAD;
    }

    public void style(State.Chain style) {
        this.mStyle = style;
    }

    public float getBias() {
        return this.mBias;
    }

    public void bias(float bias) {
        this.mBias = bias;
    }
}
