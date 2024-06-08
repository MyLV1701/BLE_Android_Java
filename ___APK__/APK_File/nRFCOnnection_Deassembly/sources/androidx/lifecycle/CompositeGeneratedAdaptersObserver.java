package androidx.lifecycle;

import androidx.lifecycle.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class CompositeGeneratedAdaptersObserver implements h {

    /* renamed from: a, reason: collision with root package name */
    private final d[] f1503a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeGeneratedAdaptersObserver(d[] dVarArr) {
        this.f1503a = dVarArr;
    }

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        n nVar = new n();
        for (d dVar : this.f1503a) {
            dVar.a(jVar, aVar, false, nVar);
        }
        for (d dVar2 : this.f1503a) {
            dVar2.a(jVar, aVar, true, nVar);
        }
    }
}
