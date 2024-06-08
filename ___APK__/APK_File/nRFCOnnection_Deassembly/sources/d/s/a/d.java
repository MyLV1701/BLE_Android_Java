package d.s.a;

/* loaded from: classes.dex */
public class d {

    /* renamed from: b, reason: collision with root package name */
    private static d.q.c f3200b = d.q.c.b(d.class);

    /* renamed from: a, reason: collision with root package name */
    private byte[] f3201a;

    public byte[] a(int i, int i2) {
        byte[] bArr = new byte[i2];
        try {
            System.arraycopy(this.f3201a, i, bArr, 0, i2);
            return bArr;
        } catch (ArrayIndexOutOfBoundsException e2) {
            f3200b.a("Array index out of bounds at position " + i + " record length " + i2);
            throw e2;
        }
    }
}
