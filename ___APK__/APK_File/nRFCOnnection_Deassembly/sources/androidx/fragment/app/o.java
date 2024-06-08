package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.v;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o extends androidx.lifecycle.u {
    private static final v.b i = new a();

    /* renamed from: f, reason: collision with root package name */
    private final boolean f1430f;

    /* renamed from: c, reason: collision with root package name */
    private final HashMap<String, Fragment> f1427c = new HashMap<>();

    /* renamed from: d, reason: collision with root package name */
    private final HashMap<String, o> f1428d = new HashMap<>();

    /* renamed from: e, reason: collision with root package name */
    private final HashMap<String, androidx.lifecycle.w> f1429e = new HashMap<>();
    private boolean g = false;
    private boolean h = false;

    /* loaded from: classes.dex */
    static class a implements v.b {
        a() {
        }

        @Override // androidx.lifecycle.v.b
        public <T extends androidx.lifecycle.u> T a(Class<T> cls) {
            return new o(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(boolean z) {
        this.f1430f = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static o a(androidx.lifecycle.w wVar) {
        return (o) new androidx.lifecycle.v(wVar, i).a(o.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.u
    public void b() {
        if (l.d(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.g = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection<Fragment> c() {
        return this.f1427c.values();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e(Fragment fragment) {
        return this.f1427c.remove(fragment.mWho) != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || o.class != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        return this.f1427c.equals(oVar.f1427c) && this.f1428d.equals(oVar.f1428d) && this.f1429e.equals(oVar.f1429e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(Fragment fragment) {
        if (!this.f1427c.containsKey(fragment.mWho)) {
            return true;
        }
        if (this.f1430f) {
            return this.g;
        }
        return !this.h;
    }

    public int hashCode() {
        return (((this.f1427c.hashCode() * 31) + this.f1428d.hashCode()) * 31) + this.f1429e.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1427c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1428d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f1429e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public o c(Fragment fragment) {
        o oVar = this.f1428d.get(fragment.mWho);
        if (oVar != null) {
            return oVar;
        }
        o oVar2 = new o(this.f1430f);
        this.f1428d.put(fragment.mWho, oVar2);
        return oVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public androidx.lifecycle.w d(Fragment fragment) {
        androidx.lifecycle.w wVar = this.f1429e.get(fragment.mWho);
        if (wVar != null) {
            return wVar;
        }
        androidx.lifecycle.w wVar2 = new androidx.lifecycle.w();
        this.f1429e.put(fragment.mWho, wVar2);
        return wVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Fragment fragment) {
        if (this.f1427c.containsKey(fragment.mWho)) {
            return false;
        }
        this.f1427c.put(fragment.mWho, fragment);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fragment b(String str) {
        return this.f1427c.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Fragment fragment) {
        if (l.d(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        o oVar = this.f1428d.get(fragment.mWho);
        if (oVar != null) {
            oVar.b();
            this.f1428d.remove(fragment.mWho);
        }
        androidx.lifecycle.w wVar = this.f1429e.get(fragment.mWho);
        if (wVar != null) {
            wVar.a();
            this.f1429e.remove(fragment.mWho);
        }
    }
}
