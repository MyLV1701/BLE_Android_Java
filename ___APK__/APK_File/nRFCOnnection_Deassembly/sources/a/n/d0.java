package a.n;

import android.annotation.SuppressLint;
import android.view.View;

/* loaded from: classes.dex */
class d0 extends i0 {

    /* renamed from: e, reason: collision with root package name */
    private static boolean f451e = true;

    @Override // a.n.i0
    public void a(View view) {
    }

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public void a(View view, float f2) {
        if (f451e) {
            try {
                view.setTransitionAlpha(f2);
                return;
            } catch (NoSuchMethodError unused) {
                f451e = false;
            }
        }
        view.setAlpha(f2);
    }

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public float b(View view) {
        if (f451e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f451e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // a.n.i0
    public void c(View view) {
    }
}
