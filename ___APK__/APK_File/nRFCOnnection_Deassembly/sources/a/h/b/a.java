package a.h.b;

import a.d.h;
import a.f.l.f0.d;
import a.f.l.f0.e;
import a.f.l.w;
import a.f.l.z;
import a.h.b.b;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.ble.error.GattError;
import no.nordicsemi.android.dfu.DfuBaseService;

/* loaded from: classes.dex */
public abstract class a extends a.f.l.a {
    private static final Rect k = new Rect(Preference.DEFAULT_ORDER, Preference.DEFAULT_ORDER, RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION);
    private static final b.a<a.f.l.f0.c> l = new C0022a();
    private static final b.InterfaceC0023b<h<a.f.l.f0.c>, a.f.l.f0.c> m = new b();

    /* renamed from: e, reason: collision with root package name */
    private final AccessibilityManager f361e;

    /* renamed from: f, reason: collision with root package name */
    private final View f362f;
    private c g;

    /* renamed from: a, reason: collision with root package name */
    private final Rect f357a = new Rect();

    /* renamed from: b, reason: collision with root package name */
    private final Rect f358b = new Rect();

    /* renamed from: c, reason: collision with root package name */
    private final Rect f359c = new Rect();

    /* renamed from: d, reason: collision with root package name */
    private final int[] f360d = new int[2];
    int h = RecyclerView.UNDEFINED_DURATION;
    int i = RecyclerView.UNDEFINED_DURATION;
    private int j = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: a.h.b.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0022a implements b.a<a.f.l.f0.c> {
        C0022a() {
        }

        @Override // a.h.b.b.a
        public void a(a.f.l.f0.c cVar, Rect rect) {
            cVar.a(rect);
        }
    }

    public a(View view) {
        if (view != null) {
            this.f362f = view;
            this.f361e = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            view.setFocusable(true);
            if (w.n(view) == 0) {
                w.i(view, 1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("View may not be null");
    }

    private boolean c() {
        int i = this.i;
        return i != Integer.MIN_VALUE && a(i, 16, (Bundle) null);
    }

    private a.f.l.f0.c d() {
        a.f.l.f0.c f2 = a.f.l.f0.c.f(this.f362f);
        w.a(this.f362f, f2);
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        if (f2.c() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            f2.a(this.f362f, ((Integer) arrayList.get(i)).intValue());
        }
        return f2;
    }

    private h<a.f.l.f0.c> e() {
        ArrayList arrayList = new ArrayList();
        a(arrayList);
        h<a.f.l.f0.c> hVar = new h<>();
        for (int i = 0; i < arrayList.size(); i++) {
            hVar.c(i, f(i));
        }
        return hVar;
    }

    private a.f.l.f0.c f(int i) {
        a.f.l.f0.c C = a.f.l.f0.c.C();
        C.g(true);
        C.h(true);
        C.a("android.view.View");
        C.c(k);
        C.d(k);
        C.b(this.f362f);
        a(i, C);
        if (C.j() == null && C.f() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        C.a(this.f358b);
        if (!this.f358b.equals(k)) {
            int b2 = C.b();
            if ((b2 & 64) != 0) {
                throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
            }
            if ((b2 & 128) == 0) {
                C.e(this.f362f.getContext().getPackageName());
                C.c(this.f362f, i);
                if (this.h == i) {
                    C.a(true);
                    C.a(128);
                } else {
                    C.a(false);
                    C.a(64);
                }
                boolean z = this.i == i;
                if (z) {
                    C.a(2);
                } else if (C.q()) {
                    C.a(1);
                }
                C.i(z);
                this.f362f.getLocationOnScreen(this.f360d);
                C.b(this.f357a);
                if (this.f357a.equals(k)) {
                    C.a(this.f357a);
                    if (C.f308b != -1) {
                        a.f.l.f0.c C2 = a.f.l.f0.c.C();
                        for (int i2 = C.f308b; i2 != -1; i2 = C2.f308b) {
                            C2.b(this.f362f, -1);
                            C2.c(k);
                            a(i2, C2);
                            C2.a(this.f358b);
                            Rect rect = this.f357a;
                            Rect rect2 = this.f358b;
                            rect.offset(rect2.left, rect2.top);
                        }
                        C2.y();
                    }
                    this.f357a.offset(this.f360d[0] - this.f362f.getScrollX(), this.f360d[1] - this.f362f.getScrollY());
                }
                if (this.f362f.getLocalVisibleRect(this.f359c)) {
                    this.f359c.offset(this.f360d[0] - this.f362f.getScrollX(), this.f360d[1] - this.f362f.getScrollY());
                    if (this.f357a.intersect(this.f359c)) {
                        C.d(this.f357a);
                        if (a(this.f357a)) {
                            C.p(true);
                        }
                    }
                }
                return C;
            }
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
    }

    private static int g(int i) {
        if (i == 19) {
            return 33;
        }
        if (i == 21) {
            return 17;
        }
        if (i != 22) {
            return GattError.GATT_WRONG_STATE;
        }
        return 66;
    }

    private boolean h(int i) {
        int i2;
        if (!this.f361e.isEnabled() || !this.f361e.isTouchExplorationEnabled() || (i2 = this.h) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            d(i2);
        }
        this.h = i;
        this.f362f.invalidate();
        a(i, DfuBaseService.ERROR_CONNECTION_STATE_MASK);
        return true;
    }

    private void i(int i) {
        int i2 = this.j;
        if (i2 == i) {
            return;
        }
        this.j = i;
        a(i, 128);
        a(i2, 256);
    }

    protected abstract int a(float f2, float f3);

    protected abstract void a(int i, a.f.l.f0.c cVar);

    protected void a(int i, AccessibilityEvent accessibilityEvent) {
    }

    protected void a(int i, boolean z) {
    }

    protected void a(a.f.l.f0.c cVar) {
    }

    protected void a(AccessibilityEvent accessibilityEvent) {
    }

    protected abstract void a(List<Integer> list);

    protected abstract boolean a(int i, int i2, Bundle bundle);

    public final boolean a(MotionEvent motionEvent) {
        if (!this.f361e.isEnabled() || !this.f361e.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7 && action != 9) {
            if (action != 10 || this.j == Integer.MIN_VALUE) {
                return false;
            }
            i(RecyclerView.UNDEFINED_DURATION);
            return true;
        }
        int a2 = a(motionEvent.getX(), motionEvent.getY());
        i(a2);
        return a2 != Integer.MIN_VALUE;
    }

    public final int b() {
        return this.i;
    }

    @Override // a.f.l.a
    public d getAccessibilityNodeProvider(View view) {
        if (this.g == null) {
            this.g = new c();
        }
        return this.g;
    }

    @Override // a.f.l.a
    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        a(accessibilityEvent);
    }

    @Override // a.f.l.a
    public void onInitializeAccessibilityNodeInfo(View view, a.f.l.f0.c cVar) {
        super.onInitializeAccessibilityNodeInfo(view, cVar);
        a(cVar);
    }

    /* loaded from: classes.dex */
    static class b implements b.InterfaceC0023b<h<a.f.l.f0.c>, a.f.l.f0.c> {
        b() {
        }

        @Override // a.h.b.b.InterfaceC0023b
        public a.f.l.f0.c a(h<a.f.l.f0.c> hVar, int i) {
            return hVar.e(i);
        }

        @Override // a.h.b.b.InterfaceC0023b
        public int a(h<a.f.l.f0.c> hVar) {
            return hVar.b();
        }
    }

    private boolean b(int i, Rect rect) {
        a.f.l.f0.c cVar;
        h<a.f.l.f0.c> e2 = e();
        int i2 = this.i;
        int i3 = RecyclerView.UNDEFINED_DURATION;
        a.f.l.f0.c a2 = i2 == Integer.MIN_VALUE ? null : e2.a(i2);
        if (i == 1 || i == 2) {
            cVar = (a.f.l.f0.c) a.h.b.b.a(e2, m, l, a2, i, w.q(this.f362f) == 1, false);
        } else {
            if (i != 17 && i != 33 && i != 66 && i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i4 = this.i;
            if (i4 != Integer.MIN_VALUE) {
                a(i4, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                a(this.f362f, i, rect2);
            }
            cVar = (a.f.l.f0.c) a.h.b.b.a(e2, m, l, a2, rect2, i);
        }
        if (cVar != null) {
            i3 = e2.c(e2.a((h<a.f.l.f0.c>) cVar));
        }
        return c(i3);
    }

    private AccessibilityEvent c(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        a.f.l.f0.c b2 = b(i);
        obtain.getText().add(b2.j());
        obtain.setContentDescription(b2.f());
        obtain.setScrollable(b2.u());
        obtain.setPassword(b2.t());
        obtain.setEnabled(b2.p());
        obtain.setChecked(b2.n());
        a(i, obtain);
        if (obtain.getText().isEmpty() && obtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        obtain.setClassName(b2.d());
        e.a(obtain, this.f362f, i);
        obtain.setPackageName(this.f362f.getContext().getPackageName());
        return obtain;
    }

    /* loaded from: classes.dex */
    private class c extends d {
        c() {
        }

        @Override // a.f.l.f0.d
        public a.f.l.f0.c a(int i) {
            return a.f.l.f0.c.a(a.this.b(i));
        }

        @Override // a.f.l.f0.d
        public a.f.l.f0.c b(int i) {
            int i2 = i == 2 ? a.this.h : a.this.i;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i2);
        }

        @Override // a.f.l.f0.d
        public boolean a(int i, int i2, Bundle bundle) {
            return a.this.b(i, i2, bundle);
        }
    }

    private AccessibilityEvent e(int i) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i);
        this.f362f.onInitializeAccessibilityEvent(obtain);
        return obtain;
    }

    public final boolean a(KeyEvent keyEvent) {
        int i = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 61) {
            if (keyCode != 66) {
                switch (keyCode) {
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                        if (!keyEvent.hasNoModifiers()) {
                            return false;
                        }
                        int g = g(keyCode);
                        int repeatCount = keyEvent.getRepeatCount() + 1;
                        boolean z = false;
                        while (i < repeatCount && b(g, (Rect) null)) {
                            i++;
                            z = true;
                        }
                        return z;
                    case 23:
                        break;
                    default:
                        return false;
                }
            }
            if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
                return false;
            }
            c();
            return true;
        }
        if (keyEvent.hasNoModifiers()) {
            return b(2, (Rect) null);
        }
        if (keyEvent.hasModifiers(1)) {
            return b(1, (Rect) null);
        }
        return false;
    }

    private boolean d(int i) {
        if (this.h != i) {
            return false;
        }
        this.h = RecyclerView.UNDEFINED_DURATION;
        this.f362f.invalidate();
        a(i, 65536);
        return true;
    }

    private boolean c(int i, int i2, Bundle bundle) {
        if (i2 == 1) {
            return c(i);
        }
        if (i2 == 2) {
            return a(i);
        }
        if (i2 == 64) {
            return h(i);
        }
        if (i2 != 128) {
            return a(i, i2, bundle);
        }
        return d(i);
    }

    private AccessibilityEvent b(int i, int i2) {
        if (i != -1) {
            return c(i, i2);
        }
        return e(i2);
    }

    public final void a(boolean z, int i, Rect rect) {
        int i2 = this.i;
        if (i2 != Integer.MIN_VALUE) {
            a(i2);
        }
        if (z) {
            b(i, rect);
        }
    }

    a.f.l.f0.c b(int i) {
        if (i == -1) {
            return d();
        }
        return f(i);
    }

    public final boolean c(int i) {
        int i2;
        if ((!this.f362f.isFocused() && !this.f362f.requestFocus()) || (i2 = this.i) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            a(i2);
        }
        this.i = i;
        a(i, true);
        a(i, 8);
        return true;
    }

    boolean b(int i, int i2, Bundle bundle) {
        if (i != -1) {
            return c(i, i2, bundle);
        }
        return a(i2, bundle);
    }

    public final int a() {
        return this.h;
    }

    private void a(int i, Rect rect) {
        b(i).a(rect);
    }

    private static Rect a(View view, int i, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i == 17) {
            rect.set(width, 0, width, height);
        } else if (i == 33) {
            rect.set(0, height, width, height);
        } else if (i == 66) {
            rect.set(-1, 0, -1, height);
        } else if (i == 130) {
            rect.set(0, -1, width, -1);
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return rect;
    }

    public final boolean a(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.f361e.isEnabled() || (parent = this.f362f.getParent()) == null) {
            return false;
        }
        return z.a(parent, this.f362f, b(i, i2));
    }

    private boolean a(int i, Bundle bundle) {
        return w.a(this.f362f, i, bundle);
    }

    private boolean a(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f362f.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.f362f.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    public final boolean a(int i) {
        if (this.i != i) {
            return false;
        }
        this.i = RecyclerView.UNDEFINED_DURATION;
        a(i, false);
        a(i, 8);
        return true;
    }
}
