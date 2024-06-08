package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
class j1 implements u0 {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3083a;

    /* renamed from: b, reason: collision with root package name */
    private d.a f3084b;

    /* renamed from: d, reason: collision with root package name */
    private s0 f3086d;

    /* renamed from: f, reason: collision with root package name */
    private t f3088f;
    private d.p.l0 g;
    private d.o h;
    private r0 i;

    /* renamed from: c, reason: collision with root package name */
    private int f3085c = 0;

    /* renamed from: e, reason: collision with root package name */
    private Stack f3087e = new Stack();

    static {
        d.q.c.b(j1.class);
    }

    public j1(byte[] bArr, d.a aVar, t tVar, d.p.l0 l0Var, d.o oVar, r0 r0Var) {
        this.f3083a = bArr;
        this.f3084b = aVar;
        this.f3088f = tVar;
        this.g = l0Var;
        this.h = oVar;
        this.i = r0Var;
        d.q.a.a(this.g != null);
    }

    private void a(int i) {
        e eVar;
        Stack stack = new Stack();
        int i2 = this.f3085c + i;
        while (true) {
            int i3 = this.f3085c;
            if (i3 >= i2) {
                return;
            }
            byte b2 = this.f3083a[i3];
            this.f3085c = i3 + 1;
            i1 a2 = i1.a(b2);
            i1 i1Var = i1.N;
            if (a2 != i1Var) {
                d.q.a.a(a2 != i1Var);
                if (a2 == i1.f3076c) {
                    j jVar = new j(this.f3084b);
                    int i4 = this.f3085c;
                    this.f3085c = i4 + jVar.a(this.f3083a, i4);
                    this.f3087e.push(jVar);
                } else if (a2 == i1.k) {
                    k kVar = new k();
                    int i5 = this.f3085c;
                    this.f3085c = i5 + kVar.a(this.f3083a, i5);
                    this.f3087e.push(kVar);
                } else if (a2 == i1.g) {
                    s sVar = new s();
                    int i6 = this.f3085c;
                    this.f3085c = i6 + sVar.a(this.f3083a, i6);
                    this.f3087e.push(sVar);
                } else if (a2 == i1.l) {
                    a1 a1Var = new a1(this.f3084b);
                    int i7 = this.f3085c;
                    this.f3085c = i7 + a1Var.a(this.f3083a, i7);
                    this.f3087e.push(a1Var);
                } else if (a2 == i1.f3077d) {
                    i iVar = new i(this.f3084b, this.f3088f);
                    int i8 = this.f3085c;
                    this.f3085c = i8 + iVar.a(this.f3083a, i8);
                    this.f3087e.push(iVar);
                } else if (a2 == i1.o) {
                    c cVar = new c();
                    int i9 = this.f3085c;
                    this.f3085c = i9 + cVar.a(this.f3083a, i9);
                    this.f3087e.push(cVar);
                } else if (a2 == i1.m) {
                    z0 z0Var = new z0(this.f3084b);
                    int i10 = this.f3085c;
                    this.f3085c = i10 + z0Var.a(this.f3083a, i10);
                    this.f3087e.push(z0Var);
                } else if (a2 == i1.r) {
                    b bVar = new b(this.f3088f);
                    int i11 = this.f3085c;
                    this.f3085c = i11 + bVar.a(this.f3083a, i11);
                    this.f3087e.push(bVar);
                } else if (a2 == i1.q) {
                    j0 j0Var = new j0();
                    int i12 = this.f3085c;
                    this.f3085c = i12 + j0Var.a(this.f3083a, i12);
                    j0Var.a(this.i);
                    this.f3087e.push(j0Var);
                } else if (a2 == i1.p) {
                    k0 k0Var = new k0(this.g);
                    int i13 = this.f3085c;
                    this.f3085c = i13 + k0Var.a(this.f3083a, i13);
                    k0Var.a(this.i);
                    this.f3087e.push(k0Var);
                } else if (a2 == i1.i) {
                    b0 b0Var = new b0();
                    int i14 = this.f3085c;
                    this.f3085c = i14 + b0Var.a(this.f3083a, i14);
                    this.f3087e.push(b0Var);
                } else if (a2 == i1.j) {
                    q qVar = new q();
                    int i15 = this.f3085c;
                    this.f3085c = i15 + qVar.a(this.f3083a, i15);
                    this.f3087e.push(qVar);
                } else if (a2 == i1.h) {
                    g gVar = new g();
                    int i16 = this.f3085c;
                    this.f3085c = i16 + gVar.a(this.f3083a, i16);
                    this.f3087e.push(gVar);
                } else if (a2 == i1.f3079f) {
                    f1 f1Var = new f1(this.h);
                    int i17 = this.f3085c;
                    this.f3085c = i17 + f1Var.a(this.f3083a, i17);
                    this.f3087e.push(f1Var);
                } else if (a2 == i1.f3078e) {
                    h0 h0Var = new h0();
                    int i18 = this.f3085c;
                    this.f3085c = i18 + h0Var.a(this.f3083a, i18);
                    this.f3087e.push(h0Var);
                } else if (a2 == i1.s) {
                    m1 m1Var = new m1();
                    int i19 = this.f3085c;
                    this.f3085c = i19 + m1Var.a(this.f3083a, i19);
                    a(m1Var);
                } else if (a2 == i1.t) {
                    k1 k1Var = new k1();
                    int i20 = this.f3085c;
                    this.f3085c = i20 + k1Var.a(this.f3083a, i20);
                    a(k1Var);
                } else if (a2 == i1.u) {
                    v0 v0Var = new v0();
                    int i21 = this.f3085c;
                    this.f3085c = i21 + v0Var.a(this.f3083a, i21);
                    a(v0Var);
                } else if (a2 == i1.x) {
                    h1 h1Var = new h1();
                    int i22 = this.f3085c;
                    this.f3085c = i22 + h1Var.a(this.f3083a, i22);
                    a(h1Var);
                } else if (a2 == i1.w) {
                    a aVar = new a();
                    int i23 = this.f3085c;
                    this.f3085c = i23 + aVar.a(this.f3083a, i23);
                    a(aVar);
                } else if (a2 == i1.y) {
                    i0 i0Var = new i0();
                    int i24 = this.f3085c;
                    this.f3085c = i24 + i0Var.a(this.f3083a, i24);
                    a(i0Var);
                } else if (a2 == i1.z) {
                    p pVar = new p();
                    int i25 = this.f3085c;
                    this.f3085c = i25 + pVar.a(this.f3083a, i25);
                    a(pVar);
                } else if (a2 == i1.B) {
                    o oVar = new o();
                    int i26 = this.f3085c;
                    this.f3085c = i26 + oVar.a(this.f3083a, i26);
                    a(oVar);
                } else if (a2 == i1.A) {
                    x0 x0Var = new x0();
                    int i27 = this.f3085c;
                    this.f3085c = i27 + x0Var.a(this.f3083a, i27);
                    a(x0Var);
                } else if (a2 == i1.C) {
                    d0 d0Var = new d0();
                    int i28 = this.f3085c;
                    this.f3085c = i28 + d0Var.a(this.f3083a, i28);
                    a(d0Var);
                } else if (a2 == i1.D) {
                    c0 c0Var = new c0();
                    int i29 = this.f3085c;
                    this.f3085c = i29 + c0Var.a(this.f3083a, i29);
                    a(c0Var);
                } else if (a2 == i1.G) {
                    a0 a0Var = new a0();
                    int i30 = this.f3085c;
                    this.f3085c = i30 + a0Var.a(this.f3083a, i30);
                    a(a0Var);
                } else if (a2 == i1.F) {
                    z zVar = new z();
                    int i31 = this.f3085c;
                    this.f3085c = i31 + zVar.a(this.f3083a, i31);
                    a(zVar);
                } else if (a2 == i1.H) {
                    l0 l0Var = new l0();
                    int i32 = this.f3085c;
                    this.f3085c = i32 + l0Var.a(this.f3083a, i32);
                    a(l0Var);
                } else if (a2 == i1.E) {
                    r rVar = new r();
                    int i33 = this.f3085c;
                    this.f3085c = i33 + rVar.a(this.f3083a, i33);
                    a(rVar);
                } else if (a2 == i1.v) {
                    q0 q0Var = new q0();
                    int i34 = this.f3085c;
                    this.f3085c = i34 + q0Var.a(this.f3083a, i34);
                    a(q0Var);
                } else if (a2 == i1.L) {
                    e eVar2 = new e(this.h);
                    int i35 = this.f3085c;
                    this.f3085c = i35 + eVar2.a(this.f3083a, i35);
                    if (eVar2.l()) {
                        a(eVar2);
                    } else if (eVar2.k()) {
                        stack.push(eVar2);
                    }
                } else if (a2 == i1.J) {
                    h hVar = new h(this.h);
                    int i36 = this.f3085c;
                    this.f3085c = i36 + hVar.a(this.f3083a, i36);
                    a(hVar);
                } else if (a2 == i1.K) {
                    n1 n1Var = new n1(this.h);
                    int i37 = this.f3085c;
                    this.f3085c = i37 + n1Var.a(this.f3083a, i37);
                    if (n1Var.j() != x.f3121e) {
                        a(n1Var);
                    } else {
                        n1Var.a(this.f3087e);
                        if (stack.empty()) {
                            eVar = new e(this.h);
                        } else {
                            eVar = (e) stack.pop();
                        }
                        eVar.a(n1Var);
                        this.f3087e.push(eVar);
                    }
                } else if (a2 == i1.M) {
                    a(new f0());
                } else if (a2 == i1.n) {
                    a(new e0());
                }
            } else {
                throw new v(v.f3113b, b2);
            }
        }
    }

    @Override // d.p.r0.u0
    public void b() {
        a(this.f3083a.length);
        this.f3086d = (s0) this.f3087e.pop();
        d.q.a.a(this.f3087e.empty());
    }

    @Override // d.p.r0.u0
    public String c() {
        StringBuffer stringBuffer = new StringBuffer();
        this.f3086d.a(stringBuffer);
        return stringBuffer.toString();
    }

    private void a(g1 g1Var) {
        int i = this.f3085c;
        this.f3085c = i + g1Var.a(this.f3083a, i);
        Stack stack = this.f3087e;
        this.f3087e = new Stack();
        a(g1Var.g());
        s0[] s0VarArr = new s0[this.f3087e.size()];
        int i2 = 0;
        while (!this.f3087e.isEmpty()) {
            s0VarArr[i2] = (s0) this.f3087e.pop();
            i2++;
        }
        g1Var.a(s0VarArr);
        this.f3087e = stack;
        this.f3087e.push(g1Var);
    }

    private void a(p0 p0Var) {
        p0Var.a(this.f3087e);
        this.f3087e.push(p0Var);
    }

    @Override // d.p.r0.u0
    public byte[] a() {
        return this.f3086d.a();
    }
}
