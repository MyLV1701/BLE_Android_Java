package d.p.q0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class u {

    /* renamed from: a, reason: collision with root package name */
    private v f3032a;

    static {
        d.q.c.b(u.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public u(v vVar) {
        this.f3032a = vVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(boolean z) {
        this.f3032a.a(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(int i) {
        this.f3032a.b(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] b();

    /* JADX INFO: Access modifiers changed from: protected */
    public final x c() {
        return this.f3032a.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int d() {
        return this.f3032a.c();
    }

    public int e() {
        return this.f3032a.d() + 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int f() {
        return this.f3032a.e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int g() {
        return this.f3032a.f();
    }

    public w h() {
        return this.f3032a.g();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i) {
        this.f3032a.a(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public u(w wVar) {
        this.f3032a = new v(wVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final byte[] a(byte[] bArr) {
        return this.f3032a.a(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        return this.f3032a.a();
    }
}
