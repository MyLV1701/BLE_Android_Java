package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;
import no.nordicsemi.android.mcp.database.DeviceContract;

/* loaded from: classes.dex */
public class FavoriteDeviceContract extends DeviceContract {

    /* loaded from: classes.dex */
    public static final class Device implements BaseColumns, NameColumns, DeviceContract.DeviceColumns, FavoriteDeviceColumns {
        private Device() {
        }
    }

    /* loaded from: classes.dex */
    protected interface FavoriteDeviceColumns {
        public static final String ADDED = "added";
        public static final String APPEARANCE = "appearance";
        public static final String LAST_SEEN = "last_seen";
    }
}
