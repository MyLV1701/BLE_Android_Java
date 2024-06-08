package io.runtime.mcumgr;

/* loaded from: classes.dex */
public enum McuMgrScheme {
    BLE,
    COAP_BLE,
    COAP_UDP;

    public boolean isCoap() {
        return this == COAP_BLE || this == COAP_UDP;
    }
}
