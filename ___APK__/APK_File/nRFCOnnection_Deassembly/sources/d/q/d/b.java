package d.q.d;

import d.q.c;

/* loaded from: classes.dex */
public class b extends c {

    /* renamed from: b, reason: collision with root package name */
    private boolean f3152b = false;

    @Override // d.q.c
    protected c a(Class cls) {
        return this;
    }

    @Override // d.q.c
    public void a(Object obj) {
        System.err.print("Error: ");
        System.err.println(obj);
    }

    @Override // d.q.c
    public void b(Object obj) {
        if (this.f3152b) {
            return;
        }
        System.err.print("Warning:  ");
        System.err.println(obj);
    }

    @Override // d.q.c
    public void a(Object obj, Throwable th) {
        if (this.f3152b) {
            return;
        }
        System.err.print("Warning:  ");
        System.err.println(obj);
        th.printStackTrace();
    }

    @Override // d.q.c
    public void a(boolean z) {
        this.f3152b = z;
    }
}
