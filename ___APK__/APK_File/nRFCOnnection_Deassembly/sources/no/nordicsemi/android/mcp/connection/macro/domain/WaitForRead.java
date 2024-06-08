package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.connection.macro.domain.AssertService;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WaitForRead extends CharacteristicOperation {
    public WaitForRead() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            iBluetoothLeConnection.waitForReadRequest(characteristic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public AssertService.AssertCharacteristic.Property getRequiredProperty() {
        return new AssertService.AssertCharacteristic.Property(AssertService.AssertCharacteristic.Property.Name.READ);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean isClientOperation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new Read(databaseHelper, this)};
    }

    public WaitForRead(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, false);
        setDescription("Wait for read request to " + getName(databaseHelper));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForRead(DatabaseHelper databaseHelper, Read read) {
        super(read);
        setDescription("Wait for read request to " + getName(databaseHelper));
    }
}
