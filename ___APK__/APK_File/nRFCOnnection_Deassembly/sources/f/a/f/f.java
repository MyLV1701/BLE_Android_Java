package f.a.f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class f implements f.a.a {

    /* renamed from: a, reason: collision with root package name */
    boolean f3453a = false;

    /* renamed from: b, reason: collision with root package name */
    final Map<String, e> f3454b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    final LinkedBlockingQueue<f.a.e.d> f3455c = new LinkedBlockingQueue<>();

    @Override // f.a.a
    public synchronized f.a.b a(String str) {
        e eVar;
        eVar = this.f3454b.get(str);
        if (eVar == null) {
            eVar = new e(str, this.f3455c, this.f3453a);
            this.f3454b.put(str, eVar);
        }
        return eVar;
    }

    public LinkedBlockingQueue<f.a.e.d> b() {
        return this.f3455c;
    }

    public List<e> c() {
        return new ArrayList(this.f3454b.values());
    }

    public void d() {
        this.f3453a = true;
    }

    public void a() {
        this.f3454b.clear();
        this.f3455c.clear();
    }
}
