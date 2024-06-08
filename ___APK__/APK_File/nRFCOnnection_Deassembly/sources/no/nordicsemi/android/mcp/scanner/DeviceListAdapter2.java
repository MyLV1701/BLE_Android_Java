package no.nordicsemi.android.mcp.scanner;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.scanner.DeviceView;
import no.nordicsemi.android.mcp.widget.scanner.DeviceViewAnimator;

/* loaded from: classes.dex */
public class DeviceListAdapter2 extends RecyclerView.g<RecyclerView.d0> {
    private static final String ANIMATORS = "animators";
    private static final int TYPE_DEVICE = 1;
    private static final int TYPE_HEADER = 0;
    private DatabaseHelper mDatabaseHelper;
    private DeviceView.DeviceListener mDeviceListener;
    private List<Device> mDevices;
    private ViewAnimator.ExpandCollapseListener mExpandCollapseListener;
    private DeviceView.OnFavoriteStateChangeListener mFavoriteStateChangeListener;
    private boolean mPanelClosable;
    private float mPanelOffset;
    private SelectionListener mSelectionListener;
    private final List<DeviceViewHolder> mDeviceHolders = new ArrayList();
    private Map<String, DeviceViewAnimator> mDeviceViewAnimators = new HashMap();

    /* loaded from: classes.dex */
    private static class DeviceViewHolder extends RecyclerView.d0 {
        private DeviceViewAnimator animator;
        private DeviceView deviceView;

        private DeviceViewHolder(DeviceView deviceView) {
            super(deviceView);
            this.deviceView = deviceView;
        }
    }

    /* loaded from: classes.dex */
    private static class HeaderViewHolder extends RecyclerView.d0 {
        private HeaderViewHolder(View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceListAdapter2(DatabaseHelper databaseHelper, DeviceView.DeviceListener deviceListener, DeviceListFragment2 deviceListFragment2, Bundle bundle) {
        this.mDeviceListener = deviceListener;
        this.mSelectionListener = deviceListFragment2;
        this.mExpandCollapseListener = deviceListFragment2;
        this.mFavoriteStateChangeListener = deviceListFragment2;
        this.mDatabaseHelper = databaseHelper;
        setHasStableIds(true);
        if (bundle != null) {
            for (Parcelable parcelable : bundle.getParcelableArray(ANIMATORS)) {
                DeviceViewAnimator deviceViewAnimator = (DeviceViewAnimator) parcelable;
                deviceViewAnimator.reinitialize();
                this.mDeviceViewAnimators.put(deviceViewAnimator.getViewId(), deviceViewAnimator);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearDevices() {
        clearSelections();
        this.mDeviceViewAnimators.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearSelections() {
        Iterator<DeviceViewAnimator> it = this.mDeviceViewAnimators.values().iterator();
        while (it.hasNext()) {
            it.next().clearActivated();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        List<Device> list = this.mDevices;
        return (list != null ? list.size() : 0) + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        if (i == 0) {
            return -1L;
        }
        return this.mDevices.get(i - 1).getDeviceHash().hashCode();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemViewType(int i) {
        return i > 0 ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<Device> getSelectedDevices() {
        ArrayList<Device> arrayList = new ArrayList<>();
        for (DeviceViewAnimator deviceViewAnimator : this.mDeviceViewAnimators.values()) {
            if (deviceViewAnimator.isActivated()) {
                arrayList.add(deviceViewAnimator.getDevice());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSelectedItemCount() {
        Iterator<DeviceViewAnimator> it = this.mDeviceViewAnimators.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().isActivated()) {
                i++;
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(RecyclerView.d0 d0Var, int i) {
        if (getItemViewType(i) == 1) {
            DeviceViewHolder deviceViewHolder = (DeviceViewHolder) d0Var;
            Device device = this.mDevices.get(i - 1);
            DeviceViewAnimator deviceViewAnimator = this.mDeviceViewAnimators.get(device.getDeviceHash());
            if (deviceViewAnimator == null) {
                deviceViewAnimator = new DeviceViewAnimator(device);
                this.mDeviceViewAnimators.put(device.getDeviceHash(), deviceViewAnimator);
            }
            deviceViewAnimator.setExpandCollapseListener(this.mExpandCollapseListener, i);
            deviceViewHolder.animator = deviceViewAnimator;
            deviceViewHolder.deviceView.setDevice(device, deviceViewAnimator);
            deviceViewHolder.deviceView.setFavorite(this.mDatabaseHelper.isDeviceFavorite(device));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public RecyclerView.d0 onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 0) {
            DeviceView deviceView = (DeviceView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_list_item2, viewGroup, false);
            deviceView.setOnFavoriteStateChangeListener(this.mFavoriteStateChangeListener);
            deviceView.setSelectionListener(this.mSelectionListener);
            deviceView.setDeviceListener(this.mDeviceListener);
            deviceView.setSlideEnabled(this.mPanelClosable);
            deviceView.setSlideOffset(this.mPanelOffset);
            return new DeviceViewHolder(deviceView);
        }
        return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.device_list_header, viewGroup, false));
    }

    public void onDestroy() {
        this.mDatabaseHelper = null;
        this.mDeviceListener = null;
        this.mSelectionListener = null;
        this.mExpandCollapseListener = null;
        this.mFavoriteStateChangeListener = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArray(ANIMATORS, (DeviceViewAnimator[]) this.mDeviceViewAnimators.values().toArray(new DeviceViewAnimator[0]));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewAttachedToWindow(RecyclerView.d0 d0Var) {
        if (d0Var instanceof DeviceViewHolder) {
            DeviceViewHolder deviceViewHolder = (DeviceViewHolder) d0Var;
            deviceViewHolder.animator.bindView(deviceViewHolder.deviceView, deviceViewHolder.deviceView.getDetailsView());
            deviceViewHolder.deviceView.setSlideOffset(this.mPanelOffset);
            this.mDeviceHolders.add(deviceViewHolder);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewDetachedFromWindow(RecyclerView.d0 d0Var) {
        if (d0Var instanceof DeviceViewHolder) {
            ((DeviceViewHolder) d0Var).animator.unbindView();
            this.mDeviceHolders.remove(d0Var);
        }
    }

    public void setDevices(List<Device> list) {
        this.mDevices = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPanelClosable(boolean z) {
        this.mPanelClosable = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPanelOffset(float f2) {
        this.mPanelOffset = f2;
        Iterator<DeviceViewHolder> it = this.mDeviceHolders.iterator();
        while (it.hasNext()) {
            it.next().deviceView.setSlideOffset(f2);
        }
    }
}
