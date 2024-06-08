package androidx.appcompat.app;

import a.f.l.w;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.u0;
import androidx.appcompat.widget.z;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class k extends androidx.appcompat.app.a {

    /* renamed from: a, reason: collision with root package name */
    z f707a;

    /* renamed from: b, reason: collision with root package name */
    boolean f708b;

    /* renamed from: c, reason: collision with root package name */
    Window.Callback f709c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f710d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f711e;

    /* renamed from: f, reason: collision with root package name */
    private ArrayList<a.b> f712f = new ArrayList<>();
    private final Runnable g = new a();
    private final Toolbar.f h = new b();

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k.this.m();
        }
    }

    /* loaded from: classes.dex */
    class b implements Toolbar.f {
        b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return k.this.f709c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class d implements g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(androidx.appcompat.view.menu.g gVar) {
            k kVar = k.this;
            if (kVar.f709c != null) {
                if (kVar.f707a.a()) {
                    k.this.f709c.onPanelClosed(108, gVar);
                } else if (k.this.f709c.onPreparePanel(0, null, gVar)) {
                    k.this.f709c.onMenuOpened(108, gVar);
                }
            }
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    private class e extends a.a.o.i {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            if (i == 0) {
                return new View(k.this.f707a.getContext());
            }
            return super.onCreatePanelView(i);
        }

        @Override // a.a.o.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean onPreparePanel = super.onPreparePanel(i, view, menu);
            if (onPreparePanel) {
                k kVar = k.this;
                if (!kVar.f708b) {
                    kVar.f707a.b();
                    k.this.f708b = true;
                }
            }
            return onPreparePanel;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        this.f707a = new u0(toolbar, false);
        this.f709c = new e(callback);
        this.f707a.setWindowCallback(this.f709c);
        toolbar.setOnMenuItemClickListener(this.h);
        this.f707a.setWindowTitle(charSequence);
    }

    private Menu n() {
        if (!this.f710d) {
            this.f707a.a(new c(), new d());
            this.f710d = true;
        }
        return this.f707a.k();
    }

    @Override // androidx.appcompat.app.a
    public void a(Drawable drawable) {
        this.f707a.a(drawable);
    }

    @Override // androidx.appcompat.app.a
    public void b(int i) {
        this.f707a.d(i);
    }

    @Override // androidx.appcompat.app.a
    public void c(int i) {
        this.f707a.e(i);
    }

    @Override // androidx.appcompat.app.a
    public void c(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void d(boolean z) {
        a(z ? 4 : 0, 4);
    }

    @Override // androidx.appcompat.app.a
    public void e(boolean z) {
        a(z ? 8 : 0, 8);
    }

    @Override // androidx.appcompat.app.a
    public void f(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public boolean f() {
        if (!this.f707a.i()) {
            return false;
        }
        this.f707a.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public int g() {
        return this.f707a.j();
    }

    @Override // androidx.appcompat.app.a
    public Context h() {
        return this.f707a.getContext();
    }

    @Override // androidx.appcompat.app.a
    public boolean i() {
        this.f707a.h().removeCallbacks(this.g);
        w.a(this.f707a.h(), this.g);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.app.a
    public void j() {
        this.f707a.h().removeCallbacks(this.g);
    }

    @Override // androidx.appcompat.app.a
    public boolean k() {
        return this.f707a.e();
    }

    public Window.Callback l() {
        return this.f709c;
    }

    void m() {
        Menu n = n();
        androidx.appcompat.view.menu.g gVar = n instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) n : null;
        if (gVar != null) {
            gVar.s();
        }
        try {
            n.clear();
            if (!this.f709c.onCreatePanelMenu(0, n) || !this.f709c.onPreparePanel(0, null, n)) {
                n.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.r();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class c implements n.a {

        /* renamed from: b, reason: collision with root package name */
        private boolean f715b;

        c() {
        }

        @Override // androidx.appcompat.view.menu.n.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callback = k.this.f709c;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, gVar);
            return true;
        }

        @Override // androidx.appcompat.view.menu.n.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (this.f715b) {
                return;
            }
            this.f715b = true;
            k.this.f707a.g();
            Window.Callback callback = k.this.f709c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f715b = false;
        }
    }

    @Override // androidx.appcompat.app.a
    public void a(Configuration configuration) {
        super.a(configuration);
    }

    @Override // androidx.appcompat.app.a
    public void b(boolean z) {
        if (z == this.f711e) {
            return;
        }
        this.f711e = z;
        int size = this.f712f.size();
        for (int i = 0; i < size; i++) {
            this.f712f.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public boolean e() {
        return this.f707a.d();
    }

    @Override // androidx.appcompat.app.a
    public void a(CharSequence charSequence) {
        this.f707a.setWindowTitle(charSequence);
    }

    public void a(int i, int i2) {
        this.f707a.b((i & i2) | ((i2 ^ (-1)) & this.f707a.j()));
    }

    @Override // androidx.appcompat.app.a
    public boolean a(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            k();
        }
        return true;
    }

    @Override // androidx.appcompat.app.a
    public boolean a(int i, KeyEvent keyEvent) {
        Menu n = n();
        if (n == null) {
            return false;
        }
        n.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return n.performShortcut(i, keyEvent, 0);
    }
}
