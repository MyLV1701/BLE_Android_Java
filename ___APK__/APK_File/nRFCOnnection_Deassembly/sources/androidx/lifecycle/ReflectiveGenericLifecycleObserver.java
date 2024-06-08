package androidx.lifecycle;

import androidx.lifecycle.b;
import androidx.lifecycle.g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements h {

    /* renamed from: a, reason: collision with root package name */
    private final Object f1521a;

    /* renamed from: b, reason: collision with root package name */
    private final b.a f1522b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1521a = obj;
        this.f1522b = b.f1529c.a(this.f1521a.getClass());
    }

    @Override // androidx.lifecycle.h
    public void a(j jVar, g.a aVar) {
        this.f1522b.a(jVar, aVar, this.f1521a);
    }
}
