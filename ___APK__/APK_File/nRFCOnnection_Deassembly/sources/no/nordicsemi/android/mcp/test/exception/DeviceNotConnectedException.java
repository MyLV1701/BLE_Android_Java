package no.nordicsemi.android.mcp.test.exception;

/* loaded from: classes.dex */
public class DeviceNotConnectedException extends Exception {
    private static final long serialVersionUID = 8313508975599035110L;

    public DeviceNotConnectedException() {
        super("Device is not connected. Did you forget to use <connect /> tag?");
    }
}
