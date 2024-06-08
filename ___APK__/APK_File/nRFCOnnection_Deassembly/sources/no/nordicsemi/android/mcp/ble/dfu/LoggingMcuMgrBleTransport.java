package no.nordicsemi.android.mcp.ble.dfu;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import io.runtime.mcumgr.McuMgrCallback;
import io.runtime.mcumgr.ble.McuMgrBleCallbacksStub;
import io.runtime.mcumgr.ble.McuMgrBleTransport;
import io.runtime.mcumgr.exception.McuMgrErrorException;
import io.runtime.mcumgr.exception.McuMgrException;
import io.runtime.mcumgr.response.McuMgrResponse;
import java.lang.reflect.Field;
import no.nordicsemi.android.log.ILogSession;
import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.log.Logger;
import no.nordicsemi.android.mcp.ble.parser.gatt.SmpParser;

/* loaded from: classes.dex */
public class LoggingMcuMgrBleTransport extends McuMgrBleTransport {
    private ILogSession mLogSession;
    private final SmpParser mParser;
    private BluetoothGattCharacteristic mSmpCharacteristic;
    private boolean mUploading;

    public LoggingMcuMgrBleTransport(Context context, BluetoothDevice bluetoothDevice) {
        super(context, bluetoothDevice);
        this.mParser = new SmpParser();
        setGattCallbacks(new McuMgrBleCallbacksStub() { // from class: no.nordicsemi.android.mcp.ble.dfu.LoggingMcuMgrBleTransport.1
            @Override // io.runtime.mcumgr.ble.McuMgrBleCallbacksStub, no.nordicsemi.android.ble.BleManagerCallbacks
            public void onDeviceReady(BluetoothDevice bluetoothDevice2) {
                try {
                    Field declaredField = getClass().getEnclosingClass().getSuperclass().getDeclaredField("mSmpCharacteristic");
                    declaredField.setAccessible(true);
                    LoggingMcuMgrBleTransport.this.mSmpCharacteristic = (BluetoothGattCharacteristic) declaredField.get(LoggingMcuMgrBleTransport.this);
                } catch (Exception unused) {
                }
            }
        });
    }

    @Override // io.runtime.mcumgr.ble.McuMgrBleTransport, no.nordicsemi.android.ble.BleManager, no.nordicsemi.android.ble.utils.ILogger
    public void log(int i, String str) {
        if (this.mUploading) {
            return;
        }
        Logger.log(this.mLogSession, LogContract.Log.Level.fromPriority(i), "[McuMgr] " + str);
        if (this.mSmpCharacteristic == null || !str.startsWith("Data written to da2e7828-fbce-4e01-ae9e-261174997c48")) {
            return;
        }
        log(10, "\"" + this.mParser.parse(null, this.mSmpCharacteristic) + "\" sent");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.runtime.mcumgr.ble.McuMgrBleTransport, io.runtime.mcumgr.McuMgrTransport
    public <T extends McuMgrResponse> void send(byte[] bArr, Class<T> cls, final McuMgrCallback<T> mcuMgrCallback) {
        super.send(bArr, cls, new McuMgrCallback<T>() { // from class: no.nordicsemi.android.mcp.ble.dfu.LoggingMcuMgrBleTransport.2
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onError(McuMgrException mcuMgrException) {
                if (mcuMgrException instanceof McuMgrErrorException) {
                    LoggingMcuMgrBleTransport.this.log(5, "\"" + mcuMgrException.toString() + "\" received");
                }
                mcuMgrCallback.onError(mcuMgrException);
            }

            /* JADX WARN: Incorrect types in method signature: (TT;)V */
            @Override // io.runtime.mcumgr.McuMgrCallback
            public void onResponse(McuMgrResponse mcuMgrResponse) {
                LoggingMcuMgrBleTransport.this.log(10, "\"" + mcuMgrResponse.toString() + "\" received");
                mcuMgrCallback.onResponse(mcuMgrResponse);
            }
        });
    }

    public void setLogger(ILogSession iLogSession) {
        this.mLogSession = iLogSession;
    }

    public void setUploading(boolean z) {
        this.mUploading = z;
    }
}
