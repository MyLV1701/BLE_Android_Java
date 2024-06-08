package c.a.b.w.n;

import c.a.b.t;
import c.a.b.w.n.i;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m<T> extends t<T> {

    /* renamed from: a, reason: collision with root package name */
    private final c.a.b.e f2288a;

    /* renamed from: b, reason: collision with root package name */
    private final t<T> f2289b;

    /* renamed from: c, reason: collision with root package name */
    private final Type f2290c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(c.a.b.e eVar, t<T> tVar, Type type) {
        this.f2288a = eVar;
        this.f2289b = tVar;
        this.f2290c = type;
    }

    @Override // c.a.b.t
    /* renamed from: a */
    public T a2(c.a.b.y.a aVar) {
        return this.f2289b.a2(aVar);
    }

    @Override // c.a.b.t
    public void a(c.a.b.y.c cVar, T t) {
        t<T> tVar = this.f2289b;
        Type a2 = a(this.f2290c, t);
        if (a2 != this.f2290c) {
            tVar = this.f2288a.a(c.a.b.x.a.a(a2));
            if (tVar instanceof i.b) {
                t<T> tVar2 = this.f2289b;
                if (!(tVar2 instanceof i.b)) {
                    tVar = tVar2;
                }
            }
        }
        tVar.a(cVar, t);
    }

    private Type a(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
