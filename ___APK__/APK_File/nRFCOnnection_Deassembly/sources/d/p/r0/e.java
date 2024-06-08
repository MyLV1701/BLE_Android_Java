package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
class e extends p0 implements t0 {

    /* renamed from: f, reason: collision with root package name */
    private int f3066f;
    private int g;
    private d.o h;
    private n1 i;

    static {
        d.q.c.b(e.class);
    }

    public e(d.o oVar) {
        this.h = oVar;
    }

    private byte[] m() {
        s0[] g = this.i.g();
        int length = g.length;
        byte[] a2 = g[0].a();
        int length2 = a2.length;
        byte[] bArr = new byte[a2.length + 4];
        System.arraycopy(a2, 0, bArr, 0, a2.length);
        bArr[length2] = i1.L.a();
        bArr[length2 + 1] = 2;
        int i = length2 + 2;
        byte[] a3 = g[1].a();
        byte[] bArr2 = new byte[bArr.length + a3.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(a3, 0, bArr2, bArr.length, a3.length);
        int length3 = bArr2.length;
        byte[] bArr3 = new byte[bArr2.length + 4];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        bArr3[length3] = i1.L.a();
        bArr3[length3 + 1] = 8;
        int i2 = length3 + 2;
        if (length > 2) {
            d.p.d0.b((bArr3.length - i) - 2, bArr3, i);
            byte[] a4 = g[length - 1].a();
            byte[] bArr4 = new byte[bArr3.length + a4.length];
            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
            System.arraycopy(a4, 0, bArr4, bArr3.length, a4.length);
            int length4 = bArr4.length;
            bArr3 = new byte[bArr4.length + 4];
            System.arraycopy(bArr4, 0, bArr3, 0, bArr4.length);
            bArr3[length4] = i1.L.a();
            bArr3[length4 + 1] = 8;
            bArr3[length4 + 2] = 3;
        }
        int length5 = bArr3.length;
        byte[] bArr5 = new byte[bArr3.length + 4];
        System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
        bArr5[length5] = i1.K.a();
        bArr5[length5 + 1] = (byte) length;
        bArr5[length5 + 2] = 1;
        bArr5[length5 + 3] = 0;
        int length6 = bArr5.length - 1;
        if (length < 3) {
            d.p.d0.b((length6 - i) - 5, bArr5, i);
        }
        d.p.d0.b((length6 - i2) - 2, bArr5, i2);
        return bArr5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(n1 n1Var) {
        this.i = n1Var;
        this.f3066f |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.p0
    public int h() {
        return 3;
    }

    public boolean j() {
        return (this.f3066f & 4) != 0;
    }

    public boolean k() {
        return (this.f3066f & 2) != 0;
    }

    public boolean l() {
        return (this.f3066f & 16) != 0;
    }

    public e(c1 c1Var, d.o oVar) {
        this.h = oVar;
        if (c1Var.a(this.h) == x.f3122f) {
            this.f3066f |= 16;
        } else if (c1Var.a(this.h) == x.h) {
            this.f3066f |= 2;
        }
    }

    public int a(byte[] bArr, int i) {
        this.f3066f = bArr[i];
        this.g = d.p.d0.a(bArr[i + 1], bArr[i + 2]);
        if (j()) {
            return ((this.g + 1) * 2) + 3;
        }
        return 3;
    }

    @Override // d.p.r0.p0
    public void a(Stack stack) {
        int i = this.f3066f;
        if ((i & 16) != 0) {
            b((s0) stack.pop());
        } else if ((i & 2) != 0) {
            b((s0) stack.pop());
        }
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        int i = this.f3066f;
        if ((i & 16) != 0) {
            s0[] g = g();
            stringBuffer.append(x.f3122f.a(this.h));
            stringBuffer.append('(');
            g[0].a(stringBuffer);
            stringBuffer.append(')');
            return;
        }
        if ((i & 2) != 0) {
            stringBuffer.append(x.h.a(this.h));
            stringBuffer.append('(');
            s0[] g2 = this.i.g();
            for (int i2 = 0; i2 < g2.length - 1; i2++) {
                g2[i2].a(stringBuffer);
                stringBuffer.append(',');
            }
            g2[g2.length - 1].a(stringBuffer);
            stringBuffer.append(')');
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] bArr = new byte[0];
        if (!l()) {
            return k() ? m() : bArr;
        }
        s0[] g = g();
        int length = g.length - 1;
        while (length >= 0) {
            byte[] a2 = g[length].a();
            byte[] bArr2 = new byte[bArr.length + a2.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(a2, 0, bArr2, bArr.length, a2.length);
            length--;
            bArr = bArr2;
        }
        byte[] bArr3 = new byte[bArr.length + 4];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        bArr3[bArr.length] = i1.L.a();
        bArr3[bArr.length + 1] = 16;
        return bArr3;
    }
}
