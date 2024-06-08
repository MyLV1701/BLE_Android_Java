package no.nordicsemi.android.support.v18.scanner;

import android.os.ParcelUuid;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;

/* loaded from: classes.dex */
final class g {

    /* renamed from: a, reason: collision with root package name */
    private static final ParcelUuid f3942a = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ParcelUuid a(byte[] bArr) {
        long j;
        if (bArr != null) {
            int length = bArr.length;
            if (length != 2 && length != 4 && length != 16) {
                throw new IllegalArgumentException("uuidBytes length invalid - " + length);
            }
            if (length == 16) {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
            }
            if (length == 2) {
                j = (bArr[0] & FlagsParser.UNKNOWN_FLAGS) + ((bArr[1] & FlagsParser.UNKNOWN_FLAGS) << 8);
            } else {
                j = ((bArr[3] & FlagsParser.UNKNOWN_FLAGS) << 24) + (bArr[0] & FlagsParser.UNKNOWN_FLAGS) + ((bArr[1] & FlagsParser.UNKNOWN_FLAGS) << 8) + ((bArr[2] & FlagsParser.UNKNOWN_FLAGS) << 16);
            }
            return new ParcelUuid(new UUID(f3942a.getUuid().getMostSignificantBits() + (j << 32), f3942a.getUuid().getLeastSignificantBits()));
        }
        throw new IllegalArgumentException("uuidBytes cannot be null");
    }
}
