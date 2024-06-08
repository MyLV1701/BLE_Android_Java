package com.androidplot;

/* loaded from: classes.dex */
public class LineRegion {
    private Number maxVal;
    private Number minVal;

    public LineRegion(Number number, Number number2) {
        if (number.doubleValue() < number2.doubleValue()) {
            setMinVal(number);
            setMaxVal(number2);
        } else {
            setMinVal(number2);
            setMaxVal(number);
        }
    }

    public static Number measure(Number number, Number number2) {
        return new LineRegion(number, number2).length();
    }

    public boolean contains(Number number) {
        return number.doubleValue() >= this.minVal.doubleValue() && number.doubleValue() <= this.maxVal.doubleValue();
    }

    public Number getMaxVal() {
        return this.maxVal;
    }

    public Number getMinVal() {
        return this.minVal;
    }

    public boolean intersects(LineRegion lineRegion) {
        return intersects(lineRegion.getMinVal(), lineRegion.getMaxVal());
    }

    public Number length() {
        return Double.valueOf(this.maxVal.doubleValue() - this.minVal.doubleValue());
    }

    public void setMaxVal(Number number) {
        if (number != null) {
            this.maxVal = number;
            return;
        }
        throw new NullPointerException("Region values can never be null.");
    }

    public void setMinVal(Number number) {
        if (number != null) {
            this.minVal = number;
            return;
        }
        throw new NullPointerException("Region values can never be null.");
    }

    public boolean intersects(Number number, Number number2) {
        return (number.doubleValue() <= this.minVal.doubleValue() && number2.doubleValue() >= this.maxVal.doubleValue()) || contains(number) || contains(number2);
    }
}
