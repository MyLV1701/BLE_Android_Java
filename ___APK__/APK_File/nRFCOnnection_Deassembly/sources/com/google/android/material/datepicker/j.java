package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;

/* loaded from: classes.dex */
class j extends BaseAdapter {

    /* renamed from: f, reason: collision with root package name */
    static final int f2497f = o.b().getMaximum(4);

    /* renamed from: b, reason: collision with root package name */
    final i f2498b;

    /* renamed from: c, reason: collision with root package name */
    final d<?> f2499c;

    /* renamed from: d, reason: collision with root package name */
    c f2500d;

    /* renamed from: e, reason: collision with root package name */
    final a f2501e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(i iVar, d<?> dVar, a aVar) {
        this.f2498b = iVar;
        this.f2499c = dVar;
        this.f2501e = aVar;
    }

    private void a(Context context) {
        if (this.f2500d == null) {
            this.f2500d = new c(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return (this.f2498b.d() + this.f2498b.g) - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(int i) {
        return (i + 1) % this.f2498b.f2496f == 0;
    }

    int d(int i) {
        return (i - this.f2498b.d()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e(int i) {
        return i >= a() && i <= b();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f2498b.g + a();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.f2498b.f2496f;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(int i) {
        return i % this.f2498b.f2496f == 0;
    }

    @Override // android.widget.Adapter
    public Long getItem(int i) {
        if (i < this.f2498b.d() || i > b()) {
            return null;
        }
        return Long.valueOf(this.f2498b.a(d(i)));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0070  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.a(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = c.a.a.a.h.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.a()
            int r7 = r6 - r7
            r8 = 1
            if (r7 < 0) goto L61
            com.google.android.material.datepicker.i r2 = r5.f2498b
            int r3 = r2.g
            if (r7 < r3) goto L2e
            goto L61
        L2e:
            int r7 = r7 + r8
            r0.setTag(r2)
            java.lang.String r2 = java.lang.String.valueOf(r7)
            r0.setText(r2)
            com.google.android.material.datepicker.i r2 = r5.f2498b
            long r2 = r2.a(r7)
            com.google.android.material.datepicker.i r7 = r5.f2498b
            int r7 = r7.f2495e
            com.google.android.material.datepicker.i r4 = com.google.android.material.datepicker.i.g()
            int r4 = r4.f2495e
            if (r7 != r4) goto L53
            java.lang.String r7 = com.google.android.material.datepicker.e.a(r2)
            r0.setContentDescription(r7)
            goto L5a
        L53:
            java.lang.String r7 = com.google.android.material.datepicker.e.b(r2)
            r0.setContentDescription(r7)
        L5a:
            r0.setVisibility(r1)
            r0.setEnabled(r8)
            goto L69
        L61:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
        L69:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L70
            return r0
        L70:
            com.google.android.material.datepicker.a r7 = r5.f2501e
            com.google.android.material.datepicker.a$b r7 = r7.d()
            long r2 = r6.longValue()
            boolean r7 = r7.a(r2)
            if (r7 == 0) goto Ld5
            r0.setEnabled(r8)
            com.google.android.material.datepicker.d<?> r7 = r5.f2499c
            java.util.Collection r7 = r7.b()
            java.util.Iterator r7 = r7.iterator()
        L8d:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto Lb5
            java.lang.Object r8 = r7.next()
            java.lang.Long r8 = (java.lang.Long) r8
            long r1 = r8.longValue()
            long r3 = r6.longValue()
            long r3 = com.google.android.material.datepicker.o.a(r3)
            long r1 = com.google.android.material.datepicker.o.a(r1)
            int r8 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r8 != 0) goto L8d
            com.google.android.material.datepicker.c r6 = r5.f2500d
            com.google.android.material.datepicker.b r6 = r6.f2461b
            r6.a(r0)
            return r0
        Lb5:
            java.util.Calendar r7 = com.google.android.material.datepicker.o.d()
            long r7 = r7.getTimeInMillis()
            long r1 = r6.longValue()
            int r6 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r6 != 0) goto Lcd
            com.google.android.material.datepicker.c r6 = r5.f2500d
            com.google.android.material.datepicker.b r6 = r6.f2462c
            r6.a(r0)
            return r0
        Lcd:
            com.google.android.material.datepicker.c r6 = r5.f2500d
            com.google.android.material.datepicker.b r6 = r6.f2460a
            r6.a(r0)
            return r0
        Ld5:
            r0.setEnabled(r1)
            com.google.android.material.datepicker.c r6 = r5.f2500d
            com.google.android.material.datepicker.b r6 = r6.g
            r6.a(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.j.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f2498b.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i) {
        return a() + (i - 1);
    }
}
