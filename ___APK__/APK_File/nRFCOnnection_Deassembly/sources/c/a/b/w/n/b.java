package c.a.b.w.n;

import c.a.b.t;
import c.a.b.u;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class b implements u {

    /* renamed from: b, reason: collision with root package name */
    private final c.a.b.w.c f2250b;

    public b(c.a.b.w.c cVar) {
        this.f2250b = cVar;
    }

    @Override // c.a.b.u
    public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
        Type b2 = aVar.b();
        Class<? super T> a2 = aVar.a();
        if (!Collection.class.isAssignableFrom(a2)) {
            return null;
        }
        Type a3 = c.a.b.w.b.a(b2, (Class<?>) a2);
        return new a(eVar, a3, eVar.a(c.a.b.x.a.a(a3)), this.f2250b.a(aVar));
    }

    /* loaded from: classes.dex */
    private static final class a<E> extends t<Collection<E>> {

        /* renamed from: a, reason: collision with root package name */
        private final t<E> f2251a;

        /* renamed from: b, reason: collision with root package name */
        private final c.a.b.w.i<? extends Collection<E>> f2252b;

        public a(c.a.b.e eVar, Type type, t<E> tVar, c.a.b.w.i<? extends Collection<E>> iVar) {
            this.f2251a = new m(eVar, tVar, type);
            this.f2252b = iVar;
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public Collection<E> a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            Collection<E> a2 = this.f2252b.a();
            aVar.a();
            while (aVar.g()) {
                a2.add(this.f2251a.a2(aVar));
            }
            aVar.d();
            return a2;
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Collection<E> collection) {
            if (collection == null) {
                cVar.g();
                return;
            }
            cVar.a();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.f2251a.a(cVar, it.next());
            }
            cVar.c();
        }
    }
}
