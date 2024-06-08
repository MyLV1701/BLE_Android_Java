package no.nordicsemi.android.mcp.ble.parser;

import android.bluetooth.BluetoothGattDescriptor;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.gatt.CharacteristicExtendedPropertiesParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.CharacteristicPresentationFormatParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ClientCharacteristicConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ESConfigurationDescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ESMeasurementDescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ESTriggerSettingsDescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.GeneralDescriptorParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ReportReferenceParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.StringParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.UIntParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ValidRangeDescriptorParser;

/* loaded from: classes.dex */
public class DescriptorParser extends UuidLibrary {
    public static String getValueAsString(BluetoothGattDescriptor bluetoothGattDescriptor, Integer num, boolean z) {
        if (bluetoothGattDescriptor.getValue() == null) {
            return null;
        }
        if (bluetoothGattDescriptor.getValue().length == 0) {
            return "";
        }
        if (!z) {
            return GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
        if (num != null && num.intValue() == 1) {
            return StringParser.parse(bluetoothGattDescriptor);
        }
        try {
            String valueAsString = getValueAsString(bluetoothGattDescriptor);
            return valueAsString != null ? valueAsString : GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        } catch (Exception unused) {
            return "Invalid data syntax: " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
    }

    private static String getValueAsString(BluetoothGattDescriptor bluetoothGattDescriptor) {
        UUID uuid = bluetoothGattDescriptor.getUuid();
        try {
            if (UuidLibrary.CLIENT_CHARACTERISTIC_CONFIGURATION.equals(uuid)) {
                return ClientCharacteristicConfigurationParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.CHARACTERISTIC_USER_DESCRIPTION.equals(uuid)) {
                return StringParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.CHARACTERISTIC_PRESENTATION_FORMAT.equals(uuid)) {
                return CharacteristicPresentationFormatParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.REPORT_REFERENCE.equals(uuid)) {
                return ReportReferenceParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.NUMBER_OF_DIGITALS.equals(uuid)) {
                return UIntParser.parse(bluetoothGattDescriptor, 0, 1, (String) null);
            }
            if (UuidLibrary.CHARACTERISTIC_EXTENDED_PROPERTIES.equals(uuid)) {
                return CharacteristicExtendedPropertiesParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.ENVIRONMENTAL_SENSING_CONFIGURATION.equals(uuid)) {
                return ESConfigurationDescriptorParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.ENVIRONMENTAL_SENSING_MEASUREMENT.equals(uuid)) {
                return ESMeasurementDescriptorParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.ENVIRONMENTAL_SENSING_TRIGGER_SETTING.equals(uuid)) {
                return ESTriggerSettingsDescriptorParser.parse(bluetoothGattDescriptor);
            }
            if (UuidLibrary.VALID_RANGE.equals(uuid)) {
                return ValidRangeDescriptorParser.parse(bluetoothGattDescriptor);
            }
            return null;
        } catch (Exception unused) {
            return "Invalid data syntax: " + GeneralDescriptorParser.parse(bluetoothGattDescriptor);
        }
    }
}
