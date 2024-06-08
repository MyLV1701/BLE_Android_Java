package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattDescriptor;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SetDescriptorValue extends DescriptorOperationWithValue {
    public SetDescriptorValue() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattDescriptor descriptor = getDescriptor(iBluetoothLeConnection);
        if (descriptor != null) {
            descriptor.setValue(this.bytes);
            iBluetoothLeConnection.onDescriptorValueSet(descriptor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean isClientOperation() {
        return false;
    }

    public SetDescriptorValue(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor, false);
        this.bytes = bluetoothGattDescriptor.getValue();
        this.value = ParserUtils.bytesToHex(this.bytes, false);
        if (!TextUtils.isEmpty(this.value)) {
            setDescription("Set value 0x" + this.value + " to " + getName(databaseHelper));
            return;
        }
        setDescription("Set empty value to " + getName(databaseHelper));
    }
}
