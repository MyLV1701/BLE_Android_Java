package no.nordicsemi.android.mcp.database;

import android.net.Uri;
import android.provider.BaseColumns;
import no.nordicsemi.android.mcp.database.provider.DatabaseContentProvider;

/* loaded from: classes.dex */
public class DescriptorContract {

    /* loaded from: classes.dex */
    public static final class Descriptor implements BaseColumns, NameColumns, UuidColumns, FormatColumns, EditableColumns {
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/no.nordicsemi.android.mcp.descriptor";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/no.nordicsemi.android.mcp.descriptor";
        public static final String DESCRIPTOR_CONTENT_DIRECTORY = "descriptor";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(DatabaseContentProvider.AUTHORITY_URI, DESCRIPTOR_CONTENT_DIRECTORY);
        public static final String USER_DESCRIPTOR_CONTENT_DIRECTORY = "descriptor/user";
        public static final Uri USER_CONTENT_URI = Uri.withAppendedPath(DatabaseContentProvider.AUTHORITY_URI, USER_DESCRIPTOR_CONTENT_DIRECTORY);

        private Descriptor() {
        }
    }
}
