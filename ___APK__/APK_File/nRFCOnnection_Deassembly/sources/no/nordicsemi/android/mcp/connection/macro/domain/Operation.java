package no.nordicsemi.android.mcp.connection.macro.domain;

import android.util.Log;
import no.nordicsemi.android.mcp.ble.IBluetoothLeConnection;
import no.nordicsemi.android.mcp.database.provider.DatabaseHelper;
import no.nordicsemi.android.mcp.domain.common.HasDescription;

/* loaded from: classes.dex */
public abstract class Operation extends HasDescription {
    public static final int ERROR = -1;
    public static final int SUCCESS = 0;
    private static final String TAG = "Operation";
    public static final int WARNING = 1;

    protected abstract void execute(IBluetoothLeConnection iBluetoothLeConnection);

    /* JADX INFO: Access modifiers changed from: protected */
    public void invalidate() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Operation[] mirror(DatabaseHelper databaseHelper) {
        return null;
    }

    public int run(IBluetoothLeConnection iBluetoothLeConnection) {
        try {
            execute(iBluetoothLeConnection);
            if (iBluetoothLeConnection.waitUntilOperationCompleted() == 0) {
                return !validateResult(iBluetoothLeConnection) ? 1 : 0;
            }
            return -1;
        } catch (Exception e2) {
            Log.e(TAG, "Exception while running operation", e2);
            return -1;
        }
    }

    protected boolean validateResult(IBluetoothLeConnection iBluetoothLeConnection) {
        return true;
    }
}
