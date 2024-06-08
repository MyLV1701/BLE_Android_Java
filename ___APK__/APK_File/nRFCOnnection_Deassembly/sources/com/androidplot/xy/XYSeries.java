package com.androidplot.xy;

import com.androidplot.Series;
import java.util.List;

/* loaded from: classes.dex */
public interface XYSeries extends Series {
    Integer getX(int i);

    Float getY(int i);

    List<Float> getY();

    int size();
}
