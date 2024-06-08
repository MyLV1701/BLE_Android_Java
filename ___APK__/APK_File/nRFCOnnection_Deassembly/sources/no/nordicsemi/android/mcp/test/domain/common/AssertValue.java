package no.nordicsemi.android.mcp.test.domain.common;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;
import android.text.TextUtils;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.ble.parser.gatt.StringParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.domain.common.exception.SyntaxException;
import no.nordicsemi.android.mcp.test.ConstantsManager;
import no.nordicsemi.android.mcp.test.Result;
import no.nordicsemi.android.mcp.test.domain.enumeration.OperationResult;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root
/* loaded from: classes.dex */
public class AssertValue extends HasExpectedAndDescription {
    private final String ALL_ZEROS;

    @Attribute(required = false)
    private String value;

    @Attribute(required = false)
    private String valueString;

    public AssertValue() {
        super("Assert-Value");
        this.ALL_ZEROS = "(00)*";
    }

    public OperationResult execute(Context context, BluetoothGattCharacteristic bluetoothGattCharacteristic, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Logger.v(logSession, "Asserting characteristic value...");
        if (this.value != null) {
            String bytesToHex = ParserUtils.bytesToHex(bluetoothGattCharacteristic.getValue(), false);
            String decode = constantsManager.decode(this.value);
            if (!decode.replaceAll("-", "").equalsIgnoreCase(bytesToHex) && (!TextUtils.isEmpty(bytesToHex) || !decode.replaceAll("-", "").matches("(00)*"))) {
                logFail(result, logSession, "Value of the characteristic '" + bytesToHex + "' is not equal to '" + decode + "'");
            } else {
                logSuccess(result, logSession, "Value of the characteristic equals '" + decode + "'");
                return toResult(0);
            }
        }
        if (this.valueString != null) {
            String parse = StringParser.parse(bluetoothGattCharacteristic);
            String decode2 = constantsManager.decode(this.valueString);
            if (parse.matches(decode2)) {
                logSuccess(result, logSession, "Value of the characteristic matches '" + decode2 + "'");
                return toResult(0);
            }
            logFail(result, logSession, "Value of the characteristic '" + parse + "' does not match '" + decode2 + "'");
        }
        return toResult(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // no.nordicsemi.android.mcp.test.domain.common.HasExpectedAndDescription
    @Validate
    public void validate() {
        super.validate();
        if (this.value == null && this.valueString == null) {
            throw new PersistenceException("No value specified for assert-value. Use 'value' for binary data or 'value-string' for text.", new Object[0]);
        }
        if (this.value != null && this.valueString != null) {
            throw new PersistenceException("Both value-string and value specified for assert-value", new Object[0]);
        }
    }

    public AssertValue(@Attribute(name = "description") String str) {
        super(str);
        this.ALL_ZEROS = "(00)*";
    }

    public OperationResult execute(Context context, BluetoothGattDescriptor bluetoothGattDescriptor, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Logger.v(logSession, "Asserting descriptor value...");
        if (this.value != null) {
            String bytesToHex = ParserUtils.bytesToHex(bluetoothGattDescriptor.getValue(), false);
            String decode = constantsManager.decode(this.value);
            if (!decode.replaceAll("-", "").equalsIgnoreCase(bytesToHex) && (!TextUtils.isEmpty(bytesToHex) || !decode.replaceAll("-", "").matches("(00)*"))) {
                logFail(result, logSession, "Value of the descriptor '" + bytesToHex + "' is not equal to '" + decode + "'");
            } else {
                logSuccess(result, logSession, "Value of the descriptor equals '" + decode + "'");
                return toResult(0);
            }
        }
        if (this.valueString != null) {
            String parse = StringParser.parse(bluetoothGattDescriptor);
            String decode2 = constantsManager.decode(this.valueString);
            if (parse.matches(decode2)) {
                logSuccess(result, logSession, "Value of the descriptor matches '" + decode2 + "'");
                return toResult(0);
            }
            logFail(result, logSession, "Value of the descriptor '" + parse + "' does not match '" + decode2 + "'");
        }
        return toResult(-1);
    }

    public OperationResult execute(Context context, int i, ConstantsManager constantsManager, Result result, LogSession logSession) {
        Logger.v(logSession, "Asserting value...");
        String str = this.value;
        if (str != null) {
            boolean endsWith = str.endsWith("+");
            boolean endsWith2 = this.value.endsWith("-");
            boolean z = true;
            try {
                int parseInt = Integer.parseInt((endsWith || endsWith2) ? this.value.substring(0, this.value.length() - 1) : this.value);
                if (i != parseInt && ((!endsWith || i < parseInt) && (!endsWith2 || i > parseInt))) {
                    z = false;
                }
                if (z) {
                    logSuccess(result, logSession, "Obtained value " + i + " is in range");
                    return toResult(0);
                }
                logFail(result, logSession, "Obtained value " + i + " is out of range");
            } catch (NumberFormatException unused) {
                logFail(result, logSession, "The value '" + this.value + "' is not a valid integer");
            }
        }
        if (this.valueString == null) {
            return toResult(-1);
        }
        throw new SyntaxException("To compare Integers use the 'value' attribute");
    }
}
