package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WaitForWrite extends CharacteristicOperationWithValueAssertion {
    public WaitForWrite() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            iBluetoothLeConnection.waitForWriteRequest(characteristic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean hasAccessToTemporaryServices() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean isClientOperation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return this.assertValue != null ? new Operation[]{new Write(databaseHelper, this)} : new Operation[]{new Sleep("NOTE: Writing characteristic skipped. Value not provided.", 0L)};
    }

    public WaitForWrite(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, false);
        setDescription("Wait for write request to " + getName(databaseHelper));
        assertValue(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForWrite(DatabaseHelper databaseHelper, Write write) {
        super(write);
        setDescription("Wait for write request to " + getName(databaseHelper));
        this.assertValue = write.createValueAssert();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForWrite(DatabaseHelper databaseHelper, UnlockEddystone unlockEddystone) {
        super(unlockEddystone);
        setDescription("Wait for write request to " + getName(databaseHelper));
    }
}
