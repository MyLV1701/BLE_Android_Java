package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class n extends ImageButton {

    /* renamed from: b, reason: collision with root package name */
    private int f2620b;

    public n(Context context) {
        this(context, null);
    }

    public final void a(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.f2620b = i;
        }
    }

    public final int getUserSetVisibility() {
        return this.f2620b;
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        a(i, true);
    }

    public n(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public n(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2620b = getVisibility();
    }
}
