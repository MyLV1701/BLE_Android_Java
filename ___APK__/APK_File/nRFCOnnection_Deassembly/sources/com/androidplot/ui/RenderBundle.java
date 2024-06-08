package com.androidplot.ui;

import com.androidplot.Series;
import com.androidplot.ui.RenderBundle;
import com.androidplot.xy.XYSeriesFormatter;

/* loaded from: classes.dex */
public abstract class RenderBundle<RenderBundleType extends RenderBundle, SeriesType extends Series, SeriesFormatterType extends XYSeriesFormatter> {
    private SeriesFormatterType formatter;
    private Series series;

    public RenderBundle(SeriesType seriestype, SeriesFormatterType seriesformattertype) {
        this.formatter = seriesformattertype;
        this.series = seriestype;
    }

    public SeriesFormatterType getFormatter() {
        return this.formatter;
    }

    public Series getSeries() {
        return this.series;
    }

    public void setFormatter(SeriesFormatterType seriesformattertype) {
        this.formatter = seriesformattertype;
    }

    public void setSeries(Series series) {
        this.series = series;
    }
}
