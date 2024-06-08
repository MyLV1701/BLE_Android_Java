package com.androidplot.util;

import android.graphics.Paint;
import android.graphics.Rect;

/* loaded from: classes.dex */
public class FontUtils {
    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (-fontMetrics.ascent) + fontMetrics.descent;
    }

    public static Rect getPackedStringDimensions(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    public static Rect getStringDimensions(String str, Paint paint) {
        Rect rect = new Rect();
        if (str == null || str.length() == 0) {
            return null;
        }
        paint.getTextBounds(str, 0, str.length(), rect);
        rect.bottom = rect.top + ((int) getFontHeight(paint));
        return rect;
    }
}
