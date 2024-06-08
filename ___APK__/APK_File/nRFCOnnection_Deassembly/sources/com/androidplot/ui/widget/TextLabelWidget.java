package com.androidplot.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.LayoutManager;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.TextOrientationType;
import com.androidplot.util.FontUtils;

/* loaded from: classes.dex */
public class TextLabelWidget extends Widget {
    private static final String TAG = "com.androidplot.ui.widget.TextLabelWidget";
    private boolean autoPackEnabled;
    private Paint labelPaint;
    private TextOrientationType orientation;
    private String text;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.ui.widget.TextLabelWidget$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$ui$TextOrientationType = new int[TextOrientationType.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$ui$TextOrientationType[TextOrientationType.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$ui$TextOrientationType[TextOrientationType.VERTICAL_ASCENDING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$ui$TextOrientationType[TextOrientationType.VERTICAL_DESCENDING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public TextLabelWidget(LayoutManager layoutManager, SizeMetrics sizeMetrics) {
        this(layoutManager, sizeMetrics, TextOrientationType.HORIZONTAL);
    }

    @Override // com.androidplot.ui.widget.Widget
    public void doOnDraw(Canvas canvas, RectF rectF) {
        String str = this.text;
        if (str == null || str.length() == 0) {
            return;
        }
        float f2 = this.labelPaint.getFontMetrics().descent;
        PointF anchorCoordinates = Widget.getAnchorCoordinates(rectF, AnchorPosition.CENTER);
        try {
            canvas.save();
            canvas.translate(anchorCoordinates.x, anchorCoordinates.y);
            int i = AnonymousClass1.$SwitchMap$com$androidplot$ui$TextOrientationType[this.orientation.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    canvas.rotate(-90.0f);
                } else if (i == 3) {
                    canvas.rotate(90.0f);
                } else {
                    throw new UnsupportedOperationException("Orientation " + this.orientation + " not yet implemented for TextLabelWidget.");
                }
            }
            canvas.drawText(this.text, 0.0f, f2, this.labelPaint);
        } finally {
            canvas.restore();
        }
    }

    public Paint getLabelPaint() {
        return this.labelPaint;
    }

    public TextOrientationType getOrientation() {
        return this.orientation;
    }

    public String getText() {
        return this.text;
    }

    public boolean isAutoPackEnabled() {
        return this.autoPackEnabled;
    }

    @Override // com.androidplot.ui.widget.Widget
    protected void onMetricsChanged(SizeMetrics sizeMetrics, SizeMetrics sizeMetrics2) {
        if (this.autoPackEnabled) {
            pack();
        }
    }

    @Override // com.androidplot.ui.widget.Widget
    public void onPostInit() {
        if (this.autoPackEnabled) {
            pack();
        }
    }

    public void pack() {
        if (FontUtils.getStringDimensions(this.text, getLabelPaint()) == null) {
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$androidplot$ui$TextOrientationType[this.orientation.ordinal()];
        if (i == 1) {
            setSize(new SizeMetrics(r0.height(), SizeLayoutType.ABSOLUTE, r0.width() + 2, SizeLayoutType.ABSOLUTE));
        } else if (i == 2 || i == 3) {
            setSize(new SizeMetrics(r0.width(), SizeLayoutType.ABSOLUTE, r0.height() + 2, SizeLayoutType.ABSOLUTE));
        }
        refreshLayout();
    }

    public void setAutoPackEnabled(boolean z) {
        this.autoPackEnabled = z;
        if (z) {
            pack();
        }
    }

    public void setLabelPaint(Paint paint) {
        this.labelPaint = paint;
        if (this.autoPackEnabled) {
            pack();
        }
    }

    public void setOrientation(TextOrientationType textOrientationType) {
        this.orientation = textOrientationType;
        if (this.autoPackEnabled) {
            pack();
        }
    }

    public void setText(String str) {
        this.text = str;
        if (this.autoPackEnabled) {
            pack();
        }
    }

    public TextLabelWidget(LayoutManager layoutManager, String str, SizeMetrics sizeMetrics, TextOrientationType textOrientationType) {
        this(layoutManager, sizeMetrics, textOrientationType);
        setText(str);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public TextLabelWidget(com.androidplot.ui.LayoutManager r4, com.androidplot.ui.SizeMetrics r5, com.androidplot.ui.TextOrientationType r6) {
        /*
            r3 = this;
            com.androidplot.ui.SizeMetrics r0 = new com.androidplot.ui.SizeMetrics
            com.androidplot.ui.SizeLayoutType r1 = com.androidplot.ui.SizeLayoutType.ABSOLUTE
            r2 = 0
            r0.<init>(r2, r1, r2, r1)
            r3.<init>(r4, r0)
            r4 = 1
            r3.autoPackEnabled = r4
            android.graphics.Paint r0 = new android.graphics.Paint
            r0.<init>()
            r3.labelPaint = r0
            android.graphics.Paint r0 = r3.labelPaint
            r1 = -1
            r0.setColor(r1)
            android.graphics.Paint r0 = r3.labelPaint
            r0.setAntiAlias(r4)
            android.graphics.Paint r4 = r3.labelPaint
            android.graphics.Paint$Align r0 = android.graphics.Paint.Align.CENTER
            r4.setTextAlign(r0)
            r3.setSize(r5)
            r3.orientation = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.androidplot.ui.widget.TextLabelWidget.<init>(com.androidplot.ui.LayoutManager, com.androidplot.ui.SizeMetrics, com.androidplot.ui.TextOrientationType):void");
    }
}
