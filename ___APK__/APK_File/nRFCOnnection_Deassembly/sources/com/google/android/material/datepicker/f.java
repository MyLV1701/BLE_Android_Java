package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
class f extends BaseAdapter {

    /* renamed from: e, reason: collision with root package name */
    private static final int f2466e;

    /* renamed from: b, reason: collision with root package name */
    private final Calendar f2467b = o.b();

    /* renamed from: c, reason: collision with root package name */
    private final int f2468c = this.f2467b.getMaximum(7);

    /* renamed from: d, reason: collision with root package name */
    private final int f2469d = this.f2467b.getFirstDayOfWeek();

    static {
        f2466e = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    private int a(int i) {
        int i2 = i + this.f2469d;
        int i3 = this.f2468c;
        return i2 > i3 ? i2 - i3 : i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2468c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @SuppressLint({"WrongConstant"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(c.a.a.a.h.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.f2467b.set(7, a(i));
        textView.setText(this.f2467b.getDisplayName(7, f2466e, Locale.getDefault()));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(c.a.a.a.j.mtrl_picker_day_of_week_column_header), this.f2467b.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }

    @Override // android.widget.Adapter
    public Integer getItem(int i) {
        if (i >= this.f2468c) {
            return null;
        }
        return Integer.valueOf(a(i));
    }
}
