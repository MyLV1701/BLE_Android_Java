package com.androidplot.ui;

import com.androidplot.ui.PositionMetric;

/* loaded from: classes.dex */
public class XPositionMetric extends PositionMetric<XLayoutStyle> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.ui.XPositionMetric$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$ui$XLayoutStyle = new int[XLayoutStyle.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$ui$XLayoutStyle[XLayoutStyle.ABSOLUTE_FROM_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$ui$XLayoutStyle[XLayoutStyle.ABSOLUTE_FROM_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$ui$XLayoutStyle[XLayoutStyle.ABSOLUTE_FROM_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$androidplot$ui$XLayoutStyle[XLayoutStyle.RELATIVE_TO_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$androidplot$ui$XLayoutStyle[XLayoutStyle.RELATIVE_TO_RIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$androidplot$ui$XLayoutStyle[XLayoutStyle.RELATIVE_TO_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public XPositionMetric(float f2, XLayoutStyle xLayoutStyle) {
        super(f2, xLayoutStyle);
        validatePair(f2, xLayoutStyle);
    }

    @Override // com.androidplot.ui.LayoutMetric
    public float getPixelValue(float f2) {
        switch (AnonymousClass1.$SwitchMap$com$androidplot$ui$XLayoutStyle[((XLayoutStyle) getLayoutType()).ordinal()]) {
            case 1:
                return getAbsolutePosition(f2, PositionMetric.Origin.FROM_BEGINING);
            case 2:
                return getAbsolutePosition(f2, PositionMetric.Origin.FROM_END);
            case 3:
                return getAbsolutePosition(f2, PositionMetric.Origin.FROM_CENTER);
            case 4:
                return getRelativePosition(f2, PositionMetric.Origin.FROM_BEGINING);
            case 5:
                return getRelativePosition(f2, PositionMetric.Origin.FROM_END);
            case 6:
                return getRelativePosition(f2, PositionMetric.Origin.FROM_CENTER);
            default:
                throw new IllegalArgumentException("Unsupported LayoutType: " + getLayoutType());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidplot.ui.LayoutMetric
    public void validatePair(float f2, XLayoutStyle xLayoutStyle) {
        switch (AnonymousClass1.$SwitchMap$com$androidplot$ui$XLayoutStyle[xLayoutStyle.ordinal()]) {
            case 1:
            case 2:
            case 3:
                PositionMetric.validateValue(f2, PositionMetric.LayoutMode.ABSOLUTE);
                return;
            case 4:
            case 5:
            case 6:
                PositionMetric.validateValue(f2, PositionMetric.LayoutMode.RELATIVE);
                return;
            default:
                return;
        }
    }
}
