package d.p;

/* loaded from: classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private int f2860a;

    /* renamed from: b, reason: collision with root package name */
    private byte[] f2861b;

    /* renamed from: c, reason: collision with root package name */
    private int f2862c;

    public g() {
        this(1024);
    }

    public void a(byte b2) {
        a(1);
        byte[] bArr = this.f2861b;
        int i = this.f2862c;
        bArr[i] = b2;
        this.f2862c = i + 1;
    }

    public g(int i) {
        this.f2860a = i;
        this.f2861b = new byte[1024];
        this.f2862c = 0;
    }

    public void a(byte[] bArr) {
        a(bArr.length);
        System.arraycopy(bArr, 0, this.f2861b, this.f2862c, bArr.length);
        this.f2862c += bArr.length;
    }

    public byte[] a() {
        int i = this.f2862c;
        byte[] bArr = new byte[i];
        System.arraycopy(this.f2861b, 0, bArr, 0, i);
        return bArr;
    }

    private void a(int i) {
        while (true) {
            int i2 = this.f2862c;
            int i3 = i2 + i;
            byte[] bArr = this.f2861b;
            if (i3 < bArr.length) {
                return;
            }
            byte[] bArr2 = new byte[bArr.length + this.f2860a];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.f2861b = bArr2;
        }
    }
}
