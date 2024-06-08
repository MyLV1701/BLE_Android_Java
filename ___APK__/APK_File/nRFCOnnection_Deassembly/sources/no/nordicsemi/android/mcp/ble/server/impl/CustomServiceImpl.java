package no.nordicsemi.android.mcp.ble.server.impl;

import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import no.nordicsemi.android.mcp.ble.server.ServiceMap;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;

/* loaded from: classes.dex */
public class CustomServiceImpl extends ServerCallbackAdapter {
    public CustomServiceImpl(Context context, Handler handler, ServiceServerController serviceServerController, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        super(context, handler, serviceServerController, serviceMap, bluetoothGattService);
    }
}
