package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class SecureDfuButtonlessParser implements ICharacteristicParser {
    private static final int REQ_ENTER_BOOTLOADER = 1;
    private static final int REQ_RESPONSE_CODE_A = 32;
    private static final int REQ_RESPONSE_CODE_B = 96;
    private static final int REQ_SET_NAME = 2;
    private static final int STATUS_BUSY = 6;
    private static final int STATUS_INVALID_ADV_NAME = 5;
    private static final int STATUS_NOT_BONDED = 7;
    private static final int STATUS_OPERATION_FAILED = 4;
    private static final int STATUS_OP_CODE_NOT_SUPPORTED = 2;
    private static final int STATUS_SUCCESS = 1;

    private static String getOpCode(int i) {
        return i != 1 ? i != 2 ? (i == 32 || i == 96) ? "Response for: " : "Reserved for future use" : "Set advertisement name" : "Enter bootloader";
    }

    private static String getStatus(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 5 ? i != 6 ? i != 7 ? "Reserved for future use" : "Not bonded" : "Busy" : "Invalid advertisement name" : "Operation failed" : "Op Code not supported" : "Success";
    }

    @Override // no.nordicsemi.android.mcp.ble.parser.gatt.ICharacteristicParser
    public String parse(DatabaseHelper databaseHelper, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return parse(bluetoothGattCharacteristic);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x002a, code lost:
    
        if (r3 != 96) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String parse(android.bluetooth.BluetoothGattCharacteristic r7) {
        /*
            byte[] r0 = r7.getValue()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 17
            r3 = 0
            java.lang.Integer r3 = r7.getIntValue(r2, r3)
            int r3 = r3.intValue()
            java.lang.String r4 = getOpCode(r3)
            r1.append(r4)
            int r0 = r0.length
            r4 = 2
            if (r0 <= r4) goto L72
            java.lang.String r0 = "\n"
            r5 = 1
            if (r3 == r4) goto L2d
            r6 = 32
            if (r3 == r6) goto L4c
            r6 = 96
            if (r3 == r6) goto L4c
            goto L72
        L2d:
            java.lang.Integer r3 = r7.getIntValue(r2, r5)
            int r3 = r3.intValue()
            java.lang.String r6 = "Length: "
            r1.append(r6)
            r1.append(r3)
            r1.append(r0)
            java.lang.String r3 = r7.getStringValue(r4)
            java.lang.String r6 = "Name: "
            r1.append(r6)
            r1.append(r3)
        L4c:
            java.lang.Integer r3 = r7.getIntValue(r2, r5)
            int r3 = r3.intValue()
            java.lang.String r3 = getOpCode(r3)
            r1.append(r3)
            r1.append(r0)
            java.lang.Integer r7 = r7.getIntValue(r2, r4)
            int r7 = r7.intValue()
            java.lang.String r0 = "Status: "
            r1.append(r0)
            java.lang.String r7 = getStatus(r7)
            r1.append(r7)
        L72:
            java.lang.String r7 = r1.toString()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.parser.gatt.SecureDfuButtonlessParser.parse(android.bluetooth.BluetoothGattCharacteristic):java.lang.String");
    }
}
