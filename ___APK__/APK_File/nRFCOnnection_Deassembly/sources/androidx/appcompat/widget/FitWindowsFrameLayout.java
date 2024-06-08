package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.appcompat.widget.c0;

/* loaded from: classes.dex */
public class FitWindowsFrameLayout extends FrameLayout implements c0 {

    /* renamed from: b, reason: collision with root package name */
    private c0.a f886b;

    public FitWindowsFrameLayout(Context context) {
        super(context);
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        c0.a aVar = this.f886b;
        if (aVar != null) {
            aVar.a(rect);
        }
        return super.fitSystemWindows(rect);
    }

    @Override // androidx.appcompat.widget.c0
    public void setOnFitSystemWindowsListener(c0.a aVar) {
        this.f886b = aVar;
    }

    public FitWindowsFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
