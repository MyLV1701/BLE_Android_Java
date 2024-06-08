package no.nordicsemi.android.mcp.ble.server.impl;

import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.g;
import androidx.core.app.j;
import no.nordicsemi.android.mcp.R;
import no.nordicsemi.android.mcp.ble.server.ServiceMap;
import no.nordicsemi.android.mcp.ble.server.ServiceServerController;
import no.nordicsemi.android.mcp.util.NotificationHelper;

/* loaded from: classes.dex */
public class LinkLossServiceImpl extends ServerCallbackAdapter {
    public static final String ACTION_SILENT_ME = "no.nordicsemi.android.mcp.broadcast.ACTION_SILENT_ME";
    private static final int HIGH_ALERT = 2;
    private static final int MILD_ALERT = 1;
    private static final int NOTIFICATION_ID = 172;
    private static final int NO_ALERT = 0;
    private static final int SILENT_ME_REQ = 1;
    private static final String TAG = "LinkLossServiceImpl";
    private Ringtone mRingtoneAlarm;
    private Ringtone mRingtoneNotification;
    private BroadcastReceiver mSilentActionListener;

    public LinkLossServiceImpl(Context context, Handler handler, ServiceServerController serviceServerController, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        super(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        this.mSilentActionListener = new BroadcastReceiver() { // from class: no.nordicsemi.android.mcp.ble.server.impl.LinkLossServiceImpl.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                LinkLossServiceImpl.this.mRingtoneAlarm.stop();
                LinkLossServiceImpl.this.mRingtoneNotification.stop();
                j.a(LinkLossServiceImpl.this.getContext()).a(LinkLossServiceImpl.NOTIFICATION_ID);
            }
        };
        this.mRingtoneAlarm = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(4));
        this.mRingtoneNotification = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(2));
        context.registerReceiver(this.mSilentActionListener, new IntentFilter(ACTION_SILENT_ME));
    }

    private void createNotification(BluetoothDevice bluetoothDevice) {
        Context context = getContext();
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 1, new Intent(ACTION_SILENT_ME), 134217728);
        g.d dVar = new g.d(context, NotificationHelper.CHANNEL_LINK_LOSS);
        String name = !TextUtils.isEmpty(bluetoothDevice.getName()) ? bluetoothDevice.getName() : context.getString(R.string.server_impl_linkloss_notification_alert_name);
        dVar.d(R.drawable.ic_stat_notify_nrf_connect);
        dVar.c(context.getString(R.string.app_name));
        dVar.b(context.getString(R.string.server_impl_linkloss_notification_alert, name));
        dVar.e(true);
        dVar.b(-1);
        dVar.a(true);
        dVar.c(1);
        dVar.a("alarm");
        dVar.a(new g.a(R.drawable.ic_stat_notify_action_alarm_off, context.getString(R.string.server_impl_linkloss_notification_alert_action), broadcast));
        j.a(context).a(NOTIFICATION_ID, dVar.a());
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onConnectionLost(BluetoothGattServer bluetoothGattServer, BluetoothDevice bluetoothDevice) {
        Integer intValue;
        BluetoothGattService deviceService = getDeviceService(bluetoothDevice);
        if (deviceService == null || (intValue = deviceService.getCharacteristics().get(0).getIntValue(17, 0)) == null) {
            return;
        }
        AudioManager audioManager = (AudioManager) getContext().getSystemService("audio");
        int intValue2 = intValue.intValue();
        if (intValue2 == 1) {
            audioManager.setStreamVolume(4, audioManager.getStreamMaxVolume(4), 8);
            this.mRingtoneNotification.play();
            createNotification(bluetoothDevice);
        } else {
            if (intValue2 != 2) {
                return;
            }
            audioManager.setStreamVolume(4, audioManager.getStreamMaxVolume(4), 8);
            this.mRingtoneAlarm.play();
            createNotification(bluetoothDevice);
        }
    }

    @Override // no.nordicsemi.android.mcp.ble.server.impl.ServerCallbackAdapter, no.nordicsemi.android.mcp.ble.server.IServerCallback
    public void onServerClosed() {
        try {
            this.mRingtoneAlarm.stop();
        } catch (Exception e2) {
            Log.w(TAG, "Stopping alarm ringtone failed", e2);
        }
        try {
            this.mRingtoneNotification.stop();
        } catch (Exception e3) {
            Log.w(TAG, "Stopping notification ringtone failed", e3);
        }
        j.a(getContext()).a(NOTIFICATION_ID);
        getContext().unregisterReceiver(this.mSilentActionListener);
    }
}
