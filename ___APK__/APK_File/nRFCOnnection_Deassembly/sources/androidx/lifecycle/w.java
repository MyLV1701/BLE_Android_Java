package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    private final HashMap<String, u> f1569a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, u uVar) {
        u put = this.f1569a.put(str, uVar);
        if (put != null) {
            put.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> b() {
        return new HashSet(this.f1569a.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final u a(String str) {
        return this.f1569a.get(str);
    }

    public final void a() {
        Iterator<u> it = this.f1569a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1569a.clear();
    }
}
