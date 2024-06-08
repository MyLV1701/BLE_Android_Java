package d.p.r0;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/* loaded from: classes.dex */
public class y {

    /* renamed from: a, reason: collision with root package name */
    private HashMap f3126a;

    /* renamed from: b, reason: collision with root package name */
    private HashMap f3127b;

    static {
        d.q.c.b(y.class);
    }

    public y(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("functions", locale);
        x[] d2 = x.d();
        this.f3126a = new HashMap(d2.length);
        this.f3127b = new HashMap(d2.length);
        for (x xVar : d2) {
            String c2 = xVar.c();
            String string = c2.length() != 0 ? bundle.getString(c2) : null;
            if (string != null) {
                this.f3126a.put(xVar, string);
                this.f3127b.put(string, xVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public x a(String str) {
        return (x) this.f3127b.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a(x xVar) {
        return (String) this.f3126a.get(xVar);
    }
}
