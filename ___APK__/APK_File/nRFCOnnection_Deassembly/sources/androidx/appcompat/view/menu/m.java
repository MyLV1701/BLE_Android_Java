package androidx.appcompat.view.menu;

import a.f.l.w;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.n;

/* loaded from: classes.dex */
public class m implements i {

    /* renamed from: a, reason: collision with root package name */
    private final Context f822a;

    /* renamed from: b, reason: collision with root package name */
    private final g f823b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f824c;

    /* renamed from: d, reason: collision with root package name */
    private final int f825d;

    /* renamed from: e, reason: collision with root package name */
    private final int f826e;

    /* renamed from: f, reason: collision with root package name */
    private View f827f;
    private int g;
    private boolean h;
    private n.a i;
    private l j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    /* loaded from: classes.dex */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            m.this.d();
        }
    }

    public m(Context context, g gVar, View view, boolean z, int i) {
        this(context, gVar, view, z, i, 0);
    }

    private l g() {
        l rVar;
        Display defaultDisplay = ((WindowManager) this.f822a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        if (Math.min(point.x, point.y) >= this.f822a.getResources().getDimensionPixelSize(a.a.d.abc_cascading_menus_min_smallest_width)) {
            rVar = new d(this.f822a, this.f827f, this.f825d, this.f826e, this.f824c);
        } else {
            rVar = new r(this.f822a, this.f823b, this.f827f, this.f825d, this.f826e, this.f824c);
        }
        rVar.a(this.f823b);
        rVar.a(this.l);
        rVar.a(this.f827f);
        rVar.a(this.i);
        rVar.b(this.h);
        rVar.a(this.g);
        return rVar;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public l b() {
        if (this.j == null) {
            this.j = g();
        }
        return this.j;
    }

    public boolean c() {
        l lVar = this.j;
        return lVar != null && lVar.e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void e() {
        if (!f()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean f() {
        if (c()) {
            return true;
        }
        if (this.f827f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public m(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.f822a = context;
        this.f823b = gVar;
        this.f827f = view;
        this.f824c = z;
        this.f825d = i;
        this.f826e = i2;
    }

    public void a(View view) {
        this.f827f = view;
    }

    public void a(boolean z) {
        this.h = z;
        l lVar = this.j;
        if (lVar != null) {
            lVar.b(z);
        }
    }

    public void a(int i) {
        this.g = i;
    }

    public boolean a(int i, int i2) {
        if (c()) {
            return true;
        }
        if (this.f827f == null) {
            return false;
        }
        a(i, i2, true, true);
        return true;
    }

    private void a(int i, int i2, boolean z, boolean z2) {
        l b2 = b();
        b2.c(z2);
        if (z) {
            if ((a.f.l.d.a(this.g, w.q(this.f827f)) & 7) == 5) {
                i -= this.f827f.getWidth();
            }
            b2.b(i);
            b2.c(i2);
            int i3 = (int) ((this.f822a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            b2.a(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        b2.d();
    }

    public void a() {
        if (c()) {
            this.j.dismiss();
        }
    }

    public void a(n.a aVar) {
        this.i = aVar;
        l lVar = this.j;
        if (lVar != null) {
            lVar.a(aVar);
        }
    }
}
