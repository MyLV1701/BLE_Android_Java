package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;

/* loaded from: classes.dex */
public class SavedPacketContract {

    /* loaded from: classes.dex */
    public static final class Data implements BaseColumns, NameColumns, SavedPacketColumns, UndoColumns {
        private Data() {
        }
    }

    /* loaded from: classes.dex */
    protected interface SavedPacketColumns {
        public static final String CREATED = "created";
        public static final String DATA = "data";
        public static final String RECENT = "recent";
    }
}
