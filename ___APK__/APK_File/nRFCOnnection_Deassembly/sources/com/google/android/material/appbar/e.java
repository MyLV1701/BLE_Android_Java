package com.google.android.material.appbar;

import android.R;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import c.a.a.a.g;
import com.google.android.material.internal.l;

/* loaded from: classes.dex */
class e {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f2371a = {R.attr.stateListAnimator};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view) {
        view.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, AttributeSet attributeSet, int i, int i2) {
        Context context = view.getContext();
        TypedArray c2 = l.c(context, attributeSet, f2371a, i, i2, new int[0]);
        try {
            if (c2.hasValue(0)) {
                view.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context, c2.getResourceId(0, 0)));
            }
        } finally {
            c2.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, float f2) {
        int integer = view.getResources().getInteger(g.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator = new StateListAnimator();
        long j = integer;
        stateListAnimator.addState(new int[]{R.attr.enabled, c.a.a.a.b.state_liftable, -c.a.a.a.b.state_lifted}, ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(j));
        stateListAnimator.addState(new int[]{R.attr.enabled}, ObjectAnimator.ofFloat(view, "elevation", f2).setDuration(j));
        stateListAnimator.addState(new int[0], ObjectAnimator.ofFloat(view, "elevation", 0.0f).setDuration(0L));
        view.setStateListAnimator(stateListAnimator);
    }
}
