package no.nordicsemi.android.mcp.ble.server;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class ServiceMap {
    private List<BluetoothGattService> mOriginalServices;
    private List<BluetoothGattService> mServerServices;
    private final Map<BluetoothDevice, List<BluetoothGattService>> mDeviceServices = new HashMap();
    private final Map<BluetoothDevice, List<BluetoothGattService>> mTemporaryServices = new HashMap();

    private static List<BluetoothGattService> cloneServices(List<BluetoothGattService> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (BluetoothGattService bluetoothGattService : list) {
            BluetoothGattService bluetoothGattService2 = new BluetoothGattService(bluetoothGattService.getUuid(), 0);
            arrayList.add(bluetoothGattService2);
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                BluetoothGattCharacteristic bluetoothGattCharacteristic2 = new BluetoothGattCharacteristic(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getProperties(), bluetoothGattCharacteristic.getPermissions());
                byte[] value = bluetoothGattCharacteristic.getValue();
                if (value != null) {
                    bluetoothGattCharacteristic2.setValue(Arrays.copyOf(value, value.length));
                }
                bluetoothGattService2.addCharacteristic(bluetoothGattCharacteristic2);
                for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                    BluetoothGattDescriptor bluetoothGattDescriptor2 = new BluetoothGattDescriptor(bluetoothGattDescriptor.getUuid(), bluetoothGattCharacteristic.getPermissions());
                    byte[] value2 = bluetoothGattDescriptor.getValue();
                    if (value2 != null) {
                        bluetoothGattDescriptor2.setValue(Arrays.copyOf(value2, value2.length));
                    }
                    bluetoothGattCharacteristic2.addDescriptor(bluetoothGattDescriptor2);
                }
            }
        }
        return arrayList;
    }

    private static BluetoothGattCharacteristic findCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, List<BluetoothGattService> list, List<BluetoothGattService> list2) {
        BluetoothGattService service = bluetoothGattCharacteristic.getService();
        BluetoothGattService findService = findService(service, list, list2);
        if (findService == null) {
            return null;
        }
        Iterator<BluetoothGattCharacteristic> it = service.getCharacteristics().iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next() == bluetoothGattCharacteristic) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return null;
        }
        List<BluetoothGattCharacteristic> characteristics = findService.getCharacteristics();
        if (i >= characteristics.size()) {
            return null;
        }
        return characteristics.get(i);
    }

    private static BluetoothGattDescriptor findDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, List<BluetoothGattService> list, List<BluetoothGattService> list2) {
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        BluetoothGattCharacteristic findCharacteristic = findCharacteristic(characteristic, list, list2);
        if (findCharacteristic == null) {
            return null;
        }
        Iterator<BluetoothGattDescriptor> it = characteristic.getDescriptors().iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (it.next() == bluetoothGattDescriptor) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            return null;
        }
        List<BluetoothGattDescriptor> descriptors = findCharacteristic.getDescriptors();
        if (i >= descriptors.size()) {
            return null;
        }
        return descriptors.get(i);
    }

    private static BluetoothGattService findService(BluetoothGattService bluetoothGattService, List<BluetoothGattService> list, List<BluetoothGattService> list2) {
        if (list != null && list2 != null) {
            Iterator<BluetoothGattService> it = list.iterator();
            boolean z = false;
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next() == bluetoothGattService) {
                    z = true;
                    break;
                }
                i++;
            }
            if (z && i < list2.size()) {
                return list2.get(i);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean abortTemporaryServices(BluetoothDevice bluetoothDevice) {
        if (this.mTemporaryServices.get(bluetoothDevice) == null) {
            return false;
        }
        this.mTemporaryServices.remove(bluetoothDevice);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String dump(BluetoothDevice bluetoothDevice) {
        StringBuilder sb = new StringBuilder();
        List<BluetoothGattService> list = this.mDeviceServices.get(bluetoothDevice);
        if (list == null) {
            return "Services not found";
        }
        for (BluetoothGattService bluetoothGattService : list) {
            sb.append("Service: ");
            sb.append(bluetoothGattService.getUuid());
            sb.append(" (");
            sb.append(bluetoothGattService.getInstanceId());
            sb.append(")\n");
            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                sb.append("- Characteristic: ");
                sb.append(bluetoothGattCharacteristic.getUuid());
                sb.append(" (");
                sb.append(bluetoothGattCharacteristic.getInstanceId());
                sb.append(")\n");
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean executeTemporaryServices(BluetoothDevice bluetoothDevice) {
        List<BluetoothGattService> list = this.mTemporaryServices.get(bluetoothDevice);
        if (list == null) {
            return false;
        }
        this.mDeviceServices.put(bluetoothDevice, list);
        this.mTemporaryServices.remove(bluetoothDevice);
        return true;
    }

    public BluetoothGattCharacteristic getDeviceCharacteristic(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        List<BluetoothGattService> list = (!z ? this.mDeviceServices : this.mTemporaryServices).get(bluetoothDevice);
        if (list == null) {
            List<BluetoothGattService> list2 = this.mDeviceServices.get(bluetoothDevice);
            if (list2 == null) {
                return null;
            }
            list = cloneServices(list2);
            this.mTemporaryServices.put(bluetoothDevice, list);
        }
        return findCharacteristic(bluetoothGattCharacteristic, this.mServerServices, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattDescriptor getDeviceDescriptor(BluetoothDevice bluetoothDevice, BluetoothGattDescriptor bluetoothGattDescriptor, boolean z) {
        List<BluetoothGattService> list = (!z ? this.mDeviceServices : this.mTemporaryServices).get(bluetoothDevice);
        if (list == null) {
            List<BluetoothGattService> list2 = this.mDeviceServices.get(bluetoothDevice);
            if (list2 == null) {
                return null;
            }
            list = cloneServices(list2);
            this.mTemporaryServices.put(bluetoothDevice, list);
        }
        return findDescriptor(bluetoothGattDescriptor, this.mServerServices, list);
    }

    public BluetoothGattService getDeviceService(BluetoothDevice bluetoothDevice, BluetoothGattService bluetoothGattService) {
        return findService(bluetoothGattService, this.mServerServices, this.mDeviceServices.get(bluetoothDevice));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<BluetoothGattService> getDeviceServices(BluetoothDevice bluetoothDevice, boolean z) {
        List<BluetoothGattService> list;
        return (!z || (list = this.mTemporaryServices.get(bluetoothDevice)) == null) ? this.mDeviceServices.get(bluetoothDevice) : list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattCharacteristic getServerCharacteristic(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return findCharacteristic(bluetoothGattCharacteristic, this.mDeviceServices.get(bluetoothDevice), this.mServerServices);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattService getServerService(BluetoothDevice bluetoothDevice, BluetoothGattService bluetoothGattService) {
        return findService(bluetoothGattService, this.mDeviceServices.get(bluetoothDevice), this.mServerServices);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onConnectionClosed(BluetoothDevice bluetoothDevice) {
        this.mDeviceServices.remove(bluetoothDevice);
        this.mTemporaryServices.remove(bluetoothDevice);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onConnectionCreated(BluetoothDevice bluetoothDevice) {
        if (this.mOriginalServices != null) {
            if (this.mDeviceServices.containsKey(bluetoothDevice)) {
                return;
            }
            this.mDeviceServices.put(bluetoothDevice, cloneServices(this.mOriginalServices));
            return;
        }
        this.mDeviceServices.put(bluetoothDevice, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onServerClosed() {
        this.mDeviceServices.clear();
        this.mTemporaryServices.clear();
        List<BluetoothGattService> list = this.mServerServices;
        if (list != null) {
            list.clear();
        }
        List<BluetoothGattService> list2 = this.mOriginalServices;
        if (list2 != null) {
            list2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateServerServices(List<BluetoothGattService> list) {
        this.mServerServices = list;
        this.mOriginalServices = cloneServices(list);
        Iterator<Map.Entry<BluetoothDevice, List<BluetoothGattService>>> it = this.mDeviceServices.entrySet().iterator();
        while (it.hasNext()) {
            it.next().setValue(cloneServices(list));
        }
    }
}
