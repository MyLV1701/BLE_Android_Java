package c.a.b;

import java.math.BigInteger;

/* loaded from: classes.dex */
public final class o extends j {

    /* renamed from: a, reason: collision with root package name */
    private final Object f2189a;

    public o(Boolean bool) {
        c.a.b.w.a.a(bool);
        this.f2189a = bool;
    }

    private static boolean a(o oVar) {
        Object obj = oVar.f2189a;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f2189a == null) {
            return oVar.f2189a == null;
        }
        if (a(this) && a(oVar)) {
            return l().longValue() == oVar.l().longValue();
        }
        if ((this.f2189a instanceof Number) && (oVar.f2189a instanceof Number)) {
            double doubleValue = l().doubleValue();
            double doubleValue2 = oVar.l().doubleValue();
            if (doubleValue != doubleValue2) {
                return Double.isNaN(doubleValue) && Double.isNaN(doubleValue2);
            }
            return true;
        }
        return this.f2189a.equals(oVar.f2189a);
    }

    public boolean h() {
        if (n()) {
            return ((Boolean) this.f2189a).booleanValue();
        }
        return Boolean.parseBoolean(m());
    }

    public int hashCode() {
        long doubleToLongBits;
        if (this.f2189a == null) {
            return 31;
        }
        if (a(this)) {
            doubleToLongBits = l().longValue();
        } else {
            Object obj = this.f2189a;
            if (obj instanceof Number) {
                doubleToLongBits = Double.doubleToLongBits(l().doubleValue());
            } else {
                return obj.hashCode();
            }
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public double i() {
        return o() ? l().doubleValue() : Double.parseDouble(m());
    }

    public int j() {
        return o() ? l().intValue() : Integer.parseInt(m());
    }

    public long k() {
        return o() ? l().longValue() : Long.parseLong(m());
    }

    public Number l() {
        Object obj = this.f2189a;
        return obj instanceof String ? new c.a.b.w.g((String) obj) : (Number) obj;
    }

    public String m() {
        if (o()) {
            return l().toString();
        }
        if (n()) {
            return ((Boolean) this.f2189a).toString();
        }
        return (String) this.f2189a;
    }

    public boolean n() {
        return this.f2189a instanceof Boolean;
    }

    public boolean o() {
        return this.f2189a instanceof Number;
    }

    public boolean p() {
        return this.f2189a instanceof String;
    }

    public o(Number number) {
        c.a.b.w.a.a(number);
        this.f2189a = number;
    }

    public o(String str) {
        c.a.b.w.a.a(str);
        this.f2189a = str;
    }
}
