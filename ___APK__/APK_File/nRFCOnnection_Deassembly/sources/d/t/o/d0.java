package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class d0 extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private ArrayList f3262c;

    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a, reason: collision with root package name */
        int f3263a;

        /* renamed from: b, reason: collision with root package name */
        int f3264b;

        /* renamed from: c, reason: collision with root package name */
        int f3265c;

        a(int i, int i2, int i3) {
            this.f3263a = i;
            this.f3264b = i2;
            this.f3265c = i3;
        }

        void a(int i) {
            int i2 = this.f3264b;
            if (i2 >= i) {
                this.f3264b = i2 + 1;
            }
            int i3 = this.f3265c;
            if (i3 >= i) {
                this.f3265c = i3 + 1;
            }
        }
    }

    public d0() {
        super(d.p.k0.g);
        this.f3262c = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i, int i2) {
        Iterator it = this.f3262c.iterator();
        boolean z = false;
        int i3 = 0;
        while (it.hasNext() && !z) {
            a aVar = (a) it.next();
            if (aVar.f3263a == i && aVar.f3264b == i2) {
                z = true;
            } else {
                i3++;
            }
        }
        if (z) {
            return i3;
        }
        this.f3262c.add(new a(i, i2, i2));
        return this.f3262c.size() - 1;
    }

    public int b(int i) {
        return ((a) this.f3262c.get(i)).f3264b;
    }

    public int c(int i) {
        return ((a) this.f3262c.get(i)).f3263a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i) {
        Iterator it = this.f3262c.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(i);
        }
    }

    @Override // d.p.n0
    public byte[] u() {
        int i = 2;
        byte[] bArr = new byte[(this.f3262c.size() * 6) + 2];
        d.p.d0.b(this.f3262c.size(), bArr, 0);
        Iterator it = this.f3262c.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            d.p.d0.b(aVar.f3263a, bArr, i);
            d.p.d0.b(aVar.f3264b, bArr, i + 2);
            d.p.d0.b(aVar.f3265c, bArr, i + 4);
            i += 6;
        }
        return bArr;
    }
}
