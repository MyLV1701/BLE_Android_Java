package com.androidplot.xy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.widget.Widget;
import com.androidplot.util.FontUtils;
import com.androidplot.util.ValPixConverter;
import com.androidplot.util.ZHash;
import com.androidplot.util.ZIndexable;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Iterator;
import no.nordicsemi.android.ble.error.GattError;

/* loaded from: classes.dex */
public class XYGraphWidget extends Widget {
    private static final int CURSOR_LABEL_SPACING = 2;
    private static final int MARKER_LABEL_SPACING = 2;
    private static final String TAG = "AndroidPlot";
    private ZHash<RectRegion, AxisValueLabelFormatter> axisValueLabelRegions;
    private Paint cursorLabelBackgroundPaint;
    private Paint cursorLabelPaint;
    private boolean domainAxisBottom;
    private Paint domainCursorPaint;
    private float domainCursorPosition;
    private Paint domainGridLinePaint;
    private float domainLabelHorizontalOffset;
    private float domainLabelOrientation;
    private Paint domainLabelPaint;
    private int domainLabelTickExtension;
    private float domainLabelVerticalOffset;
    private float domainLabelWidth;
    private Paint domainOriginLabelPaint;
    private Paint domainOriginLinePaint;
    private float domainScale;
    private Paint domainSubGridLinePaint;
    private Format domainValueFormat;
    private boolean drawCursorLabelEnabled;
    private boolean drawMarkersEnabled;
    private Paint gridBackgroundPaint;
    private float gridPaddingBottom;
    private float gridPaddingLeft;
    private float gridPaddingRight;
    private float gridPaddingTop;
    private RectF gridRect;
    private RectF paddedGridRect;
    private XYPlot plot;
    private boolean rangeAxisLeft;
    private Paint rangeCursorPaint;
    private float rangeCursorPosition;
    private Paint rangeGridLinePaint;
    private float rangeLabelHorizontalOffset;
    private float rangeLabelOrientation;
    private Paint rangeLabelPaint;
    private int rangeLabelTickExtension;
    private float rangeLabelVerticalOffset;
    private float rangeLabelWidth;
    private Paint rangeOriginLabelPaint;
    private Paint rangeOriginLinePaint;
    private Paint rangeSubGridLinePaint;
    private Format rangeValueFormat;
    private int ticksPerDomainLabel;
    private int ticksPerRangeLabel;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.xy.XYGraphWidget$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$xy$XYAxisType = new int[XYAxisType.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$xy$XYAxisType[XYAxisType.DOMAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$xy$XYAxisType[XYAxisType.RANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum XYPlotOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public XYGraphWidget(LayoutManager layoutManager, XYPlot xYPlot, SizeMetrics sizeMetrics) {
        super(layoutManager, sizeMetrics);
        this.domainLabelWidth = 15.0f;
        this.rangeLabelWidth = 41.0f;
        this.domainLabelVerticalOffset = -5.0f;
        this.domainLabelHorizontalOffset = 0.0f;
        this.rangeLabelHorizontalOffset = 1.0f;
        this.rangeLabelVerticalOffset = 0.0f;
        this.ticksPerRangeLabel = 1;
        this.ticksPerDomainLabel = 1;
        this.gridPaddingTop = 0.0f;
        this.gridPaddingBottom = 0.0f;
        this.gridPaddingLeft = 0.0f;
        this.gridPaddingRight = 0.0f;
        this.domainLabelTickExtension = 5;
        this.rangeLabelTickExtension = 5;
        this.domainScale = 1.0f;
        this.drawCursorLabelEnabled = true;
        this.drawMarkersEnabled = true;
        this.rangeAxisLeft = true;
        this.domainAxisBottom = true;
        this.gridBackgroundPaint = new Paint();
        this.gridBackgroundPaint.setColor(Color.rgb(GattError.GATT_SERVICE_STARTED, GattError.GATT_SERVICE_STARTED, GattError.GATT_SERVICE_STARTED));
        this.gridBackgroundPaint.setStyle(Paint.Style.FILL);
        this.rangeGridLinePaint = new Paint();
        this.rangeGridLinePaint.setColor(Color.rgb(180, 180, 180));
        this.rangeGridLinePaint.setAntiAlias(true);
        this.rangeGridLinePaint.setStyle(Paint.Style.STROKE);
        this.domainGridLinePaint = new Paint(this.rangeGridLinePaint);
        this.domainSubGridLinePaint = new Paint(this.domainGridLinePaint);
        this.rangeSubGridLinePaint = new Paint(this.rangeGridLinePaint);
        this.domainOriginLinePaint = new Paint();
        this.domainOriginLinePaint.setColor(-1);
        this.domainOriginLinePaint.setAntiAlias(true);
        this.rangeOriginLinePaint = new Paint();
        this.rangeOriginLinePaint.setColor(-1);
        this.rangeOriginLinePaint.setAntiAlias(true);
        this.domainOriginLabelPaint = new Paint();
        this.domainOriginLabelPaint.setColor(-1);
        this.domainOriginLabelPaint.setAntiAlias(true);
        this.domainOriginLabelPaint.setTextAlign(Paint.Align.CENTER);
        this.rangeOriginLabelPaint = new Paint();
        this.rangeOriginLabelPaint.setColor(-1);
        this.rangeOriginLabelPaint.setAntiAlias(true);
        this.rangeOriginLabelPaint.setTextAlign(Paint.Align.RIGHT);
        this.domainLabelPaint = new Paint();
        this.domainLabelPaint.setColor(-3355444);
        this.domainLabelPaint.setAntiAlias(true);
        this.domainLabelPaint.setTextAlign(Paint.Align.CENTER);
        this.rangeLabelPaint = new Paint();
        this.rangeLabelPaint.setColor(-3355444);
        this.rangeLabelPaint.setAntiAlias(true);
        this.rangeLabelPaint.setTextAlign(Paint.Align.RIGHT);
        this.domainCursorPaint = new Paint();
        this.domainCursorPaint.setColor(-256);
        this.rangeCursorPaint = new Paint();
        this.rangeCursorPaint.setColor(-256);
        this.cursorLabelPaint = new Paint();
        this.cursorLabelPaint.setColor(-256);
        this.cursorLabelBackgroundPaint = new Paint();
        this.cursorLabelBackgroundPaint.setColor(Color.argb(100, 50, 50, 50));
        setMarginTop(7.0f);
        setMarginRight(4.0f);
        setMarginBottom(4.0f);
        this.rangeValueFormat = new DecimalFormat("0.0");
        this.domainValueFormat = new DecimalFormat("0.0");
        this.axisValueLabelRegions = new ZHash<>();
        this.plot = xYPlot;
    }

    private void drawDomainTick(Canvas canvas, float f2, Number number, Paint paint, Paint paint2, boolean z) {
        float f3;
        if (z) {
            if (paint2 != null) {
                RectF rectF = this.gridRect;
                canvas.drawLine(f2, rectF.top, f2, rectF.bottom, paint2);
                return;
            }
            return;
        }
        if (paint2 != null) {
            if (this.domainAxisBottom) {
                RectF rectF2 = this.gridRect;
                canvas.drawLine(f2, rectF2.top, f2, rectF2.bottom + this.domainLabelTickExtension, paint2);
            } else {
                RectF rectF3 = this.gridRect;
                canvas.drawLine(f2, rectF3.top - this.domainLabelTickExtension, f2, rectF3.bottom, paint2);
            }
        }
        if (paint != null) {
            float fontHeight = FontUtils.getFontHeight(paint);
            if (this.domainAxisBottom) {
                f3 = this.gridRect.bottom + this.domainLabelTickExtension + this.domainLabelVerticalOffset + fontHeight;
            } else {
                f3 = (this.gridRect.top - this.domainLabelTickExtension) - this.domainLabelVerticalOffset;
            }
            drawTickText(canvas, XYAxisType.DOMAIN, number, f2 + this.domainLabelHorizontalOffset, f3, paint);
        }
    }

    private void drawMarkerText(Canvas canvas, String str, ValueMarker valueMarker, float f2, float f3) {
        RectF rectF = new RectF(FontUtils.getStringDimensions(str, valueMarker.getTextPaint()));
        rectF.offsetTo(f2 + 2.0f, (f3 - 2.0f) - rectF.height());
        float f4 = rectF.right;
        float f5 = this.paddedGridRect.right;
        if (f4 > f5) {
            rectF.offset(-(f4 - f5), 0.0f);
        }
        float f6 = rectF.top;
        float f7 = this.paddedGridRect.top;
        if (f6 < f7) {
            rectF.offset(0.0f, f7 - f6);
        }
        canvas.drawText(str, rectF.left, rectF.bottom, valueMarker.getTextPaint());
    }

    private void drawTickText(Canvas canvas, XYAxisType xYAxisType, Number number, float f2, float f3, Paint paint) {
        String formattedDomainValue;
        Paint paint2;
        double doubleValue = number.doubleValue();
        int save = canvas.save();
        try {
            int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$XYAxisType[xYAxisType.ordinal()];
            AxisValueLabelFormatter axisValueLabelFormatter = null;
            if (i == 1) {
                axisValueLabelFormatter = getAxisValueLabelFormatterForDomainVal(doubleValue);
                formattedDomainValue = getFormattedDomainValue(number);
                canvas.rotate(getDomainLabelOrientation(), f2, f3);
            } else if (i != 2) {
                formattedDomainValue = null;
            } else {
                axisValueLabelFormatter = getAxisValueLabelFormatterForRangeVal(doubleValue);
                formattedDomainValue = getFormattedRangeValue(number);
                canvas.rotate(getRangeLabelOrientation(), f2, f3);
            }
            if (axisValueLabelFormatter != null) {
                paint2 = new Paint(paint);
                paint2.setColor(axisValueLabelFormatter.getColor());
            } else {
                paint2 = paint;
            }
            canvas.drawText(formattedDomainValue, f2, f3, paint2);
        } finally {
            canvas.restoreToCount(save);
        }
    }

    private String getFormattedDomainValue(Number number) {
        return this.domainValueFormat.format(number);
    }

    private String getFormattedRangeValue(Number number) {
        return this.rangeValueFormat.format(number);
    }

    private RectF getPaddedGridRect(RectF rectF) {
        return new RectF(rectF.left + this.gridPaddingLeft, rectF.top + this.gridPaddingTop, rectF.right - this.gridPaddingRight, rectF.bottom - this.gridPaddingBottom);
    }

    public void addAxisValueLabelRegion(RectRegion rectRegion, AxisValueLabelFormatter axisValueLabelFormatter) {
        this.axisValueLabelRegions.addToTop(rectRegion, axisValueLabelFormatter);
    }

    public void addDomainAxisValueLabelRegion(double d2, double d3, AxisValueLabelFormatter axisValueLabelFormatter) {
        addAxisValueLabelRegion(new RectRegion(Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(Double.POSITIVE_INFINITY), Double.valueOf(Double.NEGATIVE_INFINITY), null), axisValueLabelFormatter);
    }

    public void addRangeAxisValueLabelRegion(double d2, double d3, AxisValueLabelFormatter axisValueLabelFormatter) {
        addAxisValueLabelRegion(new RectRegion(Double.valueOf(Double.POSITIVE_INFINITY), Double.valueOf(Double.NEGATIVE_INFINITY), Double.valueOf(d2), Double.valueOf(d3), null), axisValueLabelFormatter);
    }

    @Override // com.androidplot.ui.widget.Widget
    protected void doOnDraw(Canvas canvas, RectF rectF) {
        this.gridRect = getGridRect(rectF);
        this.paddedGridRect = getPaddedGridRect(this.gridRect);
        if (this.paddedGridRect.height() <= 0.0f || this.paddedGridRect.width() <= 0.0f || this.plot.getCalculatedMinX() == null || this.plot.getCalculatedMaxX() == null || this.plot.getCalculatedMinY() == null || this.plot.getCalculatedMaxY() == null) {
            return;
        }
        drawGrid(canvas);
        drawData(canvas);
        drawCursors(canvas);
        if (isDrawMarkersEnabled()) {
            drawMarkers(canvas);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:36:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void drawCursors(android.graphics.Canvas r15) {
        /*
            r14 = this;
            android.graphics.Paint r5 = r14.domainCursorPaint
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L21
            float r3 = r14.domainCursorPosition
            android.graphics.RectF r0 = r14.paddedGridRect
            float r1 = r0.right
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 > 0) goto L21
            float r1 = r0.left
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 < 0) goto L21
            float r2 = r0.top
            float r4 = r0.bottom
            r0 = r15
            r1 = r3
            r0.drawLine(r1, r2, r3, r4, r5)
            r0 = 1
            goto L22
        L21:
            r0 = 0
        L22:
            android.graphics.Paint r13 = r14.rangeCursorPaint
            if (r13 == 0) goto L40
            float r12 = r14.rangeCursorPosition
            android.graphics.RectF r1 = r14.paddedGridRect
            float r2 = r1.top
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 < 0) goto L40
            float r2 = r1.bottom
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 > 0) goto L40
            float r9 = r1.left
            float r11 = r1.right
            r8 = r15
            r10 = r12
            r8.drawLine(r9, r10, r11, r12, r13)
            goto L41
        L40:
            r6 = 0
        L41:
            boolean r1 = r14.drawCursorLabelEnabled
            if (r1 == 0) goto Ld8
            android.graphics.Paint r1 = r14.cursorLabelPaint
            if (r1 == 0) goto Ld8
            if (r6 == 0) goto Ld8
            if (r0 == 0) goto Ld8
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "X="
            r0.append(r1)
            java.text.Format r1 = r14.getDomainValueFormat()
            java.lang.Double r2 = r14.getDomainCursorVal()
            java.lang.String r1 = r1.format(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " Y="
            r1.append(r0)
            java.text.Format r0 = r14.getRangeValueFormat()
            java.lang.Double r2 = r14.getRangeCursorVal()
            java.lang.String r0 = r0.format(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.graphics.RectF r1 = new android.graphics.RectF
            android.graphics.Paint r2 = r14.cursorLabelPaint
            android.graphics.Rect r2 = com.androidplot.util.FontUtils.getPackedStringDimensions(r0, r2)
            r1.<init>(r2)
            float r2 = r14.domainCursorPosition
            float r3 = r14.rangeCursorPosition
            float r4 = r1.height()
            float r3 = r3 - r4
            r1.offsetTo(r2, r3)
            float r2 = r1.right
            android.graphics.RectF r3 = r14.paddedGridRect
            float r3 = r3.right
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 < 0) goto Lb7
            float r2 = r14.domainCursorPosition
            float r3 = r1.width()
            float r2 = r2 - r3
            float r3 = r1.top
            r1.offsetTo(r2, r3)
        Lb7:
            float r2 = r1.top
            android.graphics.RectF r3 = r14.paddedGridRect
            float r3 = r3.top
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto Lc8
            float r2 = r1.left
            float r3 = r14.rangeCursorPosition
            r1.offsetTo(r2, r3)
        Lc8:
            android.graphics.Paint r2 = r14.cursorLabelBackgroundPaint
            if (r2 == 0) goto Lcf
            r15.drawRect(r1, r2)
        Lcf:
            float r2 = r1.left
            float r1 = r1.bottom
            android.graphics.Paint r3 = r14.cursorLabelPaint
            r15.drawText(r0, r2, r1, r3)
        Ld8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androidplot.xy.XYGraphWidget.drawCursors(android.graphics.Canvas):void");
    }

    protected void drawData(Canvas canvas) {
        try {
            canvas.save();
            canvas.clipRect(this.gridRect, Region.Op.INTERSECT);
            Iterator<XYSeriesRenderer> it = this.plot.getRendererList().iterator();
            while (it.hasNext()) {
                it.next().render(canvas, this.paddedGridRect);
            }
        } finally {
            canvas.restore();
        }
    }

    protected void drawGrid(Canvas canvas) {
        float f2;
        RectF rectF;
        float f3;
        float f4;
        Paint paint = this.gridBackgroundPaint;
        if (paint != null) {
            canvas.drawRect(this.gridRect, paint);
        }
        if (this.plot.getDomainOrigin() != null) {
            f2 = ValPixConverter.valToPix(this.plot.getDomainOrigin().floatValue(), this.plot.getCalculatedMinX().floatValue(), this.plot.getCalculatedMaxX().floatValue(), this.paddedGridRect.width(), false) + this.paddedGridRect.left;
        } else {
            f2 = this.paddedGridRect.left;
        }
        float f5 = f2;
        XYPlot xYPlot = this.plot;
        XYStep step = XYStepCalculator.getStep(xYPlot, XYAxisType.DOMAIN, this.paddedGridRect, Double.valueOf(xYPlot.getCalculatedMinX().doubleValue()), Double.valueOf(this.plot.getCalculatedMaxX().doubleValue()));
        float floatValue = this.plot.getDomainOrigin().floatValue();
        float stepVal = (float) step.getStepVal();
        float f6 = (-floatValue) % stepVal;
        int i = (int) f6;
        float stepPix = (int) ((step.getStepPix() / stepVal) * f6);
        float f7 = f5 + stepPix;
        int i2 = 0;
        while (true) {
            rectF = this.paddedGridRect;
            f3 = rectF.right;
            if (f7 > f3) {
                break;
            }
            int i3 = (int) (((i2 * stepVal) + floatValue + i) * this.domainScale);
            if (f7 >= rectF.left && f7 <= f3) {
                if (i2 % getTicksPerDomainLabel() == 0) {
                    drawDomainTick(canvas, f7, Integer.valueOf(i3), this.domainLabelPaint, this.domainGridLinePaint, false);
                } else {
                    drawDomainTick(canvas, f7, Integer.valueOf(i3), this.domainLabelPaint, this.domainSubGridLinePaint, true);
                }
            }
            i2++;
            f7 = (i2 * step.getStepPix()) + f5 + stepPix;
        }
        if (f5 >= rectF.left && f5 <= f3) {
            Paint paint2 = this.domainOriginLinePaint;
            if (paint2 != null) {
                paint2.setTextAlign(Paint.Align.CENTER);
            }
            drawDomainTick(canvas, f5, null, null, this.domainOriginLinePaint, true);
            drawDomainTick(canvas, this.gridRect.right, null, null, this.domainGridLinePaint, true);
        }
        if (this.plot.getRangeOrigin() != null) {
            f4 = ValPixConverter.valToPix(this.plot.getRangeOrigin().floatValue(), this.plot.getCalculatedMinY().floatValue(), this.plot.getCalculatedMaxY().floatValue(), this.paddedGridRect.height(), true) + this.paddedGridRect.top;
        } else {
            f4 = this.paddedGridRect.bottom;
        }
        float f8 = f4;
        XYPlot xYPlot2 = this.plot;
        XYStep step2 = XYStepCalculator.getStep(xYPlot2, XYAxisType.RANGE, this.paddedGridRect, Double.valueOf(xYPlot2.getCalculatedMinY().doubleValue()), Double.valueOf(this.plot.getCalculatedMaxY().doubleValue()));
        RectF rectF2 = this.paddedGridRect;
        if (f8 >= rectF2.top && f8 <= rectF2.bottom) {
            Paint paint3 = this.rangeOriginLinePaint;
            if (paint3 != null) {
                paint3.setTextAlign(Paint.Align.RIGHT);
            }
            drawRangeTick(canvas, f8, Double.valueOf(this.plot.getRangeOrigin().doubleValue()), this.rangeOriginLabelPaint, this.rangeOriginLinePaint, false);
        }
        int i4 = 1;
        float stepPix2 = f8 - step2.getStepPix();
        int i5 = 1;
        while (stepPix2 >= this.paddedGridRect.top) {
            double doubleValue = this.plot.getRangeOrigin().doubleValue();
            double d2 = i5;
            double stepVal2 = step2.getStepVal();
            Double.isNaN(d2);
            double d3 = doubleValue + (d2 * stepVal2);
            RectF rectF3 = this.paddedGridRect;
            if (stepPix2 >= rectF3.top && stepPix2 <= rectF3.bottom) {
                if (i5 % getTicksPerRangeLabel() == 0) {
                    drawRangeTick(canvas, stepPix2, Double.valueOf(d3), this.rangeLabelPaint, this.rangeGridLinePaint, false);
                } else {
                    drawRangeTick(canvas, stepPix2, Double.valueOf(d3), this.rangeLabelPaint, this.rangeSubGridLinePaint, true);
                }
            }
            i5++;
            stepPix2 = f8 - (i5 * step2.getStepPix());
        }
        float stepPix3 = step2.getStepPix() + f8;
        while (stepPix3 <= this.paddedGridRect.bottom) {
            double doubleValue2 = this.plot.getRangeOrigin().doubleValue();
            double d4 = i4;
            double stepVal3 = step2.getStepVal();
            Double.isNaN(d4);
            double d5 = doubleValue2 - (d4 * stepVal3);
            RectF rectF4 = this.paddedGridRect;
            if (stepPix3 >= rectF4.top && stepPix3 <= rectF4.bottom) {
                if (i4 % getTicksPerRangeLabel() == 0) {
                    drawRangeTick(canvas, stepPix3, Double.valueOf(d5), this.rangeLabelPaint, this.rangeGridLinePaint, false);
                } else {
                    drawRangeTick(canvas, stepPix3, Double.valueOf(d5), this.rangeLabelPaint, this.rangeSubGridLinePaint, true);
                }
            }
            i4++;
            stepPix3 = f8 + (i4 * step2.getStepPix());
        }
    }

    protected void drawMarkers(Canvas canvas) {
        for (YValueMarker yValueMarker : this.plot.getYValueMarkers()) {
            if (yValueMarker.getValue() != null) {
                float valToPix = ValPixConverter.valToPix(yValueMarker.getValue().floatValue(), this.plot.getCalculatedMinY().floatValue(), this.plot.getCalculatedMaxY().floatValue(), this.paddedGridRect.height(), true);
                RectF rectF = this.paddedGridRect;
                float f2 = valToPix + rectF.top;
                canvas.drawLine(rectF.left, f2, rectF.right, f2, yValueMarker.getLinePaint());
                float pixelValue = yValueMarker.getTextPosition().getPixelValue(this.paddedGridRect.width()) + this.paddedGridRect.left;
                if (yValueMarker.getText() != null) {
                    drawMarkerText(canvas, yValueMarker.getText(), yValueMarker, pixelValue, f2);
                } else {
                    drawMarkerText(canvas, getFormattedRangeValue(yValueMarker.getValue()), yValueMarker, pixelValue, f2);
                }
            }
        }
        for (XValueMarker xValueMarker : this.plot.getXValueMarkers()) {
            if (xValueMarker.getValue() != null) {
                float valToPix2 = ValPixConverter.valToPix(xValueMarker.getValue().intValue(), this.plot.getCalculatedMinX().intValue(), this.plot.getCalculatedMaxX().floatValue(), this.paddedGridRect.width(), false);
                RectF rectF2 = this.paddedGridRect;
                float f3 = valToPix2 + rectF2.left;
                canvas.drawLine(f3, rectF2.top, f3, rectF2.bottom, xValueMarker.getLinePaint());
                float pixelValue2 = xValueMarker.getTextPosition().getPixelValue(this.paddedGridRect.height()) + this.paddedGridRect.top;
                if (xValueMarker.getText() != null) {
                    drawMarkerText(canvas, xValueMarker.getText(), xValueMarker, f3, pixelValue2);
                } else {
                    drawMarkerText(canvas, getFormattedDomainValue(xValueMarker.getValue()), xValueMarker, f3, pixelValue2);
                }
            }
        }
    }

    protected void drawPoint(Canvas canvas, PointF pointF, Paint paint) {
        canvas.drawPoint(pointF.x, pointF.y, paint);
    }

    public void drawRangeTick(Canvas canvas, float f2, Number number, Paint paint, Paint paint2, boolean z) {
        float f3;
        if (z) {
            if (paint2 != null) {
                RectF rectF = this.gridRect;
                canvas.drawLine(rectF.left, f2, rectF.right, f2, paint2);
                return;
            }
            return;
        }
        if (paint2 != null) {
            if (this.rangeAxisLeft) {
                RectF rectF2 = this.gridRect;
                canvas.drawLine(rectF2.left - this.rangeLabelTickExtension, f2, rectF2.right, f2, paint2);
            } else {
                RectF rectF3 = this.gridRect;
                canvas.drawLine(rectF3.left, f2, rectF3.right + this.rangeLabelTickExtension, f2, paint2);
            }
        }
        if (paint != null) {
            if (this.rangeAxisLeft) {
                f3 = this.gridRect.left - (this.rangeLabelTickExtension + this.rangeLabelHorizontalOffset);
            } else {
                f3 = this.gridRect.right + this.rangeLabelTickExtension + this.rangeLabelHorizontalOffset;
            }
            drawTickText(canvas, XYAxisType.RANGE, number, f3, f2 - this.rangeLabelVerticalOffset, paint);
        }
    }

    public AxisValueLabelFormatter getAxisValueLabelFormatterForDomainVal(double d2) {
        for (RectRegion rectRegion : this.axisValueLabelRegions.elements()) {
            if (rectRegion.containsDomainValue(Double.valueOf(d2))) {
                return this.axisValueLabelRegions.get(rectRegion);
            }
        }
        return null;
    }

    public AxisValueLabelFormatter getAxisValueLabelFormatterForRangeVal(double d2) {
        for (RectRegion rectRegion : this.axisValueLabelRegions.elements()) {
            if (rectRegion.containsRangeValue(Double.valueOf(d2))) {
                return this.axisValueLabelRegions.get(rectRegion);
            }
        }
        return null;
    }

    public AxisValueLabelFormatter getAxisValueLabelFormatterForVal(double d2, double d3) {
        for (RectRegion rectRegion : this.axisValueLabelRegions.elements()) {
            if (rectRegion.containsValue(Double.valueOf(d2), Double.valueOf(d3))) {
                return this.axisValueLabelRegions.get(rectRegion);
            }
        }
        return null;
    }

    public ZIndexable<RectRegion> getAxisValueLabelRegions() {
        return this.axisValueLabelRegions;
    }

    public Paint getCursorLabelBackgroundPaint() {
        return this.cursorLabelBackgroundPaint;
    }

    public Paint getCursorLabelPaint() {
        return this.cursorLabelPaint;
    }

    public float getDomainCursorPosition() {
        return this.domainCursorPosition;
    }

    public Double getDomainCursorVal() {
        return getXVal(getDomainCursorPosition());
    }

    public Paint getDomainGridLinePaint() {
        return this.domainGridLinePaint;
    }

    public float getDomainLabelHorizontalOffset() {
        return this.domainLabelHorizontalOffset;
    }

    public float getDomainLabelOrientation() {
        return this.domainLabelOrientation;
    }

    public Paint getDomainLabelPaint() {
        return this.domainLabelPaint;
    }

    public int getDomainLabelTickExtension() {
        return this.domainLabelTickExtension;
    }

    public float getDomainLabelVerticalOffset() {
        return this.domainLabelVerticalOffset;
    }

    public float getDomainLabelWidth() {
        return this.domainLabelWidth;
    }

    public Paint getDomainOriginLabelPaint() {
        return this.domainOriginLabelPaint;
    }

    public Paint getDomainOriginLinePaint() {
        return this.domainOriginLinePaint;
    }

    public Paint getDomainSubGridLinePaint() {
        return this.domainSubGridLinePaint;
    }

    public Format getDomainValueFormat() {
        return this.domainValueFormat;
    }

    public Paint getGridBackgroundPaint() {
        return this.gridBackgroundPaint;
    }

    public float getGridPaddingBottom() {
        return this.gridPaddingBottom;
    }

    public float getGridPaddingLeft() {
        return this.gridPaddingLeft;
    }

    public float getGridPaddingRight() {
        return this.gridPaddingRight;
    }

    public float getGridPaddingTop() {
        return this.gridPaddingTop;
    }

    public RectF getGridRect() {
        return this.paddedGridRect;
    }

    public float getRangeCursorPosition() {
        return this.rangeCursorPosition;
    }

    public Double getRangeCursorVal() {
        return getYVal(getRangeCursorPosition());
    }

    public Paint getRangeGridLinePaint() {
        return this.rangeGridLinePaint;
    }

    public float getRangeLabelHorizontalOffset() {
        return this.rangeLabelHorizontalOffset;
    }

    public float getRangeLabelOrientation() {
        return this.rangeLabelOrientation;
    }

    public Paint getRangeLabelPaint() {
        return this.rangeLabelPaint;
    }

    public int getRangeLabelTickExtension() {
        return this.rangeLabelTickExtension;
    }

    public float getRangeLabelVerticalOffset() {
        return this.rangeLabelVerticalOffset;
    }

    public float getRangeLabelWidth() {
        return this.rangeLabelWidth;
    }

    public Paint getRangeOriginLabelPaint() {
        return this.rangeOriginLabelPaint;
    }

    public Paint getRangeOriginLinePaint() {
        return this.rangeOriginLinePaint;
    }

    public Paint getRangeSubGridLinePaint() {
        return this.rangeSubGridLinePaint;
    }

    public Format getRangeValueFormat() {
        return this.rangeValueFormat;
    }

    public int getTicksPerDomainLabel() {
        return this.ticksPerDomainLabel;
    }

    public int getTicksPerRangeLabel() {
        return this.ticksPerRangeLabel;
    }

    public Double getXVal(PointF pointF) {
        return getXVal(pointF.x);
    }

    public Double getYVal(PointF pointF) {
        return getYVal(pointF.y);
    }

    public boolean isDomainAxisBottom() {
        return this.domainAxisBottom;
    }

    public boolean isDrawMarkersEnabled() {
        return this.drawMarkersEnabled;
    }

    public boolean isRangeAxisLeft() {
        return this.rangeAxisLeft;
    }

    public void setCursorLabelBackgroundPaint(Paint paint) {
        this.cursorLabelBackgroundPaint = paint;
    }

    public void setCursorLabelPaint(Paint paint) {
        this.cursorLabelPaint = paint;
    }

    public void setCursorPosition(float f2, float f3) {
        setDomainCursorPosition(f2);
        setRangeCursorPosition(f3);
    }

    public void setDomainAxisBottom(boolean z) {
        this.domainAxisBottom = z;
    }

    public void setDomainAxisPosition(boolean z, boolean z2, int i, String str) {
        setDomainAxisBottom(z);
        if (z2) {
            setDomainLabelWidth(1.0f);
            setDomainLabelVerticalOffset(2.0f);
            setDomainLabelTickExtension(0);
            Paint domainLabelPaint = getDomainLabelPaint();
            if (domainLabelPaint != null) {
                Rect packedStringDimensions = FontUtils.getPackedStringDimensions(str, domainLabelPaint);
                if (z) {
                    setDomainLabelVerticalOffset(packedStringDimensions.top * 2);
                    return;
                } else {
                    setDomainLabelVerticalOffset(packedStringDimensions.top - 1.0f);
                    return;
                }
            }
            return;
        }
        setDomainLabelWidth(1.0f);
        setDomainLabelTickExtension(i);
        Paint domainLabelPaint2 = getDomainLabelPaint();
        if (domainLabelPaint2 != null) {
            float fontHeight = FontUtils.getFontHeight(domainLabelPaint2);
            if (z) {
                setDomainLabelVerticalOffset(-4.0f);
            } else {
                setDomainLabelVerticalOffset(1.0f);
            }
            setDomainLabelWidth(fontHeight + getDomainLabelTickExtension());
        }
    }

    public void setDomainCursorPosition(float f2) {
        this.domainCursorPosition = f2;
    }

    public void setDomainGridLinePaint(Paint paint) {
        this.domainGridLinePaint = paint;
    }

    public void setDomainLabelHorizontalOffset(float f2) {
        this.domainLabelHorizontalOffset = f2;
    }

    public void setDomainLabelOrientation(float f2) {
        this.domainLabelOrientation = f2;
    }

    public void setDomainLabelPaint(Paint paint) {
        this.domainLabelPaint = paint;
    }

    public void setDomainLabelTickExtension(int i) {
        this.domainLabelTickExtension = i;
    }

    public void setDomainLabelVerticalOffset(float f2) {
        this.domainLabelVerticalOffset = f2;
    }

    public void setDomainLabelWidth(float f2) {
        this.domainLabelWidth = f2;
    }

    public void setDomainOriginLabelPaint(Paint paint) {
        this.domainOriginLabelPaint = paint;
    }

    public void setDomainOriginLinePaint(Paint paint) {
        this.domainOriginLinePaint = paint;
    }

    public void setDomainScale(float f2) {
        this.domainScale = f2;
    }

    public void setDomainSubGridLinePaint(Paint paint) {
        this.domainSubGridLinePaint = paint;
    }

    public void setDomainValueFormat(Format format) {
        this.domainValueFormat = format;
    }

    public void setDrawMarkersEnabled(boolean z) {
        this.drawMarkersEnabled = z;
    }

    public void setGridBackgroundPaint(Paint paint) {
        this.gridBackgroundPaint = paint;
    }

    public void setGridPadding(float f2, float f3, float f4, float f5) {
        setGridPaddingLeft(f2);
        setGridPaddingTop(f3);
        setGridPaddingRight(f4);
        setGridPaddingBottom(f5);
    }

    public void setGridPaddingBottom(float f2) {
        this.gridPaddingBottom = f2;
    }

    public void setGridPaddingLeft(float f2) {
        this.gridPaddingLeft = f2;
    }

    public void setGridPaddingRight(float f2) {
        this.gridPaddingRight = f2;
    }

    public void setGridPaddingTop(float f2) {
        this.gridPaddingTop = f2;
    }

    public void setRangeAxisLeft(boolean z) {
        this.rangeAxisLeft = z;
    }

    public void setRangeAxisPosition(boolean z, boolean z2, int i, String str) {
        setRangeAxisLeft(z);
        if (z2) {
            setRangeLabelWidth(1.0f);
            setRangeLabelHorizontalOffset(-2.0f);
            setRangeLabelVerticalOffset(2.0f);
            Paint rangeLabelPaint = getRangeLabelPaint();
            if (rangeLabelPaint != null) {
                rangeLabelPaint.setTextAlign(z ? Paint.Align.LEFT : Paint.Align.RIGHT);
            }
            Paint rangeOriginLabelPaint = getRangeOriginLabelPaint();
            if (rangeOriginLabelPaint != null) {
                rangeOriginLabelPaint.setTextAlign(z ? Paint.Align.LEFT : Paint.Align.RIGHT);
            }
            setRangeLabelTickExtension(0);
            return;
        }
        setRangeLabelWidth(1.0f);
        setRangeLabelHorizontalOffset(1.0f);
        setRangeLabelTickExtension(i);
        Paint rangeLabelPaint2 = getRangeLabelPaint();
        if (rangeLabelPaint2 != null) {
            rangeLabelPaint2.setTextAlign(!z ? Paint.Align.LEFT : Paint.Align.RIGHT);
            Rect packedStringDimensions = FontUtils.getPackedStringDimensions(str, rangeLabelPaint2);
            setRangeLabelVerticalOffset(packedStringDimensions.top / 2);
            setRangeLabelWidth(packedStringDimensions.right + getRangeLabelTickExtension());
        }
        Paint rangeOriginLabelPaint2 = getRangeOriginLabelPaint();
        if (rangeOriginLabelPaint2 != null) {
            rangeOriginLabelPaint2.setTextAlign(!z ? Paint.Align.LEFT : Paint.Align.RIGHT);
        }
    }

    public void setRangeCursorPosition(float f2) {
        this.rangeCursorPosition = f2;
    }

    public void setRangeGridLinePaint(Paint paint) {
        this.rangeGridLinePaint = paint;
    }

    public void setRangeLabelHorizontalOffset(float f2) {
        this.rangeLabelHorizontalOffset = f2;
    }

    public void setRangeLabelOrientation(float f2) {
        this.rangeLabelOrientation = f2;
    }

    public void setRangeLabelPaint(Paint paint) {
        this.rangeLabelPaint = paint;
    }

    public void setRangeLabelTickExtension(int i) {
        this.rangeLabelTickExtension = i;
    }

    public void setRangeLabelVerticalOffset(float f2) {
        this.rangeLabelVerticalOffset = f2;
    }

    public void setRangeLabelWidth(float f2) {
        this.rangeLabelWidth = f2;
    }

    public void setRangeOriginLabelPaint(Paint paint) {
        this.rangeOriginLabelPaint = paint;
    }

    public void setRangeOriginLinePaint(Paint paint) {
        this.rangeOriginLinePaint = paint;
    }

    public void setRangeSubGridLinePaint(Paint paint) {
        this.rangeSubGridLinePaint = paint;
    }

    public void setRangeValueFormat(Format format) {
        this.rangeValueFormat = format;
    }

    public void setTicksPerDomainLabel(int i) {
        this.ticksPerDomainLabel = i;
    }

    public void setTicksPerRangeLabel(int i) {
        this.ticksPerRangeLabel = i;
    }

    private RectF getGridRect(RectF rectF) {
        return new RectF(rectF.left + (this.rangeAxisLeft ? this.rangeLabelWidth : 1.0f), rectF.top + (this.domainAxisBottom ? 1.0f : this.domainLabelWidth), rectF.right - (this.rangeAxisLeft ? 1.0f : this.rangeLabelWidth), rectF.bottom - (this.domainAxisBottom ? this.domainLabelWidth : 1.0f));
    }

    public Double getXVal(float f2) {
        if (this.plot.getCalculatedMinX() == null || this.plot.getCalculatedMaxX() == null) {
            return null;
        }
        return Double.valueOf(ValPixConverter.pixToVal(f2 - this.paddedGridRect.left, this.plot.getCalculatedMinX().doubleValue(), this.plot.getCalculatedMaxX().doubleValue(), this.paddedGridRect.width(), false));
    }

    public Double getYVal(float f2) {
        if (this.plot.getCalculatedMinY() == null || this.plot.getCalculatedMaxY() == null) {
            return null;
        }
        return Double.valueOf(ValPixConverter.pixToVal(f2 - this.paddedGridRect.top, this.plot.getCalculatedMinY().doubleValue(), this.plot.getCalculatedMaxY().doubleValue(), this.paddedGridRect.height(), true));
    }

    public void setCursorPosition(PointF pointF) {
        setCursorPosition(pointF.x, pointF.y);
    }
}
