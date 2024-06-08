package com.viewpagerindicator;

import a.f.l.i;
import a.f.l.x;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.viewpager.widget.ViewPager;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class CirclePageIndicator extends View implements com.viewpagerindicator.a {

    /* renamed from: b, reason: collision with root package name */
    private float f2802b;

    /* renamed from: c, reason: collision with root package name */
    private final Paint f2803c;

    /* renamed from: d, reason: collision with root package name */
    private final Paint f2804d;

    /* renamed from: e, reason: collision with root package name */
    private final Paint f2805e;

    /* renamed from: f, reason: collision with root package name */
    private ViewPager f2806f;
    private ViewPager.j g;
    private int h;
    private int i;
    private float j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private int o;
    private float p;
    private int q;
    private boolean r;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends View.BaseSavedState {
        public static final Parcelable.Creator<b> CREATOR = new a();

        /* renamed from: b, reason: collision with root package name */
        int f2807b;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<b> {
            a() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public b createFromParcel(Parcel parcel) {
                return new b(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public b[] newArray(int i) {
                return new b[i];
            }
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f2807b);
        }

        public b(Parcelable parcelable) {
            super(parcelable);
        }

        private b(Parcel parcel) {
            super(parcel);
            this.f2807b = parcel.readInt();
        }
    }

    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    private int a(int i) {
        ViewPager viewPager;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824 || (viewPager = this.f2806f) == null) {
            return size;
        }
        int count = viewPager.getAdapter().getCount();
        float paddingLeft = getPaddingLeft() + getPaddingRight();
        float f2 = this.f2802b;
        int i2 = (int) (paddingLeft + (count * 2 * f2) + ((count - 1) * f2) + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(i2, size) : i2;
    }

    private int b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (int) ((this.f2802b * 2.0f) + getPaddingTop() + getPaddingBottom() + 1.0f);
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    public int getFillColor() {
        return this.f2805e.getColor();
    }

    public int getOrientation() {
        return this.l;
    }

    public int getPageColor() {
        return this.f2803c.getColor();
    }

    public float getRadius() {
        return this.f2802b;
    }

    public int getStrokeColor() {
        return this.f2804d.getColor();
    }

    public float getStrokeWidth() {
        return this.f2804d.getStrokeWidth();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int count;
        int height;
        int paddingTop;
        int paddingBottom;
        int paddingLeft;
        float f2;
        float f3;
        super.onDraw(canvas);
        ViewPager viewPager = this.f2806f;
        if (viewPager == null || (count = viewPager.getAdapter().getCount()) == 0) {
            return;
        }
        if (this.h >= count) {
            setCurrentItem(count - 1);
            return;
        }
        if (this.l == 0) {
            height = getWidth();
            paddingTop = getPaddingLeft();
            paddingBottom = getPaddingRight();
            paddingLeft = getPaddingTop();
        } else {
            height = getHeight();
            paddingTop = getPaddingTop();
            paddingBottom = getPaddingBottom();
            paddingLeft = getPaddingLeft();
        }
        float f4 = this.f2802b;
        float f5 = 3.0f * f4;
        float f6 = paddingLeft + f4;
        float f7 = paddingTop + f4;
        if (this.m) {
            f7 += (((height - paddingTop) - paddingBottom) / 2.0f) - ((count * f5) / 2.0f);
        }
        float f8 = this.f2802b;
        if (this.f2804d.getStrokeWidth() > 0.0f) {
            f8 -= this.f2804d.getStrokeWidth() / 2.0f;
        }
        for (int i = 0; i < count; i++) {
            float f9 = (i * f5) + f7;
            if (this.l == 0) {
                f3 = f6;
            } else {
                f3 = f9;
                f9 = f6;
            }
            if (this.f2803c.getAlpha() > 0) {
                canvas.drawCircle(f9, f3, f8, this.f2803c);
            }
            float f10 = this.f2802b;
            if (f8 != f10) {
                canvas.drawCircle(f9, f3, f10, this.f2804d);
            }
        }
        float f11 = (this.n ? this.i : this.h) * f5;
        if (!this.n) {
            f11 += this.j * f5;
        }
        if (this.l == 0) {
            f2 = f11 + f7;
        } else {
            f6 = f11 + f7;
            f2 = f6;
        }
        canvas.drawCircle(f2, f6, this.f2802b, this.f2805e);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.l == 0) {
            setMeasuredDimension(a(i), b(i2));
        } else {
            setMeasuredDimension(b(i), a(i2));
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void onPageScrollStateChanged(int i) {
        this.k = i;
        ViewPager.j jVar = this.g;
        if (jVar != null) {
            jVar.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void onPageScrolled(int i, float f2, int i2) {
        this.h = i;
        this.j = f2;
        invalidate();
        ViewPager.j jVar = this.g;
        if (jVar != null) {
            jVar.onPageScrolled(i, f2, i2);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.j
    public void onPageSelected(int i) {
        if (this.n || this.k == 0) {
            this.h = i;
            this.i = i;
            invalidate();
        }
        ViewPager.j jVar = this.g;
        if (jVar != null) {
            jVar.onPageSelected(i);
        }
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        b bVar = (b) parcelable;
        super.onRestoreInstanceState(bVar.getSuperState());
        int i = bVar.f2807b;
        this.h = i;
        this.i = i;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        b bVar = new b(super.onSaveInstanceState());
        bVar.f2807b = this.h;
        return bVar;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (super.onTouchEvent(motionEvent)) {
            return true;
        }
        ViewPager viewPager = this.f2806f;
        if (viewPager == null || viewPager.getAdapter().getCount() == 0) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action == 2) {
                    float c2 = i.c(motionEvent, i.a(motionEvent, this.q));
                    float f2 = c2 - this.p;
                    if (!this.r && Math.abs(f2) > this.o) {
                        this.r = true;
                    }
                    if (this.r) {
                        this.p = c2;
                        if (this.f2806f.isFakeDragging() || this.f2806f.beginFakeDrag()) {
                            this.f2806f.fakeDragBy(f2);
                        }
                    }
                } else if (action != 3) {
                    if (action == 5) {
                        int a2 = i.a(motionEvent);
                        this.p = i.c(motionEvent, a2);
                        this.q = i.b(motionEvent, a2);
                    } else if (action == 6) {
                        int a3 = i.a(motionEvent);
                        if (i.b(motionEvent, a3) == this.q) {
                            this.q = i.b(motionEvent, a3 == 0 ? 1 : 0);
                        }
                        this.p = i.c(motionEvent, i.a(motionEvent, this.q));
                    }
                }
            }
            if (!this.r) {
                int count = this.f2806f.getAdapter().getCount();
                float width = getWidth();
                float f3 = width / 2.0f;
                float f4 = width / 6.0f;
                if (this.h > 0 && motionEvent.getX() < f3 - f4) {
                    if (action != 3) {
                        this.f2806f.setCurrentItem(this.h - 1);
                    }
                    return true;
                }
                if (this.h < count - 1 && motionEvent.getX() > f3 + f4) {
                    if (action != 3) {
                        this.f2806f.setCurrentItem(this.h + 1);
                    }
                    return true;
                }
            }
            this.r = false;
            this.q = -1;
            if (this.f2806f.isFakeDragging()) {
                this.f2806f.endFakeDrag();
            }
        } else {
            this.q = i.b(motionEvent, 0);
            this.p = motionEvent.getX();
        }
        return true;
    }

    public void setCentered(boolean z) {
        this.m = z;
        invalidate();
    }

    public void setCurrentItem(int i) {
        ViewPager viewPager = this.f2806f;
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
            this.h = i;
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager has not been bound.");
    }

    public void setFillColor(int i) {
        this.f2805e.setColor(i);
        invalidate();
    }

    @Override // com.viewpagerindicator.a
    public void setOnPageChangeListener(ViewPager.j jVar) {
        this.g = jVar;
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.");
        }
        this.l = i;
        requestLayout();
    }

    public void setPageColor(int i) {
        this.f2803c.setColor(i);
        invalidate();
    }

    public void setRadius(float f2) {
        this.f2802b = f2;
        invalidate();
    }

    public void setSnap(boolean z) {
        this.n = z;
        invalidate();
    }

    public void setStrokeColor(int i) {
        this.f2804d.setColor(i);
        invalidate();
    }

    public void setStrokeWidth(float f2) {
        this.f2804d.setStrokeWidth(f2);
        invalidate();
    }

    @Override // com.viewpagerindicator.a
    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.f2806f;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() != null) {
            this.f2806f = viewPager;
            this.f2806f.setOnPageChangeListener(this);
            invalidate();
            return;
        }
        throw new IllegalStateException("ViewPager does not have adapter instance.");
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.vpiCirclePageIndicatorStyle);
    }

    public CirclePageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2803c = new Paint(1);
        this.f2804d = new Paint(1);
        this.f2805e = new Paint(1);
        this.p = -1.0f;
        this.q = -1;
        if (isInEditMode()) {
            return;
        }
        Resources resources = getResources();
        int color = resources.getColor(R.color.default_circle_indicator_page_color);
        int color2 = resources.getColor(R.color.default_circle_indicator_fill_color);
        int integer = resources.getInteger(R.integer.default_circle_indicator_orientation);
        int color3 = resources.getColor(R.color.default_circle_indicator_stroke_color);
        float dimension = resources.getDimension(R.dimen.default_circle_indicator_stroke_width);
        float dimension2 = resources.getDimension(R.dimen.default_circle_indicator_radius);
        boolean z = resources.getBoolean(R.bool.default_circle_indicator_centered);
        boolean z2 = resources.getBoolean(R.bool.default_circle_indicator_snap);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CirclePageIndicator, i, 0);
        this.m = obtainStyledAttributes.getBoolean(2, z);
        this.l = obtainStyledAttributes.getInt(0, integer);
        this.f2803c.setStyle(Paint.Style.FILL);
        this.f2803c.setColor(obtainStyledAttributes.getColor(4, color));
        this.f2804d.setStyle(Paint.Style.STROKE);
        this.f2804d.setColor(obtainStyledAttributes.getColor(7, color3));
        this.f2804d.setStrokeWidth(obtainStyledAttributes.getDimension(8, dimension));
        this.f2805e.setStyle(Paint.Style.FILL);
        this.f2805e.setColor(obtainStyledAttributes.getColor(3, color2));
        this.f2802b = obtainStyledAttributes.getDimension(5, dimension2);
        this.n = obtainStyledAttributes.getBoolean(6, z2);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        if (drawable != null) {
            setBackgroundDrawable(drawable);
        }
        obtainStyledAttributes.recycle();
        this.o = x.b(ViewConfiguration.get(context));
    }
}
