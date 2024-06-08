package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public final class b implements p {

    /* renamed from: a, reason: collision with root package name */
    private final RecyclerView.g f1788a;

    public b(RecyclerView.g gVar) {
        this.f1788a = gVar;
    }

    @Override // androidx.recyclerview.widget.p
    public void a(int i, int i2) {
        this.f1788a.notifyItemRangeRemoved(i, i2);
    }

    @Override // androidx.recyclerview.widget.p
    public void b(int i, int i2) {
        this.f1788a.notifyItemMoved(i, i2);
    }

    @Override // androidx.recyclerview.widget.p
    public void c(int i, int i2) {
        this.f1788a.notifyItemRangeInserted(i, i2);
    }

    @Override // androidx.recyclerview.widget.p
    public void a(int i, int i2, Object obj) {
        this.f1788a.notifyItemRangeChanged(i, i2, obj);
    }
}
