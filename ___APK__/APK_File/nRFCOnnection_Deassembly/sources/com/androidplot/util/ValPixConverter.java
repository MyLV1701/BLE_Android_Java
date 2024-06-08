package com.androidplot.util;

import android.graphics.PointF;
import android.graphics.RectF;

/* loaded from: classes.dex */
public class ValPixConverter {
    private static final int ZERO = 0;
    private static final PointF mPoint = new PointF();

    public static double pixToVal(float f2, double d2, double d3, float f3, boolean z) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("pixel values cannot be negative.");
        }
        if (f3 > 0.0f) {
            if (z) {
                f2 = f3 - f2;
            }
            double d4 = f3;
            Double.isNaN(d4);
            double d5 = (d3 - d2) / d4;
            double d6 = f2;
            Double.isNaN(d6);
            return (d5 * d6) + d2;
        }
        throw new IllegalArgumentException("Length in pixels must be greater than 0.");
    }

    public static double valPerPix(double d2, double d3, float f2) {
        double d4 = d3 - d2;
        double d5 = f2;
        Double.isNaN(d5);
        return d4 / d5;
    }

    public static float valToPix(float f2, double d2, double d3, float f3, boolean z) {
        if (f3 > 0.0f) {
            double d4 = f3;
            Double.isNaN(d4);
            double d5 = d4 / (d3 - d2);
            double d6 = f2;
            Double.isNaN(d6);
            float f4 = (float) ((d6 - d2) * d5);
            return z ? f3 - f4 : f4;
        }
        throw new IllegalArgumentException("Length in pixels must be greater than 0.");
    }

    public static PointF valToPix(int i, float f2, RectF rectF, float f3, float f4, float f5, float f6) {
        mPoint.x = valToPix(i, f3, f4, rectF.width(), false) + rectF.left;
        mPoint.y = valToPix(f2, f5, f6, rectF.height(), true) + rectF.top;
        return mPoint;
    }
}
