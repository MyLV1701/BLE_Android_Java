package io.runtime.mcumgr;

import io.runtime.mcumgr.response.McuMgrResponse;

/* loaded from: classes.dex */
public interface McuMgrTransport {

    /* loaded from: classes.dex */
    public interface ConnectionCallback {
        void onConnected();

        void onDeferred();

        void onError(Throwable th);
    }

    /* loaded from: classes.dex */
    public interface ConnectionObserver {
        void onConnected();

        void onDisconnected();
    }

    void addObserver(ConnectionObserver connectionObserver);

    void connect(ConnectionCallback connectionCallback);

    McuMgrScheme getScheme();

    void release();

    void removeObserver(ConnectionObserver connectionObserver);

    <T extends McuMgrResponse> T send(byte[] bArr, Class<T> cls);

    <T extends McuMgrResponse> void send(byte[] bArr, Class<T> cls, McuMgrCallback<T> mcuMgrCallback);
}
