package f.a.e;

import f.a.f.e;
import java.util.Queue;

/* loaded from: classes.dex */
public class a implements f.a.b {

    /* renamed from: b, reason: collision with root package name */
    String f3438b;

    /* renamed from: c, reason: collision with root package name */
    e f3439c;

    /* renamed from: d, reason: collision with root package name */
    Queue<d> f3440d;

    public a(e eVar, Queue<d> queue) {
        this.f3439c = eVar;
        this.f3438b = eVar.getName();
        this.f3440d = queue;
    }

    @Override // f.a.b
    public void a(String str, Object obj, Object obj2) {
        a(b.DEBUG, (f.a.d) null, str, obj, obj2);
    }

    @Override // f.a.b
    public void b(String str, Object obj) {
        a(b.TRACE, (f.a.d) null, str, obj);
    }

    @Override // f.a.b
    public void c(String str, Object obj) {
        a(b.DEBUG, (f.a.d) null, str, obj);
    }

    @Override // f.a.b
    public void d(String str) {
        a(b.TRACE, (f.a.d) null, str, (Throwable) null);
    }

    @Override // f.a.b
    public void e(String str) {
        a(b.DEBUG, (f.a.d) null, str, (Throwable) null);
    }

    @Override // f.a.b
    public String getName() {
        return this.f3438b;
    }

    @Override // f.a.b
    public void a(String str) {
        a(b.ERROR, (f.a.d) null, str, (Throwable) null);
    }

    @Override // f.a.b
    public void b(String str, Object obj, Object obj2) {
        a(b.TRACE, (f.a.d) null, str, obj, obj2);
    }

    @Override // f.a.b
    public void c(String str) {
        a(b.WARN, (f.a.d) null, str, (Throwable) null);
    }

    @Override // f.a.b
    public void a(String str, Object obj) {
        a(b.ERROR, (f.a.d) null, str, obj);
    }

    @Override // f.a.b
    public void b(String str) {
        a(b.INFO, (f.a.d) null, str, (Throwable) null);
    }

    @Override // f.a.b
    public void c(String str, Object obj, Object obj2) {
        a(b.ERROR, (f.a.d) null, str, obj, obj2);
    }

    @Override // f.a.b
    public void a(String str, Throwable th) {
        a(b.ERROR, (f.a.d) null, str, th);
    }

    private void a(b bVar, f.a.d dVar, String str, Throwable th) {
        a(bVar, dVar, str, (Object[]) null, th);
    }

    private void a(b bVar, f.a.d dVar, String str, Object obj) {
        a(bVar, dVar, str, new Object[]{obj}, (Throwable) null);
    }

    private void a(b bVar, f.a.d dVar, String str, Object obj, Object obj2) {
        if (obj2 instanceof Throwable) {
            a(bVar, dVar, str, new Object[]{obj}, (Throwable) obj2);
        } else {
            a(bVar, dVar, str, new Object[]{obj, obj2}, (Throwable) null);
        }
    }

    private void a(b bVar, f.a.d dVar, String str, Object[] objArr, Throwable th) {
        d dVar2 = new d();
        dVar2.a(System.currentTimeMillis());
        dVar2.a(bVar);
        dVar2.a(this.f3439c);
        dVar2.a(this.f3438b);
        dVar2.a(dVar);
        dVar2.b(str);
        dVar2.c(Thread.currentThread().getName());
        dVar2.a(objArr);
        dVar2.a(th);
        this.f3440d.add(dVar2);
    }
}
