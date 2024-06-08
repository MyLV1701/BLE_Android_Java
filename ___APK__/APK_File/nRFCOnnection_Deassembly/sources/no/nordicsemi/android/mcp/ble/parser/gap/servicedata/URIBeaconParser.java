package no.nordicsemi.android.mcp.ble.parser.gap.servicedata;

import android.util.Log;
import android.util.SparseArray;
import android.webkit.URLUtil;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.AppearanceLibrary;

/* loaded from: classes.dex */
public class URIBeaconParser {
    private static final String TAG = "URIBeaconParser";
    private static final SparseArray<String> URI_SCHEMES = new SparseArray<String>() { // from class: no.nordicsemi.android.mcp.ble.parser.gap.servicedata.URIBeaconParser.1
        {
            put(0, "http://www.");
            put(1, "https://www.");
            put(2, "http://");
            put(3, "https://");
            put(4, "urn:uuid:");
        }
    };
    private static final SparseArray<String> URL_CODES = new SparseArray<String>() { // from class: no.nordicsemi.android.mcp.ble.parser.gap.servicedata.URIBeaconParser.2
        {
            put(0, ".com/");
            put(1, ".org/");
            put(2, ".edu/");
            put(3, ".net/");
            put(4, ".info/");
            put(5, ".biz/");
            put(6, ".gov/");
            put(7, ".com");
            put(8, ".org");
            put(9, ".edu");
            put(10, ".net");
            put(11, ".info");
            put(12, ".biz");
            put(13, ".gov");
        }
    };

    private static String decodeFlags(byte b2) {
        if (b2 == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if ((b2 & 1) > 0) {
            sb.append("Invisible Hint, ");
        }
        if ((b2 & 254) > 0) {
            sb.append("Reserved, ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public static String decodeRestUrlUri(byte[] bArr, int i, int i2) {
        if (i < 0 || bArr.length < i + i2) {
            return null;
        }
        return decodeUrl(bArr, i, i2, new StringBuilder());
    }

    public static String decodeUri(byte[] bArr, int i, int i2) {
        if (i >= 0 && bArr.length >= i + i2) {
            StringBuilder sb = new StringBuilder();
            if (i2 > 0) {
                String str = URI_SCHEMES.get(bArr[i + 0]);
                if (str != null) {
                    sb.append(str);
                    if (URLUtil.isNetworkUrl(str)) {
                        return decodeUrl(bArr, i + 1, i2 - 1, sb);
                    }
                    if ("urn:uuid:".equals(str)) {
                        return decodeUrnUuid(bArr, i + 1, sb);
                    }
                }
            }
        }
        return null;
    }

    private static String decodeUrl(byte[] bArr, int i, int i2, StringBuilder sb) {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i3 + 1;
            byte b2 = bArr[i3 + i];
            String str = URL_CODES.get(b2);
            if (str != null) {
                sb.append(str);
            } else {
                sb.append((char) b2);
            }
            i3 = i4;
        }
        return sb.toString();
    }

    private static String decodeUrnUuid(byte[] bArr, int i, StringBuilder sb) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.BIG_ENDIAN);
        try {
            wrap.position(i);
            sb.append(new UUID(wrap.getLong(), wrap.getLong()).toString());
            return sb.toString();
        } catch (BufferUnderflowException unused) {
            Log.w(TAG, "decodeUrnUuid BufferUnderflowException!");
            return null;
        }
    }

    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2) {
        advData.setAppearance(AppearanceLibrary.APPEARANCE_URI_BEACON, true);
        StringBuilder sb = new StringBuilder();
        if (bArr[i] > 0) {
            sb.append("\nFlags: ");
            sb.append(decodeFlags(bArr[i]));
        }
        sb.append("\nTx Power: ");
        sb.append((int) bArr[i + 1]);
        sb.append(" dBm");
        String decodeUri = decodeUri(bArr, i + 2, i2 - 2);
        sb.append("\nURI: ");
        sb.append(decodeUri);
        dataUnion.addData("URI Beacon", sb.toString(), decodeUri);
    }
}
