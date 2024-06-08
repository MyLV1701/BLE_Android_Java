package com.androidplot.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.BoxModel;
import com.androidplot.ui.BoxModelable;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.PositionMetrics;
import com.androidplot.ui.Resizable;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetric;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.XLayoutStyle;
import com.androidplot.ui.YLayoutStyle;
import com.androidplot.util.DisplayDimensions;
import com.androidplot.util.PixelUtils;

/* loaded from: classes.dex */
public abstract class Widget implements BoxModelable, Resizable {
    private Paint backgroundPaint;
    private Paint borderPaint;
    private BoxModel boxModel;
    private boolean clippingEnabled;
    private boolean isVisible;
    private LayoutManager layoutManager;
    private DisplayDimensions plotDimensions;
    private PositionMetrics positionMetrics;
    private SizeMetrics sizeMetrics;
    private DisplayDimensions widgetDimensions;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.ui.widget.Widget$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$ui$AnchorPosition = new int[AnchorPosition.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.LEFT_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.LEFT_MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.LEFT_BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.RIGHT_TOP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.RIGHT_BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.RIGHT_MIDDLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.TOP_MIDDLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.BOTTOM_MIDDLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$androidplot$ui$AnchorPosition[AnchorPosition.CENTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    public Widget(LayoutManager layoutManager, SizeMetric sizeMetric, SizeMetric sizeMetric2) {
        this(layoutManager, new SizeMetrics(sizeMetric, sizeMetric2));
    }

    public static PointF getAnchorCoordinates(RectF rectF, AnchorPosition anchorPosition) {
        return PixelUtils.add(new PointF(rectF.left, rectF.top), getAnchorOffset(rectF.width(), rectF.height(), anchorPosition));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static PointF getAnchorOffset(float f2, float f3, AnchorPosition anchorPosition) {
        PointF pointF = new PointF();
        switch (AnonymousClass1.$SwitchMap$com$androidplot$ui$AnchorPosition[anchorPosition.ordinal()]) {
            case 1:
                return pointF;
            case 2:
                pointF.set(0.0f, f3 / 2.0f);
                return pointF;
            case 3:
                pointF.set(0.0f, f3);
                return pointF;
            case 4:
                pointF.set(f2, 0.0f);
                return pointF;
            case 5:
                pointF.set(f2, f3);
                return pointF;
            case 6:
                pointF.set(f2, f3 / 2.0f);
                return pointF;
            case 7:
                pointF.set(f2 / 2.0f, 0.0f);
                return pointF;
            case 8:
                pointF.set(f2 / 2.0f, f3);
                return pointF;
            case 9:
                pointF.set(f2 / 2.0f, f3 / 2.0f);
                return pointF;
            default:
                throw new IllegalArgumentException("Unsupported anchor location: " + anchorPosition);
        }
    }

    public boolean containsPoint(PointF pointF) {
        return this.widgetDimensions.canvasRect.contains(pointF.x, pointF.y);
    }

    protected abstract void doOnDraw(Canvas canvas, RectF rectF);

    public void draw(Canvas canvas, RectF rectF) {
        if (isVisible()) {
            if (this.backgroundPaint != null) {
                drawBackground(canvas, this.widgetDimensions.canvasRect);
            }
            doOnDraw(canvas, this.widgetDimensions.paddedRect);
            if (this.borderPaint != null) {
                drawBorder(canvas, this.widgetDimensions.paddedRect);
            }
        }
    }

    protected void drawBackground(Canvas canvas, RectF rectF) {
        canvas.drawRect(rectF, this.backgroundPaint);
    }

    protected void drawBorder(Canvas canvas, RectF rectF) {
        canvas.drawRect(rectF, this.borderPaint);
    }

    public AnchorPosition getAnchor() {
        return getPositionMetrics().getAnchor();
    }

    public Paint getBackgroundPaint() {
        return this.backgroundPaint;
    }

    public Paint getBorderPaint() {
        return this.borderPaint;
    }

    public PointF getElementCoordinates(float f2, float f3, RectF rectF, PositionMetrics positionMetrics) {
        return PixelUtils.sub(new PointF(positionMetrics.getXPositionMetric().getPixelValue(rectF.width()) + rectF.left, positionMetrics.getYPositionMetric().getPixelValue(rectF.height()) + rectF.top), getAnchorOffset(f3, f2, positionMetrics.getAnchor()));
    }

    public SizeMetric getHeightMetric() {
        return this.sizeMetrics.getHeightMetric();
    }

    public float getHeightPix(float f2) {
        return this.sizeMetrics.getHeightMetric().getPixelValue(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginBottom() {
        return this.boxModel.getMarginBottom();
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginLeft() {
        return this.boxModel.getMarginLeft();
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginRight() {
        return this.boxModel.getMarginRight();
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginTop() {
        return this.boxModel.getMarginTop();
    }

    @Override // com.androidplot.ui.BoxModelable
    public RectF getMarginatedRect(RectF rectF) {
        return this.boxModel.getMarginatedRect(rectF);
    }

    @Override // com.androidplot.ui.BoxModelable
    public RectF getPaddedRect(RectF rectF) {
        return this.boxModel.getPaddedRect(rectF);
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingBottom() {
        return this.boxModel.getPaddingBottom();
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingLeft() {
        return this.boxModel.getPaddingLeft();
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingRight() {
        return this.boxModel.getPaddingRight();
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingTop() {
        return this.boxModel.getPaddingTop();
    }

    public PositionMetrics getPositionMetrics() {
        return this.positionMetrics;
    }

    public DisplayDimensions getWidgetDimensions() {
        return this.widgetDimensions;
    }

    public SizeMetric getWidthMetric() {
        return this.sizeMetrics.getWidthMetric();
    }

    public float getWidthPix(float f2) {
        return this.sizeMetrics.getWidthMetric().getPixelValue(f2);
    }

    public boolean isClippingEnabled() {
        return this.clippingEnabled;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @Override // com.androidplot.ui.Resizable
    public synchronized void layout(DisplayDimensions displayDimensions) {
        this.plotDimensions = displayDimensions;
        refreshLayout();
    }

    protected void onMetricsChanged(SizeMetrics sizeMetrics, SizeMetrics sizeMetrics2) {
    }

    public void onPostInit() {
    }

    public void position(float f2, XLayoutStyle xLayoutStyle, float f3, YLayoutStyle yLayoutStyle) {
        position(f2, xLayoutStyle, f3, yLayoutStyle, AnchorPosition.LEFT_TOP);
    }

    public synchronized void refreshLayout() {
        if (this.positionMetrics == null) {
            return;
        }
        float widthPix = getWidthPix(this.plotDimensions.paddedRect.width());
        float heightPix = getHeightPix(this.plotDimensions.paddedRect.height());
        PointF elementCoordinates = getElementCoordinates(heightPix, widthPix, this.plotDimensions.paddedRect, this.positionMetrics);
        RectF rectF = new RectF(elementCoordinates.x, elementCoordinates.y, elementCoordinates.x + widthPix, elementCoordinates.y + heightPix);
        RectF marginatedRect = getMarginatedRect(rectF);
        this.widgetDimensions = new DisplayDimensions(rectF, marginatedRect, getPaddedRect(marginatedRect));
    }

    public void setAnchor(AnchorPosition anchorPosition) {
        getPositionMetrics().setAnchor(anchorPosition);
    }

    public void setBackgroundPaint(Paint paint) {
        this.backgroundPaint = paint;
    }

    public void setBorderPaint(Paint paint) {
        this.borderPaint = paint;
    }

    public void setClippingEnabled(boolean z) {
        this.clippingEnabled = z;
    }

    public void setHeight(float f2) {
        this.sizeMetrics.getHeightMetric().setValue(f2);
    }

    public void setMarginBottom(float f2) {
        this.boxModel.setMarginBottom(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginLeft(float f2) {
        this.boxModel.setMarginLeft(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginRight(float f2) {
        this.boxModel.setMarginRight(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginTop(float f2) {
        this.boxModel.setMarginTop(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMargins(float f2, float f3, float f4, float f5) {
        this.boxModel.setMargins(f2, f3, f4, f5);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPadding(float f2, float f3, float f4, float f5) {
        this.boxModel.setPadding(f2, f3, f4, f5);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingBottom(float f2) {
        this.boxModel.setPaddingBottom(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingLeft(float f2) {
        this.boxModel.setPaddingLeft(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingRight(float f2) {
        this.boxModel.setPaddingRight(f2);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingTop(float f2) {
        this.boxModel.setPaddingTop(f2);
    }

    public void setPositionMetrics(PositionMetrics positionMetrics) {
        this.positionMetrics = positionMetrics;
    }

    public void setSize(SizeMetrics sizeMetrics) {
        this.sizeMetrics = sizeMetrics;
    }

    public void setVisible(boolean z) {
        this.isVisible = z;
    }

    public void setWidth(float f2) {
        this.sizeMetrics.getWidthMetric().setValue(f2);
    }

    public Widget(LayoutManager layoutManager, SizeMetrics sizeMetrics) {
        this.clippingEnabled = true;
        this.boxModel = new BoxModel();
        this.plotDimensions = new DisplayDimensions();
        this.widgetDimensions = new DisplayDimensions();
        this.isVisible = true;
        this.layoutManager = layoutManager;
        SizeMetrics sizeMetrics2 = this.sizeMetrics;
        setSize(sizeMetrics);
        onMetricsChanged(sizeMetrics2, sizeMetrics);
    }

    public void position(float f2, XLayoutStyle xLayoutStyle, float f3, YLayoutStyle yLayoutStyle, AnchorPosition anchorPosition) {
        setPositionMetrics(new PositionMetrics(f2, xLayoutStyle, f3, yLayoutStyle, anchorPosition));
        this.layoutManager.addToTop(this);
    }

    public void setHeight(float f2, SizeLayoutType sizeLayoutType) {
        this.sizeMetrics.getHeightMetric().set(f2, sizeLayoutType);
    }

    public void setWidth(float f2, SizeLayoutType sizeLayoutType) {
        this.sizeMetrics.getWidthMetric().set(f2, sizeLayoutType);
    }

    public static PointF getAnchorCoordinates(float f2, float f3, float f4, float f5, AnchorPosition anchorPosition) {
        return getAnchorCoordinates(new RectF(f2, f3, f4 + f2, f5 + f3), anchorPosition);
    }
}
