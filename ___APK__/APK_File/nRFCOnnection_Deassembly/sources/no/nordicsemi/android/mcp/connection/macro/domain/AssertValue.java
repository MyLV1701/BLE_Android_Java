package no.nordicsemi.android.mcp.connection.macro.domain;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.text.TextUtils;
import no.nordicsemi.android.mcp.ble.parser.gatt.StringParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.domain.common.HasDescription;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class AssertValue extends HasDescription {
    private final String ALL_ZEROS;

    @Attribute(required = false)
    String value;

    @Attribute(required = false)
    String valueString;

    public AssertValue() {
        this.ALL_ZEROS = "(00)*";
    }

    @Validate
    private void validate() {
        if (this.value == null && this.valueString == null) {
            throw new PersistenceException("No value specified for assert-value. Use 'value' for binary data or 'value-string' for text.", new Object[0]);
        }
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for assert-value", new Object[0]);
        }
        String str = this.value;
        if (str != null) {
            this.value = str.replaceAll("-", "");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean validateResult(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (this.value != null) {
            String bytesToHex = ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), false);
            if (this.value.equalsIgnoreCase(bytesToHex) || (TextUtils.isEmpty(bytesToHex) && this.value.matches("(00)*"))) {
                return true;
            }
        }
        if (this.valueString != null) {
            if (this.valueString.equalsIgnoreCase(StringParser.parse(bluetoothGattCharacteristic))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssertValue(String str, String str2) {
        super(str);
        this.ALL_ZEROS = "(00)*";
        this.value = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AssertValue(String str, String str2, boolean z) {
        super(str);
        this.ALL_ZEROS = "(00)*";
        this.valueString = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean validateResult(BluetoothGattDescriptor bluetoothGattDescriptor) {
        if (this.value != null) {
            String bytesToHex = ParserUtils.bytesToHex(bluetoothGattDescriptor.getValue(), false);
            if (this.value.equalsIgnoreCase(bytesToHex) || (TextUtils.isEmpty(bytesToHex) && this.value.matches("(00)*"))) {
                return true;
            }
        }
        if (this.valueString != null) {
            if (this.valueString.equalsIgnoreCase(StringParser.parse(bluetoothGattDescriptor))) {
                return true;
            }
        }
        return false;
    }
}
