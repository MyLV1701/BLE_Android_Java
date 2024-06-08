package com.androidplot.xy;

import android.graphics.Paint;
import com.androidplot.ui.SeriesRenderer;
import com.androidplot.util.PixelUtils;

/* loaded from: classes.dex */
public class LineAndPointFormatter extends XYSeriesFormatter<XYRegionFormatter> {
    private static final float DEFAULT_LINE_STROKE_WIDTH_DP = 1.5f;
    private static final float DEFAULT_VERTEX_STROKE_WIDTH_DP = 4.5f;
    protected FillDirection fillDirection;
    protected Paint fillPaint;
    protected Paint linePaint;
    private PointLabelFormatter pointLabelFormatter;
    private PointLabeler pointLabeler;
    protected Paint vertexPaint;

    public LineAndPointFormatter() {
        this(-65536, -16711936, -16776961, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String a(XYSeries xYSeries, int i) {
        return xYSeries.getY(i) + "";
    }

    public void disableShadows() {
        this.linePaint.setShadowLayer(0.0f, 0.0f, 0.0f, -16777216);
        this.vertexPaint.setShadowLayer(0.0f, 0.0f, 0.0f, -16777216);
    }

    public void enableShadows() {
        this.linePaint.setShadowLayer(1.0f, 3.0f, 3.0f, -16777216);
        this.vertexPaint.setShadowLayer(1.0f, 3.0f, 3.0f, -16777216);
    }

    public FillDirection getFillDirection() {
        return this.fillDirection;
    }

    public Paint getFillPaint() {
        return this.fillPaint;
    }

    public Paint getLinePaint() {
        return this.linePaint;
    }

    public PointLabelFormatter getPointLabelFormatter() {
        return this.pointLabelFormatter;
    }

    public PointLabeler getPointLabeler() {
        return this.pointLabeler;
    }

    @Override // com.androidplot.ui.Formatter
    public Class<? extends SeriesRenderer> getRendererClass() {
        return LineAndPointRenderer.class;
    }

    public Paint getVertexPaint() {
        return this.vertexPaint;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initFillPaint(Integer num) {
        if (num == null) {
            this.fillPaint = null;
            return;
        }
        this.fillPaint = new Paint();
        this.fillPaint.setAntiAlias(true);
        this.fillPaint.setColor(num.intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initLinePaint(Integer num) {
        if (num == null) {
            this.linePaint = null;
            return;
        }
        this.linePaint = new Paint();
        this.linePaint.setAntiAlias(true);
        this.linePaint.setStrokeWidth(PixelUtils.dpToPix(DEFAULT_LINE_STROKE_WIDTH_DP));
        this.linePaint.setColor(num.intValue());
        this.linePaint.setStyle(Paint.Style.STROKE);
    }

    protected void initVertexPaint(Integer num) {
        if (num == null) {
            this.vertexPaint = null;
            return;
        }
        this.vertexPaint = new Paint();
        this.vertexPaint.setAntiAlias(true);
        this.vertexPaint.setStrokeWidth(PixelUtils.dpToPix(DEFAULT_VERTEX_STROKE_WIDTH_DP));
        this.vertexPaint.setColor(num.intValue());
        this.vertexPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void setFillDirection(FillDirection fillDirection) {
        this.fillDirection = fillDirection;
    }

    public void setFillPaint(Paint paint) {
        this.fillPaint = paint;
    }

    public void setLinePaint(Paint paint) {
        this.linePaint = paint;
    }

    public void setPointLabelFormatter(PointLabelFormatter pointLabelFormatter) {
        this.pointLabelFormatter = pointLabelFormatter;
    }

    public void setPointLabeler(PointLabeler pointLabeler) {
        this.pointLabeler = pointLabeler;
    }

    public void setVertexPaint(Paint paint) {
        this.vertexPaint = paint;
    }

    public LineAndPointFormatter(Integer num, Integer num2, Integer num3, PointLabelFormatter pointLabelFormatter) {
        this(num, num2, num3, pointLabelFormatter, FillDirection.BOTTOM);
    }

    @Override // com.androidplot.xy.XYSeriesFormatter, com.androidplot.ui.Formatter
    public SeriesRenderer getRendererInstance(XYPlot xYPlot) {
        return new LineAndPointRenderer(xYPlot);
    }

    public LineAndPointFormatter(Integer num, Integer num2, Integer num3, PointLabelFormatter pointLabelFormatter, FillDirection fillDirection) {
        this.pointLabeler = new PointLabeler() { // from class: com.androidplot.xy.a
            @Override // com.androidplot.xy.PointLabeler
            public final String getLabel(XYSeries xYSeries, int i) {
                return LineAndPointFormatter.a(xYSeries, i);
            }
        };
        this.fillDirection = FillDirection.BOTTOM;
        initLinePaint(-16777216);
        initLinePaint(num);
        initVertexPaint(num2);
        initFillPaint(num3);
        setFillDirection(fillDirection);
        setPointLabelFormatter(pointLabelFormatter);
    }
}
