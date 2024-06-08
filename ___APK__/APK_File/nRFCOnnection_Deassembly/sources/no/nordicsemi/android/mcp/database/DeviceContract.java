package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;

/* loaded from: classes.dex */
public class DeviceContract {

    /* loaded from: classes.dex */
    public static final class Device implements BaseColumns, NameColumns, DeviceColumns {
        private Device() {
        }
    }

    /* loaded from: classes.dex */
    protected interface DeviceColumns {
        public static final String ADDRESS = "address";
        public static final String COLOR = "color";
    }
}
