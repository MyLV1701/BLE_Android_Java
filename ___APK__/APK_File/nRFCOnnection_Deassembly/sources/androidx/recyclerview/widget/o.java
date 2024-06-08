package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class o extends v {

    /* renamed from: d, reason: collision with root package name */
    private r f1914d;

    /* renamed from: e, reason: collision with root package name */
    private r f1915e;

    private View b(RecyclerView.o oVar, r rVar) {
        int e2 = oVar.e();
        View view = null;
        if (e2 == 0) {
            return null;
        }
        int f2 = rVar.f() + (rVar.g() / 2);
        int i = Preference.DEFAULT_ORDER;
        for (int i2 = 0; i2 < e2; i2++) {
            View d2 = oVar.d(i2);
            int abs = Math.abs((rVar.d(d2) + (rVar.b(d2) / 2)) - f2);
            if (abs < i) {
                view = d2;
                i = abs;
            }
        }
        return view;
    }

    private r d(RecyclerView.o oVar) {
        r rVar = this.f1915e;
        if (rVar == null || rVar.f1917a != oVar) {
            this.f1915e = r.a(oVar);
        }
        return this.f1915e;
    }

    private r e(RecyclerView.o oVar) {
        r rVar = this.f1914d;
        if (rVar == null || rVar.f1917a != oVar) {
            this.f1914d = r.b(oVar);
        }
        return this.f1914d;
    }

    @Override // androidx.recyclerview.widget.v
    public int[] a(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.a()) {
            iArr[0] = a(oVar, view, d(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.b()) {
            iArr[1] = a(oVar, view, e(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.v
    public View c(RecyclerView.o oVar) {
        if (oVar.b()) {
            return b(oVar, e(oVar));
        }
        if (oVar.a()) {
            return b(oVar, d(oVar));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.v
    public int a(RecyclerView.o oVar, int i, int i2) {
        int j;
        View c2;
        int l;
        int i3;
        PointF a2;
        int i4;
        int i5;
        if (!(oVar instanceof RecyclerView.z.b) || (j = oVar.j()) == 0 || (c2 = c(oVar)) == null || (l = oVar.l(c2)) == -1 || (a2 = ((RecyclerView.z.b) oVar).a(j - 1)) == null) {
            return -1;
        }
        if (oVar.a()) {
            i4 = a(oVar, d(oVar), i, 0);
            if (a2.x < 0.0f) {
                i4 = -i4;
            }
        } else {
            i4 = 0;
        }
        if (oVar.b()) {
            i5 = a(oVar, e(oVar), 0, i2);
            if (a2.y < 0.0f) {
                i5 = -i5;
            }
        } else {
            i5 = 0;
        }
        if (oVar.b()) {
            i4 = i5;
        }
        if (i4 == 0) {
            return -1;
        }
        int i6 = l + i4;
        if (i6 < 0) {
            i6 = 0;
        }
        return i6 >= j ? i3 : i6;
    }

    private int a(RecyclerView.o oVar, View view, r rVar) {
        return (rVar.d(view) + (rVar.b(view) / 2)) - (rVar.f() + (rVar.g() / 2));
    }

    private int a(RecyclerView.o oVar, r rVar, int i, int i2) {
        int[] b2 = b(i, i2);
        float a2 = a(oVar, rVar);
        if (a2 <= 0.0f) {
            return 0;
        }
        return Math.round((Math.abs(b2[0]) > Math.abs(b2[1]) ? b2[0] : b2[1]) / a2);
    }

    private float a(RecyclerView.o oVar, r rVar) {
        int e2 = oVar.e();
        if (e2 == 0) {
            return 1.0f;
        }
        View view = null;
        View view2 = null;
        int i = Preference.DEFAULT_ORDER;
        int i2 = RecyclerView.UNDEFINED_DURATION;
        for (int i3 = 0; i3 < e2; i3++) {
            View d2 = oVar.d(i3);
            int l = oVar.l(d2);
            if (l != -1) {
                if (l < i) {
                    view = d2;
                    i = l;
                }
                if (l > i2) {
                    view2 = d2;
                    i2 = l;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        int max = Math.max(rVar.a(view), rVar.a(view2)) - Math.min(rVar.d(view), rVar.d(view2));
        if (max == 0) {
            return 1.0f;
        }
        return (max * 1.0f) / ((i2 - i) + 1);
    }
}
