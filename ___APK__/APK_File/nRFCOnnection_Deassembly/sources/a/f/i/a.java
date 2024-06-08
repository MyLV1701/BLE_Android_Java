package a.f.i;

import a.f.k.h;
import android.util.Base64;
import java.util.List;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final String f183a;

    /* renamed from: b, reason: collision with root package name */
    private final String f184b;

    /* renamed from: c, reason: collision with root package name */
    private final String f185c;

    /* renamed from: d, reason: collision with root package name */
    private final List<List<byte[]>> f186d;

    /* renamed from: e, reason: collision with root package name */
    private final int f187e;

    /* renamed from: f, reason: collision with root package name */
    private final String f188f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        h.a(str);
        this.f183a = str;
        h.a(str2);
        this.f184b = str2;
        h.a(str3);
        this.f185c = str3;
        h.a(list);
        this.f186d = list;
        this.f187e = 0;
        this.f188f = this.f183a + "-" + this.f184b + "-" + this.f185c;
    }

    public List<List<byte[]>> a() {
        return this.f186d;
    }

    public int b() {
        return this.f187e;
    }

    public String c() {
        return this.f188f;
    }

    public String d() {
        return this.f183a;
    }

    public String e() {
        return this.f184b;
    }

    public String f() {
        return this.f185c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.f183a + ", mProviderPackage: " + this.f184b + ", mQuery: " + this.f185c + ", mCertificates:");
        for (int i = 0; i < this.f186d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f186d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f187e);
        return sb.toString();
    }
}
