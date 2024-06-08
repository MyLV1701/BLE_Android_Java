package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
class p extends PopupWindow {

    /* renamed from: b, reason: collision with root package name */
    private static final boolean f1072b;

    /* renamed from: a, reason: collision with root package name */
    private boolean f1073a;

    static {
        f1072b = Build.VERSION.SDK_INT < 21;
    }

    public p(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        t0 a2 = t0.a(context, attributeSet, a.a.j.PopupWindow, i, i2);
        if (a2.g(a.a.j.PopupWindow_overlapAnchor)) {
            a(a2.a(a.a.j.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(a2.b(a.a.j.PopupWindow_android_popupBackground));
        a2.a();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (f1072b && this.f1073a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (f1072b && this.f1073a) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f1072b && this.f1073a) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    private void a(boolean z) {
        if (f1072b) {
            this.f1073a = z;
        } else {
            androidx.core.widget.h.a(this, z);
        }
    }
}
