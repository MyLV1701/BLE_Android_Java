package d.p.q0;

/* loaded from: classes.dex */
public class d implements d.p.h, x {

    /* renamed from: a, reason: collision with root package name */
    private z f2956a;

    /* renamed from: b, reason: collision with root package name */
    private b0 f2957b;

    /* renamed from: c, reason: collision with root package name */
    private int f2958c;

    /* renamed from: d, reason: collision with root package name */
    private int f2959d;

    /* renamed from: e, reason: collision with root package name */
    private d.s.a.d f2960e;

    /* renamed from: f, reason: collision with root package name */
    private o f2961f;
    private int g;
    private byte[] h;
    private boolean i;

    static {
        d.q.c.b(d.class);
    }

    private void f() {
        d.s.a.d dVar = this.f2960e;
        int i = this.f2958c;
        this.h = dVar.a(i, this.f2959d - i);
        this.i = true;
    }

    @Override // d.p.h
    public byte[] a() {
        if (!this.i) {
            f();
        }
        return this.h;
    }

    @Override // d.p.q0.x
    public byte[] b() {
        return this.f2956a.t().a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public z c() {
        return this.f2956a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b0 d() {
        return this.f2957b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public t e() {
        return this.f2961f.a(this.g);
    }

    public void a(d.p.c0 c0Var, d.p.c0 c0Var2, d.p.c0 c0Var3) {
        if (!this.i) {
            f();
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.h;
            if (i >= bArr.length) {
                return;
            }
            int a2 = d.p.d0.a(bArr[i], bArr[i + 1]);
            byte[] bArr2 = this.h;
            int a3 = d.p.d0.a(bArr2[i + 2], bArr2[i + 3]);
            d.p.k0 a4 = d.p.k0.a(a2);
            if (a4 == d.p.k0.L0) {
                byte[] bArr3 = this.h;
                int i2 = i + 4;
                d.p.d0.b(c0Var2.a(d.p.d0.a(bArr3[i2], bArr3[i + 5])), this.h, i2);
            } else if (a4 == d.p.k0.N0) {
                byte[] bArr4 = this.h;
                int i3 = i + 12;
                d.p.d0.b(c0Var2.a(d.p.d0.a(bArr4[i3], bArr4[i + 13])), this.h, i3);
            } else if (a4 == d.p.k0.M0) {
                byte[] bArr5 = this.h;
                int i4 = i + 4;
                d.p.d0.b(c0Var3.a(d.p.d0.a(bArr5[i4], bArr5[i + 5])), this.h, i4);
            } else if (a4 == d.p.k0.O0) {
                byte[] bArr6 = this.h;
                int a5 = d.p.d0.a(bArr6[i + 4], bArr6[i + 5]);
                int i5 = i + 6;
                for (int i6 = 0; i6 < a5; i6++) {
                    byte[] bArr7 = this.h;
                    int i7 = i5 + 2;
                    d.p.d0.b(c0Var2.a(d.p.d0.a(bArr7[i7], bArr7[i5 + 3])), this.h, i7);
                    i5 += 4;
                }
            }
            i += a3 + 4;
        }
    }
}
