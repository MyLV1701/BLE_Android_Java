package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class DeviceAddressParser {
    private static String parseAddresses(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3 += 6) {
            String bytesToAddress = ParserUtils.bytesToAddress(bArr, i3);
            if (!bytesToAddress.equals("")) {
                sb.append(bytesToAddress);
                sb.append(", ");
            } else {
                sb.append("Invalid data: ");
                sb.append(ParserUtils.bytesToHex(bArr, i3, Math.min(i3 + 6, i2), false));
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    public static void parseBluetoothAddress(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("LE Bluetooth Device Address", parseTypeAndAddress(bArr, i, i2));
    }

    public static void parsePublicAddresses(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("Public Target Address", parseAddresses(bArr, i, i2));
    }

    public static void parseRandomAddresses(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        dataUnion.addData("Random Target Address", parseAddresses(bArr, i, i2));
    }

    private static String parseTypeAndAddress(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = i; i3 < i + i2; i3 += 7) {
            boolean z = (bArr[i3] & 1) > 0;
            String bytesToAddress = ParserUtils.bytesToAddress(bArr, i3 + 1);
            if (!bytesToAddress.equals("")) {
                sb.append(bytesToAddress);
                sb.append(z ? " (random)" : " (public)");
                sb.append(", ");
            } else {
                sb.append("Invalid data: ");
                sb.append(ParserUtils.bytesToHex(bArr, i3, Math.min(i3 + 6, i2), false));
                sb.append(", ");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
