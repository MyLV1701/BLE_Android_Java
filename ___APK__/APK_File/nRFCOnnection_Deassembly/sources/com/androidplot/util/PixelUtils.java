package com.androidplot.util;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class PixelUtils {
    private static final float FLOAT_INT_AVG_NUDGE = 0.5f;
    private static DisplayMetrics metrics;
    public static final Map<String, Integer> dimensionConstantLookup = initDimensionConstantLookup();
    private static final Pattern DIMENSION_PATTERN = Pattern.compile("^\\s*(\\d+(\\.\\d+)*)\\s*([a-zA-Z]+)\\s*$");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InternalDimension {
        int unit;
        float value;

        public InternalDimension(float f2, int i) {
            this.value = f2;
            this.unit = i;
        }
    }

    public static PointF add(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x + pointF2.x, pointF.y + pointF2.y);
    }

    public static float dpToPix(float f2) {
        return TypedValue.applyDimension(1, f2, metrics);
    }

    public static float fractionToPixH(float f2) {
        return metrics.heightPixels * f2;
    }

    public static float fractionToPixW(float f2) {
        return metrics.widthPixels * f2;
    }

    public static void init(Context context) {
        if (metrics == null) {
            metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        }
    }

    private static Map<String, Integer> initDimensionConstantLookup() {
        HashMap hashMap = new HashMap();
        hashMap.put("px", 0);
        hashMap.put("dip", 1);
        hashMap.put("dp", 1);
        hashMap.put("sp", 2);
        hashMap.put("pt", 3);
        hashMap.put("in", 4);
        hashMap.put("mm", 5);
        return Collections.unmodifiableMap(hashMap);
    }

    public static RectF nearestPixRect(float f2, float f3, float f4, float f5) {
        return new RectF((int) (f2 + FLOAT_INT_AVG_NUDGE), (int) (f3 + FLOAT_INT_AVG_NUDGE), (int) (f4 + FLOAT_INT_AVG_NUDGE), (int) (f5 + FLOAT_INT_AVG_NUDGE));
    }

    public static RectF sink(RectF rectF) {
        return nearestPixRect(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public static float spToPix(float f2) {
        return TypedValue.applyDimension(2, f2, metrics);
    }

    public static float stringToDimension(String str) {
        InternalDimension stringToInternalDimension = stringToInternalDimension(str);
        return TypedValue.applyDimension(stringToInternalDimension.unit, stringToInternalDimension.value, metrics);
    }

    private static InternalDimension stringToInternalDimension(String str) {
        Matcher matcher = DIMENSION_PATTERN.matcher(str);
        if (matcher.matches()) {
            float floatValue = Float.valueOf(matcher.group(1)).floatValue();
            Integer num = dimensionConstantLookup.get(matcher.group(3).toLowerCase());
            if (num != null) {
                return new InternalDimension(floatValue, num.intValue());
            }
            throw new NumberFormatException();
        }
        throw new NumberFormatException();
    }

    public static PointF sub(PointF pointF, PointF pointF2) {
        return new PointF(pointF.x - pointF2.x, pointF.y - pointF2.y);
    }
}
