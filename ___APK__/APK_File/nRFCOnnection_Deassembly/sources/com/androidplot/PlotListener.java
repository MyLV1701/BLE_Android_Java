package com.androidplot;

import android.graphics.Canvas;

/* loaded from: classes.dex */
public interface PlotListener {
    void onAfterDraw(Plot plot, Canvas canvas);

    void onBeforeDraw(Plot plot, Canvas canvas);
}
