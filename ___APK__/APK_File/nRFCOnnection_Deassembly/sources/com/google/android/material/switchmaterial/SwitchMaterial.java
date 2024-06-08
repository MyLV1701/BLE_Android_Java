package com.google.android.material.switchmaterial;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import c.a.a.a.b;
import c.a.a.a.d;
import c.a.a.a.k;
import c.a.a.a.u.a;
import com.google.android.material.internal.l;
import com.google.android.material.internal.m;

/* loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {

    /* renamed from: f, reason: collision with root package name */
    private static final int f2673f = k.Widget_MaterialComponents_CompoundButton_Switch;
    private static final int[][] g = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};

    /* renamed from: b, reason: collision with root package name */
    private final a f2674b;

    /* renamed from: c, reason: collision with root package name */
    private ColorStateList f2675c;

    /* renamed from: d, reason: collision with root package name */
    private ColorStateList f2676d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f2677e;

    public SwitchMaterial(Context context) {
        this(context, null);
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.f2675c == null) {
            int a2 = c.a.a.a.s.a.a(this, b.colorSurface);
            int a3 = c.a.a.a.s.a.a(this, b.colorControlActivated);
            float dimension = getResources().getDimension(d.mtrl_switch_thumb_elevation);
            if (this.f2674b.a()) {
                dimension += m.a(this);
            }
            int b2 = this.f2674b.b(a2, dimension);
            int[] iArr = new int[g.length];
            iArr[0] = c.a.a.a.s.a.a(a2, a3, 1.0f);
            iArr[1] = b2;
            iArr[2] = c.a.a.a.s.a.a(a2, a3, 0.38f);
            iArr[3] = b2;
            this.f2675c = new ColorStateList(g, iArr);
        }
        return this.f2675c;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.f2676d == null) {
            int[] iArr = new int[g.length];
            int a2 = c.a.a.a.s.a.a(this, b.colorSurface);
            int a3 = c.a.a.a.s.a.a(this, b.colorControlActivated);
            int a4 = c.a.a.a.s.a.a(this, b.colorOnSurface);
            iArr[0] = c.a.a.a.s.a.a(a2, a3, 0.54f);
            iArr[1] = c.a.a.a.s.a.a(a2, a4, 0.32f);
            iArr[2] = c.a.a.a.s.a.a(a2, a3, 0.12f);
            iArr[3] = c.a.a.a.s.a.a(a2, a4, 0.12f);
            this.f2676d = new ColorStateList(g, iArr);
        }
        return this.f2676d;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f2677e && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.f2677e && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.f2677e = z;
        if (z) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        } else {
            setThumbTintList(null);
            setTrackTintList(null);
        }
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.switchStyle);
    }

    public SwitchMaterial(Context context, AttributeSet attributeSet, int i) {
        super(com.google.android.material.theme.a.a.b(context, attributeSet, i, f2673f), attributeSet, i);
        Context context2 = getContext();
        this.f2674b = new a(context2);
        TypedArray c2 = l.c(context2, attributeSet, c.a.a.a.l.SwitchMaterial, i, f2673f, new int[0]);
        this.f2677e = c2.getBoolean(c.a.a.a.l.SwitchMaterial_useMaterialThemeColors, false);
        c2.recycle();
    }
}
