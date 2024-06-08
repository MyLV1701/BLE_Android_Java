package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.UuidLibrary;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ESTriggerSettingsDescriptorParser extends UuidLibrary {
    private static void invalidLength(StringBuilder sb, byte[] bArr) {
        sb.append("Invalid operand length: ");
        sb.append(ParserUtils.bytesToHex(bArr, 1, bArr.length - 1, true));
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        byte[] value = bluetoothGattDescriptor.getValue();
        StringBuilder sb = new StringBuilder();
        byte b2 = value[0];
        switch (b2) {
            case 0:
                sb.append("Trigger inactive");
                if (value.length > 1) {
                    sb.append("\nRedundant operand: ");
                    sb.append(ParserUtils.bytesToHex(value, 1, value.length - 1, true));
                    break;
                }
                break;
            case 1:
                if (value.length >= 4) {
                    sb.append("Use a fixed time interval between transmissions: ");
                    sb.append(ParserUtils.getIntValue(value, 19, 1));
                    sb.append(" sec.");
                    if (value.length > 4) {
                        sb.append("\nRedundant bytes: ");
                        sb.append(ParserUtils.bytesToHex(value, 4, value.length - 4, true));
                        break;
                    }
                } else {
                    sb.append("Use a fixed time interval between transmissions\nInvalid operand (3 bytes expected): ");
                    sb.append(ParserUtils.bytesToHex(value, 1, value.length - 1, true));
                    break;
                }
                break;
            case 2:
                if (value.length >= 4) {
                    sb.append("No less than the specified time between transmissions: ");
                    sb.append(ParserUtils.getIntValue(value, 19, 1));
                    sb.append(" sec.");
                    if (value.length > 4) {
                        sb.append("\nRedundant bytes: ");
                        sb.append(ParserUtils.bytesToHex(value, 4, value.length - 4, true));
                        break;
                    }
                } else {
                    sb.append("No less than the specified time between transmissions\nInvalid operand (3 bytes expected): ");
                    sb.append(ParserUtils.bytesToHex(value, 1, value.length - 1, true));
                    break;
                }
                break;
            case 3:
                sb.append("When value changes compared to previous value");
                if (value.length > 1) {
                    sb.append("\nRedundant operand: ");
                    sb.append(ParserUtils.bytesToHex(value, 1, value.length - 1, true));
                    break;
                }
                break;
            case 4:
                sb.append("While less than the specified value: ");
                break;
            case 5:
                sb.append("While less than or equal to the specified value: ");
                break;
            case 6:
                sb.append("While greater than the specified value: ");
                break;
            case 7:
                sb.append("While greater than or equal to the specified value: ");
                break;
            case 8:
                sb.append("While equal to the specified value: ");
                break;
            case 9:
                sb.append("While not equal to the specified value: ");
                break;
            default:
                sb.append("Condition value reserved for future use (");
                sb.append((int) b2);
                sb.append(")");
                if (value.length > 1) {
                    sb.append("\nUnsupported operand: ");
                    sb.append(ParserUtils.bytesToHex(value, 1, value.length - 1, true));
                    break;
                }
                break;
        }
        if (b2 >= 4 && b2 <= 9) {
            UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
            if (UuidLibrary.APPARENT_WIND_DIRECTION.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(AngleParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.APPARENT_WIND_SPEED.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(VelocityMPSParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.DEW_POINT.equals(uuid)) {
                if (value.length == 2) {
                    sb.append(CelsiusParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.ELEVATION.equals(uuid)) {
                if (value.length == 4) {
                    sb.append(ElevationParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.GUST_FACTOR.equals(uuid)) {
                if (value.length == 2) {
                    sb.append(GustFactorParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.HEAT_INDEX.equals(uuid)) {
                if (value.length == 2) {
                    sb.append(CelsiusParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.HUMIDITY.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(HumidityParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.IRRADIANCE.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(IrradianceParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.POLLEN_CONCENTRATION.equals(uuid)) {
                if (value.length == 4) {
                    sb.append(PollenConcentrationParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.RAINFALL.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(UIntParser.parse(bluetoothGattDescriptor, 1, 2, "mm"));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.PRESSURE.equals(uuid)) {
                if (value.length == 5) {
                    sb.append(PressureParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.TEMPERATURE.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(TemperatureParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.TRUE_WIND_DIRECTION.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(AngleParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.TRUE_WIND_SPEED.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(VelocityMPSParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.UV_INDEX.equals(uuid)) {
                if (value.length == 2) {
                    sb.append(UIntParser.parse(bluetoothGattDescriptor, 1, 1, (String) null));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.WIND_CHILL.equals(uuid)) {
                if (value.length == 2) {
                    sb.append(CelsiusParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.BAROMETRIC_PRESSURE_TREND.equals(uuid)) {
                if (value.length == 2) {
                    sb.append(BarometricPressureTrendParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.MAGNETIC_DECLINATION.equals(uuid)) {
                if (value.length == 3) {
                    sb.append(AngleParser.parse(bluetoothGattDescriptor, 1));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.MAGNETIC_FLUX_DENSITY_2D.equals(uuid)) {
                if (value.length == 5) {
                    sb.append(MagneticFluxDensityParser.parse(bluetoothGattDescriptor, 1, 4));
                } else {
                    invalidLength(sb, value);
                }
            } else if (UuidLibrary.MAGNETIC_FLUX_DENSITY_3D.equals(uuid)) {
                if (value.length == 7) {
                    sb.append(MagneticFluxDensityParser.parse(bluetoothGattDescriptor, 1, 6));
                } else {
                    invalidLength(sb, value);
                }
            } else {
                sb.append("Operand: ");
                sb.append(ParserUtils.bytesToHex(value, 1, value.length - 1, true));
            }
        }
        return sb.toString();
    }
}
