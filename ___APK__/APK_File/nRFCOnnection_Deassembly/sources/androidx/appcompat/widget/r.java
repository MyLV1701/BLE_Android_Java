package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;

/* loaded from: classes.dex */
public class r extends RatingBar {

    /* renamed from: b, reason: collision with root package name */
    private final q f1081b;

    public r(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.ratingBarStyle);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap a2 = this.f1081b.a();
        if (a2 != null) {
            setMeasuredDimension(View.resolveSizeAndState(a2.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }

    public r(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1081b = new q(this);
        this.f1081b.a(attributeSet, i);
    }
}
