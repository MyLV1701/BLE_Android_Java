package no.nordicsemi.android.mcp.util;

import java.util.UUID;
import no.nordicsemi.android.mcp.ble.parser.UuidLibrary;

/* loaded from: classes.dex */
public class AdoptedServicesHelper extends UuidLibrary {
    private static final UUID GENERIC_ACCESS = UuidLibrary.generateBluetoothBaseUuid(6144);
    private static final UUID GENERIC_ATTRIBUTE = UuidLibrary.generateBluetoothBaseUuid(6145);

    public static boolean isGenericAccess(UUID uuid) {
        return GENERIC_ACCESS.equals(uuid);
    }

    public static boolean isGenericAttribute(UUID uuid) {
        return GENERIC_ATTRIBUTE.equals(uuid);
    }
}
