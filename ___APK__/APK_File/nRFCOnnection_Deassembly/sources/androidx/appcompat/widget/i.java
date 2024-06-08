package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;

/* loaded from: classes.dex */
class i {

    /* renamed from: a, reason: collision with root package name */
    private final CompoundButton f998a;

    /* renamed from: b, reason: collision with root package name */
    private ColorStateList f999b = null;

    /* renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f1000c = null;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1001d = false;

    /* renamed from: e, reason: collision with root package name */
    private boolean f1002e = false;

    /* renamed from: f, reason: collision with root package name */
    private boolean f1003f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(CompoundButton compoundButton) {
        this.f998a = compoundButton;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058 A[Catch: all -> 0x0080, TryCatch #0 {all -> 0x0080, blocks: (B:3:0x000d, B:5:0x0015, B:8:0x001d, B:11:0x0031, B:13:0x0039, B:15:0x0041, B:16:0x0050, B:18:0x0058, B:19:0x0063, B:21:0x006b), top: B:2:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b A[Catch: all -> 0x0080, TRY_LEAVE, TryCatch #0 {all -> 0x0080, blocks: (B:3:0x000d, B:5:0x0015, B:8:0x001d, B:11:0x0031, B:13:0x0039, B:15:0x0041, B:16:0x0050, B:18:0x0058, B:19:0x0063, B:21:0x006b), top: B:2:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(android.util.AttributeSet r4, int r5) {
        /*
            r3 = this;
            android.widget.CompoundButton r0 = r3.f998a
            android.content.Context r0 = r0.getContext()
            int[] r1 = a.a.j.CompoundButton
            r2 = 0
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r4, r1, r5, r2)
            int r5 = a.a.j.CompoundButton_buttonCompat     // Catch: java.lang.Throwable -> L80
            boolean r5 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L80
            if (r5 == 0) goto L2e
            int r5 = a.a.j.CompoundButton_buttonCompat     // Catch: java.lang.Throwable -> L80
            int r5 = r4.getResourceId(r5, r2)     // Catch: java.lang.Throwable -> L80
            if (r5 == 0) goto L2e
            android.widget.CompoundButton r0 = r3.f998a     // Catch: android.content.res.Resources.NotFoundException -> L2e java.lang.Throwable -> L80
            android.widget.CompoundButton r1 = r3.f998a     // Catch: android.content.res.Resources.NotFoundException -> L2e java.lang.Throwable -> L80
            android.content.Context r1 = r1.getContext()     // Catch: android.content.res.Resources.NotFoundException -> L2e java.lang.Throwable -> L80
            android.graphics.drawable.Drawable r5 = a.a.k.a.a.c(r1, r5)     // Catch: android.content.res.Resources.NotFoundException -> L2e java.lang.Throwable -> L80
            r0.setButtonDrawable(r5)     // Catch: android.content.res.Resources.NotFoundException -> L2e java.lang.Throwable -> L80
            r5 = 1
            goto L2f
        L2e:
            r5 = 0
        L2f:
            if (r5 != 0) goto L50
            int r5 = a.a.j.CompoundButton_android_button     // Catch: java.lang.Throwable -> L80
            boolean r5 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L80
            if (r5 == 0) goto L50
            int r5 = a.a.j.CompoundButton_android_button     // Catch: java.lang.Throwable -> L80
            int r5 = r4.getResourceId(r5, r2)     // Catch: java.lang.Throwable -> L80
            if (r5 == 0) goto L50
            android.widget.CompoundButton r0 = r3.f998a     // Catch: java.lang.Throwable -> L80
            android.widget.CompoundButton r1 = r3.f998a     // Catch: java.lang.Throwable -> L80
            android.content.Context r1 = r1.getContext()     // Catch: java.lang.Throwable -> L80
            android.graphics.drawable.Drawable r5 = a.a.k.a.a.c(r1, r5)     // Catch: java.lang.Throwable -> L80
            r0.setButtonDrawable(r5)     // Catch: java.lang.Throwable -> L80
        L50:
            int r5 = a.a.j.CompoundButton_buttonTint     // Catch: java.lang.Throwable -> L80
            boolean r5 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L80
            if (r5 == 0) goto L63
            android.widget.CompoundButton r5 = r3.f998a     // Catch: java.lang.Throwable -> L80
            int r0 = a.a.j.CompoundButton_buttonTint     // Catch: java.lang.Throwable -> L80
            android.content.res.ColorStateList r0 = r4.getColorStateList(r0)     // Catch: java.lang.Throwable -> L80
            androidx.core.widget.c.a(r5, r0)     // Catch: java.lang.Throwable -> L80
        L63:
            int r5 = a.a.j.CompoundButton_buttonTintMode     // Catch: java.lang.Throwable -> L80
            boolean r5 = r4.hasValue(r5)     // Catch: java.lang.Throwable -> L80
            if (r5 == 0) goto L7c
            android.widget.CompoundButton r5 = r3.f998a     // Catch: java.lang.Throwable -> L80
            int r0 = a.a.j.CompoundButton_buttonTintMode     // Catch: java.lang.Throwable -> L80
            r1 = -1
            int r0 = r4.getInt(r0, r1)     // Catch: java.lang.Throwable -> L80
            r1 = 0
            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.a0.a(r0, r1)     // Catch: java.lang.Throwable -> L80
            androidx.core.widget.c.a(r5, r0)     // Catch: java.lang.Throwable -> L80
        L7c:
            r4.recycle()
            return
        L80:
            r5 = move-exception
            r4.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.i.a(android.util.AttributeSet, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorStateList b() {
        return this.f999b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PorterDuff.Mode c() {
        return this.f1000c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        if (this.f1003f) {
            this.f1003f = false;
        } else {
            this.f1003f = true;
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ColorStateList colorStateList) {
        this.f999b = colorStateList;
        this.f1001d = true;
        a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(PorterDuff.Mode mode) {
        this.f1000c = mode;
        this.f1002e = true;
        a();
    }

    void a() {
        Drawable a2 = androidx.core.widget.c.a(this.f998a);
        if (a2 != null) {
            if (this.f1001d || this.f1002e) {
                Drawable mutate = androidx.core.graphics.drawable.a.i(a2).mutate();
                if (this.f1001d) {
                    androidx.core.graphics.drawable.a.a(mutate, this.f999b);
                }
                if (this.f1002e) {
                    androidx.core.graphics.drawable.a.a(mutate, this.f1000c);
                }
                if (mutate.isStateful()) {
                    mutate.setState(this.f998a.getDrawableState());
                }
                this.f998a.setButtonDrawable(mutate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i) {
        Drawable a2;
        return (Build.VERSION.SDK_INT >= 17 || (a2 = androidx.core.widget.c.a(this.f998a)) == null) ? i : i + a2.getIntrinsicWidth();
    }
}
