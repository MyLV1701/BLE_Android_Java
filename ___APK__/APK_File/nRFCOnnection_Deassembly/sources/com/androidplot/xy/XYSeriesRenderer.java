package com.androidplot.xy;

import com.androidplot.ui.SeriesAndFormatterList;
import com.androidplot.ui.SeriesRenderer;
import com.androidplot.xy.XYSeriesFormatter;
import java.util.Hashtable;

/* loaded from: classes.dex */
public abstract class XYSeriesRenderer<XYFormatterType extends XYSeriesFormatter> extends SeriesRenderer<XYPlot, XYSeries, XYFormatterType> {
    public XYSeriesRenderer(XYPlot xYPlot) {
        super(xYPlot);
    }

    public Hashtable<XYRegionFormatter, String> getUniqueRegionFormatters() {
        Hashtable<XYRegionFormatter, String> hashtable = new Hashtable<>();
        SeriesAndFormatterList<XYSeries, XYFormatterType> seriesAndFormatterList = getSeriesAndFormatterList();
        if (seriesAndFormatterList != 0) {
            for (XYSeriesFormatter xYSeriesFormatter : seriesAndFormatterList.getFormatterList()) {
                for (RectRegion rectRegion : xYSeriesFormatter.getRegions().elements()) {
                    hashtable.put(xYSeriesFormatter.getRegionFormatter(rectRegion), rectRegion.getLabel());
                }
            }
        }
        return hashtable;
    }
}
