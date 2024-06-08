package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.CompanyIdentifier2;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ManufacturerDataParser {
    private static final String MANUFACTURER_DATA = "Manufacturer data";
    public static final String MANUFACTURER_DATA_4_1 = "Manufacturer data (Bluetooth Core 4.1)";

    private static int decodeUint16(byte[] bArr, int i) {
        return ((bArr[i + 1] & FlagsParser.UNKNOWN_FLAGS) << 8) | (bArr[i] & FlagsParser.UNKNOWN_FLAGS);
    }

    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        if (i2 >= 2) {
            int decodeUint16 = decodeUint16(bArr, i);
            dataUnion.addData(MANUFACTURER_DATA_4_1, "\nCompany: " + CompanyIdentifier2.withId(decodeUint16) + " " + ParserUtils.bytesToHex(bArr, i + 2, i2 - 2, true));
            if (decodeUint16 == 76) {
                advData.setAppearance(8);
            } else if (decodeUint16 == 89) {
                advData.setAppearance(10);
            } else if (decodeUint16 == 224) {
                advData.setAppearance(9);
            } else if (decodeUint16 == 474) {
                advData.setAppearance(22);
            } else if (decodeUint16 == 555) {
                advData.setAppearance(19);
            } else if (decodeUint16 == 871) {
                advData.setAppearance(27);
            }
        }
        dataUnion.addData(MANUFACTURER_DATA, ParserUtils.bytesToHex(bArr, i, i2, true));
    }
}
