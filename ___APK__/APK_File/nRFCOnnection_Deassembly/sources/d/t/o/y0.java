package d.t.o;

import java.io.OutputStream;

/* loaded from: classes.dex */
class y0 implements a0 {

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3422a;

    /* renamed from: b, reason: collision with root package name */
    private int f3423b;

    /* renamed from: c, reason: collision with root package name */
    private int f3424c = 0;

    static {
        d.q.c.b(y0.class);
    }

    public y0(int i, int i2) {
        this.f3422a = new byte[i];
        this.f3423b = i2;
    }

    @Override // d.t.o.a0
    public void a(byte[] bArr) {
        while (true) {
            int i = this.f3424c;
            int length = bArr.length + i;
            byte[] bArr2 = this.f3422a;
            if (length > bArr2.length) {
                byte[] bArr3 = new byte[bArr2.length + this.f3423b];
                System.arraycopy(bArr2, 0, bArr3, 0, i);
                this.f3422a = bArr3;
            } else {
                System.arraycopy(bArr, 0, bArr2, i, bArr.length);
                this.f3424c += bArr.length;
                return;
            }
        }
    }

    @Override // d.t.o.a0
    public void close() {
    }

    @Override // d.t.o.a0
    public int getPosition() {
        return this.f3424c;
    }

    @Override // d.t.o.a0
    public void a(byte[] bArr, int i) {
        System.arraycopy(bArr, 0, this.f3422a, i, bArr.length);
    }

    @Override // d.t.o.a0
    public void a(OutputStream outputStream) {
        outputStream.write(this.f3422a, 0, this.f3424c);
    }
}
