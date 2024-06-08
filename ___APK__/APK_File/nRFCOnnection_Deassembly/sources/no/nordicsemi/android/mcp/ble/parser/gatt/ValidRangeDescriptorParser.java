package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattDescriptor;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.UuidLibrary;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ValidRangeDescriptorParser extends UuidLibrary {
    private static void invalidLength(StringBuilder sb, byte[] bArr) {
        sb.append("Invalid value: ");
        sb.append(ParserUtils.bytesToHex(bArr, 0, bArr.length, true));
    }

    public static String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
        StringBuilder sb = new StringBuilder();
        byte[] value = bluetoothGattDescriptor.getValue();
        UUID uuid = bluetoothGattDescriptor.getCharacteristic().getUuid();
        if (UuidLibrary.APPARENT_WIND_DIRECTION.equals(uuid)) {
            if (value.length == 4) {
                write(sb, AngleParser.parse(bluetoothGattDescriptor, 0), AngleParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.APPARENT_WIND_SPEED.equals(uuid)) {
            if (value.length == 4) {
                write(sb, VelocityMPSParser.parse(bluetoothGattDescriptor, 0), VelocityMPSParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.DEW_POINT.equals(uuid)) {
            if (value.length == 2) {
                write(sb, CelsiusParser.parse(bluetoothGattDescriptor, 0), CelsiusParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.ELEVATION.equals(uuid)) {
            if (value.length == 6) {
                write(sb, ElevationParser.parse(bluetoothGattDescriptor, 0), ElevationParser.parse(bluetoothGattDescriptor, 3));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.GUST_FACTOR.equals(uuid)) {
            if (value.length == 2) {
                write(sb, GustFactorParser.parse(bluetoothGattDescriptor, 0), GustFactorParser.parse(bluetoothGattDescriptor, 1));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.HEAT_INDEX.equals(uuid)) {
            if (value.length == 2) {
                write(sb, CelsiusParser.parse(bluetoothGattDescriptor, 0), CelsiusParser.parse(bluetoothGattDescriptor, 1));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.HUMIDITY.equals(uuid)) {
            if (value.length == 4) {
                write(sb, HumidityParser.parse(bluetoothGattDescriptor, 0), HumidityParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.IRRADIANCE.equals(uuid)) {
            if (value.length == 4) {
                write(sb, IrradianceParser.parse(bluetoothGattDescriptor, 0), IrradianceParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.POLLEN_CONCENTRATION.equals(uuid)) {
            if (value.length == 6) {
                write(sb, PollenConcentrationParser.parse(bluetoothGattDescriptor, 0), PollenConcentrationParser.parse(bluetoothGattDescriptor, 3));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.RAINFALL.equals(uuid)) {
            if (value.length == 3) {
                write(sb, UIntParser.parse(bluetoothGattDescriptor, 0, 2, "mm"), UIntParser.parse(bluetoothGattDescriptor, 2, 2, "mm"));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.PRESSURE.equals(uuid)) {
            if (value.length == 8) {
                write(sb, PressureParser.parse(bluetoothGattDescriptor, 0), PressureParser.parse(bluetoothGattDescriptor, 4));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.TEMPERATURE.equals(uuid)) {
            if (value.length == 4) {
                write(sb, TemperatureParser.parse(bluetoothGattDescriptor, 0), TemperatureParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.TRUE_WIND_DIRECTION.equals(uuid)) {
            if (value.length == 4) {
                write(sb, AngleParser.parse(bluetoothGattDescriptor, 0), AngleParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.TRUE_WIND_SPEED.equals(uuid)) {
            if (value.length == 4) {
                write(sb, VelocityMPSParser.parse(bluetoothGattDescriptor, 0), VelocityMPSParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.UV_INDEX.equals(uuid)) {
            if (value.length == 2) {
                write(sb, UIntParser.parse(bluetoothGattDescriptor, 0, 1, (String) null), UIntParser.parse(bluetoothGattDescriptor, 1, 1, (String) null));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.WIND_CHILL.equals(uuid)) {
            if (value.length == 2) {
                write(sb, CelsiusParser.parse(bluetoothGattDescriptor, 0), CelsiusParser.parse(bluetoothGattDescriptor, 1));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.BAROMETRIC_PRESSURE_TREND.equals(uuid)) {
            if (value.length == 2) {
                write(sb, BarometricPressureTrendParser.parse(bluetoothGattDescriptor, 0), BarometricPressureTrendParser.parse(bluetoothGattDescriptor, 1));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.MAGNETIC_DECLINATION.equals(uuid)) {
            if (value.length == 4) {
                write(sb, AngleParser.parse(bluetoothGattDescriptor, 0), AngleParser.parse(bluetoothGattDescriptor, 2));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.MAGNETIC_FLUX_DENSITY_2D.equals(uuid)) {
            if (value.length == 8) {
                write(sb, MagneticFluxDensityParser.parse(bluetoothGattDescriptor, 0, 4), MagneticFluxDensityParser.parse(bluetoothGattDescriptor, 4, 4));
            } else {
                invalidLength(sb, value);
            }
        } else if (UuidLibrary.MAGNETIC_FLUX_DENSITY_3D.equals(uuid)) {
            if (value.length == 12) {
                write(sb, MagneticFluxDensityParser.parse(bluetoothGattDescriptor, 0, 6), MagneticFluxDensityParser.parse(bluetoothGattDescriptor, 6, 6));
            } else {
                invalidLength(sb, value);
            }
        } else if (value.length % 2 == 0) {
            write(sb, ParserUtils.bytesToHex(value, 0, value.length / 2, true), ParserUtils.bytesToHex(value, value.length / 2, value.length / 2, true));
        } else {
            invalidLength(sb, value);
        }
        return sb.toString();
    }

    private static void write(StringBuilder sb, String str, String str2) {
        sb.append("Lower inclusive value: ");
        sb.append(str);
        sb.append("\nUpper inclusive value: ");
        sb.append(str2);
    }
}
