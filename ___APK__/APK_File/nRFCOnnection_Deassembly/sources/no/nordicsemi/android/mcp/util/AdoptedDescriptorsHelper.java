package no.nordicsemi.android.mcp.util;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.UuidLibrary;

/* loaded from: classes.dex */
public class AdoptedDescriptorsHelper extends UuidLibrary {
    public static BluetoothGattDescriptor getCCCD(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getDescriptor(UuidLibrary.CLIENT_CHARACTERISTIC_CONFIGURATION);
        }
        return null;
    }

    public static boolean isCCCD(UUID uuid) {
        return UuidLibrary.CLIENT_CHARACTERISTIC_CONFIGURATION.equals(uuid);
    }

    public static boolean isCEPD(UUID uuid) {
        return UuidLibrary.CHARACTERISTIC_EXTENDED_PROPERTIES.equals(uuid);
    }
}
