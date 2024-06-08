package com.androidplot.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.j;
import com.androidplot.ui.widget.Widget;
import com.androidplot.util.DisplayDimensions;
import com.androidplot.util.ZLinkedList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class LayoutManager extends ZLinkedList<Widget> implements View.OnTouchListener, Resizable {
    private Paint marginPaint;
    private Paint outlinePaint;
    private Paint outlineShadowPaint;
    private Paint paddingPaint;
    private boolean drawAnchorsEnabled = false;
    private boolean drawOutlinesEnabled = false;
    private boolean drawOutlineShadowsEnabled = false;
    private boolean drawMarginsEnabled = false;
    private boolean drawPaddingEnabled = false;
    private DisplayDimensions displayDims = new DisplayDimensions();
    private Paint anchorPaint = new Paint();

    public LayoutManager() {
        this.anchorPaint.setStyle(Paint.Style.FILL);
        this.anchorPaint.setColor(-16711936);
        this.outlinePaint = new Paint();
        this.outlinePaint.setColor(-16711936);
        this.outlinePaint.setStyle(Paint.Style.STROKE);
        this.marginPaint = new Paint();
        this.marginPaint.setColor(-256);
        this.marginPaint.setStyle(Paint.Style.FILL);
        this.marginPaint.setAlpha(j.f.DEFAULT_DRAG_ANIMATION_DURATION);
        this.paddingPaint = new Paint();
        this.paddingPaint.setColor(-16776961);
        this.paddingPaint.setStyle(Paint.Style.FILL);
        this.paddingPaint.setAlpha(j.f.DEFAULT_DRAG_ANIMATION_DURATION);
    }

    private void drawSpacing(Canvas canvas, RectF rectF, RectF rectF2, Paint paint) {
        try {
            canvas.save();
            canvas.clipRect(rectF2, Region.Op.DIFFERENCE);
            canvas.drawRect(rectF, paint);
        } finally {
            canvas.restore();
        }
    }

    public void draw(Canvas canvas) {
        if (isDrawMarginsEnabled()) {
            DisplayDimensions displayDimensions = this.displayDims;
            drawSpacing(canvas, displayDimensions.canvasRect, displayDimensions.marginatedRect, this.marginPaint);
        }
        if (isDrawPaddingEnabled()) {
            DisplayDimensions displayDimensions2 = this.displayDims;
            drawSpacing(canvas, displayDimensions2.marginatedRect, displayDimensions2.paddedRect, this.paddingPaint);
        }
        for (Widget widget : elements()) {
            try {
                canvas.save();
                PositionMetrics positionMetrics = widget.getPositionMetrics();
                float widthPix = widget.getWidthPix(this.displayDims.paddedRect.width());
                float heightPix = widget.getHeightPix(this.displayDims.paddedRect.height());
                PointF elementCoordinates = widget.getElementCoordinates(heightPix, widthPix, this.displayDims.paddedRect, positionMetrics);
                DisplayDimensions widgetDimensions = widget.getWidgetDimensions();
                if (this.drawOutlineShadowsEnabled) {
                    canvas.drawRect(widgetDimensions.canvasRect, this.outlineShadowPaint);
                }
                if (widget.isClippingEnabled()) {
                    canvas.clipRect(widgetDimensions.canvasRect, Region.Op.INTERSECT);
                }
                widget.draw(canvas, widgetDimensions.canvasRect);
                if (this.drawMarginsEnabled) {
                    drawSpacing(canvas, widgetDimensions.canvasRect, widgetDimensions.marginatedRect, getMarginPaint());
                }
                if (this.drawPaddingEnabled) {
                    drawSpacing(canvas, widgetDimensions.marginatedRect, widgetDimensions.paddedRect, getPaddingPaint());
                }
                if (this.drawAnchorsEnabled) {
                    drawAnchor(canvas, Widget.getAnchorCoordinates(elementCoordinates.x, elementCoordinates.y, widthPix, heightPix, positionMetrics.getAnchor()));
                }
                if (this.drawOutlinesEnabled) {
                    this.outlinePaint.setAntiAlias(true);
                    canvas.drawRect(widgetDimensions.canvasRect, this.outlinePaint);
                }
            } finally {
                canvas.restore();
            }
        }
    }

    protected void drawAnchor(Canvas canvas, PointF pointF) {
        float f2 = pointF.x;
        float f3 = pointF.y;
        canvas.drawRect(f2 - 4.0f, f3 - 4.0f, f2 + 4.0f, f3 + 4.0f, this.anchorPaint);
    }

    public Paint getMarginPaint() {
        return this.marginPaint;
    }

    public Paint getOutlinePaint() {
        return this.outlinePaint;
    }

    public Paint getOutlineShadowPaint() {
        return this.outlineShadowPaint;
    }

    public Paint getPaddingPaint() {
        return this.paddingPaint;
    }

    public boolean isDrawAnchorsEnabled() {
        return this.drawAnchorsEnabled;
    }

    public boolean isDrawMarginsEnabled() {
        return this.drawMarginsEnabled;
    }

    public boolean isDrawOutlineShadowsEnabled() {
        return this.drawOutlineShadowsEnabled;
    }

    public boolean isDrawOutlinesEnabled() {
        return this.drawOutlinesEnabled;
    }

    public boolean isDrawPaddingEnabled() {
        return this.drawPaddingEnabled;
    }

    @Override // com.androidplot.ui.Resizable
    public void layout(DisplayDimensions displayDimensions) {
        this.displayDims = displayDimensions;
        refreshLayout();
    }

    public synchronized void onPostInit() {
        Iterator<Widget> it = elements().iterator();
        while (it.hasNext()) {
            it.next().onPostInit();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    public void refreshLayout() {
        Iterator<Widget> it = elements().iterator();
        while (it.hasNext()) {
            it.next().layout(this.displayDims);
        }
    }

    public void setDrawAnchorsEnabled(boolean z) {
        this.drawAnchorsEnabled = z;
    }

    public void setDrawMarginsEnabled(boolean z) {
        this.drawMarginsEnabled = z;
    }

    public void setDrawOutlineShadowsEnabled(boolean z) {
        this.drawOutlineShadowsEnabled = z;
        if (z && this.outlineShadowPaint == null) {
            this.outlineShadowPaint = new Paint();
            this.outlineShadowPaint.setColor(-12303292);
            this.outlineShadowPaint.setStyle(Paint.Style.FILL);
            this.outlineShadowPaint.setShadowLayer(3.0f, 5.0f, 5.0f, -16777216);
        }
    }

    public void setDrawOutlinesEnabled(boolean z) {
        this.drawOutlinesEnabled = z;
    }

    public void setDrawPaddingEnabled(boolean z) {
        this.drawPaddingEnabled = z;
    }

    public void setMarginPaint(Paint paint) {
        this.marginPaint = paint;
    }

    public void setMarkupEnabled(boolean z) {
        setDrawOutlinesEnabled(z);
        setDrawAnchorsEnabled(z);
        setDrawMarginsEnabled(z);
        setDrawPaddingEnabled(z);
        setDrawOutlineShadowsEnabled(z);
    }

    public void setOutlinePaint(Paint paint) {
        this.outlinePaint = paint;
    }

    public void setOutlineShadowPaint(Paint paint) {
        this.outlineShadowPaint = paint;
    }

    public void setPaddingPaint(Paint paint) {
        this.paddingPaint = paint;
    }
}
