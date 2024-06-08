package androidx.fragment.app;

import androidx.lifecycle.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x implements androidx.lifecycle.j {

    /* renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.k f1502b = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        if (this.f1502b == null) {
            this.f1502b = new androidx.lifecycle.k(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return this.f1502b != null;
    }

    @Override // androidx.lifecycle.j
    public androidx.lifecycle.g getLifecycle() {
        a();
        return this.f1502b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(g.a aVar) {
        this.f1502b.a(aVar);
    }
}
