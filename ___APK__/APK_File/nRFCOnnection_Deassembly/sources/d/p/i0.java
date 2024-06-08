package d.p;

/* loaded from: classes.dex */
public class i0 implements d.k {

    /* renamed from: a, reason: collision with root package name */
    private d.l f2866a;

    /* renamed from: b, reason: collision with root package name */
    private int f2867b;

    /* renamed from: c, reason: collision with root package name */
    private int f2868c;

    /* renamed from: d, reason: collision with root package name */
    private int f2869d;

    /* renamed from: e, reason: collision with root package name */
    private int f2870e;

    @Override // d.k
    public d.a a() {
        if (this.f2869d < this.f2866a.b() && this.f2870e < this.f2866a.c()) {
            return this.f2866a.a(this.f2869d, this.f2870e);
        }
        return new v(this.f2869d, this.f2870e);
    }

    @Override // d.k
    public d.a b() {
        if (this.f2867b < this.f2866a.b() && this.f2868c < this.f2866a.c()) {
            return this.f2866a.a(this.f2867b, this.f2868c);
        }
        return new v(this.f2867b, this.f2868c);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof i0)) {
            return false;
        }
        i0 i0Var = (i0) obj;
        return this.f2867b == i0Var.f2867b && this.f2869d == i0Var.f2869d && this.f2868c == i0Var.f2868c && this.f2870e == i0Var.f2870e;
    }

    public int hashCode() {
        return (((this.f2868c ^ 65535) ^ this.f2870e) ^ this.f2867b) ^ this.f2869d;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        i.a(this.f2867b, this.f2868c, stringBuffer);
        stringBuffer.append('-');
        i.a(this.f2869d, this.f2870e, stringBuffer);
        return stringBuffer.toString();
    }

    public boolean a(i0 i0Var) {
        if (i0Var == this) {
            return true;
        }
        return this.f2870e >= i0Var.f2868c && this.f2868c <= i0Var.f2870e && this.f2869d >= i0Var.f2867b && this.f2867b <= i0Var.f2869d;
    }
}
