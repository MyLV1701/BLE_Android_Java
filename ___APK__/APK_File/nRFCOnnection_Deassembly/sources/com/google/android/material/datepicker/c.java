package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    final b f2460a;

    /* renamed from: b, reason: collision with root package name */
    final b f2461b;

    /* renamed from: c, reason: collision with root package name */
    final b f2462c;

    /* renamed from: d, reason: collision with root package name */
    final b f2463d;

    /* renamed from: e, reason: collision with root package name */
    final b f2464e;

    /* renamed from: f, reason: collision with root package name */
    final b f2465f;
    final b g;
    final Paint h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(c.a.a.a.y.b.a(context, c.a.a.a.b.materialCalendarStyle, g.class.getCanonicalName()), c.a.a.a.l.MaterialCalendar);
        this.f2460a = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_dayStyle, 0));
        this.g = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_dayInvalidStyle, 0));
        this.f2461b = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_daySelectedStyle, 0));
        this.f2462c = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList a2 = c.a.a.a.y.c.a(context, obtainStyledAttributes, c.a.a.a.l.MaterialCalendar_rangeFillColor);
        this.f2463d = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_yearStyle, 0));
        this.f2464e = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_yearSelectedStyle, 0));
        this.f2465f = b.a(context, obtainStyledAttributes.getResourceId(c.a.a.a.l.MaterialCalendar_yearTodayStyle, 0));
        this.h = new Paint();
        this.h.setColor(a2.getDefaultColor());
        obtainStyledAttributes.recycle();
    }
}
