package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class t {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(RecyclerView.a0 a0Var, r rVar, View view, View view2, RecyclerView.o oVar, boolean z, boolean z2) {
        int max;
        if (oVar.e() == 0 || a0Var.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        int min = Math.min(oVar.l(view), oVar.l(view2));
        int max2 = Math.max(oVar.l(view), oVar.l(view2));
        if (z2) {
            max = Math.max(0, (a0Var.a() - max2) - 1);
        } else {
            max = Math.max(0, min);
        }
        if (!z) {
            return max;
        }
        return Math.round((max * (Math.abs(rVar.a(view2) - rVar.d(view)) / (Math.abs(oVar.l(view) - oVar.l(view2)) + 1))) + (rVar.f() - rVar.d(view)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(RecyclerView.a0 a0Var, r rVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.e() == 0 || a0Var.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return a0Var.a();
        }
        return (int) (((rVar.a(view2) - rVar.d(view)) / (Math.abs(oVar.l(view) - oVar.l(view2)) + 1)) * a0Var.a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(RecyclerView.a0 a0Var, r rVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.e() == 0 || a0Var.a() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(oVar.l(view) - oVar.l(view2)) + 1;
        }
        return Math.min(rVar.g(), rVar.a(view2) - rVar.d(view));
    }
}
