package com.google.android.material.behavior;

import a.f.l.f0.c;
import a.f.l.f0.f;
import a.f.l.w;
import a.h.b.c;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.c<V> {

    /* renamed from: a, reason: collision with root package name */
    a.h.b.c f2377a;

    /* renamed from: b, reason: collision with root package name */
    c f2378b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2379c;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2381e;

    /* renamed from: d, reason: collision with root package name */
    private float f2380d = 0.0f;

    /* renamed from: f, reason: collision with root package name */
    int f2382f = 2;
    float g = 0.5f;
    float h = 0.0f;
    float i = 0.5f;
    private final c.AbstractC0024c j = new a();

    /* loaded from: classes.dex */
    class a extends c.AbstractC0024c {

        /* renamed from: a, reason: collision with root package name */
        private int f2383a;

        /* renamed from: b, reason: collision with root package name */
        private int f2384b = -1;

        a() {
        }

        private boolean a(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.f2383a) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.g);
            }
            boolean z = w.q(view) == 1;
            int i = SwipeDismissBehavior.this.f2382f;
            if (i == 2) {
                return true;
            }
            if (i == 0) {
                if (z) {
                    if (f2 >= 0.0f) {
                        return false;
                    }
                } else if (f2 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i != 1) {
                return false;
            }
            if (z) {
                if (f2 <= 0.0f) {
                    return false;
                }
            } else if (f2 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // a.h.b.c.AbstractC0024c
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int width;
            int width2;
            int width3;
            boolean z = w.q(view) == 1;
            int i3 = SwipeDismissBehavior.this.f2382f;
            if (i3 == 0) {
                if (z) {
                    width = this.f2383a - view.getWidth();
                    width2 = this.f2383a;
                } else {
                    width = this.f2383a;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i3 != 1) {
                width = this.f2383a - view.getWidth();
                width2 = view.getWidth() + this.f2383a;
            } else if (z) {
                width = this.f2383a;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.f2383a - view.getWidth();
                width2 = this.f2383a;
            }
            return SwipeDismissBehavior.a(width, i, width2);
        }

        @Override // a.h.b.c.AbstractC0024c
        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // a.h.b.c.AbstractC0024c
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewCaptured(View view, int i) {
            this.f2384b = i;
            this.f2383a = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewDragStateChanged(int i) {
            c cVar = SwipeDismissBehavior.this.f2378b;
            if (cVar != null) {
                cVar.a(i);
            }
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float width = this.f2383a + (view.getWidth() * SwipeDismissBehavior.this.h);
            float width2 = this.f2383a + (view.getWidth() * SwipeDismissBehavior.this.i);
            float f2 = i;
            if (f2 <= width) {
                view.setAlpha(1.0f);
            } else if (f2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.a(0.0f, 1.0f - SwipeDismissBehavior.b(width, width2, f2), 1.0f));
            }
        }

        @Override // a.h.b.c.AbstractC0024c
        public void onViewReleased(View view, float f2, float f3) {
            int i;
            boolean z;
            c cVar;
            this.f2384b = -1;
            int width = view.getWidth();
            if (a(view, f2)) {
                int left = view.getLeft();
                int i2 = this.f2383a;
                i = left < i2 ? i2 - width : i2 + width;
                z = true;
            } else {
                i = this.f2383a;
                z = false;
            }
            if (SwipeDismissBehavior.this.f2377a.d(i, view.getTop())) {
                w.a(view, new d(view, z));
            } else {
                if (!z || (cVar = SwipeDismissBehavior.this.f2378b) == null) {
                    return;
                }
                cVar.a(view);
            }
        }

        @Override // a.h.b.c.AbstractC0024c
        public boolean tryCaptureView(View view, int i) {
            int i2 = this.f2384b;
            return (i2 == -1 || i2 == i) && SwipeDismissBehavior.this.a(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements f {
        b() {
        }

        @Override // a.f.l.f0.f
        public boolean a(View view, f.a aVar) {
            boolean z = false;
            if (!SwipeDismissBehavior.this.a(view)) {
                return false;
            }
            boolean z2 = w.q(view) == 1;
            if ((SwipeDismissBehavior.this.f2382f == 0 && z2) || (SwipeDismissBehavior.this.f2382f == 1 && !z2)) {
                z = true;
            }
            int width = view.getWidth();
            if (z) {
                width = -width;
            }
            w.e(view, width);
            view.setAlpha(0.0f);
            c cVar = SwipeDismissBehavior.this.f2378b;
            if (cVar != null) {
                cVar.a(view);
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void a(int i);

        void a(View view);
    }

    /* loaded from: classes.dex */
    private class d implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private final View f2387b;

        /* renamed from: c, reason: collision with root package name */
        private final boolean f2388c;

        d(View view, boolean z) {
            this.f2387b = view;
            this.f2388c = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar;
            a.h.b.c cVar2 = SwipeDismissBehavior.this.f2377a;
            if (cVar2 != null && cVar2.a(true)) {
                w.a(this.f2387b, this);
            } else {
                if (!this.f2388c || (cVar = SwipeDismissBehavior.this.f2378b) == null) {
                    return;
                }
                cVar.a(this.f2387b);
            }
        }
    }

    static float b(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    public void a(c cVar) {
        this.f2378b = cVar;
    }

    public boolean a(View view) {
        return true;
    }

    public void b(float f2) {
        this.h = a(0.0f, f2, 1.0f);
    }

    public void a(int i) {
        this.f2382f = i;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean b(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        a.h.b.c cVar = this.f2377a;
        if (cVar == null) {
            return false;
        }
        cVar.a(motionEvent);
        return true;
    }

    public void a(float f2) {
        this.i = a(0.0f, f2, 1.0f);
    }

    private void b(View view) {
        w.g(view, AppearanceLibrary.MASK_BEACON);
        if (a(view)) {
            w.a(view, c.a.l, null, new b());
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, int i) {
        boolean a2 = super.a(coordinatorLayout, (CoordinatorLayout) v, i);
        if (w.n(v) == 0) {
            w.i(v, 1);
            b(v);
        }
        return a2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.f2379c;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f2379c = coordinatorLayout.a(v, (int) motionEvent.getX(), (int) motionEvent.getY());
            z = this.f2379c;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f2379c = false;
        }
        if (!z) {
            return false;
        }
        a((ViewGroup) coordinatorLayout);
        return this.f2377a.b(motionEvent);
    }

    private void a(ViewGroup viewGroup) {
        a.h.b.c a2;
        if (this.f2377a == null) {
            if (this.f2381e) {
                a2 = a.h.b.c.a(viewGroup, this.f2380d, this.j);
            } else {
                a2 = a.h.b.c.a(viewGroup, this.j);
            }
            this.f2377a = a2;
        }
    }

    static float a(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    static int a(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }
}
