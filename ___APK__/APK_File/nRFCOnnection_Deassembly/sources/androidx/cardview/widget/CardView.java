package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* loaded from: classes.dex */
public class CardView extends FrameLayout {
    private static final int[] i = {R.attr.colorBackground};
    private static final e j;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1141b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1142c;

    /* renamed from: d, reason: collision with root package name */
    int f1143d;

    /* renamed from: e, reason: collision with root package name */
    int f1144e;

    /* renamed from: f, reason: collision with root package name */
    final Rect f1145f;
    final Rect g;
    private final d h;

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            j = new b();
        } else if (i2 >= 17) {
            j = new androidx.cardview.widget.a();
        } else {
            j = new c();
        }
        j.a();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return j.b(this.h);
    }

    public float getCardElevation() {
        return j.c(this.h);
    }

    public int getContentPaddingBottom() {
        return this.f1145f.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f1145f.left;
    }

    public int getContentPaddingRight() {
        return this.f1145f.right;
    }

    public int getContentPaddingTop() {
        return this.f1145f.top;
    }

    public float getMaxCardElevation() {
        return j.a(this.h);
    }

    public boolean getPreventCornerOverlap() {
        return this.f1142c;
    }

    public float getRadius() {
        return j.d(this.h);
    }

    public boolean getUseCompatPadding() {
        return this.f1141b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (!(j instanceof b)) {
            int mode = View.MeasureSpec.getMode(i2);
            if (mode == Integer.MIN_VALUE || mode == 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(j.f(this.h)), View.MeasureSpec.getSize(i2)), mode);
            }
            int mode2 = View.MeasureSpec.getMode(i3);
            if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
                i3 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(j.e(this.h)), View.MeasureSpec.getSize(i3)), mode2);
            }
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, i3);
    }

    public void setCardBackgroundColor(int i2) {
        j.a(this.h, ColorStateList.valueOf(i2));
    }

    public void setCardElevation(float f2) {
        j.b(this.h, f2);
    }

    public void setMaxCardElevation(float f2) {
        j.c(this.h, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i2) {
        this.f1144e = i2;
        super.setMinimumHeight(i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i2) {
        this.f1143d = i2;
        super.setMinimumWidth(i2);
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.f1142c) {
            this.f1142c = z;
            j.h(this.h);
        }
    }

    public void setRadius(float f2) {
        j.a(this.h, f2);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f1141b != z) {
            this.f1141b = z;
            j.g(this.h);
        }
    }

    /* loaded from: classes.dex */
    class a implements d {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f1146a;

        a() {
        }

        @Override // androidx.cardview.widget.d
        public void a(Drawable drawable) {
            this.f1146a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // androidx.cardview.widget.d
        public boolean b() {
            return CardView.this.getPreventCornerOverlap();
        }

        @Override // androidx.cardview.widget.d
        public boolean c() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // androidx.cardview.widget.d
        public Drawable d() {
            return this.f1146a;
        }

        @Override // androidx.cardview.widget.d
        public void a(int i, int i2, int i3, int i4) {
            CardView.this.g.set(i, i2, i3, i4);
            CardView cardView = CardView.this;
            Rect rect = cardView.f1145f;
            CardView.super.setPadding(i + rect.left, i2 + rect.top, i3 + rect.right, i4 + rect.bottom);
        }

        @Override // androidx.cardview.widget.d
        public void a(int i, int i2) {
            CardView cardView = CardView.this;
            if (i > cardView.f1143d) {
                CardView.super.setMinimumWidth(i);
            }
            CardView cardView2 = CardView.this;
            if (i2 > cardView2.f1144e) {
                CardView.super.setMinimumHeight(i2);
            }
        }

        @Override // androidx.cardview.widget.d
        public View a() {
            return CardView.this;
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.c.a.cardViewStyle);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        j.a(this.h, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        int color;
        ColorStateList valueOf;
        this.f1145f = new Rect();
        this.g = new Rect();
        this.h = new a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.c.e.CardView, i2, a.c.d.CardView);
        if (obtainStyledAttributes.hasValue(a.c.e.CardView_cardBackgroundColor)) {
            valueOf = obtainStyledAttributes.getColorStateList(a.c.e.CardView_cardBackgroundColor);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(i);
            int color2 = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(a.c.b.cardview_light_background);
            } else {
                color = getResources().getColor(a.c.b.cardview_dark_background);
            }
            valueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = valueOf;
        float dimension = obtainStyledAttributes.getDimension(a.c.e.CardView_cardCornerRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(a.c.e.CardView_cardElevation, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(a.c.e.CardView_cardMaxElevation, 0.0f);
        this.f1141b = obtainStyledAttributes.getBoolean(a.c.e.CardView_cardUseCompatPadding, false);
        this.f1142c = obtainStyledAttributes.getBoolean(a.c.e.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_contentPadding, 0);
        this.f1145f.left = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_contentPaddingLeft, dimensionPixelSize);
        this.f1145f.top = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_contentPaddingTop, dimensionPixelSize);
        this.f1145f.right = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_contentPaddingRight, dimensionPixelSize);
        this.f1145f.bottom = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_contentPaddingBottom, dimensionPixelSize);
        float f2 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f1143d = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_android_minWidth, 0);
        this.f1144e = obtainStyledAttributes.getDimensionPixelSize(a.c.e.CardView_android_minHeight, 0);
        obtainStyledAttributes.recycle();
        j.a(this.h, context, colorStateList, dimension, dimension2, f2);
    }
}
