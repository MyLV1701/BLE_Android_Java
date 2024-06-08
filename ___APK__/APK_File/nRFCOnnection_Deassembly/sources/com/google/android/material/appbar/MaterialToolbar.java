package com.google.android.material.appbar;

import a.f.l.w;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import c.a.a.a.b0.g;
import c.a.a.a.b0.h;
import c.a.a.a.k;

/* loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    private static final int Q = k.Widget_MaterialComponents_Toolbar;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    private void a(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            g gVar = new g();
            gVar.a(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            gVar.a(context);
            gVar.b(w.l(this));
            w.a(this, gVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.a(this);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        h.a(this, f2);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.toolbarStyle);
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.b(context, attributeSet, i, Q), attributeSet, i);
        a(getContext());
    }
}
