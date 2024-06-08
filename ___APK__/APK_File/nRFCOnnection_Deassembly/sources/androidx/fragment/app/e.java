package androidx.fragment.app;

import a.f.h.a;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.fragment.app.u;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements a.InterfaceC0009a {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f1373a;

        a(Fragment fragment) {
            this.f1373a = fragment;
        }

        @Override // a.f.h.a.InterfaceC0009a
        public void a() {
            if (this.f1373a.getAnimatingAway() != null) {
                View animatingAway = this.f1373a.getAnimatingAway();
                this.f1373a.setAnimatingAway(null);
                animatingAway.clearAnimation();
            }
            this.f1373a.setAnimator(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b implements Animation.AnimationListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1374a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1375b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ u.g f1376c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ a.f.h.a f1377d;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.f1375b.getAnimatingAway() != null) {
                    b.this.f1375b.setAnimatingAway(null);
                    b bVar = b.this;
                    bVar.f1376c.a(bVar.f1375b, bVar.f1377d);
                }
            }
        }

        b(ViewGroup viewGroup, Fragment fragment, u.g gVar, a.f.h.a aVar) {
            this.f1374a = viewGroup;
            this.f1375b = fragment;
            this.f1376c = gVar;
            this.f1377d = aVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f1374a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class c extends AnimatorListenerAdapter {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1379a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1380b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1381c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ u.g f1382d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ a.f.h.a f1383e;

        c(ViewGroup viewGroup, View view, Fragment fragment, u.g gVar, a.f.h.a aVar) {
            this.f1379a = viewGroup;
            this.f1380b = view;
            this.f1381c = fragment;
            this.f1382d = gVar;
            this.f1383e = aVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1379a.endViewTransition(this.f1380b);
            Animator animator2 = this.f1381c.getAnimator();
            this.f1381c.setAnimator(null);
            if (animator2 == null || this.f1379a.indexOfChild(this.f1380b) >= 0) {
                return;
            }
            this.f1382d.a(this.f1381c, this.f1383e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(Context context, f fVar, Fragment fragment, boolean z) {
        int a2;
        int nextTransition = fragment.getNextTransition();
        int nextAnim = fragment.getNextAnim();
        boolean z2 = false;
        fragment.setNextAnim(0);
        View a3 = fVar.a(fragment.mContainerId);
        if (a3 != null && a3.getTag(a.i.b.visible_removing_fragment_view_tag) != null) {
            a3.setTag(a.i.b.visible_removing_fragment_view_tag, null);
        }
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation onCreateAnimation = fragment.onCreateAnimation(nextTransition, z, nextAnim);
        if (onCreateAnimation != null) {
            return new d(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(nextTransition, z, nextAnim);
        if (onCreateAnimator != null) {
            return new d(onCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(nextAnim));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, nextAnim);
                    if (loadAnimation != null) {
                        return new d(loadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(context, nextAnim);
                    if (loadAnimator != null) {
                        return new d(loadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (!equals) {
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, nextAnim);
                        if (loadAnimation2 != null) {
                            return new d(loadAnimation2);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        if (nextTransition != 0 && (a2 = a(nextTransition, z)) >= 0) {
            return new d(AnimationUtils.loadAnimation(context, a2));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        public final Animation f1384a;

        /* renamed from: b, reason: collision with root package name */
        public final Animator f1385b;

        d(Animation animation) {
            this.f1384a = animation;
            this.f1385b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        d(Animator animator) {
            this.f1384a = null;
            this.f1385b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: androidx.fragment.app.e$e, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class RunnableC0052e extends AnimationSet implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private final ViewGroup f1386b;

        /* renamed from: c, reason: collision with root package name */
        private final View f1387c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f1388d;

        /* renamed from: e, reason: collision with root package name */
        private boolean f1389e;

        /* renamed from: f, reason: collision with root package name */
        private boolean f1390f;

        RunnableC0052e(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f1390f = true;
            this.f1386b = viewGroup;
            this.f1387c = view;
            addAnimation(animation);
            this.f1386b.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f1390f = true;
            if (this.f1388d) {
                return !this.f1389e;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f1388d = true;
                a.f.l.s.a(this.f1386b, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f1388d && this.f1390f) {
                this.f1390f = false;
                this.f1386b.post(this);
            } else {
                this.f1386b.endViewTransition(this.f1387c);
                this.f1389e = true;
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f2) {
            this.f1390f = true;
            if (this.f1388d) {
                return !this.f1389e;
            }
            if (!super.getTransformation(j, transformation, f2)) {
                this.f1388d = true;
                a.f.l.s.a(this.f1386b, this);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Fragment fragment, d dVar, u.g gVar) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        a.f.h.a aVar = new a.f.h.a();
        aVar.a(new a(fragment));
        gVar.b(fragment, aVar);
        Animation animation = dVar.f1384a;
        if (animation != null) {
            RunnableC0052e runnableC0052e = new RunnableC0052e(animation, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            runnableC0052e.setAnimationListener(new b(viewGroup, fragment, gVar, aVar));
            fragment.mView.startAnimation(runnableC0052e);
            return;
        }
        Animator animator = dVar.f1385b;
        fragment.setAnimator(animator);
        animator.addListener(new c(viewGroup, view, fragment, gVar, aVar));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    private static int a(int i, boolean z) {
        if (i == 4097) {
            return z ? a.i.a.fragment_open_enter : a.i.a.fragment_open_exit;
        }
        if (i == 4099) {
            return z ? a.i.a.fragment_fade_enter : a.i.a.fragment_fade_exit;
        }
        if (i != 8194) {
            return -1;
        }
        return z ? a.i.a.fragment_close_enter : a.i.a.fragment_close_exit;
    }
}
