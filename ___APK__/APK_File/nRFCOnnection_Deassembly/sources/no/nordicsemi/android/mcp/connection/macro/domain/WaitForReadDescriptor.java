package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WaitForReadDescriptor extends DescriptorOperation {
    public WaitForReadDescriptor() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattDescriptor descriptor = getDescriptor(iBluetoothLeConnection);
        if (descriptor != null) {
            iBluetoothLeConnection.waitForReadRequest(descriptor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean isClientOperation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new ReadDescriptor(databaseHelper, this)};
    }

    public WaitForReadDescriptor(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor, false);
        setDescription("Wait for read request to " + getName(databaseHelper));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WaitForReadDescriptor(DatabaseHelper databaseHelper, ReadDescriptor readDescriptor) {
        super(readDescriptor);
        setDescription("Wait for read request to " + getName(databaseHelper));
    }
}
