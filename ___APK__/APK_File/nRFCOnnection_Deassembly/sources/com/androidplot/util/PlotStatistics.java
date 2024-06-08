package com.androidplot.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.androidplot.Plot;
import com.androidplot.PlotListener;
import java.util.Locale;

/* loaded from: classes.dex */
public class PlotStatistics implements PlotListener {
    private boolean annotatePlotEnabled;
    long avgRenderTimeMs;
    long fps;
    long lastAnnotation;
    long maxRenderTimeMs;
    long minRenderTimeMs;
    long updateDelayMs;
    long longestRenderMs = 0;
    long shortestRenderMs = 0;
    long lastStart = 0;
    long lastLatency = 0;
    long latencySamples = 0;
    long latencySum = 0;
    String annotationString = "";
    private Paint paint = new Paint();

    public PlotStatistics(long j, boolean z) {
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setColor(-1);
        this.paint.setTextSize(30.0f);
        resetCounters();
        this.updateDelayMs = j;
        this.annotatePlotEnabled = z;
    }

    private void annotatePlot(Plot plot, Canvas canvas) {
        long currentTimeMillis = System.currentTimeMillis();
        long j = currentTimeMillis - this.lastAnnotation;
        if (j >= this.updateDelayMs) {
            long j2 = this.latencySamples;
            float f2 = j2 > 0 ? (float) (this.latencySum / j2) : 0.0f;
            Locale locale = Locale.US;
            Object[] objArr = new Object[1];
            long j3 = this.latencySamples;
            objArr[0] = Float.valueOf(j3 > 0 ? (1000.0f / ((float) j)) * ((float) j3) : 0.0f);
            String format = String.format(locale, "%.2f", objArr);
            Locale locale2 = Locale.US;
            Object[] objArr2 = new Object[1];
            objArr2[0] = Float.valueOf(this.latencySamples > 0 ? 1000.0f / f2 : 0.0f);
            this.annotationString = "FPS (potential): " + String.format(locale2, "%.2f", objArr2) + " FPS (actual): " + format + " Latency (ms) Avg: " + this.lastLatency + " \nMin: " + this.shortestRenderMs + " Max: " + this.longestRenderMs;
            this.lastAnnotation = currentTimeMillis;
            resetCounters();
        }
        RectF rectF = plot.getDisplayDimensions().canvasRect;
        if (this.annotatePlotEnabled) {
            canvas.drawText(this.annotationString, rectF.centerX(), rectF.centerY(), this.paint);
        }
    }

    private void resetCounters() {
        this.longestRenderMs = 0L;
        this.shortestRenderMs = 999999999L;
        this.latencySamples = 0L;
        this.latencySum = 0L;
    }

    @Override // com.androidplot.PlotListener
    public void onAfterDraw(Plot plot, Canvas canvas) {
        this.lastLatency = System.currentTimeMillis() - this.lastStart;
        long j = this.lastLatency;
        if (j < this.shortestRenderMs) {
            this.shortestRenderMs = j;
        }
        long j2 = this.lastLatency;
        if (j2 > this.longestRenderMs) {
            this.longestRenderMs = j2;
        }
        this.latencySum += this.lastLatency;
        this.latencySamples++;
        annotatePlot(plot, canvas);
    }

    @Override // com.androidplot.PlotListener
    public void onBeforeDraw(Plot plot, Canvas canvas) {
        this.lastStart = System.currentTimeMillis();
    }

    public void setAnnotatePlotEnabled(boolean z) {
        this.annotatePlotEnabled = z;
    }
}
