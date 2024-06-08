package no.nordicsemi.android.mcp.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.g;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import no.nordicsemi.android.mcp.DeviceListActivity;
import no.nordicsemi.android.mcp.R;

/* loaded from: classes.dex */
public class NotificationHelper {
    private static final String ACTION_CONNECT = "no.nordicsemi.android.mcp.action.ACTION_CONNECT";
    public static final String CHANNEL_FILES = "channel_files";
    public static final String CHANNEL_FOREGROUND_SERVICE = "channel_foreground_service";
    public static final String CHANNEL_INCOMING_CONNECTION = "channel_incoming_connection";
    public static final String CHANNEL_LINK_LOSS = "channel_link_loss";
    public static final String CHANNEL_TESTS = "channel_tests";
    private static final String EXTRA_DEVICE = "no.nordicsemi.android.mcp.extra.EXTRA_DEVICE";
    private static final String EXTRA_SHOULD_CONNECT = "no.nordicsemi.android.mcp.extra.EXTRA_SHOULD_CONNECT";
    private static final String EXTRA_SHOULD_OPEN_APP = "no.nordicsemi.android.mcp.extra.EXTRA_SHOULD_OPEN_APP";
    private static final int NOTIFICATION_ID = 300;
    private static NewConnectionBroadcastReceiver mNewConnectionBroadcastReceiver;

    /* loaded from: classes.dex */
    public static class NewConnectionBroadcastReceiver extends BroadcastReceiver {
        private final NewConnectionListener mListener;

        public NewConnectionBroadcastReceiver(NewConnectionListener newConnectionListener) {
            this.mListener = newConnectionListener;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean booleanExtra = intent.getBooleanExtra(NotificationHelper.EXTRA_SHOULD_CONNECT, false);
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra(NotificationHelper.EXTRA_DEVICE);
            NotificationHelper.cancelNotification(context, bluetoothDevice);
            if (booleanExtra) {
                this.mListener.onNewConnection(bluetoothDevice);
            }
            if (intent.getBooleanExtra(NotificationHelper.EXTRA_SHOULD_OPEN_APP, false)) {
                Intent intent2 = new Intent(context, (Class<?>) DeviceListActivity.class);
                intent2.addFlags(268435456);
                context.startActivity(intent2);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface NewConnectionListener {
        void onNewConnection(BluetoothDevice bluetoothDevice);
    }

    public static void cancelNotification(Context context, BluetoothDevice bluetoothDevice) {
        ((NotificationManager) context.getSystemService("notification")).cancel(bluetoothDevice.getAddress(), NOTIFICATION_ID);
    }

    public static void initChannels(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        DfuServiceInitiator.createDfuNotificationChannel(context);
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_INCOMING_CONNECTION, context.getString(R.string.channel_incoming_connection), 4);
        notificationChannel.setDescription(context.getString(R.string.channel_incoming_connection_description));
        notificationChannel.setShowBadge(true);
        notificationChannel.setLockscreenVisibility(1);
        notificationManager.createNotificationChannel(notificationChannel);
        NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_FOREGROUND_SERVICE, context.getString(R.string.channel_foreground_service), 1);
        notificationChannel2.setDescription(context.getString(R.string.channel_foreground_service_description));
        notificationChannel2.setShowBadge(false);
        notificationChannel2.setLockscreenVisibility(1);
        notificationManager.createNotificationChannel(notificationChannel2);
        NotificationChannel notificationChannel3 = new NotificationChannel(CHANNEL_FILES, context.getString(R.string.channel_files), 2);
        notificationChannel3.setDescription(context.getString(R.string.channel_files_description));
        notificationChannel3.setShowBadge(true);
        notificationChannel3.setLockscreenVisibility(0);
        notificationManager.createNotificationChannel(notificationChannel3);
        NotificationChannel notificationChannel4 = new NotificationChannel(CHANNEL_TESTS, context.getString(R.string.channel_tests), 2);
        notificationChannel4.setDescription(context.getString(R.string.channel_tests_description));
        notificationChannel4.setShowBadge(true);
        notificationChannel4.setLockscreenVisibility(1);
        notificationManager.createNotificationChannel(notificationChannel4);
        NotificationChannel notificationChannel5 = new NotificationChannel(CHANNEL_LINK_LOSS, context.getString(R.string.channel_link_loss), 4);
        notificationChannel5.setDescription(context.getString(R.string.channel_link_loss_description));
        notificationChannel5.setShowBadge(true);
        notificationChannel5.setLockscreenVisibility(1);
        notificationManager.createNotificationChannel(notificationChannel5);
    }

    public static void registerNewConnectionListener(Context context, NewConnectionListener newConnectionListener) {
        unregisterNewConnectionListener(context, newConnectionListener);
        NewConnectionBroadcastReceiver newConnectionBroadcastReceiver = new NewConnectionBroadcastReceiver(newConnectionListener);
        mNewConnectionBroadcastReceiver = newConnectionBroadcastReceiver;
        context.registerReceiver(newConnectionBroadcastReceiver, new IntentFilter(ACTION_CONNECT));
    }

    public static void showIncomingConnectionNotification(Context context, BluetoothDevice bluetoothDevice) {
        String string = context.getString(R.string.notif_incoming_connection_title, bluetoothDevice.getName() != null ? bluetoothDevice.getName() : context.getString(R.string.unnamed_device));
        g.d dVar = new g.d(context, CHANNEL_INCOMING_CONNECTION);
        dVar.c(string);
        dVar.b(context.getString(R.string.notif_incoming_connection_text));
        g.b bVar = new g.b();
        bVar.a(context.getString(R.string.notif_incoming_connection_explanation));
        dVar.a(bVar);
        dVar.a("status");
        dVar.e(true);
        dVar.d(R.drawable.ic_stat_notify_nrf_connect);
        dVar.c(1);
        dVar.b(true);
        dVar.a(context.getResources().getColor(R.color.nordicBlue));
        dVar.e(string);
        Intent intent = new Intent(ACTION_CONNECT);
        intent.putExtra(EXTRA_SHOULD_CONNECT, true);
        intent.putExtra(EXTRA_DEVICE, bluetoothDevice);
        intent.putExtra(EXTRA_SHOULD_OPEN_APP, true);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, bluetoothDevice.hashCode(), intent, 268435456);
        dVar.a(broadcast);
        Intent intent2 = new Intent(ACTION_CONNECT);
        intent2.putExtra(EXTRA_SHOULD_CONNECT, true);
        intent2.putExtra(EXTRA_DEVICE, bluetoothDevice);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(context, bluetoothDevice.hashCode() + 1, intent2, 268435456);
        Intent intent3 = new Intent(ACTION_CONNECT);
        intent3.putExtra(EXTRA_SHOULD_CONNECT, false);
        intent3.putExtra(EXTRA_DEVICE, bluetoothDevice);
        PendingIntent broadcast3 = PendingIntent.getBroadcast(context, bluetoothDevice.hashCode() + 2, intent3, 268435456);
        dVar.a(new g.a.C0049a(R.drawable.ic_nav_scanner_normal, context.getString(R.string.yes_and_open), broadcast).a());
        dVar.a(new g.a.C0049a(R.drawable.ic_nav_scanner_normal, context.getString(R.string.yes), broadcast2).a());
        dVar.a(new g.a.C0049a(R.drawable.ic_action_clear_normal, context.getString(R.string.no), broadcast3).a());
        ((NotificationManager) context.getSystemService("notification")).notify(bluetoothDevice.getAddress(), NOTIFICATION_ID, dVar.a());
    }

    public static void unregisterNewConnectionListener(Context context, NewConnectionListener newConnectionListener) {
        NewConnectionBroadcastReceiver newConnectionBroadcastReceiver = mNewConnectionBroadcastReceiver;
        if (newConnectionBroadcastReceiver == null || newConnectionBroadcastReceiver.mListener != newConnectionListener) {
            return;
        }
        context.unregisterReceiver(mNewConnectionBroadcastReceiver);
        mNewConnectionBroadcastReceiver = null;
    }
}
