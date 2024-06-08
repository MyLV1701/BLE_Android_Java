package f.a.f;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Queue;

/* loaded from: classes.dex */
public class e implements f.a.b {

    /* renamed from: b, reason: collision with root package name */
    private final String f3448b;

    /* renamed from: c, reason: collision with root package name */
    private volatile f.a.b f3449c;

    /* renamed from: d, reason: collision with root package name */
    private Boolean f3450d;

    /* renamed from: e, reason: collision with root package name */
    private Method f3451e;

    /* renamed from: f, reason: collision with root package name */
    private f.a.e.a f3452f;
    private Queue<f.a.e.d> g;
    private final boolean h;

    public e(String str, Queue<f.a.e.d> queue, boolean z) {
        this.f3448b = str;
        this.g = queue;
        this.h = z;
    }

    @Override // f.a.b
    public void a(String str, Object obj, Object obj2) {
        a().a(str, obj, obj2);
    }

    @Override // f.a.b
    public void b(String str, Object obj) {
        a().b(str, obj);
    }

    @Override // f.a.b
    public void c(String str, Object obj) {
        a().c(str, obj);
    }

    @Override // f.a.b
    public void d(String str) {
        a().d(str);
    }

    @Override // f.a.b
    public void e(String str) {
        a().e(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && e.class == obj.getClass() && this.f3448b.equals(((e) obj).f3448b);
    }

    @Override // f.a.b
    public String getName() {
        return this.f3448b;
    }

    public int hashCode() {
        return this.f3448b.hashCode();
    }

    private f.a.b e() {
        if (this.f3452f == null) {
            this.f3452f = new f.a.e.a(this, this.g);
        }
        return this.f3452f;
    }

    @Override // f.a.b
    public void a(String str) {
        a().a(str);
    }

    @Override // f.a.b
    public void b(String str, Object obj, Object obj2) {
        a().b(str, obj, obj2);
    }

    @Override // f.a.b
    public void c(String str) {
        a().c(str);
    }

    public boolean d() {
        return this.f3449c == null;
    }

    @Override // f.a.b
    public void a(String str, Object obj) {
        a().a(str, obj);
    }

    @Override // f.a.b
    public void b(String str) {
        a().b(str);
    }

    @Override // f.a.b
    public void c(String str, Object obj, Object obj2) {
        a().c(str, obj, obj2);
    }

    @Override // f.a.b
    public void a(String str, Throwable th) {
        a().a(str, th);
    }

    public boolean b() {
        Boolean bool = this.f3450d;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.f3451e = this.f3449c.getClass().getMethod("log", f.a.e.c.class);
            this.f3450d = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.f3450d = Boolean.FALSE;
        }
        return this.f3450d.booleanValue();
    }

    public boolean c() {
        return this.f3449c instanceof b;
    }

    f.a.b a() {
        if (this.f3449c != null) {
            return this.f3449c;
        }
        if (this.h) {
            return b.f3447b;
        }
        return e();
    }

    public void a(f.a.b bVar) {
        this.f3449c = bVar;
    }

    public void a(f.a.e.c cVar) {
        if (b()) {
            try {
                this.f3451e.invoke(this.f3449c, cVar);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }
}
