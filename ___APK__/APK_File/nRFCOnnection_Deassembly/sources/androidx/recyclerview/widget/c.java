package androidx.recyclerview.widget;

/* loaded from: classes.dex */
public class c implements p {

    /* renamed from: a, reason: collision with root package name */
    final p f1789a;

    /* renamed from: b, reason: collision with root package name */
    int f1790b = 0;

    /* renamed from: c, reason: collision with root package name */
    int f1791c = -1;

    /* renamed from: d, reason: collision with root package name */
    int f1792d = -1;

    /* renamed from: e, reason: collision with root package name */
    Object f1793e = null;

    public c(p pVar) {
        this.f1789a = pVar;
    }

    public void a() {
        int i = this.f1790b;
        if (i == 0) {
            return;
        }
        if (i == 1) {
            this.f1789a.c(this.f1791c, this.f1792d);
        } else if (i == 2) {
            this.f1789a.a(this.f1791c, this.f1792d);
        } else if (i == 3) {
            this.f1789a.a(this.f1791c, this.f1792d, this.f1793e);
        }
        this.f1793e = null;
        this.f1790b = 0;
    }

    @Override // androidx.recyclerview.widget.p
    public void b(int i, int i2) {
        a();
        this.f1789a.b(i, i2);
    }

    @Override // androidx.recyclerview.widget.p
    public void c(int i, int i2) {
        int i3;
        if (this.f1790b == 1 && i >= (i3 = this.f1791c)) {
            int i4 = this.f1792d;
            if (i <= i3 + i4) {
                this.f1792d = i4 + i2;
                this.f1791c = Math.min(i, i3);
                return;
            }
        }
        a();
        this.f1791c = i;
        this.f1792d = i2;
        this.f1790b = 1;
    }

    @Override // androidx.recyclerview.widget.p
    public void a(int i, int i2) {
        int i3;
        if (this.f1790b == 2 && (i3 = this.f1791c) >= i && i3 <= i + i2) {
            this.f1792d += i2;
            this.f1791c = i;
        } else {
            a();
            this.f1791c = i;
            this.f1792d = i2;
            this.f1790b = 2;
        }
    }

    @Override // androidx.recyclerview.widget.p
    public void a(int i, int i2, Object obj) {
        int i3;
        if (this.f1790b == 3) {
            int i4 = this.f1791c;
            int i5 = this.f1792d;
            if (i <= i4 + i5 && (i3 = i + i2) >= i4 && this.f1793e == obj) {
                this.f1791c = Math.min(i, i4);
                this.f1792d = Math.max(i5 + i4, i3) - this.f1791c;
                return;
            }
        }
        a();
        this.f1791c = i;
        this.f1792d = i2;
        this.f1793e = obj;
        this.f1790b = 3;
    }
}
