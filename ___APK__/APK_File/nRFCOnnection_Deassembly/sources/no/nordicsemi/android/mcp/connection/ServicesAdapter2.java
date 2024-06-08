package no.nordicsemi.android.mcp.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.widget.SelectionListener;
import no.nordicsemi.android.mcp.widget.ViewAnimator;
import no.nordicsemi.android.mcp.widget.connection.CharacteristicView;
import no.nordicsemi.android.mcp.widget.connection.ServiceView;

/* loaded from: classes.dex */
public class ServicesAdapter2 extends RecyclerView.g<ServiceViewHolder> {
    private static final String ANIMATORS = "animators";
    private final boolean mClient;
    private CharacteristicView.OnConditionalSleepListener mConditionalSleepListener;
    private boolean mConnected;
    private final IBluetoothLeConnection mConnection;
    private final DatabaseHelper mDatabaseHelper;
    private boolean mEnabled;
    private ViewAnimator.ExpandCollapseListener mExpandCollapseListener;
    private final SelectionListener mSelectionListener;
    private List<BluetoothGattService> mServices;
    private List<BluetoothGattService> mSystemServices;
    private Map<String, ViewAnimator> mViewAnimators;
    private CharacteristicView.OnWriteRequestListener mWriteRequestListener;
    private static final UUID GENERIC_ACCESS_SERVICE_UUID = UUID.fromString("00001800-0000-1000-8000-00805f9b34fb");
    private static final UUID DEVICE_NAME_CHAR_UUID = UUID.fromString("00002A00-0000-1000-8000-00805f9b34fb");
    private static final UUID APPEARANCE_CHAR_UUID = UUID.fromString("00002A01-0000-1000-8000-00805f9b34fb");
    private static final UUID CENTRAL_ADDRESS_RESOLUTION_CHAR_UUID = UUID.fromString("00002AA6-0000-1000-8000-00805f9b34fb");
    private static final UUID GENERIC_ATTRIBUTE_SERVICE_UUID = UUID.fromString("00001801-0000-1000-8000-00805f9b34fb");
    private static final UUID SERVICE_CHANGED_CHAR_UUID = UUID.fromString("00002A05-0000-1000-8000-00805f9b34fb");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class ServiceViewHolder extends RecyclerView.d0 {
        private ViewAnimator animator;
        private ServiceView serviceView;

        ServiceViewHolder(ServiceView serviceView) {
            super(serviceView);
            this.serviceView = serviceView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServicesAdapter2(DatabaseHelper databaseHelper, DeviceDetailsFragment2 deviceDetailsFragment2, IBluetoothLeConnection iBluetoothLeConnection, boolean z) {
        this.mClient = z;
        this.mDatabaseHelper = databaseHelper;
        this.mConnection = iBluetoothLeConnection;
        this.mExpandCollapseListener = deviceDetailsFragment2;
        this.mSelectionListener = deviceDetailsFragment2;
        this.mWriteRequestListener = deviceDetailsFragment2;
        this.mConditionalSleepListener = deviceDetailsFragment2;
        if (z) {
            this.mServices = iBluetoothLeConnection.getSupportedGattServices();
        } else {
            this.mSystemServices = createSystemServices();
            this.mServices = iBluetoothLeConnection.getServerGattServices(false);
        }
        this.mConnected = true;
        this.mEnabled = true;
        this.mViewAnimators = (Map) iBluetoothLeConnection.get(ANIMATORS);
        if (this.mViewAnimators == null) {
            this.mViewAnimators = new HashMap();
            iBluetoothLeConnection.put(ANIMATORS, this.mViewAnimators);
        }
    }

    private List<BluetoothGattService> createSystemServices() {
        ArrayList arrayList = new ArrayList(2);
        BluetoothGattService bluetoothGattService = new BluetoothGattService(GENERIC_ATTRIBUTE_SERVICE_UUID, 0);
        bluetoothGattService.addCharacteristic(new BluetoothGattCharacteristic(SERVICE_CHANGED_CHAR_UUID, 32, 0));
        BluetoothGattService bluetoothGattService2 = new BluetoothGattService(GENERIC_ACCESS_SERVICE_UUID, 0);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(DEVICE_NAME_CHAR_UUID, 2, 0);
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(APPEARANCE_CHAR_UUID, 2, 0);
        BluetoothGattCharacteristic bluetoothGattCharacteristic3 = new BluetoothGattCharacteristic(CENTRAL_ADDRESS_RESOLUTION_CHAR_UUID, 2, 0);
        bluetoothGattService2.addCharacteristic(bluetoothGattCharacteristic);
        bluetoothGattService2.addCharacteristic(bluetoothGattCharacteristic2);
        bluetoothGattService2.addCharacteristic(bluetoothGattCharacteristic3);
        arrayList.add(bluetoothGattService);
        arrayList.add(bluetoothGattService2);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearSelections() {
        Iterator<Map.Entry<String, ViewAnimator>> it = this.mViewAnimators.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().clearActivated();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int getItemCount() {
        List<BluetoothGattService> list = this.mServices;
        int size = list != null ? list.size() : 0;
        List<BluetoothGattService> list2 = this.mSystemServices;
        return size + (list2 != null ? list2.size() : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getSelectedItem() {
        Iterator<Map.Entry<String, ViewAnimator>> it = this.mViewAnimators.entrySet().iterator();
        View view = null;
        while (it.hasNext() && (view = it.next().getValue().getActiveView()) == null) {
        }
        if (view == null) {
            return null;
        }
        if (view instanceof ServiceView) {
            return ((ServiceView) view).getService();
        }
        if (view instanceof CharacteristicView) {
            return ((CharacteristicView) view).getCharacteristic();
        }
        return null;
    }

    public void setConnected(boolean z) {
        this.mConnected = z;
        notifyDataSetChanged();
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateServices(List<BluetoothGattService> list) {
        this.mServices = list;
        this.mViewAnimators.clear();
        setConnected(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateServicesToCopy(List<BluetoothGattService> list) {
        this.mServices = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onBindViewHolder(ServiceViewHolder serviceViewHolder, int i) {
        List<BluetoothGattService> list = this.mSystemServices;
        int size = (list != null && i >= list.size()) ? i - this.mSystemServices.size() : i;
        List<BluetoothGattService> list2 = this.mSystemServices;
        List<BluetoothGattService> list3 = (list2 != null && i < list2.size()) ? this.mSystemServices : this.mServices;
        BluetoothGattService bluetoothGattService = list3.get(size);
        StringBuilder sb = new StringBuilder();
        sb.append(bluetoothGattService.getUuid().hashCode());
        sb.append(":");
        sb.append(bluetoothGattService.getInstanceId());
        sb.append(this.mClient ? ":C" : ":S");
        sb.append(i);
        String sb2 = sb.toString();
        ViewAnimator viewAnimator = this.mViewAnimators.get(sb2);
        if (viewAnimator == null) {
            viewAnimator = new ViewAnimator(sb2);
            this.mViewAnimators.put(sb2, viewAnimator);
        }
        viewAnimator.setExpandCollapseListener(this.mExpandCollapseListener, i);
        serviceViewHolder.animator = viewAnimator;
        serviceViewHolder.serviceView.setConnected(this.mConnected);
        serviceViewHolder.serviceView.setSystemManaged(list3 == this.mSystemServices);
        serviceViewHolder.serviceView.setActionsEnabled(this.mEnabled);
        serviceViewHolder.serviceView.setService(this.mDatabaseHelper, bluetoothGattService, viewAnimator);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ServiceView serviceView = (ServiceView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_item, viewGroup, false);
        serviceView.setClient(this.mClient);
        serviceView.setConnection(this.mConnection);
        serviceView.setOnWriteListener(this.mWriteRequestListener);
        serviceView.setOnConditionalSleepListener(this.mConditionalSleepListener);
        serviceView.setSelectionListener(this.mSelectionListener);
        return new ServiceViewHolder(serviceView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewAttachedToWindow(ServiceViewHolder serviceViewHolder) {
        serviceViewHolder.animator.bindView(serviceViewHolder.serviceView.getMainView(), serviceViewHolder.serviceView.getCharacteristicsView());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onViewDetachedFromWindow(ServiceViewHolder serviceViewHolder) {
        serviceViewHolder.animator.unbindView();
    }
}
