package a.n;

import a.n.a;
import a.n.m;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class j0 extends m {
    private static final String[] L = {"android:visibility:visibility", "android:visibility:parent"};
    private int K = 3;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends n {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f471a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f472b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f473c;

        a(ViewGroup viewGroup, View view, View view2) {
            this.f471a = viewGroup;
            this.f472b = view;
            this.f473c = view2;
        }

        @Override // a.n.n, a.n.m.f
        public void c(m mVar) {
            x.a(this.f471a).b(this.f472b);
        }

        @Override // a.n.n, a.n.m.f
        public void d(m mVar) {
            if (this.f472b.getParent() == null) {
                x.a(this.f471a).a(this.f472b);
            } else {
                j0.this.a();
            }
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            this.f473c.setTag(j.save_overlay_view, null);
            x.a(this.f471a).b(this.f472b);
            mVar.b(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        boolean f481a;

        /* renamed from: b, reason: collision with root package name */
        boolean f482b;

        /* renamed from: c, reason: collision with root package name */
        int f483c;

        /* renamed from: d, reason: collision with root package name */
        int f484d;

        /* renamed from: e, reason: collision with root package name */
        ViewGroup f485e;

        /* renamed from: f, reason: collision with root package name */
        ViewGroup f486f;

        c() {
        }
    }

    private c b(s sVar, s sVar2) {
        c cVar = new c();
        cVar.f481a = false;
        cVar.f482b = false;
        if (sVar != null && sVar.f517a.containsKey("android:visibility:visibility")) {
            cVar.f483c = ((Integer) sVar.f517a.get("android:visibility:visibility")).intValue();
            cVar.f485e = (ViewGroup) sVar.f517a.get("android:visibility:parent");
        } else {
            cVar.f483c = -1;
            cVar.f485e = null;
        }
        if (sVar2 != null && sVar2.f517a.containsKey("android:visibility:visibility")) {
            cVar.f484d = ((Integer) sVar2.f517a.get("android:visibility:visibility")).intValue();
            cVar.f486f = (ViewGroup) sVar2.f517a.get("android:visibility:parent");
        } else {
            cVar.f484d = -1;
            cVar.f486f = null;
        }
        if (sVar != null && sVar2 != null) {
            if (cVar.f483c == cVar.f484d && cVar.f485e == cVar.f486f) {
                return cVar;
            }
            int i = cVar.f483c;
            int i2 = cVar.f484d;
            if (i != i2) {
                if (i == 0) {
                    cVar.f482b = false;
                    cVar.f481a = true;
                } else if (i2 == 0) {
                    cVar.f482b = true;
                    cVar.f481a = true;
                }
            } else if (cVar.f486f == null) {
                cVar.f482b = false;
                cVar.f481a = true;
            } else if (cVar.f485e == null) {
                cVar.f482b = true;
                cVar.f481a = true;
            }
        } else if (sVar == null && cVar.f484d == 0) {
            cVar.f482b = true;
            cVar.f481a = true;
        } else if (sVar2 == null && cVar.f483c == 0) {
            cVar.f482b = false;
            cVar.f481a = true;
        }
        return cVar;
    }

    private void d(s sVar) {
        sVar.f517a.put("android:visibility:visibility", Integer.valueOf(sVar.f518b.getVisibility()));
        sVar.f517a.put("android:visibility:parent", sVar.f518b.getParent());
        int[] iArr = new int[2];
        sVar.f518b.getLocationOnScreen(iArr);
        sVar.f517a.put("android:visibility:screenLocation", iArr);
    }

    public abstract Animator a(ViewGroup viewGroup, View view, s sVar, s sVar2);

    public void a(int i) {
        if ((i & (-4)) == 0) {
            this.K = i;
            return;
        }
        throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
    }

    public abstract Animator b(ViewGroup viewGroup, View view, s sVar, s sVar2);

    @Override // a.n.m
    public void c(s sVar) {
        d(sVar);
    }

    @Override // a.n.m
    public String[] n() {
        return L;
    }

    @Override // a.n.m
    public void a(s sVar) {
        d(sVar);
    }

    @Override // a.n.m
    public Animator a(ViewGroup viewGroup, s sVar, s sVar2) {
        c b2 = b(sVar, sVar2);
        if (!b2.f481a) {
            return null;
        }
        if (b2.f485e == null && b2.f486f == null) {
            return null;
        }
        if (b2.f482b) {
            return a(viewGroup, sVar, b2.f483c, sVar2, b2.f484d);
        }
        return b(viewGroup, sVar, b2.f483c, sVar2, b2.f484d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b extends AnimatorListenerAdapter implements m.f, a.InterfaceC0031a {

        /* renamed from: a, reason: collision with root package name */
        private final View f475a;

        /* renamed from: b, reason: collision with root package name */
        private final int f476b;

        /* renamed from: c, reason: collision with root package name */
        private final ViewGroup f477c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f478d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f479e;

        /* renamed from: f, reason: collision with root package name */
        boolean f480f = false;

        b(View view, int i, boolean z) {
            this.f475a = view;
            this.f476b = i;
            this.f477c = (ViewGroup) view.getParent();
            this.f478d = z;
            a(true);
        }

        private void a() {
            if (!this.f480f) {
                c0.a(this.f475a, this.f476b);
                ViewGroup viewGroup = this.f477c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            a(false);
        }

        @Override // a.n.m.f
        public void a(m mVar) {
        }

        @Override // a.n.m.f
        public void b(m mVar) {
        }

        @Override // a.n.m.f
        public void c(m mVar) {
            a(false);
        }

        @Override // a.n.m.f
        public void d(m mVar) {
            a(true);
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            a();
            mVar.b(this);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f480f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, a.n.a.InterfaceC0031a
        public void onAnimationPause(Animator animator) {
            if (this.f480f) {
                return;
            }
            c0.a(this.f475a, this.f476b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, a.n.a.InterfaceC0031a
        public void onAnimationResume(Animator animator) {
            if (this.f480f) {
                return;
            }
            c0.a(this.f475a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        private void a(boolean z) {
            ViewGroup viewGroup;
            if (!this.f478d || this.f479e == z || (viewGroup = this.f477c) == null) {
                return;
            }
            this.f479e = z;
            x.b(viewGroup, z);
        }
    }

    public Animator a(ViewGroup viewGroup, s sVar, int i, s sVar2, int i2) {
        if ((this.K & 1) != 1 || sVar2 == null) {
            return null;
        }
        if (sVar == null) {
            View view = (View) sVar2.f518b.getParent();
            if (b(a(view, false), b(view, false)).f481a) {
                return null;
            }
        }
        return a(viewGroup, sVar2.f518b, sVar, sVar2);
    }

    @Override // a.n.m
    public boolean a(s sVar, s sVar2) {
        if (sVar == null && sVar2 == null) {
            return false;
        }
        if (sVar != null && sVar2 != null && sVar2.f517a.containsKey("android:visibility:visibility") != sVar.f517a.containsKey("android:visibility:visibility")) {
            return false;
        }
        c b2 = b(sVar, sVar2);
        if (b2.f481a) {
            return b2.f483c == 0 || b2.f484d == 0;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0080, code lost:
    
        if (r9.w != false) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator b(android.view.ViewGroup r10, a.n.s r11, int r12, a.n.s r13, int r14) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: a.n.j0.b(android.view.ViewGroup, a.n.s, int, a.n.s, int):android.animation.Animator");
    }
}
