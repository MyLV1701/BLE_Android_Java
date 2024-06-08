package c.a.b.w.n;

import c.a.b.t;
import c.a.b.u;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class a<E> extends t<Object> {

    /* renamed from: c, reason: collision with root package name */
    public static final u f2247c = new C0077a();

    /* renamed from: a, reason: collision with root package name */
    private final Class<E> f2248a;

    /* renamed from: b, reason: collision with root package name */
    private final t<E> f2249b;

    /* renamed from: c.a.b.w.n.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0077a implements u {
        C0077a() {
        }

        @Override // c.a.b.u
        public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            Type b2 = aVar.b();
            if (!(b2 instanceof GenericArrayType) && (!(b2 instanceof Class) || !((Class) b2).isArray())) {
                return null;
            }
            Type d2 = c.a.b.w.b.d(b2);
            return new a(eVar, eVar.a(c.a.b.x.a.a(d2)), c.a.b.w.b.e(d2));
        }
    }

    public a(c.a.b.e eVar, t<E> tVar, Class<E> cls) {
        this.f2249b = new m(eVar, tVar, cls);
        this.f2248a = cls;
    }

    @Override // c.a.b.t
    /* renamed from: a */
    public Object a2(c.a.b.y.a aVar) {
        if (aVar.q() == c.a.b.y.b.NULL) {
            aVar.o();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.a();
        while (aVar.g()) {
            arrayList.add(this.f2249b.a2(aVar));
        }
        aVar.d();
        int size = arrayList.size();
        Object newInstance = Array.newInstance((Class<?>) this.f2248a, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    @Override // c.a.b.t
    public void a(c.a.b.y.c cVar, Object obj) {
        if (obj == null) {
            cVar.g();
            return;
        }
        cVar.a();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f2249b.a(cVar, Array.get(obj, i));
        }
        cVar.c();
    }
}
