package a.f.l;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    private InterfaceC0016b f286a;

    /* loaded from: classes.dex */
    public interface a {
    }

    /* renamed from: a.f.l.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0016b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
    }

    public View a(MenuItem menuItem) {
        return c();
    }

    public void a(a aVar) {
    }

    public void a(SubMenu subMenu) {
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public abstract View c();

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public void f() {
        this.f286a = null;
    }

    public void a(InterfaceC0016b interfaceC0016b) {
        if (this.f286a != null && interfaceC0016b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f286a = interfaceC0016b;
    }
}
