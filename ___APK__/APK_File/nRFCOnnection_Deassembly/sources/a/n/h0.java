package a.n;

import android.graphics.Matrix;
import android.view.View;

/* loaded from: classes.dex */
class h0 extends g0 {
    @Override // a.n.d0, a.n.i0
    public void a(View view, float f2) {
        view.setTransitionAlpha(f2);
    }

    @Override // a.n.d0, a.n.i0
    public float b(View view) {
        return view.getTransitionAlpha();
    }

    @Override // a.n.g0, a.n.i0
    public void a(View view, int i) {
        view.setTransitionVisibility(i);
    }

    @Override // a.n.e0, a.n.i0
    public void b(View view, Matrix matrix) {
        view.transformMatrixToLocal(matrix);
    }

    @Override // a.n.f0, a.n.i0
    public void a(View view, int i, int i2, int i3, int i4) {
        view.setLeftTopRightBottom(i, i2, i3, i4);
    }

    @Override // a.n.e0, a.n.i0
    public void a(View view, Matrix matrix) {
        view.transformMatrixToGlobal(matrix);
    }
}
