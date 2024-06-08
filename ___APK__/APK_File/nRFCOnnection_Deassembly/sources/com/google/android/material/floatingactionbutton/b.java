package com.google.android.material.floatingactionbutton;

import a.f.l.w;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import c.a.a.a.b0.n;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b {
    static final TimeInterpolator F = c.a.a.a.m.a.f2095c;
    static final int[] G = {R.attr.state_pressed, R.attr.state_enabled};
    static final int[] H = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    static final int[] I = {R.attr.state_focused, R.attr.state_enabled};
    static final int[] J = {R.attr.state_hovered, R.attr.state_enabled};
    static final int[] K = {R.attr.state_enabled};
    static final int[] L = new int[0];
    private ViewTreeObserver.OnPreDrawListener E;

    /* renamed from: a, reason: collision with root package name */
    c.a.a.a.b0.k f2542a;

    /* renamed from: b, reason: collision with root package name */
    c.a.a.a.b0.g f2543b;

    /* renamed from: c, reason: collision with root package name */
    Drawable f2544c;

    /* renamed from: d, reason: collision with root package name */
    com.google.android.material.floatingactionbutton.a f2545d;

    /* renamed from: e, reason: collision with root package name */
    Drawable f2546e;

    /* renamed from: f, reason: collision with root package name */
    boolean f2547f;
    float h;
    float i;
    float j;
    int k;
    private c.a.a.a.m.h m;
    private c.a.a.a.m.h n;
    private Animator o;
    private c.a.a.a.m.h p;
    private c.a.a.a.m.h q;
    private float r;
    private int t;
    private ArrayList<Animator.AnimatorListener> v;
    private ArrayList<Animator.AnimatorListener> w;
    private ArrayList<h> x;
    final FloatingActionButton y;
    final c.a.a.a.a0.b z;
    boolean g = true;
    private float s = 1.0f;
    private int u = 0;
    private final Rect A = new Rect();
    private final RectF B = new RectF();
    private final RectF C = new RectF();
    private final Matrix D = new Matrix();
    private final com.google.android.material.internal.j l = new com.google.android.material.internal.j();

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2548a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f2549b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ i f2550c;

        a(boolean z, i iVar) {
            this.f2549b = z;
            this.f2550c = iVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2548a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.u = 0;
            b.this.o = null;
            if (this.f2548a) {
                return;
            }
            b.this.y.a(this.f2549b ? 8 : 4, this.f2549b);
            i iVar = this.f2550c;
            if (iVar != null) {
                iVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.y.a(0, this.f2549b);
            b.this.u = 1;
            b.this.o = animator;
            this.f2548a = false;
        }
    }

    /* renamed from: com.google.android.material.floatingactionbutton.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0085b extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f2552a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ i f2553b;

        C0085b(boolean z, i iVar) {
            this.f2552a = z;
            this.f2553b = iVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.u = 0;
            b.this.o = null;
            i iVar = this.f2553b;
            if (iVar != null) {
                iVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.y.a(0, this.f2552a);
            b.this.u = 2;
            b.this.o = animator;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends c.a.a.a.m.g {
        c() {
        }

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
            b.this.s = f2;
            return super.evaluate(f2, matrix, matrix2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements ViewTreeObserver.OnPreDrawListener {
        d() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            b.this.p();
            return true;
        }
    }

    /* loaded from: classes.dex */
    private class e extends k {
        e(b bVar) {
            super(bVar, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.k
        protected float a() {
            return 0.0f;
        }
    }

    /* loaded from: classes.dex */
    private class f extends k {
        f() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.k
        protected float a() {
            b bVar = b.this;
            return bVar.h + bVar.i;
        }
    }

    /* loaded from: classes.dex */
    private class g extends k {
        g() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.k
        protected float a() {
            b bVar = b.this;
            return bVar.h + bVar.j;
        }
    }

    /* loaded from: classes.dex */
    interface h {
        void a();

        void b();
    }

    /* loaded from: classes.dex */
    interface i {
        void a();

        void b();
    }

    /* loaded from: classes.dex */
    private class j extends k {
        j() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.k
        protected float a() {
            return b.this.h;
        }
    }

    /* loaded from: classes.dex */
    private abstract class k extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2560a;

        /* renamed from: b, reason: collision with root package name */
        private float f2561b;

        /* renamed from: c, reason: collision with root package name */
        private float f2562c;

        private k() {
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.e((int) this.f2562c);
            this.f2560a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f2560a) {
                c.a.a.a.b0.g gVar = b.this.f2543b;
                this.f2561b = gVar == null ? 0.0f : gVar.e();
                this.f2562c = a();
                this.f2560a = true;
            }
            b bVar = b.this;
            float f2 = this.f2561b;
            bVar.e((int) (f2 + ((this.f2562c - f2) * valueAnimator.getAnimatedFraction())));
        }

        /* synthetic */ k(b bVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(FloatingActionButton floatingActionButton, c.a.a.a.a0.b bVar) {
        this.y = floatingActionButton;
        this.z = bVar;
        this.l.a(G, a((k) new g()));
        this.l.a(H, a((k) new f()));
        this.l.a(I, a((k) new f()));
        this.l.a(J, a((k) new f()));
        this.l.a(K, a((k) new j()));
        this.l.a(L, a((k) new e(this)));
        this.r = this.y.getRotation();
    }

    private ViewTreeObserver.OnPreDrawListener A() {
        if (this.E == null) {
            this.E = new d();
        }
        return this.E;
    }

    private boolean B() {
        return w.F(this.y) && !this.y.isInEditMode();
    }

    private c.a.a.a.m.h y() {
        if (this.n == null) {
            this.n = c.a.a.a.m.h.a(this.y.getContext(), c.a.a.a.a.design_fab_hide_motion_spec);
        }
        c.a.a.a.m.h hVar = this.n;
        a.f.k.h.a(hVar);
        return hVar;
    }

    private c.a.a.a.m.h z() {
        if (this.m == null) {
            this.m = c.a.a.a.m.h.a(this.y.getContext(), c.a.a.a.a.design_fab_show_motion_spec);
        }
        c.a.a.a.m.h hVar = this.m;
        a.f.k.h.a(hVar);
        return hVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i2) {
        this.k = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(float f2) {
        if (this.j != f2) {
            this.j = f2;
            a(this.h, this.i, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final c.a.a.a.m.h e() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float f() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float g() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final c.a.a.a.b0.k h() {
        return this.f2542a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final c.a.a.a.m.h i() {
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j() {
        return this.y.getVisibility() == 0 ? this.u == 1 : this.u != 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.y.getVisibility() != 0 ? this.u == 2 : this.u != 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l() {
        this.l.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        c.a.a.a.b0.g gVar = this.f2543b;
        if (gVar != null) {
            c.a.a.a.b0.h.a(this.y, gVar);
        }
        if (s()) {
            this.y.getViewTreeObserver().addOnPreDrawListener(A());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        ViewTreeObserver viewTreeObserver = this.y.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.E;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.E = null;
        }
    }

    void p() {
        float rotation = this.y.getRotation();
        if (this.r != rotation) {
            this.r = rotation;
            v();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q() {
        ArrayList<h> arrayList = this.x;
        if (arrayList != null) {
            Iterator<h> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r() {
        ArrayList<h> arrayList = this.x;
        if (arrayList != null) {
            Iterator<h> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    boolean s() {
        return true;
    }

    boolean t() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean u() {
        return !this.f2547f || this.y.getSizeDimension() >= this.k;
    }

    void v() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.r % 90.0f != 0.0f) {
                if (this.y.getLayerType() != 1) {
                    this.y.setLayerType(1, null);
                }
            } else if (this.y.getLayerType() != 0) {
                this.y.setLayerType(0, null);
            }
        }
        c.a.a.a.b0.g gVar = this.f2543b;
        if (gVar != null) {
            gVar.b((int) this.r);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void w() {
        c(this.s);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void x() {
        Rect rect = this.A;
        a(rect);
        b(rect);
        this.z.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ColorStateList colorStateList) {
        Drawable drawable = this.f2544c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, c.a.a.a.z.b.b(colorStateList));
        }
    }

    final void c(float f2) {
        this.s = f2;
        Matrix matrix = this.D;
        a(f2, matrix);
        this.y.setImageMatrix(matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(float f2) {
        c.a.a.a.b0.g gVar = this.f2543b;
        if (gVar != null) {
            gVar.b(f2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        this.f2543b = a();
        this.f2543b.setTintList(colorStateList);
        if (mode != null) {
            this.f2543b.setTintMode(mode);
        }
        this.f2543b.a(-12303292);
        this.f2543b.a(this.y.getContext());
        c.a.a.a.z.a aVar = new c.a.a.a.z.a(this.f2543b.k());
        aVar.setTintList(c.a.a.a.z.b.b(colorStateList2));
        this.f2544c = aVar;
        c.a.a.a.b0.g gVar = this.f2543b;
        a.f.k.h.a(gVar);
        this.f2546e = new LayerDrawable(new Drawable[]{gVar, aVar});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.f2547f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(float f2) {
        if (this.i != f2) {
            this.i = f2;
            a(this.h, this.i, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(c.a.a.a.m.h hVar) {
        this.p = hVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(boolean z) {
        this.g = z;
        x();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Animator.AnimatorListener animatorListener) {
        if (this.v == null) {
            this.v = new ArrayList<>();
        }
        this.v.add(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(i iVar, boolean z) {
        if (k()) {
            return;
        }
        Animator animator = this.o;
        if (animator != null) {
            animator.cancel();
        }
        if (B()) {
            if (this.y.getVisibility() != 0) {
                this.y.setAlpha(0.0f);
                this.y.setScaleY(0.0f);
                this.y.setScaleX(0.0f);
                c(0.0f);
            }
            c.a.a.a.m.h hVar = this.p;
            if (hVar == null) {
                hVar = z();
            }
            AnimatorSet a2 = a(hVar, 1.0f, 1.0f, 1.0f);
            a2.addListener(new C0085b(z, iVar));
            ArrayList<Animator.AnimatorListener> arrayList = this.v;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    a2.addListener(it.next());
                }
            }
            a2.start();
            return;
        }
        this.y.a(0, z);
        this.y.setAlpha(1.0f);
        this.y.setScaleY(1.0f);
        this.y.setScaleX(1.0f);
        c(1.0f);
        if (iVar != null) {
            iVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        c.a.a.a.b0.g gVar = this.f2543b;
        if (gVar != null) {
            gVar.setTintList(colorStateList);
        }
        com.google.android.material.floatingactionbutton.a aVar = this.f2545d;
        if (aVar != null) {
            aVar.a(colorStateList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        c.a.a.a.b0.g gVar = this.f2543b;
        if (gVar != null) {
            gVar.setTintMode(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(float f2) {
        if (this.h != f2) {
            this.h = f2;
            a(this.h, this.i, this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i2) {
        if (this.t != i2) {
            this.t = i2;
            w();
        }
    }

    private void a(float f2, Matrix matrix) {
        matrix.reset();
        if (this.y.getDrawable() == null || this.t == 0) {
            return;
        }
        RectF rectF = this.B;
        RectF rectF2 = this.C;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i2 = this.t;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.t;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(c.a.a.a.b0.k kVar) {
        this.f2542a = kVar;
        c.a.a.a.b0.g gVar = this.f2543b;
        if (gVar != null) {
            gVar.setShapeAppearanceModel(kVar);
        }
        Object obj = this.f2544c;
        if (obj instanceof n) {
            ((n) obj).setShapeAppearanceModel(kVar);
        }
        com.google.android.material.floatingactionbutton.a aVar = this.f2545d;
        if (aVar != null) {
            aVar.a(kVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Drawable b() {
        return this.f2546e;
    }

    void b(Rect rect) {
        a.f.k.h.a(this.f2546e, "Didn't initialize content background");
        if (t()) {
            this.z.a(new InsetDrawable(this.f2546e, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            this.z.a(this.f2546e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(c.a.a.a.m.h hVar) {
        this.q = hVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.f2547f = z;
    }

    void a(float f2, float f3, float f4) {
        x();
        e(f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int[] iArr) {
        this.l.a(iArr);
    }

    public void a(Animator.AnimatorListener animatorListener) {
        if (this.w == null) {
            this.w = new ArrayList<>();
        }
        this.w.add(animatorListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(i iVar, boolean z) {
        if (j()) {
            return;
        }
        Animator animator = this.o;
        if (animator != null) {
            animator.cancel();
        }
        if (B()) {
            c.a.a.a.m.h hVar = this.q;
            if (hVar == null) {
                hVar = y();
            }
            AnimatorSet a2 = a(hVar, 0.0f, 0.0f, 0.0f);
            a2.addListener(new a(z, iVar));
            ArrayList<Animator.AnimatorListener> arrayList = this.w;
            if (arrayList != null) {
                Iterator<Animator.AnimatorListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    a2.addListener(it.next());
                }
            }
            a2.start();
            return;
        }
        this.y.a(z ? 8 : 4, z);
        if (iVar != null) {
            iVar.b();
        }
    }

    private AnimatorSet a(c.a.a.a.m.h hVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        hVar.a("opacity").a((Animator) ofFloat);
        arrayList.add(ofFloat);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        hVar.a("scale").a((Animator) ofFloat2);
        arrayList.add(ofFloat2);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.y, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        hVar.a("scale").a((Animator) ofFloat3);
        arrayList.add(ofFloat3);
        a(f4, this.D);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(this.y, new c.a.a.a.m.f(), new c(), new Matrix(this.D));
        hVar.a("iconScale").a((Animator) ofObject);
        arrayList.add(ofObject);
        AnimatorSet animatorSet = new AnimatorSet();
        c.a.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(h hVar) {
        if (this.x == null) {
            this.x = new ArrayList<>();
        }
        this.x.add(hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Rect rect) {
        int sizeDimension = this.f2547f ? (this.k - this.y.getSizeDimension()) / 2 : 0;
        int max = Math.max(sizeDimension, (int) Math.ceil(this.g ? c() + this.j : 0.0f));
        int max2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(max, max2, max, max2);
    }

    c.a.a.a.b0.g a() {
        c.a.a.a.b0.k kVar = this.f2542a;
        a.f.k.h.a(kVar);
        return new c.a.a.a.b0.g(kVar);
    }

    private ValueAnimator a(k kVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(F);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(kVar);
        valueAnimator.addUpdateListener(kVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }
}
