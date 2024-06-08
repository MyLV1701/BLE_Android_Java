package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements e {
    private f j(d dVar) {
        return (f) dVar.d();
    }

    @Override // androidx.cardview.widget.e
    public void a() {
    }

    @Override // androidx.cardview.widget.e
    public void a(d dVar, Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        dVar.a(new f(colorStateList, f2));
        View a2 = dVar.a();
        a2.setClipToOutline(true);
        a2.setElevation(f3);
        c(dVar, f4);
    }

    @Override // androidx.cardview.widget.e
    public void b(d dVar, float f2) {
        dVar.a().setElevation(f2);
    }

    @Override // androidx.cardview.widget.e
    public void c(d dVar, float f2) {
        j(dVar).a(f2, dVar.c(), dVar.b());
        i(dVar);
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return j(dVar).c();
    }

    @Override // androidx.cardview.widget.e
    public float e(d dVar) {
        return d(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public float f(d dVar) {
        return d(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar) {
        c(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar) {
        c(dVar, a(dVar));
    }

    public void i(d dVar) {
        if (!dVar.c()) {
            dVar.a(0, 0, 0, 0);
            return;
        }
        float a2 = a(dVar);
        float d2 = d(dVar);
        int ceil = (int) Math.ceil(g.a(a2, d2, dVar.b()));
        int ceil2 = (int) Math.ceil(g.b(a2, d2, dVar.b()));
        dVar.a(ceil, ceil2, ceil, ceil2);
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList b(d dVar) {
        return j(dVar).a();
    }

    @Override // androidx.cardview.widget.e
    public float c(d dVar) {
        return dVar.a().getElevation();
    }

    @Override // androidx.cardview.widget.e
    public void a(d dVar, float f2) {
        j(dVar).a(f2);
    }

    @Override // androidx.cardview.widget.e
    public float a(d dVar) {
        return j(dVar).b();
    }

    @Override // androidx.cardview.widget.e
    public void a(d dVar, ColorStateList colorStateList) {
        j(dVar).a(colorStateList);
    }
}
