package no.nordicsemi.android.mcp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class RelativeLayoutWithMaxHeight extends RelativeLayout {
    private int mMaxHeight;

    public RelativeLayoutWithMaxHeight(Context context) {
        super(context);
        this.mMaxHeight = 1000000;
    }

    private void initFromAttributes(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ViewWithMaxHeight, i, 0);
        this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(0, 100000);
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(Math.min(this.mMaxHeight, View.MeasureSpec.getSize(i2)), View.MeasureSpec.getMode(i2)));
    }

    public RelativeLayoutWithMaxHeight(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxHeight = 1000000;
        initFromAttributes(context, attributeSet, 0);
    }

    public RelativeLayoutWithMaxHeight(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMaxHeight = 1000000;
        initFromAttributes(context, attributeSet, i);
    }
}
