package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;

/* loaded from: classes.dex */
public class EddystoneKeyContract {

    /* loaded from: classes.dex */
    public static final class Key implements BaseColumns, NameColumns, KeyColumns {
        private Key() {
        }
    }

    /* loaded from: classes.dex */
    protected interface KeyColumns {
        public static final String EXPONENT = "exponent";
        public static final String IDENTITY_KEY = "eik";
        public static final String ZERO_TIME = "zero_time";
    }
}
