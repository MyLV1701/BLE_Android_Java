package com.androidplot.ui;

import java.lang.Enum;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class LayoutMetric<LayoutType extends Enum> {
    private LayoutType layoutType;
    private float value;

    public LayoutMetric(float f2, LayoutType layouttype) {
        validatePair(f2, layouttype);
        set(f2, layouttype);
    }

    public LayoutType getLayoutType() {
        return this.layoutType;
    }

    public abstract float getPixelValue(float f2);

    public float getValue() {
        return this.value;
    }

    public void set(float f2, LayoutType layouttype) {
        validatePair(f2, layouttype);
        this.value = f2;
        this.layoutType = layouttype;
    }

    public void setLayoutType(LayoutType layouttype) {
        validatePair(this.value, layouttype);
        this.layoutType = layouttype;
    }

    public void setValue(float f2) {
        validatePair(f2, this.layoutType);
        this.value = f2;
    }

    protected abstract void validatePair(float f2, LayoutType layouttype);
}
