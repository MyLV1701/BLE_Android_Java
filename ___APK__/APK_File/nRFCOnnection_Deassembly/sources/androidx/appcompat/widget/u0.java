package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.Toolbar;

/* loaded from: classes.dex */
public class u0 implements z {

    /* renamed from: a, reason: collision with root package name */
    Toolbar f1096a;

    /* renamed from: b, reason: collision with root package name */
    private int f1097b;

    /* renamed from: c, reason: collision with root package name */
    private View f1098c;

    /* renamed from: d, reason: collision with root package name */
    private View f1099d;

    /* renamed from: e, reason: collision with root package name */
    private Drawable f1100e;

    /* renamed from: f, reason: collision with root package name */
    private Drawable f1101f;
    private Drawable g;
    private boolean h;
    CharSequence i;
    private CharSequence j;
    private CharSequence k;
    Window.Callback l;
    boolean m;
    private c n;
    private int o;
    private int p;
    private Drawable q;

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {

        /* renamed from: b, reason: collision with root package name */
        final androidx.appcompat.view.menu.a f1102b;

        a() {
            this.f1102b = new androidx.appcompat.view.menu.a(u0.this.f1096a.getContext(), 0, R.id.home, 0, 0, u0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            u0 u0Var = u0.this;
            Window.Callback callback = u0Var.l;
            if (callback == null || !u0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f1102b);
        }
    }

    /* loaded from: classes.dex */
    class b extends a.f.l.c0 {

        /* renamed from: a, reason: collision with root package name */
        private boolean f1104a = false;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1105b;

        b(int i) {
            this.f1105b = i;
        }

        @Override // a.f.l.c0, a.f.l.b0
        public void a(View view) {
            this.f1104a = true;
        }

        @Override // a.f.l.b0
        public void b(View view) {
            if (this.f1104a) {
                return;
            }
            u0.this.f1096a.setVisibility(this.f1105b);
        }

        @Override // a.f.l.c0, a.f.l.b0
        public void c(View view) {
            u0.this.f1096a.setVisibility(0);
        }
    }

    public u0(Toolbar toolbar, boolean z) {
        this(toolbar, z, a.a.h.abc_action_bar_up_description, a.a.e.abc_ic_ab_back_material);
    }

    private void d(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f1097b & 8) != 0) {
            this.f1096a.setTitle(charSequence);
        }
    }

    private int o() {
        if (this.f1096a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f1096a.getNavigationIcon();
        return 15;
    }

    private void p() {
        if ((this.f1097b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f1096a.setNavigationContentDescription(this.p);
            } else {
                this.f1096a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void q() {
        if ((this.f1097b & 4) != 0) {
            Toolbar toolbar = this.f1096a;
            Drawable drawable = this.g;
            if (drawable == null) {
                drawable = this.q;
            }
            toolbar.setNavigationIcon(drawable);
            return;
        }
        this.f1096a.setNavigationIcon((Drawable) null);
    }

    private void r() {
        Drawable drawable;
        int i = this.f1097b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) != 0) {
            drawable = this.f1101f;
            if (drawable == null) {
                drawable = this.f1100e;
            }
        } else {
            drawable = this.f1100e;
        }
        this.f1096a.setLogo(drawable);
    }

    @Override // androidx.appcompat.widget.z
    public void a(boolean z) {
    }

    @Override // androidx.appcompat.widget.z
    public boolean a() {
        return this.f1096a.i();
    }

    public void b(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f1097b & 8) != 0) {
            this.f1096a.setSubtitle(charSequence);
        }
    }

    public void c(CharSequence charSequence) {
        this.h = true;
        d(charSequence);
    }

    @Override // androidx.appcompat.widget.z
    public void collapseActionView() {
        this.f1096a.c();
    }

    @Override // androidx.appcompat.widget.z
    public boolean e() {
        return this.f1096a.k();
    }

    public void f(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f1096a.getNavigationContentDescription())) {
            d(this.p);
        }
    }

    @Override // androidx.appcompat.widget.z
    public void g() {
        this.f1096a.d();
    }

    @Override // androidx.appcompat.widget.z
    public Context getContext() {
        return this.f1096a.getContext();
    }

    @Override // androidx.appcompat.widget.z
    public CharSequence getTitle() {
        return this.f1096a.getTitle();
    }

    @Override // androidx.appcompat.widget.z
    public ViewGroup h() {
        return this.f1096a;
    }

    @Override // androidx.appcompat.widget.z
    public boolean i() {
        return this.f1096a.f();
    }

    @Override // androidx.appcompat.widget.z
    public int j() {
        return this.f1097b;
    }

    @Override // androidx.appcompat.widget.z
    public Menu k() {
        return this.f1096a.getMenu();
    }

    @Override // androidx.appcompat.widget.z
    public int l() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.z
    public void m() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.z
    public void n() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.z
    public void setIcon(int i) {
        setIcon(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.z
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.z
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        d(charSequence);
    }

    public u0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f1096a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        t0 a2 = t0.a(toolbar.getContext(), null, a.a.j.ActionBar, a.a.a.actionBarStyle, 0);
        this.q = a2.b(a.a.j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence e2 = a2.e(a.a.j.ActionBar_title);
            if (!TextUtils.isEmpty(e2)) {
                c(e2);
            }
            CharSequence e3 = a2.e(a.a.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(e3)) {
                b(e3);
            }
            Drawable b2 = a2.b(a.a.j.ActionBar_logo);
            if (b2 != null) {
                b(b2);
            }
            Drawable b3 = a2.b(a.a.j.ActionBar_icon);
            if (b3 != null) {
                setIcon(b3);
            }
            if (this.g == null && (drawable = this.q) != null) {
                a(drawable);
            }
            b(a2.d(a.a.j.ActionBar_displayOptions, 0));
            int g = a2.g(a.a.j.ActionBar_customNavigationLayout, 0);
            if (g != 0) {
                a(LayoutInflater.from(this.f1096a.getContext()).inflate(g, (ViewGroup) this.f1096a, false));
                b(this.f1097b | 16);
            }
            int f2 = a2.f(a.a.j.ActionBar_height, 0);
            if (f2 > 0) {
                ViewGroup.LayoutParams layoutParams = this.f1096a.getLayoutParams();
                layoutParams.height = f2;
                this.f1096a.setLayoutParams(layoutParams);
            }
            int b4 = a2.b(a.a.j.ActionBar_contentInsetStart, -1);
            int b5 = a2.b(a.a.j.ActionBar_contentInsetEnd, -1);
            if (b4 >= 0 || b5 >= 0) {
                this.f1096a.a(Math.max(b4, 0), Math.max(b5, 0));
            }
            int g2 = a2.g(a.a.j.ActionBar_titleTextStyle, 0);
            if (g2 != 0) {
                Toolbar toolbar2 = this.f1096a;
                toolbar2.b(toolbar2.getContext(), g2);
            }
            int g3 = a2.g(a.a.j.ActionBar_subtitleTextStyle, 0);
            if (g3 != 0) {
                Toolbar toolbar3 = this.f1096a;
                toolbar3.a(toolbar3.getContext(), g3);
            }
            int g4 = a2.g(a.a.j.ActionBar_popupTheme, 0);
            if (g4 != 0) {
                this.f1096a.setPopupTheme(g4);
            }
        } else {
            this.f1097b = o();
        }
        a2.a();
        f(i);
        this.k = this.f1096a.getNavigationContentDescription();
        this.f1096a.setNavigationOnClickListener(new a());
    }

    @Override // androidx.appcompat.widget.z
    public void a(Menu menu, n.a aVar) {
        if (this.n == null) {
            this.n = new c(this.f1096a.getContext());
            this.n.a(a.a.f.action_menu_presenter);
        }
        this.n.a(aVar);
        this.f1096a.a((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.z
    public void e(int i) {
        a(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    @Override // androidx.appcompat.widget.z
    public void setIcon(Drawable drawable) {
        this.f1100e = drawable;
        r();
    }

    @Override // androidx.appcompat.widget.z
    public void c(int i) {
        b(i != 0 ? a.a.k.a.a.c(getContext(), i) : null);
    }

    public void b(Drawable drawable) {
        this.f1101f = drawable;
        r();
    }

    @Override // androidx.appcompat.widget.z
    public boolean c() {
        return this.f1096a.h();
    }

    @Override // androidx.appcompat.widget.z
    public boolean d() {
        return this.f1096a.g();
    }

    @Override // androidx.appcompat.widget.z
    public void d(int i) {
        a(i == 0 ? null : getContext().getString(i));
    }

    @Override // androidx.appcompat.widget.z
    public boolean f() {
        return this.f1096a.b();
    }

    @Override // androidx.appcompat.widget.z
    public void b() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.z
    public void a(m0 m0Var) {
        View view = this.f1098c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f1096a;
            if (parent == toolbar) {
                toolbar.removeView(this.f1098c);
            }
        }
        this.f1098c = m0Var;
        if (m0Var == null || this.o != 2) {
            return;
        }
        this.f1096a.addView(this.f1098c, 0);
        Toolbar.e eVar = (Toolbar.e) this.f1098c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f651a = 8388691;
        m0Var.setAllowCollapse(true);
    }

    @Override // androidx.appcompat.widget.z
    public void b(int i) {
        View view;
        int i2 = this.f1097b ^ i;
        this.f1097b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    p();
                }
                q();
            }
            if ((i2 & 3) != 0) {
                r();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f1096a.setTitle(this.i);
                    this.f1096a.setSubtitle(this.j);
                } else {
                    this.f1096a.setTitle((CharSequence) null);
                    this.f1096a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f1099d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f1096a.addView(view);
            } else {
                this.f1096a.removeView(view);
            }
        }
    }

    public void a(View view) {
        View view2 = this.f1099d;
        if (view2 != null && (this.f1097b & 16) != 0) {
            this.f1096a.removeView(view2);
        }
        this.f1099d = view;
        if (view == null || (this.f1097b & 16) == 0) {
            return;
        }
        this.f1096a.addView(this.f1099d);
    }

    @Override // androidx.appcompat.widget.z
    public void b(boolean z) {
        this.f1096a.setCollapsible(z);
    }

    @Override // androidx.appcompat.widget.z
    public a.f.l.a0 a(int i, long j) {
        a.f.l.a0 a2 = a.f.l.w.a(this.f1096a);
        a2.a(i == 0 ? 1.0f : 0.0f);
        a2.a(j);
        a2.a(new b(i));
        return a2;
    }

    @Override // androidx.appcompat.widget.z
    public void a(Drawable drawable) {
        this.g = drawable;
        q();
    }

    public void a(CharSequence charSequence) {
        this.k = charSequence;
        p();
    }

    @Override // androidx.appcompat.widget.z
    public void a(int i) {
        this.f1096a.setVisibility(i);
    }

    @Override // androidx.appcompat.widget.z
    public void a(n.a aVar, g.a aVar2) {
        this.f1096a.a(aVar, aVar2);
    }
}
