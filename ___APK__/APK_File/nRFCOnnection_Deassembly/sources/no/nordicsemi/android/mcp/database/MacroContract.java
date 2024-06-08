package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;
import no.nordicsemi.android.mcp.database.ConfigurationContract;

/* loaded from: classes.dex */
public class MacroContract extends ConfigurationContract {

    /* loaded from: classes.dex */
    public static final class Macro implements BaseColumns, NameColumns, ConfigurationContract.XmlColumns, MacroColumns, UndoColumns {
        private Macro() {
        }
    }

    /* loaded from: classes.dex */
    protected interface MacroColumns {
        public static final String GROUP_ID = "group_id";
        public static final String IS_GROUP = "is_group";
        public static final String MACROS_COUNT = "macros_count";
    }
}
