package com.androidplot.ui;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.Region;
import com.androidplot.Plot;
import com.androidplot.Series;
import com.androidplot.ui.Formatter;

/* loaded from: classes.dex */
public abstract class SeriesRenderer<PlotType extends Plot, SeriesType extends Series, SeriesFormatterType extends Formatter> {
    private PlotType plot;

    public SeriesRenderer(PlotType plottype) {
        this.plot = plottype;
    }

    protected abstract void doDrawLegendIcon(Canvas canvas, RectF rectF, SeriesFormatterType seriesformattertype);

    public void drawSeriesLegendIcon(Canvas canvas, RectF rectF, SeriesFormatterType seriesformattertype) {
        try {
            canvas.save();
            canvas.clipRect(rectF, Region.Op.INTERSECT);
            doDrawLegendIcon(canvas, rectF, seriesformattertype);
        } finally {
            canvas.restore();
        }
    }

    public SeriesFormatterType getFormatter(SeriesType seriestype) {
        return (SeriesFormatterType) this.plot.getFormatter(seriestype, getClass());
    }

    public PlotType getPlot() {
        return this.plot;
    }

    public SeriesAndFormatterList<SeriesType, SeriesFormatterType> getSeriesAndFormatterList() {
        return this.plot.getSeriesAndFormatterListForRenderer(getClass());
    }

    public abstract void onRender(Canvas canvas, RectF rectF);

    public void render(Canvas canvas, RectF rectF) {
        onRender(canvas, rectF);
    }

    public void setPlot(PlotType plottype) {
        this.plot = plottype;
    }
}
