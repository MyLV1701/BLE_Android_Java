package com.androidplot.xy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.SeriesAndFormatterList;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TableModel;
import com.androidplot.ui.widget.Widget;
import com.androidplot.util.FontUtils;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class XYLegendWidget extends Widget {
    private static final RegionEntryComparator regionEntryComparator = new RegionEntryComparator();
    private boolean drawIconBackgroundEnabled;
    private boolean drawIconBorderEnabled;
    private Paint iconBorderPaint;
    private SizeMetrics iconSizeMetrics;
    private XYPlot plot;
    private TableModel tableModel;
    private Paint textPaint;

    /* loaded from: classes.dex */
    private enum CellType {
        SERIES,
        REGION
    }

    /* loaded from: classes.dex */
    private static class RegionEntryComparator implements Comparator<Map.Entry<XYRegionFormatter, String>> {
        private RegionEntryComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Map.Entry<XYRegionFormatter, String> entry, Map.Entry<XYRegionFormatter, String> entry2) {
            return entry.getValue().compareTo(entry2.getValue());
        }
    }

    public XYLegendWidget(LayoutManager layoutManager, XYPlot xYPlot, SizeMetrics sizeMetrics, TableModel tableModel, SizeMetrics sizeMetrics2) {
        super(layoutManager, sizeMetrics);
        this.drawIconBackgroundEnabled = true;
        this.drawIconBorderEnabled = true;
        this.textPaint = new Paint();
        this.textPaint.setColor(-3355444);
        this.textPaint.setAntiAlias(true);
        this.iconBorderPaint = new Paint();
        this.iconBorderPaint.setStyle(Paint.Style.STROKE);
        this.plot = xYPlot;
        setTableModel(tableModel);
        this.iconSizeMetrics = sizeMetrics2;
    }

    private void beginDrawingCell(Canvas canvas, RectF rectF) {
        Paint gridBackgroundPaint = this.plot.getGraphWidget().getGridBackgroundPaint();
        if (!this.drawIconBackgroundEnabled || gridBackgroundPaint == null) {
            return;
        }
        canvas.drawRect(rectF, gridBackgroundPaint);
    }

    private void drawRegionLegendCell(Canvas canvas, XYRegionFormatter xYRegionFormatter, RectF rectF, String str) {
        RectF iconRect = getIconRect(rectF);
        beginDrawingCell(canvas, iconRect);
        drawRegionLegendIcon(canvas, iconRect, xYRegionFormatter);
        finishDrawingCell(canvas, rectF, iconRect, str);
    }

    private void drawSeriesLegendCell(Canvas canvas, XYSeriesRenderer xYSeriesRenderer, XYSeriesFormatter xYSeriesFormatter, RectF rectF, String str) {
        RectF iconRect = getIconRect(rectF);
        beginDrawingCell(canvas, iconRect);
        xYSeriesRenderer.drawSeriesLegendIcon(canvas, iconRect, xYSeriesFormatter);
        finishDrawingCell(canvas, rectF, iconRect, str);
    }

    private void finishDrawingCell(Canvas canvas, RectF rectF, RectF rectF2, String str) {
        Paint gridBackgroundPaint = this.plot.getGraphWidget().getGridBackgroundPaint();
        if (this.drawIconBorderEnabled && gridBackgroundPaint != null) {
            this.iconBorderPaint.setColor(gridBackgroundPaint.getColor());
            canvas.drawRect(rectF2, this.iconBorderPaint);
        }
        canvas.drawText(str, rectF2.right + 2.0f, getRectCenterY(rectF) + (FontUtils.getFontHeight(this.textPaint) / 2.0f), this.textPaint);
    }

    private RectF getIconRect(RectF rectF) {
        float height = rectF.top + (rectF.height() / 2.0f);
        RectF rectF2 = this.iconSizeMetrics.getRectF(rectF);
        rectF2.offsetTo(rectF.left + 1.0f, height - (rectF2.height() / 2.0f));
        return rectF2;
    }

    private static float getRectCenterY(RectF rectF) {
        return rectF.top + (rectF.height() / 2.0f);
    }

    @Override // com.androidplot.ui.widget.Widget
    protected synchronized void doOnDraw(Canvas canvas, RectF rectF) {
        if (this.plot.isEmpty()) {
            return;
        }
        TreeSet treeSet = new TreeSet(new RegionEntryComparator());
        int i = 0;
        for (XYSeriesRenderer xYSeriesRenderer : this.plot.getRendererList()) {
            SeriesAndFormatterList<XYSeries, XYSeriesFormatter> seriesAndFormatterListForRenderer = this.plot.getSeriesAndFormatterListForRenderer(xYSeriesRenderer.getClass());
            if (seriesAndFormatterListForRenderer != null) {
                i += seriesAndFormatterListForRenderer.size();
            }
            treeSet.addAll(xYSeriesRenderer.getUniqueRegionFormatters().entrySet());
        }
        Iterator<RectF> iterator = this.tableModel.getIterator(rectF, i + treeSet.size());
        for (XYSeriesRenderer xYSeriesRenderer2 : this.plot.getRendererList()) {
            SeriesAndFormatterList<XYSeries, XYSeriesFormatter> seriesAndFormatterListForRenderer2 = this.plot.getSeriesAndFormatterListForRenderer(xYSeriesRenderer2.getClass());
            if (seriesAndFormatterListForRenderer2 != null) {
                for (int i2 = 0; i2 < seriesAndFormatterListForRenderer2.size() && iterator.hasNext(); i2++) {
                    drawSeriesLegendCell(canvas, xYSeriesRenderer2, seriesAndFormatterListForRenderer2.getFormatter(i2), iterator.next(), seriesAndFormatterListForRenderer2.getSeries(i2).getTitle());
                }
            }
        }
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!iterator.hasNext()) {
                break;
            }
            drawRegionLegendCell(canvas, (XYRegionFormatter) entry.getKey(), iterator.next(), (String) entry.getValue());
        }
    }

    protected void drawRegionLegendIcon(Canvas canvas, RectF rectF, XYRegionFormatter xYRegionFormatter) {
        canvas.drawRect(rectF, xYRegionFormatter.getPaint());
    }

    public SizeMetrics getIconSizeMetrics() {
        return this.iconSizeMetrics;
    }

    public TableModel getTableModel() {
        return this.tableModel;
    }

    public Paint getTextPaint() {
        return this.textPaint;
    }

    public boolean isDrawIconBackgroundEnabled() {
        return this.drawIconBackgroundEnabled;
    }

    public boolean isDrawIconBorderEnabled() {
        return this.drawIconBorderEnabled;
    }

    public void setDrawIconBackgroundEnabled(boolean z) {
        this.drawIconBackgroundEnabled = z;
    }

    public void setDrawIconBorderEnabled(boolean z) {
        this.drawIconBorderEnabled = z;
    }

    public void setIconSizeMetrics(SizeMetrics sizeMetrics) {
        this.iconSizeMetrics = sizeMetrics;
    }

    public synchronized void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void setTextPaint(Paint paint) {
        this.textPaint = paint;
    }
}
