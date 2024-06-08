package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
public class BatteryParser {
    private static int decodeBatteryLevel(byte[] bArr, int i) {
        return unsignedByteToInt(bArr[i]);
    }

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("Battery Level", decodeBatteryLevel(bArr, i) + "%", 17);
    }

    private static int unsignedByteToInt(byte b2) {
        return b2 & FlagsParser.UNKNOWN_FLAGS;
    }
}
