package no.nordicsemi.android.mcp.advertiser;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeAdvertiser;
import no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter;
import no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter;
import no.nordicsemi.android.mcp.widget.RemovableViewHolder;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.advertiser.AdvertisementView;

/* loaded from: classes.dex */
public class AdvertisementAdapter extends CursorRecyclerAdapter<AdvertisementViewHolder> implements ItemTouchHelperAdapter {
    private static final String ANIMATORS = "animators";
    private AdvertiserActionListener mActionListener;
    private final ViewAnimator.ExpandCollapseListener mExpandCollapseListener;
    private IBluetoothLeAdvertiser mService;
    private Map<String, ViewAnimator> mViewAnimators;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class AdvertisementViewHolder extends RemovableViewHolder {
        private AdvertisementView advertisementView;
        private ViewAnimator animator;

        AdvertisementViewHolder(AdvertisementView advertisementView) {
            super(advertisementView);
            this.advertisementView = advertisementView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvertisementAdapter(Cursor cursor, AdvertiserActionListener advertiserActionListener, AdvertiserFragment advertiserFragment, Bundle bundle) {
        super(cursor);
        Parcelable[] parcelableArray;
        this.mActionListener = advertiserActionListener;
        this.mExpandCollapseListener = advertiserFragment;
        this.mViewAnimators = new HashMap();
        setHasStableIds(true);
        if (bundle == null || (parcelableArray = bundle.getParcelableArray(ANIMATORS)) == null || parcelableArray.length <= 0) {
            return;
        }
        for (Parcelable parcelable : parcelableArray) {
            ViewAnimator viewAnimator = (ViewAnimator) parcelable;
            this.mViewAnimators.put(viewAnimator.getViewId(), viewAnimator);
        }
    }

    public void onDestroy() {
        this.mActionListener = null;
        this.mService = null;
    }

    @Override // no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter
    public void onItemDismiss(RemovableViewHolder removableViewHolder) {
        this.mActionListener.onRemoveAdvertisement(removableViewHolder.getItemId(), removableViewHolder.getAdapterPosition());
        notifyItemRemoved(removableViewHolder.getAdapterPosition());
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArray(ANIMATORS, (ViewAnimator[]) this.mViewAnimators.values().toArray(new ViewAnimator[this.mViewAnimators.size()]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBluetoothLeAdvertiser(IBluetoothLeAdvertiser iBluetoothLeAdvertiser) {
        this.mService = iBluetoothLeAdvertiser;
        notifyDataSetChanged();
    }

    @Override // no.nordicsemi.android.mcp.widget.CursorRecyclerAdapter
    public void onBindViewHolderCursor(AdvertisementViewHolder advertisementViewHolder, Cursor cursor) {
        String valueOf = String.valueOf(cursor.getLong(0));
        ViewAnimator viewAnimator = this.mViewAnimators.get(valueOf);
        if (viewAnimator == null) {
            viewAnimator = new ViewAnimator(valueOf);
            this.mViewAnimators.put(valueOf, viewAnimator);
        }
        viewAnimator.setExpandCollapseListener(this.mExpandCollapseListener, cursor.getPosition());
        advertisementViewHolder.animator = viewAnimator;
        advertisementViewHolder.advertisementView.setData(cursor, viewAnimator);
        advertisementViewHolder.advertisementView.setBluetoothLeAdvertiser(this.mService);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public AdvertisementViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AdvertisementView advertisementView = (AdvertisementView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.advertiser_list_item, viewGroup, false);
        advertisementView.setActionListener(this.mActionListener);
        return new AdvertisementViewHolder(advertisementView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewAttachedToWindow(AdvertisementViewHolder advertisementViewHolder) {
        advertisementViewHolder.advertisementView.onViewAttached();
        advertisementViewHolder.animator.bindView(advertisementViewHolder.advertisementView, advertisementViewHolder.advertisementView.getDetailsView());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewDetachedFromWindow(AdvertisementViewHolder advertisementViewHolder) {
        advertisementViewHolder.advertisementView.onViewDetached();
        advertisementViewHolder.animator.unbindView();
    }
}
