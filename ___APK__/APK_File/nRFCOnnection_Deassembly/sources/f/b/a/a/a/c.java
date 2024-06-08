package f.b.a.a.a;

import java.security.BasicPermission;
import java.security.Permission;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public class c extends BasicPermission {

    /* renamed from: b, reason: collision with root package name */
    private final String f3458b;

    /* renamed from: c, reason: collision with root package name */
    private final int f3459c;

    public c(String str, String str2) {
        super(str, str2);
        this.f3458b = str2;
        this.f3459c = a(str2);
    }

    private int a(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(f.b.c.a.a(str), " ,");
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("threadlocalecimplicitlyca")) {
                i |= 1;
            } else if (nextToken.equals("ecimplicitlyca")) {
                i |= 2;
            } else if (nextToken.equals("threadlocaldhdefaultparams")) {
                i |= 4;
            } else if (nextToken.equals("dhdefaultparams")) {
                i |= 8;
            } else if (nextToken.equals("all")) {
                i |= 15;
            }
        }
        if (i != 0) {
            return i;
        }
        throw new IllegalArgumentException("unknown permissions passed to mask");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f3459c == cVar.f3459c && getName().equals(cVar.getName());
    }

    @Override // java.security.BasicPermission, java.security.Permission
    public String getActions() {
        return this.f3458b;
    }

    public int hashCode() {
        return getName().hashCode() + this.f3459c;
    }

    @Override // java.security.BasicPermission, java.security.Permission
    public boolean implies(Permission permission) {
        if (!(permission instanceof c) || !getName().equals(permission.getName())) {
            return false;
        }
        int i = this.f3459c;
        int i2 = ((c) permission).f3459c;
        return (i & i2) == i2;
    }
}
