package d.s.a;

import java.util.ArrayList;

/* loaded from: classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private int f3202a;

    /* renamed from: b, reason: collision with root package name */
    private int f3203b;

    /* renamed from: c, reason: collision with root package name */
    private d f3204c;

    /* renamed from: d, reason: collision with root package name */
    private byte[] f3205d;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f3206e;

    static {
        d.q.c.b(e.class);
    }

    public byte[] a() {
        if (this.f3205d == null) {
            this.f3205d = this.f3204c.a(this.f3203b, this.f3202a);
        }
        ArrayList arrayList = this.f3206e;
        if (arrayList != null) {
            byte[][] bArr = new byte[arrayList.size()];
            int i = 0;
            for (int i2 = 0; i2 < this.f3206e.size(); i2++) {
                bArr[i2] = ((e) this.f3206e.get(i2)).a();
                i += bArr[i2].length;
            }
            byte[] bArr2 = this.f3205d;
            byte[] bArr3 = new byte[bArr2.length + i];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            int length = this.f3205d.length;
            for (byte[] bArr4 : bArr) {
                System.arraycopy(bArr4, 0, bArr3, length, bArr4.length);
                length += bArr4.length;
            }
            this.f3205d = bArr3;
        }
        return this.f3205d;
    }
}
