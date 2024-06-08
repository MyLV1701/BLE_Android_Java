package no.nordicsemi.android.mcp.ble;

/* loaded from: classes.dex */
public interface ConnectionListener {

    /* loaded from: classes.dex */
    public interface OnServiceConnectedCallback {
        void onServiceConnected(IBluetoothLeConnection iBluetoothLeConnection);
    }

    void requestConnection(OnServiceConnectedCallback onServiceConnectedCallback);
}
