package no.nordicsemi.android.mcp.util;

import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.UuidLibrary;

/* loaded from: classes.dex */
public class AdoptedCharacteristicsHelper extends UuidLibrary {
    public static boolean isHeartRateControlPoint(UUID uuid) {
        return UuidLibrary.HEART_RATE_CONTROL_POINT.equals(uuid);
    }
}
