package a.n;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class s {

    /* renamed from: b, reason: collision with root package name */
    public View f518b;

    /* renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f517a = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    final ArrayList<m> f519c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f518b == sVar.f518b && this.f517a.equals(sVar.f517a);
    }

    public int hashCode() {
        return (this.f518b.hashCode() * 31) + this.f517a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f518b + "\n") + "    values:";
        for (String str2 : this.f517a.keySet()) {
            str = str + "    " + str2 + ": " + this.f517a.get(str2) + "\n";
        }
        return str;
    }

    public s(View view) {
        this.f518b = view;
    }
}
