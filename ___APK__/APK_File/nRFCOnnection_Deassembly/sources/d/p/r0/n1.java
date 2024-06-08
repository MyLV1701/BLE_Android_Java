package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
class n1 extends p0 implements t0 {

    /* renamed from: f, reason: collision with root package name */
    private x f3091f;
    private int g;
    private boolean h = true;
    private d.o i;

    static {
        d.q.c.b(n1.class);
    }

    public n1(d.o oVar) {
        this.i = oVar;
    }

    private void k() {
        if (this.f3091f == x.g) {
            s0[] g = g();
            for (int length = g.length - 1; length >= 0; length--) {
                if (g[length] instanceof c) {
                    g[length].d();
                }
            }
        }
    }

    public int a(byte[] bArr, int i) {
        this.g = bArr[i];
        int a2 = d.p.d0.a(bArr[i + 1], bArr[i + 2]);
        this.f3091f = x.a(a2);
        if (this.f3091f != x.i) {
            return 3;
        }
        throw new v(v.f3114c, a2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.p0
    public int h() {
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x j() {
        return this.f3091f;
    }

    public n1(x xVar, int i, d.o oVar) {
        this.f3091f = xVar;
        this.g = i;
        this.i = oVar;
    }

    @Override // d.p.r0.p0
    public void a(Stack stack) {
        int i = this.g;
        s0[] s0VarArr = new s0[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            s0VarArr[i2] = (s0) stack.pop();
        }
        for (int i3 = 0; i3 < this.g; i3++) {
            b(s0VarArr[i3]);
        }
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        stringBuffer.append(this.f3091f.a(this.i));
        stringBuffer.append('(');
        if (this.g > 0) {
            s0[] g = g();
            if (this.h) {
                g[0].a(stringBuffer);
                for (int i = 1; i < this.g; i++) {
                    stringBuffer.append(',');
                    g[i].a(stringBuffer);
                }
            } else {
                g[this.g - 1].a(stringBuffer);
                for (int i2 = this.g - 2; i2 >= 0; i2--) {
                    stringBuffer.append(',');
                    g[i2].a(stringBuffer);
                }
            }
        }
        stringBuffer.append(')');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        k();
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
        byte[] bArr3 = new byte[bArr.length + 4];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        bArr3[bArr.length] = !f() ? i1.K.a() : i1.K.b();
        bArr3[bArr.length + 1] = (byte) this.g;
        d.p.d0.b(this.f3091f.a(), bArr3, bArr.length + 2);
        return bArr3;
    }
}
