package no.nordicsemi.android.mcp.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class RemovableRecyclerView extends RecyclerView {
    public RemovableRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public int getMinFlingVelocity() {
        return super.getMinFlingVelocity() * 10;
    }
}
