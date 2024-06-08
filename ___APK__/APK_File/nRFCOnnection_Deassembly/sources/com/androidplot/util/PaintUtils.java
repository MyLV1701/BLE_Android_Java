package com.androidplot.util;

import android.graphics.Paint;

/* loaded from: classes.dex */
public class PaintUtils {
    public static void setFontSizeDp(Paint paint, float f2) {
        paint.setTextSize(PixelUtils.dpToPix(f2));
    }

    public static void setLineSizeDp(Paint paint, float f2) {
        paint.setStrokeWidth(PixelUtils.dpToPix(f2));
    }

    public static void setShadowDp(Paint paint, float f2, float f3, float f4, int i) {
        paint.setShadowLayer(PixelUtils.dpToPix(f2), PixelUtils.dpToPix(f3), PixelUtils.dpToPix(f4), i);
    }
}
