package androidx.constraintlayout.solver.widgets.analyzer;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\solver\widgets\analyzer\BaselineDimensionDependency.smali */
class BaselineDimensionDependency extends DimensionDependency {
    public BaselineDimensionDependency(WidgetRun run) {
        super(run);
    }

    public void update(DependencyNode node) {
        VerticalWidgetRun vrun = (VerticalWidgetRun) this.run;
        vrun.baseline.margin = this.run.widget.getBaselineDistance();
        this.resolved = true;
    }
}
