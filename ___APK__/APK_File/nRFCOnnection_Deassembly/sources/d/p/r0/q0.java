package d.p.r0;

import java.util.Stack;

/* loaded from: classes.dex */
class q0 extends p0 implements t0 {
    public int a(byte[] bArr, int i) {
        return 0;
    }

    @Override // d.p.r0.p0
    public void a(Stack stack) {
        b((s0) stack.pop());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.p0
    public int h() {
        return 4;
    }

    i1 j() {
        return i1.v;
    }

    @Override // d.p.r0.s0
    public void a(StringBuffer stringBuffer) {
        s0[] g = g();
        stringBuffer.append('(');
        g[0].a(stringBuffer);
        stringBuffer.append(')');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // d.p.r0.s0
    public byte[] a() {
        byte[] a2 = g()[0].a();
        byte[] bArr = new byte[a2.length + 1];
        System.arraycopy(a2, 0, bArr, 0, a2.length);
        bArr[a2.length] = j().a();
        return bArr;
    }
}
