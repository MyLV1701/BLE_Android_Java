package com.google.android.material.snackbar;

import a.f.l.w;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.a.a.a.f;
import c.a.a.a.l;

/* loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout implements a {

    /* renamed from: b, reason: collision with root package name */
    private TextView f2660b;

    /* renamed from: c, reason: collision with root package name */
    private Button f2661c;

    /* renamed from: d, reason: collision with root package name */
    private int f2662d;

    /* renamed from: e, reason: collision with root package name */
    private int f2663e;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f2) {
        if (f2 != 1.0f) {
            this.f2661c.setTextColor(c.a.a.a.s.a.a(c.a.a.a.s.a.a(this, c.a.a.a.b.colorSurface), this.f2661c.getCurrentTextColor(), f2));
        }
    }

    @Override // com.google.android.material.snackbar.a
    public void b(int i, int i2) {
        this.f2660b.setAlpha(1.0f);
        long j = i2;
        long j2 = i;
        this.f2660b.animate().alpha(0.0f).setDuration(j).setStartDelay(j2).start();
        if (this.f2661c.getVisibility() == 0) {
            this.f2661c.setAlpha(1.0f);
            this.f2661c.animate().alpha(0.0f).setDuration(j).setStartDelay(j2).start();
        }
    }

    public Button getActionView() {
        return this.f2661c;
    }

    public TextView getMessageView() {
        return this.f2660b;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.f2660b = (TextView) findViewById(f.snackbar_text);
        this.f2661c = (Button) findViewById(f.snackbar_action);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
    
        if (a(1, r0, r0 - r1) != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0061, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005e, code lost:
    
        if (a(0, r0, r0) != false) goto L26;
     */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            super.onMeasure(r8, r9)
            int r0 = r7.f2662d
            if (r0 <= 0) goto L18
            int r0 = r7.getMeasuredWidth()
            int r1 = r7.f2662d
            if (r0 <= r1) goto L18
            r8 = 1073741824(0x40000000, float:2.0)
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r8)
            super.onMeasure(r8, r9)
        L18:
            android.content.res.Resources r0 = r7.getResources()
            int r1 = c.a.a.a.d.design_snackbar_padding_vertical_2lines
            int r0 = r0.getDimensionPixelSize(r1)
            android.content.res.Resources r1 = r7.getResources()
            int r2 = c.a.a.a.d.design_snackbar_padding_vertical
            int r1 = r1.getDimensionPixelSize(r2)
            android.widget.TextView r2 = r7.f2660b
            android.text.Layout r2 = r2.getLayout()
            int r2 = r2.getLineCount()
            r3 = 0
            r4 = 1
            if (r2 <= r4) goto L3c
            r2 = 1
            goto L3d
        L3c:
            r2 = 0
        L3d:
            if (r2 == 0) goto L56
            int r5 = r7.f2663e
            if (r5 <= 0) goto L56
            android.widget.Button r5 = r7.f2661c
            int r5 = r5.getMeasuredWidth()
            int r6 = r7.f2663e
            if (r5 <= r6) goto L56
            int r1 = r0 - r1
            boolean r0 = r7.a(r4, r0, r1)
            if (r0 == 0) goto L61
            goto L62
        L56:
            if (r2 == 0) goto L59
            goto L5a
        L59:
            r0 = r1
        L5a:
            boolean r0 = r7.a(r3, r0, r0)
            if (r0 == 0) goto L61
            goto L62
        L61:
            r4 = 0
        L62:
            if (r4 == 0) goto L67
            super.onMeasure(r8, r9)
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.onMeasure(int, int):void");
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.SnackbarLayout);
        this.f2662d = obtainStyledAttributes.getDimensionPixelSize(l.SnackbarLayout_android_maxWidth, -1);
        this.f2663e = obtainStyledAttributes.getDimensionPixelSize(l.SnackbarLayout_maxActionInlineWidth, -1);
        obtainStyledAttributes.recycle();
    }

    private boolean a(int i, int i2, int i3) {
        boolean z;
        if (i != getOrientation()) {
            setOrientation(i);
            z = true;
        } else {
            z = false;
        }
        if (this.f2660b.getPaddingTop() == i2 && this.f2660b.getPaddingBottom() == i3) {
            return z;
        }
        a(this.f2660b, i2, i3);
        return true;
    }

    private static void a(View view, int i, int i2) {
        if (w.I(view)) {
            w.b(view, w.v(view), i, w.u(view), i2);
        } else {
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), i2);
        }
    }

    @Override // com.google.android.material.snackbar.a
    public void a(int i, int i2) {
        this.f2660b.setAlpha(0.0f);
        long j = i2;
        long j2 = i;
        this.f2660b.animate().alpha(1.0f).setDuration(j).setStartDelay(j2).start();
        if (this.f2661c.getVisibility() == 0) {
            this.f2661c.setAlpha(0.0f);
            this.f2661c.animate().alpha(1.0f).setDuration(j).setStartDelay(j2).start();
        }
    }
}
