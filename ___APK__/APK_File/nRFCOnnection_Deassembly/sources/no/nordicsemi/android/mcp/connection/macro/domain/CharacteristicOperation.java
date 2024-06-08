package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import java.util.List;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.connection.macro.domain.AssertService;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.Complete;
import org.simpleframework.xml.core.Persist;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public abstract class CharacteristicOperation extends Operation {
    private static final String TAG = "CharacteristicOperation";

    @Attribute(required = false)
    String characteristicInstanceId;

    @Attribute
    String characteristicUuid;
    private BluetoothGattCharacteristic mCharacteristic;

    @Attribute(required = false)
    String serviceInstanceId;

    @Attribute
    String serviceUuid;

    public CharacteristicOperation() {
        this.serviceInstanceId = "0";
        this.characteristicInstanceId = "0";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Complete
    public void afterSerialization() {
        if (this.serviceInstanceId == null) {
            this.serviceInstanceId = "0";
        }
        if (this.characteristicInstanceId == null) {
            this.characteristicInstanceId = "0";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Persist
    public void beforeSerialization() {
        if ("0".equals(this.serviceInstanceId)) {
            this.serviceInstanceId = null;
        }
        if ("0".equals(this.characteristicInstanceId)) {
            this.characteristicInstanceId = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothGattCharacteristic getCharacteristic(IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.mCharacteristic;
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic;
        }
        BluetoothGattService service = getService(iBluetoothLeBasicConnection);
        if (service == null) {
            return null;
        }
        int i = 0;
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : service.getCharacteristics()) {
            if (this.characteristicUuid.equalsIgnoreCase(bluetoothGattCharacteristic2.getUuid().toString())) {
                if (this.characteristicInstanceId.equals(String.valueOf(i))) {
                    this.mCharacteristic = bluetoothGattCharacteristic2;
                    return bluetoothGattCharacteristic2;
                }
                i++;
            }
        }
        Log.e(TAG, "Characteristic not found");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName(DatabaseHelper databaseHelper) {
        String characteristicName = databaseHelper.getCharacteristicName(UUID.fromString(this.characteristicUuid));
        return characteristicName != null ? characteristicName : this.characteristicUuid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssertService.AssertCharacteristic.Property getRequiredProperty() {
        return null;
    }

    BluetoothGattService getService(IBluetoothLeBasicConnection iBluetoothLeBasicConnection) {
        List<BluetoothGattService> serverGattServices;
        if (isClientOperation()) {
            serverGattServices = iBluetoothLeBasicConnection.getSupportedGattServices();
        } else {
            serverGattServices = iBluetoothLeBasicConnection.getServerGattServices(hasAccessToTemporaryServices());
        }
        if (serverGattServices == null) {
            if (isClientOperation()) {
                Log.w(TAG, "Services not discovered");
            } else {
                Log.w(TAG, "Server not started");
            }
            return null;
        }
        int i = 0;
        for (BluetoothGattService bluetoothGattService : serverGattServices) {
            if (this.serviceUuid.equalsIgnoreCase(bluetoothGattService.getUuid().toString())) {
                if (this.serviceInstanceId.equals(String.valueOf(i))) {
                    return bluetoothGattService;
                }
                i++;
            }
        }
        Log.e(TAG, "Service not found");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasAccessToTemporaryServices() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] hexStringToByteArray(String str) {
        int length = str.length();
        if (length % 2 != 1) {
            byte[] bArr = new byte[length / 2];
            for (int i = 0; i < length; i += 2) {
                int digit = Character.digit(str.charAt(i), 16);
                int digit2 = Character.digit(str.charAt(i + 1), 16);
                if (digit != -1 && digit2 != -1) {
                    bArr[i / 2] = (byte) ((digit << 4) + digit2);
                } else {
                    throw new PersistenceException("'%s' is not a valid byte array", str);
                }
            }
            return bArr;
        }
        throw new PersistenceException("The byte array '%s' must have even number of characters", str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public void invalidate() {
        this.mCharacteristic = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isClientOperation() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Validate
    public void validate() {
        try {
            UUID.fromString(this.serviceUuid);
            try {
                UUID.fromString(this.characteristicUuid);
                try {
                    Integer.parseInt(this.serviceInstanceId);
                    try {
                        Integer.parseInt(this.characteristicInstanceId);
                    } catch (NumberFormatException unused) {
                        throw new PersistenceException("The characteristic-instance-id must be a number: " + this.characteristicInstanceId, new Object[0]);
                    }
                } catch (NumberFormatException unused2) {
                    throw new PersistenceException("The service-instance-id must be a number: " + this.serviceInstanceId, new Object[0]);
                }
            } catch (IllegalArgumentException unused3) {
                throw new PersistenceException("'%s' is not a valid characteristic UUID", this.characteristicUuid);
            }
        } catch (IllegalArgumentException unused4) {
            throw new PersistenceException("'%s' is not a valid service UUID", this.serviceUuid);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicOperation(IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        this.serviceInstanceId = "0";
        this.characteristicInstanceId = "0";
        int i = 0;
        for (BluetoothGattService bluetoothGattService : z ? iBluetoothLeBasicConnection.getSupportedGattServices() : iBluetoothLeBasicConnection.getServerGattServices(hasAccessToTemporaryServices())) {
            if (bluetoothGattService.getUuid().equals(bluetoothGattCharacteristic.getService().getUuid())) {
                if (bluetoothGattService == bluetoothGattCharacteristic.getService()) {
                    int i2 = 0;
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic2 : bluetoothGattCharacteristic.getService().getCharacteristics()) {
                        if (bluetoothGattCharacteristic2.getUuid().equals(bluetoothGattCharacteristic.getUuid())) {
                            if (bluetoothGattCharacteristic2 == bluetoothGattCharacteristic) {
                                this.mCharacteristic = bluetoothGattCharacteristic;
                                this.serviceUuid = bluetoothGattService.getUuid().toString();
                                this.characteristicUuid = bluetoothGattCharacteristic2.getUuid().toString();
                                this.serviceInstanceId = String.valueOf(i);
                                this.characteristicInstanceId = String.valueOf(i2);
                                return;
                            }
                            i2++;
                        }
                    }
                }
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicOperation(CharacteristicOperation characteristicOperation) {
        this.serviceInstanceId = "0";
        this.characteristicInstanceId = "0";
        this.serviceUuid = characteristicOperation.serviceUuid;
        this.serviceInstanceId = characteristicOperation.serviceInstanceId;
        this.characteristicUuid = characteristicOperation.characteristicUuid;
        this.characteristicInstanceId = characteristicOperation.characteristicInstanceId;
    }
}
