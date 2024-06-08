package no.nordicsemi.android.mcp.definitions;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.mcp.database.CharacteristicContract;
import no.nordicsemi.android.mcp.database.DatabaseUtils;
import no.nordicsemi.android.mcp.database.DescriptorContract;
import no.nordicsemi.android.mcp.database.FormatColumns;
import no.nordicsemi.android.mcp.database.ServiceContract;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.util.FileHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class JSONDefinitionConverter {
    private static final String TAG = "JSONDefinitionConverter";

    private static void addAdoptedUuidToObject(JSONObject jSONObject, Cursor cursor) {
        int i = cursor.getInt(3);
        String string = cursor.getString(4);
        int columnIndex = cursor.getColumnIndex(FormatColumns.FORMAT);
        boolean z = columnIndex >= 0 && !cursor.isNull(columnIndex);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("name", string);
        if (z) {
            jSONObject2.put(FormatColumns.FORMAT, formatToString(cursor.getInt(columnIndex)));
        }
        jSONObject.put(uuidToKey(i), jSONObject2);
    }

    private static void addOrUpdate(Context context, JSONObject jSONObject, Uri uri) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                arrayList.add(addOrUpdate(next, jSONObject.getJSONObject(next)));
            } catch (NumberFormatException e2) {
                Log.w(TAG, "Invalid UUID", e2);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        context.getContentResolver().bulkInsert(uri, (ContentValues[]) arrayList.toArray(new ContentValues[0]));
    }

    private static void addUuidToObject(JSONObject jSONObject, Cursor cursor) {
        long j = cursor.getLong(1);
        long j2 = cursor.getLong(2);
        String string = cursor.getString(4);
        int columnIndex = cursor.getColumnIndex(FormatColumns.FORMAT);
        boolean z = columnIndex >= 0 && !cursor.isNull(columnIndex);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("name", string);
        if (z) {
            jSONObject2.put(FormatColumns.FORMAT, formatToString(cursor.getInt(columnIndex)));
        }
        jSONObject.put(uuidToKey(j, j2), jSONObject2);
    }

    @TargetApi(19)
    private static JSONArray comments() {
        return new JSONArray(new String[]{"Add UUIDs for your services, characteristics, and descriptors in this file.", "The format is the UUID of the item, followed by an object containing the data.", "'name' is a mandatory handle, while 'format' is optional.", "Currently the only supported format is 'TEXT', default is 'NO_FORMAT'.", "The application must be reloaded to access changes in this file."});
    }

    private static Integer formatToId(String str) {
        return "TEXT".equals(str) ? 1 : null;
    }

    private static String formatToString(int i) {
        return "TEXT";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean fromJSON(Context context, InputStream inputStream) {
        BufferedReader bufferedReader;
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    JSONObject jSONObject = new JSONObject(sb.toString());
                    addOrUpdate(context, jSONObject.getJSONObject("uuid16bitServiceDefinitions"), ServiceContract.Service.CONTENT_URI);
                    addOrUpdate(context, jSONObject.getJSONObject("uuid16bitCharacteristicDefinitions"), CharacteristicContract.Characteristic.CONTENT_URI);
                    addOrUpdate(context, jSONObject.getJSONObject("uuid16bitDescriptorDefinitions"), DescriptorContract.Descriptor.CONTENT_URI);
                    addOrUpdate(context, jSONObject.getJSONObject("uuid128bitServiceDefinitions"), ServiceContract.Service.CONTENT_URI);
                    addOrUpdate(context, jSONObject.getJSONObject("uuid128bitCharacteristicDefinitions"), CharacteristicContract.Characteristic.CONTENT_URI);
                    addOrUpdate(context, jSONObject.getJSONObject("uuid128bitDescriptorDefinitions"), DescriptorContract.Descriptor.CONTENT_URI);
                    return true;
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "Importing definitions failed", e2);
            return false;
        }
    }

    public static void onUserDefinitionsChanged(Context context, DatabaseHelper databaseHelper) {
        String json = toJSON(databaseHelper, true);
        if (json != null) {
            save(context, json);
        }
    }

    private static void save(Context context, String str) {
        try {
            File definitionsFile = FileHelper.getDefinitionsFile(context, true);
            definitionsFile.createNewFile();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(definitionsFile));
            outputStreamWriter.append((CharSequence) str);
            outputStreamWriter.close();
        } catch (Exception e2) {
            Log.e("DefinitionsActivity", "Error while saving definitions", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toJSON(DatabaseHelper databaseHelper, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                jSONObject.put("_comment", comments());
            }
            Cursor allServices = databaseHelper.getAllServices();
            try {
                JSONObject jSONObject2 = new JSONObject();
                while (allServices.moveToNext()) {
                    if (!allServices.isNull(3) && (!z || allServices.getInt(6) == 1)) {
                        addAdoptedUuidToObject(jSONObject2, allServices);
                    }
                }
                jSONObject.put("uuid16bitServiceDefinitions", jSONObject2);
                JSONObject jSONObject3 = new JSONObject();
                allServices.moveToFirst();
                do {
                    if (allServices.isNull(3) && (!z || allServices.getInt(6) == 1)) {
                        addUuidToObject(jSONObject3, allServices);
                    }
                } while (allServices.moveToNext());
                jSONObject.put("uuid128bitServiceDefinitions", jSONObject3);
                if (allServices != null) {
                    allServices.close();
                }
                Cursor allCharacteristics = databaseHelper.getAllCharacteristics();
                try {
                    JSONObject jSONObject4 = new JSONObject();
                    while (allCharacteristics.moveToNext()) {
                        if (!allCharacteristics.isNull(3) && (!z || allCharacteristics.getInt(7) == 1)) {
                            addAdoptedUuidToObject(jSONObject4, allCharacteristics);
                        }
                    }
                    jSONObject.put("uuid16bitCharacteristicDefinitions", jSONObject4);
                    JSONObject jSONObject5 = new JSONObject();
                    allCharacteristics.moveToFirst();
                    do {
                        if (allCharacteristics.isNull(3) && (!z || allCharacteristics.getInt(7) == 1)) {
                            addUuidToObject(jSONObject5, allCharacteristics);
                        }
                    } while (allCharacteristics.moveToNext());
                    jSONObject.put("uuid128bitCharacteristicDefinitions", jSONObject5);
                    if (allCharacteristics != null) {
                        allCharacteristics.close();
                    }
                    Cursor allDescriptors = databaseHelper.getAllDescriptors();
                    try {
                        JSONObject jSONObject6 = new JSONObject();
                        while (allDescriptors.moveToNext()) {
                            if (!allDescriptors.isNull(3) && (!z || allDescriptors.getInt(7) == 1)) {
                                addAdoptedUuidToObject(jSONObject6, allDescriptors);
                            }
                        }
                        jSONObject.put("uuid16bitDescriptorDefinitions", jSONObject6);
                        JSONObject jSONObject7 = new JSONObject();
                        allDescriptors.moveToFirst();
                        do {
                            if (allDescriptors.isNull(3) && (!z || allDescriptors.getInt(7) == 1)) {
                                addUuidToObject(jSONObject7, allDescriptors);
                            }
                        } while (allDescriptors.moveToNext());
                        jSONObject.put("uuid128bitDescriptorDefinitions", jSONObject7);
                        if (allDescriptors != null) {
                            allDescriptors.close();
                        }
                        return jSONObject.toString(3);
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } catch (JSONException e2) {
            Log.e("AA", "onOptionsItemSelected: ", e2);
            return null;
        }
    }

    private static String uuidToKey(int i) {
        return String.format(Locale.US, "%04X", Integer.valueOf(i));
    }

    private static String uuidToKey(long j, long j2) {
        return new UUID(j, j2).toString().replaceAll("-", "");
    }

    private static ContentValues addOrUpdate(String str, JSONObject jSONObject) {
        String string = jSONObject.getString("name");
        String optString = jSONObject.optString(FormatColumns.FORMAT);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", string);
        if (!optString.isEmpty()) {
            contentValues.put(FormatColumns.FORMAT, formatToId(optString));
        }
        if (str.length() == 4) {
            int parseInt = Integer.parseInt(str, 16);
            long j = parseInt;
            long msbForShortUuid = DatabaseUtils.getMsbForShortUuid(j);
            long lsbForShortUuid = DatabaseUtils.getLsbForShortUuid(j);
            contentValues.put("msb", Long.valueOf(msbForShortUuid));
            contentValues.put("lsb", Long.valueOf(lsbForShortUuid));
            contentValues.put("number", Integer.valueOf(parseInt));
        } else if (str.length() == 32) {
            String str2 = "0x" + str.substring(0, 8);
            String str3 = "0x" + str.substring(8, 16);
            String str4 = "0x" + str.substring(16, 24);
            String str5 = "0x" + str.substring(24, 32);
            long longValue = Long.decode(str3).longValue() | (Long.decode(str2).longValue() << 32);
            long longValue2 = Long.decode(str5).longValue() | (Long.decode(str4).longValue() << 32);
            contentValues.put("msb", Long.valueOf(longValue));
            contentValues.put("lsb", Long.valueOf(longValue2));
        } else {
            throw new NumberFormatException("Importing definition failed: Only 16-bit or 128-bit UUIDs supported");
        }
        return contentValues;
    }
}
