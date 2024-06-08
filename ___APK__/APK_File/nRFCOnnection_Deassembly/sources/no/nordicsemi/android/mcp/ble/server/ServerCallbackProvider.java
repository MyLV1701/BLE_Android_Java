package no.nordicsemi.android.mcp.ble.server;

import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Handler;
import no.nordicsemi.android.mcp.ble.server.impl.CurrentTimeServiceImpl;
import no.nordicsemi.android.mcp.ble.server.impl.HeartRateServiceImpl;
import no.nordicsemi.android.mcp.ble.server.impl.ImmediateAlertServiceImpl;
import no.nordicsemi.android.mcp.ble.server.impl.LinkLossServiceImpl;
import no.nordicsemi.android.mcp.server.domain.Service;

/* loaded from: classes.dex */
public class ServerCallbackProvider {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: no.nordicsemi.android.mcp.ble.server.ServerCallbackProvider$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService = new int[Service.PredefinedService.values().length];

        static {
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[Service.PredefinedService.LINK_LOSS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[Service.PredefinedService.IMMEDIATE_ALERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[Service.PredefinedService.CURRENT_TIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[Service.PredefinedService.HEART_RATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[Service.PredefinedService.CUSTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static IServerCallback newCallback(Context context, Handler handler, ServiceServerController serviceServerController, Service service, ServiceMap serviceMap, BluetoothGattService bluetoothGattService) {
        int i = AnonymousClass1.$SwitchMap$no$nordicsemi$android$mcp$server$domain$Service$PredefinedService[service.getConfiguration().ordinal()];
        if (i == 1) {
            return new LinkLossServiceImpl(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        }
        if (i == 2) {
            return new ImmediateAlertServiceImpl(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        }
        if (i == 3) {
            return new CurrentTimeServiceImpl(context, handler, serviceServerController, serviceMap, bluetoothGattService);
        }
        if (i != 4) {
            return null;
        }
        return new HeartRateServiceImpl(context, handler, serviceServerController, serviceMap, bluetoothGattService);
    }
}
