package no.nordicsemi.android.mcp.database;

import android.net.Uri;
import android.provider.BaseColumns;
import no.nordicsemi.android.mcp.database.provider.DatabaseContentProvider;

/* loaded from: classes.dex */
public class CharacteristicContract {

    /* loaded from: classes.dex */
    public static final class Characteristic implements BaseColumns, NameColumns, UuidColumns, FormatColumns, EditableColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/no.nordicsemi.android.mcp.characteristic";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/no.nordicsemi.android.mcp.characteristic";
        public static final String CHARACTERISTIC_CONTENT_DIRECTORY = "characteristic";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContentProvider.AUTHORITY_URI, CHARACTERISTIC_CONTENT_DIRECTORY);
        public static final String USER_CHARACTERISTIC_CONTENT_DIRECTORY = "characteristic/user";
        public static final Uri USER_CONTENT_URI = Uri.withAppendedPath(DatabaseContentProvider.AUTHORITY_URI, USER_CHARACTERISTIC_CONTENT_DIRECTORY);

        private Characteristic() {
        }
    }
}
