package no.nordicsemi.android.mcp.ble.parser.gap;

import android.os.Build;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import no.nordicsemi.android.dfu.DfuBaseService;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.ble.parser.gap.servicedata.URIBeaconParser;
import no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class BeaconParser {
    private static float decode88FixedPointNotation(byte[] bArr, int i) {
        return bArr[i] + ((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) / 256.0f);
    }

    private static String decodeClassOfDevice(int i) {
        int i2 = i & 7936;
        int i3 = i & 252;
        switch (i2 | i3) {
            case 0:
                return "Miscellaneous";
            case 256:
                return "Computer/Uncategorized";
            case 260:
                return "Computer/Desktop workstation";
            case 264:
                return "Computer/Server-class computer";
            case 268:
                return "Computer/Laptop";
            case 272:
                return "Computer/Handheld PC/PDA";
            case 276:
                return "Computer/Palm-size PC/PDA";
            case 280:
                return "Computer/Wearable computer";
            case 284:
                return "Computer/Tablet";
            case DfuBaseService.ERROR_REMOTE_TYPE_SECURE /* 512 */:
                return "Phone/Uncategorized";
            case 516:
                return "Phone/Cellular";
            case 520:
                return "Phone/Cordless";
            case 524:
                return "Phone/Smartphone";
            case 528:
                return "Phone/Wired modem or voice gateway";
            case 532:
                return "Phone/Common ISDN access";
            case 768:
                return "LAN/Network Access Point/Fully available";
            case 800:
                return "LAN/Network Access Point/1% to 17% utilized";
            case AppearanceLibrary.APPEARANCE_GENERIC_HEART_RATE_SENSOR /* 832 */:
                return "LAN/Network Access Point/17% to 33% utilized";
            case 864:
                return "LAN/Network Access Point/33% to 50% utilized";
            case AppearanceLibrary.APPEARANCE_GENERIC_BLOOD_PRESSURE /* 896 */:
                return "LAN/Network Access Point/50% to 67% utilized";
            case 928:
                return "LAN/Network Access Point/67% to 83% utilized";
            case AppearanceLibrary.APPEARANCE_HID /* 960 */:
                return "LAN/Network Access Point/83% to 99% utilized";
            case 992:
                return "LAN/Network Access Point/No service available";
            case 1024:
                return "Audio/Video/Uncategorized";
            case 1028:
                return "Audio/Video/Wearable Headset Device";
            case 1032:
                return "Audio/Video/Hands-free Device";
            case 1040:
                return "Audio/Video/Microphone";
            case 1044:
                return "Audio/Video/Laudspeaker";
            case 1048:
                return "Audio/Video/Headphones";
            case 1052:
                return "Audio/Video/Portable Audio";
            case 1056:
                return "Audio/Video/Car Audio";
            case 1060:
                return "Audio/Video/Set-top box";
            case 1064:
                return "Audio/Video/HiFi Audio Device";
            case 1068:
                return "Audio/Video/VCR";
            case 1072:
                return "Audio/Video/Video Camera";
            case 1076:
                return "Audio/Video/Camcorder";
            case 1080:
                return "Audio/Video/Video Monitor";
            case 1084:
                return "Audio/Video/Video Display and Loudspeaker";
            case AppearanceLibrary.APPEARANCE_GENERIC_RUNNING_WALKING_SENSOR /* 1088 */:
                return "Audio/Video/Video Conferencing";
            case 1096:
                return "Audio/Video/Gaming/Toy";
            case 1100:
                return "Audio/Video/Ear Bud";
            case 1796:
                return "Wearable/Wristwatch";
            case 1800:
                return "Wearable/Pager";
            case 1804:
                return "Wearable/Jacket";
            case 1808:
                return "Wearable/Helmet";
            case 1812:
                return "Wearable/Glasses";
            case 2052:
                return "Toy/Robot";
            case 2056:
                return "Toy/Vehicle";
            case 2060:
                return "Toy/Doll / Action figure";
            case 2064:
                return "Toy/Controller";
            case 2068:
                return "Toy/Game";
            case 2304:
                return "Health/Undefined";
            case 2308:
                return "Health/Blood Pressure Monitor";
            case 2312:
                return "Health/Thermometer";
            case 2316:
                return "Health/Weighing Scale";
            case 2320:
                return "Health/Glucose Meter";
            case 2324:
                return "Health/Pulse Oximeter";
            case 2328:
                return "Health/Heart/Pulse Rate Monitor";
            case 2332:
                return "Health/Health Data Display";
            case 2336:
                return "Health/Step Counter";
            case 2340:
                return "Health/Body Composition Analyzer";
            case 2344:
                return "Health/Peak Flow Monitor";
            case 2348:
                return "Health/Medication Monitor";
            case 2352:
                return "Health/Knee Prosthesis";
            case 2356:
                return "Health/Ankle Prosthesis";
            case 2360:
                return "Health/Generic Health Manager";
            case 2364:
                return "Health/Personal Mobility Device";
            default:
                StringBuilder sb = new StringBuilder();
                if (i2 == 1536) {
                    sb.append("Imaging/");
                    if ((i3 & 16) != 0) {
                        sb.append("Display, ");
                    }
                    if ((i3 & 32) != 0) {
                        sb.append("Camera, ");
                    }
                    if ((i3 & 64) != 0) {
                        sb.append("Scanner, ");
                    }
                    if ((i3 & 128) != 0) {
                        sb.append("Printer, ");
                    }
                    if ((i3 & 240) == 0) {
                        sb.append("Reserved, ");
                    }
                    sb.setLength(sb.length() - 2);
                    return sb.toString();
                }
                if (i2 != 1280) {
                    return i2 != 256 ? i2 != 512 ? i2 != 768 ? i2 != 1024 ? i2 != 1792 ? i2 != 2048 ? i2 != 2304 ? "Unknown" : "Health/Reserved" : "Toy/Reserved" : "Wearable/Reserved" : "Audio/Video/Reserved" : "LAN/Network Access Point/Reserved" : "Phone/Reserved" : "Computer/Reserved";
                }
                sb.append("Peripheral");
                int i4 = (i3 & AppearanceLibrary.APPEARANCE_GENERIC_WATCH) >> 6;
                if (i4 == 1) {
                    sb.append("/Keyboard");
                }
                if (i4 == 2) {
                    sb.append("/Pointing device");
                }
                if (i4 == 3) {
                    sb.append("/Keyboard, Pointing device");
                }
                int i5 = (i3 & 60) >> 2;
                switch (i5) {
                    case 1:
                        sb.append("/Joystick");
                        break;
                    case 2:
                        sb.append("/Gamepad");
                        break;
                    case 3:
                        sb.append("/Remote control");
                        break;
                    case 4:
                        sb.append("/Sensing device");
                        break;
                    case 5:
                        sb.append("/Digitizer tablet");
                        break;
                    case 6:
                        sb.append("/Card Reader");
                        break;
                    case 7:
                        sb.append("/Digital Pen");
                        break;
                    case 8:
                        sb.append("/Handheld scanner for bar-codes");
                        break;
                    case 9:
                        sb.append("/Handheld gestural input device");
                        break;
                    case 10:
                        sb.append("/Virtual Reality motion controller");
                        break;
                }
                if (i4 == 0 && i5 == 0) {
                    sb.append("/Reserved");
                }
                return sb.toString();
        }
    }

    private static String decodeClassOfDeviceFormatType(int i) {
        return String.format(Locale.US, "0x%02X", Integer.valueOf(i & 3));
    }

    private static String decodeClassOfService(int i) {
        StringBuilder sb = new StringBuilder();
        if ((8388608 & i) != 0) {
            sb.append("Information, ");
        }
        if ((4194304 & i) != 0) {
            sb.append("Telephony, ");
        }
        if ((2097152 & i) != 0) {
            sb.append("Audio, ");
        }
        if ((1048576 & i) != 0) {
            sb.append("Object Transfer, ");
        }
        if ((524288 & i) != 0) {
            sb.append("Capturing, ");
        }
        if ((262144 & i) != 0) {
            sb.append("Rendering, ");
        }
        if ((131072 & i) != 0) {
            sb.append("Networking, ");
        }
        if ((65536 & i) != 0) {
            sb.append("Positioning , ");
        }
        if ((i & DfuBaseService.ERROR_REMOTE_MASK) != 0) {
            sb.append("Limited Discoverable Mode, ");
        }
        if ((i & 16769024) != 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    private static void decodeCoD(StringBuilder sb, byte[] bArr, int i) {
        int intValue = ParserUtils.getIntValue(bArr, 19, i);
        sb.append(String.format(Locale.US, "\nClass: <0x%06X> ", Integer.valueOf(intValue)));
        sb.append(decodeClassOfDevice(intValue));
        sb.append("\nServices: ");
        sb.append(decodeClassOfService(intValue));
        sb.append("\nFormat Type: ");
        sb.append(decodeClassOfDeviceFormatType(intValue));
    }

    private static void decodeTLM(StringBuilder sb, byte[] bArr, int i, int i2) {
        int decodeUint16BigEndian = decodeUint16BigEndian(bArr, i);
        if (decodeUint16BigEndian > 0) {
            sb.append("\nBattery voltage: ");
            sb.append(decodeUint16BigEndian);
            sb.append(" mV");
        } else {
            sb.append("\nBattery voltage not supported");
        }
        float decode88FixedPointNotation = decode88FixedPointNotation(bArr, i + 2);
        if (decode88FixedPointNotation > -128.0f) {
            sb.append("\nTemperature: ");
            sb.append(decode88FixedPointNotation);
            sb.append("℃");
        } else {
            sb.append("\nTemperature not supported");
        }
        long decodeUint32BigEndian = decodeUint32BigEndian(bArr, i + 4);
        sb.append("\nAdvertisements count: ");
        sb.append(decodeUint32BigEndian);
        long decodeUint32BigEndian2 = decodeUint32BigEndian(bArr, i + 8) * 100;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(decodeUint32BigEndian2);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        sb.append("\nTime since power-up: ");
        if (decodeUint32BigEndian2 < 86400000) {
            sb.append(String.format(Locale.US, "%1$tR:%1$tS.%1$tL", calendar));
        } else if (decodeUint32BigEndian2 < 172800000) {
            sb.append(String.format(Locale.US, "1 day + %1$tR:%1$tS.%1$tL", calendar));
        } else {
            sb.append(String.format(Locale.US, "%1$d days + %2$tR:%2$tS.%2$tL", Integer.valueOf((int) (decodeUint32BigEndian2 / 86400000)), calendar));
        }
        if (i2 > 12) {
            sb.append("\nReserved: ");
            sb.append(ParserUtils.bytesToHex(bArr, i + 12, i2 - 12, true));
        }
    }

    private static int decodeUint16BigEndian(byte[] bArr, int i) {
        return (bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) | ((bArr[i] & FlagsParser.UNKNOWN_FLAGS) << 8);
    }

    private static long decodeUint32BigEndian(byte[] bArr, int i) {
        return ((bArr[i + 3] & FlagsParser.UNKNOWN_FLAGS) | ((bArr[i] & FlagsParser.UNKNOWN_FLAGS) << 24) | ((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) << 16) | ((bArr[i + 2] & FlagsParser.UNKNOWN_FLAGS) << 8)) & 4294967295L;
    }

    private static short decodeUuid16(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr[i] & FlagsParser.UNKNOWN_FLAGS));
    }

    public static void parseAltBeacon(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 == 26 && (bArr[i + 2] & FlagsParser.UNKNOWN_FLAGS) == 190 && (bArr[i + 3] & FlagsParser.UNKNOWN_FLAGS) == 172) {
            advData.setAppearance(AppearanceLibrary.APPEARANCE_ALT_BEACON, true);
            dataUnion.addData("AltBeacon", "\nCompany: " + CompanyIdentifier2.withId(decodeUuid16(bArr, i)) + "\nType: AltBeacon <0xBEAC>\nBeacon ID: " + ParserUtils.bytesToHex(bArr, i + 4, 16, true) + " - " + ParserUtils.bytesToHex(bArr, i + 20, 4, false) + "\nRSSI at 1m: " + ((int) bArr[i + 24]) + " dBm" + String.format(Locale.US, "\nManufacturer feature: 0x%02X", Byte.valueOf(bArr[i + 25])));
        }
    }

    private static String parseDeviceType(int i) {
        if (i == 1) {
            return "Xbox One";
        }
        switch (i) {
            case 6:
                return "Apple iPhone";
            case 7:
                return "Apple iPad";
            case 8:
                return "Android device";
            case 9:
                return "Windows 10 Desktop";
            default:
                switch (i) {
                    case 11:
                        return "Windows 10 Phone";
                    case 12:
                        return "Linus device";
                    case 13:
                        return "Windows IoT";
                    case 14:
                        return "Surface Hub";
                    default:
                        return "Unknown (" + i + ")";
                }
        }
    }

    public static void parseEddystoneBeacon(DatabaseHelper databaseHelper, AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 < 1) {
            return;
        }
        byte b2 = bArr[i];
        if (b2 == 0) {
            String parseEddystoneUIDBeacon = parseEddystoneUIDBeacon(bArr, i, i2);
            if (parseEddystoneUIDBeacon == null) {
                return;
            }
            dataUnion.addData("Eddystone UID", "\n" + parseEddystoneUIDBeacon);
            advData.setAppearance(AppearanceLibrary.APPEARANCE_EDDYSTONE_BEACON, true);
            return;
        }
        if (b2 == 16) {
            String parseEddystoneURLBeacon = parseEddystoneURLBeacon(bArr, i, i2);
            if (parseEddystoneURLBeacon == null) {
                return;
            }
            dataUnion.addData("Eddystone URL", "\n" + parseEddystoneURLBeacon, URIBeaconParser.decodeUri(bArr, i + 2, i2 - 2));
            advData.setAppearance(AppearanceLibrary.APPEARANCE_PHYSICAL_WEB, true);
            return;
        }
        if (b2 == 32) {
            String parseEddystoneTLMBeacon = parseEddystoneTLMBeacon(databaseHelper, bArr, i, i2);
            if (parseEddystoneTLMBeacon == null) {
                return;
            }
            dataUnion.addData("Eddystone TLM", "\n" + parseEddystoneTLMBeacon);
            advData.setAppearance(AppearanceLibrary.APPEARANCE_EDDYSTONE_BEACON, true);
            return;
        }
        if (b2 != 48) {
            dataUnion.addData("Eddystone Unsupported format", String.format(Locale.US, "\nFrame type: Unknown <0x%02X>", Byte.valueOf(b2)) + "\nData: " + ParserUtils.bytesToHex(bArr, i + 1, i2 - 1, true));
            advData.setAppearance(AppearanceLibrary.APPEARANCE_EDDYSTONE_BEACON, true);
            return;
        }
        if (i2 < 10) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\nFrame type: EID <0x30>");
        sb.append("\nTx power at 0m: ");
        sb.append((int) bArr[i + 1]);
        sb.append(" dBm");
        sb.append("\nEncrypted ID: ");
        int i3 = i + 2;
        sb.append(ParserUtils.bytesToHex(bArr, i3, 8, true));
        String nameForEddystoneKey = databaseHelper.getNameForEddystoneKey(bArr, i3);
        if (nameForEddystoneKey != null) {
            sb.append("\nResolved name: ");
            sb.append(nameForEddystoneKey);
        }
        if (i2 > 10) {
            sb.append("\nReserved: ");
            sb.append(ParserUtils.bytesToHex(bArr, i + 10, i2 - 10, true));
        }
        dataUnion.addData("Eddystone EID", sb.toString());
        advData.setAppearance(AppearanceLibrary.APPEARANCE_EDDYSTONE_BEACON, true);
    }

    public static String parseEddystoneTLMBeacon(DatabaseHelper databaseHelper, byte[] bArr, int i, int i2) {
        if (i2 < 2) {
            return null;
        }
        byte b2 = bArr[i + 1];
        if (b2 == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Frame type: TLM <0x20>\nVersion: ");
            sb.append((int) b2);
            if (i2 < 14) {
                sb.append("\nInvalid data: ");
                sb.append(ParserUtils.bytesToHex(bArr, i + 2, i2 - 2, true));
                return sb.toString();
            }
            decodeTLM(sb, bArr, i + 2, i2 - 2);
            return sb.toString();
        }
        if (b2 != 1) {
            return " (not supported)\nData: " + ParserUtils.bytesToHex(bArr, i + 2, i2 - 2, true);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Frame type: eTLM <0x20>\nVersion: ");
        sb2.append((int) b2);
        if (i2 < 18) {
            sb2.append("\nInvalid data: ");
            sb2.append(ParserUtils.bytesToHex(bArr, i + 2, i2 - 2, true));
            return sb2.toString();
        }
        sb2.append("\nEncrypted telemetry data: ");
        int i3 = i + 2;
        sb2.append(ParserUtils.bytesToHex(bArr, i3, 12, true));
        sb2.append("\nSalt: ");
        sb2.append(ParserUtils.bytesToHex(bArr, i + 14, 2, true));
        sb2.append("\nMessage Integrity Check: ");
        sb2.append(ParserUtils.bytesToHex(bArr, i + 16, 2, true));
        if (i2 > 18) {
            sb2.append("\nReserved: ");
            sb2.append(ParserUtils.bytesToHex(bArr, i + 18, i2 - 18, true));
        }
        if (Build.VERSION.SDK_INT >= 19) {
            byte[] decodeETLM = databaseHelper.decodeETLM(bArr, i3);
            if (decodeETLM != null) {
                sb2.append("\nResolved TLM data: ");
                decodeTLM(sb2, decodeETLM, 0, decodeETLM.length);
            }
        } else {
            sb2.append("\nResolving TLM data not supported on Android 4.3");
        }
        return sb2.toString();
    }

    public static String parseEddystoneUIDBeacon(byte[] bArr, int i, int i2) {
        if (i2 == 17) {
            return "Frame type: UID <0x00>\nID Namespace: " + ParserUtils.bytesToHex(bArr, i + 1, 10, true) + "\nID Instance: " + ParserUtils.bytesToHex(bArr, i + 11, 6, true);
        }
        if (i2 < 18) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Frame type: UID <0x00>");
        sb.append("\nTx power at 0m: ");
        sb.append((int) bArr[i + 1]);
        sb.append(" dBm");
        sb.append("\nID Namespace: ");
        sb.append(ParserUtils.bytesToHex(bArr, i + 2, 10, true));
        sb.append("\nID Instance: ");
        sb.append(ParserUtils.bytesToHex(bArr, i + 12, 6, true));
        if (i2 > 18) {
            sb.append("\nReserved: ");
            sb.append(ParserUtils.bytesToHex(bArr, i + 18, i2 - 18, true));
        }
        return sb.toString();
    }

    public static String parseEddystoneURLBeacon(byte[] bArr, int i, int i2) {
        String str;
        String decodeUri;
        if (i2 < 3) {
            return null;
        }
        int i3 = i + 1;
        if (bArr[i3] <= 3 && bArr[i3] >= 0 && bArr[i + 2] > 3 && (decodeUri = URIBeaconParser.decodeUri(bArr, i3, i2 - 1)) != null) {
            return "Frame type: URL <0x10>\nURL: " + decodeUri;
        }
        int i4 = i + 2;
        int i5 = i2 - 2;
        String decodeUri2 = URIBeaconParser.decodeUri(bArr, i4, i5);
        StringBuilder sb = new StringBuilder();
        sb.append("Frame type: URL <0x10>\nTx power at 0m: ");
        sb.append((int) bArr[i3]);
        sb.append(" dBm\n");
        if (decodeUri2 != null) {
            str = "URL: " + decodeUri2;
        } else {
            str = "Invalid data: " + ParserUtils.bytesToHex(bArr, i4, i5, true);
        }
        sb.append(str);
        return sb.toString();
    }

    public static void parseIBeacon(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 == 25 && bArr[i + 2] == 2 && bArr[i + 3] == 21) {
            if (bArr[i] == 76 && bArr[i + 1] == 0) {
                advData.setAppearance(AppearanceLibrary.APPEARANCE_IBEACON);
            } else if (bArr[i] == 89 && bArr[i + 1] == 0) {
                advData.setAppearance(AppearanceLibrary.APPEARANCE_NORDIC_BEACON);
            } else {
                advData.setAppearance(AppearanceLibrary.APPEARANCE_ANY_BEACON);
            }
            short decodeUuid16 = decodeUuid16(bArr, i);
            StringBuilder sb = new StringBuilder();
            sb.append("\nCompany: ");
            sb.append(CompanyIdentifier2.withId(decodeUuid16));
            sb.append("\nType: Beacon <0x02>");
            sb.append("\nLength of data: 21 bytes");
            UUID bytesToUUID = ParserUtils.bytesToUUID(bArr, i + 4, 16);
            sb.append("\nUUID: ");
            sb.append(bytesToUUID);
            sb.append("\nMajor: ");
            sb.append(decodeUint16BigEndian(bArr, i + 20));
            sb.append("\nMinor: ");
            sb.append(decodeUint16BigEndian(bArr, i + 22));
            sb.append("\nRSSI at 1m: ");
            sb.append((int) bArr[i + 24]);
            sb.append(" dBm");
            dataUnion.addData("Beacon", sb.toString());
            if (bytesToUUID.getMostSignificantBits() == -7089791425988116486L && bytesToUUID.getLeastSignificantBits() == -7525168577279210377L) {
                advData.setAppearance(5, true);
            }
        }
    }

    public static void parseMicrosoftAdvertisingBeacon(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (bArr[i] == 6 && bArr[i + 1] == 0 && bArr[i + 2] == 1) {
            int i3 = i + 3;
            int i4 = (bArr[i3] & 192) >> 6;
            int i5 = bArr[i3] & 63;
            int i6 = i + 4;
            int i7 = (bArr[i6] & 224) >> 5;
            int i8 = bArr[i6] & 31;
            byte b2 = bArr[i + 5];
            advData.setAppearance(28);
            dataUnion.addData("Microsoft Advertising Beacon", "\nScenario Type: Advertising Beacon <0x01>\nVersion: " + i4 + "\nDevice Type: " + parseDeviceType(i5) + String.format(Locale.US, "\nFlags: 0x%02X (version: %d)", Integer.valueOf(i8), Integer.valueOf(i7)) + String.format(Locale.US, "\nReserved: 0x%02X", Integer.valueOf(b2)) + "\nSalt: " + ParserUtils.bytesToHex(bArr, i + 6, 4, true) + "\nDevice Hash: " + ParserUtils.bytesToHex(bArr, i + 10, i2 - 10, true));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0075, code lost:
    
        if (r1 != 2) goto L29;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parseMicrosoftSwiftPairBeacon(no.nordicsemi.android.mcp.ble.model.AdvData r6, no.nordicsemi.android.mcp.ble.model.DataUnion r7, byte[] r8, int r9, int r10) {
        /*
            r6 = r8[r9]
            r0 = 6
            if (r6 != r0) goto La6
            int r6 = r9 + 1
            r6 = r8[r6]
            if (r6 != 0) goto La6
            int r6 = r9 + 2
            r6 = r8[r6]
            r0 = 3
            if (r6 == r0) goto L14
            goto La6
        L14:
            short r6 = decodeUuid16(r8, r9)
            int r1 = r9 + 3
            r1 = r8[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r2 = r9 + 4
            r2 = r8[r2]
            r2 = r2 & 255(0xff, float:3.57E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "\nCompany: "
            r3.append(r4)
            java.lang.String r6 = no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2.withId(r6)
            r3.append(r6)
            java.lang.String r6 = "\nType: Swift Pair Beacon <0x03>"
            r3.append(r6)
            java.lang.String r6 = "\nSub Scenario: "
            r3.append(r6)
            r6 = 2
            r4 = 1
            if (r1 == 0) goto L54
            if (r1 == r4) goto L4e
            if (r1 == r6) goto L48
            goto L59
        L48:
            java.lang.String r5 = "Pairing over Bluetooth LE and BR/EDR with Secure Connections <0x02>"
            r3.append(r5)
            goto L59
        L4e:
            java.lang.String r5 = "Pairing over BR/EDR only, using Bluetooth LE for discovery <0x01>"
            r3.append(r5)
            goto L59
        L54:
            java.lang.String r5 = "Pairing over Bluetooth LE only <0x00>"
            r3.append(r5)
        L59:
            java.lang.String r5 = "\nRSSI value: "
            r3.append(r5)
            r5 = 128(0x80, float:1.8E-43)
            if (r2 != r5) goto L68
            java.lang.String r2 = "Reserved <0x80>"
            r3.append(r2)
            goto L70
        L68:
            r3.append(r2)
            java.lang.String r2 = " dBm"
            r3.append(r2)
        L70:
            r2 = 5
            if (r1 == 0) goto L8e
            if (r1 == r4) goto L78
            if (r1 == r6) goto L88
            goto L9d
        L78:
            int r6 = r9 + 5
            java.lang.String r6 = no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils.bytesToAddress(r8, r6)
            r2 = 11
            java.lang.String r1 = "\nBR/EDR address: "
            r3.append(r1)
            r3.append(r6)
        L88:
            int r6 = r9 + r2
            decodeCoD(r3, r8, r6)
            int r2 = r2 + r0
        L8e:
            java.lang.String r6 = new java.lang.String
            int r9 = r9 + r2
            int r10 = r10 - r2
            r6.<init>(r8, r9, r10)
            java.lang.String r8 = "\nDisplay name: "
            r3.append(r8)
            r3.append(r6)
        L9d:
            java.lang.String r6 = r3.toString()
            java.lang.String r8 = "Swift Pair Beacon"
            r7.addData(r8, r6)
        La6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.parser.gap.BeaconParser.parseMicrosoftSwiftPairBeacon(no.nordicsemi.android.mcp.ble.model.AdvData, no.nordicsemi.android.mcp.ble.model.DataUnion, byte[], int, int):void");
    }

    public static void parseThreadToBLEBeacon(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        int i3;
        if (i2 >= 11 && bArr[i] == 48) {
            advData.setAppearance(29, true);
            StringBuilder sb = new StringBuilder();
            sb.append("\nVersion: 3\nOp Code: Discovery (0x0)");
            int i4 = i + 1;
            byte b2 = bArr[i4];
            int i5 = i4 + 1;
            if (b2 != 0) {
                sb.append("\nRole Flags: ");
                if ((b2 & 128) != 0) {
                    sb.append("Border Agent Enabled, ");
                }
                if ((b2 & 64) != 0) {
                    sb.append("DTC Enabled, ");
                }
                if ((b2 & 32) != 0) {
                    sb.append("Un-configured, ");
                }
                if ((b2 & 16) != 0) {
                    sb.append("Joining Permitted, ");
                }
                if ((b2 & 8) != 0) {
                    sb.append("Active Router, ");
                }
                if ((b2 & 4) != 0) {
                    sb.append("Router Capable, ");
                }
                if ((b2 & 2) != 0) {
                    sb.append("Scan Capable Router, ");
                }
                if ((b2 & 1) != 0) {
                    sb.append("Reserved, ");
                }
                sb.setLength(sb.length() - 2);
            }
            byte b3 = bArr[i5];
            int i6 = i5 + 1;
            if (b3 != 0) {
                sb.append("\nTransport Flags: ");
                if ((b3 & 128) != 0) {
                    sb.append("Connection requested, ");
                }
                if ((b3 & 64) != 0) {
                    sb.append("Frame pending, ");
                }
                if ((b3 & 62) != 0) {
                    sb.append("Reserved, ");
                }
                if ((b3 & 1) != 0) {
                    sb.append("L2CAP, ");
                }
                sb.setLength(sb.length() - 2);
            }
            sb.append((b2 & 32) != 0 ? "\nJoiner IID: " : "\nXPANID: ");
            sb.append(ParserUtils.bytesToHex(bArr, i6, 8, true));
            int i7 = i6 + 8;
            if (((b3 & 1) != 0) && i + i2 > (i3 = i7 + 1)) {
                sb.append("\nLE PSM: ");
                sb.append((int) bArr[i7]);
                i7 = i3;
            }
            int i8 = i + i2;
            int i9 = i7 + 4;
            if (i8 > i9 && bArr[i7] == 18 && bArr[i7 + 1] == 2) {
                sb.append("\nJoiner UDP Port: ");
                sb.append(ParserUtils.getIntValue(bArr, 18, i7 + 2));
            } else {
                i9 = i7;
            }
            if (i8 == i9 + 4 && bArr[i9] == 15 && bArr[i9 + 1] == 2) {
                sb.append("\nCommissioner UDP Port: ");
                sb.append(ParserUtils.getIntValue(bArr, 18, i9 + 2));
            }
            dataUnion.addData("Thread ToBLE Discovery Beacon", sb.toString());
            return;
        }
        if (bArr[i] == 3) {
            int i10 = bArr[i + 1];
            if (i2 < i10 + 2) {
                return;
            }
            String str = new String(bArr, i + 2, i10);
            advData.setAppearance(29, false);
            advData.setName(str);
            dataUnion.addData("Network Name", str);
            return;
        }
        if (bArr[i] == 8) {
            int i11 = bArr[i + 1];
            if (i2 < i11 + 2) {
                return;
            }
            advData.setAppearance(29, false);
            dataUnion.addData("Steering Data", ParserUtils.bytesToHex(bArr, i + 2, i11, true));
        }
    }
}
