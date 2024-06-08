package no.nordicsemi.android.mcp.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class BondHelper {
    private static final String TAG = "BondHelper";

    @SuppressLint({"NewApi"})
    public static boolean createBond(BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 19) {
            return bluetoothDevice.createBond();
        }
        return createBondApi18(bluetoothDevice);
    }

    public static boolean createBondApi18(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
            }
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while creating bond", e2);
        }
        return false;
    }

    public static boolean removeBond(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
            }
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while removing bond information", e2);
        }
        return false;
    }
}
