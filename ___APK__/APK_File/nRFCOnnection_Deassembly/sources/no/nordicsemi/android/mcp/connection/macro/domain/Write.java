package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.connection.macro.domain.AssertService;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;

/* loaded from: classes.dex */
public class Write extends CharacteristicOperationWithValue {

    @Attribute(required = false)
    private Type type;

    /* loaded from: classes.dex */
    public enum Type {
        WRITE_REQUEST(2),
        WRITE_COMMAND(1);

        private int value;

        Type(int i) {
            this.value = i;
        }
    }

    public Write() {
        this.type = Type.WRITE_REQUEST;
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            if (characteristic.getWriteType() != this.type.value) {
                iBluetoothLeConnection.setWriteType(characteristic, this.type.value);
            }
            iBluetoothLeConnection.writeCharacteristic(characteristic, this.bytes);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public AssertService.AssertCharacteristic.Property getRequiredProperty() {
        if (this.type == Type.WRITE_COMMAND) {
            return new AssertService.AssertCharacteristic.Property(AssertService.AssertCharacteristic.Property.Name.WRITE_WITHOUT_RESPONSE);
        }
        return new AssertService.AssertCharacteristic.Property(AssertService.AssertCharacteristic.Property.Name.WRITE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean hasAccessToTemporaryServices() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new WaitForWrite(databaseHelper, this)};
    }

    public Write(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, true);
        this.type = Type.WRITE_REQUEST;
        this.bytes = bArr;
        this.type = bluetoothGattCharacteristic.getWriteType() == 1 ? Type.WRITE_COMMAND : Type.WRITE_REQUEST;
        this.value = ParserUtils.bytesToHex(this.bytes, false);
        if (!TextUtils.isEmpty(this.value)) {
            setDescription("Write 0x" + this.value + " to " + getName(databaseHelper));
            return;
        }
        setDescription("Write empty packet to " + getName(databaseHelper));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Write(DatabaseHelper databaseHelper, WaitForWrite waitForWrite) {
        super(waitForWrite);
        this.type = Type.WRITE_REQUEST;
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
