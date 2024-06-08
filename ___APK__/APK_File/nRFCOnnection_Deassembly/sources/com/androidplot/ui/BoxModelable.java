package com.androidplot.ui;

import android.graphics.RectF;

/* loaded from: classes.dex */
public interface BoxModelable {
    float getMarginBottom();

    float getMarginLeft();

    float getMarginRight();

    float getMarginTop();

    RectF getMarginatedRect(RectF rectF);

    RectF getPaddedRect(RectF rectF);

    float getPaddingBottom();

    float getPaddingLeft();

    float getPaddingRight();

    float getPaddingTop();

    void setMarginLeft(float f2);

    void setMarginRight(float f2);

    void setMarginTop(float f2);

    void setMargins(float f2, float f3, float f4, float f5);

    void setPadding(float f2, float f3, float f4, float f5);

    void setPaddingBottom(float f2);

    void setPaddingLeft(float f2);

    void setPaddingRight(float f2);

    void setPaddingTop(float f2);
}
