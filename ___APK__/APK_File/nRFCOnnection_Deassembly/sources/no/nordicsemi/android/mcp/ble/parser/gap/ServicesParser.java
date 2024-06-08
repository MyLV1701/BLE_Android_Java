package no.nordicsemi.android.mcp.ble.parser.gap;

import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ServicesParser {
    public static final String COMPLETE_128 = "Complete list of 128-bit Service UUIDs";
    public static final String COMPLETE_16 = "Complete list of 16-bit Service UUIDs";
    public static final String COMPLETE_32 = "Complete list of 32-bit Service UUIDs";

    public static void parseCompleteList128UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 16) {
            int i4 = i + i3;
            int decodeUuid32 = ParserUtils.decodeUuid32(bArr, i4 + 12);
            int decodeUuid322 = ParserUtils.decodeUuid32(bArr, i4 + 8);
            int decodeUuid323 = ParserUtils.decodeUuid32(bArr, i4 + 4);
            int decodeUuid324 = ParserUtils.decodeUuid32(bArr, i4 + 0);
            updateAppearance(advData, decodeUuid32, decodeUuid322, decodeUuid323, decodeUuid324);
            sb.append(new UUID((decodeUuid32 << 32) + (decodeUuid322 & 4294967295L), (decodeUuid323 << 32) + (decodeUuid324 & 4294967295L)).toString());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData(COMPLETE_128, sb.toString());
    }

    public static void parseCompleteList16UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int decodeUuid16 = ParserUtils.decodeUuid16(bArr, i + i3);
            updateAppearance(advData, (short) decodeUuid16);
            sb.append(String.format(Locale.US, "0x%04X", Integer.valueOf(decodeUuid16)));
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData(COMPLETE_16, sb.toString());
    }

    public static void parseCompleteList32UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 4) {
            sb.append(String.format(Locale.US, "0x%08X", Integer.valueOf(ParserUtils.decodeUuid32(bArr, i + i3))));
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData(COMPLETE_32, sb.toString());
    }

    public static void parseMoreAvailable128UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 16) {
            int i4 = i + i3;
            int decodeUuid32 = ParserUtils.decodeUuid32(bArr, i4 + 12);
            int decodeUuid322 = ParserUtils.decodeUuid32(bArr, i4 + 8);
            int decodeUuid323 = ParserUtils.decodeUuid32(bArr, i4 + 4);
            int decodeUuid324 = ParserUtils.decodeUuid32(bArr, i4 + 0);
            updateAppearance(advData, decodeUuid32, decodeUuid322, decodeUuid323, decodeUuid324);
            sb.append(new UUID((decodeUuid32 << 32) + (decodeUuid322 & 4294967295L), (decodeUuid323 << 32) + (decodeUuid324 & 4294967295L)).toString());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData("Incomplete List of 128-bit Service UUIDs", sb.toString());
    }

    public static void parseMoreAvailable16UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int decodeUuid16 = ParserUtils.decodeUuid16(bArr, i + i3);
            updateAppearance(advData, (short) decodeUuid16);
            sb.append(String.format(Locale.US, "0x%04X", Integer.valueOf(decodeUuid16)));
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData("Incomplete List of 16-bit Service UUIDs", sb.toString());
    }

    public static void parseMoreAvailable32UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 4) {
            sb.append(String.format(Locale.US, "0x%08X", Integer.valueOf(ParserUtils.decodeUuid32(bArr, i + i3))));
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData("Incomplete List of 32-bit Service UUIDs", sb.toString());
    }

    public static void parseSolicitation128UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 16) {
            int i4 = i + i3;
            int decodeUuid32 = ParserUtils.decodeUuid32(bArr, i4 + 12);
            int decodeUuid322 = ParserUtils.decodeUuid32(bArr, i4 + 8);
            int decodeUuid323 = ParserUtils.decodeUuid32(bArr, i4 + 4);
            int decodeUuid324 = ParserUtils.decodeUuid32(bArr, i4 + 0);
            updateAppearance(advData, decodeUuid32, decodeUuid322, decodeUuid323, decodeUuid324);
            sb.append(new UUID((decodeUuid32 << 32) + (decodeUuid322 & 4294967295L), (decodeUuid323 << 32) + (decodeUuid324 & 4294967295L)).toString());
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData("List of 128 bit Service Solicitation UUIDs", sb.toString());
    }

    public static void parseSolicitation16UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int decodeUuid16 = ParserUtils.decodeUuid16(bArr, i + i3);
            updateAppearance(advData, (short) decodeUuid16);
            sb.append(String.format(Locale.US, "0x%04X", Integer.valueOf(decodeUuid16)));
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData("List of 16-bit Service Solicitation UUIDs", sb.toString());
    }

    public static void parseSolicitation32UUID(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3 += 4) {
            sb.append(String.format(Locale.US, "0x%08X", Integer.valueOf(ParserUtils.decodeUuid32(bArr, i + i3))));
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        dataUnion.addData("List of 32 bit Service Solicitation UUIDs", sb.toString());
    }

    public static void updateAppearance(AdvData advData, short s) {
        if (advData == null) {
            return;
        }
        switch (s) {
            case -493:
            case -475:
            case -374:
            case -373:
            case -313:
            case -312:
            case -311:
            case -310:
            case -309:
            case -308:
            case -307:
            case -306:
            case -305:
            case -304:
            case -303:
            case -302:
            case -301:
            case -300:
                advData.setAppearance(8);
                return;
            case -487:
            case -474:
            case -473:
            case -468:
            case -432:
            case -427:
            case -426:
            case -353:
            case -352:
            case -342:
            case -296:
            case -272:
            case -269:
            case -268:
                advData.setAppearance(9);
                return;
            case -424:
            case -284:
            case -283:
                advData.setAppearance(10);
                return;
            case -423:
                advData.setAppearance(20);
                return;
            case -415:
                advData.setAppearance(22);
                return;
            case -362:
            case -361:
                advData.setAppearance(19);
                return;
            case -358:
                advData.setAppearance(15);
                return;
            case 6147:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_PROXIMITY_SENSOR, true);
                return;
            case 6149:
                advData.setAppearance(256, true);
                return;
            case 6152:
                advData.setAppearance(1024, true);
                return;
            case 6153:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_TEMPERATURE_SENSOR, true);
                return;
            case 6157:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_HEART_RATE_BELT, true);
                return;
            case 6160:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_GENERIC_BLOOD_PRESSURE, true);
                return;
            case 6162:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_HID, true);
                return;
            case 6164:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_GENERIC_RUNNING_WALKING_SENSOR, true);
                return;
            case 6166:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_CYCLING_SPEED_AND_CADENCE_SENSOR, true);
                return;
            case 6168:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_CYCLING_POWER_SENSOR, true);
                return;
            case 6175:
                advData.setAppearance(AppearanceLibrary.APPEARANCE_GENERIC_CONTINUOUS_GLUCOSE_METER, true);
                return;
            case 6183:
            case 6184:
                advData.setAppearance(26, true);
                return;
            default:
                return;
        }
    }

    private static void updateAppearance(AdvData advData, int i, int i2, int i3, int i4) {
        if (advData == null) {
            return;
        }
        if (i == 5424 && i2 == 303230942 && i3 == 354646111 && i4 == -356724445) {
            advData.setAppearance(1, true);
        }
        if (i == 5411 && i2 == 303230942 && i3 == 354646111 && i4 == -356724445) {
            advData.setAppearance(AppearanceLibrary.APPEARANCE_GENERIC_LIGHT, true);
        }
        if (i == 1849688065 && i2 == -1247546477 && i3 == -525736690 && i4 == 618449566) {
            advData.setAppearance(25, true);
            return;
        }
        if (i == -278396672 && i2 == -1691006669 && i3 == -1693428993 && i4 == -1452015550) {
            advData.setAppearance(21, true);
            return;
        }
        if (i == -1285718618 && i2 == -288076455 && i3 == -1886783913 && i4 == 1054779751) {
            advData.setAppearance(7, true);
            return;
        }
        if (i == 1961360896 && i2 == -962326046 && i3 == -1213661182 && i4 == -1512717029) {
            advData.setAppearance(11, true);
            return;
        }
        if (i == -2009071615 && i2 == -379943858 && i3 == -985662484 && i4 == 854416980) {
            advData.setAppearance(14, true);
            return;
        }
        if (i == -301195136 && i2 == -2021244742 && i3 == -1416193607 && i4 == 449413592) {
            advData.setAppearance(AppearanceLibrary.APPEARANCE_EDDYSTONE_BEACON, true);
            return;
        }
        if (i == -1105871121 && i2 == 1636188286 && i3 == -2088694824 && i4 == -1672638988) {
            advData.setAppearance(3, true);
            return;
        }
        if (i2 == 1853703681 && i3 == -1113407494 && i4 == -1500948806) {
            advData.setAppearance(2, true);
        } else if (i == 662902051 && i2 == 871275420 && i3 == 307040413 && i4 == 1795951249) {
            advData.setAppearance(27, true);
        }
    }
}
