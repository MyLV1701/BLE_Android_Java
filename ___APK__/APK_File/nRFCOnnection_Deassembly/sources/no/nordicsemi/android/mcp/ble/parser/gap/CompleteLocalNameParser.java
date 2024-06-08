package no.nordicsemi.android.mcp.ble.parser.gap;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import no.nordicsemi.android.mcp.ble.model.AdvData;
import no.nordicsemi.android.mcp.ble.model.DataUnion;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;

/* loaded from: classes.dex */
public class CompleteLocalNameParser {
    public static final String BYTES = "Bytes";
    public static final String COMPLETE_LOCAL_NAME = "Complete Local Name";
    private static final String TAG = "CompleteLocalNameParser";

    public static String decodeLocalName(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            Log.e(TAG, "Unable to convert the complete local name to UTF-8", e2);
            return null;
        } catch (IndexOutOfBoundsException e3) {
            Log.e(TAG, "Error when reading complete local name", e3);
            return null;
        }
    }

    private static byte[] getDeviceName() {
        String name = BluetoothAdapter.getDefaultAdapter().getName();
        return TextUtils.isEmpty(name) ? new byte[0] : name.getBytes();
    }

    public static void parse(AdvData advData, DataUnion dataUnion, byte[] bArr, int i, int i2, boolean z, boolean z2) {
        if (z2) {
            bArr = getDeviceName();
            i = 0;
            i2 = bArr.length;
        }
        String decodeLocalName = decodeLocalName(bArr, i, i2);
        dataUnion.addData(COMPLETE_LOCAL_NAME, decodeLocalName);
        dataUnion.addData(BYTES, ParserUtils.bytesToHex(bArr, i, i2, true));
        if (z) {
            updateAppearanceByName(advData, decodeLocalName);
            advData.setName(decodeLocalName);
        }
    }

    public static void updateAppearanceByName(AdvData advData, String str) {
        if ("est".equalsIgnoreCase(str) || "estimote".equalsIgnoreCase(str)) {
            advData.setAppearance(15, true);
        }
        if (str != null && str.startsWith("BBC micro:bit")) {
            advData.setAppearance(23, true);
        }
        if (str != null && str.startsWith("Nordic")) {
            advData.setAppearance(10, false);
        }
        if (str == null || !str.startsWith("Zephyr")) {
            return;
        }
        advData.setAppearance(24, false);
    }
}
