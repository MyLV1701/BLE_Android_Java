package c.a.b.w.n;

import c.a.b.o;
import c.a.b.r;
import c.a.b.t;
import c.a.b.u;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public final class g implements u {

    /* renamed from: b, reason: collision with root package name */
    private final c.a.b.w.c f2256b;

    /* renamed from: c, reason: collision with root package name */
    final boolean f2257c;

    public g(c.a.b.w.c cVar, boolean z) {
        this.f2256b = cVar;
        this.f2257c = z;
    }

    @Override // c.a.b.u
    public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
        Type b2 = aVar.b();
        if (!Map.class.isAssignableFrom(aVar.a())) {
            return null;
        }
        Type[] b3 = c.a.b.w.b.b(b2, c.a.b.w.b.e(b2));
        return new a(eVar, b3[0], a(eVar, b3[0]), b3[1], eVar.a(c.a.b.x.a.a(b3[1])), this.f2256b.a(aVar));
    }

    /* loaded from: classes.dex */
    private final class a<K, V> extends t<Map<K, V>> {

        /* renamed from: a, reason: collision with root package name */
        private final t<K> f2258a;

        /* renamed from: b, reason: collision with root package name */
        private final t<V> f2259b;

        /* renamed from: c, reason: collision with root package name */
        private final c.a.b.w.i<? extends Map<K, V>> f2260c;

        public a(c.a.b.e eVar, Type type, t<K> tVar, Type type2, t<V> tVar2, c.a.b.w.i<? extends Map<K, V>> iVar) {
            this.f2258a = new m(eVar, tVar, type);
            this.f2259b = new m(eVar, tVar2, type2);
            this.f2260c = iVar;
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public Map<K, V> a2(c.a.b.y.a aVar) {
            c.a.b.y.b q = aVar.q();
            if (q == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            Map<K, V> a2 = this.f2260c.a();
            if (q == c.a.b.y.b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.g()) {
                    aVar.a();
                    K a22 = this.f2258a.a2(aVar);
                    if (a2.put(a22, this.f2259b.a2(aVar)) == null) {
                        aVar.d();
                    } else {
                        throw new r("duplicate key: " + a22);
                    }
                }
                aVar.d();
            } else {
                aVar.b();
                while (aVar.g()) {
                    c.a.b.w.f.f2224a.a(aVar);
                    K a23 = this.f2258a.a2(aVar);
                    if (a2.put(a23, this.f2259b.a2(aVar)) != null) {
                        throw new r("duplicate key: " + a23);
                    }
                }
                aVar.e();
            }
            return a2;
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Map<K, V> map) {
            if (map == null) {
                cVar.g();
                return;
            }
            if (!g.this.f2257c) {
                cVar.b();
                for (Map.Entry<K, V> entry : map.entrySet()) {
                    cVar.a(String.valueOf(entry.getKey()));
                    this.f2259b.a(cVar, entry.getValue());
                }
                cVar.d();
                return;
            }
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry<K, V> entry2 : map.entrySet()) {
                c.a.b.j a2 = this.f2258a.a((t<K>) entry2.getKey());
                arrayList.add(a2);
                arrayList2.add(entry2.getValue());
                z |= a2.d() || a2.f();
            }
            if (z) {
                cVar.a();
                int size = arrayList.size();
                while (i < size) {
                    cVar.a();
                    c.a.b.w.l.a((c.a.b.j) arrayList.get(i), cVar);
                    this.f2259b.a(cVar, arrayList2.get(i));
                    cVar.c();
                    i++;
                }
                cVar.c();
                return;
            }
            cVar.b();
            int size2 = arrayList.size();
            while (i < size2) {
                cVar.a(a((c.a.b.j) arrayList.get(i)));
                this.f2259b.a(cVar, arrayList2.get(i));
                i++;
            }
            cVar.d();
        }

        private String a(c.a.b.j jVar) {
            if (jVar.g()) {
                o c2 = jVar.c();
                if (c2.o()) {
                    return String.valueOf(c2.l());
                }
                if (c2.n()) {
                    return Boolean.toString(c2.h());
                }
                if (c2.p()) {
                    return c2.m();
                }
                throw new AssertionError();
            }
            if (jVar.e()) {
                return "null";
            }
            throw new AssertionError();
        }
    }

    private t<?> a(c.a.b.e eVar, Type type) {
        if (type != Boolean.TYPE && type != Boolean.class) {
            return eVar.a(c.a.b.x.a.a(type));
        }
        return n.f2296f;
    }
}
