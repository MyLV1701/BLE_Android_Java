package d.t.o;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class a1 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private ArrayList f3227c;

    /* JADX INFO: Access modifiers changed from: protected */
    public a1(ArrayList arrayList) {
        super(d.p.k0.q0);
        this.f3227c = arrayList;
    }

    @Override // d.p.n0
    public byte[] u() {
        int i = 2;
        byte[] bArr = new byte[(this.f3227c.size() * 8) + 2];
        d.p.d0.b(this.f3227c.size(), bArr, 0);
        for (int i2 = 0; i2 < this.f3227c.size(); i2++) {
            d.k kVar = (d.k) this.f3227c.get(i2);
            d.a b2 = kVar.b();
            d.a a2 = kVar.a();
            d.p.d0.b(b2.e(), bArr, i);
            d.p.d0.b(a2.e(), bArr, i + 2);
            d.p.d0.b(b2.f(), bArr, i + 4);
            d.p.d0.b(a2.f(), bArr, i + 6);
            i += 8;
        }
        return bArr;
    }
}
