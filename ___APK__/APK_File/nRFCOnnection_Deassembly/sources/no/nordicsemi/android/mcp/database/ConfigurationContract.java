package no.nordicsemi.android.mcp.database;

import android.provider.BaseColumns;

/* loaded from: classes.dex */
public class ConfigurationContract {

    /* loaded from: classes.dex */
    public static final class Xml implements BaseColumns, NameColumns, XmlColumns, UndoColumns {
        private Xml() {
        }
    }

    /* loaded from: classes.dex */
    protected interface XmlColumns {
        public static final String XML = "xml";
    }
}
