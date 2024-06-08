package no.nordicsemi.android.mcp.ble.parser;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.AdvDataWithStats;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.AdvertisingIntervalParser;
import no.nordicsemi.android.mcp.ble.parser.gap.AnkiLocalNameDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.AnkiManufacturerDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.AppearanceParser;
import no.nordicsemi.android.mcp.ble.parser.gap.BeaconParser;
import no.nordicsemi.android.mcp.ble.parser.gap.CompleteLocalNameParser;
import no.nordicsemi.android.mcp.ble.parser.gap.DeviceAddressParser;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.ble.parser.gap.LERoleParser;
import no.nordicsemi.android.mcp.ble.parser.gap.LeSupportedFeatures;
import no.nordicsemi.android.mcp.ble.parser.gap.ManufacturerDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.MeshParser;
import no.nordicsemi.android.mcp.ble.parser.gap.ServicesParser;
import no.nordicsemi.android.mcp.ble.parser.gap.ShortenedLocalNameParser;
import no.nordicsemi.android.mcp.ble.parser.gap.SlaveConnectionIntervalRangeParser;
import no.nordicsemi.android.mcp.ble.parser.gap.ThingyManufacturerDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.TxPowerLevelParser;
import no.nordicsemi.android.mcp.ble.parser.gap.UriParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.BatteryParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.ByteParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.CharacterParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.FloatParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.IntParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.PressureParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.ServiceDataParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.ShortParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.StringParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.TemperatureParser;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.URIBeaconParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GenericAccessProfileParser {
    private static final int ADVERTISING_INTERVAL = 26;
    private static final int APPEARANCE_DATA = 25;
    private static final short BATTERY_SERVICE_UUID = 6159;
    private static final int COMPLETE_LOCAL_NAME = 9;
    private static final short EDDYSTONE_SERVICE_UUID = -342;
    private static final int FLAGS = 1;
    private static final int LE_BLUETOOTH_DEVICE_ADDRESS = 27;
    private static final int LE_ROLE = 28;
    private static final int LE_SUPPORTED_FEATURES = 39;
    private static final int MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int MESH_BEACON = 43;
    private static final int MESH_MESSAGE = 42;
    private static final int MESH_PB_ADV = 41;
    private static final short MESH_PROVISIONING_SERVICE_UUID = 6183;
    private static final short MESH_PROXY_SERVICE_UUID = 6184;
    private static final short PRESSURE_CHARACTERISTIC__UUID = 10861;
    private static final int PUBLIC_TARGET_ADDRESS = 23;
    private static final int RANDOM_TARGET_ADDRESS = 24;
    private static final int SERVICES_COMPLETE_LIST_128_BIT = 7;
    private static final int SERVICES_COMPLETE_LIST_16_BIT = 3;
    private static final int SERVICES_COMPLETE_LIST_32_BIT = 5;
    private static final int SERVICES_MORE_AVAILABLE_128_BIT = 6;
    private static final int SERVICES_MORE_AVAILABLE_16_BIT = 2;
    private static final int SERVICES_MORE_AVAILABLE_32_BIT = 4;
    private static final int SERVICES_SOLICITATION_128_BIT = 21;
    private static final int SERVICES_SOLICITATION_16_BIT = 20;
    private static final int SERVICES_SOLICITATION_32_BIT = 31;
    private static final int SERVICE_DATA_128_BIT = 33;
    private static final int SERVICE_DATA_16_BIT = 22;
    private static final int SERVICE_DATA_32_BIT = 32;
    private static final int SHORTENED_LOCAL_NAME = 8;
    private static final int SLAVE_CONNECTION_INTERVAL_RANGE = 18;
    private static final String TAG = "GAPParser";
    private static final short TEMPERATURE_CHARACTERISTIC__UUID = 10862;
    private static final short TEMPERATURE_SERVICE_UUID = 6153;
    private static final short THREAD_TOBLE_SERVICE_UUID = -5;
    private static final int TX_POWER_LEVEL_NAME = 10;
    private static final int URI = 36;
    private static final short URI_BEACON_UUID = -268;

    public static AdvDataWithStats createDataWithStats(DatabaseHelper databaseHelper, BluetoothDevice bluetoothDevice, byte[] bArr) {
        AdvDataWithStats advDataWithStats = new AdvDataWithStats();
        parse(advDataWithStats, bluetoothDevice.getType());
        parseAdvertisingType(advDataWithStats, (ScanResult) null);
        parse(databaseHelper, advDataWithStats, bArr);
        return advDataWithStats;
    }

    public static void parse(AdvData advData, int i) {
        advData.newInfo().addData("Device type", ParserUtils.deviceTypeTyString(i));
    }

    private static void parseAdvertisingType(AdvData advData, ScanResult scanResult) {
        parseAdvertisingType(advData, scanResult == null || Build.VERSION.SDK_INT < 26 || scanResult.isLegacy());
    }

    private static void parseData(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 == 1) {
            ByteParser.parse(dataUnion, bArr, i, i2);
            CharacterParser.parse(dataUnion, bArr, i, i2);
        } else if (i2 == 2) {
            FloatParser.parse(dataUnion, bArr, i, i2);
            ShortParser.parse(dataUnion, bArr, i, i2);
        } else if (i2 == 4) {
            FloatParser.parse(dataUnion, bArr, i, i2);
            IntParser.parse(dataUnion, bArr, i, i2);
        }
        StringParser.parse(dataUnion, bArr, i, i2);
    }

    private static void parseServiceData(AdvData advData, DataUnion dataUnion, short s, byte[] bArr, int i, int i2) {
    }

    private static void parseServiceDataHighImportance(DatabaseHelper databaseHelper, AdvData advData, DataUnion dataUnion, short s, byte[] bArr, int i, int i2) {
        ServicesParser.updateAppearance(advData, s);
        if (s == -342) {
            BeaconParser.parseEddystoneBeacon(databaseHelper, advData, dataUnion, bArr, i, i2);
            return;
        }
        if (s == -268) {
            URIBeaconParser.parse(advData, dataUnion, bArr, i, i2);
            return;
        }
        if (s != -5) {
            if (s != 6153) {
                if (s == 6159) {
                    BatteryParser.parse(dataUnion, bArr, i, i2);
                    return;
                }
                if (s == 6183) {
                    MeshParser.parseBeacon(advData, dataUnion, bArr, i, i2, true);
                    return;
                }
                if (s == 6184) {
                    MeshParser.parseProxy(advData, dataUnion, bArr, i, i2);
                    return;
                } else if (s == 10861) {
                    PressureParser.parse(dataUnion, bArr, i, i2);
                    return;
                } else if (s != 10862) {
                    return;
                }
            }
            TemperatureParser.parse(dataUnion, bArr, i, i2);
            return;
        }
        BeaconParser.parseThreadToBLEBeacon(advData, dataUnion, bArr, i, i2);
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }

    public static void parse(AdvData advData, boolean z, int i, int i2, Integer num) {
        if (z) {
            return;
        }
        advData.newInfo().addData("Data status", ParserUtils.dataStatusToString(0));
        advData.newInfo().addData("Primary PHY", ParserUtils.phyToString(i));
        advData.newInfo().addData("Secondary PHY", ParserUtils.phyToString(i2));
        advData.newInfo().addData("Advertising Set ID", "1");
        if (num == null || num.intValue() == 127) {
            return;
        }
        advData.newInfo().addData("Tx Power", ParserUtils.txPowerToString(num.intValue()));
    }

    public static void parseAdvertisingType(AdvData advData, boolean z) {
        advData.newInfo().addData("Advertising type", ParserUtils.advertisingTypeToString(z));
    }

    public static AdvDataWithStats createDataWithStats(DatabaseHelper databaseHelper, BluetoothDevice bluetoothDevice, byte[] bArr, ScanResult scanResult) {
        AdvDataWithStats advDataWithStats = new AdvDataWithStats();
        parse(advDataWithStats, bluetoothDevice.getType());
        parseAdvertisingType(advDataWithStats, scanResult);
        if (Build.VERSION.SDK_INT >= 26) {
            advDataWithStats.setConnectible(scanResult.isConnectable());
            parse(advDataWithStats, scanResult);
        }
        parse(databaseHelper, advDataWithStats, bArr);
        return advDataWithStats;
    }

    public static void parse(AdvData advData, ScanResult scanResult) {
        if (scanResult.isLegacy()) {
            return;
        }
        advData.newInfo().addData("Data status", ParserUtils.dataStatusToString(scanResult.getDataStatus()));
        advData.newInfo().addData("Primary PHY", ParserUtils.phyToString(scanResult.getPrimaryPhy()));
        if (scanResult.getSecondaryPhy() != 0) {
            advData.newInfo().addData("Secondary PHY", ParserUtils.phyToString(scanResult.getSecondaryPhy()));
        }
        if (scanResult.getAdvertisingSid() != 255) {
            advData.newInfo().addData("Advertising Set ID", ParserUtils.advertisingSidToString(scanResult.getAdvertisingSid()));
        }
        if (scanResult.getPeriodicAdvertisingInterval() != 0) {
            advData.newInfo().addData("Periodic advertising interval", ParserUtils.periodicAdvertisingIntervalToString(scanResult.getPeriodicAdvertisingInterval()));
        }
        if (scanResult.getTxPower() != 127) {
            advData.newInfo().addData("Tx Power", ParserUtils.txPowerToString(scanResult.getTxPower()));
        }
    }

    public static void parse(DatabaseHelper databaseHelper, AdvData advData, byte[] bArr) {
        parse(databaseHelper, advData, bArr, false, 0);
    }

    public static void parse(DatabaseHelper databaseHelper, AdvData advData, byte[] bArr, byte[] bArr2, boolean z, int i) {
        int unsignedByteToInt;
        if (bArr == null && bArr2 == null) {
            return;
        }
        if (bArr2 == null) {
            parse(databaseHelper, advData, bArr, z, i);
            return;
        }
        if (bArr == null) {
            parse(databaseHelper, advData, bArr2, z, i);
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < bArr.length && (unsignedByteToInt = unsignedByteToInt(bArr[i2])) != 0) {
            int i3 = unsignedByteToInt + 1;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i2, i3);
            int hashCode = Arrays.hashCode(copyOfRange);
            if (!arrayList.contains(Integer.valueOf(hashCode))) {
                arrayList.add(Integer.valueOf(hashCode));
                try {
                    byteArrayOutputStream.write(copyOfRange);
                } catch (IOException e2) {
                    Log.e(TAG, "Writing to OS failed", e2);
                }
            }
            i2 += i3;
        }
        parse(databaseHelper, advData, byteArrayOutputStream.toByteArray(), z, i);
    }

    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v2 */
    public static void parse(DatabaseHelper databaseHelper, AdvData advData, byte[] bArr, boolean z, int i) {
        int unsignedByteToInt;
        if (bArr == null) {
            return;
        }
        ?? r9 = 0;
        int i2 = 0;
        while (true) {
            if (i2 < bArr.length && (unsignedByteToInt = unsignedByteToInt(bArr[i2])) != 0) {
                int i3 = i2 + 1;
                if (i3 == bArr.length) {
                    advData.lastInfo().addData("Invalid EIR", unsignedByteToInt + " bytes missing");
                } else {
                    int unsignedByteToInt2 = unsignedByteToInt(bArr[i3]);
                    if (unsignedByteToInt2 == 18) {
                        SlaveConnectionIntervalRangeParser.parse(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                    } else if (unsignedByteToInt2 == 36) {
                        UriParser.parse(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                    } else if (unsignedByteToInt2 == 39) {
                        LeSupportedFeatures.parse(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                    } else if (unsignedByteToInt2 != 255) {
                        switch (unsignedByteToInt2) {
                            case 1:
                                FlagsParser.parse(advData.newInfo(), bArr[i3 + 1]);
                                break;
                            case 2:
                                ServicesParser.parseMoreAvailable16UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 3:
                                ServicesParser.parseCompleteList16UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 4:
                                ServicesParser.parseMoreAvailable32UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 5:
                                ServicesParser.parseCompleteList32UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 6:
                                ServicesParser.parseMoreAvailable128UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 7:
                                ServicesParser.parseCompleteList128UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 8:
                                ShortenedLocalNameParser.parse(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                break;
                            case 9:
                                DataUnion newInfo = advData.newInfo();
                                if (advData.getAppearance() == 3) {
                                    AnkiLocalNameDataParser.parse(advData, newInfo, bArr, i3 + 1, unsignedByteToInt - 1);
                                }
                                CompleteLocalNameParser.parse(advData, newInfo, bArr, i3 + 1, unsignedByteToInt - 1, advData.getAppearance() != 3, z);
                                break;
                            case 10:
                                TxPowerLevelParser.parse(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1, z, i);
                                break;
                            default:
                                switch (unsignedByteToInt2) {
                                    case 20:
                                        ServicesParser.parseSolicitation16UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 21:
                                        ServicesParser.parseSolicitation128UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 22:
                                        int i4 = i3 + 1;
                                        short decodeUuid16 = (short) ParserUtils.decodeUuid16(bArr, i4);
                                        DataUnion newInfo2 = advData.newInfo();
                                        newInfo2.setServiceUuid(bArr, i4, 2);
                                        int i5 = i3 + 3;
                                        int i6 = unsignedByteToInt - 3;
                                        parseServiceDataHighImportance(databaseHelper, advData, newInfo2, decodeUuid16, bArr, i5, i6);
                                        ServiceDataParser.parse16Bit(newInfo2, decodeUuid16, bArr, i5, i6);
                                        parseServiceData(advData, newInfo2, decodeUuid16, bArr, i5, i6);
                                        parseData(newInfo2, bArr, i5, i6);
                                        break;
                                    case 23:
                                        DeviceAddressParser.parsePublicAddresses(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 24:
                                        DeviceAddressParser.parseRandomAddresses(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 25:
                                        AppearanceParser.parse(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 26:
                                        AdvertisingIntervalParser.parse(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 27:
                                        DeviceAddressParser.parseBluetoothAddress(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    case 28:
                                        LERoleParser.parse(advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                        break;
                                    default:
                                        switch (unsignedByteToInt2) {
                                            case 31:
                                                ServicesParser.parseSolicitation32UUID(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                                break;
                                            case 32:
                                                int i7 = i3 + 1;
                                                int decodeUuid32 = ParserUtils.decodeUuid32(bArr, i7);
                                                DataUnion newInfo3 = advData.newInfo();
                                                newInfo3.setServiceUuid(bArr, i7, 4);
                                                int i8 = i3 + 5;
                                                int i9 = unsignedByteToInt - 5;
                                                ServiceDataParser.parse32Bit(newInfo3, decodeUuid32, bArr, i8, i9);
                                                parseData(newInfo3, bArr, i8, i9);
                                                break;
                                            case 33:
                                                int i10 = i3 + 1;
                                                UUID decodeUuid128 = ParserUtils.decodeUuid128(bArr, i10);
                                                DataUnion newInfo4 = advData.newInfo();
                                                newInfo4.setServiceUuid(bArr, i10, 16);
                                                int i11 = i3 + 17;
                                                int i12 = unsignedByteToInt - 17;
                                                ServiceDataParser.parse128Bit(newInfo4, decodeUuid128, bArr, i11, i12);
                                                parseData(newInfo4, bArr, i11, i12);
                                                break;
                                            default:
                                                switch (unsignedByteToInt2) {
                                                    case 41:
                                                        MeshParser.parsePbAdv(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                                        break;
                                                    case 42:
                                                        MeshParser.parseMessage(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1);
                                                        break;
                                                    case 43:
                                                        MeshParser.parseBeacon(advData, advData.newInfo(), bArr, i3 + 1, unsignedByteToInt - 1, false);
                                                        break;
                                                    default:
                                                        try {
                                                            DataUnion newInfo5 = advData.newInfo();
                                                            Locale locale = Locale.US;
                                                            Object[] objArr = new Object[1];
                                                            objArr[r9] = Integer.valueOf(unsignedByteToInt2);
                                                            newInfo5.addData(String.format(locale, "Unknown EIR (0x%02X)", objArr), ParserUtils.bytesToHex(bArr, i3 + 1, unsignedByteToInt - 1, r9));
                                                            break;
                                                        } catch (Exception unused) {
                                                            advData.lastInfo().addData(String.format(Locale.US, "Error while parsing EIR (0x%02X)", Integer.valueOf(unsignedByteToInt2)), ParserUtils.bytesToHex(bArr, i3 + 1, unsignedByteToInt - 1, true));
                                                            break;
                                                        }
                                                }
                                        }
                                }
                        }
                    } else {
                        DataUnion newInfo6 = advData.newInfo();
                        int i13 = i3 + 1;
                        newInfo6.setServiceUuid(bArr, i13, 2);
                        int i14 = unsignedByteToInt - 1;
                        BeaconParser.parseIBeacon(advData, newInfo6, bArr, i13, i14);
                        BeaconParser.parseAltBeacon(advData, newInfo6, bArr, i13, i14);
                        BeaconParser.parseMicrosoftSwiftPairBeacon(advData, newInfo6, bArr, i13, i14);
                        BeaconParser.parseMicrosoftAdvertisingBeacon(advData, newInfo6, bArr, i13, i14);
                        if (advData.getAppearance() == 3) {
                            AnkiManufacturerDataParser.parse(advData, newInfo6, bArr, i13, 8);
                        }
                        ThingyManufacturerDataParser.parse(advData, newInfo6, bArr, i13, i14);
                        ManufacturerDataParser.parse(advData, newInfo6, bArr, i13, i14);
                        parseData(newInfo6, bArr, i3 + 3, unsignedByteToInt - 3);
                    }
                    i2 = i3 + unsignedByteToInt;
                    r9 = 0;
                }
            }
        }
        byte[] rawData = advData.getRawData();
        if (rawData != null && rawData.length != 0) {
            byte[] bArr2 = new byte[rawData.length + i2];
            System.arraycopy(rawData, 0, bArr2, 0, rawData.length);
            System.arraycopy(bArr, 0, bArr2, rawData.length, i2);
            advData.setRawData(bArr2);
            return;
        }
        advData.setRawData(Arrays.copyOfRange(bArr, 0, i2));
    }
}
