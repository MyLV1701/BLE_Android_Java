package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.connection.macro.domain.AssertService;
import no.nordicsemi.android.mcp.database.init.ThirdPartyCharacteristics;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.Validate;

/* loaded from: classes.dex */
public class UnlockEddystone extends CharacteristicOperation {
    private byte[] bytes;

    @Attribute
    private String key;

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

    public UnlockEddystone() {
        this.type = Type.WRITE_REQUEST;
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            byte[] value = characteristic.getValue();
            if (value != null && value.length == 16) {
                if (characteristic.getWriteType() != this.type.value) {
                    iBluetoothLeConnection.setWriteType(characteristic, this.type.value);
                }
                iBluetoothLeConnection.unlockEddystone(characteristic, this.bytes);
                return;
            }
            iBluetoothLeConnection.abortOperation();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        SetCharacteristicValue setCharacteristicValue = new SetCharacteristicValue();
        setCharacteristicValue.serviceUuid = this.serviceUuid;
        setCharacteristicValue.serviceInstanceId = this.serviceInstanceId;
        setCharacteristicValue.characteristicUuid = ThirdPartyCharacteristics.EDDYSTONE_LOCK_STATE_UUID.toString();
        setCharacteristicValue.value = "01";
        setCharacteristicValue.bytes = new byte[]{1};
        setCharacteristicValue.setDescription("Mark beacon as unlocked");
        return new Operation[]{new WaitForWrite(databaseHelper, this), setCharacteristicValue};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    @Validate
    public void validate() {
        super.validate();
        this.bytes = hexStringToByteArray(this.key);
    }

    public UnlockEddystone(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, true);
        this.type = Type.WRITE_REQUEST;
        this.bytes = bArr;
        this.type = bluetoothGattCharacteristic.getWriteType() == 1 ? Type.WRITE_COMMAND : Type.WRITE_REQUEST;
        this.key = ParserUtils.bytesToHex(bArr, false);
        setDescription("Unlock Eddystone with key 0x" + this.key);
    }
}
