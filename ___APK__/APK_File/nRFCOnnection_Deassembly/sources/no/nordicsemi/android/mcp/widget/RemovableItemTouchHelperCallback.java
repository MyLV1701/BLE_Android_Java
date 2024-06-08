package no.nordicsemi.android.mcp.widget;

import android.graphics.Canvas;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j;

/* loaded from: classes.dex */
public class RemovableItemTouchHelperCallback extends j.f {
    private final ItemTouchHelperAdapter mAdapter;

    public RemovableItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.mAdapter = itemTouchHelperAdapter;
    }

    @Override // androidx.recyclerview.widget.j.f
    public void clearView(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
        j.f.getDefaultUIUtil().a(((RemovableViewHolder) d0Var).getSwipeableView());
    }

    @Override // androidx.recyclerview.widget.j.f
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.d0 d0Var) {
        if ((d0Var instanceof RemovableViewHolder) && ((RemovableViewHolder) d0Var).isRemovable()) {
            return j.f.makeMovementFlags(0, 48);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.j.f
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.j.f
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, float f2, float f3, int i, boolean z) {
        j.f.getDefaultUIUtil().b(canvas, recyclerView, ((RemovableViewHolder) d0Var).getSwipeableView(), f2, f3, i, z);
    }

    @Override // androidx.recyclerview.widget.j.f
    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.d0 d0Var, float f2, float f3, int i, boolean z) {
        j.f.getDefaultUIUtil().a(canvas, recyclerView, ((RemovableViewHolder) d0Var).getSwipeableView(), f2, f3, i, z);
    }

    @Override // androidx.recyclerview.widget.j.f
    public boolean onMove(RecyclerView recyclerView, RecyclerView.d0 d0Var, RecyclerView.d0 d0Var2) {
        return true;
    }

    @Override // androidx.recyclerview.widget.j.f
    public void onSelectedChanged(RecyclerView.d0 d0Var, int i) {
        if (d0Var != null) {
            j.f.getDefaultUIUtil().b(((RemovableViewHolder) d0Var).getSwipeableView());
        }
    }

    @Override // androidx.recyclerview.widget.j.f
    public void onSwiped(RecyclerView.d0 d0Var, int i) {
        this.mAdapter.onItemDismiss((RemovableViewHolder) d0Var);
    }
}
