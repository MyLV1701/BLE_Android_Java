package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;

/* loaded from: classes.dex */
public class AdvertisingContract {

    /* loaded from: classes.dex */
    public static final class Advertising implements BaseColumns, NameColumns, OreoConfigColumns, DataColumns, UndoColumns {
        private Advertising() {
        }
    }

    /* loaded from: classes.dex */
    protected interface DataColumns {
        public static final String ADV_DATA = "data";
        public static final String PERIODIC_DATA = "periodic_data";
        public static final String SCAN_RESP_DATA = "resp_data";
    }

    /* loaded from: classes.dex */
    protected interface OldConfigColumns {

        @Deprecated
        public static final String MODE = "mode";

        @Deprecated
        public static final String TX_POWER = "tx_power";
    }

    /* loaded from: classes.dex */
    protected interface OreoConfigColumns extends OldConfigColumns {
        public static final String ANONYMOUS = "anonymous";
        public static final String CONNECTIBLE = "connectible";
        public static final String INCLUDE_TX_POWER = "include_tx_power";
        public static final String INTERVAL = "interval";
        public static final String LEGACY_MODE = "legacy_mode";
        public static final String PERIODIC_INCLUDE_TX_POWER = "periodic_include_tx_power";
        public static final String PERIODIC_INTERVAL = "periodic_interval";
        public static final String PRIMARY_PHY = "primary_phy";
        public static final String SCANNABLE = "scannable";
        public static final String SECONDARY_PHY = "secondary_phy";
        public static final String TX_POWER_LEVEL = "tx_power_level";
    }
}
