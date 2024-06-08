package no.nordicsemi.android.mcp.server;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.server.domain.Characteristic;
import no.nordicsemi.android.mcp.server.domain.Descriptor;
import no.nordicsemi.android.mcp.server.domain.ServerConfiguration;
import no.nordicsemi.android.mcp.server.domain.Service;
import no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter;
import no.nordicsemi.android.mcp.widget.RemovableViewHolder;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.server.ServerCharacteristicView;
import no.nordicsemi.android.mcp.widget.server.ServerDescriptorView;
import no.nordicsemi.android.mcp.widget.server.ServerServiceView;

/* loaded from: classes.dex */
public class ServerServiceAdapter extends RecyclerView.g<RecyclerView.d0> implements ItemTouchHelperAdapter {
    private static final String ANIMATORS = "animators";
    private static final int TYPE_ADD_NEW_SERVICE = 1;
    private static final int TYPE_NORMAL = 0;
    private ServerConfiguration mConfiguration;
    private final ConfigurationListener mConfigurationListener;
    private final DatabaseHelper mDatabaseHelper;
    private ViewAnimator.ExpandCollapseListener mExpandCollapseListener;
    private View.OnClickListener mAddServiceListener = new View.OnClickListener() { // from class: no.nordicsemi.android.mcp.server.ServerServiceAdapter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ServerServiceAdapter.this.mConfigurationListener.onAddService();
        }
    };
    private Map<String, ViewAnimator> mViewAnimators = new HashMap();

    /* loaded from: classes.dex */
    public static class AddServiceViewHolder extends RecyclerView.d0 {
        private View addServiceAction;

        AddServiceViewHolder(View view) {
            super(view);
            this.addServiceAction = view.findViewById(R.id.action_add);
        }
    }

    /* loaded from: classes.dex */
    public interface ConfigurationListener {
        void onAddCharacteristic(Service service);

        void onAddDescriptor(Characteristic characteristic);

        void onAddService();

        void onCharacteristicChanged(int i, int i2, String str, UUID uuid, int i3, int i4, String str2, String str3, boolean z);

        void onConfigurationChanged();

        void onDescriptorChanged(int i, int i2, String str, UUID uuid, int i3, String str2, String str3, boolean z);

        void onEditCharacteristic(Service service, Characteristic characteristic);

        void onEditDescriptor(Characteristic characteristic, Descriptor descriptor);

        void onEditService(Service service);

        void onPrepareConfigurationChange();

        void onServiceChanged(int i, Service.PredefinedService predefinedService, String str, UUID uuid, boolean z);
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RemovableViewHolder {
        private ViewAnimator animator;
        private ServerServiceView serviceView;

        public ViewHolder(ServerServiceView serverServiceView) {
            super(serverServiceView);
            this.serviceView = serverServiceView;
        }

        @Override // no.nordicsemi.android.mcp.widget.RemovableViewHolder
        public View getSwipeableView() {
            View swipedView = this.serviceView.getSwipedView();
            return swipedView != null ? swipedView : super.getSwipeableView();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerServiceAdapter(DatabaseHelper databaseHelper, ServerFragment serverFragment, Bundle bundle) {
        this.mDatabaseHelper = databaseHelper;
        this.mExpandCollapseListener = serverFragment;
        this.mConfigurationListener = serverFragment;
        if (bundle != null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray(ANIMATORS);
            for (Parcelable parcelable : parcelableArray) {
                ViewAnimator viewAnimator = (ViewAnimator) parcelable;
                this.mViewAnimators.put(viewAnimator.getViewId(), viewAnimator);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        ServerConfiguration serverConfiguration = this.mConfiguration;
        if (serverConfiguration != null) {
            return serverConfiguration.getServices().size() + 1;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        if (getItemViewType(i) == 0) {
            return this.mConfiguration.getServices().get(i).getInternalId();
        }
        return -9999L;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        return i < getItemCount() - 1 ? 0 : 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.d0 d0Var, int i) {
        if (d0Var instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) d0Var;
            Service service = this.mConfiguration.getServices().get(i);
            String str = "id:" + service.getInternalId();
            ViewAnimator viewAnimator = this.mViewAnimators.get(str);
            if (viewAnimator == null) {
                viewAnimator = new ViewAnimator(str);
                this.mViewAnimators.put(str, viewAnimator);
            }
            viewAnimator.setExpandCollapseListener(this.mExpandCollapseListener, i);
            viewHolder.animator = viewAnimator;
            viewHolder.serviceView.setService(this.mDatabaseHelper, service, viewAnimator);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.d0 onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 1) {
            ServerServiceView serverServiceView = (ServerServiceView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.server_service_item, viewGroup, false);
            serverServiceView.setConfigurationListener(this.mConfigurationListener);
            return new ViewHolder(serverServiceView);
        }
        return new AddServiceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.server_service_add, viewGroup, false));
    }

    @Override // no.nordicsemi.android.mcp.widget.ItemTouchHelperAdapter
    public void onItemDismiss(RemovableViewHolder removableViewHolder) {
        View swipeableView = removableViewHolder.getSwipeableView();
        int adapterPosition = removableViewHolder.getAdapterPosition();
        this.mConfigurationListener.onPrepareConfigurationChange();
        if (swipeableView instanceof ServerDescriptorView) {
            Descriptor descriptor = ((ServerDescriptorView) swipeableView).getDescriptor();
            descriptor.getCharacteristic().getDescriptors().remove(descriptor);
            notifyDataSetChanged();
        } else if (swipeableView instanceof ServerCharacteristicView) {
            Characteristic characteristic = ((ServerCharacteristicView) swipeableView).getCharacteristic();
            characteristic.getService().getCharacteristics().remove(characteristic);
            notifyDataSetChanged();
        } else {
            this.mConfiguration.getServices().remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        }
        this.mConfigurationListener.onConfigurationChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArray(ANIMATORS, (ViewAnimator[]) this.mViewAnimators.values().toArray(new ViewAnimator[this.mViewAnimators.size()]));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewAttachedToWindow(RecyclerView.d0 d0Var) {
        if (d0Var instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) d0Var;
            viewHolder.animator.bindView(viewHolder.serviceView.getMainView(), viewHolder.serviceView.getCharacteristicsView());
        } else if (d0Var instanceof AddServiceViewHolder) {
            ((AddServiceViewHolder) d0Var).addServiceAction.setOnClickListener(this.mAddServiceListener);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewDetachedFromWindow(RecyclerView.d0 d0Var) {
        if (d0Var instanceof ViewHolder) {
            ((ViewHolder) d0Var).animator.unbindView();
        } else if (d0Var instanceof AddServiceViewHolder) {
            ((AddServiceViewHolder) d0Var).addServiceAction.setOnClickListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConfiguration(ServerConfiguration serverConfiguration, boolean z) {
        if (this.mConfiguration != null && z) {
            this.mViewAnimators.clear();
        }
        this.mConfiguration = serverConfiguration;
        notifyDataSetChanged();
    }
}
