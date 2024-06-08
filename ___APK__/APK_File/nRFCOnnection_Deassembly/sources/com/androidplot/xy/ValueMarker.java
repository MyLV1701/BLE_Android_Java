package com.androidplot.xy;

import android.graphics.Paint;
import com.androidplot.ui.PositionMetric;

/* loaded from: classes.dex */
public abstract class ValueMarker<PositionMetricType extends PositionMetric> {
    private Paint linePaint;
    private String text;
    private int textMargin;
    private TextOrientation textOrientation;
    private Paint textPaint;
    private PositionMetricType textPosition;
    private Number value;

    /* loaded from: classes.dex */
    public enum TextOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public ValueMarker(Number number, String str, PositionMetricType positionmetrictype) {
        this.textMargin = 2;
        this.linePaint = new Paint();
        this.linePaint.setColor(-65536);
        this.linePaint.setAntiAlias(true);
        this.linePaint.setStyle(Paint.Style.STROKE);
        this.textPaint = new Paint();
        this.textPaint.setAntiAlias(true);
        this.textPaint.setColor(-65536);
        this.value = number;
        this.textPosition = positionmetrictype;
        this.text = str;
    }

    public Paint getLinePaint() {
        return this.linePaint;
    }

    public String getText() {
        return this.text;
    }

    public int getTextMargin() {
        return this.textMargin;
    }

    public TextOrientation getTextOrientation() {
        return this.textOrientation;
    }

    public Paint getTextPaint() {
        return this.textPaint;
    }

    public PositionMetricType getTextPosition() {
        return this.textPosition;
    }

    public Number getValue() {
        return this.value;
    }

    public void setLinePaint(Paint paint) {
        this.linePaint = paint;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setTextMargin(int i) {
        this.textMargin = i;
    }

    public void setTextOrientation(TextOrientation textOrientation) {
        this.textOrientation = textOrientation;
    }

    public void setTextPaint(Paint paint) {
        this.textPaint = paint;
    }

    public void setTextPosition(PositionMetricType positionmetrictype) {
        this.textPosition = positionmetrictype;
    }

    public void setValue(Number number) {
        this.value = number;
    }

    public ValueMarker(Number number, String str, PositionMetricType positionmetrictype, Paint paint, Paint paint2) {
        this(number, str, positionmetrictype);
        this.linePaint = paint;
        this.textPaint = paint2;
    }

    public ValueMarker(Number number, String str, PositionMetricType positionmetrictype, int i, int i2) {
        this(number, str, positionmetrictype);
        this.linePaint.setColor(i);
        this.textPaint.setColor(i2);
    }
}
