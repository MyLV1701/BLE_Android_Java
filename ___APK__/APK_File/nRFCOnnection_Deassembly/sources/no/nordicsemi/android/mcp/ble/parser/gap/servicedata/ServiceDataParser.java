package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class ServiceDataParser {
    public static final String SERVICE_DATA = "Service Data";

    public static void parse128Bit(DataUnion dataUnion, UUID uuid, byte[] bArr, int i, int i2) {
        dataUnion.addData(SERVICE_DATA, String.format(Locale.US, "UUID: %s Data: %s", uuid, ParserUtils.bytesToHex(bArr, i, i2, true)));
    }

    public static void parse16Bit(DataUnion dataUnion, short s, byte[] bArr, int i, int i2) {
        dataUnion.addData(SERVICE_DATA, String.format(Locale.US, "UUID: 0x%04X Data: %s", Short.valueOf(s), ParserUtils.bytesToHex(bArr, i, i2, true)));
    }

    public static void parse32Bit(DataUnion dataUnion, int i, byte[] bArr, int i2, int i3) {
        dataUnion.addData(SERVICE_DATA, String.format(Locale.US, "UUID: 0x%08X Data: %s", Integer.valueOf(i), ParserUtils.bytesToHex(bArr, i2, i3, true)));
    }
}
