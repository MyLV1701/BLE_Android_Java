package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.g;

/* loaded from: classes.dex */
public class i0 {

    /* renamed from: a, reason: collision with root package name */
    private final Context f1004a;

    /* renamed from: b, reason: collision with root package name */
    private final androidx.appcompat.view.menu.g f1005b;

    /* renamed from: c, reason: collision with root package name */
    final androidx.appcompat.view.menu.m f1006c;

    /* renamed from: d, reason: collision with root package name */
    d f1007d;

    /* renamed from: e, reason: collision with root package name */
    c f1008e;

    /* loaded from: classes.dex */
    class a implements g.a {
        a() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(androidx.appcompat.view.menu.g gVar) {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            d dVar = i0.this.f1007d;
            if (dVar != null) {
                return dVar.onMenuItemClick(menuItem);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    class b implements PopupWindow.OnDismissListener {
        b() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            i0 i0Var = i0.this;
            c cVar = i0Var.f1008e;
            if (cVar != null) {
                cVar.a(i0Var);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(i0 i0Var);
    }

    /* loaded from: classes.dex */
    public interface d {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public i0(Context context, View view) {
        this(context, view, 0);
    }

    public Menu a() {
        return this.f1005b;
    }

    public MenuInflater b() {
        return new a.a.o.g(this.f1004a);
    }

    public void c() {
        this.f1006c.e();
    }

    public i0(Context context, View view, int i) {
        this(context, view, i, a.a.a.popupMenuStyle, 0);
    }

    public void a(int i) {
        b().inflate(i, this.f1005b);
    }

    public i0(Context context, View view, int i, int i2, int i3) {
        this.f1004a = context;
        this.f1005b = new androidx.appcompat.view.menu.g(context);
        this.f1005b.a(new a());
        this.f1006c = new androidx.appcompat.view.menu.m(context, this.f1005b, view, false, i2, i3);
        this.f1006c.a(i);
        this.f1006c.a(new b());
    }

    public void a(d dVar) {
        this.f1007d = dVar;
    }

    public void a(c cVar) {
        this.f1008e = cVar;
    }
}
