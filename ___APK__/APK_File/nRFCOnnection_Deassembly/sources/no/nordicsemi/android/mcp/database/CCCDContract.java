package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;

/* loaded from: classes.dex */
public class CCCDContract {

    /* loaded from: classes.dex */
    protected interface CCCDColumns {
        public static final String ADDRESS = "address";
        public static final String INSTANCE = "instance";
        public static final String LSB = "lsb";
        public static final String MSB = "msb";
        public static final String SERVICE_INSTANCE = "service_instance";
        public static final String VALUE = "value";
    }

    /* loaded from: classes.dex */
    public static final class State implements BaseColumns, CCCDColumns {
        private State() {
        }
    }
}
