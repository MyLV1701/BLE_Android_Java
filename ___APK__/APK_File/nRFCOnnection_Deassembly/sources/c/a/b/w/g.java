package c.a.b.w;

import java.math.BigDecimal;

/* loaded from: classes.dex */
public final class g extends Number {

    /* renamed from: b, reason: collision with root package name */
    private final String f2225b;

    public g(String str) {
        this.f2225b = str;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return Double.parseDouble(this.f2225b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        String str = this.f2225b;
        String str2 = ((g) obj).f2225b;
        return str == str2 || str.equals(str2);
    }

    @Override // java.lang.Number
    public float floatValue() {
        return Float.parseFloat(this.f2225b);
    }

    public int hashCode() {
        return this.f2225b.hashCode();
    }

    @Override // java.lang.Number
    public int intValue() {
        try {
            try {
                return Integer.parseInt(this.f2225b);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(this.f2225b);
            }
        } catch (NumberFormatException unused2) {
            return new BigDecimal(this.f2225b).intValue();
        }
    }

    @Override // java.lang.Number
    public long longValue() {
        try {
            return Long.parseLong(this.f2225b);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.f2225b).longValue();
        }
    }

    public String toString() {
        return this.f2225b;
    }
}
