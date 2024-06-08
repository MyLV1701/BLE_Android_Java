package no.nordicsemi.android.mcp.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class RemovableViewHolder extends RecyclerView.d0 {
    private View mRemoveableView;

    public RemovableViewHolder(View view) {
        super(view);
        this.mRemoveableView = view.findViewById(R.id.removable);
    }

    public View getSwipeableView() {
        return this.mRemoveableView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isRemovable() {
        return true;
    }
}
