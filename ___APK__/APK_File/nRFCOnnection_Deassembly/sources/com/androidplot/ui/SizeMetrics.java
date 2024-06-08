package com.androidplot.ui;

import android.graphics.RectF;
import com.androidplot.util.PixelUtils;

/* loaded from: classes.dex */
public class SizeMetrics {
    private SizeMetric heightMetric;
    private SizeMetric widthMetric;

    public SizeMetrics(float f2, SizeLayoutType sizeLayoutType, float f3, SizeLayoutType sizeLayoutType2) {
        this.heightMetric = new SizeMetric(f2, sizeLayoutType);
        this.widthMetric = new SizeMetric(f3, sizeLayoutType2);
    }

    public SizeMetric getHeightMetric() {
        return this.heightMetric;
    }

    public RectF getRectF(RectF rectF) {
        return new RectF(0.0f, 0.0f, this.widthMetric.getPixelValue(rectF.width()), this.heightMetric.getPixelValue(rectF.height()));
    }

    public RectF getRoundedRect(RectF rectF) {
        return PixelUtils.nearestPixRect(0.0f, 0.0f, this.widthMetric.getPixelValue(rectF.width()), this.heightMetric.getPixelValue(rectF.height()));
    }

    public SizeMetric getWidthMetric() {
        return this.widthMetric;
    }

    public void setHeightMetric(SizeMetric sizeMetric) {
        this.heightMetric = sizeMetric;
    }

    public void setWidthMetric(SizeMetric sizeMetric) {
        this.widthMetric = sizeMetric;
    }

    public SizeMetrics(SizeMetric sizeMetric, SizeMetric sizeMetric2) {
        this.heightMetric = sizeMetric;
        this.widthMetric = sizeMetric2;
    }
}
