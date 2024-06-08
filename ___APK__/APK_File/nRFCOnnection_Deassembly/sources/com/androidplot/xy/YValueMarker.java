package com.androidplot.xy;

import android.graphics.Paint;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.XPositionMetric;

/* loaded from: classes.dex */
public class YValueMarker extends ValueMarker<XPositionMetric> {
    public YValueMarker(Number number, String str) {
        super(number, str, new XPositionMetric(3.0f, XLayoutStyle.ABSOLUTE_FROM_LEFT));
    }

    public YValueMarker(Number number, String str, XPositionMetric xPositionMetric, Paint paint, Paint paint2) {
        super(number, str, xPositionMetric, paint, paint2);
    }

    public YValueMarker(Number number, String str, XPositionMetric xPositionMetric, int i, int i2) {
        super(number, str, xPositionMetric, i, i2);
    }
}
