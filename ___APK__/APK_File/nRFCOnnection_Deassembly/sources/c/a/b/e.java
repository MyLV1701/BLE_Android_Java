package c.a.b;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* loaded from: classes.dex */
public final class e {
    private static final c.a.b.x.a<?> h = c.a.b.x.a.a(Object.class);

    /* renamed from: a, reason: collision with root package name */
    private final ThreadLocal<Map<c.a.b.x.a<?>, f<?>>> f2177a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<c.a.b.x.a<?>, t<?>> f2178b;

    /* renamed from: c, reason: collision with root package name */
    private final c.a.b.w.c f2179c;

    /* renamed from: d, reason: collision with root package name */
    private final c.a.b.w.n.d f2180d;

    /* renamed from: e, reason: collision with root package name */
    final List<u> f2181e;

    /* renamed from: f, reason: collision with root package name */
    final boolean f2182f;
    final boolean g;

    public e() {
        this(c.a.b.w.d.h, c.a.b.c.f2172b, Collections.emptyMap(), false, false, false, true, false, false, false, s.f2190b, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    private t<Number> a(boolean z) {
        if (z) {
            return c.a.b.w.n.n.v;
        }
        return new a(this);
    }

    private t<Number> b(boolean z) {
        if (z) {
            return c.a.b.w.n.n.u;
        }
        return new b(this);
    }

    public String toString() {
        return "{serializeNulls:" + this.f2182f + ",factories:" + this.f2181e + ",instanceCreators:" + this.f2179c + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends t<Number> {
        a(e eVar) {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return Double.valueOf(aVar.k());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            if (number == null) {
                cVar.g();
            } else {
                e.a(number.doubleValue());
                cVar.a(number);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends t<Number> {
        b(e eVar) {
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return Float.valueOf((float) aVar.k());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            if (number == null) {
                cVar.g();
            } else {
                e.a(number.floatValue());
                cVar.a(number);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends t<Number> {
        c() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return Long.valueOf(aVar.m());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            if (number == null) {
                cVar.g();
            } else {
                cVar.b(number.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends t<AtomicLong> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ t f2183a;

        d(t tVar) {
            this.f2183a = tVar;
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, AtomicLong atomicLong) {
            this.f2183a.a(cVar, Long.valueOf(atomicLong.get()));
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicLong a2(c.a.b.y.a aVar) {
            return new AtomicLong(((Number) this.f2183a.a2(aVar)).longValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.a.b.e$e, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0074e extends t<AtomicLongArray> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ t f2184a;

        C0074e(t tVar) {
            this.f2184a = tVar;
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, AtomicLongArray atomicLongArray) {
            cVar.a();
            int length = atomicLongArray.length();
            for (int i = 0; i < length; i++) {
                this.f2184a.a(cVar, Long.valueOf(atomicLongArray.get(i)));
            }
            cVar.c();
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicLongArray a2(c.a.b.y.a aVar) {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.g()) {
                arrayList.add(Long.valueOf(((Number) this.f2184a.a2(aVar)).longValue()));
            }
            aVar.d();
            int size = arrayList.size();
            AtomicLongArray atomicLongArray = new AtomicLongArray(size);
            for (int i = 0; i < size; i++) {
                atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
            }
            return atomicLongArray;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class f<T> extends t<T> {

        /* renamed from: a, reason: collision with root package name */
        private t<T> f2185a;

        f() {
        }

        public void a(t<T> tVar) {
            if (this.f2185a == null) {
                this.f2185a = tVar;
                return;
            }
            throw new AssertionError();
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public T a2(c.a.b.y.a aVar) {
            t<T> tVar = this.f2185a;
            if (tVar != null) {
                return tVar.a2(aVar);
            }
            throw new IllegalStateException();
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, T t) {
            t<T> tVar = this.f2185a;
            if (tVar != null) {
                tVar.a(cVar, t);
                return;
            }
            throw new IllegalStateException();
        }
    }

    static void a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static t<AtomicLongArray> b(t<Number> tVar) {
        return new C0074e(tVar).a();
    }

    private static t<Number> a(s sVar) {
        if (sVar == s.f2190b) {
            return c.a.b.w.n.n.t;
        }
        return new c();
    }

    e(c.a.b.w.d dVar, c.a.b.d dVar2, Map<Type, c.a.b.f<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, s sVar, String str, int i, int i2, List<u> list, List<u> list2, List<u> list3) {
        this.f2177a = new ThreadLocal<>();
        this.f2178b = new ConcurrentHashMap();
        this.f2179c = new c.a.b.w.c(map);
        this.f2182f = z;
        this.g = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(c.a.b.w.n.n.Y);
        arrayList.add(c.a.b.w.n.h.f2262b);
        arrayList.add(dVar);
        arrayList.addAll(list3);
        arrayList.add(c.a.b.w.n.n.D);
        arrayList.add(c.a.b.w.n.n.m);
        arrayList.add(c.a.b.w.n.n.g);
        arrayList.add(c.a.b.w.n.n.i);
        arrayList.add(c.a.b.w.n.n.k);
        t<Number> a2 = a(sVar);
        arrayList.add(c.a.b.w.n.n.a(Long.TYPE, Long.class, a2));
        arrayList.add(c.a.b.w.n.n.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(c.a.b.w.n.n.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(c.a.b.w.n.n.x);
        arrayList.add(c.a.b.w.n.n.o);
        arrayList.add(c.a.b.w.n.n.q);
        arrayList.add(c.a.b.w.n.n.a(AtomicLong.class, a(a2)));
        arrayList.add(c.a.b.w.n.n.a(AtomicLongArray.class, b(a2)));
        arrayList.add(c.a.b.w.n.n.s);
        arrayList.add(c.a.b.w.n.n.z);
        arrayList.add(c.a.b.w.n.n.F);
        arrayList.add(c.a.b.w.n.n.H);
        arrayList.add(c.a.b.w.n.n.a(BigDecimal.class, c.a.b.w.n.n.B));
        arrayList.add(c.a.b.w.n.n.a(BigInteger.class, c.a.b.w.n.n.C));
        arrayList.add(c.a.b.w.n.n.J);
        arrayList.add(c.a.b.w.n.n.L);
        arrayList.add(c.a.b.w.n.n.P);
        arrayList.add(c.a.b.w.n.n.R);
        arrayList.add(c.a.b.w.n.n.W);
        arrayList.add(c.a.b.w.n.n.N);
        arrayList.add(c.a.b.w.n.n.f2294d);
        arrayList.add(c.a.b.w.n.c.f2253b);
        arrayList.add(c.a.b.w.n.n.U);
        arrayList.add(c.a.b.w.n.k.f2280b);
        arrayList.add(c.a.b.w.n.j.f2278b);
        arrayList.add(c.a.b.w.n.n.S);
        arrayList.add(c.a.b.w.n.a.f2247c);
        arrayList.add(c.a.b.w.n.n.f2292b);
        arrayList.add(new c.a.b.w.n.b(this.f2179c));
        arrayList.add(new c.a.b.w.n.g(this.f2179c, z2));
        this.f2180d = new c.a.b.w.n.d(this.f2179c);
        arrayList.add(this.f2180d);
        arrayList.add(c.a.b.w.n.n.Z);
        arrayList.add(new c.a.b.w.n.i(this.f2179c, dVar2, dVar, this.f2180d));
        this.f2181e = Collections.unmodifiableList(arrayList);
    }

    private static t<AtomicLong> a(t<Number> tVar) {
        return new d(tVar).a();
    }

    public <T> t<T> a(c.a.b.x.a<T> aVar) {
        t<T> tVar = (t) this.f2178b.get(aVar == null ? h : aVar);
        if (tVar != null) {
            return tVar;
        }
        Map<c.a.b.x.a<?>, f<?>> map = this.f2177a.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap<>();
            this.f2177a.set(map);
            z = true;
        }
        f<?> fVar = map.get(aVar);
        if (fVar != null) {
            return fVar;
        }
        try {
            f<?> fVar2 = new f<>();
            map.put(aVar, fVar2);
            Iterator<u> it = this.f2181e.iterator();
            while (it.hasNext()) {
                t<T> a2 = it.next().a(this, aVar);
                if (a2 != null) {
                    fVar2.a((t<?>) a2);
                    this.f2178b.put(aVar, a2);
                    return a2;
                }
            }
            throw new IllegalArgumentException("GSON (2.8.6) cannot handle " + aVar);
        } finally {
            map.remove(aVar);
            if (z) {
                this.f2177a.remove();
            }
        }
    }

    public <T> t<T> a(u uVar, c.a.b.x.a<T> aVar) {
        if (!this.f2181e.contains(uVar)) {
            uVar = this.f2180d;
        }
        boolean z = false;
        for (u uVar2 : this.f2181e) {
            if (z) {
                t<T> a2 = uVar2.a(this, aVar);
                if (a2 != null) {
                    return a2;
                }
            } else if (uVar2 == uVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public <T> t<T> a(Class<T> cls) {
        return a(c.a.b.x.a.a((Class) cls));
    }

    public c.a.b.y.a a(Reader reader) {
        c.a.b.y.a aVar = new c.a.b.y.a(reader);
        aVar.a(this.g);
        return aVar;
    }

    public <T> T a(String str, Class<T> cls) {
        return (T) c.a.b.w.k.a((Class) cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) {
        if (str == null) {
            return null;
        }
        return (T) a(new StringReader(str), type);
    }

    public <T> T a(Reader reader, Class<T> cls) {
        c.a.b.y.a a2 = a(reader);
        Object a3 = a(a2, cls);
        a(a3, a2);
        return (T) c.a.b.w.k.a((Class) cls).cast(a3);
    }

    public <T> T a(Reader reader, Type type) {
        c.a.b.y.a a2 = a(reader);
        T t = (T) a(a2, type);
        a(t, a2);
        return t;
    }

    private static void a(Object obj, c.a.b.y.a aVar) {
        if (obj != null) {
            try {
                if (aVar.q() == c.a.b.y.b.END_DOCUMENT) {
                } else {
                    throw new k("JSON document was not fully consumed.");
                }
            } catch (c.a.b.y.d e2) {
                throw new r(e2);
            } catch (IOException e3) {
                throw new k(e3);
            }
        }
    }

    public <T> T a(c.a.b.y.a aVar, Type type) {
        boolean h2 = aVar.h();
        boolean z = true;
        aVar.a(true);
        try {
            try {
                try {
                    aVar.q();
                    z = false;
                    T a2 = a(c.a.b.x.a.a(type)).a2(aVar);
                    aVar.a(h2);
                    return a2;
                } catch (AssertionError e2) {
                    AssertionError assertionError = new AssertionError("AssertionError (GSON 2.8.6): " + e2.getMessage());
                    assertionError.initCause(e2);
                    throw assertionError;
                } catch (IllegalStateException e3) {
                    throw new r(e3);
                }
            } catch (EOFException e4) {
                if (z) {
                    aVar.a(h2);
                    return null;
                }
                throw new r(e4);
            } catch (IOException e5) {
                throw new r(e5);
            }
        } catch (Throwable th) {
            aVar.a(h2);
            throw th;
        }
    }
}
