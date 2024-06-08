package d.t.o;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
class q extends d.p.n0 {

    /* renamed from: c, reason: collision with root package name */
    private int f3370c;

    /* renamed from: d, reason: collision with root package name */
    private int f3371d;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f3372e;

    /* renamed from: f, reason: collision with root package name */
    private int f3373f;

    public q(int i) {
        super(d.p.k0.o);
        this.f3370c = i;
        this.f3372e = new ArrayList(10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i) {
        this.f3372e.add(new Integer(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i) {
        this.f3371d = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i) {
        this.f3373f = i;
    }

    @Override // d.p.n0
    protected byte[] u() {
        int i = 4;
        byte[] bArr = new byte[(this.f3372e.size() * 2) + 4];
        d.p.d0.a(this.f3373f - this.f3370c, bArr, 0);
        int i2 = this.f3371d;
        Iterator it = this.f3372e.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            d.p.d0.b(intValue - i2, bArr, i);
            i += 2;
            i2 = intValue;
        }
        return bArr;
    }
}
