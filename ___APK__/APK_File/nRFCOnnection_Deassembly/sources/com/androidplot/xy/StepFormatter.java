package com.androidplot.xy;

import com.androidplot.ui.SeriesRenderer;

/* loaded from: classes.dex */
public class StepFormatter extends LineAndPointFormatter {
    public StepFormatter() {
    }

    @Override // com.androidplot.xy.LineAndPointFormatter, com.androidplot.ui.Formatter
    public Class<? extends SeriesRenderer> getRendererClass() {
        return StepRenderer.class;
    }

    public StepFormatter(Integer num, Integer num2) {
        initLinePaint(num);
        initFillPaint(num2);
    }

    @Override // com.androidplot.xy.LineAndPointFormatter, com.androidplot.xy.XYSeriesFormatter, com.androidplot.ui.Formatter
    public SeriesRenderer getRendererInstance(XYPlot xYPlot) {
        return new StepRenderer(xYPlot);
    }
}
