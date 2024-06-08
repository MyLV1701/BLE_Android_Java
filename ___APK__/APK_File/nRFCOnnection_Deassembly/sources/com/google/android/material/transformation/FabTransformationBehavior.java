package com.google.android.material.transformation;

import a.f.l.w;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import c.a.a.a.f;
import c.a.a.a.m.h;
import c.a.a.a.m.i;
import c.a.a.a.m.j;
import c.a.a.a.r.d;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.mcp.database.DeviceContract;

/* loaded from: classes.dex */
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {

    /* renamed from: c, reason: collision with root package name */
    private final Rect f2785c;

    /* renamed from: d, reason: collision with root package name */
    private final RectF f2786d;

    /* renamed from: e, reason: collision with root package name */
    private final RectF f2787e;

    /* renamed from: f, reason: collision with root package name */
    private final int[] f2788f;
    private float g;
    private float h;

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f2789a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f2790b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f2791c;

        a(FabTransformationBehavior fabTransformationBehavior, boolean z, View view, View view2) {
            this.f2789a = z;
            this.f2790b = view;
            this.f2791c = view2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f2789a) {
                return;
            }
            this.f2790b.setVisibility(4);
            this.f2791c.setAlpha(1.0f);
            this.f2791c.setVisibility(0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f2789a) {
                this.f2790b.setVisibility(0);
                this.f2791c.setAlpha(0.0f);
                this.f2791c.setVisibility(4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f2792a;

        b(FabTransformationBehavior fabTransformationBehavior, View view) {
            this.f2792a = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f2792a.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.r.d f2793a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Drawable f2794b;

        c(FabTransformationBehavior fabTransformationBehavior, c.a.a.a.r.d dVar, Drawable drawable) {
            this.f2793a = dVar;
            this.f2794b = drawable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2793a.setCircularRevealOverlayDrawable(null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2793a.setCircularRevealOverlayDrawable(this.f2794b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.r.d f2795a;

        d(FabTransformationBehavior fabTransformationBehavior, c.a.a.a.r.d dVar) {
            this.f2795a = dVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.e revealInfo = this.f2795a.getRevealInfo();
            revealInfo.f2138c = Float.MAX_VALUE;
            this.f2795a.setRevealInfo(revealInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        public h f2796a;

        /* renamed from: b, reason: collision with root package name */
        public j f2797b;
    }

    public FabTransformationBehavior() {
        this.f2785c = new Rect();
        this.f2786d = new RectF();
        this.f2787e = new RectF();
        this.f2788f = new int[2];
    }

    @TargetApi(21)
    private void c(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator ofFloat;
        float l = w.l(view2) - w.l(view);
        if (z) {
            if (!z2) {
                view2.setTranslationZ(-l);
            }
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -l);
        }
        eVar.f2796a.a("elevation").a((Animator) ofFloat);
        list.add(ofFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator ofInt;
        if ((view2 instanceof c.a.a.a.r.d) && (view instanceof ImageView)) {
            c.a.a.a.r.d dVar = (c.a.a.a.r.d) view2;
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z) {
                if (!z2) {
                    drawable.setAlpha(255);
                }
                ofInt = ObjectAnimator.ofInt(drawable, c.a.a.a.m.e.f2100b, 0);
            } else {
                ofInt = ObjectAnimator.ofInt(drawable, c.a.a.a.m.e.f2100b, 255);
            }
            ofInt.addUpdateListener(new b(this, view2));
            eVar.f2796a.a("iconFade").a((Animator) ofInt);
            list.add(ofInt);
            list2.add(new c(this, dVar, drawable));
        }
    }

    protected abstract e a(Context context, boolean z);

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean a(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() != 8) {
            if (!(view2 instanceof FloatingActionButton)) {
                return false;
            }
            int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
            return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
        }
        throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet b(View view, View view2, boolean z, boolean z2) {
        e a2 = a(view2.getContext(), z);
        if (z) {
            this.g = view.getTranslationX();
            this.h = view.getTranslationY();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (Build.VERSION.SDK_INT >= 21) {
            c(view, view2, z, z2, a2, arrayList, arrayList2);
        }
        RectF rectF = this.f2786d;
        a(view, view2, z, z2, a2, arrayList, arrayList2, rectF);
        float width = rectF.width();
        float height = rectF.height();
        a(view, view2, z, a2, arrayList);
        d(view, view2, z, z2, a2, arrayList, arrayList2);
        a(view, view2, z, z2, a2, width, height, arrayList, arrayList2);
        b(view, view2, z, z2, a2, arrayList, arrayList2);
        a(view, view2, z, z2, a2, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        c.a.a.a.m.b.a(animatorSet, arrayList);
        animatorSet.addListener(new a(this, z, view2, view));
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            animatorSet.addListener(arrayList2.get(i));
        }
        return animatorSet;
    }

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2785c = new Rect();
        this.f2786d = new RectF();
        this.f2787e = new RectF();
        this.f2788f = new int[2];
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public void a(CoordinatorLayout.f fVar) {
        if (fVar.h == 0) {
            fVar.h = 80;
        }
    }

    private float c(View view, View view2, j jVar) {
        float centerX;
        float centerX2;
        float f2;
        RectF rectF = this.f2786d;
        RectF rectF2 = this.f2787e;
        a(view, rectF);
        b(view2, rectF2);
        int i = jVar.f2113a & 7;
        if (i == 1) {
            centerX = rectF2.centerX();
            centerX2 = rectF.centerX();
        } else if (i == 3) {
            centerX = rectF2.left;
            centerX2 = rectF.left;
        } else if (i == 5) {
            centerX = rectF2.right;
            centerX2 = rectF.right;
        } else {
            f2 = 0.0f;
            return f2 + jVar.f2114b;
        }
        f2 = centerX - centerX2;
        return f2 + jVar.f2114b;
    }

    private void a(View view, View view2, boolean z, e eVar, List<Animator> list) {
        float c2 = c(view, view2, eVar.f2797b);
        float d2 = d(view, view2, eVar.f2797b);
        Pair<i, i> a2 = a(c2, d2, z, eVar);
        i iVar = (i) a2.first;
        i iVar2 = (i) a2.second;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        if (!z) {
            c2 = this.g;
        }
        fArr[0] = c2;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fArr);
        Property property2 = View.TRANSLATION_Y;
        float[] fArr2 = new float[1];
        if (!z) {
            d2 = this.h;
        }
        fArr2[0] = d2;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fArr2);
        iVar.a((Animator) ofFloat);
        iVar2.a((Animator) ofFloat2);
        list.add(ofFloat);
        list.add(ofFloat2);
    }

    private float d(View view, View view2, j jVar) {
        float centerY;
        float centerY2;
        float f2;
        RectF rectF = this.f2786d;
        RectF rectF2 = this.f2787e;
        a(view, rectF);
        b(view2, rectF2);
        int i = jVar.f2113a & 112;
        if (i == 16) {
            centerY = rectF2.centerY();
            centerY2 = rectF.centerY();
        } else if (i == 48) {
            centerY = rectF2.top;
            centerY2 = rectF.top;
        } else if (i == 80) {
            centerY = rectF2.bottom;
            centerY2 = rectF.bottom;
        } else {
            f2 = 0.0f;
            return f2 + jVar.f2115c;
        }
        f2 = centerY - centerY2;
        return f2 + jVar.f2115c;
    }

    private ViewGroup c(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    private void a(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        ObjectAnimator ofFloat;
        ObjectAnimator ofFloat2;
        float c2 = c(view, view2, eVar.f2797b);
        float d2 = d(view, view2, eVar.f2797b);
        Pair<i, i> a2 = a(c2, d2, z, eVar);
        i iVar = (i) a2.first;
        i iVar2 = (i) a2.second;
        if (z) {
            if (!z2) {
                view2.setTranslationX(-c2);
                view2.setTranslationY(-d2);
            }
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            ofFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            a(view2, eVar, iVar, iVar2, -c2, -d2, 0.0f, 0.0f, rectF);
        } else {
            ofFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -c2);
            ofFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -d2);
        }
        iVar.a((Animator) ofFloat);
        iVar2.a((Animator) ofFloat2);
        list.add(ofFloat);
        list.add(ofFloat2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator ofInt;
        if (view2 instanceof c.a.a.a.r.d) {
            c.a.a.a.r.d dVar = (c.a.a.a.r.d) view2;
            int b2 = b(view);
            int i = 16777215 & b2;
            if (z) {
                if (!z2) {
                    dVar.setCircularRevealScrimColor(b2);
                }
                ofInt = ObjectAnimator.ofInt(dVar, d.C0070d.f2135a, i);
            } else {
                ofInt = ObjectAnimator.ofInt(dVar, d.C0070d.f2135a, b2);
            }
            ofInt.setEvaluator(c.a.a.a.m.c.a());
            eVar.f2796a.a(DeviceContract.DeviceColumns.COLOR).a((Animator) ofInt);
            list.add(ofInt);
        }
    }

    private void b(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.f2788f);
        rectF.offsetTo(r0[0], r0[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(View view, View view2, boolean z, boolean z2, e eVar, float f2, float f3, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animator;
        if (view2 instanceof c.a.a.a.r.d) {
            c.a.a.a.r.d dVar = (c.a.a.a.r.d) view2;
            float a2 = a(view, view2, eVar.f2797b);
            float b2 = b(view, view2, eVar.f2797b);
            ((FloatingActionButton) view).a(this.f2785c);
            float width = this.f2785c.width() / 2.0f;
            i a3 = eVar.f2796a.a("expansion");
            if (z) {
                if (!z2) {
                    dVar.setRevealInfo(new d.e(a2, b2, width));
                }
                if (z2) {
                    width = dVar.getRevealInfo().f2138c;
                }
                animator = c.a.a.a.r.a.a(dVar, a2, b2, c.a.a.a.w.a.a(a2, b2, 0.0f, 0.0f, f2, f3));
                animator.addListener(new d(this, dVar));
                a(view2, a3.a(), (int) a2, (int) b2, width, list);
            } else {
                float f4 = dVar.getRevealInfo().f2138c;
                Animator a4 = c.a.a.a.r.a.a(dVar, a2, b2, width);
                int i = (int) a2;
                int i2 = (int) b2;
                a(view2, a3.a(), i, i2, f4, list);
                a(view2, a3.a(), a3.b(), eVar.f2796a.a(), i, i2, width, list);
                animator = a4;
            }
            a3.a(animator);
            list.add(animator);
            list2.add(c.a.a.a.r.a.a(dVar));
        }
    }

    private float b(View view, View view2, j jVar) {
        RectF rectF = this.f2786d;
        RectF rectF2 = this.f2787e;
        a(view, rectF);
        b(view2, rectF2);
        rectF2.offset(0.0f, -d(view, view2, jVar));
        return rectF.centerY() - rectF2.top;
    }

    private int b(View view) {
        ColorStateList h = w.h(view);
        if (h != null) {
            return h.getColorForState(view.getDrawableState(), h.getDefaultColor());
        }
        return 0;
    }

    private void a(View view, View view2, boolean z, boolean z2, e eVar, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup a2;
        ObjectAnimator ofFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof c.a.a.a.r.d) && c.a.a.a.r.c.f2131a == 0) || (a2 = a(view2)) == null) {
                return;
            }
            if (z) {
                if (!z2) {
                    c.a.a.a.m.d.f2099a.set(a2, Float.valueOf(0.0f));
                }
                ofFloat = ObjectAnimator.ofFloat(a2, c.a.a.a.m.d.f2099a, 1.0f);
            } else {
                ofFloat = ObjectAnimator.ofFloat(a2, c.a.a.a.m.d.f2099a, 0.0f);
            }
            eVar.f2796a.a("contentFade").a((Animator) ofFloat);
            list.add(ofFloat);
        }
    }

    private Pair<i, i> a(float f2, float f3, boolean z, e eVar) {
        i a2;
        i a3;
        if (f2 == 0.0f || f3 == 0.0f) {
            a2 = eVar.f2796a.a("translationXLinear");
            a3 = eVar.f2796a.a("translationYLinear");
        } else if ((z && f3 < 0.0f) || (!z && f3 > 0.0f)) {
            a2 = eVar.f2796a.a("translationXCurveUpwards");
            a3 = eVar.f2796a.a("translationYCurveUpwards");
        } else {
            a2 = eVar.f2796a.a("translationXCurveDownwards");
            a3 = eVar.f2796a.a("translationYCurveDownwards");
        }
        return new Pair<>(a2, a3);
    }

    private void a(View view, RectF rectF) {
        b(view, rectF);
        rectF.offset(this.g, this.h);
    }

    private float a(View view, View view2, j jVar) {
        RectF rectF = this.f2786d;
        RectF rectF2 = this.f2787e;
        a(view, rectF);
        b(view2, rectF2);
        rectF2.offset(-c(view, view2, jVar), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private void a(View view, e eVar, i iVar, i iVar2, float f2, float f3, float f4, float f5, RectF rectF) {
        float a2 = a(eVar, iVar, f2, f4);
        float a3 = a(eVar, iVar2, f3, f5);
        Rect rect = this.f2785c;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.f2786d;
        rectF2.set(rect);
        RectF rectF3 = this.f2787e;
        b(view, rectF3);
        rectF3.offset(a2, a3);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private float a(e eVar, i iVar, float f2, float f3) {
        long a2 = iVar.a();
        long b2 = iVar.b();
        i a3 = eVar.f2796a.a("expansion");
        return c.a.a.a.m.a.a(f2, f3, iVar.c().getInterpolation(((float) (((a3.a() + a3.b()) + 17) - a2)) / ((float) b2)));
    }

    private ViewGroup a(View view) {
        View findViewById = view.findViewById(f.mtrl_child_content_container);
        if (findViewById != null) {
            return c(findViewById);
        }
        if (!(view instanceof com.google.android.material.transformation.b) && !(view instanceof com.google.android.material.transformation.a)) {
            return c(view);
        }
        return c(((ViewGroup) view).getChildAt(0));
    }

    private void a(View view, long j, int i, int i2, float f2, List<Animator> list) {
        if (Build.VERSION.SDK_INT < 21 || j <= 0) {
            return;
        }
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f2, f2);
        createCircularReveal.setStartDelay(0L);
        createCircularReveal.setDuration(j);
        list.add(createCircularReveal);
    }

    private void a(View view, long j, long j2, long j3, int i, int i2, float f2, List<Animator> list) {
        if (Build.VERSION.SDK_INT >= 21) {
            long j4 = j + j2;
            if (j4 < j3) {
                Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, i, i2, f2, f2);
                createCircularReveal.setStartDelay(j4);
                createCircularReveal.setDuration(j3 - j4);
                list.add(createCircularReveal);
            }
        }
    }
}
