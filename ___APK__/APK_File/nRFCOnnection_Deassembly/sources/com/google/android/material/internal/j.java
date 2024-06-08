package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList<b> f2604a = new ArrayList<>();

    /* renamed from: b, reason: collision with root package name */
    private b f2605b = null;

    /* renamed from: c, reason: collision with root package name */
    ValueAnimator f2606c = null;

    /* renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f2607d = new a();

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            j jVar = j.this;
            if (jVar.f2606c == animator) {
                jVar.f2606c = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        final int[] f2609a;

        /* renamed from: b, reason: collision with root package name */
        final ValueAnimator f2610b;

        b(int[] iArr, ValueAnimator valueAnimator) {
            this.f2609a = iArr;
            this.f2610b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f2606c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f2606c = null;
        }
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        b bVar = new b(iArr, valueAnimator);
        valueAnimator.addListener(this.f2607d);
        this.f2604a.add(bVar);
    }

    public void a(int[] iArr) {
        b bVar;
        int size = this.f2604a.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                bVar = null;
                break;
            }
            bVar = this.f2604a.get(i);
            if (StateSet.stateSetMatches(bVar.f2609a, iArr)) {
                break;
            } else {
                i++;
            }
        }
        b bVar2 = this.f2605b;
        if (bVar == bVar2) {
            return;
        }
        if (bVar2 != null) {
            b();
        }
        this.f2605b = bVar;
        if (bVar != null) {
            a(bVar);
        }
    }

    private void a(b bVar) {
        this.f2606c = bVar.f2610b;
        this.f2606c.start();
    }

    public void a() {
        ValueAnimator valueAnimator = this.f2606c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f2606c = null;
        }
    }
}
