package no.nordicsemi.android.mcp.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class ForegroundLinearLayout extends LinearLayout {
    private Drawable mForegroundSelector;
    private Rect mRectPadding;
    private boolean mUseBackgroundPadding;

    public ForegroundLinearLayout(Context context) {
        super(context);
        this.mUseBackgroundPadding = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Drawable drawable = this.mForegroundSelector;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    @Override // android.view.View
    @TargetApi(21)
    public void drawableHotspotChanged(float f2, float f3) {
        super.drawableHotspotChanged(f2, f3);
        Drawable drawable = this.mForegroundSelector;
        if (drawable != null) {
            drawable.setHotspot(f2, f3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mForegroundSelector;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        this.mForegroundSelector.setState(getDrawableState());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mForegroundSelector;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Drawable drawable = this.mForegroundSelector;
        if (drawable != null) {
            if (this.mUseBackgroundPadding) {
                Rect rect = this.mRectPadding;
                drawable.setBounds(rect.left, rect.top, i - rect.right, i2 - rect.bottom);
            } else {
                drawable.setBounds(0, 0, i, i2);
            }
        }
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        Drawable drawable2 = this.mForegroundSelector;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.mForegroundSelector);
            }
            this.mForegroundSelector = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mForegroundSelector;
    }

    public ForegroundLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ForegroundLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUseBackgroundPadding = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ForegroundRelativeLayout, i, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        if (drawable != null) {
            setForeground(drawable);
        }
        obtainStyledAttributes.recycle();
        if (getBackground() instanceof NinePatchDrawable) {
            NinePatchDrawable ninePatchDrawable = (NinePatchDrawable) getBackground();
            this.mRectPadding = new Rect();
            if (ninePatchDrawable.getPadding(this.mRectPadding)) {
                this.mUseBackgroundPadding = true;
            }
        }
    }
}
