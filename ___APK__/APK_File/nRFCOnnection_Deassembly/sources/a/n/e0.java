package a.n;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;

/* loaded from: classes.dex */
class e0 extends d0 {

    /* renamed from: f, reason: collision with root package name */
    private static boolean f460f = true;
    private static boolean g = true;

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public void a(View view, Matrix matrix) {
        if (f460f) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                f460f = false;
            }
        }
    }

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public void b(View view, Matrix matrix) {
        if (g) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                g = false;
            }
        }
    }
}
