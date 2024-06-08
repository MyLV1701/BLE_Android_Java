package com.androidplot.xy;

import android.content.Context;
import android.graphics.Paint;
import com.androidplot.util.Configurator;

/* loaded from: classes.dex */
public class XYRegionFormatter {
    private Paint paint = new Paint();

    public XYRegionFormatter(Context context, int i) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        if (XYRegionFormatter.class.equals(XYRegionFormatter.class)) {
            Configurator.configure(context, this, i);
        }
    }

    public int getColor() {
        return this.paint.getColor();
    }

    public Paint getPaint() {
        return this.paint;
    }

    public void setColor(int i) {
        this.paint.setColor(i);
    }

    public XYRegionFormatter(int i) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        this.paint.setColor(i);
    }
}
