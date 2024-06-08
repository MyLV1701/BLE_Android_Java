package com.google.android.material.datepicker;

import a.f.l.w;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.widget.TextView;

/* loaded from: classes.dex */
final class b {

    /* renamed from: a, reason: collision with root package name */
    private final Rect f2454a;

    /* renamed from: b, reason: collision with root package name */
    private final ColorStateList f2455b;

    /* renamed from: c, reason: collision with root package name */
    private final ColorStateList f2456c;

    /* renamed from: d, reason: collision with root package name */
    private final ColorStateList f2457d;

    /* renamed from: e, reason: collision with root package name */
    private final int f2458e;

    /* renamed from: f, reason: collision with root package name */
    private final c.a.a.a.b0.k f2459f;

    private b(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, c.a.a.a.b0.k kVar, Rect rect) {
        a.f.k.h.a(rect.left);
        a.f.k.h.a(rect.top);
        a.f.k.h.a(rect.right);
        a.f.k.h.a(rect.bottom);
        this.f2454a = rect;
        this.f2455b = colorStateList2;
        this.f2456c = colorStateList;
        this.f2457d = colorStateList3;
        this.f2458e = i;
        this.f2459f = kVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a(Context context, int i) {
        a.f.k.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, c.a.a.a.l.MaterialCalendarItem);
        Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.MaterialCalendarItem_android_insetLeft, 0), obtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.MaterialCalendarItem_android_insetTop, 0), obtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.MaterialCalendarItem_android_insetRight, 0), obtainStyledAttributes.getDimensionPixelOffset(c.a.a.a.l.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList a2 = c.a.a.a.y.c.a(context, obtainStyledAttributes, c.a.a.a.l.MaterialCalendarItem_itemFillColor);
        ColorStateList a3 = c.a.a.a.y.c.a(context, obtainStyledAttributes, c.a.a.a.l.MaterialCalendarItem_itemTextColor);
        ColorStateList a4 = c.a.a.a.y.c.a(context, obtainStyledAttributes, c.a.a.a.l.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(c.a.a.a.l.MaterialCalendarItem_itemStrokeWidth, 0);
        c.a.a.a.b0.k a5 = c.a.a.a.b0.k.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendarItem_itemShapeAppearance, 0), obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).a();
        obtainStyledAttributes.recycle();
        return new b(a2, a3, a4, dimensionPixelSize, a5, rect);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.f2454a.top;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(TextView textView) {
        c.a.a.a.b0.g gVar = new c.a.a.a.b0.g();
        c.a.a.a.b0.g gVar2 = new c.a.a.a.b0.g();
        gVar.setShapeAppearanceModel(this.f2459f);
        gVar2.setShapeAppearanceModel(this.f2459f);
        gVar.a(this.f2456c);
        gVar.a(this.f2458e, this.f2457d);
        textView.setTextColor(this.f2455b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f2455b.withAlpha(30), gVar, gVar2) : gVar;
        Rect rect = this.f2454a;
        w.a(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f2454a.bottom;
    }
}
