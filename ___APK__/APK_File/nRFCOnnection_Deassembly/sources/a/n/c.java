package a.n;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

/* loaded from: classes.dex */
public class c extends m {
    private static final String[] N = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property<Drawable, PointF> O = new b(PointF.class, "boundsOrigin");
    private static final Property<k, PointF> P = new C0032c(PointF.class, "topLeft");
    private static final Property<k, PointF> Q = new d(PointF.class, "bottomRight");
    private static final Property<View, PointF> R = new e(PointF.class, "bottomRight");
    private static final Property<View, PointF> S = new f(PointF.class, "topLeft");
    private static final Property<View, PointF> T = new g(PointF.class, "position");
    private static a.n.k U = new a.n.k();
    private int[] K = new int[2];
    private boolean L = false;
    private boolean M = false;

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f426a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ BitmapDrawable f427b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f428c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ float f429d;

        a(c cVar, ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f2) {
            this.f426a = viewGroup;
            this.f427b = bitmapDrawable;
            this.f428c = view;
            this.f429d = f2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.b(this.f426a).b(this.f427b);
            c0.a(this.f428c, this.f429d);
        }
    }

    /* renamed from: a.n.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0032c extends Property<k, PointF> {
        C0032c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.b(pointF);
        }
    }

    /* loaded from: classes.dex */
    static class d extends Property<k, PointF> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.a(pointF);
        }
    }

    /* loaded from: classes.dex */
    static class e extends Property<View, PointF> {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            c0.a(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    /* loaded from: classes.dex */
    static class f extends Property<View, PointF> {
        f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            c0.a(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    /* loaded from: classes.dex */
    static class g extends Property<View, PointF> {
        g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            c0.a(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }
    }

    /* loaded from: classes.dex */
    class h extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ k f431a;
        private k mViewBounds;

        h(c cVar, k kVar) {
            this.f431a = kVar;
            this.mViewBounds = this.f431a;
        }
    }

    /* loaded from: classes.dex */
    class i extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private boolean f432a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f433b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Rect f434c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f435d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f436e;

        /* renamed from: f, reason: collision with root package name */
        final /* synthetic */ int f437f;
        final /* synthetic */ int g;

        i(c cVar, View view, Rect rect, int i, int i2, int i3, int i4) {
            this.f433b = view;
            this.f434c = rect;
            this.f435d = i;
            this.f436e = i2;
            this.f437f = i3;
            this.g = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f432a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f432a) {
                return;
            }
            a.f.l.w.a(this.f433b, this.f434c);
            c0.a(this.f433b, this.f435d, this.f436e, this.f437f, this.g);
        }
    }

    /* loaded from: classes.dex */
    class j extends n {

        /* renamed from: a, reason: collision with root package name */
        boolean f438a = false;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewGroup f439b;

        j(c cVar, ViewGroup viewGroup) {
            this.f439b = viewGroup;
        }

        @Override // a.n.n, a.n.m.f
        public void b(m mVar) {
            x.b(this.f439b, false);
            this.f438a = true;
        }

        @Override // a.n.n, a.n.m.f
        public void c(m mVar) {
            x.b(this.f439b, false);
        }

        @Override // a.n.n, a.n.m.f
        public void d(m mVar) {
            x.b(this.f439b, true);
        }

        @Override // a.n.m.f
        public void e(m mVar) {
            if (!this.f438a) {
                x.b(this.f439b, false);
            }
            mVar.b(this);
        }
    }

    private void d(s sVar) {
        View view = sVar.f518b;
        if (!a.f.l.w.F(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        sVar.f517a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        sVar.f517a.put("android:changeBounds:parent", sVar.f518b.getParent());
        if (this.M) {
            sVar.f518b.getLocationInWindow(this.K);
            sVar.f517a.put("android:changeBounds:windowX", Integer.valueOf(this.K[0]));
            sVar.f517a.put("android:changeBounds:windowY", Integer.valueOf(this.K[1]));
        }
        if (this.L) {
            sVar.f517a.put("android:changeBounds:clip", a.f.l.w.j(view));
        }
    }

    @Override // a.n.m
    public void a(s sVar) {
        d(sVar);
    }

    @Override // a.n.m
    public void c(s sVar) {
        d(sVar);
    }

    @Override // a.n.m
    public String[] n() {
        return N;
    }

    private boolean a(View view, View view2) {
        if (!this.M) {
            return true;
        }
        s a2 = a(view, true);
        if (a2 == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == a2.f518b) {
            return true;
        }
        return false;
    }

    /* loaded from: classes.dex */
    static class b extends Property<Drawable, PointF> {

        /* renamed from: a, reason: collision with root package name */
        private Rect f430a;

        b(Class cls, String str) {
            super(cls, str);
            this.f430a = new Rect();
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f430a);
            this.f430a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f430a);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f430a);
            Rect rect = this.f430a;
            return new PointF(rect.left, rect.top);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k {

        /* renamed from: a, reason: collision with root package name */
        private int f440a;

        /* renamed from: b, reason: collision with root package name */
        private int f441b;

        /* renamed from: c, reason: collision with root package name */
        private int f442c;

        /* renamed from: d, reason: collision with root package name */
        private int f443d;

        /* renamed from: e, reason: collision with root package name */
        private View f444e;

        /* renamed from: f, reason: collision with root package name */
        private int f445f;
        private int g;

        k(View view) {
            this.f444e = view;
        }

        void a(PointF pointF) {
            this.f442c = Math.round(pointF.x);
            this.f443d = Math.round(pointF.y);
            this.g++;
            if (this.f445f == this.g) {
                a();
            }
        }

        void b(PointF pointF) {
            this.f440a = Math.round(pointF.x);
            this.f441b = Math.round(pointF.y);
            this.f445f++;
            if (this.f445f == this.g) {
                a();
            }
        }

        private void a() {
            c0.a(this.f444e, this.f440a, this.f441b, this.f442c, this.f443d);
            this.f445f = 0;
            this.g = 0;
        }
    }

    @Override // a.n.m
    public Animator a(ViewGroup viewGroup, s sVar, s sVar2) {
        int i2;
        View view;
        int i3;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator a2;
        if (sVar == null || sVar2 == null) {
            return null;
        }
        Map<String, Object> map = sVar.f517a;
        Map<String, Object> map2 = sVar2.f517a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = sVar2.f518b;
        if (a(viewGroup2, viewGroup3)) {
            Rect rect2 = (Rect) sVar.f517a.get("android:changeBounds:bounds");
            Rect rect3 = (Rect) sVar2.f517a.get("android:changeBounds:bounds");
            int i4 = rect2.left;
            int i5 = rect3.left;
            int i6 = rect2.top;
            int i7 = rect3.top;
            int i8 = rect2.right;
            int i9 = rect3.right;
            int i10 = rect2.bottom;
            int i11 = rect3.bottom;
            int i12 = i8 - i4;
            int i13 = i10 - i6;
            int i14 = i9 - i5;
            int i15 = i11 - i7;
            Rect rect4 = (Rect) sVar.f517a.get("android:changeBounds:clip");
            Rect rect5 = (Rect) sVar2.f517a.get("android:changeBounds:clip");
            if ((i12 == 0 || i13 == 0) && (i14 == 0 || i15 == 0)) {
                i2 = 0;
            } else {
                i2 = (i4 == i5 && i6 == i7) ? 0 : 1;
                if (i8 != i9 || i10 != i11) {
                    i2++;
                }
            }
            if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
                i2++;
            }
            if (i2 <= 0) {
                return null;
            }
            if (!this.L) {
                view = view2;
                c0.a(view, i4, i6, i8, i10);
                if (i2 == 2) {
                    if (i12 == i14 && i13 == i15) {
                        a2 = a.n.f.a(view, T, g().a(i4, i6, i5, i7));
                    } else {
                        k kVar = new k(view);
                        ObjectAnimator a3 = a.n.f.a(kVar, P, g().a(i4, i6, i5, i7));
                        ObjectAnimator a4 = a.n.f.a(kVar, Q, g().a(i8, i10, i9, i11));
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(a3, a4);
                        animatorSet.addListener(new h(this, kVar));
                        a2 = animatorSet;
                    }
                } else if (i4 == i5 && i6 == i7) {
                    a2 = a.n.f.a(view, R, g().a(i8, i10, i9, i11));
                } else {
                    a2 = a.n.f.a(view, S, g().a(i4, i6, i5, i7));
                }
            } else {
                view = view2;
                c0.a(view, i4, i6, Math.max(i12, i14) + i4, Math.max(i13, i15) + i6);
                ObjectAnimator a5 = (i4 == i5 && i6 == i7) ? null : a.n.f.a(view, T, g().a(i4, i6, i5, i7));
                if (rect4 == null) {
                    i3 = 0;
                    rect = new Rect(0, 0, i12, i13);
                } else {
                    i3 = 0;
                    rect = rect4;
                }
                Rect rect6 = rect5 == null ? new Rect(i3, i3, i14, i15) : rect5;
                if (rect.equals(rect6)) {
                    objectAnimator = null;
                } else {
                    a.f.l.w.a(view, rect);
                    a.n.k kVar2 = U;
                    Object[] objArr = new Object[2];
                    objArr[i3] = rect;
                    objArr[1] = rect6;
                    objectAnimator = ObjectAnimator.ofObject(view, "clipBounds", kVar2, objArr);
                    objectAnimator.addListener(new i(this, view, rect5, i5, i7, i9, i11));
                }
                a2 = r.a(a5, objectAnimator);
            }
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup4 = (ViewGroup) view.getParent();
                x.b(viewGroup4, true);
                a(new j(this, viewGroup4));
            }
            return a2;
        }
        int intValue = ((Integer) sVar.f517a.get("android:changeBounds:windowX")).intValue();
        int intValue2 = ((Integer) sVar.f517a.get("android:changeBounds:windowY")).intValue();
        int intValue3 = ((Integer) sVar2.f517a.get("android:changeBounds:windowX")).intValue();
        int intValue4 = ((Integer) sVar2.f517a.get("android:changeBounds:windowY")).intValue();
        if (intValue == intValue3 && intValue2 == intValue4) {
            return null;
        }
        viewGroup.getLocationInWindow(this.K);
        Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
        view2.draw(new Canvas(createBitmap));
        BitmapDrawable bitmapDrawable = new BitmapDrawable(createBitmap);
        float c2 = c0.c(view2);
        c0.a(view2, 0.0f);
        c0.b(viewGroup).a(bitmapDrawable);
        a.n.g g2 = g();
        int[] iArr = this.K;
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, a.n.i.a(O, g2.a(intValue - iArr[0], intValue2 - iArr[1], intValue3 - iArr[0], intValue4 - iArr[1])));
        ofPropertyValuesHolder.addListener(new a(this, viewGroup, bitmapDrawable, view2, c2));
        return ofPropertyValuesHolder;
    }
}
