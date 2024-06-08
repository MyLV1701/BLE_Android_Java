package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import c.a.a.a.m.h;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface d {
    void a();

    void a(h hVar);

    void a(ExtendedFloatingActionButton.d dVar);

    void b();

    void c();

    h d();

    AnimatorSet e();

    List<Animator.AnimatorListener> f();

    boolean g();

    void onAnimationStart(Animator animator);
}
