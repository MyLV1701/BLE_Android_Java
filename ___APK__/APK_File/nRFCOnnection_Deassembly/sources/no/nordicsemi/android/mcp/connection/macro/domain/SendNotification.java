package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.connection.macro.domain.AssertService;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SendNotification extends CharacteristicOperationWithValue {
    public SendNotification() {
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected void execute(IBluetoothLeConnection iBluetoothLeConnection) {
        BluetoothGattCharacteristic characteristic = getCharacteristic(iBluetoothLeConnection);
        if (characteristic != null) {
            characteristic.setValue(this.bytes);
            iBluetoothLeConnection.sendCharacteristicNotification(characteristic);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public AssertService.AssertCharacteristic.Property getRequiredProperty() {
        return new AssertService.AssertCharacteristic.Property(AssertService.AssertCharacteristic.Property.Name.NOTIFY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.CharacteristicOperation
    public boolean isClientOperation() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return new Operation[]{new WaitForNotification(databaseHelper, this)};
    }

    public SendNotification(DatabaseHelper databaseHelper, IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, false);
        this.bytes = bluetoothGattCharacteristic.getValue();
        this.value = ParserUtils.bytesToHex(this.bytes, false);
        String characteristicName = databaseHelper.getCharacteristicName(bluetoothGattCharacteristic.getUuid());
        if (!TextUtils.isEmpty(this.value)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Send 0x");
            sb.append(this.value);
            sb.append(" as notification from ");
            sb.append(characteristicName == null ? bluetoothGattCharacteristic.getUuid() : characteristicName);
            setDescription(sb.toString());
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Send empty packet as notification from ");
        sb2.append(characteristicName == null ? this.characteristicUuid : characteristicName);
        setDescription(sb2.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SendNotification(DatabaseHelper databaseHelper, WaitForNotification waitForNotification) {
        super(waitForNotification);
        String characteristicName = databaseHelper.getCharacteristicName(UUID.fromString(this.characteristicUuid));
        if (!TextUtils.isEmpty(this.value)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Send 0x");
            sb.append(this.value);
            sb.append(" as notification from ");
            sb.append(characteristicName == null ? this.characteristicUuid : characteristicName);
            setDescription(sb.toString());
            return;
        }
        if (!TextUtils.isEmpty(this.valueString)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Send '");
            sb2.append(this.valueString);
            sb2.append("' as notification from ");
            sb2.append(characteristicName == null ? this.characteristicUuid : characteristicName);
            setDescription(sb2.toString());
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Send empty packet as notification from ");
        sb3.append(characteristicName == null ? this.characteristicUuid : characteristicName);
        setDescription(sb3.toString());
    }
}
