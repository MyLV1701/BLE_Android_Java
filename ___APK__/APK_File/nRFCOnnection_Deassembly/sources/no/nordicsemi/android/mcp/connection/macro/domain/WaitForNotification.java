package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.connection.macro.domain.AssertService;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WaitForNotification extends CharacteristicOperationWithValueAssertion {
    public WaitForNotification() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            iBluetoothLeConnection.waitForNotification(characteristic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public AssertService.AssertCharacteristic.Property getRequiredProperty() {
        return new AssertService.AssertCharacteristic.Property(AssertService.AssertCharacteristic.Property.Name.NOTIFY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return this.assertValue != null ? new Operation[]{new SendNotification(databaseHelper, this)} : new Operation[]{new Sleep("NOTE: Sending notification skipped. Value not provided.", 0L)};
    }

    public WaitForNotification(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, true);
        setDescription("Wait for notification to " + getName(databaseHelper));
        assertValue(bluetoothGattCharacteristic);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForNotification(DatabaseHelper databaseHelper, SendNotification sendNotification) {
        super(sendNotification);
        setDescription("Wait for notification to " + getName(databaseHelper));
        this.assertValue = sendNotification.createValueAssert();
    }
}
