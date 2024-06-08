package no.nordicsemi.android.mcp.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import java.util.Queue;
import no.nordicsemi.android.mcp.widget.RecycledPagerAdapter.ViewHolder;

/* loaded from: classes.dex */
public abstract class RecycledPagerAdapter<VH extends ViewHolder> extends androidx.viewpager.widget.a {
    private final Queue<VH> destroyedItems = new LinkedList();

    /* loaded from: classes.dex */
    public static abstract class ViewHolder {
        final View itemView;

        public ViewHolder(View view) {
            this.itemView = view;
        }
    }

    @Override // androidx.viewpager.widget.a
    public final void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ViewHolder viewHolder = (ViewHolder) obj;
        viewGroup.removeView(viewHolder.itemView);
        this.destroyedItems.add(viewHolder);
    }

    @Override // androidx.viewpager.widget.a
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        VH poll = this.destroyedItems.poll();
        if (poll != null) {
            viewGroup.addView(poll.itemView);
            onBindViewHolder(poll, i);
            return poll;
        }
        VH onCreateViewHolder = onCreateViewHolder(viewGroup);
        onBindViewHolder(onCreateViewHolder, i);
        viewGroup.addView(onCreateViewHolder.itemView);
        return onCreateViewHolder;
    }

    @Override // androidx.viewpager.widget.a
    public final boolean isViewFromObject(View view, Object obj) {
        return ((ViewHolder) obj).itemView == view;
    }

    public abstract void onBindViewHolder(VH vh, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup);
}
