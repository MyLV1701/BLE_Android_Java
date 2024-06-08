package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public abstract class u extends RecyclerView.l {
    boolean g = true;

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.d0 d0Var) {
        return !this.g || d0Var.isInvalid();
    }

    public abstract boolean a(RecyclerView.d0 d0Var, int i, int i2, int i3, int i4);

    public abstract boolean a(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, int i, int i2, int i3, int i4);

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean b(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i = cVar.f1707a;
        int i2 = cVar.f1708b;
        View view = d0Var.itemView;
        int left = cVar2 == null ? view.getLeft() : cVar2.f1707a;
        int top = cVar2 == null ? view.getTop() : cVar2.f1708b;
        if (!d0Var.isRemoved() && (i != left || i2 != top)) {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            return a(d0Var, i, i2, left, top);
        }
        return g(d0Var);
    }

    public void c(RecyclerView.d0 d0Var, boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean c(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        if (cVar.f1707a == cVar2.f1707a && cVar.f1708b == cVar2.f1708b) {
            j(d0Var);
            return false;
        }
        return a(d0Var, cVar.f1707a, cVar.f1708b, cVar2.f1707a, cVar2.f1708b);
    }

    public void d(RecyclerView.d0 d0Var, boolean z) {
    }

    public abstract boolean f(RecyclerView.d0 d0Var);

    public abstract boolean g(RecyclerView.d0 d0Var);

    public final void h(RecyclerView.d0 d0Var) {
        n(d0Var);
        b(d0Var);
    }

    public final void i(RecyclerView.d0 d0Var) {
        o(d0Var);
    }

    public final void j(RecyclerView.d0 d0Var) {
        p(d0Var);
        b(d0Var);
    }

    public final void k(RecyclerView.d0 d0Var) {
        q(d0Var);
    }

    public final void l(RecyclerView.d0 d0Var) {
        r(d0Var);
        b(d0Var);
    }

    public final void m(RecyclerView.d0 d0Var) {
        s(d0Var);
    }

    public void n(RecyclerView.d0 d0Var) {
    }

    public void o(RecyclerView.d0 d0Var) {
    }

    public void p(RecyclerView.d0 d0Var) {
    }

    public void q(RecyclerView.d0 d0Var) {
    }

    public void r(RecyclerView.d0 d0Var) {
    }

    public void s(RecyclerView.d0 d0Var) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.d0 d0Var, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        if (cVar != null && (cVar.f1707a != cVar2.f1707a || cVar.f1708b != cVar2.f1708b)) {
            return a(d0Var, cVar.f1707a, cVar.f1708b, cVar2.f1707a, cVar2.f1708b);
        }
        return f(d0Var);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2, RecyclerView.l.c cVar, RecyclerView.l.c cVar2) {
        int i;
        int i2;
        int i3 = cVar.f1707a;
        int i4 = cVar.f1708b;
        if (d0Var2.shouldIgnore()) {
            int i5 = cVar.f1707a;
            i2 = cVar.f1708b;
            i = i5;
        } else {
            i = cVar2.f1707a;
            i2 = cVar2.f1708b;
        }
        return a(d0Var, d0Var2, i3, i4, i, i2);
    }

    public final void b(RecyclerView.d0 d0Var, boolean z) {
        d(d0Var, z);
    }

    public final void a(RecyclerView.d0 d0Var, boolean z) {
        c(d0Var, z);
        b(d0Var);
    }
}
