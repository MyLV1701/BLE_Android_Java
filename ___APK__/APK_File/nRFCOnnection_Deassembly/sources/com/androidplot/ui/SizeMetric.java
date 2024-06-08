package com.androidplot.ui;

/* loaded from: classes.dex */
public class SizeMetric extends LayoutMetric<SizeLayoutType> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.androidplot.ui.SizeMetric$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$androidplot$ui$SizeLayoutType = new int[SizeLayoutType.values().length];

        static {
            try {
                $SwitchMap$com$androidplot$ui$SizeLayoutType[SizeLayoutType.RELATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$androidplot$ui$SizeLayoutType[SizeLayoutType.ABSOLUTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$androidplot$ui$SizeLayoutType[SizeLayoutType.FILL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public SizeMetric(float f2, SizeLayoutType sizeLayoutType) {
        super(f2, sizeLayoutType);
    }

    @Override // com.androidplot.ui.LayoutMetric
    public float getPixelValue(float f2) {
        int i = AnonymousClass1.$SwitchMap$com$androidplot$ui$SizeLayoutType[getLayoutType().ordinal()];
        if (i == 1) {
            return getValue() * f2;
        }
        if (i == 2) {
            return getValue();
        }
        if (i == 3) {
            return f2 - getValue();
        }
        throw new IllegalArgumentException("Unsupported LayoutType: " + getLayoutType());
    }

    @Override // com.androidplot.ui.LayoutMetric
    public /* bridge */ /* synthetic */ float getValue() {
        return super.getValue();
    }

    @Override // com.androidplot.ui.LayoutMetric
    public /* bridge */ /* synthetic */ void setValue(float f2) {
        super.setValue(f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.androidplot.ui.LayoutMetric
    public void validatePair(float f2, SizeLayoutType sizeLayoutType) {
        if (AnonymousClass1.$SwitchMap$com$androidplot$ui$SizeLayoutType[sizeLayoutType.ordinal()] != 1) {
            return;
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("SizeMetric Relative and Hybrid layout values must be within the range of 0 to 1.");
        }
    }
}
