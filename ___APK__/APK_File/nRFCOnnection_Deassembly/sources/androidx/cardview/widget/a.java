package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.cardview.widget.g;

/* loaded from: classes.dex */
class a extends c {

    /* renamed from: androidx.cardview.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0045a implements g.a {
        C0045a(a aVar) {
        }

        @Override // androidx.cardview.widget.g.a
        public void a(Canvas canvas, RectF rectF, float f2, Paint paint) {
            canvas.drawRoundRect(rectF, f2, f2, paint);
        }
    }

    @Override // androidx.cardview.widget.c, androidx.cardview.widget.e
    public void a() {
        g.r = new C0045a(this);
    }
}
