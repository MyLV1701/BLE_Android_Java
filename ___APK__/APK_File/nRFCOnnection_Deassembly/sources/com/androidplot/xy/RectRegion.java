package com.androidplot.xy;

import android.graphics.PointF;
import android.graphics.RectF;
import com.androidplot.LineRegion;
import com.androidplot.util.ValPixConverter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RectRegion {
    private String label;
    LineRegion xLineRegion;
    LineRegion yLineRegion;

    public RectRegion(Number number, Number number2, Number number3, Number number4, String str) {
        this.xLineRegion = new LineRegion(number, number2);
        this.yLineRegion = new LineRegion(number3, number4);
        setLabel(str);
    }

    public static List<RectRegion> regionsWithin(List<RectRegion> list, int i, int i2, float f2, float f3) {
        ArrayList arrayList = new ArrayList();
        for (RectRegion rectRegion : list) {
            if (rectRegion.intersects(Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(f2), Float.valueOf(f3))) {
                arrayList.add(rectRegion);
            }
        }
        return arrayList;
    }

    public boolean containsDomainValue(Number number) {
        return this.xLineRegion.contains(number);
    }

    public boolean containsPoint(PointF pointF) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public boolean containsRangeValue(Number number) {
        return this.yLineRegion.contains(number);
    }

    public boolean containsValue(Number number, Number number2) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    public String getLabel() {
        return this.label;
    }

    public int getMaxX() {
        return this.xLineRegion.getMaxVal().intValue();
    }

    public float getMaxY() {
        return this.yLineRegion.getMaxVal().floatValue();
    }

    public int getMinX() {
        return this.xLineRegion.getMinVal().intValue();
    }

    public float getMinY() {
        return this.yLineRegion.getMinVal().floatValue();
    }

    public RectF getRectF(RectF rectF, int i, int i2, float f2, float f3) {
        int i3;
        float f4;
        int intValue = this.xLineRegion.getMinVal().intValue() != Integer.MIN_VALUE ? this.xLineRegion.getMinVal().intValue() : i;
        if (this.yLineRegion.getMaxVal().floatValue() != Float.POSITIVE_INFINITY) {
            f4 = this.yLineRegion.getMaxVal().floatValue();
            i3 = i;
        } else {
            i3 = i;
            f4 = f3;
        }
        float f5 = i3;
        float f6 = i2;
        PointF valToPix = ValPixConverter.valToPix(intValue, f4, rectF, f5, f6, f2, f3);
        PointF valToPix2 = ValPixConverter.valToPix(this.xLineRegion.getMaxVal().intValue() != Integer.MIN_VALUE ? this.xLineRegion.getMaxVal().intValue() : i2, this.yLineRegion.getMinVal().floatValue() != Float.NEGATIVE_INFINITY ? this.yLineRegion.getMinVal().floatValue() : f2, rectF, f5, f6, f2, f3);
        return new RectF(valToPix.x, valToPix.y, valToPix2.x, valToPix2.y);
    }

    public boolean intersects(RectRegion rectRegion) {
        return intersects(Integer.valueOf(rectRegion.getMinX()), Integer.valueOf(rectRegion.getMaxX()), Float.valueOf(rectRegion.getMinY()), Float.valueOf(rectRegion.getMaxY()));
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setMaxX(int i) {
        this.xLineRegion.setMaxVal(Integer.valueOf(i));
    }

    public void setMaxY(float f2) {
        this.yLineRegion.setMaxVal(Float.valueOf(f2));
    }

    public void setMinX(int i) {
        this.xLineRegion.setMinVal(Integer.valueOf(i));
    }

    public void setMinY(float f2) {
        this.yLineRegion.setMinVal(Float.valueOf(f2));
    }

    public boolean intersects(Integer num, Integer num2, Float f2, Float f3) {
        return this.xLineRegion.intersects(num, num2) && this.yLineRegion.intersects(f2, f3);
    }

    public boolean intersects(RectF rectF, Integer num, Integer num2, Float f2, Float f3) {
        return RectF.intersects(getRectF(rectF, num.intValue(), num2.intValue(), f2.floatValue(), f3.floatValue()), rectF);
    }

    public RectRegion(Number number, Number number2, Number number3, Number number4) {
        this(number, number2, number3, number4, null);
    }
}
