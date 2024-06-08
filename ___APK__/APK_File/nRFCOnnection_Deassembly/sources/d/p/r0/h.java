package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
class h extends p0 implements t0 {

    /* renamed from: f, reason: collision with root package name */
    private x f3072f;
    private d.o g;

    static {
        d.q.c.b(h.class);
    }

    public h(d.o oVar) {
        this.g = oVar;
    }

    public int a(byte[] bArr, int i) {
        int a2 = d.p.d0.a(bArr[i], bArr[i + 1]);
        this.f3072f = x.a(a2);
        d.q.a.a(this.f3072f != x.i, "function code " + a2);
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.p0
    public int h() {
        return 3;
    }

    public h(x xVar, d.o oVar) {
        this.f3072f = xVar;
        this.g = oVar;
    }

    @Override // d.p.r0.p0
    public void a(Stack stack) {
        s0[] s0VarArr = new s0[this.f3072f.b()];
        for (int b2 = this.f3072f.b() - 1; b2 >= 0; b2--) {
            s0VarArr[b2] = (s0) stack.pop();
        }
        for (int i = 0; i < this.f3072f.b(); i++) {
            b(s0VarArr[i]);
        }
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append(this.f3072f.a(this.g));
        stringBuffer.append('(');
        int b2 = this.f3072f.b();
        if (b2 > 0) {
            s0[] g = g();
            g[0].a(stringBuffer);
            for (int i = 1; i < b2; i++) {
                stringBuffer.append(',');
                g[i].a(stringBuffer);
            }
        }
        stringBuffer.append(')');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        s0[] g = g();
        byte[] bArr = new byte[0];
        int i = 0;
        while (i < g.length) {
            byte[] a2 = g[i].a();
            byte[] bArr2 = new byte[bArr.length + a2.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(a2, 0, bArr2, bArr.length, a2.length);
            i++;
            bArr = bArr2;
        }
        byte[] bArr3 = new byte[bArr.length + 3];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        bArr3[bArr.length] = !f() ? i1.J.a() : i1.J.b();
        d.p.d0.b(this.f3072f.a(), bArr3, bArr.length + 1);
        return bArr3;
    }
}
