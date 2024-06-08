package no.nordicsemi.android.mcp.ble.dfu.initiator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.dfu.initiator.ExtendedBluetoothDevice;

/* loaded from: classes.dex */
public class DeviceListAdapter extends BaseAdapter {
    private static final int TYPE_EMPTY = 2;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_TITLE = 0;
    private final Context mContext;
    private final ArrayList<ExtendedBluetoothDevice> mListBondedValues = new ArrayList<>();
    private final ArrayList<ExtendedBluetoothDevice> mListValues = new ArrayList<>();
    private final ExtendedBluetoothDevice.AddressComparator comparator = new ExtendedBluetoothDevice.AddressComparator();

    /* loaded from: classes.dex */
    private class ViewHolder {
        private TextView address;
        private TextView name;
        private ImageView rssi;

        private ViewHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceListAdapter(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addBondedDevice(ExtendedBluetoothDevice extendedBluetoothDevice) {
        this.mListBondedValues.add(extendedBluetoothDevice);
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addOrUpdateDevice(ExtendedBluetoothDevice extendedBluetoothDevice) {
        if (this.mListBondedValues.contains(extendedBluetoothDevice)) {
            return;
        }
        int indexOf = this.mListValues.indexOf(extendedBluetoothDevice);
        if (indexOf >= 0) {
            this.mListValues.get(indexOf).rssi = extendedBluetoothDevice.rssi;
            notifyDataSetChanged();
        } else {
            this.mListValues.add(extendedBluetoothDevice);
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearDevices() {
        this.mListValues.clear();
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        int size = this.mListBondedValues.size() + 1;
        int size2 = this.mListValues.isEmpty() ? 2 : this.mListValues.size() + 1;
        return size == 1 ? size2 : size + size2;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        int size = this.mListBondedValues.size() + 1;
        boolean isEmpty = this.mListBondedValues.isEmpty();
        Integer valueOf = Integer.valueOf(R.string.scanner_subtitle__not_bonded);
        if (isEmpty) {
            return i == 0 ? valueOf : this.mListValues.get(i - 1);
        }
        if (i == 0) {
            return Integer.valueOf(R.string.scanner_subtitle_bonded);
        }
        if (i < size) {
            return this.mListBondedValues.get(i - 1);
        }
        return i == size ? valueOf : this.mListValues.get((i - size) - 1);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (this.mListBondedValues.isEmpty() || i != this.mListBondedValues.size() + 1) {
            return (i == getCount() - 1 && this.mListValues.isEmpty()) ? 2 : 1;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater from = LayoutInflater.from(this.mContext);
        int itemViewType = getItemViewType(i);
        View view2 = view;
        if (itemViewType == 0) {
            if (view == null) {
                view2 = from.inflate(R.layout.device_list_title, viewGroup, false);
            }
            ((TextView) view2).setText(((Integer) getItem(i)).intValue());
            return view2;
        }
        View view3 = view;
        if (itemViewType == 2) {
            return view == null ? from.inflate(R.layout.device_list_empty, viewGroup, false) : view;
        }
        if (view == null) {
            View inflate = from.inflate(R.layout.device_list_row, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) inflate.findViewById(R.id.display_name);
            viewHolder.address = (TextView) inflate.findViewById(R.id.address);
            viewHolder.rssi = (ImageView) inflate.findViewById(R.id.rssi);
            inflate.setTag(viewHolder);
            view3 = inflate;
        }
        ExtendedBluetoothDevice extendedBluetoothDevice = (ExtendedBluetoothDevice) getItem(i);
        ViewHolder viewHolder2 = (ViewHolder) view3.getTag();
        viewHolder2.name.setText(extendedBluetoothDevice.device.getName());
        viewHolder2.address.setText(extendedBluetoothDevice.device.getAddress());
        if (!extendedBluetoothDevice.isBonded || extendedBluetoothDevice.rssi != -1000) {
            viewHolder2.rssi.setImageLevel((int) (((extendedBluetoothDevice.rssi + 127.0f) * 100.0f) / 147.0f));
            viewHolder2.rssi.setVisibility(0);
            return view3;
        }
        viewHolder2.rssi.setVisibility(8);
        return view3;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return 3;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return getItemViewType(i) == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateRssiOfBondedDevice(String str, int i) {
        ExtendedBluetoothDevice.AddressComparator addressComparator = this.comparator;
        addressComparator.address = str;
        int indexOf = this.mListBondedValues.indexOf(addressComparator);
        if (indexOf >= 0) {
            this.mListBondedValues.get(indexOf).rssi = i;
            notifyDataSetChanged();
        }
    }
}
