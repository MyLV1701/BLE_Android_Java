package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.IBluetoothLeBasicConnection;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import org.simpleframework.xml.Element;

/* loaded from: classes.dex */
public abstract class CharacteristicOperationWithValueAssertion extends CharacteristicOperation {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    @Element(required = false)
    protected AssertValue assertValue;

    public CharacteristicOperationWithValueAssertion() {
    }

    private static AssertValue parseValueOf(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value != null && value.length != 0) {
            int length = value.length;
            char[] cArr = new char[length * 2];
            boolean z = true;
            for (int i = 0; i < length; i++) {
                int i2 = value[i] & FlagsParser.UNKNOWN_FLAGS;
                z = z && i2 >= 32 && i2 <= 126;
                int i3 = i * 2;
                char[] cArr2 = HEX_ARRAY;
                cArr[i3] = cArr2[i2 >>> 4];
                cArr[i3 + 1] = cArr2[i2 & 15];
            }
            if (!z) {
                String str = new String(cArr);
                return new AssertValue("Assert value equals 0x" + str, str);
            }
            String str2 = new String(value, 0, length);
            return new AssertValue("Assert value equals '" + str2 + "'", str2, true);
        }
        return new AssertValue("Assert value is empty", "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void assertValue(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.assertValue = parseValueOf(bluetoothGattCharacteristic);
    }

    @Override // no.nordicsemi.android.mcp.domain.common.HasDescription
    public String getDescription() {
        AssertValue assertValue = this.assertValue;
        if (assertValue != null && !TextUtils.isEmpty(assertValue.getDescription())) {
            return super.getDescription() + "\n" + this.assertValue.getDescription();
        }
        return super.getDescription();
    }

    @Override // no.nordicsemi.android.mcp.connection.macro.domain.Operation
    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        if (this.assertValue == null) {
            return true;
        }
        if (hasAccessToTemporaryServices()) {
            invalidate();
        }
        return this.assertValue.validateResult(getCharacteristic(iBluetoothLeConnection));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicOperationWithValueAssertion(IBluetoothLeBasicConnection iBluetoothLeBasicConnection, BluetoothGattCharacteristic bluetoothGattCharacteristic, boolean z) {
        super(iBluetoothLeBasicConnection, bluetoothGattCharacteristic, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharacteristicOperationWithValueAssertion(CharacteristicOperation characteristicOperation) {
        super(characteristicOperation);
    }
}
