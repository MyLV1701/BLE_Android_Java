package no.nordicsemi.android.mcp.ble.parser.utils;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import no.nordicsemi.android.mcp.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CompanyIdentifier2 {
    private static final String TAG = "CompanyIdentifier2";
    private static final String TAG_COMPANIES = "companies";
    private static final String TAG_COMPANY_NAME = "CompanyName";
    private static final String TAG_DECIMAL_VALUE = "DecimalValue";
    private static SparseArray<String> sIdentifiers = new SparseArray<>();

    private static byte[] getResource(Context context, int i) {
        InputStream openRawResource = context.getResources().openRawResource(i);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = openRawResource.read(bArr, 0, bArr.length);
                if (read == -1) {
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                openRawResource.close();
            }
        }
    }

    private static String getStringResource(Context context, int i) {
        return new String(getResource(context, i), Charset.forName("UTF-8"));
    }

    public static void init(Context context) {
        try {
            JSONArray jSONArray = new JSONObject(getStringResource(context, R.raw.company_id)).getJSONArray(TAG_COMPANIES);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt(TAG_DECIMAL_VALUE);
                String string = jSONObject.getString(TAG_COMPANY_NAME);
                if (i2 != 65535) {
                    sIdentifiers.put(i2, string);
                } else {
                    sIdentifiers.put(i2, "No Company ID");
                }
            }
        } catch (IOException e2) {
            Log.e(TAG, "Init failed", e2);
        } catch (JSONException e3) {
            Log.e(TAG, "Parsing Company Identifiers failed", e3);
        }
    }

    public static String withId(int i) {
        String str = sIdentifiers.get(i);
        return str != null ? String.format(Locale.US, "%s <0x%04X>", str, Integer.valueOf(i)) : String.format(Locale.US, "Reserved ID <0x%04X>", Integer.valueOf(i));
    }
}
