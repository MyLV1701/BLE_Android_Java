package com.androidplot.xy;

import android.graphics.RectF;
import com.androidplot.util.ValPixConverter;

/* loaded from: classes.dex */
public class XYStepCalculator {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.xy.XYStepCalculator$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$xy$XYAxisType;
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$xy$XYStepMode = new int[XYStepMode.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$xy$XYStepMode[XYStepMode.INCREMENT_BY_VAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$xy$XYStepMode[XYStepMode.INCREMENT_BY_PIXELS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$xy$XYStepMode[XYStepMode.SUBDIVIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$androidplot$xy$XYAxisType = new int[XYAxisType.values().length];
            try {
                $SwitchMap$com$androidplot$xy$XYAxisType[XYAxisType.DOMAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$androidplot$xy$XYAxisType[XYAxisType.RANGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static XYStep getStep(XYPlot xYPlot, XYAxisType xYAxisType, RectF rectF, Number number, Number number2) {
        int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$XYAxisType[xYAxisType.ordinal()];
        if (i == 1) {
            return getStep(xYPlot.getDomainStepMode(), rectF.width(), xYPlot.getDomainStepValue(), number, number2);
        }
        if (i != 2) {
            return null;
        }
        return getStep(xYPlot.getRangeStepMode(), rectF.height(), xYPlot.getRangeStepValue(), number, number2);
    }

    public static XYStep getStep(XYStepMode xYStepMode, float f2, double d2, Number number, Number number2) {
        float f3;
        int i = AnonymousClass1.$SwitchMap$com$androidplot$xy$XYStepMode[xYStepMode.ordinal()];
        float f4 = 0.0f;
        if (i == 1) {
            f4 = (float) (d2 / ValPixConverter.valPerPix(number.doubleValue(), number2.doubleValue(), f2));
            f3 = f2 / f4;
        } else if (i == 2) {
            f4 = new Double(d2).floatValue();
            f3 = f2 / f4;
            double valPerPix = ValPixConverter.valPerPix(number.doubleValue(), number2.doubleValue(), f2);
            double d3 = f4;
            Double.isNaN(d3);
            d2 = valPerPix * d3;
        } else if (i != 3) {
            d2 = 0.0d;
            f3 = 0.0f;
        } else {
            float floatValue = new Double(d2).floatValue();
            float f5 = f2 / (floatValue - 1.0f);
            double valPerPix2 = ValPixConverter.valPerPix(number.doubleValue(), number2.doubleValue(), f2);
            double d4 = f5;
            Double.isNaN(d4);
            d2 = valPerPix2 * d4;
            f4 = f5;
            f3 = floatValue;
        }
        return new XYStep(f3, f4, d2);
    }
}
