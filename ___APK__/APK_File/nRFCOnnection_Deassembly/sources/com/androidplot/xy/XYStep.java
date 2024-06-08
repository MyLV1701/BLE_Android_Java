package com.androidplot.xy;

/* loaded from: classes.dex */
public class XYStep {
    private final float stepCount;
    private final float stepPix;
    private final double stepVal;

    public XYStep(float f2, float f3, double d2) {
        this.stepCount = f2;
        this.stepPix = f3;
        this.stepVal = d2;
    }

    public double getStepCount() {
        return this.stepCount;
    }

    public float getStepPix() {
        return this.stepPix;
    }

    public double getStepVal() {
        return this.stepVal;
    }
}
