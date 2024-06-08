package no.nordicsemi.android.mcp.bonded;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.ViewSelector;
import no.nordicsemi.android.mcp.widget.bonded.BondedDeviceView;
import no.nordicsemi.android.mcp.widget.bonded.DeviceViewSelector;

/* loaded from: classes.dex */
public class BondedDevicesAdapter extends RecyclerView.g<DeviceViewHolder> {
    private static final String SELECTORS = "selectors";
    private List<Device> mBondedDevices;
    private BondedDeviceView.BondedDeviceListener mDeviceListener;
    private SelectionListener mSelectionListener;
    private Map<String, DeviceViewSelector> mViewSelectors = new HashMap();

    /* loaded from: classes.dex */
    public class DeviceViewHolder extends RecyclerView.d0 {
        private BondedDeviceView deviceView;
        private ViewSelector selector;

        public DeviceViewHolder(BondedDeviceView bondedDeviceView) {
            super(bondedDeviceView);
            this.deviceView = bondedDeviceView;
        }
    }

    public BondedDevicesAdapter(BondedDeviceView.BondedDeviceListener bondedDeviceListener, BondedDevicesListFragment bondedDevicesListFragment, Bundle bundle) {
        this.mDeviceListener = bondedDeviceListener;
        this.mSelectionListener = bondedDevicesListFragment;
        setHasStableIds(true);
        if (bundle != null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray(SELECTORS);
            for (Parcelable parcelable : parcelableArray) {
                DeviceViewSelector deviceViewSelector = (DeviceViewSelector) parcelable;
                this.mViewSelectors.put(deviceViewSelector.getViewId(), deviceViewSelector);
            }
        }
    }

    public void clearSelections() {
        Iterator<DeviceViewSelector> it = this.mViewSelectors.values().iterator();
        while (it.hasNext()) {
            it.next().clearActivated();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        List<Device> list = this.mBondedDevices;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i) {
        return this.mBondedDevices.get(i).hashCode();
    }

    public List<Device> getSelectedDevices() {
        ArrayList arrayList = new ArrayList();
        for (DeviceViewSelector deviceViewSelector : this.mViewSelectors.values()) {
            if (deviceViewSelector.isActivated()) {
                arrayList.add(deviceViewSelector.getDevice());
            }
        }
        return arrayList;
    }

    public int getSelectedItemCount() {
        Iterator<DeviceViewSelector> it = this.mViewSelectors.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().isActivated()) {
                i++;
            }
        }
        return i;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArray(SELECTORS, (ViewSelector[]) this.mViewSelectors.values().toArray(new ViewSelector[0]));
    }

    public void setBondedDevices(List<Device> list) {
        this.mBondedDevices = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(DeviceViewHolder deviceViewHolder, int i) {
        Device device = this.mBondedDevices.get(i);
        DeviceViewSelector deviceViewSelector = this.mViewSelectors.get(device.getAddress(null));
        if (deviceViewSelector == null) {
            deviceViewSelector = new DeviceViewSelector(device);
            this.mViewSelectors.put(device.getAddress(null), deviceViewSelector);
        }
        deviceViewHolder.selector = deviceViewSelector;
        deviceViewHolder.deviceView.setDevice(device, deviceViewSelector);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        BondedDeviceView bondedDeviceView = (BondedDeviceView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bonded_device_list_item, viewGroup, false);
        bondedDeviceView.setSelectionListener(this.mSelectionListener);
        bondedDeviceView.setDeviceListener(this.mDeviceListener);
        return new DeviceViewHolder(bondedDeviceView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewAttachedToWindow(DeviceViewHolder deviceViewHolder) {
        deviceViewHolder.selector.bindView(deviceViewHolder.deviceView, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewDetachedFromWindow(DeviceViewHolder deviceViewHolder) {
        deviceViewHolder.selector.unbindView();
    }
}
