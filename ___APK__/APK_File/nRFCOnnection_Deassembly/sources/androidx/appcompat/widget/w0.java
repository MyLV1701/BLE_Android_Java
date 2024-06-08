package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;

/* loaded from: classes.dex */
class w0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static w0 k;
    private static w0 l;

    /* renamed from: b, reason: collision with root package name */
    private final View f1124b;

    /* renamed from: c, reason: collision with root package name */
    private final CharSequence f1125c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1126d;

    /* renamed from: e, reason: collision with root package name */
    private final Runnable f1127e = new a();

    /* renamed from: f, reason: collision with root package name */
    private final Runnable f1128f = new b();
    private int g;
    private int h;
    private x0 i;
    private boolean j;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            w0.this.a(false);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            w0.this.a();
        }
    }

    private w0(View view, CharSequence charSequence) {
        this.f1124b = view;
        this.f1125c = charSequence;
        this.f1126d = a.f.l.x.a(ViewConfiguration.get(this.f1124b.getContext()));
        c();
        this.f1124b.setOnLongClickListener(this);
        this.f1124b.setOnHoverListener(this);
    }

    public static void a(View view, CharSequence charSequence) {
        w0 w0Var = k;
        if (w0Var != null && w0Var.f1124b == view) {
            a((w0) null);
        }
        if (TextUtils.isEmpty(charSequence)) {
            w0 w0Var2 = l;
            if (w0Var2 != null && w0Var2.f1124b == view) {
                w0Var2.a();
            }
            view.setOnLongClickListener(null);
            view.setLongClickable(false);
            view.setOnHoverListener(null);
            return;
        }
        new w0(view, charSequence);
    }

    private void b() {
        this.f1124b.removeCallbacks(this.f1127e);
    }

    private void c() {
        this.g = Preference.DEFAULT_ORDER;
        this.h = Preference.DEFAULT_ORDER;
    }

    private void d() {
        this.f1124b.postDelayed(this.f1127e, ViewConfiguration.getLongPressTimeout());
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.i != null && this.j) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f1124b.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                c();
                a();
            }
        } else if (this.f1124b.isEnabled() && this.i == null && a(motionEvent)) {
            a(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.g = view.getWidth() / 2;
        this.h = view.getHeight() / 2;
        a(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }

    void a(boolean z) {
        long j;
        int longPressTimeout;
        long j2;
        if (a.f.l.w.E(this.f1124b)) {
            a((w0) null);
            w0 w0Var = l;
            if (w0Var != null) {
                w0Var.a();
            }
            l = this;
            this.j = z;
            this.i = new x0(this.f1124b.getContext());
            this.i.a(this.f1124b, this.g, this.h, this.j, this.f1125c);
            this.f1124b.addOnAttachStateChangeListener(this);
            if (this.j) {
                j2 = 2500;
            } else {
                if ((a.f.l.w.y(this.f1124b) & 1) == 1) {
                    j = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j2 = j - longPressTimeout;
            }
            this.f1124b.removeCallbacks(this.f1128f);
            this.f1124b.postDelayed(this.f1128f, j2);
        }
    }

    void a() {
        if (l == this) {
            l = null;
            x0 x0Var = this.i;
            if (x0Var != null) {
                x0Var.a();
                this.i = null;
                c();
                this.f1124b.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (k == this) {
            a((w0) null);
        }
        this.f1124b.removeCallbacks(this.f1128f);
    }

    private static void a(w0 w0Var) {
        w0 w0Var2 = k;
        if (w0Var2 != null) {
            w0Var2.b();
        }
        k = w0Var;
        w0 w0Var3 = k;
        if (w0Var3 != null) {
            w0Var3.d();
        }
    }

    private boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.g) <= this.f1126d && Math.abs(y - this.h) <= this.f1126d) {
            return false;
        }
        this.g = x;
        this.h = y;
        return true;
    }
}
