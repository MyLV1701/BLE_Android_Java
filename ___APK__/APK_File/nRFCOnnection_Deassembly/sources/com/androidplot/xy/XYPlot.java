package com.androidplot.xy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import com.androidplot.Plot;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.DynamicTableModel;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.YLayoutStyle;
import com.androidplot.ui.widget.TextLabelWidget;
import com.androidplot.util.PixelUtils;
import java.text.Format;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class XYPlot extends Plot<XYSeries, XYSeriesFormatter, XYSeriesRenderer> {
    private static final int DEFAULT_DOMAIN_LABEL_WIDGET_H_DP = 10;
    private static final int DEFAULT_DOMAIN_LABEL_WIDGET_W_DP = 80;
    private static final int DEFAULT_DOMAIN_LABEL_WIDGET_X_OFFSET_DP = -90;
    private static final int DEFAULT_DOMAIN_LABEL_WIDGET_Y_OFFSET_DP = 0;
    private static final int DEFAULT_GRAPH_WIDGET_H_DP = 18;
    private static final int DEFAULT_GRAPH_WIDGET_RIGHT_MARGIN_DP = 3;
    private static final int DEFAULT_GRAPH_WIDGET_TOP_MARGIN_DP = 3;
    private static final int DEFAULT_GRAPH_WIDGET_W_DP = 10;
    private static final int DEFAULT_GRAPH_WIDGET_X_OFFSET_DP = 0;
    private static final int DEFAULT_GRAPH_WIDGET_Y_OFFSET_DP = 0;
    private static final int DEFAULT_LEGEND_WIDGET_H_DP = 10;
    private static final int DEFAULT_LEGEND_WIDGET_ICON_SIZE_DP = 7;
    private static final int DEFAULT_LEGEND_WIDGET_X_OFFSET_DP = 40;
    private static final int DEFAULT_LEGEND_WIDGET_Y_OFFSET_DP = 0;
    private static final int DEFAULT_PLOT_BOTTOM_MARGIN_DP = 2;
    private static final int DEFAULT_PLOT_LEFT_MARGIN_DP = 2;
    private static final int DEFAULT_PLOT_RIGHT_MARGIN_DP = 2;
    private static final int DEFAULT_RANGE_LABEL_WIDGET_H_DP = 50;
    private static final int DEFAULT_RANGE_LABEL_WIDGET_W_DP = 10;
    private static final int DEFAULT_RANGE_LABEL_WIDGET_X_OFFSET_DP = 0;
    private static final int DEFAULT_RANGE_LABEL_WIDGET_Y_OFFSET_DP = 0;
    private Number calculatedDomainOrigin;
    private Number calculatedMaxX;
    private Number calculatedMaxY;
    private Number calculatedMinX;
    private Number calculatedMinY;
    private Number calculatedRangeOrigin;
    private RectRegion defaultBounds;
    private XYFramingModel domainFramingModel;
    private TextLabelWidget domainLabelWidget;
    private Number domainLeftMax;
    private Number domainLeftMin;
    private BoundaryMode domainLowerBoundaryMode;
    private BoundaryMode domainOriginBoundaryMode;
    private Number domainOriginExtent;
    private Number domainRightMax;
    private Number domainRightMin;
    private XYStepMode domainStepMode;
    private double domainStepValue;
    private BoundaryMode domainUpperBoundaryMode;
    private boolean drawDomainOriginEnabled;
    private boolean drawRangeOriginEnabled;
    private XYGraphWidget graphWidget;
    private XYLegendWidget legendWidget;
    private Number prevMaxX;
    private Number prevMaxY;
    private Number prevMinX;
    private Number prevMinY;
    private Number rangeBottomMax;
    private Number rangeBottomMin;
    private XYFramingModel rangeFramingModel;
    private TextLabelWidget rangeLabelWidget;
    private BoundaryMode rangeLowerBoundaryMode;
    private BoundaryMode rangeOriginBoundaryMode;
    private Number rangeOriginExtent;
    private XYStepMode rangeStepMode;
    private double rangeStepValue;
    private Number rangeTopMax;
    private Number rangeTopMin;
    private BoundaryMode rangeUpperBoundaryMode;
    private Number userDomainOrigin;
    private Number userMaxX;
    private Number userMaxY;
    private Number userMinX;
    private Number userMinY;
    private Number userRangeOrigin;
    private ArrayList<XValueMarker> xValueMarkers;
    private ArrayList<YValueMarker> yValueMarkers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.xy.XYPlot$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$xy$BoundaryMode = new int[BoundaryMode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$xy$XYFramingModel;

        static {
            try {
                $SwitchMap$com$androidplot$xy$BoundaryMode[BoundaryMode.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$xy$BoundaryMode[BoundaryMode.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$xy$BoundaryMode[BoundaryMode.GROW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$androidplot$xy$BoundaryMode[BoundaryMode.SHRINNK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $SwitchMap$com$androidplot$xy$XYFramingModel = new int[XYFramingModel.values().length];
            try {
                $SwitchMap$com$androidplot$xy$XYFramingModel[XYFramingModel.ORIGIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$androidplot$xy$XYFramingModel[XYFramingModel.EDGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public XYPlot(Context context, String str) {
        super(context, str);
        XYStepMode xYStepMode = XYStepMode.SUBDIVIDE;
        this.domainStepMode = xYStepMode;
        this.domainStepValue = 10.0d;
        this.rangeStepMode = xYStepMode;
        this.rangeStepValue = 10.0d;
        this.rangeTopMin = null;
        this.rangeTopMax = null;
        this.rangeBottomMin = null;
        this.rangeBottomMax = null;
        this.domainLeftMin = null;
        this.domainLeftMax = null;
        this.domainRightMin = null;
        this.domainRightMax = null;
        XYFramingModel xYFramingModel = XYFramingModel.EDGE;
        this.domainFramingModel = xYFramingModel;
        this.rangeFramingModel = xYFramingModel;
        this.domainOriginExtent = null;
        this.rangeOriginExtent = null;
        BoundaryMode boundaryMode = BoundaryMode.AUTO;
        this.domainUpperBoundaryMode = boundaryMode;
        this.domainLowerBoundaryMode = boundaryMode;
        this.rangeUpperBoundaryMode = boundaryMode;
        this.rangeLowerBoundaryMode = boundaryMode;
        this.drawDomainOriginEnabled = true;
        this.drawRangeOriginEnabled = true;
    }

    private Number applyUserMinMax(Number number, Number number2, Number number3) {
        if (number2 != null && number.doubleValue() <= number2.doubleValue()) {
            number = number2;
        }
        return (number3 == null || number.doubleValue() < number3.doubleValue()) ? number : number3;
    }

    private double distance(double d2, double d3) {
        return d2 > d3 ? d2 - d3 : d3 - d2;
    }

    private boolean isPointVisible(Number number, Number number2) {
        return number != null && number2 != null && isValWithinRange(number2.doubleValue(), this.userMinY, this.userMaxY) && isValWithinRange(number.doubleValue(), this.userMinX, this.userMaxX);
    }

    private boolean isValWithinRange(double d2, Number number, Number number2) {
        return (number == null || (d2 > number.doubleValue() ? 1 : (d2 == number.doubleValue() ? 0 : -1)) >= 0) && (number2 == null || (d2 > number2.doubleValue() ? 1 : (d2 == number2.doubleValue() ? 0 : -1)) <= 0);
    }

    private boolean isXValWithinView(double d2) {
        Number number = this.userMinY;
        return ((number == null || d2 >= number.doubleValue()) && this.userMaxY == null) || d2 <= this.userMaxY.doubleValue();
    }

    private void updateDomainMinMaxForEdgeModel() {
        int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$BoundaryMode[this.domainUpperBoundaryMode.ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3) {
                if (i == 4) {
                    if (this.prevMaxX != null && this.calculatedMaxX.doubleValue() >= this.prevMaxX.doubleValue()) {
                        this.calculatedMaxX = this.prevMaxX;
                    }
                } else {
                    throw new UnsupportedOperationException("DomainUpperBoundaryMode not yet implemented: " + this.domainUpperBoundaryMode);
                }
            } else if (this.prevMaxX != null && this.calculatedMaxX.doubleValue() <= this.prevMaxX.doubleValue()) {
                this.calculatedMaxX = this.prevMaxX;
            }
        }
        int i2 = AnonymousClass1.$SwitchMap$com$androidplot$xy$BoundaryMode[this.domainLowerBoundaryMode.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return;
        }
        if (i2 == 3) {
            if (this.prevMinX == null || this.calculatedMinX.doubleValue() < this.prevMinX.doubleValue()) {
                return;
            }
            this.calculatedMinX = this.prevMinX;
            return;
        }
        if (i2 == 4) {
            if (this.prevMinX == null || this.calculatedMinX.doubleValue() > this.prevMinX.doubleValue()) {
                return;
            }
            this.calculatedMinX = this.prevMinX;
            return;
        }
        throw new UnsupportedOperationException("DomainLowerBoundaryMode not supported: " + this.domainLowerBoundaryMode);
    }

    public boolean addMarker(YValueMarker yValueMarker) {
        if (this.yValueMarkers.contains(yValueMarker)) {
            return false;
        }
        return this.yValueMarkers.add(yValueMarker);
    }

    public void calculateMinMaxVals() {
        Number number;
        Number number2;
        this.prevMinX = this.calculatedMinX;
        this.prevMaxX = this.calculatedMaxX;
        this.prevMinY = this.calculatedMinY;
        this.prevMaxY = this.calculatedMaxY;
        this.calculatedMinX = this.userMinX;
        this.calculatedMaxX = this.userMaxX;
        this.calculatedMinY = this.userMinY;
        this.calculatedMaxY = this.userMaxY;
        Iterator<XYSeries> it = getSeriesSet().iterator();
        while (it.hasNext()) {
            int i = 0;
            for (Float f2 : it.next().getY()) {
                if (this.userMinX == null && ((number2 = this.calculatedMinX) == null || i < number2.doubleValue())) {
                    this.calculatedMinX = Integer.valueOf(i);
                }
                if (this.userMaxX == null && ((number = this.calculatedMaxX) == null || i > number.doubleValue())) {
                    this.calculatedMaxX = Integer.valueOf(i);
                }
                if (this.userMinY == null && f2 != null && (this.calculatedMinY == null || f2.doubleValue() < this.calculatedMinY.doubleValue())) {
                    this.calculatedMinY = f2;
                }
                if (this.userMaxY == null && f2 != null && (this.calculatedMaxY == null || f2.doubleValue() > this.calculatedMaxY.doubleValue())) {
                    this.calculatedMaxY = f2;
                }
                i++;
            }
        }
        int i2 = AnonymousClass1.$SwitchMap$com$androidplot$xy$XYFramingModel[this.domainFramingModel.ordinal()];
        if (i2 == 1) {
            updateDomainMinMaxForOriginModel();
        } else if (i2 == 2) {
            updateDomainMinMaxForEdgeModel();
            this.calculatedMinX = applyUserMinMax(this.calculatedMinX, this.domainLeftMin, this.domainLeftMax);
            this.calculatedMaxX = applyUserMinMax(this.calculatedMaxX, this.domainRightMin, this.domainRightMax);
        } else {
            throw new UnsupportedOperationException("Domain Framing Model not yet supported: " + this.domainFramingModel);
        }
        int i3 = AnonymousClass1.$SwitchMap$com$androidplot$xy$XYFramingModel[this.rangeFramingModel.ordinal()];
        if (i3 == 1) {
            updateRangeMinMaxForOriginModel();
        } else if (i3 == 2) {
            if (getSeriesSet().size() > 0) {
                updateRangeMinMaxForEdgeModel();
                this.calculatedMinY = applyUserMinMax(this.calculatedMinY, this.rangeBottomMin, this.rangeBottomMax);
                this.calculatedMaxY = applyUserMinMax(this.calculatedMaxY, this.rangeTopMin, this.rangeTopMax);
            }
        } else {
            throw new UnsupportedOperationException("Range Framing Model not yet supported: " + this.domainFramingModel);
        }
        Number number3 = this.userDomainOrigin;
        if (number3 == null) {
            number3 = getCalculatedMinX();
        }
        this.calculatedDomainOrigin = number3;
        Number number4 = this.userRangeOrigin;
        if (number4 == null) {
            number4 = getCalculatedMinY();
        }
        this.calculatedRangeOrigin = number4;
    }

    public void centerOnDomainOrigin(Number number) {
        centerOnDomainOrigin(number, null, BoundaryMode.AUTO);
    }

    public void centerOnRangeOrigin(Number number) {
        centerOnRangeOrigin(number, null, BoundaryMode.AUTO);
    }

    public boolean containsPoint(float f2, float f3) {
        if (getGraphWidget().getGridRect() != null) {
            return getGraphWidget().getGridRect().contains(f2, f3);
        }
        return false;
    }

    public Number getCalculatedMaxX() {
        Number number = this.calculatedMaxX;
        return number != null ? number : Integer.valueOf(getDefaultBounds().getMaxX());
    }

    public Number getCalculatedMaxY() {
        Number number = this.calculatedMaxY;
        return number != null ? number : Float.valueOf(getDefaultBounds().getMaxY());
    }

    public Number getCalculatedMinX() {
        Number number = this.calculatedMinX;
        return number != null ? number : Integer.valueOf(getDefaultBounds().getMinX());
    }

    public Number getCalculatedMinY() {
        Number number = this.calculatedMinY;
        return number != null ? number : Float.valueOf(getDefaultBounds().getMinY());
    }

    public RectRegion getDefaultBounds() {
        return this.defaultBounds;
    }

    public XYFramingModel getDomainFramingModel() {
        return this.domainFramingModel;
    }

    public String getDomainLabel() {
        return getDomainLabelWidget().getText();
    }

    public TextLabelWidget getDomainLabelWidget() {
        return this.domainLabelWidget;
    }

    public Number getDomainLeftMax() {
        return this.domainLeftMax;
    }

    public Number getDomainLeftMin() {
        return this.domainLeftMin;
    }

    protected BoundaryMode getDomainLowerBoundaryMode() {
        return this.domainLowerBoundaryMode;
    }

    public Number getDomainOrigin() {
        return this.calculatedDomainOrigin;
    }

    public Number getDomainRightMax() {
        return this.domainRightMax;
    }

    public Number getDomainRightMin() {
        return this.domainRightMin;
    }

    public XYStepMode getDomainStepMode() {
        return this.domainStepMode;
    }

    public double getDomainStepValue() {
        return this.domainStepValue;
    }

    protected BoundaryMode getDomainUpperBoundaryMode() {
        return this.domainUpperBoundaryMode;
    }

    public Format getDomainValueFormat() {
        return this.graphWidget.getDomainValueFormat();
    }

    public XYGraphWidget getGraphWidget() {
        return this.graphWidget;
    }

    public XYLegendWidget getLegendWidget() {
        return this.legendWidget;
    }

    public Number getRangeBottomMax() {
        return this.rangeBottomMax;
    }

    public Number getRangeBottomMin() {
        return this.rangeBottomMin;
    }

    public XYFramingModel getRangeFramingModel() {
        return this.rangeFramingModel;
    }

    public String getRangeLabel() {
        return getRangeLabelWidget().getText();
    }

    public TextLabelWidget getRangeLabelWidget() {
        return this.rangeLabelWidget;
    }

    protected BoundaryMode getRangeLowerBoundaryMode() {
        return this.rangeLowerBoundaryMode;
    }

    public Number getRangeOrigin() {
        return this.calculatedRangeOrigin;
    }

    public XYStepMode getRangeStepMode() {
        return this.rangeStepMode;
    }

    public double getRangeStepValue() {
        return this.rangeStepValue;
    }

    public Number getRangeTopMax() {
        return this.rangeTopMax;
    }

    public Number getRangeTopMin() {
        return this.rangeTopMin;
    }

    protected BoundaryMode getRangeUpperBoundaryMode() {
        return this.rangeUpperBoundaryMode;
    }

    public Format getRangeValueFormat() {
        return this.graphWidget.getRangeValueFormat();
    }

    public int getTicksPerDomainLabel() {
        return this.graphWidget.getTicksPerDomainLabel();
    }

    public int getTicksPerRangeLabel() {
        return this.graphWidget.getTicksPerRangeLabel();
    }

    public Number getUserMaxX() {
        return this.userMaxX;
    }

    public Number getUserMaxY() {
        return this.userMaxY;
    }

    public Number getUserMinX() {
        return this.userMinX;
    }

    public Number getUserMinY() {
        return this.userMinY;
    }

    public Number getXVal(PointF pointF) {
        return getGraphWidget().getXVal(pointF);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<XValueMarker> getXValueMarkers() {
        return this.xValueMarkers;
    }

    public Number getYVal(PointF pointF) {
        return getGraphWidget().getYVal(pointF);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<YValueMarker> getYValueMarkers() {
        return this.yValueMarkers;
    }

    public boolean isDrawDomainOriginEnabled() {
        return this.drawDomainOriginEnabled;
    }

    public boolean isDrawRangeOriginEnabled() {
        return this.drawRangeOriginEnabled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidplot.Plot
    public void notifyListenersBeforeDraw(Canvas canvas) {
        super.notifyListenersBeforeDraw(canvas);
        calculateMinMaxVals();
    }

    @Override // com.androidplot.Plot
    protected void onPreInit() {
        this.legendWidget = new XYLegendWidget(getLayoutManager(), this, new SizeMetrics(PixelUtils.dpToPix(10.0f), SizeLayoutType.ABSOLUTE, 0.5f, SizeLayoutType.RELATIVE), new DynamicTableModel(0, 1), new SizeMetrics(PixelUtils.dpToPix(7.0f), SizeLayoutType.ABSOLUTE, PixelUtils.dpToPix(7.0f), SizeLayoutType.ABSOLUTE));
        this.graphWidget = new XYGraphWidget(getLayoutManager(), this, new SizeMetrics(PixelUtils.dpToPix(18.0f), SizeLayoutType.FILL, PixelUtils.dpToPix(10.0f), SizeLayoutType.FILL));
        Paint paint = new Paint();
        paint.setColor(-12303292);
        paint.setStyle(Paint.Style.FILL);
        this.graphWidget.setBackgroundPaint(paint);
        this.domainLabelWidget = new TextLabelWidget(getLayoutManager(), new SizeMetrics(PixelUtils.dpToPix(10.0f), SizeLayoutType.ABSOLUTE, PixelUtils.dpToPix(80.0f), SizeLayoutType.ABSOLUTE), TextOrientationType.HORIZONTAL);
        this.rangeLabelWidget = new TextLabelWidget(getLayoutManager(), new SizeMetrics(PixelUtils.dpToPix(50.0f), SizeLayoutType.ABSOLUTE, PixelUtils.dpToPix(10.0f), SizeLayoutType.ABSOLUTE), TextOrientationType.VERTICAL_ASCENDING);
        this.legendWidget.position(PixelUtils.dpToPix(40.0f), XLayoutStyle.ABSOLUTE_FROM_RIGHT, PixelUtils.dpToPix(0.0f), YLayoutStyle.ABSOLUTE_FROM_BOTTOM, AnchorPosition.RIGHT_BOTTOM);
        this.graphWidget.position(PixelUtils.dpToPix(0.0f), XLayoutStyle.ABSOLUTE_FROM_RIGHT, PixelUtils.dpToPix(0.0f), YLayoutStyle.ABSOLUTE_FROM_CENTER, AnchorPosition.RIGHT_MIDDLE);
        this.domainLabelWidget.position(PixelUtils.dpToPix(-90.0f), XLayoutStyle.ABSOLUTE_FROM_CENTER, PixelUtils.dpToPix(0.0f), YLayoutStyle.ABSOLUTE_FROM_BOTTOM, AnchorPosition.LEFT_BOTTOM);
        this.rangeLabelWidget.position(PixelUtils.dpToPix(0.0f), XLayoutStyle.ABSOLUTE_FROM_LEFT, PixelUtils.dpToPix(0.0f), YLayoutStyle.ABSOLUTE_FROM_CENTER, AnchorPosition.LEFT_MIDDLE);
        getLayoutManager().moveToTop(getTitleWidget());
        getLayoutManager().moveToTop(getLegendWidget());
        this.graphWidget.setMarginTop(PixelUtils.dpToPix(3.0f));
        this.graphWidget.setMarginRight(PixelUtils.dpToPix(3.0f));
        getDomainLabelWidget().pack();
        getRangeLabelWidget().pack();
        setPlotMarginLeft(PixelUtils.dpToPix(2.0f));
        setPlotMarginRight(PixelUtils.dpToPix(2.0f));
        setPlotMarginBottom(PixelUtils.dpToPix(2.0f));
        this.xValueMarkers = new ArrayList<>();
        this.yValueMarkers = new ArrayList<>();
        setDefaultBounds(new RectRegion(-1, 1, -1, 1));
    }

    public YValueMarker removeMarker(YValueMarker yValueMarker) {
        int indexOf = this.yValueMarkers.indexOf(yValueMarker);
        if (indexOf == -1) {
            return null;
        }
        return this.yValueMarkers.remove(indexOf);
    }

    public int removeMarkers() {
        return removeXMarkers() + removeYMarkers();
    }

    public int removeXMarkers() {
        int size = this.xValueMarkers.size();
        this.xValueMarkers.clear();
        return size;
    }

    public int removeYMarkers() {
        int size = this.yValueMarkers.size();
        this.yValueMarkers.clear();
        return size;
    }

    public void setCursorPosition(PointF pointF) {
        getGraphWidget().setCursorPosition(pointF);
    }

    public void setDefaultBounds(RectRegion rectRegion) {
        this.defaultBounds = rectRegion;
    }

    public synchronized void setDomainBoundaries(Number number, Number number2, BoundaryMode boundaryMode) {
        setDomainBoundaries(number, boundaryMode, number2, boundaryMode);
    }

    protected void setDomainFramingModel(XYFramingModel xYFramingModel) {
        this.domainFramingModel = xYFramingModel;
    }

    public void setDomainLabel(String str) {
        getDomainLabelWidget().setText(str);
    }

    public void setDomainLabelWidget(TextLabelWidget textLabelWidget) {
        this.domainLabelWidget = textLabelWidget;
    }

    public synchronized void setDomainLeftMax(Number number) {
        this.domainLeftMax = number;
    }

    public synchronized void setDomainLeftMin(Number number) {
        this.domainLeftMin = number;
    }

    public synchronized void setDomainLowerBoundary(Number number, BoundaryMode boundaryMode) {
        if (boundaryMode != BoundaryMode.FIXED) {
            number = null;
        }
        setUserMinX(number);
        setDomainLowerBoundaryMode(boundaryMode);
        setDomainFramingModel(XYFramingModel.EDGE);
    }

    protected synchronized void setDomainLowerBoundaryMode(BoundaryMode boundaryMode) {
        this.domainLowerBoundaryMode = boundaryMode;
    }

    public synchronized void setDomainRightMax(Number number) {
        this.domainRightMax = number;
    }

    public synchronized void setDomainRightMin(Number number) {
        this.domainRightMin = number;
    }

    public void setDomainStep(XYStepMode xYStepMode, double d2) {
        setDomainStepMode(xYStepMode);
        setDomainStepValue(d2);
    }

    public void setDomainStepMode(XYStepMode xYStepMode) {
        this.domainStepMode = xYStepMode;
    }

    public void setDomainStepValue(double d2) {
        this.domainStepValue = d2;
    }

    public synchronized void setDomainUpperBoundary(Number number, BoundaryMode boundaryMode) {
        if (boundaryMode != BoundaryMode.FIXED) {
            number = null;
        }
        setUserMaxX(number);
        setDomainUpperBoundaryMode(boundaryMode);
        setDomainFramingModel(XYFramingModel.EDGE);
    }

    protected synchronized void setDomainUpperBoundaryMode(BoundaryMode boundaryMode) {
        this.domainUpperBoundaryMode = boundaryMode;
    }

    public void setDomainValueFormat(Format format) {
        this.graphWidget.setDomainValueFormat(format);
    }

    public void setDrawDomainOriginEnabled(boolean z) {
        this.drawDomainOriginEnabled = z;
    }

    public void setDrawRangeOriginEnabled(boolean z) {
        this.drawRangeOriginEnabled = z;
    }

    public void setGraphWidget(XYGraphWidget xYGraphWidget) {
        this.graphWidget = xYGraphWidget;
    }

    public void setGridPadding(float f2, float f3, float f4, float f5) {
        getGraphWidget().setGridPaddingTop(f3);
        getGraphWidget().setGridPaddingBottom(f5);
        getGraphWidget().setGridPaddingLeft(f2);
        getGraphWidget().setGridPaddingRight(f4);
    }

    public void setLegendWidget(XYLegendWidget xYLegendWidget) {
        this.legendWidget = xYLegendWidget;
    }

    public synchronized void setRangeBottomMax(Number number) {
        this.rangeBottomMax = number;
    }

    public synchronized void setRangeBottomMin(Number number) {
        this.rangeBottomMin = number;
    }

    public synchronized void setRangeBoundaries(Number number, Number number2, BoundaryMode boundaryMode) {
        setRangeBoundaries(number, boundaryMode, number2, boundaryMode);
    }

    protected void setRangeFramingModel(XYFramingModel xYFramingModel) {
        this.rangeFramingModel = xYFramingModel;
    }

    public void setRangeLabel(String str) {
        getRangeLabelWidget().setText(str);
    }

    public void setRangeLabelWidget(TextLabelWidget textLabelWidget) {
        this.rangeLabelWidget = textLabelWidget;
    }

    public synchronized void setRangeLowerBoundary(Number number, BoundaryMode boundaryMode) {
        if (boundaryMode != BoundaryMode.FIXED) {
            number = null;
        }
        setUserMinY(number);
        setRangeLowerBoundaryMode(boundaryMode);
        setRangeFramingModel(XYFramingModel.EDGE);
    }

    protected synchronized void setRangeLowerBoundaryMode(BoundaryMode boundaryMode) {
        this.rangeLowerBoundaryMode = boundaryMode;
    }

    public void setRangeStep(XYStepMode xYStepMode, double d2) {
        setRangeStepMode(xYStepMode);
        setRangeStepValue(d2);
    }

    public void setRangeStepMode(XYStepMode xYStepMode) {
        this.rangeStepMode = xYStepMode;
    }

    public void setRangeStepValue(double d2) {
        this.rangeStepValue = d2;
    }

    public synchronized void setRangeTopMax(Number number) {
        this.rangeTopMax = number;
    }

    public synchronized void setRangeTopMin(Number number) {
        this.rangeTopMin = number;
    }

    public synchronized void setRangeUpperBoundary(Number number, BoundaryMode boundaryMode) {
        if (boundaryMode != BoundaryMode.FIXED) {
            number = null;
        }
        setUserMaxY(number);
        setRangeUpperBoundaryMode(boundaryMode);
        setRangeFramingModel(XYFramingModel.EDGE);
    }

    protected synchronized void setRangeUpperBoundaryMode(BoundaryMode boundaryMode) {
        this.rangeUpperBoundaryMode = boundaryMode;
    }

    public void setRangeValueFormat(Format format) {
        this.graphWidget.setRangeValueFormat(format);
    }

    public void setTicksPerDomainLabel(int i) {
        this.graphWidget.setTicksPerDomainLabel(i);
    }

    public void setTicksPerRangeLabel(int i) {
        this.graphWidget.setTicksPerRangeLabel(i);
    }

    public synchronized void setUserDomainOrigin(Number number) {
        if (number != null) {
            this.userDomainOrigin = number;
        } else {
            throw new NullPointerException("Origin value cannot be null.");
        }
    }

    protected synchronized void setUserMaxX(Number number) {
        this.userMaxX = number;
    }

    protected synchronized void setUserMaxY(Number number) {
        this.userMaxY = number;
    }

    protected synchronized void setUserMinX(Number number) {
        this.userMinX = number;
    }

    protected synchronized void setUserMinY(Number number) {
        this.userMinY = number;
    }

    public synchronized void setUserRangeOrigin(Number number) {
        if (number != null) {
            this.userRangeOrigin = number;
        } else {
            throw new NullPointerException("Origin value cannot be null.");
        }
    }

    public void updateDomainMinMaxForOriginModel() {
        double doubleValue = this.userDomainOrigin.doubleValue();
        double distance = distance(this.calculatedMaxX.doubleValue(), doubleValue);
        double distance2 = distance(this.calculatedMinX.doubleValue(), doubleValue);
        if (distance <= distance2) {
            distance = distance2;
        }
        double d2 = doubleValue - distance;
        double d3 = doubleValue + distance;
        int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$BoundaryMode[this.domainOriginBoundaryMode.ordinal()];
        if (i != 1) {
            if (i == 2) {
                this.calculatedMinX = Double.valueOf(d2);
                this.calculatedMaxX = Double.valueOf(d3);
                return;
            }
            if (i == 3) {
                Number number = this.prevMinX;
                if (number != null && d2 >= number.doubleValue()) {
                    this.calculatedMinX = this.prevMinX;
                } else {
                    this.calculatedMinX = Double.valueOf(d2);
                }
                Number number2 = this.prevMaxX;
                if (number2 != null && d3 <= number2.doubleValue()) {
                    this.calculatedMaxX = this.prevMaxX;
                    return;
                } else {
                    this.calculatedMaxX = Double.valueOf(d3);
                    return;
                }
            }
            if (i == 4) {
                Number number3 = this.prevMinX;
                if (number3 != null && d2 <= number3.doubleValue()) {
                    this.calculatedMinX = this.prevMinX;
                } else {
                    this.calculatedMinX = Double.valueOf(d2);
                }
                Number number4 = this.prevMaxX;
                if (number4 != null && d3 >= number4.doubleValue()) {
                    this.calculatedMaxX = this.prevMaxX;
                    return;
                } else {
                    this.calculatedMaxX = Double.valueOf(d3);
                    return;
                }
            }
            throw new UnsupportedOperationException("Domain Origin Boundary Mode not yet supported: " + this.domainOriginBoundaryMode);
        }
    }

    public void updateRangeMinMaxForEdgeModel() {
        int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$BoundaryMode[this.rangeUpperBoundaryMode.ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3) {
                if (i == 4) {
                    if (this.prevMaxY != null && this.calculatedMaxY.doubleValue() >= this.prevMaxY.doubleValue()) {
                        this.calculatedMaxY = this.prevMaxY;
                    }
                } else {
                    throw new UnsupportedOperationException("RangeUpperBoundaryMode not supported: " + this.rangeUpperBoundaryMode);
                }
            } else if (this.prevMaxY != null && this.calculatedMaxY.doubleValue() <= this.prevMaxY.doubleValue()) {
                this.calculatedMaxY = this.prevMaxY;
            }
        }
        int i2 = AnonymousClass1.$SwitchMap$com$androidplot$xy$BoundaryMode[this.rangeLowerBoundaryMode.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return;
        }
        if (i2 == 3) {
            if (this.prevMinY == null || this.calculatedMinY.doubleValue() < this.prevMinY.doubleValue()) {
                return;
            }
            this.calculatedMinY = this.prevMinY;
            return;
        }
        if (i2 == 4) {
            if (this.prevMinY == null || this.calculatedMinY.doubleValue() > this.prevMinY.doubleValue()) {
                return;
            }
            this.calculatedMinY = this.prevMinY;
            return;
        }
        throw new UnsupportedOperationException("RangeLowerBoundaryMode not supported: " + this.rangeLowerBoundaryMode);
    }

    public void updateRangeMinMaxForOriginModel() {
        if (AnonymousClass1.$SwitchMap$com$androidplot$xy$BoundaryMode[this.rangeOriginBoundaryMode.ordinal()] == 2) {
            double doubleValue = this.userRangeOrigin.doubleValue();
            double distance = distance(this.calculatedMaxY.doubleValue(), doubleValue);
            double distance2 = distance(this.calculatedMinY.doubleValue(), doubleValue);
            if (distance > distance2) {
                this.calculatedMinY = Double.valueOf(doubleValue - distance);
                this.calculatedMaxY = Double.valueOf(doubleValue + distance);
                return;
            } else {
                this.calculatedMinY = Double.valueOf(doubleValue - distance2);
                this.calculatedMaxY = Double.valueOf(doubleValue + distance2);
                return;
            }
        }
        throw new UnsupportedOperationException("Range Origin Boundary Mode not yet supported: " + this.rangeOriginBoundaryMode);
    }

    public void centerOnDomainOrigin(Number number, Number number2, BoundaryMode boundaryMode) {
        if (number != null) {
            this.domainFramingModel = XYFramingModel.ORIGIN;
            setUserDomainOrigin(number);
            this.domainOriginExtent = number2;
            this.domainOriginBoundaryMode = boundaryMode;
            if (this.domainOriginBoundaryMode == BoundaryMode.FIXED) {
                double doubleValue = this.userDomainOrigin.doubleValue();
                double doubleValue2 = this.domainOriginExtent.doubleValue();
                this.userMaxX = Double.valueOf(doubleValue + doubleValue2);
                this.userMinX = Double.valueOf(doubleValue - doubleValue2);
                return;
            }
            this.userMaxX = null;
            this.userMinX = null;
            return;
        }
        throw new NullPointerException("Origin param cannot be null.");
    }

    public void centerOnRangeOrigin(Number number, Number number2, BoundaryMode boundaryMode) {
        if (number != null) {
            this.rangeFramingModel = XYFramingModel.ORIGIN;
            setUserRangeOrigin(number);
            this.rangeOriginExtent = number2;
            this.rangeOriginBoundaryMode = boundaryMode;
            if (this.rangeOriginBoundaryMode == BoundaryMode.FIXED) {
                double doubleValue = this.userRangeOrigin.doubleValue();
                double doubleValue2 = this.rangeOriginExtent.doubleValue();
                this.userMaxY = Double.valueOf(doubleValue + doubleValue2);
                this.userMinY = Double.valueOf(doubleValue - doubleValue2);
                return;
            }
            this.userMaxY = null;
            this.userMinY = null;
            return;
        }
        throw new NullPointerException("Origin param cannot be null.");
    }

    public void setCursorPosition(float f2, float f3) {
        getGraphWidget().setCursorPosition(f2, f3);
    }

    public boolean addMarker(XValueMarker xValueMarker) {
        return !this.xValueMarkers.contains(xValueMarker) && this.xValueMarkers.add(xValueMarker);
    }

    public boolean containsPoint(PointF pointF) {
        return containsPoint(pointF.x, pointF.y);
    }

    public XValueMarker removeMarker(XValueMarker xValueMarker) {
        int indexOf = this.xValueMarkers.indexOf(xValueMarker);
        if (indexOf == -1) {
            return null;
        }
        return this.xValueMarkers.remove(indexOf);
    }

    public synchronized void setDomainBoundaries(Number number, BoundaryMode boundaryMode, Number number2, BoundaryMode boundaryMode2) {
        setDomainLowerBoundary(number, boundaryMode);
        setDomainUpperBoundary(number2, boundaryMode2);
    }

    public synchronized void setRangeBoundaries(Number number, BoundaryMode boundaryMode, Number number2, BoundaryMode boundaryMode2) {
        setRangeLowerBoundary(number, boundaryMode);
        setRangeUpperBoundary(number2, boundaryMode2);
    }

    public XYPlot(Context context, String str, Plot.RenderMode renderMode) {
        super(context, str, renderMode);
        XYStepMode xYStepMode = XYStepMode.SUBDIVIDE;
        this.domainStepMode = xYStepMode;
        this.domainStepValue = 10.0d;
        this.rangeStepMode = xYStepMode;
        this.rangeStepValue = 10.0d;
        this.rangeTopMin = null;
        this.rangeTopMax = null;
        this.rangeBottomMin = null;
        this.rangeBottomMax = null;
        this.domainLeftMin = null;
        this.domainLeftMax = null;
        this.domainRightMin = null;
        this.domainRightMax = null;
        XYFramingModel xYFramingModel = XYFramingModel.EDGE;
        this.domainFramingModel = xYFramingModel;
        this.rangeFramingModel = xYFramingModel;
        this.domainOriginExtent = null;
        this.rangeOriginExtent = null;
        BoundaryMode boundaryMode = BoundaryMode.AUTO;
        this.domainUpperBoundaryMode = boundaryMode;
        this.domainLowerBoundaryMode = boundaryMode;
        this.rangeUpperBoundaryMode = boundaryMode;
        this.rangeLowerBoundaryMode = boundaryMode;
        this.drawDomainOriginEnabled = true;
        this.drawRangeOriginEnabled = true;
    }

    public XYPlot(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        XYStepMode xYStepMode = XYStepMode.SUBDIVIDE;
        this.domainStepMode = xYStepMode;
        this.domainStepValue = 10.0d;
        this.rangeStepMode = xYStepMode;
        this.rangeStepValue = 10.0d;
        this.rangeTopMin = null;
        this.rangeTopMax = null;
        this.rangeBottomMin = null;
        this.rangeBottomMax = null;
        this.domainLeftMin = null;
        this.domainLeftMax = null;
        this.domainRightMin = null;
        this.domainRightMax = null;
        XYFramingModel xYFramingModel = XYFramingModel.EDGE;
        this.domainFramingModel = xYFramingModel;
        this.rangeFramingModel = xYFramingModel;
        this.domainOriginExtent = null;
        this.rangeOriginExtent = null;
        BoundaryMode boundaryMode = BoundaryMode.AUTO;
        this.domainUpperBoundaryMode = boundaryMode;
        this.domainLowerBoundaryMode = boundaryMode;
        this.rangeUpperBoundaryMode = boundaryMode;
        this.rangeLowerBoundaryMode = boundaryMode;
        this.drawDomainOriginEnabled = true;
        this.drawRangeOriginEnabled = true;
    }

    public XYPlot(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        XYStepMode xYStepMode = XYStepMode.SUBDIVIDE;
        this.domainStepMode = xYStepMode;
        this.domainStepValue = 10.0d;
        this.rangeStepMode = xYStepMode;
        this.rangeStepValue = 10.0d;
        this.rangeTopMin = null;
        this.rangeTopMax = null;
        this.rangeBottomMin = null;
        this.rangeBottomMax = null;
        this.domainLeftMin = null;
        this.domainLeftMax = null;
        this.domainRightMin = null;
        this.domainRightMax = null;
        XYFramingModel xYFramingModel = XYFramingModel.EDGE;
        this.domainFramingModel = xYFramingModel;
        this.rangeFramingModel = xYFramingModel;
        this.domainOriginExtent = null;
        this.rangeOriginExtent = null;
        BoundaryMode boundaryMode = BoundaryMode.AUTO;
        this.domainUpperBoundaryMode = boundaryMode;
        this.domainLowerBoundaryMode = boundaryMode;
        this.rangeUpperBoundaryMode = boundaryMode;
        this.rangeLowerBoundaryMode = boundaryMode;
        this.drawDomainOriginEnabled = true;
        this.drawRangeOriginEnabled = true;
    }
}
