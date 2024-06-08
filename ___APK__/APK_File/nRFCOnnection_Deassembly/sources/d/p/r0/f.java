package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
abstract class f extends p0 implements t0 {
    static {
        d.q.c.b(f.class);
    }

    public int a(byte[] bArr, int i) {
        return 0;
    }

    @Override // d.p.r0.p0
    public void a(Stack stack) {
        s0 s0Var = (s0) stack.pop();
        s0 s0Var2 = (s0) stack.pop();
        b(s0Var);
        b(s0Var2);
    }

    abstract String j();

    abstract i1 k();

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        s0[] g = g();
        g[1].a(stringBuffer);
        stringBuffer.append(j());
        g[0].a(stringBuffer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        s0[] g = g();
        byte[] bArr = new byte[0];
        int length = g.length - 1;
        while (length >= 0) {
            byte[] a2 = g[length].a();
            byte[] bArr2 = new byte[bArr.length + a2.length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            System.arraycopy(a2, 0, bArr2, bArr.length, a2.length);
            length--;
            bArr = bArr2;
        }
        byte[] bArr3 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        bArr3[bArr.length] = k().a();
        return bArr3;
    }
}
