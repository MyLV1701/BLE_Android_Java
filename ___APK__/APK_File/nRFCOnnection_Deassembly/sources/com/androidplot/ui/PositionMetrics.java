package com.androidplot.ui;

/* loaded from: classes.dex */
public class PositionMetrics implements Comparable<PositionMetrics> {
    private AnchorPosition anchor;
    private float layerDepth;
    private XPositionMetric xPositionMetric;
    private YPositionMetric yPositionMetric;

    public PositionMetrics(float f2, XLayoutStyle xLayoutStyle, float f3, YLayoutStyle yLayoutStyle, AnchorPosition anchorPosition) {
        setXPositionMetric(new XPositionMetric(f2, xLayoutStyle));
        setYPositionMetric(new YPositionMetric(f3, yLayoutStyle));
        setAnchor(anchorPosition);
    }

    public AnchorPosition getAnchor() {
        return this.anchor;
    }

    public XPositionMetric getXPositionMetric() {
        return this.xPositionMetric;
    }

    public YPositionMetric getYPositionMetric() {
        return this.yPositionMetric;
    }

    public void setAnchor(AnchorPosition anchorPosition) {
        this.anchor = anchorPosition;
    }

    public void setXPositionMetric(XPositionMetric xPositionMetric) {
        this.xPositionMetric = xPositionMetric;
    }

    public void setYPositionMetric(YPositionMetric yPositionMetric) {
        this.yPositionMetric = yPositionMetric;
    }

    @Override // java.lang.Comparable
    public int compareTo(PositionMetrics positionMetrics) {
        float f2 = this.layerDepth;
        float f3 = positionMetrics.layerDepth;
        if (f2 < f3) {
            return -1;
        }
        return f2 == f3 ? 0 : 1;
    }
}
