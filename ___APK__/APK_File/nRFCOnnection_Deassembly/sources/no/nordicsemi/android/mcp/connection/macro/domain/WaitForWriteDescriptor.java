package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WaitForWriteDescriptor extends DescriptorOperationWithValueAssertion {
    public WaitForWriteDescriptor() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattDescriptor descriptor = getDescriptor(iBluetoothLeConnection);
        if (descriptor != null) {
            iBluetoothLeConnection.waitForWriteRequest(descriptor);
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
        return this.assertValue != null ? new Operation[]{new WriteDescriptor(databaseHelper, this)} : new Operation[]{new Sleep("NOTE: Writing descriptor skipped. Value not provided.", 0L)};
    }

    public WaitForWriteDescriptor(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor, false);
        setDescription("Wait for write request to " + getName(databaseHelper));
        assertValue(bluetoothGattDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForWriteDescriptor(DatabaseHelper databaseHelper, WriteDescriptor writeDescriptor) {
        super(writeDescriptor);
        setDescription("Wait for write request to " + getName(databaseHelper));
        this.assertValue = writeDescriptor.createValueAssert();
    }
}
