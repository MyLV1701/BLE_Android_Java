package no.nordicsemi.android.mcp.database;

import java.util.UUID;

/* loaded from: classes.dex */
public class DatabaseUtils {
    public static final long LSB = -9223371485494954757L;
    public static final long MSB = 4096;

    public static UUID generateBluetoothBaseUuid(long j) {
        return new UUID((j << 32) + MSB, LSB);
    }

    public static long getLsbForShortUuid(long j) {
        return LSB;
    }

    public static long getMsbForShortUuid(long j) {
        return (j << 32) + MSB;
    }
}
