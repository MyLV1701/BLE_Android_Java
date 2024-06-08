package com.androidplot.ui;

import android.graphics.RectF;

/* loaded from: classes.dex */
public class BoxModel implements BoxModelable {
    private float marginBottom;
    private float marginLeft;
    private float marginRight;
    private float marginTop;
    private float paddingBottom;
    private float paddingLeft;
    private float paddingRight;
    private float paddingTop;

    public BoxModel() {
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginBottom() {
        return this.marginBottom;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginLeft() {
        return this.marginLeft;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginRight() {
        return this.marginRight;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getMarginTop() {
        return this.marginTop;
    }

    @Override // com.androidplot.ui.BoxModelable
    public RectF getMarginatedRect(RectF rectF) {
        return new RectF(rectF.left + getMarginLeft(), rectF.top + getMarginTop(), rectF.right - getMarginRight(), rectF.bottom - getMarginBottom());
    }

    @Override // com.androidplot.ui.BoxModelable
    public RectF getPaddedRect(RectF rectF) {
        return new RectF(rectF.left + getPaddingLeft(), rectF.top + getPaddingTop(), rectF.right - getPaddingRight(), rectF.bottom - getPaddingBottom());
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingBottom() {
        return this.paddingBottom;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingLeft() {
        return this.paddingLeft;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingRight() {
        return this.paddingRight;
    }

    @Override // com.androidplot.ui.BoxModelable
    public float getPaddingTop() {
        return this.paddingTop;
    }

    public void setMarginBottom(float f2) {
        this.marginBottom = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginLeft(float f2) {
        this.marginLeft = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginRight(float f2) {
        this.marginRight = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMarginTop(float f2) {
        this.marginTop = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setMargins(float f2, float f3, float f4, float f5) {
        setMarginLeft(f2);
        setMarginTop(f3);
        setMarginRight(f4);
        setMarginBottom(f5);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPadding(float f2, float f3, float f4, float f5) {
        setPaddingLeft(f2);
        setPaddingTop(f3);
        setPaddingRight(f4);
        setPaddingBottom(f5);
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingBottom(float f2) {
        this.paddingBottom = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingLeft(float f2) {
        this.paddingLeft = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingRight(float f2) {
        this.paddingRight = f2;
    }

    @Override // com.androidplot.ui.BoxModelable
    public void setPaddingTop(float f2) {
        this.paddingTop = f2;
    }

    public BoxModel(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.marginLeft = f2;
        this.marginTop = f3;
        this.marginRight = f4;
        this.marginBottom = f5;
        this.paddingLeft = f6;
        this.paddingTop = f7;
        this.paddingRight = f8;
        this.paddingBottom = f9;
    }
}
