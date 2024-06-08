package com.androidplot.ui;

import android.content.Context;
import com.androidplot.Plot;
import com.androidplot.util.Configurator;

/* loaded from: classes.dex */
public abstract class Formatter<PlotType extends Plot> {
    public Formatter<PlotType> configure(Context context, int i) {
        Configurator.configure(context, this, i);
        return this;
    }

    public abstract Class<? extends SeriesRenderer> getRendererClass();

    public abstract SeriesRenderer getRendererInstance(PlotType plottype);
}
