package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class c {

    /* renamed from: a, reason: collision with root package name */
    final Context f765a;

    /* renamed from: b, reason: collision with root package name */
    private Map<a.f.f.a.b, MenuItem> f766b;

    /* renamed from: c, reason: collision with root package name */
    private Map<a.f.f.a.c, SubMenu> f767c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        this.f765a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof a.f.f.a.b)) {
            return menuItem;
        }
        a.f.f.a.b bVar = (a.f.f.a.b) menuItem;
        if (this.f766b == null) {
            this.f766b = new a.d.a();
        }
        MenuItem menuItem2 = this.f766b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        k kVar = new k(this.f765a, bVar);
        this.f766b.put(bVar, kVar);
        return kVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        Map<a.f.f.a.b, MenuItem> map = this.f766b;
        if (map != null) {
            map.clear();
        }
        Map<a.f.f.a.c, SubMenu> map2 = this.f767c;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(int i) {
        Map<a.f.f.a.b, MenuItem> map = this.f766b;
        if (map == null) {
            return;
        }
        Iterator<a.f.f.a.b> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == it.next().getItemId()) {
                it.remove();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof a.f.f.a.c)) {
            return subMenu;
        }
        a.f.f.a.c cVar = (a.f.f.a.c) subMenu;
        if (this.f767c == null) {
            this.f767c = new a.d.a();
        }
        SubMenu subMenu2 = this.f767c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        t tVar = new t(this.f765a, cVar);
        this.f767c.put(cVar, tVar);
        return tVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        Map<a.f.f.a.b, MenuItem> map = this.f766b;
        if (map == null) {
            return;
        }
        Iterator<a.f.f.a.b> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == it.next().getGroupId()) {
                it.remove();
            }
        }
    }
}
