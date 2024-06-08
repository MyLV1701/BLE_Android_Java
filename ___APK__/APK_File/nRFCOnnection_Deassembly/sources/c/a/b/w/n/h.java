package c.a.b.w.n;

import c.a.b.t;
import c.a.b.u;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class h extends t<Object> {

    /* renamed from: b, reason: collision with root package name */
    public static final u f2262b = new a();

    /* renamed from: a, reason: collision with root package name */
    private final c.a.b.e f2263a;

    /* loaded from: classes.dex */
    class a implements u {
        a() {
        }

        @Override // c.a.b.u
        public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
            if (aVar.a() == Object.class) {
                return new h(eVar);
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    static /* synthetic */ class b {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2264a = new int[c.a.b.y.b.values().length];

        static {
            try {
                f2264a[c.a.b.y.b.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2264a[c.a.b.y.b.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2264a[c.a.b.y.b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2264a[c.a.b.y.b.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2264a[c.a.b.y.b.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2264a[c.a.b.y.b.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    h(c.a.b.e eVar) {
        this.f2263a = eVar;
    }

    @Override // c.a.b.t
    /* renamed from: a */
    public Object a2(c.a.b.y.a aVar) {
        switch (b.f2264a[aVar.q().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.g()) {
                    arrayList.add(a2(aVar));
                }
                aVar.d();
                return arrayList;
            case 2:
                c.a.b.w.h hVar = new c.a.b.w.h();
                aVar.b();
                while (aVar.g()) {
                    hVar.put(aVar.n(), a2(aVar));
                }
                aVar.e();
                return hVar;
            case 3:
                return aVar.p();
            case 4:
                return Double.valueOf(aVar.k());
            case 5:
                return Boolean.valueOf(aVar.j());
            case 6:
                aVar.o();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // c.a.b.t
    public void a(c.a.b.y.c cVar, Object obj) {
        if (obj == null) {
            cVar.g();
            return;
        }
        t a2 = this.f2263a.a(obj.getClass());
        if (a2 instanceof h) {
            cVar.b();
            cVar.d();
        } else {
            a2.a(cVar, obj);
        }
    }
}
