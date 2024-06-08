package com.androidplot.xy;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Pair;
import com.androidplot.util.ValPixConverter;
import com.androidplot.xy.LineAndPointFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class LineAndPointRenderer<FormatterType extends LineAndPointFormatter> extends XYSeriesRenderer<FormatterType> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.xy.LineAndPointRenderer$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$xy$FillDirection = new int[FillDirection.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$xy$FillDirection[FillDirection.BOTTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$xy$FillDirection[FillDirection.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$xy$FillDirection[FillDirection.RANGE_ORIGIN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public LineAndPointRenderer(XYPlot xYPlot) {
        super(xYPlot);
    }

    protected void appendToPath(Path path, PointF pointF, PointF pointF2) {
        path.lineTo(pointF.x, pointF.y);
    }

    protected void drawSeries(Canvas canvas, RectF rectF, XYSeries xYSeries, LineAndPointFormatter lineAndPointFormatter) {
        PointF pointF;
        Path path;
        Paint linePaint = lineAndPointFormatter.getLinePaint();
        ArrayList arrayList = new ArrayList(xYSeries.size());
        XYPlot plot = getPlot();
        int intValue = getPlot().getCalculatedMinX().intValue();
        int intValue2 = getPlot().getCalculatedMaxX().intValue();
        float floatValue = plot.getCalculatedMinY().floatValue();
        float floatValue2 = plot.getCalculatedMaxY().floatValue();
        int i = 0;
        PointF pointF2 = null;
        PointF pointF3 = null;
        Path path2 = null;
        for (Float f2 : xYSeries.getY()) {
            int i2 = i + 1;
            int intValue3 = xYSeries.getX(i).intValue();
            if (f2 != null) {
                pointF = ValPixConverter.valToPix(intValue3, f2.floatValue(), rectF, intValue, intValue2, floatValue, floatValue2);
                arrayList.add(new Pair(new PointF(pointF.x, pointF.y), Integer.valueOf(i2)));
            } else {
                pointF = null;
            }
            if (linePaint == null || pointF == null) {
                if (pointF2 != null) {
                    renderPath(canvas, rectF, path2, pointF3, pointF2, lineAndPointFormatter);
                }
                pointF2 = null;
                pointF3 = null;
            } else {
                if (pointF3 == null) {
                    path = new Path();
                    PointF pointF4 = new PointF(pointF.x, pointF.y);
                    path.moveTo(pointF4.x, pointF4.y);
                    pointF3 = pointF4;
                } else {
                    path = path2;
                }
                if (pointF2 != null) {
                    appendToPath(path, pointF, pointF2);
                }
                pointF2 = pointF;
                path2 = path;
            }
            i = i2;
        }
        PointF pointF5 = pointF2;
        if (linePaint != null && pointF3 != null) {
            renderPath(canvas, rectF, path2, pointF3, pointF5, lineAndPointFormatter);
        }
        Paint vertexPaint = lineAndPointFormatter.getVertexPaint();
        lineAndPointFormatter.getPointLabelFormatter();
        if (vertexPaint != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object obj = ((Pair) it.next()).first;
                canvas.drawPoint(((PointF) obj).x, ((PointF) obj).y, lineAndPointFormatter.getVertexPaint());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.androidplot.ui.SeriesRenderer
    public void onRender(Canvas canvas, RectF rectF) {
        List<XYSeries> seriesListForRenderer = getPlot().getSeriesListForRenderer(getClass());
        if (seriesListForRenderer != null) {
            for (XYSeries xYSeries : seriesListForRenderer) {
                drawSeries(canvas, rectF, xYSeries, (LineAndPointFormatter) getFormatter(xYSeries));
            }
        }
    }

    protected void renderPath(Canvas canvas, RectF rectF, Path path, PointF pointF, PointF pointF2, LineAndPointFormatter lineAndPointFormatter) {
        Path path2 = new Path(path);
        int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$FillDirection[lineAndPointFormatter.getFillDirection().ordinal()];
        if (i == 1) {
            path.lineTo(pointF2.x, rectF.bottom);
            path.lineTo(pointF.x, rectF.bottom);
            path.close();
        } else if (i == 2) {
            path.lineTo(pointF2.x, rectF.top);
            path.lineTo(pointF.x, rectF.top);
            path.close();
        } else if (i == 3) {
            float valToPix = ValPixConverter.valToPix(getPlot().getRangeOrigin().intValue(), getPlot().getCalculatedMinY().floatValue(), getPlot().getCalculatedMaxY().floatValue(), rectF.height(), true) + rectF.top;
            path.lineTo(pointF2.x, valToPix);
            path.lineTo(pointF.x, valToPix);
            path.close();
        } else {
            throw new UnsupportedOperationException("Fill direction not yet implemented: " + lineAndPointFormatter.getFillDirection());
        }
        if (lineAndPointFormatter.getFillPaint() != null) {
            canvas.drawPath(path, lineAndPointFormatter.getFillPaint());
        }
        int intValue = getPlot().getCalculatedMinX().intValue();
        int intValue2 = getPlot().getCalculatedMaxX().intValue();
        float floatValue = getPlot().getCalculatedMinY().floatValue();
        float floatValue2 = getPlot().getCalculatedMaxY().floatValue();
        for (RectRegion rectRegion : RectRegion.regionsWithin(lineAndPointFormatter.getRegions().elements(), intValue, intValue2, floatValue, floatValue2)) {
            XYRegionFormatter regionFormatter = lineAndPointFormatter.getRegionFormatter(rectRegion);
            RectF rectF2 = rectRegion.getRectF(rectF, intValue, intValue2, floatValue, floatValue2);
            if (rectF2 != null) {
                try {
                    canvas.save();
                    canvas.clipPath(path);
                    canvas.drawRect(rectF2, regionFormatter.getPaint());
                } finally {
                    canvas.restore();
                }
            }
        }
        if (lineAndPointFormatter.getLinePaint() != null) {
            canvas.drawPath(path2, lineAndPointFormatter.getLinePaint());
        }
        path.rewind();
    }

    @Override // com.androidplot.ui.SeriesRenderer
    public void doDrawLegendIcon(Canvas canvas, RectF rectF, LineAndPointFormatter lineAndPointFormatter) {
        float centerY = rectF.centerY();
        float centerX = rectF.centerX();
        if (lineAndPointFormatter.getFillPaint() != null) {
            canvas.drawRect(rectF, lineAndPointFormatter.getFillPaint());
        }
        if (lineAndPointFormatter.getLinePaint() != null) {
            canvas.drawLine(rectF.left, rectF.bottom, rectF.right, rectF.top, lineAndPointFormatter.getLinePaint());
        }
        if (lineAndPointFormatter.getVertexPaint() != null) {
            canvas.drawPoint(centerX, centerY, lineAndPointFormatter.getVertexPaint());
        }
    }
}
