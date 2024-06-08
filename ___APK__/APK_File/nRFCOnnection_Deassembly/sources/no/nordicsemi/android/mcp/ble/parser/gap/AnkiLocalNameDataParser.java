package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class AnkiLocalNameDataParser {
    private static int decodeUint16(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr[i] & FlagsParser.UNKNOWN_FLAGS));
    }

    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder("\nStatus: ");
        if ((bArr[i] & 64) > 0) {
            sb.append("On charger, ");
        }
        if ((bArr[i] & 32) > 0) {
            sb.append("Low battery, ");
        }
        if ((bArr[i] & 16) > 0) {
            sb.append("Full battery, ");
        }
        if (sb.length() > 10) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("Ready");
        }
        sb.append("\nVersion: 0x");
        sb.append(Integer.toHexString(decodeUint16(bArr, i + 1)));
        String decodeLocalName = CompleteLocalNameParser.decodeLocalName(bArr, i + 8, i2 - 8);
        sb.append("\nName: ");
        sb.append(decodeLocalName);
        dataUnion.addData("ANKI Drive Local Name", sb.toString());
        if (decodeLocalName.startsWith("Drive")) {
            return;
        }
        advData.setName(decodeLocalName);
    }
}
