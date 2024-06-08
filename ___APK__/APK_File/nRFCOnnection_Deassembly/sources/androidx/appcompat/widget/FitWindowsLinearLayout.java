package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.appcompat.widget.c0;

/* loaded from: classes.dex */
public class FitWindowsLinearLayout extends LinearLayout implements c0 {

    /* renamed from: b, reason: collision with root package name */
    private c0.a f887b;

    public FitWindowsLinearLayout(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        c0.a aVar = this.f887b;
        if (aVar != null) {
            aVar.a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.c0
    public void setOnFitSystemWindowsListener(c0.a aVar) {
        this.f887b = aVar;
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
