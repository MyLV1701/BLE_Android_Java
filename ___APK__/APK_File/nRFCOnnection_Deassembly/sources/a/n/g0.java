package a.n;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

/* loaded from: classes.dex */
class g0 extends f0 {
    private static boolean i = true;

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public void a(View view, int i2) {
        if (Build.VERSION.SDK_INT == 28) {
            super.a(view, i2);
        } else if (i) {
            try {
                view.setTransitionVisibility(i2);
            } catch (NoSuchMethodError unused) {
                i = false;
            }
        }
    }
}
