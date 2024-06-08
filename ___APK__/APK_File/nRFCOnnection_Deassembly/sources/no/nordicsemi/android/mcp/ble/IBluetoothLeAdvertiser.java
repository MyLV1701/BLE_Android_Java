package no.nordicsemi.android.mcp.ble;

/* loaded from: classes.dex */
public interface IBluetoothLeAdvertiser {
    public static final String BROADCAST_ADVERTISING_STATE_CHANGED = "ADV_STATE_CHANGED_";
    public static final String EXTRA_STATE = "no.nordicsemi.android.mcp.EXTRA_STATE";
    public static final String EXTRA_TX_POWER = "no.nordicsemi.android.mcp.EXTRA_TX_POWER";

    /* loaded from: classes.dex */
    public interface AdvertisingStartedObserver {
        void onAdvertisingStarted();
    }

    /* loaded from: classes.dex */
    public interface AdvertisingStoppedObserver {
        void onAdvertisingStopped();
    }

    int getAdvertisingTxPower(long j);

    boolean isAdvertisementActive(long j);

    boolean isAdvertising();

    void startAdvertisement(long j, int i, int i2, AdvertisingStatusCallback advertisingStatusCallback);

    void stopAdvertisement(long j);

    void stopAllAdvertisements();
}
