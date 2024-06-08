package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattDescriptor;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class ReadDescriptor extends DescriptorOperationWithValueAssertion {
    public ReadDescriptor() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattDescriptor descriptor = getDescriptor(iBluetoothLeConnection);
        if (descriptor != null) {
            iBluetoothLeConnection.readDescriptor(descriptor);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new WaitForReadDescriptor(databaseHelper, this)};
    }

    public ReadDescriptor(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor, true);
        setDescription("Read value of " + getName(databaseHelper));
        assertValue(bluetoothGattDescriptor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReadDescriptor(DatabaseHelper databaseHelper, WaitForReadDescriptor waitForReadDescriptor) {
        super(waitForReadDescriptor);
        setDescription("Read value of " + getName(databaseHelper));
    }
}
