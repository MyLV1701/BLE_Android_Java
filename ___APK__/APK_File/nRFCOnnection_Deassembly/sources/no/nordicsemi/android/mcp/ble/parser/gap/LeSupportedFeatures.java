package no.nordicsemi.android.mcp.ble.parser.gap;

import no.nordicsemi.android.mcp.ble.model.DataUnion;

/* loaded from: classes.dex */
public class LeSupportedFeatures {
    public static final String LE_SUPPORTED_FEATURES = "LE Supported Features";

    public static void parse(DataUnion dataUnion, byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        if (i2 > 0) {
            byte b2 = bArr[i];
            if ((b2 & 1) > 0) {
                sb.append("LE Encryption, ");
            }
            if ((b2 & 2) > 0) {
                sb.append("Connection Parameters Request Procedure, ");
            }
            if ((b2 & 4) > 0) {
                sb.append("Extended Reject Indication, ");
            }
            if ((b2 & 8) > 0) {
                sb.append("Slave-initiated Features Exchange, ");
            }
            if ((b2 & 16) > 0) {
                sb.append("LE Ping, ");
            }
            if ((b2 & 32) > 0) {
                sb.append("LE Data Packet Length Extension, ");
            }
            if ((b2 & 64) > 0) {
                sb.append("LL Privacy, ");
            }
            if ((b2 & 128) > 0) {
                sb.append("Extended Scanner Filter Policies, ");
            }
        }
        if (i2 > 1) {
            byte b3 = bArr[i + 1];
            if ((b3 & 1) > 0) {
                sb.append("LE 2M PHY, ");
            }
            if ((b3 & 2) > 0) {
                sb.append("Stable Modulation Index - Transmitter, ");
            }
            if ((b3 & 4) > 0) {
                sb.append("Stable Modulation Index - Receiver, ");
            }
            if ((b3 & 8) > 0) {
                sb.append("LE Coded PHY, ");
            }
            if ((b3 & 16) > 0) {
                sb.append("LE Extended Advertising, ");
            }
            if ((b3 & 32) > 0) {
                sb.append("LE Periodic Advertising, ");
            }
            if ((b3 & 64) > 0) {
                sb.append("Channel Selection Algorithm #2, ");
            }
            if ((b3 & 128) > 0) {
                sb.append("LE Power Class 1, ");
            }
        }
        if (i2 > 2 && (bArr[i + 2] & 1) > 0) {
            sb.append("Minimum Number of Used Channels Procedure, ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        } else {
            sb.append("No features");
        }
        dataUnion.addData(LE_SUPPORTED_FEATURES, sb.toString());
    }
}
