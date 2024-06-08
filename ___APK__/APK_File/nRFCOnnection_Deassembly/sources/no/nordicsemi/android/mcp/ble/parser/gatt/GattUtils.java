package no.nordicsemi.android.mcp.ble.parser.gatt;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;

/* loaded from: classes.dex */
public class GattUtils {
    private static final long LSB = -9223371485494954757L;
    private static final long MSB = 4096;

    private GattUtils() {
    }

    public static UUID generateBluetoothBaseUuid(long j) {
        return new UUID((j << 32) + 4096, -9223371485494954757L);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x008e A[Catch: all -> 0x00aa, TryCatch #0 {all -> 0x00aa, blocks: (B:3:0x000d, B:5:0x0016, B:7:0x0022, B:8:0x003b, B:12:0x005c, B:14:0x007d, B:16:0x0084, B:18:0x008e, B:19:0x0096, B:20:0x00a2, B:25:0x0043, B:26:0x0033, B:27:0x004c), top: B:2:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCharacteristicAsString(no.nordicsemi.android.mcp.database.provider.DatabaseHelper r10, android.content.Context r11, android.bluetooth.BluetoothGattCharacteristic r12) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.util.UUID r1 = r12.getUuid()
            android.database.Cursor r1 = r10.getCharacteristic(r1)
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> Laa
            r3 = 0
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L4c
            r2 = 4
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> Laa
            r6 = 3
            int r6 = r1.getInt(r6)     // Catch: java.lang.Throwable -> Laa
            if (r6 <= 0) goto L33
            java.util.Locale r7 = java.util.Locale.US     // Catch: java.lang.Throwable -> Laa
            java.lang.String r8 = "0x%04X"
            java.lang.Object[] r9 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Laa
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> Laa
            r9[r3] = r6     // Catch: java.lang.Throwable -> Laa
            java.lang.String r6 = java.lang.String.format(r7, r8, r9)     // Catch: java.lang.Throwable -> Laa
            goto L3b
        L33:
            java.util.UUID r6 = r12.getUuid()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Laa
        L3b:
            r7 = 6
            boolean r8 = r1.isNull(r7)     // Catch: java.lang.Throwable -> Laa
            if (r8 == 0) goto L43
            goto L5b
        L43:
            int r7 = r1.getInt(r7)     // Catch: java.lang.Throwable -> Laa
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> Laa
            goto L5c
        L4c:
            r2 = 2131821325(0x7f11030d, float:1.927539E38)
            java.lang.String r2 = r11.getString(r2)     // Catch: java.lang.Throwable -> Laa
            java.util.UUID r6 = r12.getUuid()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Laa
        L5b:
            r7 = r5
        L5c:
            r0.append(r2)     // Catch: java.lang.Throwable -> Laa
            java.lang.String r2 = "\n"
            r0.append(r2)     // Catch: java.lang.Throwable -> Laa
            r0.append(r6)     // Catch: java.lang.Throwable -> Laa
            java.lang.String r2 = "\nProperties: "
            r0.append(r2)     // Catch: java.lang.Throwable -> Laa
            int r2 = r12.getProperties()     // Catch: java.lang.Throwable -> Laa
            java.lang.String r11 = getPropertiesAsString(r11, r2)     // Catch: java.lang.Throwable -> Laa
            r0.append(r11)     // Catch: java.lang.Throwable -> Laa
            byte[] r11 = r12.getValue()     // Catch: java.lang.Throwable -> Laa
            if (r11 == 0) goto La2
            byte[] r11 = r12.getValue()     // Catch: java.lang.Throwable -> Laa
            int r11 = r11.length     // Catch: java.lang.Throwable -> Laa
            if (r11 <= 0) goto La2
            java.lang.String r11 = no.nordicsemi.android.mcp.ble.parser.CharacteristicParser.getValueAsString(r10, r12, r7, r4)     // Catch: java.lang.Throwable -> Laa
            boolean r2 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> Laa
            if (r2 != 0) goto L96
            java.lang.String r2 = "\nParsed value: "
            r0.append(r2)     // Catch: java.lang.Throwable -> Laa
            r0.append(r11)     // Catch: java.lang.Throwable -> Laa
        L96:
            java.lang.String r11 = "\nRaw value: "
            r0.append(r11)     // Catch: java.lang.Throwable -> Laa
            java.lang.String r10 = no.nordicsemi.android.mcp.ble.parser.CharacteristicParser.getValueAsString(r10, r12, r5, r3)     // Catch: java.lang.Throwable -> Laa
            r0.append(r10)     // Catch: java.lang.Throwable -> Laa
        La2:
            java.lang.String r10 = r0.toString()     // Catch: java.lang.Throwable -> Laa
            r1.close()
            return r10
        Laa:
            r10 = move-exception
            r1.close()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.ble.parser.gatt.GattUtils.getCharacteristicAsString(no.nordicsemi.android.mcp.database.provider.DatabaseHelper, android.content.Context, android.bluetooth.BluetoothGattCharacteristic):java.lang.String");
    }

    public static int getPossibleWriteTypeCount(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int properties = bluetoothGattCharacteristic.getProperties();
        int i = (properties & 4) > 0 ? 1 : 0;
        if ((properties & 8) > 0) {
            i++;
        }
        return (properties & 64) > 0 ? i + 1 : i;
    }

    public static String getPropertiesAsString(Context context, int i) {
        Resources resources = context.getResources();
        String string = resources.getString(R.string.property_separator);
        StringBuilder sb = new StringBuilder();
        if ((i & 1) > 0) {
            sb.append(resources.getString(R.string.property_broadcast));
            sb.append(string);
        }
        if ((i & 128) > 0) {
            sb.append(resources.getString(R.string.property_extended_props));
            sb.append(string);
        }
        if ((i & 32) > 0) {
            sb.append(resources.getString(R.string.property_indicate));
            sb.append(string);
        }
        if ((i & 16) > 0) {
            sb.append(resources.getString(R.string.property_notify));
            sb.append(string);
        }
        if ((i & 2) > 0) {
            sb.append(resources.getString(R.string.property_read));
            sb.append(string);
        }
        if ((i & 64) > 0) {
            sb.append(resources.getString(R.string.property_signed_write));
            sb.append(string);
        }
        if ((i & 8) > 0) {
            sb.append(resources.getString(R.string.property_write));
            sb.append(string);
        }
        if ((i & 4) > 0) {
            sb.append(resources.getString(R.string.property_write_no_response));
            sb.append(string);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - string.length());
        }
        return sb.toString();
    }

    public static String getServiceAsString(DatabaseHelper databaseHelper, Context context, BluetoothGattService bluetoothGattService) {
        String string;
        String uuid;
        StringBuilder sb = new StringBuilder();
        Cursor service = databaseHelper.getService(bluetoothGattService.getUuid());
        try {
            if (service.moveToNext()) {
                string = service.getString(4);
                int i = service.getInt(3);
                if (i > 0) {
                    uuid = String.format(Locale.US, "0x%04X", Integer.valueOf(i));
                } else {
                    uuid = bluetoothGattService.getUuid().toString();
                }
            } else {
                string = context.getString(R.string.service_unknown);
                uuid = bluetoothGattService.getUuid().toString();
            }
            sb.append(string);
            sb.append("\n");
            sb.append(uuid);
            sb.append("\n");
            sb.append(getTypeAsString(context, bluetoothGattService.getType()));
            return sb.toString();
        } finally {
            service.close();
        }
    }

    public static String getTypeAsString(Context context, int i) {
        if (i != 0) {
            return context.getString(R.string.type_secondary);
        }
        return context.getString(R.string.type_primary);
    }

    public static String getUuidAsString(UUID uuid) {
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        String upperCase = uuid.toString().toUpperCase(Locale.US);
        if ((286331153 & mostSignificantBits) != 4096 && leastSignificantBits != -9223371485494954757L) {
            return upperCase;
        }
        if ((mostSignificantBits & 1229764173248856064L) == 0) {
            return "0x" + upperCase.substring(4, 8);
        }
        return "0x" + upperCase.substring(0, 8);
    }

    public static String getWriteTypeAsString(Context context, int i) {
        if (i == 1) {
            return context.getString(R.string.write_cmd);
        }
        if (i == 2) {
            return context.getString(R.string.write_req);
        }
        if (i != 4) {
            return context.getString(R.string.unknown);
        }
        return context.getString(R.string.write_signed);
    }

    public static String getWriteTypesAsString(Context context, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        int writeType = bluetoothGattCharacteristic.getWriteType();
        StringBuilder sb = new StringBuilder();
        if ((writeType & 1) > 0) {
            sb.append(context.getString(R.string.write_cmd));
            sb.append(", ");
        }
        if ((writeType & 2) > 0) {
            sb.append(context.getString(R.string.write_req));
            sb.append(", ");
        }
        if ((writeType & 4) > 0) {
            sb.append(context.getString(R.string.write_signed));
            sb.append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
