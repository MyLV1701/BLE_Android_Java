package d.p;

/* loaded from: classes.dex */
public abstract class n0 extends h0 implements h {
    static {
        d.q.c.b(n0.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n0(k0 k0Var) {
        super(k0Var);
    }

    @Override // d.p.h
    public final byte[] a() {
        byte[] u = u();
        int length = u.length;
        if (u.length > 8224) {
            u = a(u);
            length = 8224;
        }
        byte[] bArr = new byte[u.length + 4];
        System.arraycopy(u, 0, bArr, 4, u.length);
        d0.b(s(), bArr, 0);
        d0.b(length, bArr, 2);
        return bArr;
    }

    protected abstract byte[] u();

    private byte[] a(byte[] bArr) {
        int length = ((bArr.length - 8224) / 8224) + 1;
        byte[] bArr2 = new byte[bArr.length + (length * 4)];
        System.arraycopy(bArr, 0, bArr2, 0, 8224);
        int i = 8224;
        int i2 = 8224;
        for (int i3 = 0; i3 < length; i3++) {
            int min = Math.min(bArr.length - i, 8224);
            d0.b(k0.r.f2888a, bArr2, i2);
            d0.b(min, bArr2, i2 + 2);
            System.arraycopy(bArr, i, bArr2, i2 + 4, min);
            i += min;
            i2 += min + 4;
        }
        return bArr2;
    }
}
