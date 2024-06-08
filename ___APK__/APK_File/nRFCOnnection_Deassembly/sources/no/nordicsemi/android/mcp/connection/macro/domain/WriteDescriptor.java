package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattDescriptor;
import android.text.TextUtils;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class WriteDescriptor extends DescriptorOperationWithValue {
    private static final UUID CCCD_UUID = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    public WriteDescriptor() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattDescriptor descriptor = getDescriptor(iBluetoothLeConnection);
        if (descriptor != null) {
            iBluetoothLeConnection.writeDescriptor(descriptor, this.bytes);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean hasAccessToTemporaryServices() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new WaitForWriteDescriptor(databaseHelper, this)};
    }

    public WriteDescriptor(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        super(iBluetoothLeBasicConnection, bluetoothGattDescriptor, true);
        byte[] bArr2;
        this.bytes = bArr;
        this.value = ParserUtils.bytesToHex(this.bytes, false);
        if (bluetoothGattDescriptor.getUuid().equals(CCCD_UUID) && (bArr2 = this.bytes) != null && bArr2.length == 2 && bArr2[1] == 0) {
            byte b2 = bArr2[0];
            if (b2 == 0) {
                setDescription("Disable notifications and indications for " + getParentName(databaseHelper));
                return;
            }
            if (b2 == 1) {
                setDescription("Enable notifications for " + getParentName(databaseHelper));
                return;
            }
            if (b2 == 2) {
                setDescription("Enable indications for " + getParentName(databaseHelper));
                return;
            }
        }
        if (!TextUtils.isEmpty(this.value)) {
            setDescription("Write 0x" + this.value + " to " + getName(databaseHelper));
            return;
        }
        setDescription("Write empty packet to " + getName(databaseHelper));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WriteDescriptor(DatabaseHelper databaseHelper, WaitForWriteDescriptor waitForWriteDescriptor) {
        super(waitForWriteDescriptor);
        byte[] bArr;
        if (UUID.fromString(this.uuid).equals(CCCD_UUID) && (bArr = this.bytes) != null && bArr.length == 2 && bArr[1] == 0) {
            byte b2 = bArr[0];
            if (b2 == 0) {
                setDescription("Disable notifications and indications for " + getParentName(databaseHelper));
                return;
            }
            if (b2 == 1) {
                setDescription("Enable notifications for " + getParentName(databaseHelper));
                return;
            }
            if (b2 == 2) {
                setDescription("Enable indications for " + getParentName(databaseHelper));
                return;
            }
        }
        if (!TextUtils.isEmpty(this.value)) {
            setDescription("Write 0x" + this.value + " to " + getName(databaseHelper));
            return;
        }
        if (!TextUtils.isEmpty(this.valueString)) {
            setDescription("Write '" + this.valueString + "' to " + getName(databaseHelper));
            return;
        }
        setDescription("Write empty packet to " + getName(databaseHelper));
    }
}
