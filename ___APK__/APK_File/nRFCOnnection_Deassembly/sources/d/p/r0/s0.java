package d.p.r0;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class s0 {

    /* renamed from: a, reason: collision with root package name */
    private s0 f3104a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3105b = false;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3106c = false;

    /* renamed from: d, reason: collision with root package name */
    private r0 f3107d = r0.f3101a;

    static {
        d.q.c.b(s0.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(s0 s0Var) {
        this.f3104a = s0Var;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(StringBuffer stringBuffer);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] a();

    /* JADX INFO: Access modifiers changed from: protected */
    public final r0 b() {
        return this.f3107d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        return this.f3105b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        this.f3106c = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e() {
        this.f3105b = true;
        s0 s0Var = this.f3104a;
        if (s0Var == null || s0Var.c()) {
            return;
        }
        this.f3104a.e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean f() {
        return this.f3106c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(r0 r0Var) {
        this.f3107d = r0Var;
    }
}
