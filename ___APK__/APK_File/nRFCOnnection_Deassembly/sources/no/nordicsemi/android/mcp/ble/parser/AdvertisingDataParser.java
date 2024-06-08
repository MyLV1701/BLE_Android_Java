package no.nordicsemi.android.mcp.ble.parser;

import android.annotation.TargetApi;
import android.bluetooth.le.AdvertiseData;
import android.os.ParcelUuid;
import android.util.Log;
import java.util.Arrays;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.DatabaseUtils;

@TargetApi(21)
/* loaded from: classes.dex */
public class AdvertisingDataParser {
    private static final int COMPLETE_LOCAL_NAME = 9;
    private static final int MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int SERVICES_COMPLETE_LIST_128_BIT_SERVICE_CLASS_UUIDS = 7;
    private static final int SERVICES_COMPLETE_LIST_16_BIT_SERVICE_CLASS_UUIDS = 3;
    private static final int SERVICES_COMPLETE_LIST_32_BIT_SERVICE_CLASS_UUIDS = 5;
    private static final int SERVICES_INCOMPLETE_LIST_128_BIT_SERVICE_CLASS_UUIDS = 6;
    private static final int SERVICES_INCOMPLETE_LIST_16_BIT_SERVICE_CLASS_UUIDS = 2;
    private static final int SERVICES_INCOMPLETE_LIST_32_BIT_SERVICE_CLASS_UUIDS = 4;
    private static final int SERVICE_DATA_128_BIT = 33;
    private static final int SERVICE_DATA_16_BIT = 22;
    private static final int SERVICE_DATA_32_BIT = 32;
    private static final String TAG = "AdvertisingDataParser";
    private static final int TX_POWER_LEVEL = 10;

    /* JADX WARN: Failed to find 'out' block for switch in B:22:0x004f. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void clone(byte[] r12, java.io.ByteArrayOutputStream r13, java.io.ByteArrayOutputStream r14, boolean r15) {
        /*
            if (r15 != 0) goto L11
            int r15 = android.os.Build.VERSION.SDK_INT
            r0 = 26
            if (r15 < r0) goto L11
            android.bluetooth.BluetoothAdapter r15 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            int r15 = r15.getLeMaximumAdvertisingDataLength()
            goto L13
        L11:
            r15 = 31
        L13:
            android.bluetooth.BluetoothAdapter r0 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            java.lang.String r1 = r0.getName()
            r2 = 0
            if (r1 == 0) goto L27
            java.lang.String r0 = r0.getName()
            int r0 = r0.length()
            goto L28
        L27:
            r0 = 0
        L28:
            r1 = 0
            r3 = 0
            r4 = 0
        L2b:
            int r5 = r12.length
            if (r1 >= r5) goto La8
            r5 = r12[r1]
            int r6 = r1 + 1
            r7 = r12[r6]
            r8 = 255(0xff, float:3.57E-43)
            r7 = r7 & r8
            r9 = 9
            r10 = 2
            r11 = 1
            if (r7 == r9) goto L8e
            r9 = 10
            if (r7 == r9) goto L70
            r9 = 22
            if (r7 == r9) goto L59
            if (r7 == r8) goto L59
            r8 = 32
            if (r7 == r8) goto L59
            r8 = 33
            if (r7 == r8) goto L59
            switch(r7) {
                case 2: goto L53;
                case 3: goto L59;
                case 4: goto L53;
                case 5: goto L59;
                case 6: goto L53;
                case 7: goto L59;
                default: goto L52;
            }
        L52:
            goto La4
        L53:
            r7 = r12[r6]     // Catch: java.lang.Exception -> La4
            int r7 = r7 + r11
            byte r7 = (byte) r7     // Catch: java.lang.Exception -> La4
            r12[r6] = r7     // Catch: java.lang.Exception -> La4
        L59:
            int r6 = r13.size()     // Catch: java.lang.Exception -> La4
            int r6 = r6 + r5
            int r6 = r6 + r11
            int r6 = r6 + r3
            if (r6 <= r15) goto L64
            r6 = r14
            goto L65
        L64:
            r6 = r13
        L65:
            int r7 = r1 + r5
            int r7 = r7 + r11
            byte[] r7 = java.util.Arrays.copyOfRange(r12, r1, r7)     // Catch: java.lang.Exception -> La4
            r6.write(r7)     // Catch: java.lang.Exception -> La4
            goto La4
        L70:
            int r6 = r13.size()     // Catch: java.lang.Exception -> La4
            int r6 = r6 + r3
            r7 = 3
            int r6 = r6 + r7
            if (r6 > r15) goto L7f
            if (r4 == 0) goto L7c
            goto L7f
        L7c:
            r4 = r13
            r6 = 1
            goto L81
        L7f:
            r6 = r4
            r4 = r14
        L81:
            byte[] r7 = new byte[r7]     // Catch: java.lang.Exception -> L8c
            r7[r2] = r10     // Catch: java.lang.Exception -> L8c
            r7[r11] = r9     // Catch: java.lang.Exception -> L8c
            r7[r10] = r2     // Catch: java.lang.Exception -> L8c
            r4.write(r7)     // Catch: java.lang.Exception -> L8c
        L8c:
            r4 = r6
            goto La4
        L8e:
            int r6 = r13.size()     // Catch: java.lang.Exception -> La4
            int r6 = r6 + r3
            int r6 = r6 + r0
            int r6 = r6 + r10
            if (r6 <= r15) goto L99
            r6 = r14
            goto L9a
        L99:
            r6 = r13
        L9a:
            byte[] r7 = new byte[r10]     // Catch: java.lang.Exception -> La4
            r7[r2] = r11     // Catch: java.lang.Exception -> La4
            r7[r11] = r9     // Catch: java.lang.Exception -> La4
            r6.write(r7)     // Catch: java.lang.Exception -> La4
            r3 = r0
        La4:
            int r5 = r5 + 1
            int r1 = r1 + r5
            goto L2b
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.parser.AdvertisingDataParser.clone(byte[], java.io.ByteArrayOutputStream, java.io.ByteArrayOutputStream, boolean):void");
    }

    public static byte[] copyServiceData(int i, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (i == 22) {
            return Arrays.copyOfRange(bArr, 2, bArr.length);
        }
        if (i == 32) {
            return Arrays.copyOfRange(bArr, 4, bArr.length);
        }
        if (i != 33) {
            return null;
        }
        return Arrays.copyOfRange(bArr, 16, bArr.length);
    }

    public static void parse(AdvertiseData.Builder builder, byte[] bArr) {
        int i;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        int i2 = 0;
        while (i2 < bArr.length && (i = bArr[i2] & FlagsParser.UNKNOWN_FLAGS) != 0) {
            int i3 = i2 + 1;
            int i4 = bArr[i3] & FlagsParser.UNKNOWN_FLAGS;
            if (i4 == 9) {
                builder.setIncludeDeviceName(true);
            } else if (i4 == 10) {
                builder.setIncludeTxPowerLevel(true);
            } else if (i4 == 22) {
                builder.addServiceData(new ParcelUuid(DatabaseUtils.generateBluetoothBaseUuid(ParserUtils.decodeUuid16(bArr, i3 + 1) & 65535)), Arrays.copyOfRange(bArr, i3 + 3, i3 + i));
            } else if (i4 == 255) {
                int i5 = i3 + 1;
                builder.addManufacturerData(ParserUtils.decodeUuid16(bArr, i5), i > 3 ? Arrays.copyOfRange(bArr, i5 + 2, i3 + i) : new byte[0]);
            } else if (i4 == 32) {
                builder.addServiceData(new ParcelUuid(DatabaseUtils.generateBluetoothBaseUuid(ParserUtils.decodeUuid32(bArr, i3 + 1))), Arrays.copyOfRange(bArr, i3 + 5, i3 + i));
            } else if (i4 != 33) {
                switch (i4) {
                    case 2:
                    case 3:
                        for (int i6 = 0; i6 < i - 1; i6 += 2) {
                            builder.addServiceUuid(new ParcelUuid(DatabaseUtils.generateBluetoothBaseUuid(ParserUtils.decodeUuid16(bArr, i6 + i3 + 1))));
                        }
                        break;
                    case 4:
                    case 5:
                        for (int i7 = 0; i7 < i - 1; i7 += 4) {
                            builder.addServiceUuid(new ParcelUuid(DatabaseUtils.generateBluetoothBaseUuid(ParserUtils.decodeUuid32(bArr, i7 + i3 + 1))));
                        }
                        break;
                    case 6:
                    case 7:
                        for (int i8 = 0; i8 < i - 1; i8 += 16) {
                            builder.addServiceUuid(new ParcelUuid(ParserUtils.decodeUuid128(bArr, i8 + i3 + 1)));
                        }
                        break;
                    default:
                        Log.w(TAG, "EIR type " + i4 + " is not supported");
                        break;
                }
            } else {
                builder.addServiceData(new ParcelUuid(ParserUtils.decodeUuid128(bArr, i3 + 1)), Arrays.copyOfRange(bArr, i3 + 17, i3 + i));
            }
            i2 = i3 + i;
        }
    }

    public static UUID parseUuid(int i, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (i != 22) {
            if (i != 32) {
                if (i != 33) {
                    switch (i) {
                        case 2:
                        case 3:
                            break;
                        case 4:
                        case 5:
                            break;
                        case 6:
                        case 7:
                            break;
                        default:
                            return null;
                    }
                }
                return ParserUtils.decodeUuid128(bArr, 0);
            }
            return DatabaseUtils.generateBluetoothBaseUuid(ParserUtils.decodeUuid32(bArr, 0));
        }
        return DatabaseUtils.generateBluetoothBaseUuid(ParserUtils.decodeUuid16(bArr, 0));
    }
}
