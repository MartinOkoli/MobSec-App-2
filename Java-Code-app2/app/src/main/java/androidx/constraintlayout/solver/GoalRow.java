package androidx.constraintlayout.solver;

/* loaded from: C:\Users\MaOk\Desktop\ADB\platform-tools-latest-windows\platform-tools\app-source3\base\smali\androidx\constraintlayout\solver\GoalRow.smali */
public class GoalRow extends ArrayRow {
    public GoalRow(Cache cache) {
        super(cache);
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.Row
    public void addError(SolverVariable error) {
        super.addError(error);
        error.usageInRowCount--;
    }
}
