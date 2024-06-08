package d.t.o;

import java.io.OutputStream;

/* loaded from: classes.dex */
public final class e0 {

    /* renamed from: a, reason: collision with root package name */
    private a0 f3274a;

    /* renamed from: b, reason: collision with root package name */
    private OutputStream f3275b;

    /* renamed from: c, reason: collision with root package name */
    private int f3276c;

    /* renamed from: d, reason: collision with root package name */
    private int f3277d;

    /* renamed from: e, reason: collision with root package name */
    private d.o f3278e;

    /* renamed from: f, reason: collision with root package name */
    d.s.a.c f3279f;

    static {
        d.q.c.b(e0.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e0(OutputStream outputStream, d.o oVar, d.s.a.c cVar) {
        this.f3275b = outputStream;
        this.f3278e = oVar;
        this.f3279f = cVar;
        b();
    }

    private void b() {
        if (this.f3278e.p()) {
            this.f3274a = new f0(this.f3278e.o());
            return;
        }
        this.f3276c = this.f3278e.j();
        this.f3277d = this.f3278e.a();
        this.f3274a = new y0(this.f3276c, this.f3277d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        a0 a0Var = this.f3274a;
        new n(a0Var, a0Var.getPosition(), this.f3275b, this.f3279f).a();
        this.f3275b.flush();
        this.f3274a.close();
        if (z) {
            this.f3275b.close();
        }
        this.f3274a = null;
        if (this.f3278e.h()) {
            return;
        }
        System.gc();
    }

    public void a(d.p.h hVar) {
        this.f3274a.a(hVar.a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f3274a.getPosition();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(byte[] bArr, int i) {
        this.f3274a.a(bArr, i);
    }
}
