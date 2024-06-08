package c.a.b.w.n;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* loaded from: classes.dex */
public final class n {

    /* renamed from: a, reason: collision with root package name */
    public static final c.a.b.t<Class> f2291a = new k().a();

    /* renamed from: b, reason: collision with root package name */
    public static final c.a.b.u f2292b = a(Class.class, f2291a);

    /* renamed from: c, reason: collision with root package name */
    public static final c.a.b.t<BitSet> f2293c = new v().a();

    /* renamed from: d, reason: collision with root package name */
    public static final c.a.b.u f2294d = a(BitSet.class, f2293c);

    /* renamed from: e, reason: collision with root package name */
    public static final c.a.b.t<Boolean> f2295e = new c0();

    /* renamed from: f, reason: collision with root package name */
    public static final c.a.b.t<Boolean> f2296f = new d0();
    public static final c.a.b.u g = a(Boolean.TYPE, Boolean.class, f2295e);
    public static final c.a.b.t<Number> h = new e0();
    public static final c.a.b.u i = a(Byte.TYPE, Byte.class, h);
    public static final c.a.b.t<Number> j = new f0();
    public static final c.a.b.u k = a(Short.TYPE, Short.class, j);
    public static final c.a.b.t<Number> l = new g0();
    public static final c.a.b.u m = a(Integer.TYPE, Integer.class, l);
    public static final c.a.b.t<AtomicInteger> n = new h0().a();
    public static final c.a.b.u o = a(AtomicInteger.class, n);
    public static final c.a.b.t<AtomicBoolean> p = new i0().a();
    public static final c.a.b.u q = a(AtomicBoolean.class, p);
    public static final c.a.b.t<AtomicIntegerArray> r = new a().a();
    public static final c.a.b.u s = a(AtomicIntegerArray.class, r);
    public static final c.a.b.t<Number> t = new b();
    public static final c.a.b.t<Number> u = new c();
    public static final c.a.b.t<Number> v = new d();
    public static final c.a.b.t<Number> w = new e();
    public static final c.a.b.u x = a(Number.class, w);
    public static final c.a.b.t<Character> y = new f();
    public static final c.a.b.u z = a(Character.TYPE, Character.class, y);
    public static final c.a.b.t<String> A = new g();
    public static final c.a.b.t<BigDecimal> B = new h();
    public static final c.a.b.t<BigInteger> C = new i();
    public static final c.a.b.u D = a(String.class, A);
    public static final c.a.b.t<StringBuilder> E = new j();
    public static final c.a.b.u F = a(StringBuilder.class, E);
    public static final c.a.b.t<StringBuffer> G = new l();
    public static final c.a.b.u H = a(StringBuffer.class, G);
    public static final c.a.b.t<URL> I = new m();
    public static final c.a.b.u J = a(URL.class, I);
    public static final c.a.b.t<URI> K = new C0078n();
    public static final c.a.b.u L = a(URI.class, K);
    public static final c.a.b.t<InetAddress> M = new o();
    public static final c.a.b.u N = b(InetAddress.class, M);
    public static final c.a.b.t<UUID> O = new p();
    public static final c.a.b.u P = a(UUID.class, O);
    public static final c.a.b.t<Currency> Q = new q().a();
    public static final c.a.b.u R = a(Currency.class, Q);
    public static final c.a.b.u S = new r();
    public static final c.a.b.t<Calendar> T = new s();
    public static final c.a.b.u U = b(Calendar.class, GregorianCalendar.class, T);
    public static final c.a.b.t<Locale> V = new t();
    public static final c.a.b.u W = a(Locale.class, V);
    public static final c.a.b.t<c.a.b.j> X = new u();
    public static final c.a.b.u Y = b(c.a.b.j.class, X);
    public static final c.a.b.u Z = new w();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a0 implements c.a.b.u {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f2297b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.a.b.t f2298c;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* loaded from: classes.dex */
        class a<T1> extends c.a.b.t<T1> {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ Class f2299a;

            a(Class cls) {
                this.f2299a = cls;
            }

            @Override // c.a.b.t
            public void a(c.a.b.y.c cVar, T1 t1) {
                a0.this.f2298c.a(cVar, t1);
            }

            @Override // c.a.b.t
            /* renamed from: a */
            public T1 a2(c.a.b.y.a aVar) {
                T1 t1 = (T1) a0.this.f2298c.a2(aVar);
                if (t1 == null || this.f2299a.isInstance(t1)) {
                    return t1;
                }
                throw new c.a.b.r("Expected a " + this.f2299a.getName() + " but was " + t1.getClass().getName());
            }
        }

        a0(Class cls, c.a.b.t tVar) {
            this.f2297b = cls;
            this.f2298c = tVar;
        }

        @Override // c.a.b.u
        public <T2> c.a.b.t<T2> a(c.a.b.e eVar, c.a.b.x.a<T2> aVar) {
            Class<? super T2> a2 = aVar.a();
            if (this.f2297b.isAssignableFrom(a2)) {
                return new a(a2);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f2297b.getName() + ",adapter=" + this.f2298c + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class b0 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2301a = new int[c.a.b.y.b.values().length];

        static {
            try {
                f2301a[c.a.b.y.b.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2301a[c.a.b.y.b.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2301a[c.a.b.y.b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2301a[c.a.b.y.b.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2301a[c.a.b.y.b.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2301a[c.a.b.y.b.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2301a[c.a.b.y.b.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2301a[c.a.b.y.b.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2301a[c.a.b.y.b.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2301a[c.a.b.y.b.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes.dex */
    class k extends c.a.b.t<Class> {
        k() {
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public /* bridge */ /* synthetic */ Class a2(c.a.b.y.a aVar) {
            a2(aVar);
            throw null;
        }

        @Override // c.a.b.t
        public /* bridge */ /* synthetic */ void a(c.a.b.y.c cVar, Class cls) {
            a2(cVar, cls);
            throw null;
        }

        /* renamed from: a, reason: avoid collision after fix types in other method */
        public void a2(c.a.b.y.c cVar, Class cls) {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Class a2(c.a.b.y.a aVar) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    }

    /* loaded from: classes.dex */
    class r implements c.a.b.u {
        r() {
        }

        @Override // c.a.b.u
        public <T> c.a.b.t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            return new a(this, eVar.a(Date.class));
        }

        /* loaded from: classes.dex */
        class a extends c.a.b.t<Timestamp> {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ c.a.b.t f2304a;

            a(r rVar, c.a.b.t tVar) {
                this.f2304a = tVar;
            }

            @Override // c.a.b.t
            /* renamed from: a, reason: avoid collision after fix types in other method */
            public Timestamp a2(c.a.b.y.a aVar) {
                Date date = (Date) this.f2304a.a2(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // c.a.b.t
            public void a(c.a.b.y.c cVar, Timestamp timestamp) {
                this.f2304a.a(cVar, timestamp);
            }
        }
    }

    /* loaded from: classes.dex */
    class w implements c.a.b.u {
        w() {
        }

        @Override // c.a.b.u
        public <T> c.a.b.t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            Class<? super T> a2 = aVar.a();
            if (!Enum.class.isAssignableFrom(a2) || a2 == Enum.class) {
                return null;
            }
            if (!a2.isEnum()) {
                a2 = a2.getSuperclass();
            }
            return new j0(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class x implements c.a.b.u {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f2305b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ c.a.b.t f2306c;

        x(Class cls, c.a.b.t tVar) {
            this.f2305b = cls;
            this.f2306c = tVar;
        }

        @Override // c.a.b.u
        public <T> c.a.b.t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            if (aVar.a() == this.f2305b) {
                return this.f2306c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f2305b.getName() + ",adapter=" + this.f2306c + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class y implements c.a.b.u {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f2307b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f2308c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.a.b.t f2309d;

        y(Class cls, Class cls2, c.a.b.t tVar) {
            this.f2307b = cls;
            this.f2308c = cls2;
            this.f2309d = tVar;
        }

        @Override // c.a.b.u
        public <T> c.a.b.t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            Class<? super T> a2 = aVar.a();
            if (a2 == this.f2307b || a2 == this.f2308c) {
                return this.f2309d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f2308c.getName() + "+" + this.f2307b.getName() + ",adapter=" + this.f2309d + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class z implements c.a.b.u {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f2310b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f2311c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.a.b.t f2312d;

        z(Class cls, Class cls2, c.a.b.t tVar) {
            this.f2310b = cls;
            this.f2311c = cls2;
            this.f2312d = tVar;
        }

        @Override // c.a.b.u
        public <T> c.a.b.t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            Class<? super T> a2 = aVar.a();
            if (a2 == this.f2310b || a2 == this.f2311c) {
                return this.f2312d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f2310b.getName() + "+" + this.f2311c.getName() + ",adapter=" + this.f2312d + "]";
        }
    }

    public static <TT> c.a.b.u a(Class<TT> cls, c.a.b.t<TT> tVar) {
        return new x(cls, tVar);
    }

    public static <TT> c.a.b.u b(Class<TT> cls, Class<? extends TT> cls2, c.a.b.t<? super TT> tVar) {
        return new z(cls, cls2, tVar);
    }

    /* loaded from: classes.dex */
    class a extends c.a.b.t<AtomicIntegerArray> {
        a() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicIntegerArray a2(c.a.b.y.a aVar) {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.g()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.l()));
                } catch (NumberFormatException e2) {
                    throw new c.a.b.r(e2);
                }
            }
            aVar.d();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, AtomicIntegerArray atomicIntegerArray) {
            cVar.a();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                cVar.a(atomicIntegerArray.get(i));
            }
            cVar.c();
        }
    }

    /* loaded from: classes.dex */
    class b extends c.a.b.t<Number> {
        b() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                return Long.valueOf(aVar.m());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class c extends c.a.b.t<Number> {
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
            return Float.valueOf((float) aVar.k());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class c0 extends c.a.b.t<Boolean> {
        c0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Boolean a2(c.a.b.y.a aVar) {
            c.a.b.y.b q = aVar.q();
            if (q == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            if (q == c.a.b.y.b.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.p()));
            }
            return Boolean.valueOf(aVar.j());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Boolean bool) {
            cVar.a(bool);
        }
    }

    /* loaded from: classes.dex */
    class d extends c.a.b.t<Number> {
        d() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return Double.valueOf(aVar.k());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class d0 extends c.a.b.t<Boolean> {
        d0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Boolean a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return Boolean.valueOf(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Boolean bool) {
            cVar.b(bool == null ? "null" : bool.toString());
        }
    }

    /* loaded from: classes.dex */
    class e extends c.a.b.t<Number> {
        e() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            c.a.b.y.b q = aVar.q();
            int i = b0.f2301a[q.ordinal()];
            if (i == 1 || i == 3) {
                return new c.a.b.w.g(aVar.p());
            }
            if (i == 4) {
                aVar.o();
                return null;
            }
            throw new c.a.b.r("Expecting number, got: " + q);
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class e0 extends c.a.b.t<Number> {
        e0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.l());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class f extends c.a.b.t<Character> {
        f() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Character a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            String p = aVar.p();
            if (p.length() == 1) {
                return Character.valueOf(p.charAt(0));
            }
            throw new c.a.b.r("Expecting character, got: " + p);
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Character ch) {
            cVar.b(ch == null ? null : String.valueOf(ch));
        }
    }

    /* loaded from: classes.dex */
    class f0 extends c.a.b.t<Number> {
        f0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.l());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class g extends c.a.b.t<String> {
        g() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public String a2(c.a.b.y.a aVar) {
            c.a.b.y.b q = aVar.q();
            if (q == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            if (q == c.a.b.y.b.BOOLEAN) {
                return Boolean.toString(aVar.j());
            }
            return aVar.p();
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, String str) {
            cVar.b(str);
        }
    }

    /* loaded from: classes.dex */
    class g0 extends c.a.b.t<Number> {
        g0() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public Number a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                return Integer.valueOf(aVar.l());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Number number) {
            cVar.a(number);
        }
    }

    /* loaded from: classes.dex */
    class h extends c.a.b.t<BigDecimal> {
        h() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public BigDecimal a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                return new BigDecimal(aVar.p());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, BigDecimal bigDecimal) {
            cVar.a(bigDecimal);
        }
    }

    /* loaded from: classes.dex */
    class h0 extends c.a.b.t<AtomicInteger> {
        h0() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicInteger a2(c.a.b.y.a aVar) {
            try {
                return new AtomicInteger(aVar.l());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, AtomicInteger atomicInteger) {
            cVar.a(atomicInteger.get());
        }
    }

    /* loaded from: classes.dex */
    class i extends c.a.b.t<BigInteger> {
        i() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public BigInteger a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                return new BigInteger(aVar.p());
            } catch (NumberFormatException e2) {
                throw new c.a.b.r(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, BigInteger bigInteger) {
            cVar.a(bigInteger);
        }
    }

    /* loaded from: classes.dex */
    class i0 extends c.a.b.t<AtomicBoolean> {
        i0() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public AtomicBoolean a2(c.a.b.y.a aVar) {
            return new AtomicBoolean(aVar.j());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, AtomicBoolean atomicBoolean) {
            cVar.b(atomicBoolean.get());
        }
    }

    /* loaded from: classes.dex */
    class j extends c.a.b.t<StringBuilder> {
        j() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public StringBuilder a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return new StringBuilder(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, StringBuilder sb) {
            cVar.b(sb == null ? null : sb.toString());
        }
    }

    /* loaded from: classes.dex */
    private static final class j0<T extends Enum<T>> extends c.a.b.t<T> {

        /* renamed from: a, reason: collision with root package name */
        private final Map<String, T> f2302a = new HashMap();

        /* renamed from: b, reason: collision with root package name */
        private final Map<T, String> f2303b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    c.a.b.v.c cVar = (c.a.b.v.c) cls.getField(name).getAnnotation(c.a.b.v.c.class);
                    if (cVar != null) {
                        name = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f2302a.put(str, t);
                        }
                    }
                    this.f2302a.put(name, t);
                    this.f2303b.put(t, name);
                }
            } catch (NoSuchFieldException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public T a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return this.f2302a.get(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, T t) {
            cVar.b(t == null ? null : this.f2303b.get(t));
        }
    }

    /* loaded from: classes.dex */
    class l extends c.a.b.t<StringBuffer> {
        l() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public StringBuffer a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return new StringBuffer(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, StringBuffer stringBuffer) {
            cVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* loaded from: classes.dex */
    class m extends c.a.b.t<URL> {
        m() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public URL a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            String p = aVar.p();
            if ("null".equals(p)) {
                return null;
            }
            return new URL(p);
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, URL url) {
            cVar.b(url == null ? null : url.toExternalForm());
        }
    }

    /* renamed from: c.a.b.w.n.n$n, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0078n extends c.a.b.t<URI> {
        C0078n() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public URI a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            try {
                String p = aVar.p();
                if ("null".equals(p)) {
                    return null;
                }
                return new URI(p);
            } catch (URISyntaxException e2) {
                throw new c.a.b.k(e2);
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, URI uri) {
            cVar.b(uri == null ? null : uri.toASCIIString());
        }
    }

    /* loaded from: classes.dex */
    class o extends c.a.b.t<InetAddress> {
        o() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public InetAddress a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return InetAddress.getByName(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, InetAddress inetAddress) {
            cVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* loaded from: classes.dex */
    class p extends c.a.b.t<UUID> {
        p() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public UUID a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            return UUID.fromString(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, UUID uuid) {
            cVar.b(uuid == null ? null : uuid.toString());
        }
    }

    /* loaded from: classes.dex */
    class q extends c.a.b.t<Currency> {
        q() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public Currency a2(c.a.b.y.a aVar) {
            return Currency.getInstance(aVar.p());
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Currency currency) {
            cVar.b(currency.getCurrencyCode());
        }
    }

    /* loaded from: classes.dex */
    class s extends c.a.b.t<Calendar> {
        s() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public Calendar a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            aVar.b();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.q() != c.a.b.y.b.END_OBJECT) {
                String n = aVar.n();
                int l = aVar.l();
                if ("year".equals(n)) {
                    i = l;
                } else if ("month".equals(n)) {
                    i2 = l;
                } else if ("dayOfMonth".equals(n)) {
                    i3 = l;
                } else if ("hourOfDay".equals(n)) {
                    i4 = l;
                } else if ("minute".equals(n)) {
                    i5 = l;
                } else if ("second".equals(n)) {
                    i6 = l;
                }
            }
            aVar.e();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Calendar calendar) {
            if (calendar == null) {
                cVar.g();
                return;
            }
            cVar.b();
            cVar.a("year");
            cVar.a(calendar.get(1));
            cVar.a("month");
            cVar.a(calendar.get(2));
            cVar.a("dayOfMonth");
            cVar.a(calendar.get(5));
            cVar.a("hourOfDay");
            cVar.a(calendar.get(11));
            cVar.a("minute");
            cVar.a(calendar.get(12));
            cVar.a("second");
            cVar.a(calendar.get(13));
            cVar.d();
        }
    }

    /* loaded from: classes.dex */
    class t extends c.a.b.t<Locale> {
        t() {
        }

        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        public Locale a2(c.a.b.y.a aVar) {
            if (aVar.q() == c.a.b.y.b.NULL) {
                aVar.o();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.p(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, Locale locale) {
            cVar.b(locale == null ? null : locale.toString());
        }
    }

    /* loaded from: classes.dex */
    class u extends c.a.b.t<c.a.b.j> {
        u() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // c.a.b.t
        /* renamed from: a */
        public c.a.b.j a2(c.a.b.y.a aVar) {
            switch (b0.f2301a[aVar.q().ordinal()]) {
                case 1:
                    return new c.a.b.o(new c.a.b.w.g(aVar.p()));
                case 2:
                    return new c.a.b.o(Boolean.valueOf(aVar.j()));
                case 3:
                    return new c.a.b.o(aVar.p());
                case 4:
                    aVar.o();
                    return c.a.b.l.f2187a;
                case 5:
                    c.a.b.g gVar = new c.a.b.g();
                    aVar.a();
                    while (aVar.g()) {
                        gVar.a(a2(aVar));
                    }
                    aVar.d();
                    return gVar;
                case 6:
                    c.a.b.m mVar = new c.a.b.m();
                    aVar.b();
                    while (aVar.g()) {
                        mVar.a(aVar.n(), a2(aVar));
                    }
                    aVar.e();
                    return mVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, c.a.b.j jVar) {
            if (jVar != null && !jVar.e()) {
                if (jVar.g()) {
                    c.a.b.o c2 = jVar.c();
                    if (c2.o()) {
                        cVar.a(c2.l());
                        return;
                    } else if (c2.n()) {
                        cVar.b(c2.h());
                        return;
                    } else {
                        cVar.b(c2.m());
                        return;
                    }
                }
                if (jVar.d()) {
                    cVar.a();
                    Iterator<c.a.b.j> it = jVar.a().iterator();
                    while (it.hasNext()) {
                        a(cVar, it.next());
                    }
                    cVar.c();
                    return;
                }
                if (jVar.f()) {
                    cVar.b();
                    for (Map.Entry<String, c.a.b.j> entry : jVar.b().h()) {
                        cVar.a(entry.getKey());
                        a(cVar, entry.getValue());
                    }
                    cVar.d();
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + jVar.getClass());
            }
            cVar.g();
        }
    }

    /* loaded from: classes.dex */
    class v extends c.a.b.t<BitSet> {
        v() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
        
            if (java.lang.Integer.parseInt(r1) != 0) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        
            r5 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0067, code lost:
        
            if (r8.l() != 0) goto L23;
         */
        @Override // c.a.b.t
        /* renamed from: a, reason: avoid collision after fix types in other method */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.BitSet a2(c.a.b.y.a r8) {
            /*
                r7 = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.a()
                c.a.b.y.b r1 = r8.q()
                r2 = 0
                r3 = 0
            Le:
                c.a.b.y.b r4 = c.a.b.y.b.END_ARRAY
                if (r1 == r4) goto L75
                int[] r4 = c.a.b.w.n.n.b0.f2301a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L63
                r6 = 2
                if (r4 == r6) goto L5e
                r6 = 3
                if (r4 != r6) goto L47
                java.lang.String r1 = r8.p()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L30
                if (r1 == 0) goto L2e
                goto L69
            L2e:
                r5 = 0
                goto L69
            L30:
                c.a.b.r r8 = new c.a.b.r
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L47:
                c.a.b.r r8 = new c.a.b.r
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L5e:
                boolean r5 = r8.j()
                goto L69
            L63:
                int r1 = r8.l()
                if (r1 == 0) goto L2e
            L69:
                if (r5 == 0) goto L6e
                r0.set(r3)
            L6e:
                int r3 = r3 + 1
                c.a.b.y.b r1 = r8.q()
                goto Le
            L75:
                r8.d()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: c.a.b.w.n.n.v.a2(c.a.b.y.a):java.util.BitSet");
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, BitSet bitSet) {
            cVar.a();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                cVar.a(bitSet.get(i) ? 1L : 0L);
            }
            cVar.c();
        }
    }

    public static <TT> c.a.b.u a(Class<TT> cls, Class<TT> cls2, c.a.b.t<? super TT> tVar) {
        return new y(cls, cls2, tVar);
    }

    public static <T1> c.a.b.u b(Class<T1> cls, c.a.b.t<T1> tVar) {
        return new a0(cls, tVar);
    }
}
