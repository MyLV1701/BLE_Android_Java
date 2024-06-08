package no.nordicsemi.android.mcp.database.provider;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;
import no.nordicsemi.android.mcp.ble.model.Device;
import no.nordicsemi.android.mcp.ble.parser.gap.FlagsParser;
import no.nordicsemi.android.mcp.ble.parser.gatt.ClientCharacteristicConfigurationParser;
import no.nordicsemi.android.mcp.ble.parser.utils.ParserUtils;
import no.nordicsemi.android.mcp.database.AdvertisingContract;
import no.nordicsemi.android.mcp.database.CCCDContract;
import no.nordicsemi.android.mcp.database.ConfigurationContract;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.DeviceContract;
import no.nordicsemi.android.mcp.database.EddystoneKeyContract;
import no.nordicsemi.android.mcp.database.EditableColumns;
import no.nordicsemi.android.mcp.database.FavoriteDeviceContract;
import no.nordicsemi.android.mcp.database.FormatColumns;
import no.nordicsemi.android.mcp.database.MacroContract;
import no.nordicsemi.android.mcp.database.SavedPacketContract;
import no.nordicsemi.android.mcp.database.UndoColumns;
import no.nordicsemi.android.mcp.database.UuidColumns;

/* loaded from: classes.dex */
public class DatabaseHelper {
    private static final String CCCD_SELECTION = "address=? AND msb=? AND lsb=? AND service_instance=? AND instance=?";
    private static final String CHARACTERISTIC_SELECTION = "lsb=? AND msb=?";
    private static final String CHARACTERISTIC_SELECTION_SHORT = "number=?";
    private static final String DATABASE_NAME = "mcp.db";
    private static final int DATABASE_VERSION = 19;
    private static final String DEFAULT_GROUP_SELECTION = "group_id IS NULL AND deleted=0";
    private static final String DELETED_SELECTION = "deleted=1";
    private static final String DESCRIPTOR_SELECTION = "lsb=? AND msb=?";
    private static final String DESCRIPTOR_SELECTION_SHORT = "number=?";
    private static final String DEVICE_ADDRESS_SELECTION = "address=?";
    private static final int FORMAT_BEACON_MAJOR_MINOR = 2;
    private static final int FORMAT_BYTE = 0;
    private static final int FORMAT_BYTE_ARRAY = 3;
    private static final int FORMAT_TEXT = 1;
    private static final String GROUP_SELECTION = "group_id=? AND deleted=0";
    private static final String ID_AND_NOT_DELETED_SELECTION = "_id=? AND deleted=0";
    private static final String ID_SELECTION = "_id=?";
    private static final String NAME_SELECTION = "name=?";
    private static final String NOT_DELETED_SELECTION = "deleted=0";
    private static final String OTHER_GROUPS_FROM_GROUP_SELECTION = "xml IS NULL AND deleted=0 AND group_id=? AND _id!=?";
    private static final String OTHER_GROUPS_FROM_MAIN_GROUP_SELECTION = "xml IS NULL AND deleted=0 AND group_id IS NULL AND _id!=?";
    private static final String SERVICE_SELECTION = "lsb=? AND msb=?";
    private static final String SERVICE_SELECTION_SHORT = "number=?";
    private static SQLiteDatabase mDatabase;
    private static SQLiteHelper mDatabaseHelper;
    private static final String[] ID_PROJECTION = {"_id"};
    private static final String[] DEVICE_PROJECTION = {"_id", "address", "name", FavoriteDeviceContract.FavoriteDeviceColumns.APPEARANCE, FavoriteDeviceContract.FavoriteDeviceColumns.ADDED, FavoriteDeviceContract.FavoriteDeviceColumns.LAST_SEEN};
    private static final String[] SERVICE_PROJECTION = {"_id", "msb", "lsb", "number", "name", UuidColumns.SPECIFICATION_TYPE, EditableColumns.EDITABLE};
    private static final String[] UUID_PROJECTION = {"_id", "msb", "lsb", "number", "name", UuidColumns.SPECIFICATION_TYPE, FormatColumns.FORMAT, EditableColumns.EDITABLE};
    private static final String[] VALUE_PROJECTION = {CCCDContract.CCCDColumns.VALUE};
    private static final String[] NAME_AND_COLOR_PROJECTION = {"name", DeviceContract.DeviceColumns.COLOR};
    private static final String[] SAVED_PACKET_PROJECTION = {"_id", "name", SavedPacketContract.SavedPacketColumns.CREATED, SavedPacketContract.SavedPacketColumns.RECENT, "data", UndoColumns.DELETED};
    private static final String[] NAME_PROJECTION = {"_id", "name"};
    private static final String[] XML_PROJECTION = {"_id", ConfigurationContract.XmlColumns.XML};
    private static final String[] MACRO_NAME_PROJECTION = {"_id", "name", "xml IS NULL AS is_group", "(SELECT COUNT(_id) FROM macros WHERE group_id=mac._id AND deleted=0) as macros_count"};
    private static final String[] MACRO_PROJECTION = {"_id", "name", ConfigurationContract.XmlColumns.XML, MacroContract.MacroColumns.GROUP_ID};
    private static final String[] ADVERTISING_PACKET_PROJECTION = {"_id", "name", AdvertisingContract.OreoConfigColumns.CONNECTIBLE, AdvertisingContract.OreoConfigColumns.SCANNABLE, AdvertisingContract.OreoConfigColumns.ANONYMOUS, AdvertisingContract.OreoConfigColumns.INTERVAL, AdvertisingContract.OreoConfigColumns.TX_POWER_LEVEL, AdvertisingContract.OreoConfigColumns.INCLUDE_TX_POWER, AdvertisingContract.OreoConfigColumns.LEGACY_MODE, AdvertisingContract.OreoConfigColumns.PRIMARY_PHY, AdvertisingContract.OreoConfigColumns.SECONDARY_PHY, "data", AdvertisingContract.DataColumns.SCAN_RESP_DATA, AdvertisingContract.DataColumns.PERIODIC_DATA, AdvertisingContract.OreoConfigColumns.PERIODIC_INTERVAL, AdvertisingContract.OreoConfigColumns.PERIODIC_INCLUDE_TX_POWER};
    private static final String[] EDDYSTONE_KEY_PROJECTION = {"_id", "name", EddystoneKeyContract.KeyColumns.IDENTITY_KEY, EddystoneKeyContract.KeyColumns.ZERO_TIME, EddystoneKeyContract.KeyColumns.EXPONENT};
    private final ContentValues mValues = new ContentValues();
    private final String[] mSingleArg = new String[1];
    private final String[] mDoubleArg = new String[2];
    private final String[] m5Arg = new String[5];

    /* loaded from: classes.dex */
    private class SQLiteHelper extends SQLiteOpenHelper {
        private static final String ALTER_CHARACTERISTICS_1 = "ALTER TABLE characteristics ADD COLUMN editable INTEGER NOT NULL DEFAULT(0)";
        private static final String ALTER_CHARACTERISTICS_2 = "CREATE UNIQUE INDEX IF NOT EXISTS unique_index_char ON characteristics(msb, lsb);";
        private static final String ALTER_DESCRIPTORS_1 = "ALTER TABLE descriptors ADD COLUMN format INTEGER";
        private static final String ALTER_DESCRIPTORS_2 = "ALTER TABLE descriptors ADD COLUMN editable INTEGER NOT NULL DEFAULT(0)";
        private static final String ALTER_DESCRIPTORS_3 = "CREATE UNIQUE INDEX IF NOT EXISTS unique_index_descr ON descriptors(msb, lsb);";
        private static final String ALTER_DEVICES1 = "ALTER TABLE devices ADD COLUMN name TEXT";
        private static final String ALTER_DEVICES2 = "ALTER TABLE devices ADD COLUMN appearance INTEGER NOT NULL DEFAULT(0)";
        private static final String ALTER_DEVICES3 = "ALTER TABLE devices ADD COLUMN added INTEGER";
        private static final String ALTER_DEVICES4 = "ALTER TABLE devices ADD COLUMN last_seen INTEGER";
        private static final String ALTER_SERVICES_1 = "ALTER TABLE services ADD COLUMN editable INTEGER NOT NULL DEFAULT(0)";
        private static final String ALTER_SERVICES_2 = "CREATE UNIQUE INDEX IF NOT EXISTS unique_index_service ON services(msb, lsb);";
        private static final String CREATE_ADVERTISING_PACKETS = "CREATE TABLE advertising_packets(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, connectible INTEGER NOT NULL, scannable INTEGER NOT NULL DEFAULT(1), anonymous INTEGER NOT NULL DEFAULT(0), legacy_mode INTEGER NOT NULL DEFAULT(1), interval INTEGER NOT NULL, tx_power_level INTEGER NOT NULL, include_tx_power INTEGER, primary_phy INTEGER, secondary_phy INTEGER, data BLOB, resp_data BLOB, periodic_data BLOB, periodic_interval INTEGER, periodic_include_tx_power INTEGER, deleted INTEGER NOT NULL DEFAULT(0))";
        private static final String CREATE_CCCD = "CREATE TABLE cccd(_id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT NOT NULL, msb INTEGER NOT NULL, lsb INTEGER NOT NULL, service_instance INTEGER NOT NULL, instance INTEGER NOT NULL, value INTEGER);";
        private static final String CREATE_CHARACTERISTICS = "CREATE TABLE characteristics(_id INTEGER PRIMARY KEY AUTOINCREMENT, msb INTEGER NOT NULL, lsb INTEGER NOT NULL, number INTEGER, name TEXT NOT NULL, type TEXT, format INTEGER, editable INTEGER NOT NULL DEFAULT(0),UNIQUE(msb,lsb));";
        private static final String CREATE_DESCRIPTORS = "CREATE TABLE descriptors(_id INTEGER PRIMARY KEY AUTOINCREMENT, msb INTEGER NOT NULL, lsb INTEGER NOT NULL, number INTEGER, name TEXT NOT NULL, type TEXT, format INTEGER, editable INTEGER NOT NULL DEFAULT(0),UNIQUE(msb,lsb));";
        private static final String CREATE_DEVICES = "CREATE TABLE devices(_id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT NOT NULL UNIQUE, name TEXT, appearance INTEGER NOT NULL DEFAULT(0), added INTEGER, last_seen INTEGER);";
        private static final String CREATE_EDDYSTONE_KEYS = "CREATE TABLE eddystone_keys(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, eik BLOB UNIQUE NOT NULL, zero_time INTEGER NOT NULL, exponent INTEGER NOT NULL)";
        private static final String CREATE_MACROS = "CREATE TABLE macros(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL, xml TEXT, deleted INTEGER NOT NULL DEFAULT(0), group_id INTEGER REFERENCES macros(_id) ON DELETE CASCADE)";
        private static final String CREATE_NAMES_AND_COLORS = "CREATE TABLE names_and_colors(_id INTEGER PRIMARY KEY AUTOINCREMENT, address TEXT UNIQUE NOT NULL, name TEXT, color INTEGER);";
        private static final String CREATE_SAVED_PACKETS = "CREATE TABLE saved_packets(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL, created INTEGER NOT NULL, recent INTEGER NOT NULL DEFAULT(0), data BLOB NOT NULL, deleted INTEGER NOT NULL DEFAULT(0))";
        private static final String CREATE_SERVER_CONFIGURATIONS = "CREATE TABLE server_configurations(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL, xml TEXT NOT NULL, deleted INTEGER NOT NULL DEFAULT(0))";
        private static final String CREATE_SERVICES = "CREATE TABLE services(_id INTEGER PRIMARY KEY AUTOINCREMENT, msb INTEGER NOT NULL, lsb INTEGER NOT NULL, number INTEGER, name TEXT NOT NULL, type TEXT, editable INTEGER NOT NULL DEFAULT(0),UNIQUE(msb,lsb));";
        private static final String DROP_IF_EXISTS = "DROP TABLE IF EXISTS ";

        SQLiteHelper(Context context) {
            super(context, DatabaseHelper.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 19);
        }

        private void createMacrosTutorial(SQLiteDatabase sQLiteDatabase) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "Tutorial");
            sQLiteDatabase.insert(Tables.MACROS, null, contentValues);
            contentValues.clear();
            contentValues.put("name", "3. Send notification every second");
            contentValues.put(ConfigurationContract.XmlColumns.XML, "<macro name=\"3. Send notifications every second\" icon=\"UPLOAD\">\n   <assert-server-service description=\"To run this tutorial enable &apos;Sample configuration&apos; in GATT Server configuration. Service validation\" instance-id=\"0\" uuid=\"0000aaa0-0000-1000-8000-aabbccddeeff\">\n      <assert-characteristic description=\"Ensure 0000aaa1-0000-1000-8000-aabbccddeeff characteristic\" uuid=\"0000aaa1-0000-1000-8000-aabbccddeeff\">\n         <property name=\"NOTIFY\"/>\n      </assert-characteristic>\n   </assert-server-service>\n   <sleep description=\"This sample will show how to automate sending every second a notification from the Android device.\" timeout=\"300\" />\n   <sleep description=\"Make sure the connected device enabled CCCD for 0000aaa1-0000-1000-8000-aabbccddeeff characteristic.\" timeout=\"200\" />\n   <send-notification description=\"Send 0x0123456789 as notification from 0000aaa1-0000-1000-8000-aabbccddeeff.\" characteristic-uuid=\"0000aaa1-0000-1000-8000-aabbccddeeff\" service-uuid=\"0000aaa0-0000-1000-8000-aabbccddeeff\" value=\"0123456789\"/>\n   <sleep description=\"Now sleep some time, so the notification is sent every second.\" timeout=\"400\"/>\n   <sleep description=\"To run the macro in a loop check the &#8644; option above, and run macro with &#9654; button.\" timeout=\"100\"/>\n</macro>");
            contentValues.put(MacroContract.MacroColumns.GROUP_ID, (Integer) 1);
            sQLiteDatabase.insert(Tables.MACROS, null, contentValues);
            contentValues.clear();
            contentValues.put("name", "2. Sample macro");
            contentValues.put(ConfigurationContract.XmlColumns.XML, "<macro name=\"2. Sample macro\" icon=\"DOWNLOAD\">\n   <assert-service description=\"Please, connect to a device with Generic Access service with Device Name characteristic. Service validation\" uuid=\"00001800-0000-1000-8000-00805f9b34fb\">\n      <assert-characteristic description=\"Ensure Device Name\" uuid=\"00002a00-0000-1000-8000-00805f9b34fb\">\n         <property name=\"READ\"/>\n      </assert-characteristic>\n   </assert-service>\n   <sleep description=\"This sample will demonstrate some very basic operations you can do using macros.\" timeout=\"400\" />\n   <read description=\"First, read the Device Name characteristic value.\" characteristic-uuid=\"00002a00-0000-1000-8000-00805f9b34fb\" service-uuid=\"00001800-0000-1000-8000-00805f9b34fb\"/>\n   <read description=\"Incoming data may also be validated.\" characteristic-uuid=\"00002a00-0000-1000-8000-00805f9b34fb\" service-uuid=\"00001800-0000-1000-8000-00805f9b34fb\">\n      <assert-value description=\"Let&apos;s check if Device Name equals &apos;Kaczka&apos;.\" value-string=\"Kaczka\"/>\n   </read>\n   <sleep description=\"Now the macro will wait 3 seconds...\" timeout=\"3000\" />\n   <read-rssi description=\"...and read the RSSI of the connected device.\" />\n</macro>");
            contentValues.put(MacroContract.MacroColumns.GROUP_ID, (Integer) 1);
            sQLiteDatabase.insert(Tables.MACROS, null, contentValues);
            contentValues.clear();
            contentValues.put("name", "1. Click a row to expand");
            contentValues.put(ConfigurationContract.XmlColumns.XML, "<macro name=\"1. Click a row to expand\" icon=\"INFO\">\n   <sleep description=\"Hi, meet Macros!\" timeout=\"400\" />\n   <sleep description=\"Macros allow you to record list of operations and replay them with a single click of &#9654; button.\" timeout=\"1200\"/>\n   <sleep description=\"Using the 4 buttons above you may also: step over or skip a single operation (&#8631;), reset (&#8617;) or toggle the LOOP (&#8644;).\" timeout=\"1500\"/>\n   <sleep description=\"To start recording a macro just click the red &#9679; button and start performing operations as usual.\" timeout=\"1300\"/>\n   <sleep description=\"Macro will store meta-data of every operation you execute. The incoming events will also be recorded, together with received data.\" timeout=\"1800\"/>\n   <sleep description=\"If needed, use the &#8987;+ button to add a delay.\" timeout=\"400\"/>\n   <sleep description=\"When done, click the &#9632; button, and provide a name and icon.\" timeout=\"800\"/>\n   <sleep description=\"Your new macro will appear on this list. Folders will help you manage higher number of macros. To delete a macro make sure it&apos;s reset (&#8617;) and swipe it to left or right.\" timeout=\"900\"/>\n</macro>");
            contentValues.put(MacroContract.MacroColumns.GROUP_ID, (Integer) 1);
            sQLiteDatabase.insert(Tables.MACROS, null, contentValues);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(CREATE_SERVICES);
            sQLiteDatabase.execSQL(CREATE_CHARACTERISTICS);
            sQLiteDatabase.execSQL(CREATE_DESCRIPTORS);
            sQLiteDatabase.execSQL(CREATE_DEVICES);
            sQLiteDatabase.execSQL(CREATE_CCCD);
            sQLiteDatabase.execSQL(CREATE_SAVED_PACKETS);
            sQLiteDatabase.execSQL(CREATE_NAMES_AND_COLORS);
            sQLiteDatabase.execSQL(CREATE_SERVER_CONFIGURATIONS);
            sQLiteDatabase.execSQL(CREATE_ADVERTISING_PACKETS);
            sQLiteDatabase.execSQL(CREATE_EDDYSTONE_KEYS);
            sQLiteDatabase.execSQL(CREATE_MACROS);
            createMacrosTutorial(sQLiteDatabase);
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0005. Please report as an issue. */
        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            switch (i) {
                case 1:
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS descriptors");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS characteristics");
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS services");
                    onCreate(sQLiteDatabase);
                case 2:
                    sQLiteDatabase.execSQL(CREATE_DEVICES);
                case 4:
                case 5:
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cccd");
                case 3:
                    sQLiteDatabase.execSQL(CREATE_CCCD);
                case 6:
                    sQLiteDatabase.execSQL(CREATE_SAVED_PACKETS);
                case 7:
                    sQLiteDatabase.execSQL(ALTER_SERVICES_1);
                    sQLiteDatabase.execSQL(ALTER_CHARACTERISTICS_1);
                case 8:
                    if (i > 2) {
                        sQLiteDatabase.execSQL(ALTER_DEVICES1);
                        sQLiteDatabase.execSQL(ALTER_DEVICES2);
                        sQLiteDatabase.execSQL(ALTER_DEVICES3);
                        sQLiteDatabase.execSQL(ALTER_DEVICES4);
                    }
                case 9:
                    sQLiteDatabase.execSQL(CREATE_NAMES_AND_COLORS);
                case 10:
                    sQLiteDatabase.execSQL(CREATE_SERVER_CONFIGURATIONS);
                case 11:
                case 12:
                    sQLiteDatabase.execSQL(CREATE_EDDYSTONE_KEYS);
                case 13:
                    sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
                    sQLiteDatabase.execSQL(CREATE_MACROS);
                    createMacrosTutorial(sQLiteDatabase);
                case 14:
                    if (i == 14) {
                        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
                        sQLiteDatabase.execSQL("ALTER TABLE macros RENAME TO tmp");
                        sQLiteDatabase.execSQL(CREATE_MACROS);
                        sQLiteDatabase.execSQL("INSERT INTO macros(name, xml) SELECT name, xml FROM tmp");
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS tmp");
                    }
                case 15:
                    DatabaseHelper.this.migrateSavedData(sQLiteDatabase);
                case 16:
                    sQLiteDatabase.execSQL("DELETE FROM services WHERE _id NOT IN (SELECT MAX(_id) FROM services GROUP BY msb,lsb)");
                    sQLiteDatabase.execSQL("DELETE FROM characteristics WHERE _id NOT IN (SELECT MAX(_id) FROM characteristics GROUP BY msb,lsb)");
                    sQLiteDatabase.execSQL("DELETE FROM descriptors WHERE _id NOT IN (SELECT MAX(_id) FROM descriptors GROUP BY msb,lsb)");
                    sQLiteDatabase.execSQL(ALTER_SERVICES_2);
                    sQLiteDatabase.execSQL(ALTER_CHARACTERISTICS_2);
                    sQLiteDatabase.execSQL(ALTER_DESCRIPTORS_1);
                    sQLiteDatabase.execSQL(ALTER_DESCRIPTORS_2);
                    sQLiteDatabase.execSQL(ALTER_DESCRIPTORS_3);
                    sQLiteDatabase.execSQL("ALTER TABLE characteristics RENAME TO TMP");
                    sQLiteDatabase.execSQL(CREATE_CHARACTERISTICS);
                    sQLiteDatabase.execSQL("INSERT INTO characteristics(_id,name,msb,lsb,number,type,editable) SELECT _id, name, msb, lsb, short_uuid, type, editable FROM TMP");
                    sQLiteDatabase.execSQL("DROP TABLE TMP");
                    ContentValues contentValues = DatabaseHelper.this.mValues;
                    contentValues.clear();
                    contentValues.put(FormatColumns.FORMAT, (Integer) 1);
                    sQLiteDatabase.update(Tables.DESCRIPTORS, contentValues, "editable=0 AND number=10497", null);
                    sQLiteDatabase.update(Tables.CHARACTERISTICS, contentValues, "editable=0 AND number IN (10752,10790,10791,10793,10788,10789,10792,10887,10935,10937,10914,10934,10890,10896,10933,10942)", null);
                    sQLiteDatabase.update(Tables.CHARACTERISTICS, contentValues, "editable=0 AND (msb=-1195704594656310989 OR msb=-1195704573181474509)", null);
                case 17:
                    if (i > 11) {
                        sQLiteDatabase.execSQL("ALTER TABLE advertising_packets RENAME TO TMP");
                        sQLiteDatabase.execSQL(CREATE_ADVERTISING_PACKETS);
                        sQLiteDatabase.execSQL("INSERT INTO advertising_packets(_id, name, connectible, data, resp_data, interval, tx_power_level) SELECT _id, name, connectible, data, resp_data, CASE mode WHEN 0 THEN 1600 WHEN 1 THEN 400 ELSE 160 END, CASE tx_power WHEN 0 THEN -21 WHEN 1 THEN -15 WHEN 2 THEN -7 ELSE 1 END FROM TMP");
                        sQLiteDatabase.execSQL("DROP TABLE TMP");
                    } else {
                        sQLiteDatabase.execSQL(CREATE_ADVERTISING_PACKETS);
                    }
                case 18:
                    sQLiteDatabase.execSQL("UPDATE services SET name = LOWER(SUBSTR(name, 1, 1)) || SUBSTR(name, 2) WHERE name LIKE 'Micro:bit%'");
                    sQLiteDatabase.execSQL("UPDATE characteristics SET name = LOWER(SUBSTR(name, 1, 1)) || SUBSTR(name, 2) WHERE name LIKE 'Micro:bit%'");
                    return;
                default:
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    interface Tables {
        public static final String ADVERTISING_PACKETS = "advertising_packets";
        public static final String CCCD = "cccd";
        public static final String CHARACTERISTICS = "characteristics";
        public static final String DESCRIPTORS = "descriptors";
        public static final String EDDYSTONE_KEYS = "eddystone_keys";
        public static final String FAVORITE_DEVICES = "devices";
        public static final String MACROS = "macros";
        public static final String MACROS_ALIAS = "mac";
        public static final String NAMES_AND_COLORS = "names_and_colors";
        public static final String SAVED_PACKETS = "saved_packets";
        public static final String SERVER_CONFIGURATIONS = "server_configurations";
        public static final String SERVICES = "services";
    }

    public DatabaseHelper(Context context) {
        if (mDatabaseHelper == null) {
            mDatabaseHelper = new SQLiteHelper(context.getApplicationContext());
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void migrateSavedData(SQLiteDatabase sQLiteDatabase) {
        int i;
        int i2;
        int i3;
        int i4;
        ArrayList<String> arrayList = new ArrayList();
        Cursor query = sQLiteDatabase.query(Tables.SAVED_PACKETS, SAVED_PACKET_PROJECTION, null, null, null, null, null);
        while (query.moveToNext()) {
            try {
                long j = query.getLong(0);
                byte[] blob = query.getBlob(4);
                int length = blob.length;
                int intValue = ParserUtils.getIntValue(blob, 18, 0);
                byte b2 = blob[2];
                byte b3 = 1;
                if (intValue > 255 && b2 > 1) {
                    arrayList.add(String.valueOf(j));
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte b4 = 3;
                    byteArrayOutputStream.write(blob, 0, 3);
                    int i5 = 3;
                    while (i5 < length) {
                        int i6 = i5 + 1;
                        byte b5 = blob[i5];
                        byteArrayOutputStream.write(b5);
                        if (b5 != 0) {
                            if (b5 != b3) {
                                if (b5 == 2) {
                                    i = length;
                                    i2 = i6;
                                    i3 = 2;
                                } else if (b5 != b4) {
                                    i3 = ParserUtils.getTypeLen(b5);
                                    i = length;
                                    i2 = i6;
                                }
                            }
                            int i7 = i6 + 1;
                            i3 = blob[i6] & FlagsParser.UNKNOWN_FLAGS;
                            if (intValue > 255 && b2 == 1) {
                                i4 = i7;
                                i3 = intValue;
                                byte[] bArr = new byte[2];
                                i = length;
                                ParserUtils.setValue(bArr, 0, i3, 18);
                                byteArrayOutputStream.write(bArr, 0, 2);
                                i2 = i4;
                            }
                            i4 = i7;
                            byte[] bArr2 = new byte[2];
                            i = length;
                            ParserUtils.setValue(bArr2, 0, i3, 18);
                            byteArrayOutputStream.write(bArr2, 0, 2);
                            i2 = i4;
                        } else {
                            i = length;
                            i2 = i6;
                            i3 = 1;
                        }
                        byteArrayOutputStream.write(blob, i2, i3);
                        i5 = i2 + i3;
                        length = i;
                        b3 = 1;
                        b4 = 3;
                    }
                    this.mValues.clear();
                    this.mValues.put("data", byteArrayOutputStream.toByteArray());
                    this.mSingleArg[0] = String.valueOf(j);
                    sQLiteDatabase.update(Tables.SAVED_PACKETS, this.mValues, ID_SELECTION, this.mSingleArg);
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (query != null) {
                        try {
                            query.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th2;
                }
            }
        }
        if (query != null) {
            query.close();
        }
        if (arrayList.isEmpty()) {
            return;
        }
        for (String str : arrayList) {
            String[] strArr = this.mSingleArg;
            strArr[0] = str;
            sQLiteDatabase.delete(Tables.SAVED_PACKETS, ID_SELECTION, strArr);
        }
    }

    private String offset(int i) {
        return new String(new char[i * 3]).replace("\u0000", " ");
    }

    public long addAdvertisingPacket(String str, boolean z, boolean z2, int i, int i2, byte[] bArr, byte[] bArr2) {
        long updateAdvertisingPacket = updateAdvertisingPacket(0L, str, z, bArr, bArr2);
        updateAdvertisingPacket(updateAdvertisingPacket, z2);
        return updateAdvertisingPacket(updateAdvertisingPacket, i, i2);
    }

    public long addCharacteristic(long j, String str, String str2) {
        return addCharacteristic(j, str, str2, (Integer) null);
    }

    public long addDescriptor(long j, String str, String str2) {
        return addDescriptor(j, str, str2, null);
    }

    public long addMacro(String str, String str2, long j) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str);
        contentValues.put(ConfigurationContract.XmlColumns.XML, str2);
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        if (j > 0) {
            contentValues.put(MacroContract.MacroColumns.GROUP_ID, Long.valueOf(j));
        }
        return mDatabase.replace(Tables.MACROS, null, contentValues);
    }

    public long addMacroGroup(String str, long j) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str);
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        if (j > 0) {
            contentValues.put(MacroContract.MacroColumns.GROUP_ID, Long.valueOf(j));
        }
        return mDatabase.replace(Tables.MACROS, null, contentValues);
    }

    public long addServerConfiguration(String str, String str2) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str);
        contentValues.put(ConfigurationContract.XmlColumns.XML, str2);
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        return mDatabase.replace(Tables.SERVER_CONFIGURATIONS, null, contentValues);
    }

    public long addService(long j, String str, String str2) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(DatabaseUtils.getMsbForShortUuid(j)));
        contentValues.put("lsb", Long.valueOf(DatabaseUtils.getLsbForShortUuid(j)));
        contentValues.put("number", Long.valueOf(j));
        contentValues.put("name", str);
        contentValues.put(UuidColumns.SPECIFICATION_TYPE, str2);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 0);
        return mDatabase.replace(Tables.SERVICES, null, contentValues);
    }

    public long addUpdateOrRemoveCharacteristic(long j, long j2, String str, Integer num) {
        this.mDoubleArg[0] = String.valueOf(j2);
        this.mDoubleArg[1] = String.valueOf(j);
        mDatabase.delete(Tables.CHARACTERISTICS, "lsb=? AND msb=?", this.mDoubleArg);
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(j));
        contentValues.put("lsb", Long.valueOf(j2));
        contentValues.put("name", str);
        contentValues.put(FormatColumns.FORMAT, num);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 1);
        return mDatabase.replace(Tables.CHARACTERISTICS, null, contentValues);
    }

    public long addUpdateOrRemoveService(long j, long j2, String str) {
        this.mDoubleArg[0] = String.valueOf(j2);
        this.mDoubleArg[1] = String.valueOf(j);
        mDatabase.delete(Tables.SERVICES, "lsb=? AND msb=?", this.mDoubleArg);
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(j));
        contentValues.put("lsb", Long.valueOf(j2));
        contentValues.put("name", str);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 1);
        return mDatabase.replace(Tables.SERVICES, null, contentValues);
    }

    public int clearCCCD(BluetoothDevice bluetoothDevice) {
        this.mSingleArg[0] = bluetoothDevice.getAddress();
        return mDatabase.delete(Tables.CCCD, DEVICE_ADDRESS_SELECTION, this.mSingleArg);
    }

    public int clearFavorites() {
        return mDatabase.delete(Tables.FAVORITE_DEVICES, null, null);
    }

    public long cloneAdvertisingPacket(long j) {
        Cursor advertisingPacket = getAdvertisingPacket(j);
        try {
            if (!advertisingPacket.moveToNext()) {
                if (advertisingPacket == null) {
                    return -1L;
                }
                advertisingPacket.close();
                return -1L;
            }
            ContentValues contentValues = this.mValues;
            contentValues.clear();
            contentValues.put("name", advertisingPacket.getString(1));
            contentValues.put(AdvertisingContract.OreoConfigColumns.CONNECTIBLE, Integer.valueOf(advertisingPacket.getInt(2)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.SCANNABLE, Integer.valueOf(advertisingPacket.getInt(3)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.ANONYMOUS, Integer.valueOf(advertisingPacket.getInt(4)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.INTERVAL, Integer.valueOf(advertisingPacket.getInt(5)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.TX_POWER_LEVEL, Integer.valueOf(advertisingPacket.getInt(6)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.INCLUDE_TX_POWER, Integer.valueOf(advertisingPacket.getInt(7)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.LEGACY_MODE, Integer.valueOf(advertisingPacket.getInt(8)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.PRIMARY_PHY, Integer.valueOf(advertisingPacket.getInt(9)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.SECONDARY_PHY, Integer.valueOf(advertisingPacket.getInt(10)));
            contentValues.put("data", advertisingPacket.getBlob(11));
            contentValues.put(AdvertisingContract.DataColumns.SCAN_RESP_DATA, advertisingPacket.getBlob(12));
            contentValues.put(AdvertisingContract.DataColumns.PERIODIC_DATA, advertisingPacket.getBlob(13));
            contentValues.put(AdvertisingContract.OreoConfigColumns.PERIODIC_INTERVAL, Integer.valueOf(advertisingPacket.getInt(14)));
            contentValues.put(AdvertisingContract.OreoConfigColumns.PERIODIC_INCLUDE_TX_POWER, Integer.valueOf(advertisingPacket.getInt(15)));
            contentValues.put(UndoColumns.DELETED, (Integer) 0);
            long insert = mDatabase.insert(Tables.ADVERTISING_PACKETS, null, contentValues);
            if (advertisingPacket != null) {
                advertisingPacket.close();
            }
            return insert;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (advertisingPacket != null) {
                    try {
                        advertisingPacket.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public byte[] decodeETLM(byte[] bArr, int i) {
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        if (Build.VERSION.SDK_INT < 19) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        Cursor query = mDatabase.query(Tables.EDDYSTONE_KEYS, EDDYSTONE_KEY_PROJECTION, null, null, null, null, null);
        try {
            if (query.getCount() > 0) {
                int i2 = i + 12;
                byte[] copyOfRange = Arrays.copyOfRange(bArr, i, i2);
                int i3 = i + 14;
                byte[] copyOfRange2 = Arrays.copyOfRange(bArr, i2, i3);
                bArr4 = Arrays.copyOfRange(bArr, i3, i + 16);
                bArr3 = copyOfRange2;
                bArr2 = copyOfRange;
            } else {
                bArr2 = null;
                bArr3 = null;
                bArr4 = null;
            }
            while (query.moveToNext()) {
                byte[] decodeETLM = EddystoneUtils.decodeETLM(bArr2, bArr3, bArr4, query.getBlob(2), (int) (currentTimeMillis - query.getLong(3)), query.getInt(4));
                if (decodeETLM != null) {
                    if (query != null) {
                        query.close();
                    }
                    return decodeETLM;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public int deleteAdvertisingPacket(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 1);
        return mDatabase.update(Tables.ADVERTISING_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
    }

    public int deleteMacro(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 1);
        return mDatabase.update(Tables.MACROS, contentValues, ID_SELECTION, this.mSingleArg);
    }

    public int deleteSavedPacket(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 1);
        return mDatabase.update(Tables.SAVED_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
    }

    public int deleteServerConfiguration(String str) {
        this.mSingleArg[0] = str;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 1);
        return mDatabase.update(Tables.SERVER_CONFIGURATIONS, contentValues, NAME_SELECTION, this.mSingleArg);
    }

    public Cursor getAdvertisingPacket(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        return mDatabase.query(Tables.ADVERTISING_PACKETS, ADVERTISING_PACKET_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
    }

    public Cursor getAdvertisingPackets() {
        return mDatabase.query(Tables.ADVERTISING_PACKETS, ADVERTISING_PACKET_PROJECTION, NOT_DELETED_SELECTION, null, null, null, "_id ASC");
    }

    public Cursor getAllCharacteristics() {
        return mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, null, null, null, null, "name ASC");
    }

    public Cursor getAllDescriptors() {
        return mDatabase.query(Tables.DESCRIPTORS, UUID_PROJECTION, "number <> 10496 AND number <> 10498", null, null, null, "name ASC");
    }

    public Cursor getAllServices() {
        return mDatabase.query(Tables.SERVICES, SERVICE_PROJECTION, null, null, null, null, "name ASC");
    }

    public int getCCCD(BluetoothDevice bluetoothDevice, BluetoothGattDescriptor bluetoothGattDescriptor) {
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        this.m5Arg[0] = bluetoothDevice.getAddress();
        this.m5Arg[1] = String.valueOf(characteristic.getUuid().getMostSignificantBits());
        this.m5Arg[2] = String.valueOf(characteristic.getUuid().getLeastSignificantBits());
        this.m5Arg[3] = String.valueOf(characteristic.getService().getInstanceId());
        this.m5Arg[4] = String.valueOf(characteristic.getInstanceId());
        Cursor query = mDatabase.query(Tables.CCCD, VALUE_PROJECTION, CCCD_SELECTION, this.m5Arg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            int i = query.getInt(0);
            if (query != null) {
                query.close();
            }
            return i;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getCharacteristic(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        return mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
    }

    public String getCharacteristicName(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        Cursor query = mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(4);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public UUID getCharacteristicUuid(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        Cursor query = mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            UUID uuid = new UUID(query.getLong(1), query.getLong(2));
            if (query != null) {
                query.close();
            }
            return uuid;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getCharacteristics(String str) {
        this.mSingleArg[0] = "%" + str + "%";
        return mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, "name LIKE ?", this.mSingleArg, null, null, "name ASC");
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }

    public Cursor getDescriptor(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        return mDatabase.query(Tables.DESCRIPTORS, UUID_PROJECTION, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
    }

    public String getDescriptorName(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        Cursor query = mDatabase.query(Tables.DESCRIPTORS, UUID_PROJECTION, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(4);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public UUID getDescriptorUuid(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        Cursor query = mDatabase.query(Tables.DESCRIPTORS, UUID_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            UUID uuid = new UUID(query.getLong(1), query.getLong(2));
            if (query != null) {
                query.close();
            }
            return uuid;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getDescriptors(String str) {
        this.mSingleArg[0] = "%" + str + "%";
        return mDatabase.query(Tables.DESCRIPTORS, UUID_PROJECTION, "name LIKE ? AND number <> 10496 AND number <> 10498", this.mSingleArg, null, null, "name ASC");
    }

    public int getDeviceColor(Device device) {
        this.mSingleArg[0] = device.getAddress(null);
        Cursor query = mDatabase.query(Tables.NAMES_AND_COLORS, NAME_AND_COLOR_PROJECTION, DEVICE_ADDRESS_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            int i = query.getInt(1);
            if (query != null) {
                query.close();
            }
            return i;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public String getDeviceName(Device device) {
        this.mSingleArg[0] = device.getAddress(null);
        Cursor query = mDatabase.query(Tables.NAMES_AND_COLORS, NAME_AND_COLOR_PROJECTION, DEVICE_ADDRESS_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(0);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getFavoritesDevices() {
        return mDatabase.query(Tables.FAVORITE_DEVICES, DEVICE_PROJECTION, null, null, null, null, "last_seen ASC");
    }

    public Cursor getMacro(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        return mDatabase.query(Tables.MACROS, MACRO_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
    }

    public Cursor getMacroGroups(long j, long j2) {
        if (j > 0) {
            this.mDoubleArg[0] = String.valueOf(j);
            this.mDoubleArg[1] = String.valueOf(j2);
            return mDatabase.query(Tables.MACROS, NAME_PROJECTION, OTHER_GROUPS_FROM_GROUP_SELECTION, this.mDoubleArg, null, null, "name ASC");
        }
        this.mSingleArg[0] = String.valueOf(j2);
        return mDatabase.query(Tables.MACROS, NAME_PROJECTION, OTHER_GROUPS_FROM_MAIN_GROUP_SELECTION, this.mSingleArg, null, null, "name ASC");
    }

    public String getMacroName(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        Cursor query = mDatabase.query(Tables.MACROS, NAME_PROJECTION, ID_AND_NOT_DELETED_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(1);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Pair<String, String> getMacroOrGroupXml(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        Cursor query = mDatabase.query(Tables.MACROS, NAME_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query == null) {
                    return null;
                }
                query.close();
                return null;
            }
            StringBuilder sb = new StringBuilder();
            String string = query.getString(1);
            getMacroOrGroupXml(j, sb, 0);
            Pair<String, String> pair = new Pair<>(string, sb.toString());
            if (query != null) {
                query.close();
            }
            return pair;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public String getMacroXml(long j) {
        Cursor query = mDatabase.query(Tables.MACROS, XML_PROJECTION, ID_SELECTION, new String[]{String.valueOf(j)}, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(1);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getMacrosAndGroups(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        return mDatabase.query(Tables.MACROS, ID_PROJECTION, GROUP_SELECTION, this.mSingleArg, null, null, "_id DESC");
    }

    public Cursor getMacrosNames(long j) {
        if (j == 0) {
            return mDatabase.query("macros AS mac", MACRO_NAME_PROJECTION, DEFAULT_GROUP_SELECTION, null, null, null, "_id DESC");
        }
        this.mSingleArg[0] = String.valueOf(j);
        return mDatabase.query("macros AS mac", MACRO_NAME_PROJECTION, GROUP_SELECTION, this.mSingleArg, null, null, "_id DESC");
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
    
        r14 = r2.getString(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0073, code lost:
    
        if (r2 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0075, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0078, code lost:
    
        return r14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getNameForEddystoneKey(byte[] r14, int r15) {
        /*
            r13 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            android.database.sqlite.SQLiteDatabase r2 = no.nordicsemi.android.mcp.database.provider.DatabaseHelper.mDatabase
            java.lang.String[] r4 = no.nordicsemi.android.mcp.database.provider.DatabaseHelper.EDDYSTONE_KEY_PROJECTION
            java.lang.String r3 = "eddystone_keys"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)
        L16:
            boolean r3 = r2.moveToNext()     // Catch: java.lang.Throwable -> L7f
            r4 = 0
            if (r3 == 0) goto L79
            r3 = 2
            byte[] r5 = r2.getBlob(r3)     // Catch: java.lang.Throwable -> L7f
            r6 = 3
            long r6 = r2.getLong(r6)     // Catch: java.lang.Throwable -> L7f
            r8 = 4
            int r8 = r2.getInt(r8)     // Catch: java.lang.Throwable -> L7f
            long r6 = r0 - r6
            int r7 = (int) r6     // Catch: java.lang.Throwable -> L7f
            byte[] r6 = no.nordicsemi.android.mcp.database.provider.EddystoneUtils.getEidr(r5, r7, r8)     // Catch: java.lang.Throwable -> L7f
            r9 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r11 = (double) r8     // Catch: java.lang.Throwable -> L7f
            double r9 = java.lang.Math.pow(r9, r11)     // Catch: java.lang.Throwable -> L7f
            int r9 = (int) r9     // Catch: java.lang.Throwable -> L7f
            int r10 = r7 % r9
            if (r10 >= r3) goto L45
            int r3 = r7 - r9
            byte[] r4 = no.nordicsemi.android.mcp.database.provider.EddystoneUtils.getEidr(r5, r3, r8)     // Catch: java.lang.Throwable -> L7f
        L45:
            int r3 = r7 % r9
            int r10 = r9 + (-2)
            if (r3 <= r10) goto L50
            int r7 = r7 + r9
            byte[] r4 = no.nordicsemi.android.mcp.database.provider.EddystoneUtils.getEidr(r5, r7, r8)     // Catch: java.lang.Throwable -> L7f
        L50:
            if (r6 == 0) goto L16
            r3 = 0
            r5 = 0
        L54:
            int r7 = r6.length     // Catch: java.lang.Throwable -> L7f
            r8 = 1
            if (r5 >= r7) goto L6c
            r7 = r6[r5]     // Catch: java.lang.Throwable -> L7f
            int r9 = r15 + r5
            r10 = r14[r9]     // Catch: java.lang.Throwable -> L7f
            if (r7 == r10) goto L69
            if (r4 == 0) goto L6d
            r7 = r4[r5]     // Catch: java.lang.Throwable -> L7f
            r9 = r14[r9]     // Catch: java.lang.Throwable -> L7f
            if (r7 == r9) goto L69
            goto L6d
        L69:
            int r5 = r5 + 1
            goto L54
        L6c:
            r3 = 1
        L6d:
            if (r3 == 0) goto L16
            java.lang.String r14 = r2.getString(r8)     // Catch: java.lang.Throwable -> L7f
            if (r2 == 0) goto L78
            r2.close()
        L78:
            return r14
        L79:
            if (r2 == 0) goto L7e
            r2.close()
        L7e:
            return r4
        L7f:
            r14 = move-exception
            throw r14     // Catch: java.lang.Throwable -> L81
        L81:
            r14 = move-exception
            if (r2 == 0) goto L87
            r2.close()     // Catch: java.lang.Throwable -> L87
        L87:
            goto L89
        L88:
            throw r14
        L89:
            goto L88
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.mcp.database.provider.DatabaseHelper.getNameForEddystoneKey(byte[], int):java.lang.String");
    }

    public Cursor getSavedPackets() {
        return mDatabase.query(Tables.SAVED_PACKETS, SAVED_PACKET_PROJECTION, null, null, null, null, "recent DESC, created DESC");
    }

    public String getServerConfiguration(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        Cursor query = mDatabase.query(Tables.SERVER_CONFIGURATIONS, XML_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(1);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getServerConfigurationsNames() {
        return mDatabase.query(Tables.SERVER_CONFIGURATIONS, NAME_PROJECTION, NOT_DELETED_SELECTION, null, null, null, "name ASC");
    }

    public Cursor getService(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        return mDatabase.query(Tables.SERVICES, SERVICE_PROJECTION, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
    }

    public String getServiceName(UUID uuid) {
        Cursor service = getService(uuid);
        try {
            if (!service.moveToNext()) {
                if (service != null) {
                    service.close();
                }
                return null;
            }
            String string = service.getString(4);
            if (service != null) {
                service.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (service != null) {
                    try {
                        service.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public UUID getServiceUuid(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        Cursor query = mDatabase.query(Tables.SERVICES, SERVICE_PROJECTION, ID_SELECTION, this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            UUID uuid = new UUID(query.getLong(1), query.getLong(2));
            if (query != null) {
                query.close();
            }
            return uuid;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public Cursor getServices(String str) {
        this.mSingleArg[0] = "%" + str + "%";
        return mDatabase.query(Tables.SERVICES, SERVICE_PROJECTION, "name LIKE ?", this.mSingleArg, null, null, "name ASC");
    }

    public int getSupportedCharacteristicsCount() {
        Cursor query = mDatabase.query(Tables.CHARACTERISTICS, null, null, null, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            int i = query.getInt(0);
            if (query != null) {
                query.close();
            }
            return i;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public int getSupportedServicesCount() {
        Cursor query = mDatabase.query(Tables.SERVICES, null, null, null, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            int i = query.getInt(0);
            if (query != null) {
                query.close();
            }
            return i;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public boolean isCharacteristicDefined(int i) {
        this.mSingleArg[0] = String.valueOf(i);
        Cursor query = mDatabase.query(Tables.CHARACTERISTICS, null, "number=?", this.mSingleArg, null, null, null);
        try {
            boolean moveToNext = query.moveToNext();
            if (query != null) {
                query.close();
            }
            return moveToNext;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public boolean isDeviceFavorite(Device device) {
        this.mSingleArg[0] = device.getAddress(null);
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(FavoriteDeviceContract.FavoriteDeviceColumns.LAST_SEEN, Long.valueOf(Calendar.getInstance().getTimeInMillis()));
        return mDatabase.update(Tables.FAVORITE_DEVICES, contentValues, DEVICE_ADDRESS_SELECTION, this.mSingleArg) > 0;
    }

    public boolean isServiceDefined(int i) {
        this.mSingleArg[0] = String.valueOf(i);
        Cursor query = mDatabase.query(Tables.SERVICES, null, "number=?", this.mSingleArg, null, null, null);
        try {
            boolean moveToNext = query.moveToNext();
            if (query != null) {
                query.close();
            }
            return moveToNext;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public boolean macroExists(String str) {
        String[] strArr = this.mSingleArg;
        strArr[0] = str;
        Cursor query = mDatabase.query(Tables.MACROS, NAME_PROJECTION, "name=? AND deleted=0", strArr, null, null, null);
        try {
            boolean z = query.getCount() > 0;
            if (query != null) {
                query.close();
            }
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public int moveMacro(long j, long j2) {
        this.mSingleArg[0] = String.valueOf(j);
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        if (j2 > 0) {
            contentValues.put(MacroContract.MacroColumns.GROUP_ID, Long.valueOf(j2));
        } else {
            contentValues.putNull(MacroContract.MacroColumns.GROUP_ID);
        }
        return mDatabase.update(Tables.MACROS, contentValues, ID_SELECTION, this.mSingleArg);
    }

    public long registerEddystoneKey(String str, byte[] bArr, long j, int i) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str);
        contentValues.put(EddystoneKeyContract.KeyColumns.IDENTITY_KEY, bArr);
        contentValues.put(EddystoneKeyContract.KeyColumns.ZERO_TIME, Long.valueOf(currentTimeMillis - j));
        contentValues.put(EddystoneKeyContract.KeyColumns.EXPONENT, Integer.valueOf(i));
        return mDatabase.replace(Tables.EDDYSTONE_KEYS, null, contentValues);
    }

    public int removeCharacteristic(long j) {
        this.mDoubleArg[0] = String.valueOf(DatabaseUtils.getLsbForShortUuid(j));
        this.mDoubleArg[1] = String.valueOf(DatabaseUtils.getMsbForShortUuid(j));
        return mDatabase.delete(Tables.CHARACTERISTICS, "lsb=? AND msb=?", this.mDoubleArg);
    }

    public int removeDeletedAdvertisingPackets() {
        return mDatabase.delete(Tables.ADVERTISING_PACKETS, DELETED_SELECTION, null);
    }

    public int removeDeletedMacros() {
        return mDatabase.delete(Tables.MACROS, DELETED_SELECTION, null);
    }

    public int removeDeletedSavedPackets() {
        return mDatabase.delete(Tables.SAVED_PACKETS, DELETED_SELECTION, null);
    }

    public int removeDeletedServerConfigurations() {
        return mDatabase.delete(Tables.SERVER_CONFIGURATIONS, DELETED_SELECTION, null);
    }

    public void removeFavoriteDevice(String str) {
        String[] strArr = this.mSingleArg;
        strArr[0] = str;
        mDatabase.delete(Tables.FAVORITE_DEVICES, DEVICE_ADDRESS_SELECTION, strArr);
    }

    public int removeService(long j) {
        this.mDoubleArg[0] = String.valueOf(DatabaseUtils.getLsbForShortUuid(j));
        this.mDoubleArg[1] = String.valueOf(DatabaseUtils.getMsbForShortUuid(j));
        return mDatabase.delete(Tables.SERVICES, "lsb=? AND msb=?", this.mDoubleArg);
    }

    public int renameMacro(String str, String str2, String str3) {
        this.mSingleArg[0] = str;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str2);
        contentValues.put(ConfigurationContract.XmlColumns.XML, str3);
        return mDatabase.update(Tables.MACROS, contentValues, NAME_SELECTION, this.mSingleArg);
    }

    public int renameServerConfiguration(String str, String str2, String str3) {
        this.mSingleArg[0] = str;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str2);
        contentValues.put(ConfigurationContract.XmlColumns.XML, str3);
        return mDatabase.update(Tables.SERVER_CONFIGURATIONS, contentValues, NAME_SELECTION, this.mSingleArg);
    }

    public int restoreDeletedAdvertisingPackets() {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        return mDatabase.update(Tables.ADVERTISING_PACKETS, contentValues, null, null);
    }

    public int restoreDeletedMacros() {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        return mDatabase.update(Tables.MACROS, contentValues, DELETED_SELECTION, null);
    }

    public int restoreDeletedServerConfigurations() {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        return mDatabase.update(Tables.SERVER_CONFIGURATIONS, contentValues, null, null);
    }

    public void saveCCCD(BluetoothDevice bluetoothDevice, BluetoothGattDescriptor bluetoothGattDescriptor) {
        BluetoothGattCharacteristic characteristic = bluetoothGattDescriptor.getCharacteristic();
        this.m5Arg[0] = bluetoothDevice.getAddress();
        this.m5Arg[1] = String.valueOf(characteristic.getUuid().getMostSignificantBits());
        this.m5Arg[2] = String.valueOf(characteristic.getUuid().getLeastSignificantBits());
        this.m5Arg[3] = String.valueOf(characteristic.getService().getInstanceId());
        this.m5Arg[4] = String.valueOf(characteristic.getInstanceId());
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(CCCDContract.CCCDColumns.VALUE, Integer.valueOf(ClientCharacteristicConfigurationParser.getValue(bluetoothGattDescriptor)));
        if (mDatabase.update(Tables.CCCD, contentValues, CCCD_SELECTION, this.m5Arg) == 0) {
            contentValues.put("address", bluetoothDevice.getAddress());
            contentValues.put("msb", Long.valueOf(characteristic.getUuid().getMostSignificantBits()));
            contentValues.put("lsb", Long.valueOf(characteristic.getUuid().getLeastSignificantBits()));
            contentValues.put(CCCDContract.CCCDColumns.SERVICE_INSTANCE, Integer.valueOf(characteristic.getService().getInstanceId()));
            contentValues.put(CCCDContract.CCCDColumns.INSTANCE, Integer.valueOf(characteristic.getInstanceId()));
            mDatabase.insert(Tables.CCCD, null, contentValues);
        }
    }

    public long savePacket(String str, byte[] bArr, boolean z) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str);
        contentValues.put(SavedPacketContract.SavedPacketColumns.CREATED, Long.valueOf(System.currentTimeMillis()));
        contentValues.put(SavedPacketContract.SavedPacketColumns.RECENT, z ? "1" : "0");
        contentValues.put("data", bArr);
        return mDatabase.replace(Tables.SAVED_PACKETS, null, this.mValues);
    }

    public boolean serverConfigurationExists(String str) {
        String[] strArr = this.mSingleArg;
        strArr[0] = str;
        Cursor query = mDatabase.query(Tables.SERVER_CONFIGURATIONS, NAME_PROJECTION, "name=? AND deleted=0", strArr, null, null, null);
        try {
            boolean z = query.getCount() > 0;
            if (query != null) {
                query.close();
            }
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public long setDeviceColor(String str, String str2) {
        this.mSingleArg[0] = str;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("address", str);
        contentValues.put(DeviceContract.DeviceColumns.COLOR, str2);
        long insertWithOnConflict = mDatabase.insertWithOnConflict(Tables.NAMES_AND_COLORS, null, contentValues, 4);
        return insertWithOnConflict == -1 ? mDatabase.update(Tables.NAMES_AND_COLORS, contentValues, DEVICE_ADDRESS_SELECTION, this.mSingleArg) : insertWithOnConflict;
    }

    public void setDeviceFavorite(Device device) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("address", device.getAddress(null));
        contentValues.put("name", device.getName());
        contentValues.put(FavoriteDeviceContract.FavoriteDeviceColumns.APPEARANCE, Integer.valueOf(device.getAppearance()));
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        contentValues.put(FavoriteDeviceContract.FavoriteDeviceColumns.ADDED, Long.valueOf(timeInMillis));
        contentValues.put(FavoriteDeviceContract.FavoriteDeviceColumns.LAST_SEEN, Long.valueOf(timeInMillis));
        mDatabase.replace(Tables.FAVORITE_DEVICES, null, contentValues);
    }

    public long setDeviceName(String str, String str2) {
        this.mSingleArg[0] = str;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("address", str);
        if (TextUtils.isEmpty(str2.trim())) {
            str2 = null;
        }
        contentValues.put("name", str2);
        long insertWithOnConflict = mDatabase.insertWithOnConflict(Tables.NAMES_AND_COLORS, null, contentValues, 4);
        if (insertWithOnConflict == -1) {
            insertWithOnConflict = mDatabase.update(Tables.NAMES_AND_COLORS, contentValues, DEVICE_ADDRESS_SELECTION, this.mSingleArg);
        }
        mDatabase.update(Tables.FAVORITE_DEVICES, contentValues, DEVICE_ADDRESS_SELECTION, this.mSingleArg);
        return insertWithOnConflict;
    }

    public int undoDeleteSavedPacket(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        return mDatabase.update(Tables.SAVED_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
    }

    public long updateAdvertisingPacket(long j, String str, boolean z, byte[] bArr, byte[] bArr2) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("name", str);
        contentValues.put(AdvertisingContract.OreoConfigColumns.CONNECTIBLE, Boolean.valueOf(z));
        contentValues.put(AdvertisingContract.OreoConfigColumns.SCANNABLE, Boolean.valueOf(z || (bArr2 != null && bArr2.length > 0)));
        contentValues.put("data", bArr);
        contentValues.put(AdvertisingContract.DataColumns.SCAN_RESP_DATA, bArr2);
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        contentValues.put(AdvertisingContract.OreoConfigColumns.INTERVAL, (Integer) 400);
        contentValues.put(AdvertisingContract.OreoConfigColumns.TX_POWER_LEVEL, (Integer) (-7));
        if (j <= 0) {
            return mDatabase.insert(Tables.ADVERTISING_PACKETS, null, contentValues);
        }
        this.mSingleArg[0] = String.valueOf(j);
        mDatabase.update(Tables.ADVERTISING_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
        return j;
    }

    public int updateServerConfiguration(String str, String str2) {
        this.mSingleArg[0] = str;
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(ConfigurationContract.XmlColumns.XML, str2);
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        return mDatabase.update(Tables.SERVER_CONFIGURATIONS, contentValues, NAME_SELECTION, this.mSingleArg);
    }

    public long addCharacteristic(long j, String str, String str2, Integer num) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(DatabaseUtils.getMsbForShortUuid(j)));
        contentValues.put("lsb", Long.valueOf(DatabaseUtils.getLsbForShortUuid(j)));
        contentValues.put("number", Long.valueOf(j));
        contentValues.put("name", str);
        contentValues.put(UuidColumns.SPECIFICATION_TYPE, str2);
        contentValues.put(FormatColumns.FORMAT, num);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 0);
        return mDatabase.replace(Tables.CHARACTERISTICS, null, contentValues);
    }

    public long addDescriptor(long j, String str, String str2, Integer num) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(DatabaseUtils.getMsbForShortUuid(j)));
        contentValues.put("lsb", Long.valueOf(DatabaseUtils.getLsbForShortUuid(j)));
        contentValues.put("number", Long.valueOf(j));
        contentValues.put("name", str);
        contentValues.put(UuidColumns.SPECIFICATION_TYPE, str2);
        contentValues.put(FormatColumns.FORMAT, num);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 0);
        return mDatabase.replace(Tables.DESCRIPTORS, null, contentValues);
    }

    public void removeFavoriteDevice(long j) {
        this.mSingleArg[0] = String.valueOf(j);
        mDatabase.delete(Tables.FAVORITE_DEVICES, ID_SELECTION, this.mSingleArg);
    }

    public Cursor getCharacteristic(int i) {
        this.mSingleArg[0] = String.valueOf(i);
        return mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, "number=?", this.mSingleArg, null, null, null);
    }

    public Cursor getDescriptor(int i) {
        this.mSingleArg[0] = String.valueOf(i);
        return mDatabase.query(Tables.DESCRIPTORS, UUID_PROJECTION, "number=?", this.mSingleArg, null, null, null);
    }

    public int removeCharacteristic(long j, long j2) {
        this.mDoubleArg[0] = String.valueOf(j2);
        this.mDoubleArg[1] = String.valueOf(j);
        return mDatabase.delete(Tables.CHARACTERISTICS, "lsb=? AND msb=?", this.mDoubleArg);
    }

    public int removeService(long j, long j2) {
        this.mDoubleArg[0] = String.valueOf(j2);
        this.mDoubleArg[1] = String.valueOf(j);
        return mDatabase.delete(Tables.SERVICES, "lsb=? AND msb=?", this.mDoubleArg);
    }

    public boolean isCharacteristicDefined(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        Cursor query = mDatabase.query(Tables.CHARACTERISTICS, null, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
        try {
            boolean moveToNext = query.moveToNext();
            if (query != null) {
                query.close();
            }
            return moveToNext;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public boolean isServiceDefined(UUID uuid) {
        this.mDoubleArg[0] = String.valueOf(uuid.getLeastSignificantBits());
        this.mDoubleArg[1] = String.valueOf(uuid.getMostSignificantBits());
        Cursor query = mDatabase.query(Tables.SERVICES, null, "lsb=? AND msb=?", this.mDoubleArg, null, null, null);
        try {
            boolean moveToNext = query.moveToNext();
            if (query != null) {
                query.close();
            }
            return moveToNext;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public String getCharacteristicName(int i) {
        this.mSingleArg[0] = String.valueOf(i);
        Cursor query = mDatabase.query(Tables.CHARACTERISTICS, UUID_PROJECTION, "number=?", this.mSingleArg, null, null, null);
        try {
            if (!query.moveToNext()) {
                if (query != null) {
                    query.close();
                }
                return null;
            }
            String string = query.getString(4);
            if (query != null) {
                query.close();
            }
            return string;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (query != null) {
                    try {
                        query.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public long addService(long j, long j2, String str, String str2) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(j));
        contentValues.put("lsb", Long.valueOf(j2));
        contentValues.put("name", str);
        contentValues.put(UuidColumns.SPECIFICATION_TYPE, str2);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 0);
        return mDatabase.replace(Tables.SERVICES, null, contentValues);
    }

    private void getMacroOrGroupXml(long j, StringBuilder sb, int i) {
        Cursor macro = getMacro(j);
        try {
            if (macro.moveToNext()) {
                String offset = offset(i);
                String string = macro.getString(2);
                if (string != null) {
                    sb.append(string.replaceAll("(?m)^", offset));
                    sb.append("\n");
                } else {
                    String string2 = macro.getString(1);
                    sb.append(offset);
                    sb.append("<group name=\"");
                    sb.append(string2);
                    sb.append("\">\n");
                    Cursor macrosAndGroups = getMacrosAndGroups(j);
                    while (macrosAndGroups.moveToNext()) {
                        try {
                            getMacroOrGroupXml(macrosAndGroups.getLong(0), sb, i + 1);
                        } finally {
                        }
                    }
                    if (macrosAndGroups != null) {
                        macrosAndGroups.close();
                    }
                    sb.append(offset);
                    sb.append("</group>\n");
                }
            }
            if (macro != null) {
                macro.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (macro != null) {
                    try {
                        macro.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th2;
            }
        }
    }

    public long addCharacteristic(long j, long j2, String str, String str2) {
        return addCharacteristic(j, j2, str, str2, null);
    }

    public long addCharacteristic(long j, long j2, String str, String str2, Integer num) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put("msb", Long.valueOf(j));
        contentValues.put("lsb", Long.valueOf(j2));
        contentValues.put("name", str);
        contentValues.put(UuidColumns.SPECIFICATION_TYPE, str2);
        contentValues.put(FormatColumns.FORMAT, num);
        contentValues.put(EditableColumns.EDITABLE, (Integer) 0);
        return mDatabase.replace(Tables.CHARACTERISTICS, null, contentValues);
    }

    public long updateAdvertisingPacket(long j, boolean z) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(AdvertisingContract.OreoConfigColumns.SCANNABLE, Boolean.valueOf(z));
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        this.mSingleArg[0] = String.valueOf(j);
        mDatabase.update(Tables.ADVERTISING_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
        return j;
    }

    public long updateAdvertisingPacket(long j, int i, int i2) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(AdvertisingContract.OreoConfigColumns.INTERVAL, Integer.valueOf(i));
        contentValues.put(AdvertisingContract.OreoConfigColumns.TX_POWER_LEVEL, Integer.valueOf(i2));
        contentValues.put(UndoColumns.DELETED, (Integer) 0);
        this.mSingleArg[0] = String.valueOf(j);
        mDatabase.update(Tables.ADVERTISING_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
        return j;
    }

    public long updateAdvertisingPacket(long j, boolean z, boolean z2, boolean z3, int i, int i2) {
        ContentValues contentValues = this.mValues;
        contentValues.clear();
        contentValues.put(AdvertisingContract.OreoConfigColumns.LEGACY_MODE, Boolean.valueOf(z));
        contentValues.put(AdvertisingContract.OreoConfigColumns.INCLUDE_TX_POWER, Boolean.valueOf(z2));
        contentValues.put(AdvertisingContract.OreoConfigColumns.ANONYMOUS, Boolean.valueOf(z3));
        contentValues.put(AdvertisingContract.OreoConfigColumns.PRIMARY_PHY, Integer.valueOf(i));
        contentValues.put(AdvertisingContract.OreoConfigColumns.SECONDARY_PHY, Integer.valueOf(i2));
        this.mSingleArg[0] = String.valueOf(j);
        mDatabase.update(Tables.ADVERTISING_PACKETS, contentValues, ID_SELECTION, this.mSingleArg);
        return j;
    }
}
