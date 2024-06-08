package com.androidplot.xy;

import com.androidplot.ui.Formatter;
import com.androidplot.ui.SeriesRenderer;
import com.androidplot.util.ZHash;
import com.androidplot.util.ZIndexable;
import com.androidplot.xy.XYRegionFormatter;

/* loaded from: classes.dex */
public abstract class XYSeriesFormatter<XYRegionFormatterType extends XYRegionFormatter> extends Formatter<XYPlot> {
    ZHash<RectRegion, XYRegionFormatterType> regions = new ZHash<>();

    public void addRegion(RectRegion rectRegion, XYRegionFormatterType xyregionformattertype) {
        this.regions.addToBottom(rectRegion, xyregionformattertype);
    }

    public XYRegionFormatterType getRegionFormatter(RectRegion rectRegion) {
        return this.regions.get(rectRegion);
    }

    public ZIndexable<RectRegion> getRegions() {
        return this.regions;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.androidplot.ui.Formatter
    public abstract SeriesRenderer getRendererInstance(XYPlot xYPlot);

    public void removeRegion(RectRegion rectRegion) {
        this.regions.remove(rectRegion);
    }
}
