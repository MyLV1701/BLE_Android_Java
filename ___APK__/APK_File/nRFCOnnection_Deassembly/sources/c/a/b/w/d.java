package c.a.b.w;

import c.a.b.t;
import c.a.b.u;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.ble.error.GattError;

/* loaded from: classes.dex */
public final class d implements u, Cloneable {
    public static final d h = new d();

    /* renamed from: e, reason: collision with root package name */
    private boolean f2215e;

    /* renamed from: b, reason: collision with root package name */
    private double f2212b = -1.0d;

    /* renamed from: c, reason: collision with root package name */
    private int f2213c = GattError.GATT_PENDING;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2214d = true;

    /* renamed from: f, reason: collision with root package name */
    private List<c.a.b.a> f2216f = Collections.emptyList();
    private List<c.a.b.a> g = Collections.emptyList();

    private boolean b(Class<?> cls, boolean z) {
        Iterator<c.a.b.a> it = (z ? this.f2216f : this.g).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean c(Class<?> cls) {
        return cls.isMemberClass() && !d(cls);
    }

    private boolean d(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    @Override // c.a.b.u
    public <T> t<T> a(c.a.b.e eVar, c.a.b.x.a<T> aVar) {
        Class<? super T> a2 = aVar.a();
        boolean a3 = a(a2);
        boolean z = a3 || b(a2, true);
        boolean z2 = a3 || b(a2, false);
        if (z || z2) {
            return new a(z2, z, eVar, aVar);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public d m3clone() {
        try {
            return (d) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes.dex */
    class a<T> extends t<T> {

        /* renamed from: a, reason: collision with root package name */
        private t<T> f2217a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f2218b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f2219c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ c.a.b.e f2220d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ c.a.b.x.a f2221e;

        a(boolean z, boolean z2, c.a.b.e eVar, c.a.b.x.a aVar) {
            this.f2218b = z;
            this.f2219c = z2;
            this.f2220d = eVar;
            this.f2221e = aVar;
        }

        private t<T> b() {
            t<T> tVar = this.f2217a;
            if (tVar != null) {
                return tVar;
            }
            t<T> a2 = this.f2220d.a(d.this, this.f2221e);
            this.f2217a = a2;
            return a2;
        }

        @Override // c.a.b.t
        /* renamed from: a */
        public T a2(c.a.b.y.a aVar) {
            if (this.f2218b) {
                aVar.r();
                return null;
            }
            return b().a2(aVar);
        }

        @Override // c.a.b.t
        public void a(c.a.b.y.c cVar, T t) {
            if (this.f2219c) {
                cVar.g();
            } else {
                b().a(cVar, t);
            }
        }
    }

    private boolean b(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public boolean a(Field field, boolean z) {
        c.a.b.v.a aVar;
        if ((this.f2213c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f2212b != -1.0d && !a((c.a.b.v.d) field.getAnnotation(c.a.b.v.d.class), (c.a.b.v.e) field.getAnnotation(c.a.b.v.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f2215e && ((aVar = (c.a.b.v.a) field.getAnnotation(c.a.b.v.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f2214d && c(field.getType())) || b(field.getType())) {
            return true;
        }
        List<c.a.b.a> list = z ? this.f2216f : this.g;
        if (list.isEmpty()) {
            return false;
        }
        c.a.b.b bVar = new c.a.b.b(field);
        Iterator<c.a.b.a> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().a(bVar)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        if (this.f2212b == -1.0d || a((c.a.b.v.d) cls.getAnnotation(c.a.b.v.d.class), (c.a.b.v.e) cls.getAnnotation(c.a.b.v.e.class))) {
            return (!this.f2214d && c(cls)) || b(cls);
        }
        return true;
    }

    public boolean a(Class<?> cls, boolean z) {
        return a(cls) || b(cls, z);
    }

    private boolean a(c.a.b.v.d dVar, c.a.b.v.e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(c.a.b.v.d dVar) {
        return dVar == null || dVar.value() <= this.f2212b;
    }

    private boolean a(c.a.b.v.e eVar) {
        return eVar == null || eVar.value() > this.f2212b;
    }
}
